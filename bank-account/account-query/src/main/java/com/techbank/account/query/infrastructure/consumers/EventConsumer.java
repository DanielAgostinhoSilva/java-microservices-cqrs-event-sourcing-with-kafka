package com.techbank.account.query.infrastructure.consumers;

import com.techbanck.account.common.events.AccountClosedEvent;
import com.techbanck.account.common.events.AccountOpenedEvent;
import com.techbanck.account.common.events.FundsDepositedEvent;
import com.techbanck.account.common.events.FundsWithdrawnEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consumer(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consumer(@Payload FundsDepositedEvent event, Acknowledgment ack);
    void consumer(@Payload FundsWithdrawnEvent event, Acknowledgment ack);
    void consumer(@Payload AccountClosedEvent event, Acknowledgment ack);
}
