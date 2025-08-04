package org.example.itkacademy;

import lombok.Data;

import java.util.UUID;

@Data
public class WalletOperationRequest {
    private UUID walletId;
    private OperationType operationType;
    private Long amount;
}
