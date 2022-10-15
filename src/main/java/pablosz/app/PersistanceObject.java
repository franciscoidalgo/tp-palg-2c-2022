package pablosz.app;

import javax.persistence.Id;


public class PersistanceObject {

	@Id
	private int id;
	
	
	private Session session;
	
	private String clazz;
	
	private String data; // Json
 
	
}
