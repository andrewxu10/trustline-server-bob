package hello;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;



@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GreetingController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @RequestMapping("/testConnection")
    @ResponseBody
    public boolean canConnectToDB() {
        boolean result;
        try {
            jdbcTemplate.getDataSource().getConnection();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        Connection connection = datasource.getConnection;
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/acct")
    public Account acct(@RequestParam(value="name", defaultValue="World") String name) {
        AccountJdbcRepository n = new AccountJdbcRepository(jdbcTemplate);
        return n.findById(1L);

    //        Long id = 1L;
    //        return jdbcTemplate.queryForObject("select * from account where id=?", new Object[] {
    //                        id
    //                },
    //                new BeanPropertyRowMapper< Account >(Account.class));

    }

//        AccountJdbcRepository accountGetter = new AccountJdbcRepository();
//        List b = accountGetter.findAll();
//        System.out.println("sakhldj " + b.size());
//        Account a = accountGetter.findById(1);
//        System.out.println(a.getName());
    }


//    public Account findById(long id) {
////        jdbcTemplate.findById()
//        return jdbcTemplate.queryForObject("select * from student where id=?", new Object[] {
//                        id
//                },
//                new BeanPropertyRowMapper < Account > (Account.class));
//    }
//}




//@Repository
//class AccountJdbcRepository {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
////    JdbcTemplate jdbcTemplate = ApplicationContextHolder.getContext().getBean(JdbcTemplate.class);
//
//
//    class AccountRowMapper implements RowMapper< Account > {
//        @Override
//        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Account account = new Account();
//            account.setId(rs.getLong("id"));
//            account.setName(rs.getString("name"));
//            account.setBalance(rs.getLong("balance"));
//            return account;
//        }
//    }
//
//    public Account findById(long id) {
////        jdbcTemplate.findById()
////        jdbcTemplate.
//        System.out.println("hello world");
//        System.out.println("1zjdosdhj " + jdbcTemplate.getFetchSize());
//        System.out.println("sdko39392 " + jdbcTemplate.getMaxRows());
////        jdbcTemplate.query();
////
////        jdbcTemplate.query(
////                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
////                (rs, rowNum) -> new Account(rs.getLong("id"), rs.getString("name"), rs.getString("last_name"))
////        ).forEach(customer -> log.info(customer.toString()));
//
//        return jdbcTemplate.queryForObject("select * from account where id=?", new Object[] {
//                        id
//                },
//                new BeanPropertyRowMapper< Account >(Account.class));
//    }
//
//    public List< Account > findAll() {
////        jdbcTemplate.
//        return jdbcTemplate.query("select * from account", new AccountRowMapper());
//    }
//}