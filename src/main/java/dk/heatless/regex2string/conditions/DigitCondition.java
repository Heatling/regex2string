package dk.heatless.regex2string.conditions;

import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

/**
 * Condition that requires the state to accept a digit.<br>
 * A digit is defined by {@link Character#isDigit}.
 */
public class DigitCondition extends NotNullCondition {

	@Override
	protected boolean acceptNotNull(GenerationState state) {
		for(Transition t : state.getCurrentState().getTransitions()){
			return Character.isDigit(t.getMin()) && Character.isDigit(t.getMax());
		}
		return false;
	}

}
