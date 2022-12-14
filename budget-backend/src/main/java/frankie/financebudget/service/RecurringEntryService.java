package frankie.financebudget.service;

import frankie.financebudget.entities.objects.RecurringEntry;
import frankie.financebudget.service.inputValidation.RecurringEntryInputValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecurringEntryService {

    List<RecurringEntry> getAll();

    RecurringEntry getById(Long id);

    List<RecurringEntry> getAllActives();

    RecurringEntry createNewEntry(RecurringEntry recurringEntry);

    RecurringEntry updateRecurringEntry(RecurringEntry recurringEntry);

    RecurringEntryInputValidator getEntryInputValidator();
}
