package crawl.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import crawl.shop.type.ProviderCode;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/home")
public class HomeController {

	@GetMapping("")
	public String viewHome(final Model model) {
		model.addAttribute("providers", ProviderCode.values());

		return "/main/home";
	}

}
