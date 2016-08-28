package dk.heatless.regex2string.generators;

import java.util.HashSet;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.rules.Rule;

/**
 * A generator that returns a generated string based on an arbitrarily chosen, applicable rule.
 */
public class RuleSetGenerator extends HashSet<Rule> implements Generator {

	/**
	 * Chooses an arbitrary rule in the set that is applicable, and returns its generated string.<br>
	 * If no Rule is applicable, null is returned.
	 */
	@Override
	public String generate(GenerationState state) {
		String result;
		
		for(Rule r : this){
			result = r.generate(state);
			if(result != null){
				return result;
			}
		}
		return null;
	}
}
