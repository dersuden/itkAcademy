package org.example.itkacademy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void processOperation(UUID walletId, OperationType operationType, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Wallet wallet = walletRepository.findById(walletId)
                .orElseGet(() -> createNewWallet(walletId));

        if (operationType == OperationType.WITHDRAW && wallet.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds");
        }

        long newBalance = operationType == OperationType.DEPOSIT
                ? wallet.getBalance() + amount
                : wallet.getBalance() - amount;

        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
    }

    public Long getBalance(UUID walletId) {
        return walletRepository.findById(walletId)
                .map(Wallet::getBalance)
                .orElse(0L);
    }

    private Wallet createNewWallet(UUID walletId) {
        Wallet wallet = new Wallet();
        wallet.setId(walletId);
        wallet.setBalance(0L);
        return wallet;
    }
}
