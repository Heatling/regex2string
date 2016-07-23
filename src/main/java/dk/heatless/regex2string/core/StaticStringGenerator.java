package dk.heatless.regex2string.core;

public class StaticStringGenerator implements Generator {
	
	public final String toGenerate;
	
	public StaticStringGenerator(String toGenerate){
		this.toGenerate = toGenerate;
	}
	
	@Override
	public String generate() {
		return this.toGenerate;
	}
	
}
