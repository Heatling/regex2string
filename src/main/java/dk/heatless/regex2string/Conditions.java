package dk.heatless.regex2string;

import dk.heatless.regex2string.conditions.AtGenerationIndexCondition;
import dk.heatless.regex2string.conditions.NotNullCondition;
import dk.heatless.regex2string.conditions.ValidStringHasBeenGeneratedCondition;

/**
 * A collection of standard conditions, ready to be used.
 */
public class Conditions {
	
	/**
	 * To meet this condition, the generation process must be in its initial state.
	 * IE, it must not have generated anything yet.
	 */
	public static final Condition START_OF_GENERATION = new AtGenerationIndexCondition(0);
		
	/**
	 * To meet this condition, the generation process must be completed.
	 */
	public static final Condition VALID_STRING_HAS_BEEN_GENERATED = new ValidStringHasBeenGeneratedCondition();
	
	/**
	 * Accepts anything but {@code null}.
	 */
	public static final Condition NO_CONDITION = new NotNullCondition(){
		@Override
		protected boolean acceptNotNull(GenerationState state) {
			return true;
		}
	};
	
	
	
}
