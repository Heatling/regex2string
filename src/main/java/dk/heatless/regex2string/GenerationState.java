package dk.heatless.regex2string;

import java.util.ArrayList;
import java.util.List;

import dk.brics.automaton.State;
import dk.heatless.regex2string.utilities.StateOperations;


public class GenerationState {

//Fields
	private final State currentState;
	private final String generatedString;
	private final GenerationState previous;

//Constructors
	public GenerationState(State initialState) {
		this.currentState = initialState;
		this.generatedString = "";
		this.previous = null;
	}
	
	public GenerationState(State initialState, String generatedString, GenerationState previous){
		this.currentState = initialState;
		this.generatedString = generatedString;
		this.previous = previous;
	}
	

//Methods

	public GenerationState apply(Rule rule) {
		String result = rule.applicationResult(this);
		if(result != null){
			return apply(result);
		}
		return null;
	}
	
	public GenerationState apply(String text) {
		State newState = StateOperations.stringStep(this.currentState, text);
		if(newState != null){
			return new GenerationState(newState, text, this);
		}
		return null;
	}

	public boolean completed(){
		return this.currentState.isAccept();
	}
	
//Accessors
	public State getCurrentState() {
		return currentState;
	}
	
	public String getGenerated(){
		return getGeneratedWithPrevious(new ArrayList<String>());
	}

	public GenerationState getPrevious(){
		return this.previous;
	}

//Private methods
	private String getGeneratedWithPrevious(List<String> generatedAfter){
		if(previous == null){
			//This is the root state
			StringBuilder result = new StringBuilder();
			
			//Construct complete string
			for(int i = generatedAfter.size()-1; i>=0; i--){
				result.append(generatedAfter.get(i));
			}
			return result.toString();
		}else{
			//not the root
			generatedAfter.add(this.generatedString);
			return this.previous.getGeneratedWithPrevious(generatedAfter);
		}
	}
	
}
