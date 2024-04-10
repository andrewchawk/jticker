package garbagetld.ach.jticker;

import java.util.function.Function;
import java.util.LinkedList;
import java.net.URL;

import garbagetld.ach.jticker.TickerSource;
import garbagetld.ach.jticker.TickerEntry;

/**
 *
 * {@code TickerSourceRss} objects refer to RSS-based sources of news.
 *
 */
public class
TickerSourceRss
extends TickerSource {
	/**
	 *
	 * This value is the URL of the RSS feed.
	 *
	 */
	URL feedLocation;

	/**
	 *
	 * This function is used to process the RSS feed.  The input is an RSS
	 * feed, and the output is the list of all entries in the RSS feed.
	 *
	 */
	Function<String, LinkedList<TickerEntry>> parseRssFeed;

	/**
	 *
	 * This function returns the RSS feed to which the ticker source points.
	 * No parsing is done.  The output is just XML or, in the event of a bad
	 * configuration, possibly something else.
	 *
	 */
	public String
	getRawRssFeed() {
		throw new UnsupportedOperationException
			("Fetching RSS feeds is unsupported.");
	}

	/**
	 *
	 * {@code getArticles(n)} returns {@code n} articles from this RSS feed.
	 *
	 * @param i the number of articles which should be returned
	 *
	 * @return {@code i} articles from this source
	 *
	 */
	public LinkedList<TickerEntry>
	getArticles(int i) {
		return parseRssFeed.apply(getRawRssFeed());
	}

	/**
	 *
	 * This constructor creates a new {@code TickerSourceRss} object which
	 * points to the specified URL and also has the specified name.  The
	 * naming system inherits all of the lovely quirks of
	 * {@link TickerSource#setName(String)}.
	 *
	 * @param newUrl the URL of the RSS feed
	 * @param newName the empty string or the name of the
	 *
	 */
	public
	TickerSourceRss(URL newUrl, String newName) {
		this.feedLocation = newUrl;
		this.setName(newName);
	}
}
