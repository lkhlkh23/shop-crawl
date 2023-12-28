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
	public List<String> crawl(final String url) throws Exception {
		final List<String> images = new ArrayList<>();
		final Connection conn = Jsoup.connect(url);
		try {
			final Document document = conn.get();
			final String selector = "ul[class='XLgdq7 _0xLoFW JgpeIw r9BRio be4rWJ xlsKrm _4oK5GO heWLCX _MmCDa']";
			final Elements elements = document.select(selector)
											  .select("li");
			for (final Element element : elements) {
				final String image = element.select("img")
											.attr("src");
				images.add(toBase64(ProviderCode.ZALANDO, image));
			}
		} catch (IOException e) {
			throw new Exception("Zalando crawling is failed");
		}

		return images;
	}

}
