package com.krit.test.hello.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount {

  @Id
  @Column(name = "account_no")
  private String accountNo;
  @Column(name = "balance")
  private BigDecimal balance;
  @Column(name = "status")
  private Integer status;
  @Column(name = "person_id")
  private String personId;

  public BankAccount() {}

  public BankAccount(String accountNo, BigDecimal balance, Integer status, String personId) {
    super();
    this.accountNo = accountNo;
    this.balance = balance;
    this.status = status;
    this.personId = personId;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  @Override
  public String toString() {
    return "[ACC_NO=" + accountNo + ", PERSON_ID=" + personId + ", BALANCE=" + balance + ", STATUS="
        + status + "]";
  }

}
