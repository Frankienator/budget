package frankie.financebudget.entities.dto;

import frankie.financebudget.entities.objects.RecurringInterval;
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
