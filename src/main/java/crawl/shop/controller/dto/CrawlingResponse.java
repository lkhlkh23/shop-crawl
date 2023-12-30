package crawl.shop.controller.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrawlingResponse {

	private List<String> images;
	private String brand;
	private String name;
	private String price;

}
