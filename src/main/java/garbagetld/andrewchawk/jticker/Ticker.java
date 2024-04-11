package garbagetld.ach.jticker;

import java.util.LinkedList;
import java.util.Optional;
import java.net.URL;

import garbagetld.ach.jticker.TickerOptions;
import garbagetld.ach.jticker.TickerSource;
import garbagetld.ach.jticker.TickerEntry;

/**
 *
 * {@code Ticker} objects contain news processing options,
 * lists of sources of news, and various other goodies.
 *
 * Using {@code Ticker} instead of a bunch of variables in the program's main
 * {@code main} encourages an object-oriented style and prevents clutter.  The
 * only real cost is the difficulty of writing good documentation <i>for</i>
 * {@code Ticker}.
 *
 */
public class
Ticker {
	/**
	 *
	 * This value contains the options which are specific to the ticker.
	 *
	 * Other types of options could, conceivably, exist.
	 *
	 */
	TickerOptions options;
	/**
	 *
	 * This value is a list of the sources from which news should be
	 * gathered.
	 *
	 */
	LinkedList<TickerSource> sources;
	/**
	 *
	 * This value is a list of articles which could be used by the ticker.
	 *
	 */
	LinkedList<TickerEntry> articles;
	/**
	 *
	 * At a given point in time, this value is empty or refers to whatever
	 * line is being displayed on the ticker.
	 *
	 * Causes for emptiness include a lack of news.
	 *
	 */
	Optional<String> currentLine;

	/**
	 *
	 * Ignoring the order of the list, articles from the specified source
	 * are deleted in accordance with the {@code options} until at most
	 * {@code source.maxEntries} articles from the specified source remain.
	 *
	 * @param source the source whose articles should possibly be deleted
	 *
	 */
	public void
	removeExcessEntriesFromSource(TickerSource source) {
		if (source.maxEntries < 0)
			// If having infinitely many entries is acceptable, then
			// "excess entries" is a meaningless statement!
			return;

		// Declaring variables here prevents excessive memory
		// allocation but is a bit ugly.
		LinkedList<TickerEntry> entriesFromSource = new LinkedList<>();
		LinkedList<Integer> deletionIndices = new LinkedList<>();

		// We actually define entriesFromSource and deletionIndices.
		for (int i = 0; i < articles.size(); i++) {
			if (source.equals(articles.get(i).source)) {
				entriesFromSource.add(articles.get(i));
				deletionIndices.add(i);
			}
		}

		if (entriesFromSource.size() > source.maxEntries) {
			for (int i = 0; i < deletionIndices.size(); i++)
				// Adding i, which is the total number of
				// elements which have *already* been removed,
				// ensures that the indexing is correct.  A
				// trivial example of bad indexing is as
				// follows:
				//
				//   {1, 2, 3, 4, 5} is the array from which
				//   values should be removed.
				//   The array of the indices of the values
				//   which should be removed is {1, 3}.
				//   We begin by removing the element at 1.  The
				//   result is {1, 3, 4, 5}.
				//   We then remove the elemnent at 3.  The
				//   result is {1, 3, 4}.  This result is bad.
				articles.remove(i + deletionIndices.get(i));

			articles = options.recombineEntries.apply(
				articles,
				options.trimSingleSource.apply(
					entriesFromSource
				)
			);
		}
	}

	/**
	 *
	 * Add the specified RSS source to the list of sources.
	 *
	 * @param feedUrl the URL of the RSS source
	 * @param possibleName the empty string or desired name
	 *
	 */
	public void
	addSourceRss(URL feedUrl, String possibleName) {
		TickerSourceRss toAdd =
			new TickerSourceRss(feedUrl, possibleName);
		toAdd.parseRssFeed = options.defaultParseRss;
		sources.add(toAdd);
	}
}
