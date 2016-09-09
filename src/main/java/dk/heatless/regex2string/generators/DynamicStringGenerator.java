package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.GenerationState;

public class DynamicStringGenerator extends VerifiedGenerator{
	
//Fields
	private String toGenerate = null;
	
//Methods
	
	@Override
	protected String generateUnverified(GenerationState state) {
		return toGenerate;
	}
	
//Accessors and mutators
	public void setToGenerate(String toGenerate){
		this.toGenerate = toGenerate;
	}
}
