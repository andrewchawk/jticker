package garbagetld.ach.jticker;

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
	 * {@code getArticles(n)} returns {@code n} articles from this RSS feed.
	 *
	 * @param i the number of articles which should be returned
	 *
	 * @return {@code i} articles from this source
	 *
	 */
	public LinkedList<TickerEntry>
	getArticles(int i) {
		throw new UnsupportedOperationException
			("Fetching RSS feeds is unsupported.");
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
