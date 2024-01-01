package crawl.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crawl.shop.controller.dto.CrawlingRequest;
import crawl.shop.controller.dto.CrawlingResponse;
import crawl.shop.controller.dto.Response;
import crawl.shop.domain.PageCrawling;
import crawl.shop.type.ProviderCode;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/crawl")
public class CrawlingController {

	@PostMapping("/download/images")
	public CrawlingResponse downloadImages(@RequestBody final CrawlingRequest request) {
		try {
			final PageCrawling pages = ProviderCode.getCrawler(request.getBrand()).crawl(request.getUrl(), request.getPage(), request.getOffset());
			return new CrawlingResponse(pages.getImages(), pages.isEnd());
		} catch (Exception e) {
			log.error("Crawling is failed", e);
			return new CrawlingResponse(List.of(), true);
		}
	}

}
