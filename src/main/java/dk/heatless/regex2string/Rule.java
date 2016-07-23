package dk.heatless.regex2string;

import dk.heatless.regex2string.core.Generator;

public class Rule {

//Fields
	private Condition precondition;
	private Condition postcondition;
	private Generator generator;

//Constructors
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
	public String applicationResult(GenerationState initialState){
		if(precondition.accept(initialState)){
			String generated = generator.generate();
			if(postcondition.accept(initialState.apply(generated))){
				return generated;
			}
		}
		return null;
	}

//Accessors
	public Condition getPrecondition() {
		return precondition;
	}

	public Condition getPostcondition() {
		return postcondition;
	}

	public Generator getGenerator() {
		return generator;
	}
}
