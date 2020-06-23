package bwi.prog2B.SS2017.wixxx.LW.util.formatters;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
import bwi.prog2B.SS2017.wixxx.LW.util.MyFormatter;

public class ShortTrackFormatter implements MyFormatter<Track> {

	/**
	 * Get the headers for the table as a single string.<br>
	 * Contains the names for all columns separated by the correct number of blanks.
	 */
	@Override
	public String header() {
		return "Title      (min:sec)";
	}
	
	/**
	 * Creates a short format of a track.
	 * The short representation of a track is "title (duration)" (without quotes).
	 * Title is exactly ten characters wide with leading blanks (if any). 
	 * Duration is in the format minutes:seconds, both at least two digits wide with leading zeros.
	 */
	@Override
	public String format(Track t) {
		return String.format("%10.10s (%02d:%02d)", 
				t.getTitle(),
				t.getDuration() / 60, 
				t.getDuration() % 60);
	}

	/**
	 * top separator consists of dashes (-) only. It is exactly as wide as the header.
	 */
	@Override
	public String topSeparator() {
		return String.format("%020d", 0).replace('0', '-');
	}

	/**
	 * the string representation of this formatter is
	 * "short format [Title (min:sec)]" (without quotes)
	 */
	public String toString(){
		return "short format [Title (min:sec)]";
	}

	
}
