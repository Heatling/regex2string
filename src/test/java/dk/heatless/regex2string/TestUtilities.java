package dk.heatless.regex2string;

import dk.brics.automaton.RegExp;
import dk.heatless.regex2string.conditions.NotNullCondition;
import dk.heatless.regex2string.generators.StaticStringGenerator;
import dk.heatless.regex2string.utilities.StateOperations;

public class TestUtilities {
	
	public static GenerationState getGenerationStateAcceptingAnything(){
		return getGenerationStateFor("(.)*");
	}
	
	public static GenerationState getGenerationStateFor(String regex){
		return StateOperations.getInitialGenerationState(new RegExp(regex));
	}
	
	public static Generator getGenerator(String produces){
		return new StaticStringGenerator(produces);
	}
	
	public static Condition getConditionAccepting(final GenerationState given){
		return new NotNullCondition(){

			@Override
			protected boolean acceptNotNull(GenerationState state) {
				return given == state;
			}
			
		};
	}
	
	public static Condition getConditionAcceptingAnythingBut(final GenerationState given){
		return new NotNullCondition(){

			@Override
			protected boolean acceptNotNull(GenerationState state) {
				return given != state;
			}
			
		};
	}
}
