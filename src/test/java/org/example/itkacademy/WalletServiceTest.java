package org.example.itkacademy;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.UUID;

@SpringBootTest
public class WalletServiceTest {
    @Autowired
    private WalletService walletService;

    @Test
    public void testDepositAndWithdraw() {
        UUID walletId = UUID.randomUUID();


        assert walletService.getBalance(walletId) == 0;


        walletService.processOperation(walletId, OperationType.DEPOSIT, 1000L);
        assert walletService.getBalance(walletId) == 1000;

        walletService.processOperation(walletId, OperationType.WITHDRAW, 500L);
        assert walletService.getBalance(walletId) == 500;
    }

    @Test(expected = IllegalStateException.class)
    public void testInsufficientFunds() {
        UUID walletId = UUID.randomUUID();
        walletService.processOperation(walletId, OperationType.WITHDRAW, 1000L);
    }
}
