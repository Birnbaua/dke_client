package birnbaua.dke_pr_client.basics;

public class Study {
	private String name;
	
	public Study(String str) {
		this.name = str;
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return this.name;
	}
}
