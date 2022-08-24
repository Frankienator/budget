package frankie.financebudget.entities.objects;

import java.time.LocalDate;

import frankie.financebudget.entities.enumerations.EntryType;

public class entry {

    private Long id;
    private double amount;
    private String description;
    private LocalDate dateCreated;
    private EntryType type;
}
