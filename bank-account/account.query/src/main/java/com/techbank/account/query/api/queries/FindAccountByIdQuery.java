package com.techbank.account.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.Query;

@Data
@AllArgsConstructor
public class FindAccountByIdQuery extends Query {
    private String id;
}
