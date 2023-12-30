package crawl.shop.domain;

import java.awt.*;
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

	protected String toBase64(final ProviderCode brand, final String url, final double percentage) throws Exception {
		final BufferedImage image = ImageIO.read(new URL(url));
		final int width = (int) (image.getWidth() * percentage);
		final int height = (int) (image.getHeight() * percentage);

		final BufferedImage resized = new BufferedImage(width, height, image.getType());
		final Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();

		final File completed = new File(brand.getCode() + LocalDateTime.now().toString());
		ImageIO.write(resized, "png", completed);
		final String encoded = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(completed));
		completed.delete();

		return encoded;
	}

}
