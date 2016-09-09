package dk.heatless.regex2string.special;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * <b>WARNING</b>: This implementation <b>does not</b>, by design, 
 * <b>conform</b> with the requirements of the {@link Generator} interface. 
 * <b>Use at your own risk</b>.<br>
 * <br>
 * A generator wrapper that 'tricks' its caller to always think that it successfully generated a string.<br>
 * The {@link #generate} method tried to make its internal generator generate a string, but if it does not do so (IE returns {@code null}), 
 * this class will return the empty string. <b>This goes against the {@link Generator} interface</b> and is used to make callers always think
 * that a string was successfully generated.<br>
 * <br>
 * Dangers of using this class:
 * <ul>
 * 		<li>
 * 			If used in any kind of loop that is intended to terminate when {@code null} is generated, the loop will run forever (or run more than intended),
 * 			since this class will always return a non-{@code null} string.
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
	private Generator option;
	
//Constructors
	
	public OptionalGenerator(Generator option){
		this.option = option;
	}
	
	@Override
	public String generate(GenerationState state) {
		String temp = option.generate(state);
		return (temp == null)? "": temp;
	}

}
