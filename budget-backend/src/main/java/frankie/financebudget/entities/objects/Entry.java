package frankie.financebudget.entities.objects;

import java.time.LocalDate;

import frankie.financebudget.entities.enumerations.EntryType;

public class Entry {

    private Long id;
    private double amount;
    private String description;
    private LocalDate dateCreated;
    private EntryType type;

    public Entry(
            double amount,
            String description,
            LocalDate dateCreated,
            EntryType type) {

        this.amount = amount;
        this.description = description;
        this.dateCreated = dateCreated;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

}
