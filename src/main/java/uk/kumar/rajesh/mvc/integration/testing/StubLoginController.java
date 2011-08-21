package uk.kumar.rajesh.mvc.integration.testing;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StubLoginController extends AbstractRestController {
	
	// REST CRUD URLs for TestDataSet CRUD activities:
	private final String LOGIN_URL = "/login";
		
	
	@RequestMapping(value= LOGIN_URL,method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView getValidUsersList(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) {
		
		List<String> loginList =  new ArrayList<String>();
		loginList.add("Rajesh");
		ModelAndView modelAndView = new ModelAndView("userLogin");		
		modelAndView.addObject("userLoginList", loginList);		
		return modelAndView;
	}	
	


}
