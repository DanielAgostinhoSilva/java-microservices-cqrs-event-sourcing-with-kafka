package com.techbank.account.query.infrastructure.handlers;

import com.techbanck.account.common.events.AccountClosedEvent;
import com.techbanck.account.common.events.AccountOpenedEvent;
import com.techbanck.account.common.events.FundsDepositedEvent;
import com.techbanck.account.common.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
