package frankie.financebudget.persistence;

import frankie.financebudget.entities.objects.RecurringEntry;

import java.util.List;

public interface RecurringEntryDAO {

    List<RecurringEntry> getAll();

    List<RecurringEntry> getById(Long id);

    List<RecurringEntry> getAllActives();

    RecurringEntry createNewEntry(RecurringEntry recurringEntry);

    RecurringEntry updateRecurringEntry(RecurringEntry recurringEntry);
}
