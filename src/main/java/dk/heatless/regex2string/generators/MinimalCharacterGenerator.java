package dk.heatless.regex2string.generators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

public class MinimalCharacterGenerator extends VerifiedGenerator{
	
	protected boolean getMinimal(Transition t, List<Character> addTo){
		addTo.add(t.getMin());
		return true;
	}
	
	@Override
	protected String generateUnverified(GenerationState state) {
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
		
		return minChars.size()>0? Character.toString(minChars.get(0)) :null;
	}

}
