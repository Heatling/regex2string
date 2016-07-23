package dk.heatless.regex2string.core;

/**
 * Implementing classes can generate {@link String Strings} on command.
 */
public interface Generator {
	
	/**
	 * @return
	 */
	public String generate();
}
