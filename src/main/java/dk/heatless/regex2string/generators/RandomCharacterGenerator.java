package dk.heatless.regex2string.generators;

import java.util.Random;
import java.util.Set;

import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * A generator that generates a random digit accepted by the generation state.
 */
public class RandomCharacterGenerator implements Generator{
	
	private Random r;
	
	public RandomCharacterGenerator(){
		this(new Random());
	}
	
	public RandomCharacterGenerator(Random r){
		this.r = r;
	}
	
	@Override
	public String generate(GenerationState state) {
		Set<Transition> transitions = state.getCurrentState().getTransitions();
		
		if(transitions.size() != 0){
			int tempR = r.nextInt(transitions.size());
			int i = 0;
			for(Transition t : transitions){
				if(i == tempR){
					if(t.getMin() != t.getMax()){
						tempR = r.nextInt(t.getMax() - t.getMin());
						return Character.toString((char)(t.getMin() + tempR));
					}else{
						return Character.toString(t.getMin());
					}
				}else{
					i++;
				}
			}
		}
		return null;
	}

}
