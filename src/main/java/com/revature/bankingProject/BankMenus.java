package com.revature.bankingProject;

import static com.revature.bankingProject.Driver.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class BankMenus {

  // Whether the user is currently logged in

  private static AccountService accServ = new AccountService();
  private static Account acc = new Account();

  /**
   * Runs the welcome menu that provides only 3 options: login, create account, and exit
   * 
   * @return
   * @throws InvalidPasswordException
   * @throws MultipleUsernameException
   */
  public static int runWelcomeMenu() throws MultipleUsernameException, InvalidPasswordException {

    System.out.println("_________________________________");
    System.out.println("Welcome to my banking application");
    System.out.println("Choose 1 for Create Account");
    System.out.println("Choose 2 for Login");
    System.out.println("Choose 3 for Exit");

    while(sc.hasNext()) {
    //int input = Integer.parseInt(sc.nextLine());
      int input = sc.nextInt();

    switch (input) {

      case 1:
        createAccount();
        System.out.println("");
        return 1;

      case 2:

        if (accServ.login()) {
          
          if(acc.getUsername().equals("username") && acc.getPassword().equals("password")) {
            System.out.println("Welcome bank employee");
            deleteAccountMenu();
          }
        else {
          bankMenu();
        }
    
        } 
        
        else {
          System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
          System.out.println("Something went wrong, you were not logged in. Please try again.");
          return 1;
        }
    
        return 1;


      case 3:
        System.out.println("Exiting program...");
        System.exit(0);

      default:
        System.out.println("Input not recognized. Please try again");
        System.out.println("");
        return 1;
    }
    }
    return 0;
  }

  private static int deleteAccountMenu() {
    System.out.println("_________________________________");
    System.out.println("Press 1 to delete a user account");
    System.out.println("Press 2 for the main menu");
    System.out.println("Press 3 to go to your bank menu");
    System.out.println("_________________________________");
    
    while(sc.hasNext()) {
    int input = sc.nextInt();

    switch (input) {

      case 1:
        System.out.print("Enter the username of the account you would like to delete\n");
        String id = sc.next();
        accServ.deleteAccount(id);
        return 1;

      case 2: 
        System.out.println("Returning you to the main menu.");
        return 1;
        
      case 3:
        bankMenu();
        
       default:
         System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
         return 1;
  }
    
  }
return 0;
  }
  /**
   * This menu runs after the user logs in.
   * @return
   */

  public static int bankMenu() {
    System.out.println("________________________");
    System.out.println("Welcome to the main menu");
    System.out.println("Your current balance is " + acc.getBalance());
    System.out.println("________________________");
    System.out.println("Choose 1 to Withdraw");
    System.out.println("Choose 2 to Deposit Money");
    System.out.println("Choose 3 to Transfer Funds");
    System.out.println("Choose 0 to Exit");
    System.out.println("________________________");

    while(sc.hasNext()) {
    int userOption = sc.nextInt();
    switch (userOption) {
      case 1:
        withdraw();
        return 2;

      case 2:
        deposit();
        return 2;

      case 3:
        transferFunds();
        return 2;

      case 0:
        System.out.println("Logging you out! I love you <3");
        System.exit(0);

      default:
        System.out.println("Failed to recognized option. Please try again!");
        return 2;
    }
  }
    return 0;
  }
  /**
   * This method creates an account and also checks whether or not the username is already taken.
   */

  public static void createAccount() {
    System.out.println("Welcome to Account creation.");
    int retryCount = 0;
    while (retryCount < 3) {
      System.out.println("Please provide a username:");
//      String username = sc.nextLine();
      String username = sc.next();
      System.out.println("Please provide a password at least 8 characters long:");
//      String password = sc.nextLine();
      String password = sc.next();
      Account account = new Account(username, password);
      if (accServ.createAccount(account)) {
        System.out.println("Account Created!");
      } else {
        System.out.println("Accout not created. Try again.");
        retryCount++;
        createAccount();
      }
      break;
    }
  }

  /**
   * This method returns you back to the bank menu if the amount you want to deposit is less than 0.
   */

  private static void deposit() {
    System.out.println("Please enter the amount you would like to deposit:");
    double deposit = sc.nextDouble();
    while (deposit < 0) {
      System.out.println("Deposit amount is less than 0.\nTaking you back to the main menu...\n");
      deposit = 0;
      bankMenu();
    }
    acc.setBalance(accServ.deposit(acc.getId(), deposit));
    System.out.println("Your new balance is " + acc.getBalance());
    bankMenu();
  }

  /**
   * This method checks whether the amount you want to withdraw is greater than your account balance and withdraws it if it is not.
   */

  private static void withdraw() {
    System.out.println("Please enter the amount you would like to withdraw");
    double withdraw = sc.nextDouble();
    if (acc.getBalance() < withdraw) {
      System.out.println(
          "You have insufficient funds for this transaction. Taking you back to the main menu.");
      bankMenu();
    } else {
      acc.setBalance(accServ.withdraw(acc.getId(), withdraw));
      System.out.println("Your new balance is " + acc.getBalance());
      bankMenu();
    }
  }

  /**
   * This method asks for the username of the account you want to transfer funds to, the amount you want to transfer, checks if the transfer amount is less than 0,
   * checks if the transfer amount is less than your account balance, and then transfers the money into the specified account.
   */

  private static void transferFunds() {
    System.out.println("Please enter the amount you would like to transfer:");
    double transferAmount = sc.nextDouble();

    while (transferAmount < 0) {
      System.out.println("Transfer amount is less than 0.\nTaking you back to the main menu...\n");
      transferAmount = 0;
      bankMenu();
    }
    
    if (acc.getBalance() > transferAmount) {
    System.out.println("Please enter the username of the account you would like to transfer funds to:");
    String user = sc.next();
   if(accServ.transferFunds(acc.getId(), user, transferAmount)) {
    System.out.println("You have successfully transfered " + transferAmount + " into " + user + "'s account!");
    System.out.println("Your new balance is " + acc.getBalance());
    bankMenu();
    }
   else {
     System.out.println("\nThis username does not exist. Try again loser.");
   }
  }
  }

}

