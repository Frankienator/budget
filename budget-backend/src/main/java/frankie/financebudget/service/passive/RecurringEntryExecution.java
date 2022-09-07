package frankie.financebudget.service.passive;

import frankie.financebudget.entities.objects.Entry;
import frankie.financebudget.entities.objects.RecurringEntry;
import frankie.financebudget.persistence.EntryDAO;
import frankie.financebudget.persistence.RecurringEntryDAO;
import frankie.financebudget.persistence.impl.EntryDAOimpl;
import frankie.financebudget.persistence.impl.RecurringEntryDAOimpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RecurringEntryExecution{

    LocalDate now;
    EntryDAO entryDAO;
    RecurringEntryDAO recurringEntryDAO;

    @Scheduled(cron = "0 0 0 1/1 * ? *")
    public void run() throws RuntimeException{
        try {
            //Set up time
            now = LocalDate.now();

            //Set up Database Access
            recurringEntryDAO = new RecurringEntryDAOimpl(new JdbcTemplate());
            entryDAO = new EntryDAOimpl(new JdbcTemplate());

            //Fetch active recurring Entries, filter entries and add
            List<RecurringEntry> actives = recurringEntryDAO.getAllActives();
            if (!actives.isEmpty()){
                createEntriesFromRecurring(actives);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    //private methods

    private void createEntriesFromRecurring(List<RecurringEntry> entries) throws RuntimeException{
        Entry toAdd;

        for (int i = 0; i < entries.size(); i++) {

            toAdd = toCreate(entries.get(i));
            if (toAdd == null) continue;
            entryDAO.createEntry(toAdd);

        }
    }

    private Entry toCreate(RecurringEntry input) {
        LocalDate getStart = input.getStartingDate();
        if (!input.getRecurringInterval().checkEligibility(getStart)) {
            return null;
        }
        return new Entry(
               input.getAmount(),
               input.getDescription(),
               now,
               input.getType()
        );
    }


}
