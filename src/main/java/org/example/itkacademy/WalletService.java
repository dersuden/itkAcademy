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
        Wallets wallet = walletRepository.findById(walletId)
                .orElseGet(() -> {
                    Wallets newWallet = new Wallets(walletId);
                    newWallet.setBalance(0L);
                    return walletRepository.saveAndFlush(newWallet);
                });


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
                .map(Wallets::getBalance)
                .orElse(0L);
    }
}