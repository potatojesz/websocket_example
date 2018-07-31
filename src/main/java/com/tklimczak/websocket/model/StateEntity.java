package com.tklimczak.websocket.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "state")
public class StateEntity {
	public static final String ENTITY = "StateEntity";

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 3)
    private String address;
 
    @Size(max = 5)
    private String code;
    
    @Size(max = 2)
    private String channel;
   
    @Size(max = 15)
    private String value;
   
    private LocalDateTime ts;

    public Long getId() {
        return id;
    }
	public String getAddress() {
		return address;
	}
	public String getCode() {
		return code;
	}
	public String getChannel() {
		return channel;
	}
	public String getValue() {
		return value;
	}
	public LocalDateTime getTs() {
		return ts;
	}
}
