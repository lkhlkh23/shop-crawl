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
			final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
			long start2 = System.currentTimeMillis();
			final Document document = Jsoup.connect(url)
										   .userAgent(userAgent)
										   .referrer("http://www.google.com")
										   .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
										   .header("Accept-Encoding", "gzip, deflate, br")
										   .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
										   .header("Cache-Control", "max-age=0")
										   /*.header("Zalando-Client-Id", "c85ea97b-60b4-46c4-b458-18c6f8a0cc45")
										   .header("language-preference", "en")
										   .header("_gcl_au", "1.1.931594343.1703680788")
										   .header("_ga", "GA1.2.1839771124.1703680788")
										   .header("_fbp", "fb.1.1703680831596.1195907018")
										   .header("ncx", "f")
										   .header("_gid", "GA1.2.91189159.1703922557")
										   .header("frsx", "AAAAADFJ_Wb8fp9hC1hVuZ_CIFc5GpCfMoLHOCbk8Yg6KEEvZ-jmF68f-FycutZd72yZxP1y_M3Xrzt7yytC5r6QjHcmbzyr5oOWcQL07ak2EQzfevD-Ctyle3yV5KRd--Xpp3o2doUKZziw-xZLgb4=")
										   .header("mpulseinject", "false")
										   .header("bm_sz", "64F734AC8DC5F8762ABAC923858EFC0D~YAAQt8YTAulK7m2MAQAAnir7vxZEn81zS+ahWMLSS/rU/FwYKCkFRc9W9XsQWYpWjquwqd5pHqEtPBdDofnFCtCPlNH/cDsfrShgWG9ggX/DE0tsKmokcWKTwoZbhfuWmb6tBYIz9gHWNJ/LRfj3RsnykCP+J2BzANY3dF95QR2M3NWyA3aX0utXPbVjlCGV9lX4rxLhzzAcsucRPWgJvrw1YWJXV0mXkjm2mrh1zRQWjp+SSPhciGK+F6b9dY7QzUq8i4QCsSdEtnHXLZk6VAx8QShLIe8R4lLht/YwDURad7QuTnZBF1kFe2UEXMMaCLAzNOF3WHPpzxTT87ywHQ5tXf6Miqx1XsQGhFpbeHYSlfGonzDhp2LFdaVQbEQ6LJ34PtT+91WKAgI2ijQimxunQaaN8trftDF6YIV6FjfMA8DAHFD+1miq63jN~3421510~4343345")
										   .header("ak_bmsc", "A031D5C80E442B274A05CFB325717F71~000000000000000000000000000000~YAAQt8YTAixL7m2MAQAA2jL7vxYeMCLLnRuKjoaxhQVD5YWLOxQqmM0GID/UKTFj2MyDs3FpCm3qFKW80sBio4xFx038BCFsnWPk5EE9+mzpDzHPpo6ekx4+4nx3eXnTUtbv/s+r1yvRWzu75M02NqKeGVhv/CtNy0veOKaleasBU8PRDfXJP9/fnQMIaXMzmgCD3lWly5+TbC4Wy4ktiHPA9QvSy1n1OtqNWlYKQDCts69Hm1oqTxpqv8exCEGEkBIcquTehlhLlPpyszER+CbZRtY1lb9+HkwiyaUASVetCn4E8GGLxzUNuRMfHOOKtX3eQlGWzr0stCkFrlxh0ORgIZZ4MtcYnGr7MMXAuAdheflTHD6OuSse2ZoAX25ikw+I/Lul9Vw6z0UYy+o4JyzYy7x94ONk9h+V8m7ES64XRt0W6Q9BSQFfMjpn+0kuqskBCpc7zaX6BCVBm6mj99dOc+DaVeBGt+HZUghDS0kS8IJ5MwnM3mo4qZUIT0215tg=")
										   .header("bm_sv", "343563680658FFB3A7B16AC8DF89CB5E~YAAQt8YTAl5n7m2MAQAASwz+vxaZW5X+Gg8R+u/DgFIskt0j67SB6KQytf4KKkB0rEL6UVyHUk8kGrsF5nWS3us9ACgsBXCn3JjDZIO5Y9JjEeVYbuFF1bNRJYumKnVjGAio1xgASItgbwDx7Bs1SLXZ3P6L0PYLOzER4U8en2qTUk0aXB1odSgZWsplajOf3za9pTm7aPZWpsRkTmAGCzhDpTOiGxn9mYzG6wjGhQp4qgwnhbviJkVwapC0YLCU~1")
										   .header("_abck", "23D77A4E63102E12139D8005A01D2F27~-1~YAAQt8YTAqhp7m2MAQAAC0n+vwst7hJqsRKL6iYQEWMWgDo19AKMf60eo/2+hqNsJKsdBR58pXOU1P1qaHoK92n8CpXRFHJX1yY7XylxVD8jbsbNWGzjOU0oMqTGBPcNR7tMXODdwxpoTVAV9fvlzDe6ZE5B2DoR0yYExjg9pRrB7WDHJ4JlClAegGIzguK33EpftQXuw287jn+AY8ni0HvtRlH5m3MjKdy0nJsiYV1Ou6gNmdHz3BuCJtaLwkls9VJo8Iw5sKuEahAGB1yjBMaFfAE2cp3s7Q2BKLCLGP7vnYgSIG0N5gUK84miYB127jal5AjwlaOGVY5nuvwyU2hu56be8PzQiduYF740d5edk7GcVlrME/ml/AkspCCgBBnjSU+7Sa5AWOTC4rFLARv02wB7xz/QfAeNGhpyh74j4+q20GN/qh0BZJv2ADa0KU620uyVa7VFDOVzzpvwqlNc2IWj+qU/rK/9Hnb3zDWwn8crMWw4EYWKe50s/kBQpnUj9fNXCrBmaQ==~-1~-1~-1")
										   */.timeout(0)
										   .get();
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
