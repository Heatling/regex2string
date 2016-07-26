package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;

/**
 * A condition that accepts anything except {@code null}.
 */
public class NotNullCondition implements Condition {

	@Override
	public boolean accept(GenerationState state) {
		return state != null;
	}

}
