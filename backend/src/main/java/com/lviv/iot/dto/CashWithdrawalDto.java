package com.lviv.iot.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "cashWithdrawal", collectionRelation = "cashWithdrawals")
public class CashWithdrawalDto extends RepresentationModel<CashWithdrawalDto> {
    private final String serviceMemberName;
    private final String serviceMemberLastName;
    private final String vendingMachineModelName;
    private final Integer amount;
}