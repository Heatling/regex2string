package feature;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.heatless.regex2string.Conditions;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generators;
import dk.heatless.regex2string.generators.MinimalDigitGenerator;
import dk.heatless.regex2string.generators.RepeatGenerator;
import dk.heatless.regex2string.generators.RuleSetGenerator;
import dk.heatless.regex2string.generators.StaticStringGenerator;
import dk.heatless.regex2string.rules.CompleteRegexMatchRule;
import dk.heatless.regex2string.rules.PostfixRule;
import dk.heatless.regex2string.rules.PrefixRule;
import dk.heatless.regex2string.rules.PriorityGenerator;
import dk.heatless.regex2string.rules.Rule;
import dk.heatless.regex2string.utilities.StateOperations;

public class Features {
	
	@Test
	public void t1(){
		Rule g = new CompleteRegexMatchRule(Generators.MINIMAL_STRING_GENERATOR);
		
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(1|2|3|0)(3)generator"));
		
		assertEquals(g.generate(gen), "03generator");
	}
	
	@Test
	public void t2(){
		RuleSetGenerator rules = new RuleSetGenerator();
		rules.add(new PrefixRule(new StaticStringGenerator("{")));
		rules.add(new PostfixRule(new StaticStringGenerator("}")));
		rules.add(new Rule(Conditions.NO_CONDITION, Conditions.NO_CONDITION, new StaticStringGenerator("something")));
		
		Rule g = new CompleteRegexMatchRule(new RepeatGenerator(rules));
		
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(\\[|\\{)something(\\]|\\})"));
		
		assertEquals(g.generate(gen), "{something}");
	}

	@Test
	public void t3(){
		PriorityGenerator pG = new PriorityGenerator(Conditions.NO_CONDITION, new StaticStringGenerator("-"), Generators.MINIMAL_STRING_GENERATOR);
		RepeatGenerator rG = new RepeatGenerator(pG);
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(\\+|-) 1234 (\\+|-)"));
		
		assertEquals(rG.generate(gen), "- 1234 +");
	}
	
	@Test
	public void t4(){
		RuleSetGenerator rules = new RuleSetGenerator();
		rules.add(new PrefixRule(new StaticStringGenerator("-")));
		rules.add(new PostfixRule(new StaticStringGenerator("23")));
		PriorityGenerator pG = new PriorityGenerator(Conditions.NO_CONDITION, rules, new MinimalDigitGenerator());
		RepeatGenerator rG = new RepeatGenerator(pG);
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(\\+|-)[0-9]{10}"));
		
		assertEquals(rG.generate(gen), "-0000000023");
	}
}
