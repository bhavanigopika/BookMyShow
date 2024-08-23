package com.ticket.bookmyshow.dtos;

import com.ticket.bookmyshow.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    private User user;
    private ResponseStatus responseStatus;

}
