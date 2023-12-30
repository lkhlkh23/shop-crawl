package crawl.shop.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCrawling {

	private List<String> images = new ArrayList<>();
	private boolean isEnd;

	public void addImage(final String image) {
		this.images.add(image);
	}

}
