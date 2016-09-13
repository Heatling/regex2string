package dk.heatless.regex2string.special;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * <b>WARNING</b>: This implementation <b>does not</b>, by design, 
 * <b>conform</b> with the requirements of the {@link Generator} interface. 
 * <b>Use at your own risk</b>.<br>
 * <br>
 * A generator wrapper that 'tricks' its caller to always think that it successfully generated something.<br>
 * The {@link #generate} method tried to make its internal generator generate, 
 * but if it does not do so (IE returns {@code null}), 
 * this class will return the same GenerationState instance it received (effectively generating nothing). 
 * <b>This goes against the {@link Generator} interface</b> and is used to make callers always think
 * that something was generated.<br>
 * <br>
 * Dangers of using this class:
 * <ul>
 * 		<li>
 * 			If used in any kind of loop that is intended to terminate when {@code null} is returned 
 * 			by the generate() method, the loop will run forever (or run more than intended).
 * 		</li>
 * </ul>
 * 
 * 
 * @deprecated
 * This class will be eternally deprecated because of its non-conformity with its interface.<br>
 * The class may be removed at any future release without warning.
 */
@Deprecated
public class OptionalGenerator implements Generator {

//Fields
	/**
	 * The generator to use.
	 */
	private Generator option;
	
//Constructors
	/**
	 * Constructs a generator that uses the given generator to generate with.<br>
	 * If the given generator cannot generate, the given {@link GenerationState} is returned.<br>
	 * <b>Use with caution.</b>
	 * @param option
	 */
	public OptionalGenerator(Generator option){
		if(option == null){
			throw new IllegalArgumentException("Generator was null");
		}
		this.option = option;
	}
	
	/**
	 * Returns what the given generator generates, or, if it fails, returns the given state.<br>
	 * <b>Use with caution.</b>
	 * 
	 */
	@Override
	public GenerationState generate(GenerationState state) {
		GenerationState temp = option.generate(state);
		return (temp == null)? state: temp;
	}

}
