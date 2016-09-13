package dk.heatless.regex2string;

/**
 * Implementing classes generate characters by generating new {@link GenerationState GenerationStates} 
 * from a given state.<br>
 * If a generator cannot generate, {@code null} is returned.
 */
public interface Generator {
	
	/**
	 * @param state
	 * to generate from
	 * @return 
	 * the resulting state of the generation. The given state must be an ancestor of the resulting state.<br>
	 * {@code null} is returned if no characters could be generated.
	 */
	public GenerationState generate(GenerationState state);
}
