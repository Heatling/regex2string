package dk.heatless.regex2string;

/**
 * An implementing class specifies a condition that must be met by given {@link GenerationState GenerationStates}.<br>
 * 
 */
public interface Condition{
	
	/**
	 * @param state
	 * @return
	 * {@code true} if the given state meets the condition of the instance, otherwise {@code false}.<br>
	 * if the state is {@code null} it assumed to not meet the condition.
	 */
	public boolean accept(GenerationState state);
}
