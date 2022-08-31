package frankie.financebudget.persistence.impl;

import frankie.financebudget.entities.entities.objects.Entry;
import frankie.financebudget.entities.entities.objects.RecurringEntry;
import frankie.financebudget.entities.entities.objects.RecurringInterval;
import frankie.financebudget.entities.enumerations.EntryType;
import frankie.financebudget.entities.enumerations.StatusEnum;
import frankie.financebudget.entities.enumerations.TimeSet;
import frankie.financebudget.persistence.RecurringEntryDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RecurringEntryDAOimpl implements RecurringEntryDAO {


    //Queries
    //-----------------------------------------------------------------------
    //Table Name
    private static final String TABLE_NAME = "recurringEntry";

    //GET Queries
    private static final String SQL_GET_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String SQL_GET_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SQL_GET_TO_UPDATE = "SELECT * FROM " + TABLE_NAME +
            " WHERE status = 'active'";
    //POST Queries
    private static final String SQL_CREATE = "INSERT INTO " +
            TABLE_NAME +
            " (id, description, amount, startingDate, intervalCount, timeInterval, status, type) " +
            "VALUES (?,?,?,?,?,?,?,?)";

    //PUT Queries


    //Delete Queries


    //-----------------------------------------------------------------------


    private final JdbcTemplate jdbcTemplate;

    public RecurringEntryDAOimpl(JdbcTemplate jdbcTemplate) {
     this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<RecurringEntry> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, this::mapRow);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<RecurringEntry> getById(Long id) {
        try {
            return jdbcTemplate.query(SQL_GET_BY_ID, this::mapRow, id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }




    //Private Functions

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
