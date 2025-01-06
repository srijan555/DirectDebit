package com.tec.billing.DirectDebit.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "OUTBOUND_MANDATE_INFORMATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class OutboundMandateInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    @Column(name = "seq_omi")
    private Long seqOmi;

    @Column(name = "account_name", length = 100)
    private String accountName;

    @Column(name = "account_no", length = 50)
    private String accountNo;

    @Column(name = "sort_code", length = 10)
    private String sortCode;

    @Column(name = "group_no", length = 20)
    private String groupNo;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "group_effective_date")
    @Temporal(TemporalType.DATE)
    private Date groupEffectiveDate;

    @Column(name = "system_entity_code", length = 50)
    private Long systemEntityCode;

    @Column(name = "originator_reference_number", length = 100)
    private String originatorReferenceNumber;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_type", length = 50)
    private String transactionType;

    @Column(name = "ddi_status", length = 20)
    private String ddiStatus;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "file_yn", length = 1)
    private String fileYn;

    @Column(name = "ddiStatus_prev_date")
    @Temporal(TemporalType.DATE)
    private Date ddiStatusPrevDate;

    @Column(name = "ddiStatus_update_date")
    @Temporal(TemporalType.DATE)
    private Date ddiStatusUpdateDate;

    @Column(name = "system_transaction_seq")
    private Long systemTransactionSeq;

    @Column(name = "active_mandate_date")
    @Temporal(TemporalType.DATE)
    private Date activeMandateDate;

    @Column(name = "Is_ADDACS_yn", length = 1)
    private String isAddacsYn;

    @Column(name = "ddiStatus_ADDACS_date")
    @Temporal(TemporalType.DATE)
    private Date ddiStatusAddacsDate;

    @Column(name = "Is_BankAmend_yn", length = 1)
    private String isBankAmendYn;

    @Column(name = "ddiStatus_BankAmend_date")
    @Temporal(TemporalType.DATE)
    private Date ddiStatusBankAmendDate;

    @Column(name = "Is_PaymentMethodAmend_yn", length = 1)
    private String isPaymentMethodAmendYn;

    @Column(name = "ddiStatus_PaymentMethodAmend_date")
    @Temporal(TemporalType.DATE)
    private Date ddiStatusPaymentMethodAmendDate;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public Long getSeqOmi() {
        return seqOmi;
    }

    public void setSeqOmi(Long seqOmi) {
        this.seqOmi = seqOmi;
    }

    @XmlElement
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @XmlElement
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @XmlElement
    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    @XmlElement
    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    @XmlElement
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getGroupEffectiveDate() {
        return groupEffectiveDate;
    }

    public void setGroupEffectiveDate(Date groupEffectiveDate) {
        this.groupEffectiveDate = groupEffectiveDate;
    }

    public Long getSystemEntityCode() {
        return systemEntityCode;
    }

    public void setSystemEntityCode(Long systemEntityCode) {
        this.systemEntityCode = systemEntityCode;
    }

    @XmlElement
    public String getOriginatorReferenceNumber() {
        return originatorReferenceNumber;
    }

    public void setOriginatorReferenceNumber(String originatorReferenceNumber) {
        this.originatorReferenceNumber = originatorReferenceNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDdiStatus() {
        return ddiStatus;
    }

    public void setDdiStatus(String ddiStatus) {
        this.ddiStatus = ddiStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getFileYn() {
        return fileYn;
    }

    public void setFileYn(String fileYn) {
        this.fileYn = fileYn;
    }

    public Date getDdiStatusPrevDate() {
        return ddiStatusPrevDate;
    }

    public void setDdiStatusPrevDate(Date ddiStatusPrevDate) {
        this.ddiStatusPrevDate = ddiStatusPrevDate;
    }

    public Date getDdiStatusUpdateDate() {
        return ddiStatusUpdateDate;
    }

    public void setDdiStatusUpdateDate(Date ddiStatusUpdateDate) {
        this.ddiStatusUpdateDate = ddiStatusUpdateDate;
    }

    public Long getSystemTransactionSeq() {
        return systemTransactionSeq;
    }

    public void setSystemTransactionSeq(Long systemTransactionSeq) {
        this.systemTransactionSeq = systemTransactionSeq;
    }

    public Date getActiveMandateDate() {
        return activeMandateDate;
    }

    public void setActiveMandateDate(Date activeMandateDate) {
        this.activeMandateDate = activeMandateDate;
    }

    public String getIsAddacsYn() {
        return isAddacsYn;
    }

    public void setIsAddacsYn(String isAddacsYn) {
        this.isAddacsYn = isAddacsYn;
    }

    public Date getDdiStatusAddacsDate() {
        return ddiStatusAddacsDate;
    }

    public void setDdiStatusAddacsDate(Date ddiStatusAddacsDate) {
        this.ddiStatusAddacsDate = ddiStatusAddacsDate;
    }

    public String getIsBankAmendYn() {
        return isBankAmendYn;
    }

    public void setIsBankAmendYn(String isBankAmendYn) {
        this.isBankAmendYn = isBankAmendYn;
    }

    public Date getDdiStatusBankAmendDate() {
        return ddiStatusBankAmendDate;
    }

    public void setDdiStatusBankAmendDate(Date ddiStatusBankAmendDate) {
        this.ddiStatusBankAmendDate = ddiStatusBankAmendDate;
    }

    public String getIsPaymentMethodAmendYn() {
        return isPaymentMethodAmendYn;
    }

    public void setIsPaymentMethodAmendYn(String isPaymentMethodAmendYn) {
        this.isPaymentMethodAmendYn = isPaymentMethodAmendYn;
    }

    public Date getDdiStatusPaymentMethodAmendDate() {
        return ddiStatusPaymentMethodAmendDate;
    }

    public void setDdiStatusPaymentMethodAmendDate(Date ddiStatusPaymentMethodAmendDate) {
        this.ddiStatusPaymentMethodAmendDate = ddiStatusPaymentMethodAmendDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}

