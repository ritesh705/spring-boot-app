package com.ritesh.app.moneytransferapp.controller;

import com.ritesh.app.moneytransferapp.model.MoneyTransfer;
import com.ritesh.app.moneytransferapp.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/transfers")
@Tag(name = "Money Transfer", description = "Money transfer management APIs")
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @PostMapping("/initiate")
    @Operation(summary = "Initiate a money transfer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transfer initiated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request or insufficient funds")
    })
    public ResponseEntity<?> initiateTransfer(@RequestBody MoneyTransfer transfer) {
        try {
            MoneyTransfer result = moneyTransferService.initiateTransfer(transfer);
            if (result.getStatus().equals("FAILED")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{transferId}")
    public ResponseEntity<?> getTransferById(@PathVariable("transferId") String transferId) {
        MoneyTransfer transfer = moneyTransferService.getTransferById(transferId);
        if (transfer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transfer not found");
        }
        return ResponseEntity.ok(transfer);
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<?> getTransfersByAccount(@PathVariable("accountNumber") String accountNumber) {
        try {
            List<MoneyTransfer> transfers = moneyTransferService.getTransfersByAccount(accountNumber);
            return ResponseEntity.ok(transfers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<?> getAccountBalance(@PathVariable("accountNumber") String accountNumber) {
        BigDecimal balance = moneyTransferService.getAccountBalance(accountNumber);
        if (balance == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
        return ResponseEntity.ok(new BalanceResponse(accountNumber, balance));
    }

    @PostMapping("/{transferId}/cancel")
    public ResponseEntity<?> cancelTransfer(@PathVariable("transferId") String transferId) {
        try {
            MoneyTransfer transfer = moneyTransferService.cancelTransfer(transferId);
            return ResponseEntity.ok(transfer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Inner class for balance response
    private static class BalanceResponse {
        private String accountNumber;
        private BigDecimal balance;

        public BalanceResponse(String accountNumber, BigDecimal balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }
    }
}
