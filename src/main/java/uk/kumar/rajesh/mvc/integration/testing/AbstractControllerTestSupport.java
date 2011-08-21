package uk.kumar.rajesh.mvc.integration.testing;

import javax.servlet.ServletException;

import junit.framework.Assert;

import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public abstract class AbstractControllerTestSupport {

	private static DispatcherServlet dispatcherServlet;

	@SuppressWarnings("serial")
	public static DispatcherServlet getServletInstance() {
		if (null == dispatcherServlet) {
			dispatcherServlet = new DispatcherServlet() {
				protected WebApplicationContext createWebApplicationContext(
						WebApplicationContext parent) {
					GenericWebApplicationContext wac = new GenericWebApplicationContext();
                    wac.setParent(MockWebApplicationContextLoader.getInstance());
                    wac.registerBeanDefinition("viewResolver", new RootBeanDefinition(JSPViewResolver.class));
                    wac.refresh();
                    return wac;

				}
			};
			try {
				dispatcherServlet.init(new MockServletConfig());
			} catch (ServletException se) {
				Assert.fail("Unable to create a dispatcher servlet: " + se.getMessage());
			}
		}
		return dispatcherServlet;
	}
}
