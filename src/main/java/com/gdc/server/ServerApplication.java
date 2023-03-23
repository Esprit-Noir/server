package com.gdc.server;

import com.gdc.server.enumeration.Status;
import com.gdc.server.model.ServerModel;
import com.gdc.server.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository){
		return args -> {
			serverRepository.save(new ServerModel(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/images/server1.png", Status.SERVER_UP));
			serverRepository.save(new ServerModel(null, "192.168.1.58", "Fedora Linux", "16 GB", "Dell Tower", "http://localhost:8080/server/images/server2.png", Status.SERVER_UP));
			serverRepository.save(new ServerModel(null, "192.168.1.21", "MS 2008", "32 GB", "Web Server", "http://localhost:8080/server/images/server3.png", Status.SERVER_UP));
			serverRepository.save(new ServerModel(null, "192.168.1.14", "Red Hat Entreprise Linux", "64 GB", "Mail Server", "http://localhost:8080/server/images/server4.png", Status.SERVER_UP));

		};
	}

}
