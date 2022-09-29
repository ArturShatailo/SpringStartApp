package com.training.springstart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
public class ClientAreaViewDTO {

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of a client.", example = "Martin", required = true)
    private String name;

    @NotNull(message = "Surname may not be null")
    @Size(min = 2, max = 32, message = "Surname must be between 2 and 32 characters long")
    @Schema(description = "Surname of a client.", example = "Jefferson", required = true)
    private String surname;

    @Email
    @NotNull(message = "Email number may not be null")
    @Schema(description = "Email address of a client.", example = "martinJefferson@mail.com", required = true)
    private String email;

    @NotNull(message = "Phone number may not be null")
    @Size(min = 11, max = 13, message = "Phone number must be between 11 and 13 characters long")
    @Schema(description = "Phone number of a client.", example = "+12465396454", required = true)
    private String phone_number;

}
