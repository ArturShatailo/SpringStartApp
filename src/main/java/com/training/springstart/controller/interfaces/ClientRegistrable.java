package com.training.springstart.controller.interfaces;

import com.training.springstart.model.dto.clientDTO.ClientDateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public interface ClientRegistrable {

    @Operation(summary = "This is endpoint to check and register new client and save a record in database",
            description = "Create request to check client register data and save a new client object into DB.",
            tags = {"Client Register API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "REGISTERED. The client has been successfully registered in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PostMapping(value = "/client-register")
    String saveClient(@Valid ClientDateDTO clientDateDTO);

}
