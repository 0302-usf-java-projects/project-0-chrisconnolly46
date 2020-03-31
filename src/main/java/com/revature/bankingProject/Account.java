package com.revature.bankingProject;


public class Account {
 
  //private fields, public getters and setters
  private static int id;
  private static String username;
  private static String password;
  private static double balance = 0.0;
  private static boolean loggedIn = false;
  
  //array list for the transaction history
  
  
  
  public Account(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }
  
  public Account() {
  }

  public void setUsername(String username) {
    this.username=username;
    
  }
  
  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

  public boolean getLoggedIn() {
    return loggedIn;
  }
  
  public String getUsername() {
    return username;
  }
  
  public void setBalance(double balance) {
    this.balance = balance;
  }
    
  public double getBalance() {
    return balance;
  }
  
  public String getPassword() {
    return password;
  }
  public void setPassword(String password){
    this.password = password;
  }

  @Override
  public String toString() {
    return "Account [username=" + username + ", balance=" + balance + "]";
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
 
  
  
  
  
  
}
