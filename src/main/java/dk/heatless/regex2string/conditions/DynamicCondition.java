package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;

public abstract class DynamicCondition<E> implements Condition {
	
//Fields
	private E dependency = null;
	
//Methods
	
	protected abstract boolean acceptDependency(GenerationState state);
	
	@Override
	public final boolean accept(GenerationState state) {
		return (state == null)? false: acceptDependency(state);
	}

//Accessors
	public void setDependency(E dependency){
		this.dependency = dependency;
	}
	
	public E getDependency(){
		return this.dependency;
	}
}
