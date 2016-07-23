package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;

public class ValidStringHasBeenGenerated implements Condition {

	@Override
	public boolean accept(GenerationState state) {
		return state == null ? false: state.completed();
	}

}
