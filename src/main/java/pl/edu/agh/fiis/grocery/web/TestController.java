package pl.edu.agh.fiis.grocery.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("msg", "TestMessage");
		
		return "test";
	}
}
