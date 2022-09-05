package frankie.financebudget.service.impl;

import frankie.financebudget.entities.objects.CompressedEntries;
import frankie.financebudget.entities.objects.Entry;
import frankie.financebudget.exceptions.NotFoundException;
import frankie.financebudget.persistence.EntryDAO;
import frankie.financebudget.exceptions.ValidationException;
import frankie.financebudget.service.EntryService;

import frankie.financebudget.service.inputValidation.EntryInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryDAO entryDAO;
    private final EntryInputValidator entryInputValidator;

    @Autowired
    public EntryServiceImpl(EntryDAO entryDAO, EntryInputValidator entryInputValidator){
        this.entryDAO = entryDAO;
        this.entryInputValidator = entryInputValidator;
    }

    @Override
    public List<Entry> getAllEntries() {
        return entryDAO.getAllEntries();
    }

    @Override
    public Entry getById(Long id) throws NotFoundException{
        try {
            return entryDAO.getById(id);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public CompressedEntries getByMonth(LocalDate monthYearComp) {
        return entryDAO.getMonthResults(monthYearComp);
    }

    @Override
    public Entry createEntry(Entry create) {
        try {
            entryInputValidator.createValidation(create);
            return entryDAO.createEntry(create);
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public Entry updateEntry(Entry update) {
        try {
            getById(update.getId());
            entryInputValidator.updateValidation(update);

            return entryDAO.updateEntry(update);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public Long deleteEntry(Long delete) {
        try {

            return entryDAO.deleteEntry(delete);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public EntryInputValidator getEntryInputValidator() {
        return entryInputValidator;
    }
}
