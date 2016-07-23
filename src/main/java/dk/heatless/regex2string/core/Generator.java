package dk.heatless.regex2string.core;

/**
 * Implementing classes might be able generate {@link String Strings} on command.<br>
 * Implementors must return {@code null} if not able to generate a string.
 */
public interface Generator {
	
	/**
	 * @return a {@link String} if able to, otherwise {@code null}
	 */
	public String generate();
}
