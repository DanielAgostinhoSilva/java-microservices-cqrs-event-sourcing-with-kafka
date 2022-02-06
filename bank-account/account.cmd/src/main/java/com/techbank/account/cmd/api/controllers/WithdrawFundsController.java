package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.DepositFundsCommand;
import com.techbank.account.cmd.api.commands.WithdrawFundsCommand;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.exceptions.AggregateNotFoundException;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/withdrawFunds")
public class WithdrawFundsController {

    private final Logger logger = Logger.getLogger(WithdrawFundsController.class.getName());
    private CommandDispatcher commandDispatcher;


    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(
            @PathVariable("id") String id,
            @RequestBody WithdrawFundsCommand command
    ) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(
                    new BaseResponse("Withdraw funds request completed successfully!"),
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
                    "Error while processing request to withdraw funds from bank account with id - {0}.",
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
