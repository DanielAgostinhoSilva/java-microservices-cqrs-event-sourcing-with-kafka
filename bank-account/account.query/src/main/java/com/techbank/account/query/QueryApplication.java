package com.techbank.account.query;

import com.techbank.account.query.api.queries.*;
import com.techbank.cqrs.core.infrastructure.QueryDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@AllArgsConstructor
@SpringBootApplication
public class QueryApplication {

	private QueryDispatcher queryDispatcher;
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountsByBalanceQuery.class, queryHandler::handle);
	}

}
