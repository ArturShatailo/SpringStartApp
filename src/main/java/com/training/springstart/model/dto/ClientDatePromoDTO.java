package com.training.springstart.model.dto;

import com.training.springstart.model.PromoCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ClientDatePromoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of a client.", example = "Martin", required = true)
    private String name;

    @NotNull(message = "Surname may not be null")
    @Size(min = 2, max = 32, message = "Surname must be between 2 and 32 characters long")
    @Schema(description = "Surname of a client.", example = "Jefferson", required = true)
    private String surname;

    @NotNull(message = "Password may not be null")
    @Size(min = 6, message = "Password must be longer than 6 characters")
    @Schema(description = "Password of a client.", example = "MEW)h8iubPN9gh4w", required = true)
    private String password;

    @NotNull(message = "Password repeat may not be null")
    @Size(min = 6, message = "Password repeat must be longer than 6 characters")
    @Schema(description = "Password repeat", example = "MEW)h8iubPN9gh4w", required = true)
    private String passwordRepeat;

    @Email
    @NotNull(message = "Email may not be null")
    @Schema(description = "Email address of a client.", example = "martinJefferson@mail.com", required = true)
    private String email;

    @NotNull(message = "Phone number may not be null")
    @Size(min = 11, max = 13, message = "Phone number must be between 11 and 13 characters long")
    @Schema(description = "Phone number of a client.", example = "+12465396454", required = true)
    private String phone_number;

    private boolean deleted;

    @Schema(description = "Promo code of a client", example = "NuI233i32", required = true)
    private PromoCode promo_code;

    @Schema(description = "Date of the registration", example = "2824.07.18 at 04:14:09", required = true)
    private String date =
            new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss")
                    .format(new Date());

}
