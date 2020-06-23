package bwi.prog2B.SS2017.wixxx.LW.entities;

public class MusicVideo extends Release {

	private Track track;

	public MusicVideo() {

	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	@Override
	public int totalTime() {
		if (track == null) {
			return 0;
		}
		return track.getDuration();
	}

	@Override
	public String toString() {
		return String.format("%s-(Video)", super.toString());
	}

}
