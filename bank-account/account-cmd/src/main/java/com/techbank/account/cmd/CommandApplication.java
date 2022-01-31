package com.techbank.account.cmd;

import com.techbank.account.cmd.api.commands.*;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@SpringBootApplication
public class CommandApplication {

    private CommandDispatcher commandDispatcher;
    private CommandHandler commandHandler;

    public static void main(String[] args) {
        SpringApplication.run(CommandApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handler);
        commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handler);
        commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handler);
        commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handler);
    }
}
