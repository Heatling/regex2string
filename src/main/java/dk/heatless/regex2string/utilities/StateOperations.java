package dk.heatless.regex2string.utilities;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.heatless.regex2string.GenerationState;

/**
 * A collection of methods that operate on regular expression {@link State States} and 
 * {@link GenerationState GenerationStates}.
 */
public class StateOperations {
	
	/**
	 * Steps through the given initial state with the given string, returning the resulting state.<br>
	 * This is done by using the {@link State#step()} with each character in the given string.
	 * @param initialState
	 * to start from. IE {@link State#step step(text.charAt(0))}.
	 * @param text
	 * to step
	 * @return
	 * The {@link State} returned by the final {@link State#step} call.
	 */
	public static State stringStep(State initialState, String text){
		
		if(initialState == null){
			throw new IllegalArgumentException("State was null");
		}
		if(text == null){
			throw new IllegalArgumentException("String was null");
		}
		
		return privateStringStep(initialState, text, 0, text.length());
	}
	
	/**
	 * Steps through the given initial state with a substring of the given string, returning the resulting state.<br>
	 * This is done by using the {@link State#step()} with the characters in the substring.<br>
	 * The substring is defined by the given offsets.
	 * @param initialState
	 * to start from. IE {@link State#step step(text.charAt(start))}.
	 * @param text
	 * to step
	 * @param start
	 * The start index in the given text to use in stepping. Inclusive.
	 * @param end
	 * The end index in the given text to use in stepping. Exclusive.
	 * @return
	 */
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
	
	/**
	 * 
	 * @param regex
	 * @return
	 * The given regular expressions {@link Automaton#getInitialState initial state}.
	 */
	public static GenerationState getInitialGenerationState(RegExp regex) {
		return new GenerationState(regex.toAutomaton().getInitialState());
	}
	
//Private methods
	/**
	 * Implements {@link #stringStep(State, String, int, int)} except it does not do any
	 * argument checking.
	 * @param initialState
	 * @param text
	 * @param start
	 * @param end
	 * @return
	 */
	private static State privateStringStep(State initialState, String text, int start, int end){
		for(int i = start; (i<end) && (initialState != null); i++){
			initialState = initialState.step(text.charAt(i));
		}
		return initialState;
	}

	
}
