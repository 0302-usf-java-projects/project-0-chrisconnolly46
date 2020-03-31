package com.revature.bankingProject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.revature.bankingProject.Account;
import com.revature.bankingProject.ConnectionUtil;

public class BankDao implements AccountDaoContract {

  Account acc = new Account();

  @Override
  public boolean addAccount(Account acc) {
    if (!checkUsername(acc.getUsername())) {
      try (Connection conn = ConnectionUtil.connect()) {
        String sql = "insert into account (username, pwd, balance) values (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, acc.getUsername());
        ps.setString(2, acc.getPassword());
        ps.setDouble(3, acc.getBalance());
        ps.execute();
        return true;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      return false;
    }
    return false;


  }

  @Override
  public boolean login(Account acc) {

    try (Connection conn = ConnectionUtil.connect()) {
      String sql = "select * from account where username = ? and pwd = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, acc.getUsername());
      ps.setString(2, acc.getPassword());
      // result set will refer to a record in database
      ResultSet rs = ps.executeQuery();
      rs.next();    //move the curse down to the first row
      acc.setLoggedIn(true);
      acc.setId(rs.getInt(1)); // 1 corresponds to the id column because it is the first column in
                               // the table
      acc.setUsername(rs.getString(2)); // 2nd column in the table is username
      acc.setPassword(rs.getString(3));
      acc.setBalance(rs.getDouble(4)); // 4th column in the table is balance
      System.out.println("You are now logged in " + acc.getUsername() + "!\n");

      return true;
    } catch (SQLException e) {
      acc.setLoggedIn(false);
    }

    return false;
  }

  @Override
  public double withdraw(int id, double withdraw) {
    try (Connection conn = ConnectionUtil.connect()) {
      String sql = "{ ? = call withdraw(?,?) }";
      CallableStatement cs = conn.prepareCall(sql);
      cs.registerOutParameter(1, Types.DOUBLE);
      cs.setInt(2, id);
      cs.setDouble(3, withdraw);
      cs.execute();
      double a = cs.getDouble(1);
      cs.close();
      return a;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  @Override
  public double deposit(int id, double deposit) {
    try (Connection conn = ConnectionUtil.connect()) {
      String sql = "{ ? = call deposit(?,?) }";
      CallableStatement cs = conn.prepareCall(sql);
      cs.registerOutParameter(1, Types.DOUBLE);
      cs.setInt(2, id);
      cs.setDouble(3, deposit);
      cs.execute();
      double a = cs.getDouble(1);
      cs.close();
      return a;
    } catch (SQLException e) {
      e.printStackTrace();
    }
   
    return -1;
  }

  @Override
  public boolean checkUsername(String username) {
    try (Connection conn = ConnectionUtil.connect()) {
      String sql = "select exists(select 1 from account where username = '" + username + "')";
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      rs.next();
      boolean isExists = rs.getBoolean(1);
      return isExists;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean transferFunds(int id, String id2, double transferAmount) {
    if (checkUsername(id2)) {
      try (Connection conn = ConnectionUtil.connect()) {
        String sql = "{ ? = call transfer(?,?,?) }";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.DOUBLE);
        cs.setInt(2, id);
        cs.setString(3, id2);
        cs.setDouble(4, transferAmount);
        cs.execute();

        double a = cs.getDouble(1);
        acc.setBalance(a);
        cs.close();
        return true;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
      return false;
    
  }

  public boolean deleteAccount(String id) {
    if(!checkUsername(id)) {
      System.out.println("This username does not exist in the database");
      return false;
    }else {
    try (Connection conn = ConnectionUtil.connect()) {
      String sql = "delete from account where username = '" + id + "'";
      PreparedStatement ps = conn.prepareStatement(sql);
      System.out.println("Checking database for this username...");
      ps.execute();
      ps.close();
      System.out.print("Account has been deleted! Returning you to the main menu.\n");
      return true;
     
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
    }
  }
  
}
