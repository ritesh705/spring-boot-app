package com.ritesh.app.moneytransferapp.service;

import com.ritesh.app.moneytransferapp.model.MoneyTransfer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MoneyTransferService {
    
    private Map<String, MoneyTransfer> transferMap = new ConcurrentHashMap<>();
    private Map<String, BigDecimal> accountBalances = new ConcurrentHashMap<>();
    
    public MoneyTransferService() {
        // Initialize with some sample accounts
        accountBalances.put("ACC001", new BigDecimal("10000.00"));
        accountBalances.put("ACC002", new BigDecimal("5000.00"));
        accountBalances.put("ACC003", new BigDecimal("15000.00"));
    }

    public MoneyTransfer initiateTransfer(MoneyTransfer transfer) {
        // Validate accounts
        if (!accountBalances.containsKey(transfer.getFromAccount())) {
            throw new IllegalArgumentException("Source account does not exist");
        }
        if (!accountBalances.containsKey(transfer.getToAccount())) {
            throw new IllegalArgumentException("Destination account does not exist");
        }
        
        // Validate amount
        if (transfer.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        
        // Check sufficient balance
        BigDecimal fromBalance = accountBalances.get(transfer.getFromAccount());
        if (fromBalance.compareTo(transfer.getAmount()) < 0) {
            transfer.setStatus("FAILED");
            transfer.setDescription("Insufficient funds");
            transfer.setTransferId(generateTransferId());
            transferMap.put(transfer.getTransferId(), transfer);
            return transfer;
        }
        
        // Generate transfer ID
        transfer.setTransferId(generateTransferId());
        transfer.setReferenceNumber(generateReferenceNumber());
        
        // Process transfer
        processTransfer(transfer);
        
        return transfer;
    }

    public MoneyTransfer getTransferById(String transferId) {
        return transferMap.get(transferId);
    }

    public List<MoneyTransfer> getTransfersByAccount(String accountNumber) {
        List<MoneyTransfer> transfers = new ArrayList<>();
        for (MoneyTransfer transfer : transferMap.values()) {
            if (transfer.getFromAccount().equals(accountNumber) || 
                transfer.getToAccount().equals(accountNumber)) {
                transfers.add(transfer);
            }
        }
        return transfers;
    }

    public BigDecimal getAccountBalance(String accountNumber) {
        return accountBalances.get(accountNumber);
    }

    public MoneyTransfer cancelTransfer(String transferId) {
        MoneyTransfer transfer = transferMap.get(transferId);
        if (transfer == null) {
            throw new IllegalArgumentException("Transfer not found");
        }
        
        if (!transfer.getStatus().equals("PENDING")) {
            throw new IllegalArgumentException("Only pending transfers can be cancelled");
        }
        
        transfer.setStatus("CANCELLED");
        transfer.setDescription("Transfer cancelled by user");
        return transfer;
    }

    private void processTransfer(MoneyTransfer transfer) {
        try {
            // Deduct from source account
            BigDecimal fromBalance = accountBalances.get(transfer.getFromAccount());
            accountBalances.put(transfer.getFromAccount(), fromBalance.subtract(transfer.getAmount()));
            
            // Add to destination account
            BigDecimal toBalance = accountBalances.get(transfer.getToAccount());
            accountBalances.put(transfer.getToAccount(), toBalance.add(transfer.getAmount()));
            
            transfer.setStatus("COMPLETED");
            transfer.setDescription("Transfer completed successfully");
            
        } catch (Exception e) {
            transfer.setStatus("FAILED");
            transfer.setDescription("Transfer failed: " + e.getMessage());
        }
        
        transferMap.put(transfer.getTransferId(), transfer);
    }

    private String generateTransferId() {
        return "TXN" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private String generateReferenceNumber() {
        return "REF" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
