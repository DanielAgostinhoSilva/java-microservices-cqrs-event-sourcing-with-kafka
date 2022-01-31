package com.techbank.account.cmd.api.commands;

import com.techbank.account.cmd.domain.AccountAggregate;
import com.techbank.cqrs.core.handlers.EventSourcingHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountCommandHandler implements CommandHandler{

    private EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    @Override
    public void handler(OpenAccountCommand command) {
        var aggregate = new AccountAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handler(DepositFundsCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.depositFunds(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handler(WithdrawFundsCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        if (command.getAmount() > aggregate.getBalance()) {
            throw new IllegalStateException("Withdraw declined, insufficient funds!");
        }
        aggregate.withdrawFunds(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handler(CloseAccountCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.clouseAccount();
        eventSourcingHandler.save(aggregate);
    }
}
