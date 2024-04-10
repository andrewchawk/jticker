package garbagetld.ach.jticker;

import java.util.LinkedList;
import java.util.Optional;

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

}
