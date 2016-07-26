package dk.heatless.regex2string.rules;

import dk.heatless.regex2string.Conditions;
import dk.heatless.regex2string.Rule;
import dk.heatless.regex2string.core.Generator;

/**
 * A rule that generates a String that on its own matches the {@link RegExp regular expression}.
 */
public class CompleteRegexMatchRule extends Rule {
	
	/**
	 * Constructs a rule that is applicable only if nothing has been generated yet and
	 * the generated string from the given {@link Generator generator} matches the {@link RegExp regular expression}.
	 * @param generator
	 * Generates a string that matches the regular expression.
	 */
	public CompleteRegexMatchRule(Generator generator){
		super(	Conditions.START_OF_GENERATION, 
				Conditions.VALID_STRING_HAS_BEEN_GENERATED, 
				generator);
	}
	
}