package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.CloseAccountCommand;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.exceptions.AggregateNotFoundException;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/closeAccount")
public class CloseAccountController {
    private final Logger logger = Logger.getLogger(CloseAccountController.class.getName());
    private CommandDispatcher commandDispatcher;

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable("id") String id) {
        try {
            commandDispatcher.send(new CloseAccountCommand(id));
            return new ResponseEntity<BaseResponse>(
                    new BaseResponse("Bank account closure request successfully completed!"),
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
                    "Error while processing request to close bank account with id - {0}.",
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
