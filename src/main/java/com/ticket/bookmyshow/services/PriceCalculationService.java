package com.ticket.bookmyshow.services;

import com.ticket.bookmyshow.models.SeatType;
import com.ticket.bookmyshow.models.Show;
import com.ticket.bookmyshow.models.ShowSeat;
import com.ticket.bookmyshow.models.ShowSeatType;
import com.ticket.bookmyshow.repositories.ShowSeatRepository;
import com.ticket.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceCalculationService {
    ShowSeatTypeRepository showSeatTypeRepository;

    //Apply constructor dependency injection
    PriceCalculationService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public int calculatePrice(List<ShowSeat> showSeatList, Show show){
        //Show bookingThisShow = showSeatList.get(0).getShow();

        //you will get price based on show + seatType -> ShowSeatType -> Refer model class
        List<ShowSeatType> showSeatTypeList = showSeatTypeRepository.findAllByShow(show);

        //And the response for the above line is a) see below
        /*
        a) ShowSeatType:                b) ShowSeat:                     c) Seat:
        show    seatType    price       Show    Seat   status            SeatNumber seatType rowVal colVal
         4       GOLD       200          4       1       AVAILABLE         11       CLASSIC    1       1
         4       PRIME      100          4       2       BOOKED            25       PRIME      2       5
         4       DIAMOND    300                                            88       PRIME      8       8
         4       CLASSIC    65           6      10       BLOCKED           82       DIAMOND    8       2
         4       PLATINUM   400          6       9       AVAILABLE         97       GOLD       9       7
                                         6       1       BOOKED

                                         5       9       BOOKED
         */

        //now get the price
        int totalAmount = 0;
        //iterate all the showSeat
        for(ShowSeat showSeat : showSeatList){
            //get the currentShowSeatType to find the price...I am iterating the ShowSeat
            SeatType currentSeatType = showSeat.getSeat().getSeatType();

            /*BELOW LINE, get the showSeatType from database to find out seatType by findBySeatType
            so, already we have 1 db call from showSeatType to get show...no need to do multiple db call to get seatType*/

            /*
            Optional<ShowSeatType> showSeatType1 = showSeatTypeRepository.findBySeatType(currentSeatType);
            //now get the price
            if(showSeatType1.isPresent()){
                totalAmount = totalAmount + showSeatType1.get().getPrice();
            }
            */

            /*or, just iterate the showSeatType database to find out the same seatType and get the price by findAllByShow
            Say, there are 4 different seat types in show(at max)...So first iterate the seatType in a show (i.e) ShowSeatType*/
            for(ShowSeatType showSeatType : showSeatTypeList){
                //if showSeatType is same with the currentSeatType
                if(showSeatType.getSeatType() == (currentSeatType)){
                    totalAmount = totalAmount + showSeatType.getPrice();
                    //I got the amount for the seatType for that show
                    break;
                }
            }
        }
        return totalAmount;
    }

}
/*
List<ShowSeat> -> say, we have 5 list of showseat object...
               -> In one booking, we have same show_id...get the currentbookingshow by index
    show_id seat_id status
 1.   4       2      AVAILABLE
 2.   4       5      BOOKED
 3.   4       2      BLOCKED
 4.   4       1      BOOKED
 5.   4       6      AVAILABLE

 SO, we can get currentBooking show by
        Show bookingThisShow = showSeatList.get(0).getShow()
 (or)
 you can you can use separate show object
 */
