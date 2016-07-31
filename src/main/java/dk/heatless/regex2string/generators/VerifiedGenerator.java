package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * An abstract generator class that guarantees that its subclasses generate a
 * valid {@link String}, and if not return {@code null}.
 */
public abstract class VerifiedGenerator implements Generator {
	
	/**
	 * Overrides return a string that is potentially invalid.
	 * @param state
	 * to generate a string for
	 * @return
	 * a string that may or may not be valid for the given state.
	 */
	protected abstract String generateUnverified(GenerationState state);
	
	/**
	 * Returns a String that the given state can step.
	 */
	@Override
	public final String generate(GenerationState state) {
		String text = generateUnverified(state);
		return text != null ? 
				(state.step(text)!= null)? text: null : null;
	}

}
