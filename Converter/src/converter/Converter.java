package converter;

import com.google.gson.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Converter {
	
	private static HashMap<String,Number> convertedTable;
	private static Path pathToConvertedTable;
	
 	Converter(){
		convertedTable = new HashMap<>();	
	}

	public void setConvertedTable(String path)throws IOException{
		
		pathToConvertedTable = Paths.get(path);		
		convertedTable = new Gson().fromJson(new FileReader(pathToConvertedTable.toString()), HashMap.class);
	}
	public boolean isTable() {
		return convertedTable.isEmpty();
	}
	public void addUnit(String name, double value) {
		
		var gUnit = new Gson();
		convertedTable.put(name, value);
		
		try(var fw = new FileWriter ("C:\\Java\\java_study\\SysGears\\Converter\\src\\input.json", false)){
			fw.write(gUnit.toJson(convertedTable));
		}catch(IOException e) {
			System.out.println(e);
		}

	}
	public Distance convert(double value, String unit, String convertTo) {
		
		if(value <= 0)
			return new Distance(convertTo, 0);
		if(unit.equals(convertTo))
			return new Distance(convertTo, value);
		if(!convertedTable.containsKey(unit)) 
			return new Distance("?",0);
		if(!convertedTable.containsKey(convertTo))
			return new Distance("?",0);

		return new Distance(convertTo, Math.round(100*(value * convertedTable.get(unit).doubleValue() / convertedTable.get(convertTo).doubleValue())) / 100);
	}
	
	public boolean writeToJsonFile(Distance dist, String path){
		
		var jsonWriter = new GsonBuilder().create();
		
		try(var fw = new FileWriter(Paths.get(path).toFile(),false)){
			jsonWriter.toJson(dist, fw);
			return true;
		}catch(IOException e) {
			System.out.println(e);
		}
		return false;
	}


	public static void main(String[] args){
		// TODO Auto-generated method stub
		var c = new Converter();
		var dist = new Distance("",0);
		
		/*c.addUnit("m", 1);
		c.addUnit("mm", 0.001);
		c.addUnit("cm", 0.01);
		c.addUnit("km", 1000);
		c.addUnit("in", 0.0254);
		c.addUnit("ft", 0.3048);
		c.addUnit("yd", 0.9144);
		c.addUnit("mile", 1609.3);
		c.addUnit("dm", 0.1);*/
		
		var gUnit = new Gson();
		try{
			c.setConvertedTable("C:\\Java\\java_study\\SysGears\\Converter\\src\\input.json");
			InputData in = gUnit.fromJson(new FileReader("C:\\Java\\java_study\\SysGears\\Converter\\src\\in_data.json"), InputData.class);
			dist= c.convert(in.distance.value, in.distance.unit, in.convert_to);
			System.out.println(dist);
//			var c1 = new Converter();
//			c1.setConvertedTable("C:\\Java\\java_study\\SysGears\\Converter\\src\\input.json");
//			System.out.println(c1.convert(254, "mm", "in"));
		}catch(IOException e) {
			System.out.println(e);
		}
		c.writeToJsonFile(dist, "C:\\Java\\java_study\\SysGears\\Converter\\src\\output.json");
		
		
		
	}

}
