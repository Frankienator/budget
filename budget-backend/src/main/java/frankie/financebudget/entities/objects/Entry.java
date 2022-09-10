package frankie.financebudget.entities.objects;

import java.time.LocalDate;

import frankie.financebudget.entities.enumerations.EntryType;

public class Entry {

    private Long id;
    private Double amount;
    private String description;
    private LocalDate dateCreated;
    private EntryType type;

    public Entry(
            Double amount,
            String description,
            LocalDate dateCreated,
            EntryType type) {

        this.amount = amount;
        this.description = description;
        this.dateCreated = dateCreated;
        this.type = type;
    }

    public Entry(
            Long id,
            Double amount,
            String description,
            LocalDate dateCreated,
            EntryType type) {

        this.id = id;
        this.amount = amount;
        this.description = description;
        this.dateCreated = dateCreated;
        this.type = type;
    }

    public Entry() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
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

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", type=" + type +
                '}';
    }
}
