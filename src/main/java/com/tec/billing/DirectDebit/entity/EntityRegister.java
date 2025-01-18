package com.tec.billing.DirectDebit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ENTITY_REGISTER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntityRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "system_entity_code", length = 50)
    private Long systemEntityCode;

    @Column(name = "group_no", length = 50)
    private String groupNo;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "group_effective_date")
    @Temporal(TemporalType.DATE)
    private Date groupEffectiveDate;

    @Column(name = "account_no", length = 50)
    private String accountNo;

    @Column(name = "account_name", length = 100)
    private String accountName;

    @Column(name = "sort_code", length = 50)
    private String sortCode;

    @Column(name = "is_mandate_created_yn", length = 1)
    private String isMandateCreatedYN;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public Long getSystemEntityCode() {
        return systemEntityCode;
    }

    public void setSystemEntityCode(Long systemEntityCode) {
        this.systemEntityCode = systemEntityCode;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getIsMandateCreatedYN() {
        return isMandateCreatedYN;
    }

    public void setIsMandateCreatedYN(String isMandateCreatedYN) {
        this.isMandateCreatedYN = isMandateCreatedYN;
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
