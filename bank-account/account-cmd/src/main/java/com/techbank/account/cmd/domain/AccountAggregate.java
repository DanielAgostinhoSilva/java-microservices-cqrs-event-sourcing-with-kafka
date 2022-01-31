package com.techbank.account.cmd.domain;

import com.techbanck.account.common.events.AccountClosedEvent;
import com.techbanck.account.common.events.AccountOpenedEvent;
import com.techbanck.account.common.events.FundsDepositedEvent;
import com.techbanck.account.common.events.FundsWithdrawnEvent;
import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.cqrs.core.domain.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private Boolean ative;
    private double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdDate(new Date())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpenBalance())
                .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.ative = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if(!ative) {
            throw new IllegalStateException("Founds cannot be deposited into a closed account!");
        }
        if(amount <= 0) {
            throw new IllegalStateException("The deposit amount must be greater than 0!");
        }
        raiseEvent(FundsDepositedEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsDepositedEvent event) {
        this.id = id;
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount) {
        if(!ative) {
            throw new IllegalStateException("Founds cannot be withdraw from a closed account!");
        }
        raiseEvent(FundsWithdrawnEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsWithdrawnEvent event) {
        this.id = id;
        this.balance -= event.getAmount();
    }

    public void clouseAccount() {
        if(!ative) {
            throw new IllegalStateException("The bank account has already been closed!");
        }
        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = id;
        this.ative = false;
    }
}
