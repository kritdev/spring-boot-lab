package com.krit.test.hello.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {
  List<BankAccount> findByAccountNo(String accountNo);
}
