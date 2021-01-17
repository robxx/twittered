package dev.brickbat.twitterd;

import java.io.File;
import java.io.IOException;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.signature.TwitterCredentials;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			final TwitterClient twitterClient = new TwitterClient(TwitterClient.OBJECT_MAPPER
					.readValue(new File("twitter-credentials.json"), TwitterCredentials.class));

			// System.out.println(twitterClient.postTweet("test"));

			/*String userName = "twitter";
			String id = "1063586091129802752";							
			User result = twitterClient.getUserFromUserId(id);
			System.out.println(result.getDescription());		
			result = twitterClient.getUserFromUserName(userName);
			System.out.println(result.getName());
			
			System.out.println(twitterClient.getBearerToken());
			
			String id = "1063586091129802752";							
			User u = twitterClient.getUserFromUserId(id);
			System.out.println(u.getDescription());	
			*/
			
			/*TwitterClient.TWITTER_CREDENTIALS.setAccessToken("");
		    TwitterClient.TWITTER_CREDENTIALS.setAccessTokenSecret("");
		    
		    RequestToken result = twitterClient.getOauth1Token("oob");
		    
		 // https://twitter.com/oauth/authenticate?oauth_token=

		    TwitterClient.TWITTER_CREDENTIALS.setAccessToken(result.getOauthToken());
		    TwitterClient.TWITTER_CREDENTIALS.setAccessTokenSecret(result.getOauthTokenSecret());
		    
		    byte[] b = System.in.readNBytes(7);
		    String pin = new String(b);
		    		    
		    RequestToken x = twitterClient.getOAuth1AccessToken(result, pin);	
		    
		    TwitterClient.TWITTER_CREDENTIALS.setAccessToken(x.getOauthToken());
		    TwitterClient.TWITTER_CREDENTIALS.setAccessTokenSecret(x.getOauthTokenSecret());*/
		    
			
			System.out.println(twitterClient.postTweet("test"));		    

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
