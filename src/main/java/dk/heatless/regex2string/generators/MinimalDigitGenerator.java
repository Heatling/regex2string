package dk.heatless.regex2string.generators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

/**
 * A generator that generates the minimal digit accepted by the generation state.<br>
 * Minimal is defined by a char comparison. A digit is defined by {@link Character#isDigit}.
 */
public class MinimalDigitGenerator extends VerifiedGenerator {

	@Override
	protected String generateUnverified(GenerationState state) {
		Set<Transition> transitions = state.getCurrentState().getTransitions();
		List<Character> minDigits = new ArrayList<Character>();
		char tempMin;
		for(Transition t : transitions){
			tempMin = t.getMin();
			if(Character.isDigit(tempMin)){
				minDigits.add(tempMin);
			}
		}
	
		minDigits.sort(new Comparator<Character>(){

			@Override
			public int compare(Character c1, Character c2) {
				return c1-c2;
			}
			
		});
		
		return minDigits.size()>0? Character.toString(minDigits.get(0)) :null;
	}

}
