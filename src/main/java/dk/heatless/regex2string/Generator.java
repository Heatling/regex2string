package dk.heatless.regex2string;

/**
 * Implementing classes might be able generate {@link String Strings} that given generation states can step.<br>
 * Implementors must return {@code null} if not able to generate a such strings.
 */
public interface Generator {
	
	/**
	 * @param state
	 * to step
	 * @return 
	 * a non-empty {@link String} that the given generation state can {@link GenerationState#step(String) step}, otherwise {@code null}.
	 */
	public String generate(GenerationState state);
}
