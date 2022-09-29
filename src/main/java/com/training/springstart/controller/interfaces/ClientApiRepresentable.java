package com.training.springstart.controller.interfaces;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.model.dto.ClientChangePassDTO;
import com.training.springstart.model.dto.ClientDateDTO;
import com.training.springstart.model.dto.ClientDatePromoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

public interface ClientApiRepresentable {

    @Operation(summary = "This is endpoint to create client after receiving ClientDTO object in RequestBody",
            description = "Create request to save client in DB.",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The client has been successfully added into database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PostMapping("/clients/create")
    @ResponseStatus(HttpStatus.CREATED)
    Client saveClient(@RequestBody @Valid ClientDateDTO clientDateDTO);

    @Operation(summary = "This is endpoint to find all Client objects and return list of them",
            description = "Create request to find all clients in DB",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. Clients have been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    List<Client> getAllClients();

    @Operation(summary = "This is endpoint to find all Client objects that wasn't deleted and return list of them",
            description = "Create request to find all not deleted clients in DB",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. Clients have been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @GetMapping("/clients/active")
    @ResponseStatus(HttpStatus.FOUND)
    List<Client> getNotDeletedClients();

    @Operation(summary = "This is endpoint to find Client object by id and return",
            description = "Create request to find client in DB by id field",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. Client has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Client getClientById(@PathVariable("id") Integer id);

    @Operation(summary = "This is endpoint to find Client object by id and update it",
            description = "Create request to find client in DB by id field and update",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "UPDATED. Client has been successfully found in database and updated."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT UPDATED. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    Client refreshClient(@PathVariable("id") Integer id, @RequestBody Client client);

    @Operation(summary = "This is endpoint to find Client object by 'email' from session and update it",
            description = "Create request to find client in DB by 'email' field and update",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "UPDATED. Client has been successfully found in database and updated."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT UPDATED. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PostMapping("/clients/update")
    @ResponseStatus(HttpStatus.OK)
    void updateClient(@RequestBody @Valid ClientAreaViewDTO clientAreaViewDTO, HttpServletResponse response) throws IOException;

    @Operation(summary = "This is endpoint to find Client object by email from session and update 'password' field",
            description = "Create request to find client in DB by id field and update 'password' field",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PASSWORD UPDATED. Client has been successfully found in database and password updated."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT DELETED. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PostMapping("/clients/update/password")
    @ResponseStatus(HttpStatus.OK)
    void updateClientPassword(@RequestBody @Valid ClientChangePassDTO clientChangePassDTO, HttpServletResponse response) throws IOException;

    @Operation(summary = "This is endpoint to find Client object by id and update 'deleted' field",
            description = "Create request to find client in DB by id field and update 'deleted' field",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "DELETED. Client has been successfully found in database and deleted."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT DELETED. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeClientById(@PathVariable("id") Integer id);

    @Operation(summary = "This is endpoint to find Client object by 'email' from session and return",
            description = "Create request to find client in DB by 'email' from session",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. Client has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @GetMapping("/get-client")
    @ResponseStatus(HttpStatus.FOUND)
    Client checkClientBySession(HttpServletResponse response) throws IOException;

    @Operation(summary = "This is endpoint to find all Client objects and return list of them with paging and sorting",
            description = "Create request to find all clients in DB with paging and sorting",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. Clients have been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified clients request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @GetMapping(value = "/clients/table", params = {"page", "size", "sort"})
    @ResponseStatus(HttpStatus.FOUND)
    List<Client> getClientsPage(@RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       @RequestParam(defaultValue = "id") String sort);

    @Operation(summary = "This is endpoint to find clients by phone code.",
            description = "Create request to find clients by phone code.",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. The client has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified client request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @GetMapping(value = "/clients/phones", params = {"phone_code"})
    @ResponseStatus(HttpStatus.FOUND)
    List<Client> getClientsByPhoneCodes(@RequestParam String phone_code);

    @Operation(summary = "This is endpoint to find clients by phone code with paging and sorting.",
            description = "Create request to find clients by phone code.",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "FOUND. The client has been successfully found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified client request not found."),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @GetMapping(value = "/clients/table/p")
    @ResponseStatus(HttpStatus.FOUND)
    Page<Client> getClientsPageByPhoneCode(@RequestParam String phone_code);

    @Operation(summary = "This is endpoint to create client after receiving ClientDTO object in RequestBody including PromoCode",
            description = "Create request to save client in DB and save new PromoCode in DB.",
            tags = {"Client API"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The client and it's promo code have been successfully added into database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "conflict in the request")})
    @PostMapping("/clients/promo-create")
    @ResponseStatus(HttpStatus.CREATED)
    Client saveClient(@RequestBody @Valid ClientDatePromoDTO clientDatePromoDTO);

}
