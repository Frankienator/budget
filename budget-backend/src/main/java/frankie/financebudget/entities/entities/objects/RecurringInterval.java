package frankie.financebudget.entities.entities.objects;

import frankie.financebudget.entities.enumerations.TimeSet;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

/*
An Object to be used within a RecurringEntry Object defining the Interval
 */
public class RecurringInterval {

    private TimeSet timeSetter;
    private int intervalRange;

    public RecurringInterval(){};

    public RecurringInterval(int intervalRange, TimeSet timeSetter) {
        this.timeSetter = timeSetter;
        this.intervalRange = intervalRange;
    }

    public TimeSet getTimeSetter() {
        return timeSetter;
    }

    public void setTimeSetter(TimeSet timeSetter) {
        this.timeSetter = timeSetter;
    }

    public int getIntervalRange() {
        return intervalRange;
    }

    public void setIntervalRange(int intervalRange) {
        this.intervalRange = intervalRange;
    }


    //Implement edge-case 29th february -> Recurring on 29.Feb will be stored as 1.Mar
    public boolean checkEligibility(LocalDate date) {
        LocalDate now = LocalDate.now();
        switch (timeSetter) {
            case year:

                if ((date.getMonth() != now.getMonth()) || (date.getDayOfMonth() != now.getDayOfMonth())) {
                    return false;
                }
                if ((now.getYear() - date.getYear() % intervalRange) == 0) {
                    return true;
                }

                break;
            case month:
                if (date.getDayOfMonth() != now.getDayOfMonth()) {
                    return false;
                }
                Long monthsBetween = ChronoUnit.MONTHS.between(
                        YearMonth.from(date),
                        YearMonth.from(now)
                );
                if (monthsBetween.intValue() % intervalRange == 0) {
                    return true;
                }
                break;
            default:
                Long daysBetween = ChronoUnit.DAYS.between(date, now);
                if (daysBetween % intervalRange == 0) {
                    return true;
                }
                break;
        }
        return false;
    }
}
