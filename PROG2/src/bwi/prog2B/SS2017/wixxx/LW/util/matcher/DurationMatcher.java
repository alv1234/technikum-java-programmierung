package bwi.prog2B.SS2017.wixxx.LW.util.matcher;

import bwi.prog.utils.TextIO;
import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
import bwi.prog2B.SS2017.wixxx.LW.util.MyMatcher;

/**
 * Encapsulates the concept of matching a track based on its duration.
 * This class is used to test whether given a given track's duration lies in a certain range, 
 * the range being the pattern of this matcher.
 * The pattern is a simple string consisting of the (white-space separated) lower and upper bounds 
 * (inclusive) of the range of duration s (in seconds) accepted by this matcher. 
 * More precisely, a valid pattern is a String that can be interpreted as
 * either a single integer number (leading and trailing whitespace are ignored, 
 * if present) which then represents the lower bound or two integer numbers, 
 * separated by (any number of) whitespace, which then represent lower and upper bound.
 * The bounds are understood to be inclusive.
 * @author alessandro
 *
 */
public class DurationMatcher extends MyMatcher<Track>{

	public int	lower, upper;
	
	/**
	 * Creates a default duration matcher.
	 * By default a matcher matches any duration, including unknown duration.
	 */
	public DurationMatcher(){
		super("0 " + Integer.MAX_VALUE);
	}
	
	public DurationMatcher(String pat) {
		super(pat);
	}

	/**
	 * A track matches if its duration is in the range accepted by this matcher.
	 */
	@Override
	public boolean matches(Track t) {
		if (lower <= t.getDuration() && t.getDuration() <= upper) return true;
		return false;
	}

	/**
	 * Sets the pattern of this matcher.
	 * Interprets the argument as described in the class documentation. 
	 * First sets the lower, then the upper bound. The bounds specified are set if and only if 
	 * at the time of setting they are actually lower (for the lower bound) or higher (for the upper bound) 
	 * than the other or at least equal to the other.
	 * 
	 */
	@Override
	public void setPattern(String pat) {
		String[] parts = pat.trim().split("\\s+");

		int b;
		//both boundaries should be specified
		if (parts.length <= 1)
			return;
		try {
			if ((b = Integer.parseInt(parts[0].trim())) >= 0)
				if (b <= upper||b <=Integer.parseInt(parts[1].trim()))
					lower = b;
		} catch (NumberFormatException e) {
			TextIO.putf("Error: invalid pattern (%s)\n", pat);
		}


		try {
			if ((b = Integer.parseInt(parts[1].trim())) >= lower)
					upper = b;

		} catch (NumberFormatException e) {
			TextIO.putf("Error: invalid pattern (%s)\n", pat);
		}
	}

	/**
	 * the valid pattern is 
	 * lower upper
	 * separated by whitespace.
	 */
	@Override
	public String getPattern() {
		return String.format("%d %d", lower, upper);
	}

	/**
	 * the string representation is 
	 * duration in range (RANGE)
	 * with range as described in getPattern
	 */
	
	public String toString(){
		return String.format("duration in range (%s)", this.getPattern());
	}
	
}
