package com.training.springstart.controller.interfaces;

import com.training.springstart.model.dto.clientDTO.ClientAdminDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

public interface AdminLoggable {

    @Operation(summary = "This is endpoint to find admin and get it logged in.",
            description = "Create request to check admin login data.",
            tags = {"Admin Login"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "LOGGED IN. The admin has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified admin request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PostMapping("/admin-login")
    String checkAndLoginAdmin(@Valid ClientAdminDTO clientAdminDTO);

}
