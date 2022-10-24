package pablosz.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/sessions")
public class SessionRestController {
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
