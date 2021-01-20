package dev.brickbat.twitterd;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.user.User;
import com.github.redouane59.twitter.signature.TwitterCredentials;

import dev.brickbat.twitterd.lists.TwitterList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test2 {

	/*
	 * args[0] = screen_name
	 * 
	 * add the accounts User u follows to a private list screen_name-test
	 * impractical because of create, create_all rate limiting voodoo
	 * 
	 */
	public static void main(String[] args) {
		try {
			TwitterClient tc = new TwitterClient(TwitterClient.OBJECT_MAPPER
					.readValue(new File("twitter-credentials.json"), TwitterCredentials.class));

			User u = tc.getUserFromUserName(args[0]);

			System.out.println("protected: " + u.isProtectedAccount());
			System.out.println("following " + u.getFollowingCount() + " accounts");

			// get ratelimit status
			// -> can't

			List<User> lu = tc.getFollowing(u.getId()); // sort?
			System.out.println(lu.size() + " following got");

			// create private list			
			TwitterList tl = tc.createTwitterList(args[0] + "_" + "test", "private", null);

			
			int i = 0;
			int c = 100; // # ids to create_all
			while (i < lu.size() && i < 5000) { // max 5000 to a list
				List<User> sl = i > lu.size() - c ? lu.subList(i, i + (lu.size() % c)) : lu.subList(i, i + c);
				String ids = "";
				for(User su : sl) {
					if (su.isProtectedAccount()) {
						LOGGER.info(su.getName() + " is protected");
						//continue;
					} else {
						ids += su.getId() + ",";
						//tc.createTwitterListMember(o.getId(), uu.getId());
					}				
				} 
				//o.createAll
				tc.createAllTwitterListMembers(tl.getId(), ids);					
				LOGGER.info(i + "-" + sl.size() + " " + ids);				
				i += c;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
