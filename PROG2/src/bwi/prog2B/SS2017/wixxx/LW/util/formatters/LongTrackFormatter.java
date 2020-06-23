package bwi.prog2B.SS2017.wixxx.LW.util.formatters;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
import bwi.prog2B.SS2017.wixxx.LW.util.MyFormatter;

public class LongTrackFormatter implements MyFormatter<Track>{

	@Override
	public String header() {
		return "Title      (min:sec) Performer  Writer     Year";
	}

	@Override
	public String format(Track t) {
		return String.format("%-10.10s (%02d:%02d) %-10.10s %-10.10s %04d", 
				t.getTitle(),
				t.getDuration() / 60, 
				t.getDuration() % 60,
				t.getPerformer(),
				t.getWriter(),
				t.getYear());
	}

	@Override
	public String topSeparator() {
		return String.format("%050d", 0).replace('0', '-');
	}

	@Override
	public String toString(){
		return "long format [Title (min:sec) Performer Writer Year]";
	}
	
}
