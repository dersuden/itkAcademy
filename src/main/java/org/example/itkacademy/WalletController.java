package org.example.itkacademy;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public void processOperation(@RequestBody WalletOperationRequest request) {
        walletService.processOperation(
                request.getWalletId(),
                request.getOperationType(),
                request.getAmount()
        );
    }

    @GetMapping("/{walletId}")
    public Long getBalance(@PathVariable UUID walletId) {
        return walletService.getBalance(walletId);
    }
}