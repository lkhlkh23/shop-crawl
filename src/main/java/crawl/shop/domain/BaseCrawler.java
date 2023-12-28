package crawl.shop.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public abstract class BaseCrawler {

	public abstract void crawl(final String url) throws Exception;

	protected void download(final String image) {
		try (final InputStream in = new URL(image).openStream()) {
			final String directory = System.getProperty("user.home");
			Files.copy(in, Paths.get(String.format("%s/Desktop/image-%s.jpg", directory, LocalDateTime.now())));
		} catch (Exception e) {

		}
	}

}
