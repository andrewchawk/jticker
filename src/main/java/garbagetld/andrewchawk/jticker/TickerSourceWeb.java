package garbagetld.ach.jticker;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.LinkedList;
import java.net.URL;

import garbagetld.ach.jticker.TickerSource;
import garbagetld.ach.jticker.TickerEntry;

/**
 *
 * {@code TickerSourceWeb} objects refer to Web-based sources of news.
 *
 * Previously, this class -- though by a different name -- was officially usable
 * only for RSS-based news sources, but a quick review of the the class showed
 * that the class was sufficiently abstract to be used for pretty much
 * <i>any</i> single-file, Web-based news source.
 *
 */
public class
TickerSourceWeb
extends TickerSource {
	/**
	 *
	 * This value is the URL of the file which describes the articles or the
	 * like.  A common and easy example of such a file is an RSS feed.
	 *
	 */
	URL feedLocation;

	/**
	 *
	 * This function is used to process the file which contains the news.
	 * The input is the sort of information which is fetched from the news
	 * source, and the output is the list of all entries which are described
	 * by the news feed.
	 *
	 */

	Function<String, LinkedList<TickerEntry>> parseWebFeed;
	/**
	 *
	 * This value, if present, will be used as the encoding scheme for the
	 * raw RSS feed.  If this value is absent, then {@code getRawWebeed()}
	 * will use {@code "UTF-8"}.
	 *
	 */
	Optional<String> scannerEncodingScheme;

	/**
	 *
	 * This function returns the content of the file to which
	 * {@code feedLocation} points.  If the file is an RSS feed, then the
	 * output should be XML.  Determining other behavior is left as an
	 * exercise for the reader.
	 *
	 * @return the raw content of the Web feed
	 * @throws IOException network errors
	 *
	 */
	public String
	getRawWebFeed()
	throws IOException {
		String out = "";
		Scanner webScanner = new Scanner(
			feedLocation.openStream(),
			scannerEncodingScheme.orElse("UTF-8")
		);

		out = webScanner.useDelimiter("\\a").next();

		webScanner.close();

		return out;
	}

	/**
	 *
	 * {@code getArticles(n)} returns {@code n} articles from the news
	 * source.
	 *
	 * @param i the number of articles which should be returned
	 *
	 * @return {@code i} articles from this source
	 *
	 */
	public LinkedList<TickerEntry>
	getArticles(int i)
	throws IOException {
		return parseWebFeed.apply(getRawWebFeed());
	}

	/**
	 *
	 * This constructor creates a new {@code TickerSourceWeb} object which
	 * points to the specified URL, has the specified name, and uses the
	 * specified parsing function.  The naming system inherits all of the
	 * lovely quirks of {@link TickerSource#setName(String)}.
	 *
	 * @param newUrl the URL of the Web-based source of news
	 * @param newName the empty string or the name of the
	 * @param maxEntries the maximum number of tracked articles from this
	 * source
	 * @param parseFun the parsing function
	 *
	 */
	public
	TickerSourceWeb(URL newUrl,
	                String newName,
			int maxEntries,
	                Function<String, LinkedList<TickerEntry>> parseFun) {
		super(maxEntries, Optional.empty());
		this.setName(newName);

		this.feedLocation = newUrl;
		this.scannerEncodingScheme = Optional.empty();
		this.parseWebFeed = parseFun;
	}
}
