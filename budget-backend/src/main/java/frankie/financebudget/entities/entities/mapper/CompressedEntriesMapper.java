package frankie.financebudget.entities.entities.mapper;

import frankie.financebudget.entities.entities.dto.CompressedEntriesDto;
import frankie.financebudget.entities.entities.objects.CompressedEntries;
import org.springframework.stereotype.Component;

@Component
public class CompressedEntriesMapper {

    private final EntryMapper entryMapper = new EntryMapper();


    public CompressedEntriesDto entityToDto(CompressedEntries compressedEntries) {
        return new CompressedEntriesDto(
               compressedEntries.getOutgoing(),
               compressedEntries.getIncoming(),
               compressedEntries.getEntries().stream()
                       .map(entryMapper::entityToDto)
        );
    }
}
