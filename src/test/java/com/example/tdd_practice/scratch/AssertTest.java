package com.example.tdd_practice.scratch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AssertTest {

    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }

    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }
    }

    private Account account;

    @BeforeEach
    public void createAccount() {
        account = new Account("an account name");
    }

    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
    }

    /**
     * hamcrest assertThat의 경우 message라는 선택적 첫번쨰 인자 존재
     */
    @Test
    public void testWithWorthlessAssertionComment() {
        account.deposit(50);
        assertThat("account balance is 100", account.getBalance(), equalTo(50));
    }

    /**
     * 예외를 기대하는 3가지 방법
     */
//    @Test(expected=InsufficientFundsException.class)
//    public void throwsWhenWithdrawingTooMuch_1st(){
//        account.withdraw(100);
//    }
    @Test
    public void throwsWhenWithdrawingTooMuch_2nd() {
        try {
            account.withdraw(100);
            fail();
        } catch (InsufficientFundsException expected) {

        }
    }

    @Test
    public void throwsWhenWithdrawingTooMuch_3rd() {
        try {
            account.withdraw(100);
            fail();
        } catch (InsufficientFundsException expected) {
            assertThat(expected.getMessage(), equalTo("balance only 0"));
        }
    }

    /**
     * JUnit의 Rule이란?
     * Rule은 테스트 클래스에서 동작 방식을 재정의 하거나 쉽게 추가하는 것을 가능하게 합니다.
     * 사용자는 기존의 Rule을 재사용하거나 확장하는 것이 가능합니다.
     */
/*    @Rule
    public ExpectedExceptionSupport thrown = ExpectedExceptionSupport.none();
    @Test
    public void throwsWhenWithdrawingTooMuch_4th(){

    }*/

    //예외가 발생해도 Test실패가 발생X
    @Test
    public void readsFromTestFile() throws IOException {
        String filename = "test.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write("test data");
        writer.close();
    }


}
