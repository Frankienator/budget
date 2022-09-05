package frankie.financebudget.persistence.impl;

import frankie.financebudget.entities.objects.RecurringEntry;
import frankie.financebudget.entities.objects.RecurringInterval;
import frankie.financebudget.persistence.RecurringEntryDAO;
import frankie.financebudget.entities.enumerations.EntryType;
import frankie.financebudget.entities.enumerations.StatusEnum;
import frankie.financebudget.entities.enumerations.TimeSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
    private static final String SQL_CREATE_RECURRING_ENTRY = "INSERT INTO " +
            TABLE_NAME +
            " (description, amount, startingDate, intervalCount, timeInterval, status, type) " +
            "VALUES (?,?,?,?,?,?,?)";

    //PUT Queries
    private static final String SQL_UPDATE_RECURRING_ENTRY = "UPDATE TABLE " +
            TABLE_NAME +
            " SET description = ?, amount = ?, startingDate = ?, intervalCount = ?, timeInterval = ?" +
            "status = ?, type = ? " +
            "WHERE id = ?";

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

    @Override
    public List<RecurringEntry> getAllActives() {
        try {
            return jdbcTemplate.query(SQL_GET_TO_UPDATE, this::mapRow);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //(description, amount, startingDate, intervalCount, timeInterval, status, type)
    @Override
    public RecurringEntry createNewEntry(RecurringEntry recurringEntry) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(
                        SQL_CREATE_RECURRING_ENTRY,
                        Statement.RETURN_GENERATED_KEYS
                );
                statement.setString(1, recurringEntry.getDescription());
                statement.setDouble(2, recurringEntry.getAmount());
                statement.setDate(3, Date.valueOf(recurringEntry.getStartingDate()));
                statement.setInt(4, recurringEntry.getRecurringInterval().getIntervalRange());
                statement.setString(5, recurringEntry.getRecurringInterval().getTimeSetter().toString());
                statement.setString(6, recurringEntry.getStatusEnum().toString());
                statement.setString(7, recurringEntry.getType().toString());

                return statement;
            }, keyHolder);

            RecurringEntry created = new RecurringEntry(
                    recurringEntry.getAmount(),
                    recurringEntry.getDescription(),
                    recurringEntry.getType(),
                    recurringEntry.getStartingDate(),
                    recurringEntry.getRecurringInterval(),
                    recurringEntry.getStatusEnum()
            );

            created.setrId(((Number)keyHolder.getKeys().get("id")).longValue());

            return created;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // " SET description = ?, amount = ?, startingDate = ?, intervalCount = ?, timeInterval = ?" +
    //            "status = ?, type = ? " +
    //            "WHERE id = ?";
    @Override
    public RecurringEntry updateRecurringEntry(RecurringEntry recurringEntry) {
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(
                        SQL_UPDATE_RECURRING_ENTRY
                );
                statement.setString(1, recurringEntry.getDescription());
                statement.setDouble(2, recurringEntry.getAmount());
                statement.setDate(3, Date.valueOf(recurringEntry.getStartingDate()));
                statement.setInt(4, recurringEntry.getRecurringInterval().getIntervalRange());
                statement.setString(5, recurringEntry.getRecurringInterval().getTimeSetter().toString());
                statement.setString(6, recurringEntry.getStatusEnum().toString());
                statement.setString(7, recurringEntry.getType().toString());
                statement.setLong(8, recurringEntry.getrId());

                return statement;
            });

            return recurringEntry;

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
