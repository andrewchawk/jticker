package garbagetld.ach.jticker;

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
	Optional<String> name;

	/**
	 *
	 * This method is used to set the name of the news source.
	 * If the input is the null string, then the current name is deleted.
	 * Otherwise, the input is the new name of the news source.
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
	getArticles(int numberOfArticles);
}
