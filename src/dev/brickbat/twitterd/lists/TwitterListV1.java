package dev.brickbat.twitterd.lists;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TwitterListV1 implements TwitterList {

	@JsonProperty("id_str")
	private String id;
	@JsonProperty("slug")
	private String slug;
	@JsonProperty("uri")
	private String url;

	/*@Override
	public String getId() {

		return this.id;
	}

	@Override
	public String getSlug() {

		return this.slug;
	}

	@Override
	public String getUrl() {

		return this.url;
	}*/

}
