package uk.kumar.rajesh.mvc.integration.testing;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;


/**
 * A Spring {@link ContextLoader} that establishes a mock Servlet environment and {@link WebApplicationContext}
 * so that Spring MVC stacks can be tested from within JUnit.
 */
public class MockWebApplicationContextLoader extends AbstractContextLoader {
		
		private MockWebApplication configuration;
	
        private final static GenericWebApplicationContext webApplicationContext = new GenericWebApplicationContext();
        
        @Override
        public final ConfigurableApplicationContext loadContext(String... locations) throws Exception {
                // Establish the servlet context and config based on the test class's MockWebApplication annotation.
                final MockServletContext servletContext = new MockServletContext(configuration.webapp(), new FileSystemResourceLoader());
                servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webApplicationContext);
                
                webApplicationContext.setServletContext(servletContext);
                createBeanDefinitionReader(webApplicationContext).loadBeanDefinitions(locations);
                AnnotationConfigUtils.registerAnnotationConfigProcessors(webApplicationContext);
                
                // Prepare the context.
                webApplicationContext.refresh();
                webApplicationContext.registerShutdownHook();
                
                return webApplicationContext;
        }
        
        protected BeanDefinitionReader createBeanDefinitionReader(final GenericApplicationContext context) {
            return new XmlBeanDefinitionReader(context);
        }

        public static WebApplicationContext getInstance() {
            return webApplicationContext;
        }

        @Override
        protected String getResourceSuffix() {
                return "-context.xml";
        }
        
        /**
         * One of these two methods will get called before {@link #loadContext(String...)}.
         * We just use this chance to extract the configuration.
         */
        @Override
        protected String[] generateDefaultLocations(Class<?> clazz) {
                extractConfiguration(clazz);            
                return super.generateDefaultLocations(clazz);
        }
        
        /**
         * One of these two methods will get called before {@link #loadContext(String...)}.
         * We just use this chance to extract the configuration.
         */
        @Override
        protected String[] modifyLocations(Class<?> clazz, String... locations) {
                extractConfiguration(clazz);
                return super.modifyLocations(clazz, locations);
        }
        
        private void extractConfiguration(Class<?> clazz) {
                configuration = AnnotationUtils.findAnnotation(clazz, MockWebApplication.class);
                if (configuration == null)
                        throw new IllegalArgumentException("Test class " + clazz.getName() + " must be annotated @MockWebApplication.");
        }
}