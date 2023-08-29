package objects;

public class Distances {
	
	double distMeter;
	double distFeet;
	double distMiles;
	
	public Distances(double distMeter, double distFeet, double distMiles) {
		super();
		this.distMeter = distMeter;
		this.distFeet = distFeet;
		this.distMiles = distMiles;
	}

	public double getDistMeter() {
		return distMeter;
	}

	public void setDistMeter(double distMeter) {
		this.distMeter = distMeter;
	}

	public double getDistFeet() {
		return distFeet;
	}

	public void setDistFeet(double distFeet) {
		this.distFeet = distFeet;
	}

	public double getDistMiles() {
		return distMiles;
	}

	public void setDistMiles(double distMiles) {
		this.distMiles = distMiles;
	}
	
}
