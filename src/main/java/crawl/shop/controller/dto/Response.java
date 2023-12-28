package crawl.shop.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {

	private String data;
	private int status;

	public static Response ok(final String data) {
		final Response response = new Response();
		response.data = data;
		response.status = 200;

		return response;
	}

	public static Response fail(final String data) {
		final Response response = new Response();
		response.data = data;
		response.status = 500;

		return response;
	}

}
