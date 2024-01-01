package crawl.shop.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;

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
										   .method(Connection.Method.HEAD)
										   .ignoreContentType(true)
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
										   .cookies(getCookies())
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

	private Map<String, String> getCookies() {
		final Map<String, String> cookies = new HashMap<>();
		cookies.put("_evga_0c2d", "{%22uuid%22:%22db18318c9b2a562f%22}");
		cookies.put("_sfid_7b04", "{%22anonymousId%22:%22db18318c9b2a562f%22%2C%22consents%22:[]}");
		cookies.put("__lt__cid", "1518b729-2427-47f3-b5cf-5bc745d96bea");
		cookies.put("_fbp", "fb.2.1696142068101.2024728496");
		cookies.put("FPID", "FPID2.3.rsau%2BRI8uu9ACK2v7r9qDcNCMMeMI6YL5QnM2e6Z0Tk%3D.1696142068");
		cookies.put("_yjsu_yjad", "1696142068.d848a78b-5de8-4aa0-b34a-2737e70b2151");
		cookies.put("rpr_opted_in", "1");
		cookies.put("rpr_uid", "92c2e780-6024-11ee-b38c-8765afd1cf06");
		cookies.put("rpr_is_first_session", "{%2292c2e780-6024-11ee-b38c-8765afd1cf06%22:0}");
		cookies.put("ch-veil-id", "38f40ad2-0542-4a2c-b329-5e5c003a7631");
		cookies.put("virtusize.bid", "6SCMSQWlbbLmvbqkmQmQUpw.ln739wdc");
		cookies.put("snexid", "bf1bec79-8d49-49c5-ab14-8eba764aa411");
		cookies.put("__rtbh.lid", "%7B%22eventType%22%3A%22lid%22%2C%22id%22%3A%225KqSTj2kqnPw0rFDjBQy%22%7D");
		cookies.put("_pin_unauth", "dWlkPU56QmxaREl4WWpjdFkyTm1aUzAwTjJGa0xXSm1NV0V0TnpFNFptUTFPRGRpTURRMQ");
		cookies.put("_cs_c", "0");
		cookies.put("WAPID", "H1vHQ4NtruMxkyxG2zSHr2PGSPzyGVCxwHh");
		cookies.put("wap_last_event", "showWidgetPage");
		cookies.put("_gcl_au", "1.1.1545063991.1703928560");
		cookies.put("_gid", "GA1.3.1100647569.1703928561");
		cookies.put("FPAU", "1.1.1545063991.1703928560");
		cookies.put("wovn_selected_lang", "ja");
		cookies.put("timeline-s_dt", "2023/12/31 00:15:00");
		cookies.put("ASP.NET_SessionId", "lvooz45yciksjvucxtttvopf");
		cookies.put("LBSessionCookie", "rd400o00000000000000000000ffff0a830012o10443");
		cookies.put("beams_secure", "SecureKey=");
		cookies.put("FPLC", "vzeYeMKP2P8i%2F7xGo6aKSxeGrEueEFp6g50BjSFj4%2B9QzvFtE71bFal8FHpfy2M4yq%2B5%2FLWQ5biywnZ52NVTCT3Bvx63%2FvvPvYrc0QvN5vU2szfsU1Bfe9DeLpdKaw%3D%3D");
		cookies.put("beams", "Session=1772725818&Key=432ee2ed04d651d1439dda723f5583180610b5d97b3936675a6be13143a6194f");
		cookies.put("_abck", "0A434D5A5D150C30121DD5EFC22517D4~0~YAAQfHXTF+sNgbmMAQAAttKMxAtaJh8hEh4ki8rtQgM75NT+zpi2H/QpFMvt9+LOShMkSnUzCW3zlss/fmaquVgjWVzZzAmOqMZg9nvg6BO9AUHxzHEgcLZsiB3myqWXZJiF+LRNgad6RGpjIF2cm28FLmcve8WpBD3RMCPCi6fqah3ez/WvZxdhrsnKxDG7+T4Ul0yVmUDnx8wWtaNWZvXysMu4mfxCF/E+D/qP7sZzMbefrddoG4MaC3AnU33I5rzOWYowURBbE5yEoLB62e1jC+ZwCqg5m7gjaBSLSuO1o8poARwIL8ofCEiCtKnT3IfkWgQaGLLQkXHj/z3yR7IK1MQhkQSCzHGEjxTpm+VB8A4Z0Po0ITJ8luDuyEBGDzR27a6RyybDlk5Nad949BdTW4ZLKmnTFQ==~-1~-1~-1");
		cookies.put("bm_sz", "2EC6DB810CE009207584B31B769D3EFA~YAAQfHXTF+4NgbmMAQAAttKMxBafP9HaI0taAfFJabTo3lkY4hTo/6EXXfAOuU3b4AreL23NGeRCmq6OTnYwjPpS1uvajuQ3k/+edWo+T+NjeTWPyBcg/YtQ9sKF6jD4USxt3LrFCfOVaBfaVI5pKON0BMUXjrgz16DJUhBdZ2NilzFnFekm+vyWQZHlCw1J4zAmRzSCpVTaT4XLxTOO1nQORWLYSINdk2ssVQ1BdV2wsqQLuyx4rtr1wyD7+hPp6Dq+NHJHXHs4FC5tPh1awX7xOaHtKxsoCYR5qZiiHyde+E7+~3748674~4338744");
		cookies.put("ftr_blst_1h", "1704104613207");
		cookies.put("__lt__sid", "0ceaa87a-202f06d0");
		cookies.put("_ga_1E2L0V6NJZ", "GS1.1.1704104613.10.0.1704104613.0.0.0");
		cookies.put("_ga", "GA1.3.1547961416.1696142068");
		cookies.put("_gat_UA-63270542-5", "1");
		cookies.put("_gat_UA-63270542-7", "1");
		cookies.put("_gat_UA-63270542-8", "1");
		cookies.put("_gat_UA-63270542-9", "1");
		cookies.put("PageViewCount_gtm", "3");
		cookies.put("ss_tracking_session_id", "14682d9be1fd428fafc7bc9622d9304f");
		cookies.put("_td", "00372803-56a0-434e-b81e-a923992b65cd");
		cookies.put("GTM_PageViewCount_s", "3");
		cookies.put("ak_bmsc", "021173BE0A4316D3C2C6C5D05AF14934~000000000000000000000000000000~YAAQfHXTF9kOgbmMAQAAU9uMxBai5R/RSdRYepjyzIYJMwO4BMZNIn2nV+wRT9geE5kl6RahQ+NDGN+1dd3KQpAk/Lz/qZQGGkVNlAy7ci1r6GKkTXqnQvs/4qqq/Kscd+8gcWBx4Q9n6P+DdLAD8s5ZgRJFEQ5iRoOq8ljmxoUG1TVH1ReLagwIV3dCoX1XK/hLhS4jy2lu17qn65F1yXJlKcYZK30WYwllxMDo1TGJ87pB0+DKQpdmLHPSpkv1vqDNY3oc119cC7hXJQEzzDtVDJCWz7ZxrEg5ZaaYfj9RJo9lmSwoullTH5LtHTTc0k0bEf6g+Bn9KAk2dzgdIpfDVzMr8LtGjQ1gdDkEVOCtXoiWKTd39Mn8bBWKgmLSyWIreldXbGatIX9D6IZgGl47jbbeLwGXJhoyWNXr/7xue7aw0zjy3J3WzuU0KY/W3pzwJi7K5a5wBWguTclV9HfV0DhGMOPWFZ5CsD1KzJG/TqKft14=");
		cookies.put("_cs_id", "aa7181f7-272b-a3c2-e288-f2811c66acdc.1703684762.11.1704104614.1704104614.1.1737848762745");
		cookies.put("forterToken", "874bbf519866415f94942b40ed0a5bc7_1704104612957__UDF43-m4_14ck");
		cookies.put("cto_bundle", "wAJFHl9hS09uYjd2Q2k4MUltdWVNRjZHJTJCNUNXNmwwc0lyZGl1c0wwUjFTckJtUFd3R0taVGZYQzVyeUolMkJHa0I5WkEyelJwSmNCbTFGdktkdiUyQiUyQnYxaE9oRmFHc0ZMZ3B3UXVKRm16aFNITk1wNjdvZGtWQm0lMkZGTEJmRGZWVCUyQmNDdUVVY29mJTJGM1J1N0dXbHh6b1lmMzNBOGJWZlRHS0lZNngzTTVyVUwwcXJDVmttYjkyaVFiTzUxaUhKTlQxdVpZTXoyNmFtaUolMkZrUE1zNzRaYzIydklNSGJwWUY0SWp6WUY5VnFXOXl2bkpNYzE1QTdvZG5HN0tlTzRjJTJGRURBREFlb3Y1c0FMZXolMkZyVFhURXp6JTJCckl5Wk5uVFBVdGFQQ0lnczFnMUI1YXQzeEN3eGh3OFYlMkJUOVQ0SUd2d2ZTVnYyUnBWRA");
		cookies.put("ch-session-106926", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXMiLCJrZXkiOiIxMDY5MjYtNjUxOTEyZjgwNTFkNWI3NDJmNzUiLCJpYXQiOjE3MDQxMDQ2MTQsImV4cCI6MTcwNjY5NjYxNH0.Fwcgb0DBj44wzq1eGkHJgOj_OCQzEW4Lqk5decd3hQU");
		cookies.put("_cs_s", "1.5.0.1704106416219");
		cookies.put("ph_phc_tnQZyAK9BM0WkS5fdAZAxA1bNnyKUlWSACWzmxDfVrA_posthog", "%7B%22distinct_id%22%3A%2218ae9f2581d1aae-027d99ae13f9d5-18525634-157188-18ae9f2581f11c0%22%7D");

		return cookies;
	}

}
