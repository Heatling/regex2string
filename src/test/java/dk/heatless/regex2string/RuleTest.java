package dk.heatless.regex2string;

import org.testng.annotations.*;
import static org.mockito.Mockito.*;

import static org.testng.Assert.*;

public class RuleTest {
	Condition mockPre, mockPost;
	SubstringGenerator mockGen;
	
	Rule r;
	
	@BeforeMethod
	public void setupTest(){
		mockPre = mock(Condition.class);
		mockPost = mock(Condition.class);
		mockGen = mock(SubstringGenerator.class);
		
		
		r = new Rule(mockPre, mockPost, mockGen);
	}
	
	@Test
	public void testGetters(){
		assertTrue(r.getPrecondition() == mockPre);
		assertTrue(r.getPostcondition() == mockPost);
		assertTrue(r.getGenerator() == mockGen);
	}
	
}
