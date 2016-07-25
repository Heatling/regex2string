package dk.heatless.regex2string;

import dk.brics.automaton.RegExp;
import dk.heatless.regex2string.core.Generator;
import dk.heatless.regex2string.rules.CompleteRegexMatchRule;
import dk.heatless.regex2string.utilities.StateOperations;

/**
 * An abstract {@link Generator} class that guarantees that the strings any of its subclasses
 * generate are valid in a given regular expression.
 */
public abstract class RegexGenerator implements Generator {

//Fields
	/**
	 * The regex that any generated string must match.
	 */
	private final RegExp regex;
	
//Constructors
	
	/**
	 * Constructs a new generator that guarantees that any strings it generates match
	 * the given regular expression.
	 * @param regex
	 * Regular expression that any {@link String} returned from {@link #generate()} method matches.
	 */
	public RegexGenerator(RegExp regex){
		this.regex = regex;
	}

//Abstract methods
	
	/**
	 * Returns a generator that can generate a {@link String} that will match the given
	 * regular expression.
	 * @param regex
	 * The regular expression to match.
	 * @return
	 * A {@link Generator} that will generate a {@link String} to match the given regular expresion.
	 */
	protected abstract Generator createGeneratorForRegex(RegExp regex);

//Methods
	@Override
	public String generate() {
		GenerationState currentState = StateOperations.getInitialGenerationState(this.regex);
		currentState = currentState.apply(new CompleteRegexMatchRule(createGeneratorForRegex(regex)));
		return (currentState != null)? currentState.getGenerated(): null; 
	}
	
//Accessors
	/**
	 * 
	 * @return
	 * The regular expression that any {@link String} returned by {@link #generate()} will match.
	 */
	public RegExp getRegex(){
		return this.regex;
	}

}
