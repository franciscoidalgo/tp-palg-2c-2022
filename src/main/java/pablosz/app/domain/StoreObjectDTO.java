package pablosz.app.domain;

import lombok.Getter;
import lombok.Setter;
import pablosz.app.persistance.PersistentObject;

@Getter @Setter
public class StoreObjectDTO {
	private long key;
	
	private String className;
	
	private String data;

	public StoreObjectDTO() {
	}

	public StoreObjectDTO(long key, String className, String data) {
		this.key = key;
		this.className = className;
		this.data = data;
	}

	public PersistentObject toPersistantObject(){
		return new PersistentObject();
	}
}
