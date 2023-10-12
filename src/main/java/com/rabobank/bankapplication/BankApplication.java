package com.rabobank.bankapplication;

import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.BankAccountRepository;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import com.rabobank.bankapplication.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rabobank.bankapplication.repositories")

public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	private BankAccountRepository bankAccountRepository;
	private TransactionRepository transactionRepository;
	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	public BankApplication(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.bankAccountRepository = bankAccountRepository;
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server h2Server() throws SQLException {
//		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
//	}

	// TODO Try running ASAP and using postman to check whether 'CRUD' calls work
	// TODO Finish database
	// TODO Make sure balance, username, category re-labelling works
	// TODO Connect front end
	// TODO JAVADOCS -- Look into other people's javadocs to learn about conventions and best practices
	// TODO look into publishing the javadocs (method to create html file out of them) (banking-application.adoc)
	// TODO javadocs not just for methods but for classes as well -- what is the use? where is it needed? etc
	// TODO Create graphs using PlantUML -- ft Antora -- ASCIIdoc
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		User jane = new User();
		jane.setFirstName("jane");
		jane.setLastName("doe");
		jane.setEmail("jane@gmail.com");
		jane.setPassword(passwordEncoder.encode("a123456"));
		userRepository.save(jane);

		User joe = new User();
		joe.setFirstName("joe");
		joe.setLastName("doe");
		joe.setEmail("joe@gmail.com");
		joe.setPassword(passwordEncoder.encode("a123456"));
		userRepository.save(joe);

		BankAccount janeAccount = new BankAccount();
		janeAccount.setIban("NLRABO293864298365");
		janeAccount.setBalance(3459L);
		janeAccount.setUser(jane);
		bankAccountRepository.save(janeAccount);

		BankAccount joeAccount = new BankAccount();
		joeAccount.setIban("NLRABO293864298366");
		joeAccount.setBalance(3459L);
		joeAccount.setUser(joe);
		bankAccountRepository.save(joeAccount);

		Transaction transaction1 = new Transaction();
		transaction1.setFrom(janeAccount);
		transaction1.setFromIban(janeAccount.getIban());
		transaction1.setTo(joeAccount);
		transaction1.setToIban(joeAccount.getIban());
		transaction1.setAmount(100);
		transaction1.setDate(null);
		transactionRepository.save(transaction1);

		Transaction transaction2 = new Transaction();
		transaction2.setFrom(joeAccount);
		transaction2.setFromIban(joeAccount.getIban());
		transaction2.setTo(joeAccount);
		transaction2.setToIban(joeAccount.getIban());
		transaction2.setAmount(100);
		transaction2.setDate(null);
		transactionRepository.save(transaction2);

	}


//	@GetMapping("/transactions/{toIban}")
//	public List<Transaction> getbankAccountTransactions(@PathVariable String toIban) {
//		BankAccount bankAccount = bankAccountRepository.getBankAccountByToIban(toIban);
//		if (user != null) {
//			return transactionRepository.getTransactionsByToIban();
//		}
//		throw new IllegalArgumentException("No customer with such email: " + email);
//	}
}
