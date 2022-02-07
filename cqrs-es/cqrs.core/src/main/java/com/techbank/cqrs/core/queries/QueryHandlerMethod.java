package com.techbank.cqrs.core.queries;

import com.techbank.cqrs.core.domain.BaseEntity;
import com.techbank.cqrs.core.events.BaseEvent;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handler(T query);
}
