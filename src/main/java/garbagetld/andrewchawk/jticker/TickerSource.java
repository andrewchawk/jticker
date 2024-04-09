package garbagetld.ach.jticker;

import java.util.Optional;

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
}
