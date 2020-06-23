package bwi.prog2B.SS2017.wixxx.LW.entities;

public class Concert extends Event {

	private int nextIdx;
	private Track[] setList;

	public Concert() {
		nextIdx = 0;
		setList = new Track[20];
	}

	public boolean addTrack(Track t) {
		if (t == null) {
			return false;
		}
		// if setList is Full, add 10 Tracks
		if (setList.length <= nextIdx) {
			ensureCapacity(setList.length + 10);
		}

		setList[nextIdx++] = t;
		return true;
	}

	private void ensureCapacity(int length) {
		if (setList.length < length) {
			Track[] capEnsured = new Track[length];
			for (int i = 0; i < nextIdx; i++) {
				capEnsured[i] = setList[i];
			}
			setList = capEnsured;
		}
	}

	public Track[] getSetList() {
		Track[] returnList = new Track[nextIdx];
		for (int i = 0; i < nextIdx; i++) {
			returnList[i] = new Track(setList[i]);
		}
		return returnList;
	}

	public void setSetList(Track[] tracks) {
		if (tracks == null) {
			return;
		}
		int count = 0;
		// null values can also be in the middle, not only at the end
		for (int i = 0; i < tracks.length; i++) {
			if (tracks[i] != null) {
				count++;
			}
		}
		setList = new Track[count];
		nextIdx = 0;
		for (int i = 0; i < tracks.length; i++) {
			if (tracks[i] != null) {
				addTrack(new Track(tracks[i]));
			}
		}
	}

	public void resetSetList() {
		setList = new Track[20];
		nextIdx = 0;
	}

	public int nrTracks() {
		return nextIdx;
	}

	public int duration() {
		int duration = 0;
		for (int i = 0; i < nextIdx; i++) {
			duration += setList[i].getDuration();
		}
		return duration;
	}

	public int impact() {
		// The number of attendees is mulitplied by the duration factor, which
		// is initially 1 but increases by one for every started half hour the
		// concert lasts. E.G: 400 people attending the concert. 75 minutes
		// duration; duration factor=3 (two full half hours, plus one started
		// half hour) impact therefore is 400*3.
		
		//if the initial value is 1 and increases, shouldn't it be 400*4 
		return getAttendees() * (duration() / 1800 + 1);
	}

	@Override
	public String toString() {
		return String.format("%s\n%d tracks played, total duration %02d:%02d.", super.toString(), nrTracks(),
				duration() / 3600, (duration() % 3600) / 60);
	}
}
