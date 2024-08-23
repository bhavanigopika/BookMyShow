package com.ticket.bookmyshow.services;

import com.ticket.bookmyshow.exceptions.ShowNotFoundException;
import com.ticket.bookmyshow.exceptions.ShowSeatsNoLongerAvailableException;
import com.ticket.bookmyshow.exceptions.UserNotFoundException;
import com.ticket.bookmyshow.models.Booking;
import com.ticket.bookmyshow.models.BookingStatus;
import com.ticket.bookmyshow.models.Show;
import com.ticket.bookmyshow.models.ShowSeat;
import com.ticket.bookmyshow.models.ShowSeatStatus;
import com.ticket.bookmyshow.models.User;
import com.ticket.bookmyshow.repositories.ShowRepository;
import com.ticket.bookmyshow.repositories.ShowSeatRepository;
import com.ticket.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    UserRepository userRepository;
    ShowRepository showRepository;
    ShowSeatRepository showSeatRepository;
    PriceCalculationService priceCalculationService;

    //inject user repository object by constructorDependency injection
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository ShowSeatRepository, PriceCalculationService priceCalculationService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = ShowSeatRepository;
        this.priceCalculationService = priceCalculationService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIdList) throws UserNotFoundException, ShowNotFoundException, ShowSeatsNoLongerAvailableException {
        //Approach 2:
        /*
            1) Get the user object with the user id
            2) Get the sshow object with the show id
            3) Get all the show seat objects from the showSeatIdList
            4) Now, check if all the seats are available…
                        a. If no, then throw an exception
                        b. If yes, proceed with the booking
                                 i.	Put a lock
                                ii.	Mark the selected seats as BLOCKED
                               iii.	Release a lock
                                iv.	Create the booking and make the payment
                                 v.	If payment succeeds
                                        1. Again, put a lock here
                                        2. Mark the selected seats as BOOKED
                                        3. Release a lock
                                        4. Return the booking ticket to the user
                                vi.	If payment fails (or) timer expires
                                        1. Again, put a lock here
                                        2. Mark the selected seats as AVAILABLE
                                        3. Release a lock

         */
        //Approach 1:
        //First, Get the user object using userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            //go to the signup workflow, ask the user to register
            throw new UserNotFoundException("User with userId" + userId + " is not valid.");
        }
        //if the user object is present in the UserRepository, then get the user object
        User user = optionalUser.get();

        /*************************************************************************************************************/
        //Second, Get the show object using showId
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show with showId" + showId + " is not present in the database.");
        }
        //if the show object is present in the ShowRepository, then get the show object
        Show show = optionalShow.get();

        /*************************************************************************************************************/
        //Third, Get the ShowSeat object using the List<ShowSeat>
        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIdList);

        //if there is no show seat in list of showSeat, then we can tell to choose the seat in the show
        if(showSeatList.size() == 0){
            throw new RuntimeException("Please select atleast one seat to proceed");
        }

        //if the seats are not available in the show, then throw the exception with message
        for(ShowSeat showSeat : showSeatList){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatsNoLongerAvailableException("Some seats are not available in the current show");
            }
        }

        //if the seats available in the show, then blocked the seat
        for(ShowSeat showSeat : showSeatList){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            //blocking the seatstatus in in-memory not in database
            //now, save to the database, some of the JPA methods is build...so, no need to type save method in showSeatRepository
            showSeatRepository.save(showSeat);
        }

        /*************************************************************************************************************/

        //see BookingMovieRequestDTO...now, we made the user object, show object, list of showSeat object...
        //checking seats are available...once it is available, then blocked
        //now make the booking process...

        //Create the booking object and move to the payment object
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSeatList(showSeatList);
        //As of now, booking status is pending, once the payment is success/confirmed then, booking status is confirmed
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculationService.calculatePrice(showSeatList, show));

        //After booking, move to the payment page

        /*
        Here, 3rd party payment gateway will work that is integrating with Razorpay integration
        Razorpay confirms the payment and sends you the reference id
        once get the reference id and payment is successful, then booking is confirmed
        now change the BookingStatus to confirmed -> BookingStatus.CONFIRMED
        if the payment fails, then BookingStatus is still pending -> BookingStatus.PENDING
        */

        /*
        Update booking status and seat status in database:

        Save the booking and showSeatStatus in database, then only we get booking object and
        return the booking object to the client.
        if booking succeeds -> make the showSeatStatus as permanently BOOKED
        if booking fails -> make the showSeatStatus AVAILABLE
        */

        /*
        if anything fails either of the above steps, the controller will handle that.
        Controller see the catch block and see the responseStatus as FAILED,
        and controller tells the client the status as FAILED, it means request is FAILED
         */
        return booking;
    }
}
