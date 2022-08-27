package frankie.financebudget.service.impl;

import frankie.financebudget.entities.entities.objects.Entry;
import frankie.financebudget.persistence.EntryDAO;
import frankie.financebudget.service.EntryService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryDAO entryDAO;

    public EntryServiceImpl(EntryDAO entryDAO){
        this.entryDAO = entryDAO;
    }

    public List<Entry> getAllEntries() {
        return entryDAO.getAllEntries();
    }

    @Override
    public Entry getById(Long id) {
        try {
            return entryDAO.getById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Entry createEntry(Entry create) {
        try {
            return entryDAO.createEntry(create);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Entry updateEntry(Entry update) {
        try {

            return entryDAO.updateEntry(update);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
