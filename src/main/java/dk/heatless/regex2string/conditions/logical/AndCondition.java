package dk.heatless.regex2string.conditions.logical;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.conditions.CompareCondition;

/**
 * A condition that compares the results of two other condition
 * with the logical 'an' operator.
 */
public class AndCondition extends CompareCondition {
	
	/**
	 * Constructs a condition that compares the given conditions
	 * with the logical 'and' operation.
	 */
	public AndCondition(Condition c1, Condition c2) {
		super(c1, c2);
	}

	@Override
	protected boolean acceptNotNull(GenerationState state) {
		return c1.accept(state) && c2.accept(state);
	}

}
