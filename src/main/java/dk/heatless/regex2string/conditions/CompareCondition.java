package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;

/**
 * Provides standard constructor for conditions that compare the results
 * of two other conditions.<br>
 * Extending classes should implement the comparison logic in the {@link #acceptNotNull} method.
 */
public abstract class CompareCondition extends NotNullCondition {
	
//Fields
	/**
	 * First condition to compare
	 */
	protected final Condition c1;
	
	/**
	 * Second condition to compare
	 */
	protected final Condition c2;
//Constructors
	
	/**
	 * Constructs a new condition that that will compare the two given conditions.
	 * @param c1
	 * @param c2
	 */
	public CompareCondition(Condition c1, Condition c2){
		if(c1 == null){
			throw new IllegalArgumentException("First condition cannot be null");
		}
		if(c2 == null){
			throw new IllegalArgumentException("Second condition cannot be null");
		}
		
		this.c1 = c1;
		this.c2 = c2;
	}
}
