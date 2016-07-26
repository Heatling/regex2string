package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.GenerationState;

/**
 * A condition that specifies that the string generated up to this point 
 * must match the {@link RegExp regular expression} linked to the {@link GenerationState}. 
 */
public class ValidStringHasBeenGeneratedCondition extends NotNullCondition {

	@Override
	protected boolean acceptNotNull(GenerationState state) {
		return state.completed();
	}

}
