package frankie.financebudget.persistence;

import frankie.financebudget.entities.entities.objects.RecurringEntry;

import java.util.List;

public interface RecurringEntryDAO {

    public List<RecurringEntry> getAll();

    public List<RecurringEntry> getById(Long id);
}
