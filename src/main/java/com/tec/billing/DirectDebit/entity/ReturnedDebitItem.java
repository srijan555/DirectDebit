package com.tec.billing.DirectDebit.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "RETURNED_DEBIT_ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReturnedDebitItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "returnedDebitItemSeq")
    private Long returnedDebitItemSeq;

    @Column(name = "reference")
    private String reference;

    @Column(name = "amount")
    private String amount;

    @Column(name = "accountName")
    private String accountName;

    @Column(name = "accountNo")
    private String accountNo;

    @Column(name = "sortCode")
    private String sortCode;

    @Column(name = "currency")
    private String currency;

    @Column(name = "transactionCode")
    private String transactionCode;

    @Column(name = "returnCode")
    private String returnCode;

    @Column(name = "reasonForReturn")
    private String reasonForReturn;

    @Column(name = "processingDate")
    private String processingDate;

    @Column(name = "notifiedYn")
    private String notifiedYn;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "groupNo")
    private String groupNo;

    @Column(name = "createdOn")
    private LocalDateTime createdOn;

    @Column(name = "createdBy")
    private String createdBy;

    // Getters and Setters

    public Long getReturnedDebitItemSeq() {
        return returnedDebitItemSeq;
    }

    public void setReturnedDebitItemSeq(Long returnedDebitItemSeq) {
        this.returnedDebitItemSeq = returnedDebitItemSeq;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReasonForReturn() {
        return reasonForReturn;
    }

    public void setReasonForReturn(String reasonForReturn) {
        this.reasonForReturn = reasonForReturn;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public String getNotifiedYn() {
        return notifiedYn;
    }

    public void setNotifiedYn(String notifiedYn) {
        this.notifiedYn = notifiedYn;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "ReturnedDebitItem{" +
                "returnedDebitItemSeq=" + returnedDebitItemSeq +
                ", reference='" + reference + '\'' +
                ", amount=" + amount +
                ", accountName='" + accountName + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", sortCode='" + sortCode + '\'' +
                ", currency='" + currency + '\'' +
                ", transactionCode='" + transactionCode + '\'' +
                ", returnCode='" + returnCode + '\'' +
                ", reasonForReturn='" + reasonForReturn + '\'' +
                ", processingDate=" + processingDate +
                ", notifiedYn='" + notifiedYn + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupNo='" + groupNo + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
