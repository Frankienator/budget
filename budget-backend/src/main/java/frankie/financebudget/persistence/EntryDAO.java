package frankie.financebudget.persistence;

import frankie.financebudget.entities.entities.objects.Entry;

import java.time.LocalDate;
import java.util.List;

public interface EntryDAO {

    public List<Entry> getAllEntries();

    public Entry getById(Long id);

    public List<Entry> getMonthResults(LocalDate monthYearCheck);

    public Entry createEntry(Entry create);

    public Entry updateEntry(Entry update);

    public Long deleteEntry(Long delete);
}
