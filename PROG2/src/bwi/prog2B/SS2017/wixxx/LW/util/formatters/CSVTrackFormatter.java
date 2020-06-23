package bwi.prog2B.SS2017.wixxx.LW.util.formatters;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
import bwi.prog2B.SS2017.wixxx.LW.util.MyFormatter;

public class CSVTrackFormatter implements MyFormatter<Track> {

	@Override
	public String header() {

		return "Title, Writer, Performer, duration, year";
	}

	@Override
	public String format(Track t) {
		return String.format("%s, %s, %s, %d, %d", 
				t.getTitle(), t.getWriter(), t.getPerformer(), t.getDuration(), t.getYear());
	}

	@Override
	public String topSeparator() {
		return "";
	}

	@Override
	public String toString(){
		return "CSV format [Title, Writer, Performer, duration, year]";
	}

}
