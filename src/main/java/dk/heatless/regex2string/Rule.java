package dk.heatless.regex2string;

import dk.heatless.regex2string.core.Generator;

/**
 * Specifies a {@link String} generation rule.<br>
 * A rule consists of a {@link Condition precondition}, a {@link Condition postcondition}, and a {@link Generator generator}.
 * For a given rule to be applicable to a {@link GenerationState state} the following steps must be true:
 * <ol>
 * 	<li>The given state must comply with the precondition.</li>
 * 	<li>A generated {@link String} must be accepted by the state.</li>
 *	<li>The state that is a result of applying the aforementioned {@link String} to the original state must comply with the postcondition.</li>
 * </ol>
 * <br>
 * The {@link #applicationResult} method evaluates whether a rule applies to a given state. 
 * If it does, a string that was generated by the rule is returned, otherwise {@link null} is returned, 
 * specifying that the rule cannot be applied to the state.
 */
public class Rule {

//Fields
	/**
	 * A condition that a given state must comply with.
	 */
	private Condition precondition;
	
	/**
	 * A condition that must be true of the state that is the result of applying 
	 * the {@link String} generated from the {@link #generator}to the original state.
	 */
	private Condition postcondition;
	
	/**
	 * Generates a {@link String} for applying to a given {@link GenerationState}.
	 */
	private Generator generator;

//Constructors
	
	/**
	 * Constructs a new rule with the given precondition, postcondition, and generator.
	 * @param precondition
	 * Must be met by any {@link GenerationState state} that the rule is to be applied to.
	 * @param postcondition
	 * Must be met by the {@link GenerationState state} that is the result of applying the generated string to a {@link GenerationState state} that 
	 * the rule is to be applied to.
	 * @param generator
	 * Generates a string to apply to a {@link GenerationState state} that the rule is to be applied to.
	 */
	public Rule(Condition precondition, Condition postcondition, Generator generator) {
		if(precondition == null){
			throw new IllegalArgumentException("Precondition was null");
		}
		if(postcondition == null){
			throw new IllegalArgumentException("Postcondition was null");
		}
		if(generator == null){
			throw new IllegalArgumentException("Generator was null");
		}
		
		this.precondition = precondition;
		this.postcondition = postcondition;
		this.generator = generator;
		
	}

//Methods
	/**
	 * Evaluates whether the rule instance can be applied to the given state.
	 * @param state
	 * to evaluate
	 * @return
	 * If the rule can be applied to the given state, returns a {@link String} which is a result of the application.<br>
	 * Otherwise returns {@link null}.
	 */
	public String applicationResult(GenerationState state){
		if(precondition.accept(state)){
			String generated = generator.generate();
			if(postcondition.accept(state.apply(generated))){
				return generated;
			}
		}
		return null;
	}

//Accessors
	/**
	 * @return
	 * The condition that a given {@link GenerationState} must comply with for the rule to be applicable.
	 * 
	 */
	public Condition getPrecondition() {
		return precondition;
	}
	
	/**
	 * 
	 * @return
	 * The condition that must be true of the state that is the result of applying 
	 * the {@link String} generated from the {@link #getGenerator generator}to the original state.
	 */
	public Condition getPostcondition() {
		return postcondition;
	}
	
	/**
	 * @return
	 * A {@link Generator} that generates a {@link String} for applying to a given {@link GenerationState} that the rule can be applied to.
	 */
	public Generator getGenerator() {
		return generator;
	}
}
