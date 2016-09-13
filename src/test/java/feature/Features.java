package feature;

import org.testng.annotations.*;

import static org.testng.Assert.*;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.Conditions;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.GeneratorWrapper;
import dk.heatless.regex2string.Generators;
import dk.heatless.regex2string.conditions.CanGenerateCondition;
import dk.heatless.regex2string.conditions.DynamicCondition;
import dk.heatless.regex2string.generators.ConditionalGenerator;
import dk.heatless.regex2string.generators.DynamicStringGenerator;
import dk.heatless.regex2string.generators.MinimalDigitGenerator;
import dk.heatless.regex2string.generators.PriorityGenerator;
import dk.heatless.regex2string.generators.RepeatGenerator;
import dk.heatless.regex2string.generators.SetGenerator;
import dk.heatless.regex2string.generators.SequentialGenerator;
import dk.heatless.regex2string.generators.StaticStringGenerator;
import dk.heatless.regex2string.generators.WhileGenerator;
import dk.heatless.regex2string.rules.CompleteRegexMatchRule;
import dk.heatless.regex2string.rules.PostfixRule;
import dk.heatless.regex2string.rules.PrefixRule;
import dk.heatless.regex2string.rules.Rule;
import dk.heatless.regex2string.special.OptionalGenerator;
import dk.heatless.regex2string.utilities.StateOperations;

public class Features {
	
	@Test
	public void t1(){
		Rule g = new CompleteRegexMatchRule(Generators.MINIMAL_STRING_GENERATOR);
		
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(1|2|3|0)(3)generator"));
		
		assertEquals(g.generate(gen).getGenerated(), "03generator");
	}
	
	@Test
	public void t2(){
		SetGenerator rules = new SetGenerator();
		rules.add(new PrefixRule(new StaticStringGenerator("{")));
		rules.add(new PostfixRule(new StaticStringGenerator("}")));
		rules.add(new Rule(Conditions.NO_CONDITION, Conditions.NO_CONDITION, new StaticStringGenerator("something")));
		
		Rule g = new CompleteRegexMatchRule(new RepeatGenerator(rules));
		
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(\\[|\\{)something(\\]|\\})"));
		
		assertEquals(g.generate(gen).getGenerated(), "{something}");
	}

	@Test
	public void t3(){
		PriorityGenerator pG = new PriorityGenerator(Conditions.NO_CONDITION, new StaticStringGenerator("-"), Generators.MINIMAL_STRING_GENERATOR);
		RepeatGenerator rG = new RepeatGenerator(pG);
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(\\+|-) 1234 (\\+|-)"));
		
		assertEquals(rG.generate(gen).getGenerated(), "- 1234 +");
	}
	
	@Test
	public void t4(){
		SetGenerator rules = new SetGenerator();
		rules.add(new PrefixRule(new StaticStringGenerator("-")));
		rules.add(new PostfixRule(new StaticStringGenerator("23")));
		PriorityGenerator pG = new PriorityGenerator(Conditions.NO_CONDITION, rules, new MinimalDigitGenerator());
		RepeatGenerator rG = new RepeatGenerator(pG);
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(\\+|-)[0-9]{10}"));
		
		assertEquals(rG.generate(gen).getGenerated(), "-0000000023");
	}
	
	@Test
	public void t5(){
		SetGenerator rules = new SetGenerator();
		rules.add(new PrefixRule(new StaticStringGenerator("-")));
		
		DynamicStringGenerator insert = new DynamicStringGenerator();
		rules.add(new PostfixRule(insert));
		
		PriorityGenerator pG = new PriorityGenerator(Conditions.NO_CONDITION, rules, new MinimalDigitGenerator());
		RepeatGenerator rG = new RepeatGenerator(pG);
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(\\+|-)[0-9]{10}"));
		
		insert.setToGenerate("23");
		assertEquals(rG.generate(gen).getGenerated(), "-0000000023");
	}
	
	@Test
	public void t6(){
		
		DynamicCondition<Integer> positiveIntCondition = new DynamicCondition<Integer>(){

			@Override
			protected boolean acceptDependency(GenerationState state) {
				return this.getDependency()>=0;
			}
			
			
		};
		ConditionalGenerator signOfIntGenerator = new ConditionalGenerator(positiveIntCondition, new StaticStringGenerator("+"), new StaticStringGenerator("-"));
		DynamicStringGenerator intGenerator = new DynamicStringGenerator();
		
		SequentialGenerator signedIntOfLengthGen = new SequentialGenerator(
				new Generator[]{signOfIntGenerator,
						new OptionalGenerator(new RepeatGenerator(new Rule(Conditions.NO_CONDITION, new CanGenerateCondition(intGenerator), new MinimalDigitGenerator()))) ,
						intGenerator });
		
		GenerationState gen = StateOperations.getInitialGenerationState(new RegExp("(\\+|-)[0-9]{10}"));
		GenerationState gen2 = StateOperations.getInitialGenerationState(new RegExp("(\\+|-)[0-9]{4}"));
		
		int toGenerate = 23;
		positiveIntCondition.setDependency(toGenerate);
		intGenerator.setToGenerate(""+((toGenerate<0)? -1*toGenerate:toGenerate));
		assertEquals(signedIntOfLengthGen.generate(gen).getGenerated(), "+0000000023");
		
		toGenerate = -23;
		positiveIntCondition.setDependency(toGenerate);
		intGenerator.setToGenerate(""+((toGenerate<0)? -1*toGenerate:toGenerate));
		assertEquals(signedIntOfLengthGen.generate(gen).getGenerated(), "-0000000023");
		
		toGenerate = -2763;
		positiveIntCondition.setDependency(toGenerate);
		intGenerator.setToGenerate(""+((toGenerate<0)? -1*toGenerate:toGenerate));
		assertEquals(signedIntOfLengthGen.generate(gen).getGenerated(), "-0000002763");
		
		toGenerate = -2763;
		positiveIntCondition.setDependency(toGenerate);
		intGenerator.setToGenerate(""+((toGenerate<0)? -1*toGenerate:toGenerate));
		assertEquals(signedIntOfLengthGen.generate(gen2).getGenerated(), "-2763");
	}
	
	@Test
	public void t7(){
		
		DynamicCondition<Integer> positiveIntCondition = new DynamicCondition<Integer>(){

			@Override
			protected boolean acceptDependency(GenerationState state) {
				return this.getDependency()>=0;
			}
			
			
		};
		ConditionalGenerator signOfIntGenerator = new ConditionalGenerator(positiveIntCondition, new StaticStringGenerator("+"), new StaticStringGenerator("-"));
		DynamicStringGenerator intGenerator = new DynamicStringGenerator();
		
		SequentialGenerator signedIntOfLengthGen = new SequentialGenerator(
				new Generator[]{signOfIntGenerator,
						new OptionalGenerator(new RepeatGenerator(new Rule(Conditions.NO_CONDITION, new CanGenerateCondition(intGenerator), new MinimalDigitGenerator()))) ,
						intGenerator });
		GeneratorWrapper gW = new GeneratorWrapper(signedIntOfLengthGen);
		
		
		String regex1 = "(\\+|-)[0-9]{10}";
		String regex2 = "(\\+|-)[0-9]{4}";
		
		int toGenerate = 23;
		positiveIntCondition.setDependency(toGenerate);
		intGenerator.setToGenerate(""+((toGenerate<0)? -1*toGenerate:toGenerate));
		assertEquals(gW.generateForRegex(regex1), "+0000000023");
		
		toGenerate = -23;
		positiveIntCondition.setDependency(toGenerate);
		intGenerator.setToGenerate(""+((toGenerate<0)? -1*toGenerate:toGenerate));
		assertEquals(gW.generateForRegex(regex1), "-0000000023");
		
		toGenerate = -2763;
		positiveIntCondition.setDependency(toGenerate);
		intGenerator.setToGenerate(""+((toGenerate<0)? -1*toGenerate:toGenerate));
		assertEquals(gW.generateForRegex(regex1), "-0000002763");
		
		toGenerate = -2763;
		positiveIntCondition.setDependency(toGenerate);
		intGenerator.setToGenerate(""+((toGenerate<0)? -1*toGenerate:toGenerate));
		assertEquals(gW.generateForRegex(regex2), "-2763");
	}
}
