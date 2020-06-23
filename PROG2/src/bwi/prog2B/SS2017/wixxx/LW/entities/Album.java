package bwi.prog2B.SS2017.wixxx.LW.entities;

import java.util.Iterator;

public class Album extends Release {

	private TrackListItem trackListHead;

	public Album() {
		// default super constructor gets called automatically
	}

	public Album(Album orig) {
		super(orig);
		trackListHead = orig.trackListHead;
	}

	public Album(String title, Artist artist, int year) {
		super(title, artist, year);
	}

	public boolean addTrack(Track t) {
		if (t != null) {

			// list empty
			if (trackListHead == null) {
				trackListHead = new TrackListItem(t);
				return true;
			}

			TrackListItem tli = trackListHead;
			while (tli.next != null) {
				tli = tli.next;
			}
			tli.next = new TrackListItem(t);
			return true;

		}
		return false;
	}

	public Track removeTrack(int n) {
		
		if (trackListHead == null) {
			return null;
		}
		
		TrackListItem current = trackListHead;
		TrackListItem previous = null;

		for (int i = 0; i < n; i++) {
			if (current == null) {
				return null;
			}
			previous = current;
			current = current.next;
		}

		if(previous == null) {
			trackListHead = current.next;
			return current.track;
		}
		
		if (current.next == null) {
			previous.next = null;
		} else {
			previous.next = current.next;
		}
		return current.track;

	}

	public int nrTracks() {
		int number = 1;
		if (trackListHead == null) {
			return 0;
		}
		TrackListItem tli = trackListHead;
		while (tli.next != null) {
			tli = tli.next;
			number++;
		}
		return number;
	}

	public Track[] getTracks() {
		int length = nrTracks();
		Track[] tracks = new Track[length];
		TrackListItem tli = trackListHead;

		for (int i = 0; i < length; i++) {
			tracks[i] = tli.track;
			tli = tli.next;
		}
		return tracks;
	}

	@Override
	public int totalTime() {
		// not using getTracks() reduces number of loops to two

		TrackListItem tli = trackListHead;
		int duration = 0;

		for (int i = 0; i < nrTracks(); i++) {
			duration += tli.track.getDuration();
			tli = tli.next;
		}
		return duration;
	}

	@Override
	public String toString() {
		// not using getTracks() reduces number of loops to two
		
		TrackListItem tli = trackListHead;
		String tracks = "";

		for (int i = 0; i < nrTracks(); i++) {
			tracks += "[" + tli.track.getTitle() + "]";
			tli = tli.next;
		}

		return String.format("%s\n[%s]", super.toString(), tracks);
	}

	private class TrackListItem {

		TrackListItem next;
		Track track;

		TrackListItem(Track t) {
			track = t;
		}
	}

}
