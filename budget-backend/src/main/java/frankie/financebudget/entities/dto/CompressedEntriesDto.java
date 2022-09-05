package frankie.financebudget.entities.dto;

import java.util.stream.Stream;

public record CompressedEntriesDto (
        double outgoing,
        double incoming,
        Stream<EntryDto> entries
){
}
