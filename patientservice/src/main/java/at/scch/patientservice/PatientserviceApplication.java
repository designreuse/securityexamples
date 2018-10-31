package at.scch.patientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(scanBasePackageClasses = {PatientserviceApplication.class, at.scch.securitylibary.SecuritylibaryApplication.class} )
public class PatientserviceApplication{

	public static void main(String[] args) {
		SpringApplication.run(PatientserviceApplication.class, args);
	}
}
