package dev.brickbat.twitterd;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.others.RateLimitStatus;
import com.github.redouane59.twitter.signature.TwitterCredentials;

public class Rate {

	public static void main(String args[]) {
		try {
			TwitterClient tc = new TwitterClient(
					TwitterClient.OBJECT_MAPPER.readValue(new File("twitter-credentials.json"), TwitterCredentials.class));

			
			RateLimitStatus rls = tc.getRateLimitStatus();
			Map <String, JsonNode> res = rls.getResources();
			
			/*
			 * for(Entry<String, JsonNode> entry : res.entrySet()) {
			 * System.out.println(entry.getKey() + ", "); }
			 */
			JsonNode nn = res.get("lists").get("/lists/members");			
			System.out.println(nn);
			
			JsonNode n = res.get("lists");
			System.out.println(n);
			Iterator<String> x = n.fieldNames();
			
			 do { System.out.println(x.next());			 
			 } while(x.hasNext());						
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
