package frankie.financebudget.entities.entities.objects;

import frankie.financebudget.entities.enumerations.TimeSet;

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
}
