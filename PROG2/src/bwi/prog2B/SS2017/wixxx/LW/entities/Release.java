package bwi.prog2B.SS2017.wixxx.LW.entities;

public abstract class Release {

	private Artist artist;
	private String title;
	private int year;

	public Release() {
		artist = new Artist();
		year = 1900;
		title = null;
	}

	public Release(Release orig) {
		artist = new Artist(orig.artist);
		year = orig.year;
		title = orig.title;
	}

	public Release(String title, Artist artist, int year) {
		this.title = title;
		this.artist = artist;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year >= 0) {
			this.year = year;
		}
	}

	public abstract int totalTime();

	@Override
	public String toString() {
		return String.format("%s-%s-%s-%s", title == null ? "unknown" : title, artist == null ? "unknown" : artist,
				year == 0 ? "unknown" : year, totalTime());
	}
}
