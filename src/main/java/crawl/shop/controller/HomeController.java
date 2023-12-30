package crawl.shop.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import crawl.shop.controller.dto.ProviderOption;
import crawl.shop.type.ProviderCode;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/home")
	public String viewHome(final Model model) {
		model.addAttribute("providers", ProviderCode.values());

		return "main/home";
	}

}
