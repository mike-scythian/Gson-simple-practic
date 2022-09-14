package converter;

class InputData {
	
	Distance distance;
	String convert_to;
	
	InputData(Distance dist, String conv){
		
		this.distance = dist;
		this.convert_to = conv;
	}
	
	
	@Override
	public String toString() {
		return "distance:"+this.distance.toString()+"convert_to:"+this.convert_to; 
	}
	
	/*public static void main(String[] args) {
		
		
		var in = new InputData(new Distance("mm",4.5), "in");
		System.out.print(in.toString());
	}
	*/

}
