package dk.heatless.regex2string.rules;

import dk.heatless.regex2string.Conditions;
import dk.heatless.regex2string.Rule;
import dk.heatless.regex2string.core.Generator;

public class CompleteRegexMatchRule extends Rule {

	public CompleteRegexMatchRule(Generator generator){
		super(	Conditions.START_OF_GENERATION, 
				Conditions.VALID_STRING_HAS_BEEN_GENERATED, 
				generator);
	}
	
}
