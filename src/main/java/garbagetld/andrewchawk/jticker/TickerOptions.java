package garbagetld.ach.jticker;

/**
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
}
