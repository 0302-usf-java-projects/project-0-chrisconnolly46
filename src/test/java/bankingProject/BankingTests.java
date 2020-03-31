package bankingProject;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import com.revature.bankingProject.*;
import org.junit.Test;

public class BankingTests {

 
  private static AccountService accServ;
  
  @Before
  public void setUp() {
    accServ = new AccountService();
  }
  
  @After
  public void tearDown() {
    accServ = null;
  }
  
  
  @Test
  public void transferingToAUsernameThatIsNotThere() {
    System.out.println("Not there test");
    String result = "notThere";
    int id = 1;
    double transferAmount = 500;
    assertTrue(accServ.transferFunds(id, result, transferAmount) == false);
  }
  
  @Test
  public void transferingToAUsernameThatIsThere() {
    System.out.println("transfer to a username that is there test");
    String result = "usernam";
    int id = 1;
    double transferAmount = 500;
    assertTrue(accServ.transferFunds(id, result, transferAmount) == true);
  }

  @Test
  public void CheckUsernameUsername() {
    BankDao bankdao = new BankDao();
    System.out.println("Check if a username exists");
    String result = "username";
    assertTrue(bankdao.checkUsername(result));
  }

}
