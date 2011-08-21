package uk.kumar.rajesh.mvc.integration.testing;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.kumar.rajesh.mvc.integration.testing.AbstractControllerTestSupport;
import uk.kumar.rajesh.mvc.integration.testing.MockWebApplication;
import uk.kumar.rajesh.mvc.integration.testing.MockWebApplicationContextLoader;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-servlet.xml", loader=MockWebApplicationContextLoader.class)
@MockWebApplication(name="StubLogin-controller", webapp="/webapp")
public class StubLoginControllerTest extends AbstractControllerTestSupport{

	/**
	 * Tests that GET /view?id=0 returns the correct view.
	 * This shows an example of testing the view.
	 */
	@Test
	public void viewTest() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/login");
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		getServletInstance().service(request, response);
		String results = response.getContentAsString().trim();
		
		assertTrue(results.contains("userLoginList"));
	}
	
}
