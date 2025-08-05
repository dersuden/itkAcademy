package org.example.itkacademy;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class WalletOperationRequest {
    private UUID walletId;
    private OperationType operationType;
    private Long amount;
}