package frankie.financebudget.persistence.impl;

import frankie.financebudget.entities.enumerations.EntryType;
import frankie.financebudget.entities.entities.objects.Entry;
import frankie.financebudget.persistence.EntryDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class EntryDAOimpl implements EntryDAO {

    //Strings for Queries and prepared statements
    private static final String TABLE_NAME = "entry";
    private static final String GET_ALL_ENTRIES = "SELECT * FROM " + TABLE_NAME;
    private static final String GET_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String CREATE_ENTRY = "INSERT INTO " + TABLE_NAME + " (description, amount, dateCreated, type) VALUES (?, ?, ?, ?)";
    //-------------------------------------------------


    private final JdbcTemplate jdbcTemplate;

    public EntryDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Entry> getAllEntries() {
        try {
            return jdbcTemplate.query(GET_ALL_ENTRIES, this::mapRow);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Entry getById(Long id){
        try {
            List<Entry> result = jdbcTemplate.query(GET_BY_ID, this::mapRow, id);
            if (result.isEmpty()){
                throw new Exception();
            }
            return result.get(0);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Entry createEntry(Entry create) {
        try {
           KeyHolder keyHolder = new GeneratedKeyHolder();
           jdbcTemplate.update(connection -> {
               PreparedStatement statement = connection.prepareStatement(
                       CREATE_ENTRY,
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


    private Entry mapRow(ResultSet result, int rowNum) throws SQLException {
        Entry entry = new Entry();

        entry.setId(result.getLong("id"));
        entry.setDescription(result.getString("description"));
        entry.setAmount(result.getDouble("amount"));
        entry.setDateCreated(result.getDate("dateCreated").toLocalDate());
        entry.setType(EntryType.valueOf(result.getString("type")));

        return entry;
    }
}
