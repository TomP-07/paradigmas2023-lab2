package parser.feed;


/*
 * Esta clase implementa el parser de feed de tipo reddit (json)
 * pero no es necesario su implemntacion
 * */

import feed.Feed;
import parser.feed.FeedParser;

import java.io.StringReader;

public class RedditParser extends FeedParser {

    public Feed parseFeed(String rawReddit) {
        StringReader reader = new StringReader(rawReddit);
        return null;
    }

}
