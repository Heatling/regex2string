package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;

/**
 * An abstract condition implementation that handles if the generation state given is null.
 */
public abstract class NotNullCondition implements Condition {
	
	/**
	 * Overrides evaluate whether the given state meets the condition assuming the given state is not {@code null}.
	 * @param state
	 * to evaluate.
	 * @return
	 * whether the condition is met.
	 */
	protected abstract boolean acceptNotNull(GenerationState state);
	
	@Override
	public final boolean accept(GenerationState state) {
		return (state != null)? acceptNotNull(state): false;
	}

}
