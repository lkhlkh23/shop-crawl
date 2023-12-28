package crawl.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import crawl.shop.type.ProviderCode;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/home")
	public String viewHome(final Model model) {
		log.info("view home");
		log.info("user directory : {}", System.getProperty("user.home"));
		model.addAttribute("providers", ProviderCode.values());

		return "main/home";
	}

}
