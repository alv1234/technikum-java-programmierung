package bwi.prog2B.SS2017.wixxx.LW.util.comparators;

import bwi.prog2B.SS2017.wixxx.LW.entities.Event;

public class MyEventAttendeesComparator extends MyEventComparator {

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
		
		return e1.getAttendees() - e2.getAttendees();
	}

}
