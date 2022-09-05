package frankie.financebudget.entities.entities.dto;

import frankie.financebudget.entities.entities.objects.RecurringInterval;
import frankie.financebudget.entities.enumerations.EntryType;
import frankie.financebudget.entities.enumerations.StatusEnum;

import java.time.LocalDate;

public record RecurringEntryDto(
        Long rId,
        Double amount,
        String description,
        EntryType type,
        LocalDate startingDate,
        RecurringInterval recurringInterval,
        StatusEnum statusEnum
) {}
