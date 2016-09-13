package dk.heatless.regex2string.generators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * A generator that generates the minimal character accepted by the generation state.<br>
 * Minimal is defined by a char comparison.
 */
public class MinimalCharacterGenerator implements Generator{
	
	/**
	 * 
	 * @param t
	 * @param addTo
	 * @return
	 */
	protected boolean getMinimal(Transition t, List<Character> addTo){
		addTo.add(t.getMin());
		return true;
	}
	
	@Override
	public GenerationState generate(GenerationState state) {
		Set<Transition> transitions = state.getCurrentState().getTransitions();
		List<Character> minChars = new ArrayList<Character>();
		for(Transition t : transitions){
			getMinimal(t, minChars);
		}
	
		minChars.sort(new Comparator<Character>(){

			@Override
			public int compare(Character c1, Character c2) {
				return c1-c2;
			}
			
		});
		
		return minChars.size()>0? state.step(minChars.get(0)) :null;
	}

}
