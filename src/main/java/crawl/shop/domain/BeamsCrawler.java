package crawl.shop.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeamsCrawler extends BaseCrawler {

	private static final BeamsCrawler INSTANCE = new BeamsCrawler();

	public static BaseCrawler getInstance() {
		return INSTANCE;
	}

	@Override
	public void crawl(final String url) throws Exception {
		final Connection conn = Jsoup.connect(url);
		try {
			final Document document = conn.get();
			final Elements elements = document.getElementsByClass("item-detail-main")
											  .select("ul")
											  .select("li");
			for (final Element element : elements) {
				final String image = element.select("img")
											.attr("data-zoom-image");
				download("http:" + image);
			}
		} catch (IOException e) {
			throw new Exception("Beams crawling is failed");
		}
	}

}
