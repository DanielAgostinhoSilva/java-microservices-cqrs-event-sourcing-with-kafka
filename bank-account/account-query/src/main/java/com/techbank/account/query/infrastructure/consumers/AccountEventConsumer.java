package com.techbank.account.query.infrastructure.consumers;

import com.techbanck.account.common.events.AccountClosedEvent;
import com.techbanck.account.common.events.AccountOpenedEvent;
import com.techbanck.account.common.events.FundsDepositedEvent;
import com.techbanck.account.common.events.FundsWithdrawnEvent;
import com.techbank.account.query.infrastructure.handlers.EventHandler;
import com.techbank.cqrs.core.infrastructure.EventStore;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountEventConsumer implements EventConsumer {

    private EventHandler accountEventHandler;

    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(AccountOpenedEvent event, Acknowledgment ack) {
        accountEventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(FundsDepositedEvent event, Acknowledgment ack) {
        accountEventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsWithdrawnEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(FundsWithdrawnEvent event, Acknowledgment ack) {
        accountEventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(AccountClosedEvent event, Acknowledgment ack) {
        accountEventHandler.on(event);
        ack.acknowledge();
    }
}
