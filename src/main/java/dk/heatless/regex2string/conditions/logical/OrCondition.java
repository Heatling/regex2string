package dk.heatless.regex2string.conditions.logical;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.conditions.CompareCondition;

/**
 * A condition that compares the results of two other condition
 * with the logical 'or' operator.
 */
public class OrCondition extends CompareCondition {
	
	/**
	 * Constructs a new condition who's acceptance depends
	 * on a logical 'or' comparison between the given conditions.
	 * @param c1
	 * @param c2
	 */
	public OrCondition(Condition c1, Condition c2) {
		super(c1, c2);
	}

	@Override
	protected boolean acceptNotNull(GenerationState state){
		return c1.accept(state) || c2.accept(state);
	}

}
