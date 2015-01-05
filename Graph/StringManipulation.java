import java.util.ArrayList;

/**
 * A static helper class that contains methods used for string manipulation.
 *
 * @author Andrei Muntean
 */
public class StringManipulation
{
	/**
	 * Returns all strings between two boundaries from a specified string.
	 * For example, getSubstringsBetween("<a> and <b> and <c>", "<", ">") will return { "a", "b", "c" }.
	 *
	 * @param source A specified string.
	 * @param lowerBoundary The lower boundary.
	 * @param upperBoundary The upper boundary.
	 *
	 * @return An array of strings.
	 */
	public static String[] getSubstringsBetween(String source, String lowerBoundary, String upperBoundary)
	{
		ArrayList<String> substrings = new ArrayList<String>();
		int startIndex = source.indexOf(lowerBoundary);

		while (startIndex != -1)
		{
			// Gets the next appearance of the upperBoundary substring.
			int endIndex = source.indexOf(upperBoundary);

			// Stores the substring between the two boundaries.
			substrings.add(source.substring(startIndex + lowerBoundary.length(), endIndex));

			// Truncates the string.
			source = source.substring(endIndex + upperBoundary.length(), source.length());
			
			// Searches for the next appearance of the lowerBoundary substring.
			startIndex = source.indexOf(lowerBoundary);
		}

		return substrings.toArray(new String[substrings.size()]);
	}
}