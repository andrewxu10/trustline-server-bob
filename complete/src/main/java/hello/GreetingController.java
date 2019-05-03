package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GreetingController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @RequestMapping("/sendRequest")
    public RequestResponse sendRequest(@RequestParam(value="amountSending") Long amountSending) {
        AccountJdbcRepository n = new AccountJdbcRepository(jdbcTemplate);
        RequestResponse response = n.processTransactionRequest(amountSending, "send");
        System.out.println("Sending $" + amountSending);
        return response;
    }

    @RequestMapping("/receiveRequest")
    public RequestResponse receiveRequest(@RequestParam(value="amountReceiving") Long amountReceiving) {
        AccountJdbcRepository n = new AccountJdbcRepository(jdbcTemplate);
        RequestResponse response = n.processTransactionRequest(amountReceiving,"receive");
        System.out.println("Received $" + amountReceiving);
        return response;
    }

    @RequestMapping("/getCurrentBalance")
    public Long getCurrentBalance() {
        AccountJdbcRepository n = new AccountJdbcRepository(jdbcTemplate);
        Long response = n.getCurrentBalance();
        return response;
    }

    @RequestMapping("/confirmTransaction")
    public Long confirmTransaction() { //@RequestParam(value="diffValue")Long diffValue, @RequestParam(value="currentBalance") Long currentBalance
        AccountJdbcRepository n = new AccountJdbcRepository(jdbcTemplate);
        Long updatedBalance = n.confirmTransactionRequest();
//        updatedBalance = 1L;
        System.out.println("Current balance is $" + n.getCurrentBalance());
        return updatedBalance;
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
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/acct")
    public Account acct(@RequestParam(value="name", defaultValue="World") String name) {
        AccountJdbcRepository n = new AccountJdbcRepository(jdbcTemplate);
        Account a = n.findById(1L);
        System.out.println("hjdksfz;jkl" + a.getBalance());
        return a;
    }
}