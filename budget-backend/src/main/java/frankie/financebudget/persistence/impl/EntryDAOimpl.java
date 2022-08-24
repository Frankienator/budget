package frankie.financebudget.persistence.impl;

import frankie.financebudget.persistence.EntryDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

@Repository
public class EntryDAOimpl implements EntryDAO {

    private final JdbcTemplate jdbcTemplate;

    public EntryDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
