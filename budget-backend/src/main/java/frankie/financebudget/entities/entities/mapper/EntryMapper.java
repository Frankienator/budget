package frankie.financebudget.entities.entities.mapper;

import frankie.financebudget.entities.entities.dto.EntryDto;
import frankie.financebudget.entities.entities.objects.Entry;

public class EntryMapper {

    public Entry dtoToEntity(EntryDto entryDto) {
        return new Entry(
                entryDto.id(),
                entryDto.amount(),
                entryDto.description(),
                entryDto.dateCreated(),
                entryDto.type()
        );
    }

    public EntryDto entityToDto(Entry entry) {
        return new EntryDto(
                entry.getId(),
                entry.getAmount(),
                entry.getDescription(),
                entry.getDateCreated(),
                entry.getType()
        );
    }
}
