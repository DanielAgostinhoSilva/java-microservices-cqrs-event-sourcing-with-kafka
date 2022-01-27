package com.techbank.cqrs.core.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handler(T command);
}
