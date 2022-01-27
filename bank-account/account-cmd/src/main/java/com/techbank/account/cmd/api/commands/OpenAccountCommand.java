package com.techbank.account.cmd.api.commands;

import com.techbanck.account.common.dto.AccountType;
import com.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openBalance;
}
