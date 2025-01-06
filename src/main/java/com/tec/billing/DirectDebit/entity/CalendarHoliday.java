package com.tec.billing.DirectDebit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "CALENDAR_HOLIDAY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CalendarHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendarHolidaySeq")
    private Long calendarHolidaySeq;

    @Column(name = "year", nullable = false)
    private String year;

    @Column(name = "holidayDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date holidayDate;

    @Column(name = "holidayName", nullable = false)
    private String holidayName;

    @Column(name = "createdBy", nullable = false)
    private String createdBy;

    @Column(name = "createdOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public Long getCalendarHolidaySeq() {
        return calendarHolidaySeq;
    }

    public void setCalendarHolidaySeq(Long calendarHolidaySeq) {
        this.calendarHolidaySeq = calendarHolidaySeq;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
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
        return "CalendarHoliday{" +
                "calendarHolidaySeq=" + calendarHolidaySeq +
                ", year=" + year +
                ", holidayDate=" + holidayDate +
                ", holidayName='" + holidayName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}

