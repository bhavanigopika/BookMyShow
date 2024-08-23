package com.ticket.bookmyshow.controllers;

import com.ticket.bookmyshow.dtos.ResponseStatus;
import com.ticket.bookmyshow.dtos.SignUpRequestDTO;
import com.ticket.bookmyshow.dtos.SignUpResponseDTO;
import com.ticket.bookmyshow.models.User;
import com.ticket.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO){
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        try{
            //name, email, password return the user object
            User user = userService.signUp(signUpRequestDTO.getName(), signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword());
            //once the user request all the details signUpResponseDTO provide user object and responseStatus
            signUpResponseDTO.setUser(user);
            signUpResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            //return signUpResponseDTO;
        }
        catch(Exception ex){
            signUpResponseDTO.setResponseStatus(ResponseStatus.FAILED);
            //return signUpResponseDTO;
        }
        return signUpResponseDTO;
    }



}
