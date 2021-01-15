package dev.brickbat.twitterd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.redouane59.RelationType;
import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.dto.user.User;
import com.github.redouane59.twitter.dto.user.UserListV2;
import com.github.redouane59.twitter.helpers.RequestHelperV2;
import com.github.redouane59.twitter.helpers.URLHelper;
import com.github.redouane59.twitter.signature.TwitterCredentials;

import lombok.extern.slf4j.*;

@Slf4j
public class Test2 extends TwitterClient {
	private URLHelper urlHelper = new URLHelper();
	private RequestHelperV2 requestHelperV2;
	private static final String PAGINATION_TOKEN = "pagination_token";

	public Test2(TwitterCredentials readValue) {
		super(readValue);
		requestHelperV2 = super.getRequestHelperV2();
	}

	public static void main(String[] args) {
		try {
			Test2 twitterClient = new Test2(
					OBJECT_MAPPER.readValue(new File("twitter-credentials.json"), TwitterCredentials.class));

			User u = twitterClient.getUserFromUserName("nextlevelpope");
			System.out.println(u.getDisplayedName());

			/*
			 * List<User> lu = twitterClient.getFollowers(id);
			 * 
			 * System.out.println(lu.size());
			 * 
			 * List<User> lf = twitterClient.getFollowing(u.getId()); for (User uu : lf) {
			 * System.out.println(uu.getName()); } System.out.println(lf.size());
			 */

			/*
			 * List<Tweet> twerts = twitterClient.getUserTimeline(u.getId(), 200); for(Tweet
			 * t : twerts) { log.info(t.getText()); }
			 */
			twitterClient.postTweet("test2");
			/*
			 * RateLimitStatus r = twitterClient.getRateLimitStatus(); Map<String, JsonNode>
			 * m = r.getResources(); for(Entry<String, JsonNode> entry : m.entrySet()) {
			 * System.out.println(entry.getKey() + " : " + entry.getValue().toString()); }
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*@Override
	public Tweet postTweet(String text) {
		String url = this.getUrlHelper().getPostTweetUrl();
		Map<String, String> parameters = new HashMap<>();
		parameters.put("status", text);

		return this.getRequestHelper().postRequest(url, parameters, TweetV1.class)
				.orElseThrow(NoSuchElementException::new);
	}*/

	@Override
	public List<User> getFollowers(String userId) {
		return this.getUsersInfoByRelation(userId, RelationType.FOLLOWER);
	}

	@Override
	public List<User> getFollowing(String userId) {
		return this.getUsersInfoByRelation(userId, RelationType.FOLLOWING);
	}

	private List<User> getUsersInfoByRelation(String userId, RelationType relationType) {
		String url = null;
		if (relationType == RelationType.FOLLOWER) {
			url = this.urlHelper.getFollowersUrl(userId);
		} else if (relationType == RelationType.FOLLOWING) {
			url = this.urlHelper.getFollowingUrl(userId);
		}
		return this.getUsersInfoByRelation(url);
	}

	List<User> getUsersInfoByRelation(String url) {
		String token = null;
		List<User> result = new ArrayList<>();
		do {
			String urlWithCursor = url;
			if (token != null) {
				urlWithCursor = urlWithCursor + "&" + PAGINATION_TOKEN + "=" + token;
			}

			Map<String, String> parameters = new HashMap<>();
			parameters.put("max_results", "1000");
			Optional<UserListV2> userListDTO = this.requestHelperV2.getRequestWithParameters(urlWithCursor, parameters,
					UserListV2.class);

			if (userListDTO.isEmpty()) {
				break;
			}
			result.addAll(userListDTO.get().getData());
			token = userListDTO.get().getMeta().getNextToken();
		} while (token != null);
		return result;
	}
	
	List<Tweet> getHomeTimeline() {
		List<Tweet> result = new ArrayList<>();
		
		return result;
	}
}
