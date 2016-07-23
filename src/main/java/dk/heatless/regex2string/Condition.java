package dk.heatless.regex2string;

/**
 * An implementing class specifies a condition that must be met by given {@link GenerationState GenerationStates}.
 */
public interface Condition{
	
	/**
	 * @param state
	 * @return
	 * {@code true} if the given state meets the condition of the instance, otherwise {@code false}.
	 */
	public boolean accept(GenerationState state);
}
