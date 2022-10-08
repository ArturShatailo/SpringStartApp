package com.training.springstart.controller.interfaces;

import com.training.springstart.model.dto.clientDTO.ClientLoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

public interface ClientLoggable {

    @Operation(summary = "This is endpoint to find client and get it logged in.",
            description = "Create request to check client login data.",
            tags = {"Client Login API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "LOGGED IN. The client has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified client request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PostMapping(value = "/client-login")
    String checkAndLoginClient(@Valid ClientLoginDTO clientLoginDTO);

}
