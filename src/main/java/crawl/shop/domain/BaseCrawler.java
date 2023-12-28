package crawl.shop.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import crawl.shop.type.ProviderCode;

public abstract class BaseCrawler {

	public abstract List<String> crawl(final String url) throws Exception;

	protected void download(final String image) {
		try (final InputStream in = new URL(image).openStream()) {
			final String directory = System.getProperty("user.home");
			Files.copy(in, Paths.get(String.format("%s/Desktop/image-%s.jpg", directory, LocalDateTime.now())));
		} catch (Exception e) {

		}
	}

	protected String toBase64(final ProviderCode brand, final String url) throws Exception {
		final BufferedImage image = ImageIO.read(new URL(url));
		final File file = new File(brand.getCode() + LocalDateTime.now().toString());
		ImageIO.write(image, "jpeg", file);

		return Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
	}

}
