package ma.enset.comptecqrses.commands.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ma.enset.comptecqrses.commonApi.commands.CreateAccountCommand;
import ma.enset.comptecqrses.commonApi.commands.CreditAccountCommand;
import ma.enset.comptecqrses.commonApi.commands.DebitAccountCommand;
import ma.enset.comptecqrses.commonApi.dto.CreateAccountRequestDTO;
import ma.enset.comptecqrses.commonApi.dto.CreditAccountDTO;
import ma.enset.comptecqrses.commonApi.dto.DebitAccountDTO;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/commands/account")
public class AccountCommandController {
    private EventStore eventStore;
    private CommandGateway commandGateway;
    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO createAccountDto) {
        CompletableFuture<String> response =  commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), createAccountDto.getInitialBalance(), createAccountDto.getCurrency()));
        return response;
    }
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return e.getMessage();
    }
    @GetMapping("/eventStore/{id}")
    public Stream eventStore(@PathVariable String id){
        return eventStore.readEvents(id).asStream();
    }
    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountDTO creditAccountDTO) {
        CompletableFuture<String> response =  commandGateway.send(new CreditAccountCommand(creditAccountDTO.getAccountID(), creditAccountDTO.getAmount(), creditAccountDTO.getCurrency()));
        return response;
    }
    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountDTO debitAccountDTO) {
        CompletableFuture<String> response =  commandGateway.send(new DebitAccountCommand(debitAccountDTO.getAccountID(), debitAccountDTO.getAmount(), debitAccountDTO.getCurrency()));
        return response;
    }
}
