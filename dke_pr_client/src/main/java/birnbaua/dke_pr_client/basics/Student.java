package birnbaua.dke_pr_client.basics;

public class Student {
	private  String FIRSTNAME;
	private  String LASTNAME;
	private  String MATRNR;
	
	public Student(String first, String last, String matr_nr) {
		this.FIRSTNAME = first;
		this.LASTNAME = last;
		this.MATRNR = matr_nr;
	}

	public String getFIRSTNAME() {
		return FIRSTNAME;
	}

	public String getLASTNAME() {
		return LASTNAME;
	}

	public String getMATRNR() {
		return MATRNR;
	}
}