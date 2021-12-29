package kr.tracom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BMSWebApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BMSWebApplication.class, args);
		
        SpringApplication application = new SpringApplication(BMSWebApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
		
	}
}
