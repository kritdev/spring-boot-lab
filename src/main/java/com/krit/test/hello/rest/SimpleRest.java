package com.krit.test.hello.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.krit.test.hello.model.BankAccount;
import com.krit.test.hello.model.BankAccountRepository;

@RestController
public class SimpleRest {
  @Autowired
  private BankAccountRepository bankRepository;

  @PostMapping("/api/bankaccount")
  @ResponseBody
  BankAccount createAccount(@RequestBody BankAccount account) {
    System.out.println("createAccount - " + account);
    return bankRepository.save(account);
  }

  @PutMapping("/api/bankaccount")
  @ResponseBody
  BankAccount updateAccount(@RequestBody BankAccount account) {
    System.out.println("updateAccount - " + account);
    return createAccount(account);
  }

  @DeleteMapping("/api/bankaccount/{accountNo}")
  @ResponseBody
  BankAccount deleteAccount(@PathVariable String accountNo) {
    System.out.println("deleteAccount - " + accountNo);
    BankAccount account = getAccount(accountNo);

    if (account != null) {
      bankRepository.delete(account);
      return account;
    } else {
      return null;
    }
  }


  @GetMapping("/api/bankaccount/{accountNo}")
  @ResponseBody
  BankAccount getAccount(@PathVariable String accountNo) {
    System.out.println("getAccount - " + accountNo);
    List<BankAccount> accounts = bankRepository.findByAccountNo(accountNo);
    if (accounts.size() >= 1)
      return accounts.get(0);
    else
      return null;
  }

  @GetMapping("/api/bankaccount")
  @ResponseBody
  List<BankAccount> getAllAccount() {
    System.out.println("getAllAccount");
    return (List<BankAccount>) bankRepository.findAll();
  }

}
