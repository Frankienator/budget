package frankie.financebudget.service.impl;

import frankie.financebudget.entities.objects.RecurringEntry;
import frankie.financebudget.exceptions.NotFoundException;
import frankie.financebudget.exceptions.ValidationException;
import frankie.financebudget.persistence.RecurringEntryDAO;
import frankie.financebudget.service.RecurringEntryService;
import frankie.financebudget.service.inputValidation.RecurringEntryInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecurringEntryServiceImpl implements RecurringEntryService {

    private final RecurringEntryDAO recurringEntryDAO;
    private final RecurringEntryInputValidator entryInputValidator;

    @Autowired
    public RecurringEntryServiceImpl(RecurringEntryDAO recurringEntryDAO, RecurringEntryInputValidator entryInputValidator) {
        this.recurringEntryDAO = recurringEntryDAO;
        this.entryInputValidator = entryInputValidator;
    }


    @Override
    public List<RecurringEntry> getAll() {
        return recurringEntryDAO.getAll();
    }

    @Override
    public RecurringEntry getById(Long id) {
        try {
            List<RecurringEntry> ret = recurringEntryDAO.getById(id);
            return ret.get(0);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public List<RecurringEntry> getAllActives() {
        return recurringEntryDAO.getAllActives();
    }

    @Override
    public RecurringEntry createNewEntry(RecurringEntry recurringEntry) {
        try {
            entryInputValidator.createValidation(recurringEntry);
            return recurringEntryDAO.createNewEntry(recurringEntry);
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public RecurringEntry updateRecurringEntry(RecurringEntry recurringEntry) {
        try {
            entryInputValidator.updateValidation(recurringEntry);
            return recurringEntryDAO.updateRecurringEntry(recurringEntry);
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    public RecurringEntryInputValidator getEntryInputValidator() {
        return entryInputValidator;
    }
}


