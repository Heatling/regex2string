package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;

/**
 * A condition that specifies that the string generated up to this point, 
 * must match the {@link RegExp regular expression}. 
 */
public class ValidStringHasBeenGenerated implements Condition {

	@Override
	public boolean accept(GenerationState state) {
		return state == null ? false: state.completed();
	}

}
