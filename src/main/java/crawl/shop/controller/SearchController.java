package crawl.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import crawl.shop.type.ProviderCode;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/search")
@Slf4j
public class SearchController {

	@GetMapping("/cos")
	public String viewSearchCos(final Model model) {
		model.addAttribute("providers", ProviderCode.values());
		model.addAttribute("provider", ProviderCode.COS.getCode());

		return "search/cos";
	}

	@GetMapping("/beams")
	public String viewSearchBeams(final Model model) {
		model.addAttribute("providers", ProviderCode.values());
		model.addAttribute("provider", ProviderCode.BEAMS.getCode());

		return "search/beams";
	}

	@GetMapping("/zalando")
	public String viewSearchZalando(final Model model) {
		model.addAttribute("providers", ProviderCode.values());
		model.addAttribute("provider", ProviderCode.ZALANDO.getCode());

		return "search/zalando";
	}

}
