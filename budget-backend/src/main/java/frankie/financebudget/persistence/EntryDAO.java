package frankie.financebudget.persistence;

import frankie.financebudget.entities.objects.CompressedEntries;
import frankie.financebudget.entities.objects.Entry;

import java.time.LocalDate;
import java.util.List;

public interface EntryDAO {

    public List<Entry> getAllEntries(Long offset, Long size);

    public Entry getById(Long id);

    public CompressedEntries getMonthResults(LocalDate monthYearCheck);

    public Entry createEntry(Entry create);

    public Entry updateEntry(Entry update);

    public Long deleteEntry(Long delete);
}
