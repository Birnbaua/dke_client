package birnbaua.dke_pr_client.basics;

public class University {
	private String name = "";
	
	public University(String name) {
		this.name = name;
	}
	
	public University() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
