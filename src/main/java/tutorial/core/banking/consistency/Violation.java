package tutorial.core.banking.consistency;

public class Violation {

	private int code;
	private String description;
	
	public Violation (int code, String description) {
		
		this.code=code;
		this.description=description;
		
	}
	
	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code=code;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description=description;
	}
	
	@Override
	public String toString() {
	
		String result = "{Violation Code: " + this.code + "},{Description: }" + this.description;
		return result;
	}
	
}
