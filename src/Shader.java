import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Shader {

	// TODO:Error Handling
	
	private String type;
	private String shaderCode;
	
	public Shader(String sType, String code) {
		type = sType;
		shaderCode = code;
	}
	
	public Shader(String path) throws IllegalArgumentException, FileNotFoundException, IOException {
		shaderCode = "";
		
		String line;
		
		BufferedReader shader = new BufferedReader(new FileReader(path));
		
		line = shader.readLine();
		
		if (line.equals("vertex")) {
			type = "vertex";
		}
		else if (line.equals("fragment")) {
			type = "fragment";
		}
		else {
			System.out.println("Unknown type of shader: " + path);
			shader.close();
			throw new IllegalArgumentException();
		}
		
		while ((line = shader.readLine()) != null)
			shaderCode += line + "\n";
		
		shader.close();
	}
	
	public String getType() {
		return type;
	}
	
	public String getCode() {
		return shaderCode;
	}
}
