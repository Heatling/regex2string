package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;

public class AtGenerationIndex implements Condition {
	
	private final int index;
	
	public AtGenerationIndex(int index){
		if(index < 0){
			throw new IllegalArgumentException("Index is negative");
		}
		
		this.index = index;
	}
	
	@Override
	public boolean accept(GenerationState state) {
		return state == null ? false : state.getLengthOfGenerated() == index;
	}

}
