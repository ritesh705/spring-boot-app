package com.ritesh.app.moneytransferapp.model;

import java.math.BigDecimal;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;

public class MoneyTransfer {
    @Schema(description = "Unique transfer identifier", example = "TXN123456789")
    private String transferId;

    @Schema(description = "Source account number", example = "ACC001", required = true)
    private String fromAccount;

    @Schema(description = "Destination account number", example = "ACC002", required = true)
    private String toAccount;

    @Schema(description = "Transfer amount", example = "1000.00", required = true)
    private BigDecimal amount;

    @Schema(description = "Currency code", example = "USD", required = true)
    private String currency;

    @Schema(description = "Transfer status", example = "COMPLETED", allowableValues = {"PENDING", "COMPLETED", "FAILED", "CANCELLED"})
    private String status;

    @Schema(description = "Transfer timestamp", example = "Wed Jul 16 20:30:45 IST 2026")
    private String timestamp;

    @Schema(description = "Transfer description or reason", example = "Transfer completed successfully")
    private String description;

    @Schema(description = "Reference number for tracking", example = "REF1234567890")
    private String referenceNumber;

    public MoneyTransfer() {
    }

    public MoneyTransfer(String fromAccount, String toAccount, BigDecimal amount, String currency) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currency = currency;
        this.status = "PENDING";
        this.timestamp = new Date().toString();
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
