package com.tec.billing.DirectDebit.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="ARUDD_STATUS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AruddStatus {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusCode")
    private String statusCode;

    @Column(name = "statusDescription")
    private String statusDescription;

    @Column(name = "ddiStatus")
    private String ddiStatus;

    // Getters and Setters

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @Override
    public String toString() {
        return "AruddStatus{" +
                "statusCode=" + statusCode +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }

    /*INSERT INTO ARUDD_STATUS (statusCode, statusDescription, ddiStatus) VALUES
        ('0', 'Refer to Payer', 'Active'),
        ('1', 'Instruction cancelled', 'Cancelled'),
        ('2', 'Payer Deceased', 'Cancelled'),
        ('3', 'Account transferred', 'Cancelled'),
        ('4', 'Advance Notice disputed', 'Suspended'),
        ('5', 'No Account', 'Cancelled'),
        ('6', 'No Instruction', 'Cancelled'),
        ('7', 'Amount differs', 'Active'),
        ('8', 'Amount not yet due', 'Active'),
        ('9', 'Presentation overdue', 'Active'),
        ('A', 'Originator differs', 'Active'),
        ('B', 'Account closed', 'Cancelled');*/

}
