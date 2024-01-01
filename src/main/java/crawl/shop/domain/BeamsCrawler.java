package crawl.shop.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
	public PageCrawling crawl(final String url, final int page, final int offset) throws Exception {
		final PageCrawling pageCrawling = new PageCrawling();
		try {
			final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
			final Document document = Jsoup.connect(url)
										   .userAgent(userAgent)
										   .referrer("http://www.google.com")
										   .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
										   .header("Accept-Encoding", "gzip, deflate, br")
										   .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
										   .header("Cache-Control", "max-age=0")
										   // .header("Sec-Ch-Ua", "'Not_A Brand';v='8', 'Chromium';v='120', 'Google Chrome';v='120'")
										   .header("Sec-Ch-Ua-Mobile", "?0")
										   // .header("Sec-Ch-Ua-Platform", "'macOS'")
										   .header("Sec-Fetch-Dest", "document")
										   .header("Sec-Fetch-Mode", "navigate")
										   .header("Sec-Fetch-Site", "none")
										   .header("Sec-Fetch-User", "?1")
										   .header("Upgrade-Insecure-Requests", "1")
										   .timeout(1000 * 30)
										   .get();
			final Elements elements = document.getElementsByClass("item-detail-main")
											  .select("ul")
											  .select("li[class='item-image']");
			final int limit = Math.min(elements.size(), (offset + 1) * page);
			for (int i = offset * page; i < limit; i++) {
				final String image = elements.get(i)
											 .select("img")
											 .attr("data-original");
				pageCrawling.addImage(toBase64(ProviderCode.BEAMS, "http:" + image));
				pageCrawling.setEnd(i == (elements.size() - 1));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Beams crawling is failed");
		}

		return pageCrawling;
	}

}
