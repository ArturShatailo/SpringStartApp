package com.training.springstart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Setter
@Getter
public class PromoCodeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotNull(message = "Email of owner may not be null")
    @Schema(description = "Email address of a client that is owner of this promo_code.", example = "martinJefferson@mail.com", required = true)
    private String owner_email;

    @NotNull(message = "Value may not be null")
    @Size(min = 7, max = 12, message = "Value must be between 7 and 12 characters long")
    @Schema(description = "Value of a promo code.", example = "I3QW9uuw23", required = true)
    private String value;

    @NotNull(message = "Status may not be null")
    @Schema(description = "Status (active or disabled) of a promo code.", example = "true", required = true)
    private boolean status;

    @Schema(description = "Date of the registration", example = "2824.07.18 at 04:14:09", required = true)
    private String date =
            new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss")
                    .format(new Date());

}
