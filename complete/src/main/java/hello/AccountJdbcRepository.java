package hello;

import org.springframework.beans.factory.annotation.Autowired;
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
//    JdbcTemplate jdbcTemplate = ApplicationContextHolder.getContext().getBean(JdbcTemplate.class);


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

    public Account findById(long id) {
//        jdbcTemplate.findById()
//        jdbcTemplate.
//        System.out.println("hello world");
//        System.out.println("1zjdosdhj " + jdbcTemplate.getFetchSize());
//        System.out.println("sdko39392 " + jdbcTemplate.getMaxRows());
//        jdbcTemplate.query();
//
//        jdbcTemplate.query(
//                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
//                (rs, rowNum) -> new Account(rs.getLong("id"), rs.getString("name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));

        return jdbcTemplate.queryForObject("select * from account where id=?", new Object[] {
                        id
                },
                new BeanPropertyRowMapper< Account >(Account.class));
    }

    public List< Account > findAll() {
//        jdbcTemplate.
        return jdbcTemplate.query("select * from account", new AccountRowMapper());
    }
}
