package com.revature.bankingProject;

import java.util.Scanner;
import org.apache.log4j.Logger;

public class Driver {

  public static final Scanner sc = new Scanner(System.in);
  public static final Logger logger = Logger.getLogger(Logger.class);

  public static void main(String[] args)
      throws MultipleUsernameException, InvalidPasswordException {
    
    
   // logger.info("Info message");
    //logger.error("Error message");

    int running = BankMenus.runWelcomeMenu();

    while (running != 0) {
      if (running == 1) {
        BankMenus.runWelcomeMenu();
      }
      if (running == 2) {
        BankMenus.bankMenu();
      }
    }
    System.exit(0);
  }

  
}
