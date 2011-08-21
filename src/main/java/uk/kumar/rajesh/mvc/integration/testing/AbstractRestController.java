package uk.kumar.rajesh.mvc.integration.testing;


public abstract class AbstractRestController {

	String resourceName = getResourceNameFromClass();
	
	protected String getResourceNameFromClass() {
		String className = this.getClass().getSimpleName();
		String resourceName = className.replaceAll("Controller$", "");
		return resourceName;
	}
	
	
	
}
