package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }


    class AccountRowMapper implements RowMapper< Account > {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(rs.getLong("id"));
            account.setName(rs.getString("name"));
            account.setBalance(rs.getLong("balance"));
            return account;
        }
    }

    public RequestResponse processSendRequest(int amountSending) {
        //clear table first
        jdbcTemplate.update(
                "DELETE FROM pendingtransaction"
        );

        //add request to temp table
        jdbcTemplate.update(
                "INSERT INTO pendingtransaction (type, amount) VALUES (?, ?)",
                "send", amountSending
        );

        if (jdbcTemplate.query("select * from pendingtransaction", )){
            jdbcTemplate.
        }
        int id = 1;

        try {
            //will throw error if transaction didn't insert
            jdbcTemplate.queryForObject("select * from pendingtransaction", new Object[] {
                    },
                    new BeanPropertyRowMapper< PendingTransaction >(PendingTransaction.class));


        } catch (NullPointerException a) {
            //nothing found, abort
        }
        //validate insert, and create response to return
    }

    public Account findById(long id) {
        return jdbcTemplate.queryForObject("select * from account where id=?", new Object[] {
                        id
                },
                new BeanPropertyRowMapper< Account >(Account.class));
    }

    public List< Account > findAll() {
        return jdbcTemplate.query("select * from account", new AccountRowMapper());
    }
}
