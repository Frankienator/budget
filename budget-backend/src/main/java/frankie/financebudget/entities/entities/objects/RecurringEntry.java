package frankie.financebudget.entities.entities.objects;

import frankie.financebudget.entities.enumerations.EntryType;
import frankie.financebudget.entities.enumerations.StatusEnum;

import java.time.LocalDate;

public class RecurringEntry {

    private Long rId;
    private double amount;
    private String description;
    private EntryType type;
    private LocalDate startingDate;
    private RecurringInterval recurringInterval;
    private StatusEnum statusEnum;

    public RecurringEntry(){}

    public RecurringEntry(Long rId, double amount, String description, EntryType type, LocalDate startingDate, RecurringInterval recurringInterval, StatusEnum statusEnum) {
        this.rId = rId;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.startingDate = startingDate;
        this.recurringInterval = recurringInterval;
        this.statusEnum = statusEnum;
    }
}
