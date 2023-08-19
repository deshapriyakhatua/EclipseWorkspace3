package mavenProject1;

public class Students {

	String name;
	int age;
	String interest;
	int daysSpent;

	public Students(String name, int age, String interest, int daysSpent) {
		super();
		this.name = name;
		this.age = age;
		this.interest = interest;
		this.daysSpent = daysSpent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public int getDaysSpent() {
		return daysSpent;
	}

	public void setDaysSpent(int daysSpent) {
		this.daysSpent = daysSpent;
	}

}
