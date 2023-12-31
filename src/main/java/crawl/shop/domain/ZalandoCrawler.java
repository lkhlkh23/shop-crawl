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
public class ZalandoCrawler extends BaseCrawler {

	private static final ZalandoCrawler INSTANCE = new ZalandoCrawler();

	public static ZalandoCrawler getInstance() {
		return INSTANCE;
	}

	@Override
	public PageCrawling crawl(final String url, final int page, final int offset) throws Exception {
		final PageCrawling pageCrawling = new PageCrawling();
		try {
			long start2 = System.currentTimeMillis();
			final Document document = Jsoup.connect(url).timeout(30 * 1000).get();
			long end2 = System.currentTimeMillis();
			System.out.println("get connection : " + (end2 - start2));
			final String selector = "ul[class='XLgdq7 _0xLoFW JgpeIw r9BRio be4rWJ xlsKrm _4oK5GO heWLCX _MmCDa']";
			final Elements elements = document.select(selector)
											  .select("li");
			final int limit = Math.min(elements.size(), (offset + 1) * page);
			for (int i = offset * page; i < limit; i++) {
				final String image = elements.get(i)
											 .select("img")
											 .attr("src");
				long start3 = System.currentTimeMillis();
				pageCrawling.addImage(toBase64(ProviderCode.ZALANDO, image));
				long end3 = System.currentTimeMillis();
				System.out.println("base64 : " + (end3 - start3));
				pageCrawling.setEnd(i == (limit - 1));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Zalando crawling is failed");
		}

		return pageCrawling;
	}

}
