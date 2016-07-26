package dk.heatless.regex2string.conditions;

import dk.brics.automaton.Transition;
import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;

/**
 * To meet the condition, a letter must be accepted as the next character to be generated.<br>
 * A character is defined by {@link Character#isLetter()}.
 */
public class LetterCondition extends NotNullCondition {

	@Override
	public boolean accept(GenerationState state) {
		for(Transition t : state.getCurrentState().getTransitions()){
			if(Character.isLetter(t.getMin()) && Character.isLetter(t.getMin())){
				return super.accept(state);
			}
		}
		return false;
	}

}
