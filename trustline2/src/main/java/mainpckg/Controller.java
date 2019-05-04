package mainpckg;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@RestController
public class Controller {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Controller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @RequestMapping("/sendRequest")
    public RequestResponse sendRequest(@RequestParam(value="amountSending") Long amountSending) {
        GeneralJdbcRepository n = new GeneralJdbcRepository(jdbcTemplate);
        RequestResponse response = n.processTransactionRequest(amountSending, "send");
        System.out.println("Sending $" + amountSending);
        return response;
    }

    @RequestMapping("/receiveRequest")
    public RequestResponse receiveRequest(@RequestParam(value="amountReceiving") Long amountReceiving) {
        GeneralJdbcRepository n = new GeneralJdbcRepository(jdbcTemplate);
        RequestResponse response = n.processTransactionRequest(amountReceiving,"receive");
        System.out.println("Received $" + amountReceiving);
        return response;
    }

    @RequestMapping("/getCurrentBalance")
    public Long getCurrentBalance() {
        GeneralJdbcRepository n = new GeneralJdbcRepository(jdbcTemplate);
        Long response = n.getCurrentBalance();
        return response;
    }

    @RequestMapping("/confirmTransaction")
    public Long confirmTransaction() { //@RequestParam(value="diffValue")Long diffValue, @RequestParam(value="currentBalance") Long currentBalance
        GeneralJdbcRepository n = new GeneralJdbcRepository(jdbcTemplate);
        Long updatedBalance = n.confirmTransactionRequest();
        System.out.println("Current balance is " + n.getCurrentBalance());
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

    @RequestMapping("/acct")
    public Account acct(@RequestParam(value="name", defaultValue="World") String name) {
        GeneralJdbcRepository n = new GeneralJdbcRepository(jdbcTemplate);
        Account a = n.findById(1L);
        return a;
    }
}