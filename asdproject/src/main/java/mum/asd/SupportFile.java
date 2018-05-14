package mum.asd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("mum.asd")
@SpringBootApplication
public class SupportFile {

	public static void main(String[] args) {
		SpringApplication.run(SupportFile.class, args);
	}

}
