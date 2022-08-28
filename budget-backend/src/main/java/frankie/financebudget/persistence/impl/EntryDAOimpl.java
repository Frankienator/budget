package frankie.financebudget.persistence.impl;

import frankie.financebudget.entities.entities.objects.CompressedEntries;
import frankie.financebudget.entities.enumerations.EntryType;
import frankie.financebudget.entities.entities.objects.Entry;
import frankie.financebudget.persistence.EntryDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EntryDAOimpl implements EntryDAO {

    //Strings for Queries and prepared statements
    //-----------------------------------------------------------------------------------------------------------------
    //Table name
    private static final String SQL_TABLE_NAME = "entry";

    //Get Queries
    private static final String SQL_GET_ALL_ENTRIES = "SELECT * FROM " + SQL_TABLE_NAME;
    private static final String SQL_GET_BY_ID = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE id = ?";
    private static final String SQL_GET_BY_MONTH_YEAR = "SELECT * FROM " + SQL_TABLE_NAME +
            " WHERE EXTRACT(YEAR FROM dateCreated) = ? AND EXTRACT(MONTH FROM dateCreated) = ?";
    private static final String SQL_GET_INCOMING = "SELECT SUM(amount) AS sum FROM " + SQL_TABLE_NAME +
            " WHERE EXTRACT(YEAR FROM dateCreated) = ? AND EXTRACT(MONTH FROM dateCreated) = ? AND amount > 0";
    private static final String SQL_GET_OUTGOING = "SELECT SUM(amount) AS sum FROM " + SQL_TABLE_NAME +
            " WHERE EXTRACT(YEAR FROM dateCreated) = ? AND EXTRACT(MONTH FROM dateCreated) = ? AND amount < 0";

    //Post Queries
    private static final String SQL_CREATE_ENTRY = "INSERT INTO " + SQL_TABLE_NAME + " (description, amount, dateCreated, type) VALUES (?, ?, ?, ?)";

    //Put Queries
    private static final String SQL_UPDATE_ENTRY = "UPDATE " + SQL_TABLE_NAME + " SET description = ?, amount = ?, dateCreated = ?, type = ? WHERE id = ?";

    //Delete Queries
    private static final String SQL_DELETE_ENTRY = "DELETE FROM " + SQL_TABLE_NAME + " WHERE id = ?";
    //-----------------------------------------------------------------------------------------------------------------


    private final JdbcTemplate jdbcTemplate;

    public EntryDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Methods using GET queries
    //-----------------------------------------------------------------------------------------------------------------

    //SELECT * FROM entry
    @Override
    public List<Entry> getAllEntries() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_ENTRIES, this::mapRow);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    //SELECT * FROM entry WHERE id = ?
    @Override
    public Entry getById(Long id){
        try {
            List<Entry> result = jdbcTemplate.query(SQL_GET_BY_ID, this::mapRow, id);
            if (result.isEmpty()){
                throw new Exception();
            }
            return result.get(0);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    //SELECT * FROM entry WHERE EXTRACT(INTERVAL YEAR FROM dateCreated) = ? AND EXTRACT(INTERVAL MONTH FROM dateCreated) = ?;
    @Override
    public CompressedEntries getMonthResults(LocalDate monthYearCheck) {
        try {

            double outgoing = jdbcTemplate.query(SQL_GET_OUTGOING, this::mapRowSum, monthYearCheck.getYear(), monthYearCheck.getMonthValue()).get(0);
            double incoming = jdbcTemplate.query(SQL_GET_INCOMING, this::mapRowSum, monthYearCheck.getYear(), monthYearCheck.getMonthValue()).get(0);
            List<Entry> entries = jdbcTemplate.query(SQL_GET_BY_MONTH_YEAR, this::mapRow, monthYearCheck.getYear(), monthYearCheck.getMonthValue());

            return new CompressedEntries(outgoing, incoming, entries);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    //Methods using POST queries
    //-----------------------------------------------------------------------------------------------------------------


    //INSERT INTO entry (description, amount, dateCreated, type) VALUES (?, ?, ?, ?)";
    @Override
    public Entry createEntry(Entry create) {
        try {
           KeyHolder keyHolder = new GeneratedKeyHolder();
           jdbcTemplate.update(connection -> {
               PreparedStatement statement = connection.prepareStatement(
                       SQL_CREATE_ENTRY,
                       Statement.RETURN_GENERATED_KEYS
               );

               statement.setString(1, create.getDescription());
               statement.setDouble(2, create.getAmount());
               statement.setDate(3, Date.valueOf(create.getDateCreated()));
               statement.setString(4, create.getType().toString());

               return statement;
           }, keyHolder);

           Entry created = new Entry(
                   create.getAmount(),
                   create.getDescription(),
                   create.getDateCreated(),
                   create.getType()
           );

           created.setId(((Number)keyHolder.getKeys().get("id")).longValue());

           return created;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


    //Methods using PUT queries
    //-----------------------------------------------------------------------------------------------------------------


    //UPDATE TABLE entry SET description = ?, amount = ?, dateCreated = ?, type = ? WHERE id = ?;
    @Override
    public Entry updateEntry(Entry update) {
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(
                        SQL_UPDATE_ENTRY
                );
                statement.setString(1, update.getDescription());
                statement.setDouble(2, update.getAmount());
                statement.setDate(3, Date.valueOf(update.getDateCreated()));
                statement.setString(4, update.getType().toString());
                statement.setLong(5, update.getId());

                return statement;
            });

            return update;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    //Methods using DELETE queries
    //-----------------------------------------------------------------------------------------------------------------


    //DELETE FROM entry WHERE id = ?;
    @Override
    public Long deleteEntry(Long delete) {
        try {

            Long ret = delete;
            int updateStatus = jdbcTemplate.update(SQL_DELETE_ENTRY, ret);
            return ret;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    //Private methods
    //-----------------------------------------------------------------------------------------------------------------


    private Entry mapRow(ResultSet result, int rowNum) throws SQLException {
        Entry entry = new Entry();

        entry.setId(result.getLong("id"));
        entry.setDescription(result.getString("description"));
        entry.setAmount(result.getDouble("amount"));
        entry.setDateCreated(result.getDate("dateCreated").toLocalDate());
        entry.setType(EntryType.valueOf(result.getString("type")));

        return entry;
    }

    private double mapRowSum(ResultSet result, int rowNum) throws SQLException {
        return result.getDouble("sum");
    }
}
