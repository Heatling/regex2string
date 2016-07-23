package dk.heatless.regex2string;

import dk.brics.automaton.RegExp;
import dk.heatless.regex2string.core.Generator;
import dk.heatless.regex2string.rules.CompleteRegexMatchRule;
import dk.heatless.regex2string.utilities.StateOperations;

public abstract class RegexGenerator implements Generator {

//Fields
	private final RegExp regex;
	
//Constructors
	
	public RegexGenerator(RegExp regex){
		this.regex = regex;
	}

//Abstract methods
	protected abstract Generator createGeneratorForRegex(RegExp regex);

//Methods
	@Override
	public String generate() {
		GenerationState currentState = StateOperations.getInitialGenerationState(this.regex);
		currentState = currentState.apply(new CompleteRegexMatchRule(createGeneratorForRegex(regex)));
		return (currentState != null)? currentState.getGenerated(): null; 
	}
	
//Accessors
	public RegExp getRegex(){
		return this.regex;
	}

}
