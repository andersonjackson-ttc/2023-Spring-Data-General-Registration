package com.registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/registration")
public class RegistrationController {
	
	
	private RegistrationService registrationService;
	
	

	
	
	@PostMapping
	@GetMapping
	public String register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("major") int id) {
		RegistrationRequest request = new RegistrationRequest(username, password, id);
		registrationService.register(request);
		
		
		return "success";
}
