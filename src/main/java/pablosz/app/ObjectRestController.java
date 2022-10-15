package pablosz.app;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/objects")
public class ObjectRestController
{
	@Autowired
	private EntityManager em;
	
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

	
}
