package frankie.financebudget.entities.objects;

import frankie.financebudget.entities.enumerations.EntryType;
import frankie.financebudget.entities.enumerations.StatusEnum;
import frankie.financebudget.entities.enumerations.TimeSet;

import java.time.LocalDate;

public class RecurringEntry {

    private Long rId;
    private Double amount;
    private String description;
    private EntryType type;
    private LocalDate startingDate;
    private RecurringInterval recurringInterval;
    private StatusEnum statusEnum;

    public RecurringEntry(){}

    public RecurringEntry(Long rId, Double amount, String description, EntryType type, LocalDate startingDate, RecurringInterval recurringInterval, StatusEnum statusEnum) {
        this.rId = rId;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.startingDate = startingDate;
        this.recurringInterval = recurringInterval;
        this.statusEnum = statusEnum;
    }

    public RecurringEntry(Double amount, String description, EntryType type, LocalDate startingDate, RecurringInterval recurringInterval, StatusEnum statusEnum) {
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.startingDate = startingDate;
        this.recurringInterval = recurringInterval;
        this.statusEnum = statusEnum;
    }

    public RecurringEntry(Long rId, Double amount, String description, EntryType type, LocalDate startingDate, TimeSet timeSet, Integer intervalRange, StatusEnum statusEnum) {
        this.rId = rId;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.startingDate = startingDate;
        this.recurringInterval = new RecurringInterval(intervalRange, timeSet);
        this.statusEnum = statusEnum;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public RecurringInterval getRecurringInterval() {
        return recurringInterval;
    }

    public void setRecurringInterval(RecurringInterval recurringInterval) {
        this.recurringInterval = recurringInterval;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
