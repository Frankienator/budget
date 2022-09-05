package frankie.financebudget.entities.mapper;

import frankie.financebudget.entities.dto.CompressedEntriesDto;
import frankie.financebudget.entities.objects.CompressedEntries;
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
