package dk.heatless.regex2string;

import java.util.List;

import dk.brics.automaton.State;

public class GenerationState {

//Fields
	private State currentState;
	private StringBuilder generatedString;
	
//Methods
	public boolean applyRules(List<Rule> rules){
		return false;
	}
	
	public boolean completed(){
		return false;
	}
	
	public String getGenerated(){
		return null;
	}
}
