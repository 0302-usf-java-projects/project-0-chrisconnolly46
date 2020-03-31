package com.revature.bankingProject;

public interface AccountDaoContract {

  boolean addAccount(Account acc);

  boolean login(Account acc);

  double withdraw(int id, double withdraw);

  double deposit(int id, double depost);

  boolean checkUsername(String username);

  boolean transferFunds(int id, String id2, double transferAmount);

}
