package frankie.financebudget.persistence;

import frankie.financebudget.entities.objects.Entry;

import java.util.List;

public interface EntryDAO {

    public List<Entry> getAllEntries();

    public Entry getById(Long id);
}
