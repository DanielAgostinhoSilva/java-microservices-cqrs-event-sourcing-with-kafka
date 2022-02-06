package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.DepositFundsCommand;
import com.techbank.account.cmd.api.dto.OpenAccountResponse;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.exceptions.AggregateNotFoundException;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/depositFunds")
public class DepositFundsController {

    private final Logger logger = Logger.getLogger(DepositFundsController.class.getName());
    private CommandDispatcher commandDispatcher;


    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> depositFunds(
            @PathVariable("id") String id,
            @RequestBody DepositFundsCommand command
    ) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(
                    new BaseResponse("Deposit funds request completed successfully!"),
                    HttpStatus.OK
            );
        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(
                    Level.WARNING,
                    MessageFormat.format("Client made a bad request - {0}.", e.toString())
            );
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format(
                    "Error while processing request to deposit funds to bank account with id - {0}.",
                    id
            );
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(
                    new BaseResponse(safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
