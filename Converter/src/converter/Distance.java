package converter;

public class Distance {
	
	String unit;
	double value;
	
	Distance(String u, double v){
		
		this.unit = u;
		this.value = v;
	}

	public String getUnit() {
		return this.unit;
	}
	public double getValue() {
		return this.value;
	}
	@Override
	public String toString() {
		return "distance:"+this.unit+" "+this.value; 
	}
}
