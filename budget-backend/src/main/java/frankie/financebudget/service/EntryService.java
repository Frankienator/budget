package frankie.financebudget.service;

import frankie.financebudget.entities.entities.objects.CompressedEntries;
import frankie.financebudget.entities.entities.objects.Entry;

import java.time.LocalDate;
import java.util.List;

public interface EntryService {

    public List<Entry> getAllEntries();

    public Entry getById(Long id);

    public CompressedEntries getByMonth(LocalDate monthYearComp);

    public Entry createEntry(Entry create);

    public Entry updateEntry(Entry update);

    public Long deleteEntry(Long delete);
}
