package com.tklimczak.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tklimczak.websocket.exception.ResourceNotFoundException;
import com.tklimczak.websocket.model.StateEntity;
import com.tklimczak.websocket.repository.StateRepository;

@EnableScheduling
@RestController
@RequestMapping("/api/states")
public class StateController {

	@Autowired
	private SimpMessagingTemplate template;

	private transient StateRepository repository;
	
	@Autowired
	public StateController(StateRepository repository) {
		this.repository = repository;
	}
	
	@CrossOrigin(origins = "http://localhost:8070")   // only in development
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	Iterable<StateEntity> getStates() {
		return this.repository.findAll();
	}

	@CrossOrigin(origins = "http://localhost:8070")   // only in development
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	StateEntity getState(@PathVariable Long id) throws NotFoundException {
		return this.repository
			.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(StateEntity.ENTITY, "id", id));
	}

	@Scheduled(fixedRate = 1000)
    public void greeting() {
        StateEntity state = this.repository
    			.findById(1L)
    			.orElseThrow(() -> new ResourceNotFoundException(StateEntity.ENTITY, "id", 1L));
        this.template.convertAndSend("/topic/state", state);
    }
}
