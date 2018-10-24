package at.scch.patientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {PatientserviceApplication.class, at.scch.securitylibary.SecuritylibaryApplication.class} )
public class PatientserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientserviceApplication.class, args);
	}
}
