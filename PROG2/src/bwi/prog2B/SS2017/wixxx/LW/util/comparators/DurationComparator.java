package bwi.prog2B.SS2017.wixxx.LW.util.comparators;

import java.util.Comparator;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;

public class DurationComparator implements Comparator<Track>{

	@Override
	public int compare(Track o1, Track o2) {
		return o1.getDuration() - o2.getDuration();
	}
	
	@Override
	public String toString() {
		return "by duration";
	}
}
