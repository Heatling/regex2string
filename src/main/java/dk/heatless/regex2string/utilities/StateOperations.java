package dk.heatless.regex2string.utilities;

import dk.brics.automaton.State;

public class StateOperations {
	
	public static State stringStep(State initialState, String text){
		
		if(initialState == null){
			throw new IllegalArgumentException("State was null");
		}
		if(text == null){
			throw new IllegalArgumentException("String was null");
		}
		
		return privateStringStep(initialState, text, 0, text.length());
	}
	
	public static State stringStep(State initialState, String text, int start, int end){
		if(initialState == null){
			throw new IllegalArgumentException("State was null");
		}
		if(text == null){
			throw new IllegalArgumentException("String was null");
		}
		if(start<0 || start>= text.length()){
			throw new StringIndexOutOfBoundsException("Start index out of bounds");
		}
		if(end<=0 || end>text.length()){
			throw new StringIndexOutOfBoundsException("End index out of bounds");
		}
		
		
		return privateStringStep(initialState, text, start, end);
	}
	
//Private methods
	
	private static State privateStringStep(State initialState, String text, int start, int end){
		for(int i = start; (i<end) && (initialState != null); i++){
			initialState = initialState.step(text.charAt(i));
		}
		return initialState;
	}
}
