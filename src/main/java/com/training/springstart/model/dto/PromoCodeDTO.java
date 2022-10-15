package com.training.springstart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PromoCodeDTO {

    public Integer id;

    @NotNull(message = "Value may not be null")
    @Size(min = 7, max = 12, message = "Value must be between 7 and 12 characters long")
    @Schema(description = "Value of a promo code.", example = "I3QW9uuw23", required = true)
    public String value;
    @NotNull(message = "Status may not be null")
    @Schema(description = "Status (active or disabled) of a promo code.", example = "true", required = true)
    public boolean isActive = Boolean.TRUE;

    @Schema(description = "Date of the registration", example = "2824.07.18 at 04:14:09", required = true)
    public String date =
            new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss")
                    .format(new Date());

}
