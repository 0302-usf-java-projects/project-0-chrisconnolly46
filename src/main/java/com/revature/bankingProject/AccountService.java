package com.revature.bankingProject;

import static com.revature.bankingProject.Driver.*;


public class AccountService {

  private static BankDao bankDAO = new BankDao();

  public boolean createAccount(Account acc) {
    try {
      if (!bankDAO.addAccount(acc)) {   //if the account creation was not successful
        throw new MultipleUsernameException();      //throw multiple username exception
      } else {          //if account creation was actually successful
        return true;
        }
        } catch (MultipleUsernameException e) {
          System.out.println("Username is already taken. Please try another username"); //gets printed out if the username exists in the database
        } 
    return false;   //returns false if account creation was not successful
  }
  

  public boolean login() throws MultipleUsernameException, InvalidPasswordException {

    System.out.println("Please log in. Provide username:");
//    String usernameInput = sc.nextLine();
    String usernameInput = sc.next();
    System.out.println("Provide password");
//    String passwordInput = sc.nextLine();   //get login credentials
    String passwordInput = sc.next();
    System.out.println("");                                          //pass in the login credentials as an account object to the bank dao
    return bankDAO.login(new Account(usernameInput, passwordInput)); //this will return true if login was successful and false if login was not successful.

  }

  public double withdraw(int id, double withdraw) {

    return bankDAO.withdraw(id, withdraw); //this will return the new balance after money has been withdrawn from the account
  }

  public double deposit(int id, double deposit) {

    return bankDAO.deposit(id, deposit); //this will return the new balance after money has been deposited into the account
  }

  public boolean transferFunds(int id, String id2, double transferAmount) {

    return bankDAO.transferFunds(id, id2, transferAmount); //this will return true if the transfer is complete
  }
  
  public boolean deleteAccount(String id) {

    return bankDAO.deleteAccount(id); //this will return true if the delete is complete
  }

}
