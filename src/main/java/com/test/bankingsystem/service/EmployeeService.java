package com.test.bankingsystem.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.test.bankingsystem.entity.AccountType;
import com.test.bankingsystem.entity.BankAccount;
import com.test.bankingsystem.entity.Customer;
import com.test.bankingsystem.entity.Transaction;
import com.test.bankingsystem.repository.BankAccountRepo;
import com.test.bankingsystem.repository.CustomerRepo;
import com.test.bankingsystem.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService{
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private BankAccountRepo bankAccountRepo;
    @Autowired
    private TransactionRepo transactionRepo;


    public String addCustomer(Customer customer) throws Exception {
        try {
            customerRepo.save(customer);
            return "Customer added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

    }
    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }
    public Customer getDetailsById(int id) throws Exception {
        Customer customer;
        try {
            customer = customerRepo.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }


        return customer;
    }
    public String deleteCustomer(int id) throws Exception {
       try {
           customerRepo.deleteById(id);
       } catch (Exception e) {
           e.printStackTrace();
           throw new Exception(e.getMessage());
       }


        return "Customer deleted successfully";
    }

    public String deleteAllCustomers(){
        customerRepo.deleteAll();
        return "All customers data deleted successfully";
    }
    public String addBankAccountOfCustomerById(int customerId, AccountType accountType, int accountBalance, int accountNumber) throws Exception {
        try {
            BankAccount bankAccount = new BankAccount(accountNumber,accountBalance,accountType);
            Customer customer= customerRepo.findById(customerId).orElse(null);
            if (customer == null){
                throw new NullPointerException();
            }
            List<BankAccount> bankAccounts = customer.getBankAccounts();
            bankAccount.setCustomer(customer);
            bankAccounts.add(bankAccount);
            customer.setBankAccounts(bankAccounts);
            bankAccountRepo.save(bankAccount);
            customerRepo.save(customer);
            return accountType.toString() + " account created for customer with ID = " + customerId;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

    }

    public int getAccountBalance(int customerId, int accountNumber) throws Exception {
        try {
            Customer customer= customerRepo.findById(customerId).orElse(null);
            List<BankAccount> bankAccounts = customer.getBankAccounts();
            if (bankAccounts == null){
                throw new NullPointerException();
            }

            for (int i = 0;i<bankAccounts.size();i++){
                if (bankAccounts.get(i).getAccountNumber() == accountNumber){
                    return bankAccounts.get(i).getAccountBalance();

                }
            }
            return -1;
        } catch (NullPointerException ex){
            throw new Exception(ex.getMessage());
        }
    }

    public String transferMoney(int srcId, int srcAccountNumber, int destId, int destAccountNumber, int amount) throws Exception {
        try{
            BankAccount srcBankAccount = bankAccountRepo.findByIdAndAccountNumber(srcId,srcAccountNumber);
            BankAccount destBankAccount = bankAccountRepo.findByIdAndAccountNumber(destId,destAccountNumber);

            int balanceInSrc  = srcBankAccount.getAccountBalance();
            int balanceInDest = destBankAccount.getAccountBalance();
            Transaction debitTransaction = new Transaction(amount);
            Transaction creditTransaction = new Transaction(amount);
            if (amount > balanceInSrc){
                return "Insufficient balance";
            }
            balanceInSrc -= amount;
            balanceInDest += amount;
            debitTransaction.setSrcBankAccount(srcBankAccount);
            debitTransaction.setDestBankAccount(destBankAccount);
            creditTransaction.setDestBankAccount(srcBankAccount);
            creditTransaction.setDestBankAccount(destBankAccount);
            List<Transaction> srcDebitTransactions = srcBankAccount.getDebitTransactions();
            List<Transaction> destCreditTransactions = destBankAccount.getCreditTransactions();

            srcDebitTransactions.add(debitTransaction);
            destCreditTransactions.add(creditTransaction);
            srcBankAccount.setDebitTransactions(srcDebitTransactions);
            destBankAccount.setCreditTransactions(destCreditTransactions);
            srcBankAccount.setAccountBalance(balanceInSrc);
            destBankAccount.setAccountBalance(balanceInDest);
            transactionRepo.save(debitTransaction);
            transactionRepo.save(creditTransaction);
            bankAccountRepo.save(srcBankAccount);
            bankAccountRepo.save(destBankAccount);
            return "Money transfer successfull";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }


    }

    public void addRowPdf(Table table,Transaction transaction,BankAccount bankAccount){
        table.addCell(transaction.getDate().toString());
        table.addCell(Integer.toString(transaction.getId()));
        if (bankAccount == transaction.getSrcBankAccount()){
            table.addCell(Integer.toString(transaction.getDestBankAccount().getAccountNumber()));
            table.addCell("-"+transaction.getAmount());
        }else {
            table.addCell(Integer.toString(transaction.getSrcBankAccount().getAccountNumber()));
            table.addCell("+"+transaction.getAmount());
        }

    }
    public void addHeaderPdf(Table table){
        table.addHeaderCell("DATE");
        table.addHeaderCell("ID");
        table.addHeaderCell("TRANSFER FROM/TO");
        table.addHeaderCell("AMOUNT");
    }
    public String printAccountStatement(int id, int accountNumber, LocalDate startDate, LocalDate endDate) throws Exception {
        try {
            BankAccount bankAccount = bankAccountRepo.findByIdAndAccountNumber(id,accountNumber);
            List<Transaction> debitTransactions = bankAccount.getDebitTransactions();
            List<Transaction> creditTransactions = bankAccount.getCreditTransactions();
            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions.addAll(debitTransactions);
            transactions.addAll(creditTransactions);
            String dir = System.getProperty("user.dir");
            PdfWriter writer = new PdfWriter(dir);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            float [] pointColumnWidths = {150F, 150F, 150F, 150F};
            Table table = new Table(pointColumnWidths);
            addHeaderPdf(table);
            List<Transaction> transactionList = new ArrayList<Transaction>() ;
            for (Transaction transaction : transactions){
                if (transaction.getDate().isAfter(startDate) && transaction.getDate().isBefore(endDate)){
                    transactionList.add(transaction);
                }
            }
            for (Transaction transaction : transactionList){
                addRowPdf(table,transaction,bankAccount);
            }
            document.add(table);
            document.close();
            return "Account statement generated";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }


    public String calculateInterest(int id, int accountNumber) throws Exception {
        try {
            BankAccount bankAccount = bankAccountRepo.findByIdAndAccountNumber(id,accountNumber);
            int balance = bankAccount.getAccountBalance();
            float interest = 3.5F;
            float newBalance = (float) balance +  ((interest) * (float) balance)/100F;
            bankAccount.setAccountBalance((int)newBalance);
            bankAccountRepo.save(bankAccount);
            return "Interest amount credited successfully";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }


    }



}
