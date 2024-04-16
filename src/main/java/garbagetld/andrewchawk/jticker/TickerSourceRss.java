package garbagetld.ach.jticker;

import java.io.IOException;
import java.util.Scanner;
import java.util.Optional;
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
	 * This value, if present, will be used as the encoding scheme for the
	 * raw RSS feed.  If this value is absent, then {@code getRawRssFeed()}
	 * will use {@code "UTF-8"}.
	 *
	 */
	Optional<String> scannerEncodingScheme;

	/**
	 *
	 * This function returns the RSS feed to which the ticker source points.
	 * No parsing is done.  The output is just XML or, in the event of a bad
	 * configuration, possibly something else.
	 *
	 */
	public String
	getRawRssFeed()
	throws IOException {
		String out = "";
		Scanner rssScanner = new Scanner(
			feedLocation.openStream(),
			scannerEncodingScheme.orElse("UTF-8")
		);

		out = rssScanner.useDelimiter("\\a").next();

		rssScanner.close();

		return out;
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
	getArticles(int i)
	throws IOException {
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
		this.scannerEncodingScheme = Optional.empty();
		this.setName(newName);
	}
}
