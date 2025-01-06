package com.tec.billing.DirectDebit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "OUTPUT_FORMS_SCHEDULE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OutputFormsSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outputSeq")
    private Long outputSeq;

    @Column(name = "outputDirectory")
    private String outputDirectory;

    @Column(name = "outputFormCode")
    private String outputFormCode;

    @Column(name = "outputFileName")
    private String outputFileName;

    @Column(name = "systemEntityCode")
    private Long systemEntityCode;

    @Column(name = "outputStatus")
    private String outputStatus;

    @Lob
    @Column(name = "outputResponse", columnDefinition = "TEXT")
    private String outputResponse;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public Long getOutputSeq() {
        return outputSeq;
    }

    public void setOutputSeq(Long outputSeq) {
        this.outputSeq = outputSeq;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getOutputFormCode() {
        return outputFormCode;
    }

    public void setOutputFormCode(String outputFormCode) {
        this.outputFormCode = outputFormCode;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public Long getSystemEntityCode() {
        return systemEntityCode;
    }

    public void setSystemEntityCode(Long systemEntityCode) {
        this.systemEntityCode = systemEntityCode;
    }

    public String getOutputStatus() {
        return outputStatus;
    }

    public void setOutputStatus(String outputStatus) {
        this.outputStatus = outputStatus;
    }

    public String getOutputResponse() {
        return outputResponse;
    }

    public void setOutputResponse(String outputResponse) {
        this.outputResponse = outputResponse;
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

    @Override
    public String toString() {
        return "OutputFormsSchedule{" +
                "outputSeq=" + outputSeq +
                ", outputDirectory='" + outputDirectory + '\'' +
                ", outputFormCode='" + outputFormCode + '\'' +
                ", outputFileName='" + outputFileName + '\'' +
                ", systemEntityCode='" + systemEntityCode + '\'' +
                ", outputStatus='" + outputStatus + '\'' +
                ", outputResponse='" + outputResponse + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
