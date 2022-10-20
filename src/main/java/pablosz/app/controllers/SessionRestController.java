package pablosz.app.controllers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pablosz.app.domain.Session;

@RestController
@RequestMapping("/sessions")
public class SessionRestController
{
	@Autowired
	private EntityManager em;
	
	// No se usa mas no? BORRAR
	/*
	// createSession
	@PostMapping("/")
	@Transactional
	public void createSession(@RequestBody Session session) {
		em.persist(session);
	}
	
	// destroySession
	@DeleteMapping("/{id}")
	@Transactional
	public void destroySession(@PathVariable int id) {
		Session sessionToDestroy = em.find(Session.class, id);
		if(sessionToDestroy != null) {
			em.remove(sessionToDestroy);
		}
	}
	*/
}
