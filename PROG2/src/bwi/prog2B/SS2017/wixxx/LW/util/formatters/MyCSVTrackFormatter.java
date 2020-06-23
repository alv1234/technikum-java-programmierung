package bwi.prog2B.SS2017.wixxx.LW.util.formatters;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;

public class MyCSVTrackFormatter extends MyTrackFormatter {

	@Override
	public String format(Track t) {
		return String.format("%s,%s,%s,%s,%s;", t.getTitle(), t.getPerformer(), t.getWriter(), t.getYear(),
				t.getDuration());
	}

}
