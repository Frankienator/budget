package frankie.financebudget.service.impl;

import frankie.financebudget.entities.entities.objects.RecurringEntry;
import frankie.financebudget.persistence.RecurringEntryDAO;
import frankie.financebudget.service.RecurringEntryService;

import java.util.List;

public class RecurringEntryServiceImpl implements RecurringEntryService {

    private final RecurringEntryDAO recurringEntryDAO;

    public RecurringEntryServiceImpl(RecurringEntryDAO recurringEntryDAO) {
        this.recurringEntryDAO = recurringEntryDAO;
    }


    @Override
    public List<RecurringEntry> getAll() {
        return recurringEntryDAO.getAll();
    }

    @Override
    public RecurringEntry getById(Long id) {
        try {
            List<RecurringEntry> ret = recurringEntryDAO.getById(id);
            if (ret.isEmpty()){
                throw new RuntimeException("List is empty");
            }
            return ret.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<RecurringEntry> getAllActives() {
        return recurringEntryDAO.getAllActives();
    }

    @Override
    public RecurringEntry createNewEntry(RecurringEntry recurringEntry) {
        try {
            return recurringEntryDAO.createNewEntry(recurringEntry);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public RecurringEntry updateRecurringEntry(RecurringEntry recurringEntry) {
        try {
            return recurringEntryDAO.updateRecurringEntry(recurringEntry);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
