package dk.heatless.regex2string;

/**
 * An implementing class specifies whether it can be applied to given 
 * {@link GenerationState GenerationStates}.<br>
 * An instance can be applied if it can produce a string that can be 
 * {@link GenerationState#step(String) stepped}.
 */
public interface Applicable {
	/**
	 * Evaluates whether the instance can be applied to the given state.
	 * @param state
	 * to evaluate
	 * @return
	 * If it can be applied to the given state, returns a {@link String} which is a result of the application.<br>
	 * Otherwise returns {@link null}.
	 */
	public String applicationResult(GenerationState state);
}
