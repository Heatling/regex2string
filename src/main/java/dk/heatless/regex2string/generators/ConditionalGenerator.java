package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

public class ConditionalGenerator implements Generator{
	
//Fields
	private Condition condition;
	private Generator a, b;
	
//Constructors
	public ConditionalGenerator(Condition condition, Generator a, Generator b ){
		if(condition == null){
			throw new IllegalArgumentException("Condition was null");
		}
		if(a == null){
			throw new IllegalArgumentException("Generator 'a' was null");
		}
		if(b == null){
			throw new IllegalArgumentException("Generator 'b' was null");
		}
		this.condition = condition;
		this.a = a;
		this.b = b;
	}
	
//Methods
	@Override
	public String generate(GenerationState state){
		return (condition.accept(state))? a.generate(state): b.generate(state);
	}

}
