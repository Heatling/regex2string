package dk.heatless.regex2string.conditions;

import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

/**
 * To meet the condition, a letter must be accepted as the next character to be generated.<br>
 * A letter is defined by {@link Character#isLetter}.
 */
public class LetterCondition extends NotNullCondition {

	@Override
	protected boolean acceptNotNull(GenerationState state) {
		for(Transition t : state.getCurrentState().getTransitions()){
			return Character.isLetter(t.getMin()) && Character.isLetter(t.getMax());
		}
		return false;
	}

}
