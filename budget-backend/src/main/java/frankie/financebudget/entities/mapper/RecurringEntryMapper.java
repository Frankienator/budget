package frankie.financebudget.entities.mapper;

import frankie.financebudget.entities.dto.RecurringEntryDto;
import frankie.financebudget.entities.objects.RecurringEntry;
import org.springframework.stereotype.Component;

@Component
public class RecurringEntryMapper {

    public RecurringEntry dtoToEntity(RecurringEntryDto recurringEntryDto) {
        return new RecurringEntry(
                recurringEntryDto.rId(),
                recurringEntryDto.amount(),
                recurringEntryDto.description(),
                recurringEntryDto.type(),
                recurringEntryDto.startingDate(),
                recurringEntryDto.recurringInterval(),
                recurringEntryDto.statusEnum()
        );
    }

    public RecurringEntryDto entityToDto(RecurringEntry recurringEntry) {
        return new RecurringEntryDto(
          recurringEntry.getrId(),
          recurringEntry.getAmount(),
          recurringEntry.getDescription(),
          recurringEntry.getType(),
          recurringEntry.getStartingDate(),
          recurringEntry.getRecurringInterval(),
          recurringEntry.getStatusEnum()
        );
    }
}
