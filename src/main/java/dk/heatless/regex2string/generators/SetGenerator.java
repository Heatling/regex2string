package dk.heatless.regex2string.generators;

import java.util.HashSet;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * A generator that returns a generated string based on an arbitrarily chosen, applicable generator.
 */
public class SetGenerator extends HashSet<Generator> implements Generator {

	/**
	 * Chooses an arbitrary generator in the set that is applicable, and returns its generated GenerationState.<br>
	 * Only if no generator is applicable, null is returned.
	 */
	@Override
	public GenerationState generate(GenerationState state) {
		GenerationState result;
		
		for(Generator r : this){
			result = r.generate(state);
			if(result != null){
				return result;
			}
		}
		return null;
	}
}
