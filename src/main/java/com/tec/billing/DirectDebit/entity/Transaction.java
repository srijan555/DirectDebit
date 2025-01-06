package com.tec.billing.DirectDebit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "TRANSACTION_LOG")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "system_transaction_seq", length = 50)
    private Long systemTransactionSeq;

    private String transactionType;

    @Temporal(TemporalType.DATE)
    private Date transactionEffectiveDate;

    private Long systemEntityCode;

    public Long getSystemEntityCode() {
        return systemEntityCode;
    }

    public void setSystemEntityCode(Long systemEntityCode) {
        this.systemEntityCode = systemEntityCode;
    }

    public Long getSystemTransactionSeq() {
        return systemTransactionSeq;
    }

    public void setSystemTransactionSeq(Long systemTransactionSeq) {
        this.systemTransactionSeq = systemTransactionSeq;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionEffectiveDate() {
        return transactionEffectiveDate;
    }

    public void setTransactionEffectiveDate(Date transactionEffectiveDate) {
        this.transactionEffectiveDate = transactionEffectiveDate;
    }
}
