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
 * 	<li>The char used to {@link #step} with from the previous {@link GenerationState} that resulted in the current one.</li>
 * </ul>
 * <br>
 * The process of generating a {@link String} from a {@link RegExp regular expression} is modeled by this class as follows:
 * <ol>
 * 	<li>Initially, the {@link GenerationState} holds the {@link Automaton#getInitialState initial state} 
 * 		of the {@link RegExp regular expression} {@link Automaton} and no previous {@link GenrationState}.</li>
 * 	<li>
 * 		{@link #apply(Rule) Applying a Rule} or a {@link #step(char) character} to the {@link GenerationState} 
 * 		results in a new {@link GenerationState}. <br>
 * 		The new {@link GenerationState} holds the current {@link State} of the {@link Automaton}
 * 		after using the stepped character (previously mentioned) to {@link State#step step} through the automaton.<br>
 * 		Additionally, the new {@link GenerationState} holds the previous {@link GenerationState} and the {@code char}
 * 		that was stepped to the previous {@link GenerationState} to get to the new one.
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
public class GenerationState {

//Fields
	/**
	 * The state of the regex automaton if the string generated
	 * to achieve this generation state was run
	 */
	private final State currentState;
	/**
	 * The char value that was generated to get to this generation state.
	 * If this generation state is the initial state, the value of this char
	 * is not part of the generated string.
	 */
	private final char stepChar;
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
		this.stepChar = ' ';
		this.previous = null;
	}
	
	/**
	 * Constructs an intermediate generation state with the given state parameters.
	 * @param currentState
	 * The current {@link State} of the {@link RegExp regular expression} {@link Automaton} after applying
	 * the given string, and whatever was generated before it, to the {@link Automaton#getInitialState() initial state}
	 * of the @link RegExp regular expression}.
	 * @param stepChar
	 * The char that was generated to result in the generation state that is being constructed.
	 * @param previous
	 * The previous state in the generation process.
	 */
	public GenerationState(State currentState, char stepChar, GenerationState previous){
		this.currentState = currentState;
		this.stepChar = stepChar;
		this.previous = previous;
	}
	

//Methods
	
	/**
	 * Tries to apply the given {@link Rule} to the current state.
	 * @param rule
	 * to try to apply
	 * @return
	 * The generation state that was the result of a successful application of the given {@link Rule}.<br>
	 * If the rule cannot be applied to the state, {@code null} is returned.
	 */
	public GenerationState apply(Rule rule) {
		String result = rule.applicationResult(this);
		if(result != null){
			return step(result);
		}
		return null;
	}
	
	/**
	 * Tries to step with the given {@link String} on the current state.
	 * @param text
	 * to try to step with.
	 * @return
	 * The generation state that was the result of a successful application of the given {@link String}.<br>
	 * If the string cannot be applied to the state, {@code null} is returned.
	 */
	public GenerationState step(String text) {
		GenerationState newState = this;
		for(int i = 0; i<text.length() && newState != null; i++){
			newState = newState.step(text.charAt(i));
		}
		return newState;
	}
	
	/**
	 * Step with the given char returning the resulting generation state.
	 * @param c
	 * Character to generate.
	 * @return
	 * The resulting state, if the character is valid, otherwise {@code null}.
	 */
	public GenerationState step(char c){
		State newState = currentState.step(c);
		
		return (newState != null) ? 
				new GenerationState(newState, c, this)
				: 
				null;
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
		return getGeneratedWithPrevious(new StringBuilder());
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
			return 1 + previous.getLengthOfGenerated();
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
	 * ******
	 * Rename
	 * ******
	 * Used to construct the result of {@link #getGenerated()}.<br>
	 * Traverses all previous states recursively and collects their {@link #stepChar}.
	 * The result will be in reverse order. The base case will then reorder and return the string
	 * that was generated to reach the calling generation state.
	 * @param generatedAfter
	 * all the generated chars in reverse order.
	 * @return
	 * The complete string that was run on the regex automaton to result in this generation state.
	 */
	private String getGeneratedWithPrevious(StringBuilder result){
		if(previous == null){
			//This is the root state
			
			/* This reversing is potentially problematic when used
			 * with multi-byte character sets.
			 */
			result.reverse();
			return result.toString();
		}else{
			//not the root
			result.append(this.stepChar);
			return this.previous.getGeneratedWithPrevious(result);
		}
	}
	
}
