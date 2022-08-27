package frankie.financebudget.entities.entities.dto;

import frankie.financebudget.entities.enumerations.EntryType;

import java.time.LocalDate;

public record EntryDto(
        Long id,
        double amount,
        String description,
        LocalDate dateCreated,
        EntryType type) {
}
