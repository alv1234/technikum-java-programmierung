package bwi.prog2B.SS2017.wixxx.LW.util.comparators;

import bwi.prog2B.SS2017.wixxx.LW.entities.Event;

public class MyEventDateComparator extends MyEventComparator {

	@Override
	public int compare(Event e1, Event e2) {
		// compare events
		if (e1 == null) {
			if (e2 == null) {
				return 0;
			} else {
				return -1;
			}
		} else if (e2 == null) {
			if (e1 != null) {
				return 1;
			}
		}
		
		//compare dates
		if (e1.getDate() == null) {
			if (e2.getDate() == null) {
				return 0;
			} else {
				return -1;
			}
		} else if (e2.getDate() == null) {
			if (e1.getDate() != null) {
				return 1;
			}
		}
		
		return e1.getDate().compareTo(e2.getDate());
	}

}
