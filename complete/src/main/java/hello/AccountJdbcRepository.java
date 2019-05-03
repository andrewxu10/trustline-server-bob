package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public RequestResponse processTransactionRequest(Long transactionAmount, String transactionType) {

        if(transactionType == "send") { transactionAmount = transactionAmount * -1; }

        //clear table first
        jdbcTemplate.update(
                "DELETE FROM pendingtransaction"
        );

        Long currentBalance = getCurrentBalance();

        //add request to temp table
        try {
            Long proposedBalance = currentBalance + transactionAmount;
            jdbcTemplate.update(
                    "INSERT INTO pendingtransaction (change, proposedBalance) VALUES (?, ?)",
                    transactionAmount, proposedBalance//amountSending
            );
            //build success response, which is returned
            return new RequestResponse(true, proposedBalance, transactionType);

        } catch (DataAccessException a) {

            //abort if can't insert
            jdbcTemplate.update(
                    "DELETE FROM pendingtransaction"
            );

            //build error Response, which is returned
            return new RequestResponse(false, currentBalance, transactionType);
        }
    }

    public Long getCurrentBalance() {
        return jdbcTemplate.queryForObject("select * from account", new Object[] {
                },
                new BeanPropertyRowMapper< Account >(Account.class))
                .getBalance();
    }

    public Long confirmTransactionRequest() {
        Long newBalance = jdbcTemplate.queryForObject("select * from pendingTransaction", new Object[] {
                },
                new BeanPropertyRowMapper< PendingTransaction >(PendingTransaction.class))
                .getProposedBalance();
        jdbcTemplate.update(
                "UPDATE account SET balance = (?)",
                newBalance
        );

        jdbcTemplate.update(
                "DELETE FROM pendingtransaction"
        );

        return newBalance;
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
