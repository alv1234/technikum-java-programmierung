package bwi.prog2B.SS2017.wixxx.LW.util.matcher;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
import bwi.prog2B.SS2017.wixxx.LW.util.MyMatcher;

/**
 * Encapsulates the concept of matching a track based on its title.
 * This class is used to test whether a given track's title starts with a certain string, 
 * the starting string being the pattern of this matcher.
 * The pattern is a simple string. Null patterns are not accepted.
 * 
 * @author alessandro
 *
 */
public class TitleMatcher extends MyMatcher<Track>{

	private String pattern;
	
	/**
	 * Creates a title matcher with a specified pattern.
	 * 
	 * @param pat
	 */
	public TitleMatcher(String pat){
		this.setPattern(pat);
	}

	
	/**
	 * a track matches if its title starts with the pattern of this matcher.
	 */
	@Override
	public boolean matches(Track t) {
		if (t.getTitle().matches("^"+pattern+".+")) return true;
		return false;
	}

	/**
	 * Any non-null String is an acceptable pattern.
	 */
	@Override
	public void setPattern(String pat) {
		if (pat != null) pattern = pat;
	}

	/**
	 * the valid pattern is simply the pattern
	 */
	@Override
	public String getPattern() {
		return pattern;
	}

	/**
	 * the string representation is 
	 * title starts with (PATTERN)
	 * with range as described in getPAttern
	 */
	public String toString(){
		return "title starts with (" + this.getPattern() + ")";
	}
	
}
