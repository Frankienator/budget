package frankie.financebudget.entities.entities.objects;

import frankie.financebudget.entities.enumerations.TimeSet;

public class RecurringInterval {

    private TimeSet timeSetter;
    private int intervalRange;

    public RecurringInterval(){};

    public RecurringInterval(TimeSet timeSetter, int intervalRange) {
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
