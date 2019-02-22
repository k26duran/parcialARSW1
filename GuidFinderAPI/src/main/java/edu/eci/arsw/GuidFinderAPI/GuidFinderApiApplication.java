package edu.eci.arsw.GuidFinderAPI;

import java.util.Date;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class GuidFinderApiApplication {

	private final Date fecha;
	private final int count;
	private final String guid;
	
	public GuidFinderApiApplication(Date fecha, String guid, int count){
		this.count=count;
		this.fecha=fecha;
		this.guid=guid;
	}

	public static void main(String[] args) {
		SpringApplication.run(GuidFinderApiApplication.class, args);
	}

	public Date getFecha() {
		return fecha;
	}

	public int getCount() {
		return count;
	}

	public String getGuid() {
		return guid;
	}

}
