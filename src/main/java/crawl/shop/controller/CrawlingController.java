package crawl.shop.controller;

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
	public Response downloadImages(@RequestBody final CrawlingRequest request) {
		log.info("request : {}", request.toString());
		try {
			ProviderCode.getCrawler(request.getBrand()).crawl(request.getUrl());
			return Response.ok("다운로드가 모두 성공했습니다!");
		} catch (Exception e) {
			log.error("Crawling is failed", e);
			return Response.fail("다운로드가 실패했습니다!");
		}
	}

}
