package crawl.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crawl.shop.controller.dto.CrawlingRequest;
import crawl.shop.controller.dto.Response;
import crawl.shop.type.ProviderCode;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/crawl")
public class CrawlingController {

	@PostMapping("/download/images")
	public Response<List<String>> downloadImages(@RequestBody final CrawlingRequest request) {
		log.info("request : {}", request.toString());
		try {
			final List<String> images = ProviderCode.getCrawler(request.getBrand()).crawl(request.getUrl());
			return new Response<>(images, 200);
		} catch (Exception e) {
			log.error("Crawling is failed", e);
			return new Response<>(List.of(), 500);
		}
	}

}
