package garbagetld.ach.jticker;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;
import java.util.LinkedList;

import garbagetld.ach.jticker.TickerEntry;

/**
 *
 * {@code TickerOptions} objects contain the options which are to be used by the
 * program.
 *
 */
public class TickerOptions {
	/**
	 *
	 * If this value is nonnegative, then by default, each
	 * new {@code TickerSource} object can have at most
	 * {@code defaultMaxEntries} articles in the list of ticker entries.
	 * If this value <i>is</i> negative, then by default, each new
	 * {@code TickerSource} object can have infinitely many articles in
	 * the list of ticker entries.
	 *
	 */
	public int defaultMaxEntries;

	/**
	 *
	 * This function is used to recombine lists of entries after deleting
	 * -- well, not really
	 * -- see {@link Ticker#removeExcessEntriesFromSource(Source)}
	 * -- excess entries from the
	 * list of news entries.
	 *
	 */
	public BinaryOperator<LinkedList<TickerEntry>> recombineEntries;

	/**
	 *
	 * This function is used to delete excess entries from lists of entries
	 * which are fetched from <i>a single source</i>.
	 *
	 * {@code
	 *   trimSingleSource.apply(entries).size()
	 * } should be {@code
	 *   Math.min(entries.source.maxEntries, entries.size())
	 * }.  However, we currently do not check for compliance.
	 *
	 */
	public UnaryOperator<LinkedList<TickerEntry>> trimSingleSource;

	/**
	 *
	 * This function is the default parser for RSS feeds.  The input is an
	 * RSS feed, and the output is a list of the entries in the RSS feed.
	 * If no entries are recognized, then the output should be an empty
	 * list.
	 *
	 */
	public Function<String, LinkedList<TickerEntry>> defaultParseRss;
}
