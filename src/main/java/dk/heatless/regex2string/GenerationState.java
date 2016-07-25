package dk.heatless.regex2string;

import java.util.ArrayList;
import java.util.List;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.State;
import dk.heatless.regex2string.utilities.StateOperations;

/**
 * Holds the state of the process of generating a {@link String} from a {@link RegExp regular expression}.<br>
 * <br>
 * Each instance of the class holds the following:
 * <ul>
 * 	<li>The current {@link State} in the {@link Automaton} of the {@link RegExp regular expression}.</li>
 * 	<li>The previous {@link GeneratedState} of the generation process before reaching the current one.</li>
 * 	<li>The string applied to the previous {@link GenerationState} that resulted in the current one.</li>
 * </ul>
 * <br>
 * The process of generating a {@link String} from a {@link RegExp regular expression} is modeled by this class as follows:
 * <ol>
 * 	<li>Initially, the {@link GenerationState} holds the {@link Automaton#getInitialState initial state} 
 * 		of the {@link RegExp regular expression} {@link Automaton} and no previous {@link GenrationState}.</li>
 * 	<li>
 * 		{@link #apply(Rule) Applying a Rule} or a {@link #apply(String) String} to the {@link GenerationState} 
 * 		results in a new {@link GenerationState}. <br>
 * 		The new {@link GenerationState} holds the current {@link State} of the {@link Automaton}
 * 		after using the applied string (previously mentioned) to {@link State#step step} through the automaton.<br>
 * 		Additionally, the new {@link GenerationState} holds the previous {@link GenerationState} and the {@link String}
 * 		that was applied to the previous {@link GenerationState} to get to the new one.
 * 	</li>
 * 	<li>The generation process is {@link #completed} when the {@link Automaton} is in an {@link State#isAccept acceptance state}, 
 * 		which means that the generated {@link String} matches the {@link RegExp regular expression}.</li>
 * </ol>
 * <br>
 * Each instance of this class can be seen as immutable since it never changes its internal state, but returns a new instance that
 * represents the new generation process state. Although, users must be aware, that the instances returned by this class's getters might be mutable
 * and are the exact ones used by the class. Changing the state of those instances will change the abstract state of the class but not the internal state,
 * which could result in the class instance not complying with the class invariants. Therefore, making such changes to the instances must be done with caution.
 */
public final class GenerationState {

//Fields
	/**
	 * The state of the regex automaton if the string generated
	 * to achieve this generation state was run
	 */
	private final State currentState;
	/**
	 * The String value that was generated to get to this generation state.
	 * If this generation state is the initial state, the value of this string
	 * is not part of the final generated string.
	 */
	private final String generatedString;
	/**
	 * The previous generation state. If null, then this state is the initial generation state
	 * IE nothing has been generated before.
	 */
	private final GenerationState previous;

//Constructors
	/**
	 * Constructs a new generation state that assumes itself to be the initial state.<br>
	 * @param initialState
	 * The initial {@link State} of the {@link RegExp regular expression} {@link Automaton}.
	 */
	public GenerationState(State initialState) {
		this.currentState = initialState;
		this.generatedString = "";
		this.previous = null;
	}
	
	/**
	 * Constructs an intermediate generation state with the given state parameters.
	 * @param currentState
	 * The current {@link State} of the {@link RegExp regular expression} {@link Automaton} after applying
	 * the given string, and whatever was generated before it, to the {@link Automaton#getInitialState() initial state}
	 * of the @link RegExp regular expression}.
	 * @param generatedString
	 * The string that was generated to result in the generation state that is being constructed.
	 * @param previous
	 * The previous state in the generation process.
	 */
	public GenerationState(State currentState, String generatedString, GenerationState previous){
		this.currentState = currentState;
		this.generatedString = generatedString;
		this.previous = previous;
	}
	

//Methods
	
	/**
	 * Tries to apply the given {@link Rule} to the current state.
	 * @param rule
	 * to try to apply
	 * @return
	 * The generation state that was the result of a successfull application of the given {@link Rule}.<br>
	 * If the rule cannot be applied to the state, {@code null} is returned.
	 */
	public GenerationState apply(Rule rule) {
		String result = rule.applicationResult(this);
		if(result != null){
			return apply(result);
		}
		return null;
	}
	
	/**
	 * Tries to apply the given {@link String} to the current state.
	 * @param text
	 * to try to apply
	 * @return
	 * The generation state that was the result of a successful application of the given {@link String}.<br>
	 * If the string cannot be applied to the state, {@code null} is returned.
	 */
	public GenerationState apply(String text) {
		State newState = StateOperations.stringStep(this.currentState, text);
		if(newState != null){
			return new GenerationState(newState, text, this);
		}
		return null;
	}
	
	/**
	 * @return
	 * Whether the {@link String} has been generated up to this generation state 
	 * matches the regular expression.
	 */
	public boolean completed(){
		return this.currentState.isAccept();
	}
	
//Accessors
	/**
	 * @return
	 * The state of the {@link RegExp regular expression} {@link Automaton} if the {@link String} generated
	 * (and any before) to achieve this generation state was run on the automaton.
	 */
	public State getCurrentState() {
		return currentState;
	}
	
	/**
	 * @return
	 * The {@link String} value that was generated to get to this generation state.
	 * If this generation state is the {@link Automaton#getInitialState initial state}, the value of this string
	 * is not part of the final generated string.
	 */
	public String getGenerated(){
		return getGeneratedWithPrevious(new ArrayList<String>());
	}
	
	/**
	 * @return
	 * The length of the {@link String} returned by {@link #getGenerated}.
	 */
	public int getLengthOfGenerated(){
		if(previous == null){
			//root
			return 0;
		}else{
			return generatedString.length() + previous.getLengthOfGenerated();
		}
	}
	
	/**
	 * @return
	 * The previous generation state. If {@code null}, then this state is the initial generation state
	 * IE nothing has been generated before.
	 */
	public GenerationState getPrevious(){
		return this.previous;
	}

//Private methods
	/**
	 * Used to construct the result of {@link #getGenerated()}.<br>
	 * Traverses all previous states recursively and collects their {@link #generatedString} into the given list.
	 * The list will then have all the substrings in reverse order. The base case will then concatenate them in correct order
	 * into the complete string that has been generated to result in this generation state.
	 * @param generatedAfter
	 * a list all the generated substrings in reverse order.
	 * @return
	 * The complete string that was run on the regex automaton to result in this generation state.
	 */
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
