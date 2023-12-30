package crawl.shop.domain;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import crawl.shop.type.ProviderCode;

public abstract class BaseCrawler {

	public abstract PageCrawling crawl(final String url, final int page, final int offset) throws Exception;

	protected String toResizedBase64(final ProviderCode brand, final String url, final double percentage) {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	protected String toBase64(final ProviderCode brand, final String url) {
		try {
			final BufferedImage image = ImageIO.read(new URL(url));
			final File file = new File(brand.getCode() + LocalDateTime.now().toString());
			ImageIO.write(image, "png", file);
			final String encoded = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
			file.delete();

			return encoded;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
