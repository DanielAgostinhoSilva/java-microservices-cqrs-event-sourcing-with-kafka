package com.techbank.account.query.api.queries;

import com.techbank.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountsByBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
