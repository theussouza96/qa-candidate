package qa.candidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("qa.candidate")
@SpringBootApplication
public class CandidateApplication {


	public static void main(String[] args) {
		SpringApplication.run(CandidateApplication.class, args);
	}

}
