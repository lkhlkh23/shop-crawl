package crawl.shop.controller.dto;

import crawl.shop.type.ProviderCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderOption {

	private String code;
	private boolean selected;

	public static ProviderOption of(final ProviderCode provider, final String selectedProvider) {
		final ProviderOption option = new ProviderOption();
		option.setCode(provider.getCode());
		option.setSelected(provider.getCode().equals(selectedProvider));

		return option;
	}

}
