package dk.heatless.regex2string.conditions.logical;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.conditions.NotNullCondition;
/**
 * A condition that negates another, given condition.
 */
public class NotCondition extends NotNullCondition {
	
	/**
	 * Condition to negate
	 */
	private Condition toNot;
	
	/**
	 * Constructs a condition that negates the given condition.
	 * @param toNot
	 * condition to negate
	 */
	public NotCondition(Condition toNot){
		this.toNot = toNot;
	}
	
	@Override
	protected boolean acceptNotNull(GenerationState state) {
		return !toNot.accept(state);
	}

}
