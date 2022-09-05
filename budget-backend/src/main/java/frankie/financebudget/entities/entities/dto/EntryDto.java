package frankie.financebudget.entities.entities.dto;

import frankie.financebudget.entities.enumerations.EntryType;

import java.time.LocalDate;

public record EntryDto(
        Long id,
        Double amount,
        String description,
        LocalDate dateCreated,
        EntryType type) {
}
