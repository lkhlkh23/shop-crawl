package crawl.shop.type;

import crawl.shop.domain.BaseCrawler;
import crawl.shop.domain.BeamsCrawler;
import crawl.shop.domain.CosCrawler;
import crawl.shop.domain.ZalandoCrawler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProviderCode {

	COS("cos", CosCrawler.getInstance()),
	BEAMS("beams", BeamsCrawler.getInstance()),
	ZALANDO("zalando", ZalandoCrawler.getInstance());

	private String code;
	private BaseCrawler crawler;

	public static BaseCrawler getCrawler(final String code) {
		for (final ProviderCode value : values()) {
			if(value.code.equals(code)) {
				return value.crawler;
			}
		}

		return null;
	}

}
