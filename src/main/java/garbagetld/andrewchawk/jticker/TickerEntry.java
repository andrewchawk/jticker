package garbagetld.ach.jticker;

import java.util.Optional;
import java.net.URL;
import java.util.Date;

import garbagetld.ach.jticker.TickerSource;

/**
 *
 * {@code TickerEntry} objects describe the news entries or similar things which
 * should be used by the main ticker program.
 *
 * Within the remainder of the {@code TickerEntry} documentation, "article" is
 * used as a convenient shorthand for "news entry or similar thing".
 *
 */
public class
TickerEntry {
	/**
	 *
	 * This value is a short description of the article.
	 * These short descriptions crawl along the ticker.
	 *
	 * If this value is missing, then something is seriously broken.  Also,
	 * printing this entry would be gross.
	 *
	 */
	String shortDescription;
	/**
	 *
	 * This value is {@code empty} or points to the URL to which the
	 * corresponding entry in the RSS feed or similar source points.
	 *
	 * We use an {@code Optional} type because some RSS feeds and the like
	 * could just be methods of distributing articles without using fancy
	 * pages.
	 *
	 */
	Optional<URL> fullEntryUrl;
	/**
	 *
	 * This value, if present, is a relatively lengthy description of
	 * the article.  This value could even be a nice summary of the article.
	 *
	 */
	Optional<String> longDescription;
	/**
	 *
	 * This value, if present, is the date of the publishing of the article.
	 *
	 */
	Optional<Date> dateOfPublishing;
	/**
	 *
	 * This value was the source of this article.
	 *
	 */
	TickerSource source;

	/**
	 *
	 * This constructor generates a {@code TickerSource} in accordance with
	 * the specified information.
	 *
	 * Within this documentation, "the article" is an abbreviated form of
	 * "the item which is described by the result of the application of this
	 * constructor".
	 *
	 * @param s the source of the article
	 * @param sd a headline or short description of the article
	 * @param feu optionally, the URL of the full version of the article,
	 * as opposed to a headline
	 * @param ld optionally, the full article or a long description or
	 * summary of the article
	 * @param dop optionally, the date of the publishing of the article
	 *
	 */
	public
	TickerEntry(TickerSource s,
	            String sd,
	            Optional<String> ld,
	            Optional<URL> feu,
	            Optional<Date> dop) {
		shortDescription = sd;
		fullEntryUrl = feu;
		longDescription = ld;
		dateOfPublishing = dop;
		source = s;
	}
}
