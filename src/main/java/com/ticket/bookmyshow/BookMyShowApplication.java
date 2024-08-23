package com.ticket.bookmyshow;

import com.ticket.bookmyshow.controllers.UserController;
import com.ticket.bookmyshow.dtos.SignUpRequestDTO;
import com.ticket.bookmyshow.dtos.SignUpResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication  implements CommandLineRunner {
    //@Autowired or line no: 16,17,18
    private UserController userController;

    public BookMyShowApplication(UserController userController) {
        this.userController = userController;
    }
    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setName("Shekara");
        signUpRequestDTO.setEmail("vsb@gmail.com");
        signUpRequestDTO.setPassword("vidhushekara18");
        SignUpResponseDTO signUpResponseDTO = userController.signUp(signUpRequestDTO);

    }
}
