package com.training.springstart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClientAdminDTO {

    @Email
    @NotNull(message = "Email number may not be null")
    @Schema(description = "Email address of a client.", example = "martinJefferson@mail.com", required = true)
    private String email;

    @NotNull(message = "Password may not be null")
    @Size(min = 6, message = "Password must be longer than 6 characters")
    @Schema(description = "Password of a client.", example = "MEW)h8iubPN9gh4w", required = true)
    private String password;

    @Schema(description = "Role of the person", example = "admin", required = true)
    private String status = "admin";

}
