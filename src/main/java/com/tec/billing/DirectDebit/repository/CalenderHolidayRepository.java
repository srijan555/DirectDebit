package com.tec.billing.DirectDebit.repository;

import com.tec.billing.DirectDebit.entity.CalendarHoliday;
import com.tec.billing.DirectDebit.entity.EntityRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CalenderHolidayRepository extends JpaRepository<CalendarHoliday,Long> {

    @Query("SELECT ch.holidayDate FROM CalendarHoliday ch WHERE ch.year = :year")
    List<Date> getUkHolidayDatesForYear(String year);

}
