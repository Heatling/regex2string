package dk.heatless.regex2string;

import dk.brics.automaton.RegExp;
import dk.heatless.regex2string.utilities.StateOperations;

public class GeneratorWrapper {

//Fields
	
	private Generator g;
	
//Constructors
	
	public GeneratorWrapper(Generator g){
		this.g = g;
	}
	
//Methods
	
	public String generateForRegex(String regex){
		return generateForRegex(new RegExp(regex));
	}
	
	public String generateForRegex(RegExp regex){
		return (g.generate(StateOperations.getInitialGenerationState(regex))).getGenerated();
	}
	
}
