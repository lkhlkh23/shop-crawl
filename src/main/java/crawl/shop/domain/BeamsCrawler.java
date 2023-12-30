package crawl.shop.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.shop.type.ProviderCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeamsCrawler extends BaseCrawler {

	private static final BeamsCrawler INSTANCE = new BeamsCrawler();

	public static BaseCrawler getInstance() {
		return INSTANCE;
	}

	@Override
	public List<String> crawl(final String url) throws Exception {
		final List<String> images = new ArrayList<>();
		final Connection conn = Jsoup.connect(url);
		try {
			final Document document = conn.get();
			final Elements elements = document.getElementsByClass("item-detail-main")
											  .select("ul")
											  .select("li[class='item-image']");
			for (final Element element : elements) {
				final String image = element.select("img")
											.attr("data-zoom-image");
				images.add(toBase64(ProviderCode.BEAMS, "http:" + image, 0.58));
			}
		} catch (IOException e) {
			throw new Exception("Beams crawling is failed");
		}

		return images;
	}

}
