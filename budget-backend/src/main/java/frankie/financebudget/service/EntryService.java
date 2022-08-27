package frankie.financebudget.service;

import frankie.financebudget.entities.entities.objects.Entry;

import java.util.List;

public interface EntryService {

    public List<Entry> getAllEntries();

    public Entry getById(Long id);

    public Entry createEntry(Entry create);

    public Entry updateEntry(Entry update);
}
