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
public class CosCrawler extends BaseCrawler {

	private static final CosCrawler INSTANCE = new CosCrawler();

	public static CosCrawler getInstance() {
		return INSTANCE;
	}

	@Override
	public PageCrawling crawl(final String url, final int page, final int offset) throws Exception {
		final PageCrawling pageCrawling = new PageCrawling();
		final Connection conn = Jsoup.connect(url);
		try {
			final Document document = conn.get();
			final Elements elements = document.getElementById("mainImageList")
											  .select("li");
			final int limit = Math.min(elements.size(), (offset + 1) * page);
			for (int i = offset * page; i < limit; i++) {
				final String image = elements.get(i)
											 .select("img")
											 .attr("data-zoom-src");
				pageCrawling.addImage(toResizedBase64(ProviderCode.COS, "http:" + image, 0.58));
				pageCrawling.setEnd(i == (limit - 1));
			}
		} catch (IOException e) {
			throw new Exception("Cos crawling is failed");
		}

		return pageCrawling;
	}

}
