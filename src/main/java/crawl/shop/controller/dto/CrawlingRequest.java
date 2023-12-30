package crawl.shop.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CrawlingRequest {

	private String url;
	private String brand;
	private int page;
	private int offset;

}
