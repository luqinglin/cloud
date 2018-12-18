package me.sta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinStreamServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinStreamServerApplication.class, args);
	}
}
