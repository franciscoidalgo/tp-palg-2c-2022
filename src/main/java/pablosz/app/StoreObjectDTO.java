package pablosz.app;

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

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
