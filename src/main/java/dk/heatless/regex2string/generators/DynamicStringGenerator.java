package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

public class DynamicStringGenerator implements Generator{
	
//Fields
	private String toGenerate = null;
	
//Methods
	
	@Override
	public GenerationState generate(GenerationState state) {
		return state.step(toGenerate);
	}
	
//Accessors and mutators
	public void setToGenerate(String toGenerate){
		this.toGenerate = toGenerate;
	}
}
