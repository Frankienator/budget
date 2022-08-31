package frankie.financebudget.persistence.impl;

import frankie.financebudget.entities.entities.objects.Entry;
import frankie.financebudget.entities.entities.objects.RecurringEntry;
import frankie.financebudget.entities.entities.objects.RecurringInterval;
import frankie.financebudget.entities.enumerations.EntryType;
import frankie.financebudget.entities.enumerations.StatusEnum;
import frankie.financebudget.entities.enumerations.TimeSet;
import frankie.financebudget.persistence.RecurringEntryDAO;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Repository
public class RecurringEntryDAOimpl implements RecurringEntryDAO {









    private RecurringEntry mapRow(ResultSet result, int rowNum) throws SQLException {
        RecurringEntry recurringEntry = new RecurringEntry();

        recurringEntry.setrId(result.getLong("id"));
        recurringEntry.setDescription(result.getString("description"));
        recurringEntry.setAmount(result.getDouble("amount"));
        recurringEntry.setStartingDate(result.getDate("startingDate").toLocalDate());
        recurringEntry.setRecurringInterval(
                new RecurringInterval(
                        result.getInt("intervalCount"),
                        TimeSet.valueOf(result.getString("timeInterval"))
        ));
        recurringEntry.setStatusEnum(StatusEnum.valueOf(result.getString("status")));
        recurringEntry.setType(EntryType.valueOf(result.getString("type")));

        return recurringEntry;
    }
}
