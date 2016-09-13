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
	
//Fields
	
	/**
	 * Number generator
	 */
	private Random r;
	
//Constructors
	
	/**
	 * Constructs a generator that generates a random valid character.<br>
	 * For randomness {@link Random} is used.<br>
	 * <br>
	 * Is equivalent to {@code new RandomCharacterGenerator(new Random())}
	 */
	public RandomCharacterGenerator(){
		this(new Random());
	}
	
	/**
	 * Constructos a generator that generates a random valid character.<br>
	 * 
	 * @param r
	 * Used for randomness.
	 */
	public RandomCharacterGenerator(Random r){
		this.r = r;
	}
	
//methods
	
	/**
	 * Generates a random valid character.
	 */
	@Override
	public GenerationState generate(GenerationState state) {
		Set<Transition> transitions = state.getCurrentState().getTransitions();
		char result;
		
		if(transitions.size() != 0){
			int tempR = r.nextInt(transitions.size());
			int i = 0;
			for(Transition t : transitions){
				if(i == tempR){
					if(t.getMin() != t.getMax()){
						tempR = r.nextInt(t.getMax() - t.getMin());
						result = (char)(t.getMin() + tempR);
					}else{
						result =  t.getMin();
					}
					return state.step(result);
				}else{
					i++;
				}
			}
		}
		return null;
	}

}
