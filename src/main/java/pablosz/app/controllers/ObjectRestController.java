package pablosz.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/objects")
public class ObjectRestController {
    @Autowired
    private EntityManager em;

    // No se usa mas no? BORRAR
	/*
	
	// store
	@PostMapping(value = "/", consumes = "application/json")
	public void store(@RequestBody StoreObjectDTO objectToStore) {
		System.out.println();
	}
	
	// load
	@GetMapping("/{sessionId}/{className}")
	public void load(@PathVariable long sessionId, @PathVariable  String className) {
		System.out.println();
	}
	
	
	// remove
	@DeleteMapping("/{sessionId}/{className}")
	public void remove(@RequestParam long sessionId, @RequestParam String className) {
		
	}

	*/

}
