package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

public class CanGenerateCondition extends NotNullCondition {
	
	private Generator g;
	
	public CanGenerateCondition(Generator g){
		this.g  = g;
	}
	
	
	@Override
	public boolean acceptNotNull(GenerationState state) {
		return state.apply(g) != null;
	}

}
