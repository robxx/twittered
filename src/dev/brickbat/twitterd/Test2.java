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
import com.github.redouane59.twitter.dto.tweet.TweetV1;
import com.github.redouane59.twitter.dto.user.User;
import com.github.redouane59.twitter.dto.user.UserListV2;
import com.github.redouane59.twitter.helpers.RequestHelperV2;
import com.github.redouane59.twitter.helpers.URLHelper;
import com.github.redouane59.twitter.signature.TwitterCredentials;

import lombok.extern.slf4j.*;

@Slf4j
public class Test2 {	

	public static void main(String[] args) {
		try {
			TwitterClient twitterClient = new TwitterClient(
					TwitterClient.OBJECT_MAPPER.readValue(new File("twitter-credentials.json"), TwitterCredentials.class));

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
			//twitterClient.postTweet("test2");
			/*
			 * RateLimitStatus r = twitterClient.getRateLimitStatus(); Map<String, JsonNode>
			 * m = r.getResources(); for(Entry<String, JsonNode> entry : m.entrySet()) {
			 * System.out.println(entry.getKey() + " : " + entry.getValue().toString()); }
			 */
			List<TweetV1> twerts = twitterClient.getHomeTimeline(200); 
			for(Tweet t : twerts) { System.out.println(t.getText()); }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
