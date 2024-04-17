package garbagetld.ach.jticker;

import java.io.IOException;
import java.util.Optional;
import java.util.LinkedList;

import garbagetld.ach.jticker.TickerEntry;

/**
 *
 * {@code TickerSource} objects refer to entities from which new ticker
 * headlines can be fetched.
 *
 */
public abstract class
TickerSource {
	/**
	 *
	 * This value refers to the name of the source.
	 *
	 */
	public Optional<String> name;
	/**
	 *
	 * If this value is negative, then infinitely many news entries from
	 * this source can accumulate in the list of news entries.  If this
	 * value is nonnegative, then at any point in time, no more than
	 * {@code maxEntries} news entries from this source are in the list of
	 * news entries.
	 *
	 */
	public int maxEntries;

	/**
	 *
	 * This method is used to set the name of the news source.
	 * If the input is the null string, then the current name is deleted.
	 * Otherwise, the input is the new name of the news source.
	 *
	 * This function does not account for <i>all</i> terrible names; names
	 * which consist solely of whitespace characters <i>are</i> permitted.
	 * This functionality <i>may</i> eventually be changed, but such change
	 * will probably just encourage the creation of a significantly more
	 * robust -- and complex -- detector of terrible names.
	 *
	 * @param possibleNewName the empty string or the desired name
	 *
	 */
	public void
	setName(String possibleNewName) {
		this.name =
			possibleNewName.equals("") ?
				Optional.empty() :
				Optional.of(possibleNewName);
	}

	/**
	 *
	 * {@code getArticles(num)} returns a list of {@code n} articles from
	 * this source, where {@code n} is {@code
	 *   Math.min(Math.max(0, num), NUMBER_OF_ARTICLES)
	 * }, where {@code NUMBER_OF_ARTICLES} is the number of articles which
	 * are available through this source.
	 *
	 * @param numberOfArticles the number of articles which should be
	 * returned
	 *
	 * @return a list of articles
	 *
	 */
	abstract public LinkedList<TickerEntry>
	getArticles(int numberOfArticles)
	throws IOException;

	/**
	 *
	 * In accordance with the specified parameters, this constructor returns
	 * a new source object.
	 *
	 * @param newMaxEntries a negative number or the maximum number of
	 * entries from this article which should be tracked
	 * @param newName optionally, the {@code name} of the new source object
	 *
	 */
	public
	TickerSource(int newMaxEntries,
	             Optional<String> newName) {
		maxEntries = newMaxEntries;
		name = newName;
	}
}
