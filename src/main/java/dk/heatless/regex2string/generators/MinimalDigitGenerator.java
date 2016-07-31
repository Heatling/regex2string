package dk.heatless.regex2string.generators;

import java.util.List;

import dk.brics.automaton.Transition;

/**
 * A generator that generates the minimal digit accepted by the generation state.<br>
 * Minimal is defined by a char comparison. A digit is defined by {@link Character#isDigit}.
 */
public class MinimalDigitGenerator extends MinimalCharacterGenerator {

	public static final char SMALLEST_DIGIT = '0';
	public static final char LARGEST_DIGIT = '9';
	
	@Override 
	protected boolean getMinimal(Transition t, List<Character> addTo){
		char 	tMin = t.getMin(),
				tMax = t.getMax();
		if((tMin<SMALLEST_DIGIT)){
			if(SMALLEST_DIGIT <= tMax){
				//overlapping with 0, so add that
				addTo.add(SMALLEST_DIGIT);
				return true;
			}
		}else if((tMin<=LARGEST_DIGIT)){
			addTo.add(tMin);
			return true;
		}else{
			// tMin > LARGEST_DIGIT, not digit
		}
		return false;
	}
	
	
}
