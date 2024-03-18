package com.yatra.mailsender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.yatra.mailsender.service.DataService;
import com.yatra.mailsender.service.EmailSenderService;

@SpringBootApplication
@EnableScheduling // Enable Spring's scheduling capabilities
public class MailSenderApplication {

	@Autowired
	private EmailSenderService emailService;

	@Autowired
	private DataService dataService;

	public static void main(String[] args) {
		SpringApplication.run(MailSenderApplication.class, args);
		
	}

	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron = "0 0 09 * * ?") // Execute at 9AM every day
	public void triggerEmail() {
		
		emailService.sendMimeEmail();
		/*
		 * List<String> data = dataService.fetchData();
		 * 
		 * // Get the current date and format it as needed LocalDate currentDate =
		 * LocalDate.now(); DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd"); String formattedDate =
		 * currentDate.format(formatter);
		 * 
		 * String subject = "Mo Drop Issues in DB - " + formattedDate;
		 * 
		 * String[] recipients = { "yatratest0222@gmail.com",
		 * "v-bellamkonda.c@yatra.com", "v-venkata.puppalla@yatra.com" };
		 * emailService.simpleMailSender(recipients, subject, data);
		 */
	}

}
