package parser.feed;

import feed.Feed;
import parser.GeneralParser;

public abstract class FeedParser extends GeneralParser {


    public abstract Feed parseFeed(String rawFeed);
}
