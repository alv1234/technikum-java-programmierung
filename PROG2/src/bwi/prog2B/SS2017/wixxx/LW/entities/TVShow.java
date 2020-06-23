package bwi.prog2B.SS2017.wixxx.LW.entities;

public class TVShow extends Event {

	private String name;
	private int viewers;

	public TVShow() {
		super(); //gets called automatically
		viewers = 0;
	}

	public TVShow(Event e) {
		super(e);
	}

	public TVShow(TVShow tvs) {
		super(tvs);
		name = tvs.name;
		viewers = tvs.viewers;
	}

	public int getViewers() {
		return viewers;
	}

	public void setViewers(int v) {
		if (v >= 0) {
			viewers = v;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null && !name.trim().isEmpty()) {
			this.name = name;
		}
	}

	@Override
	public String toString() {
		return String.format("%s @ %s on %s\n%s\n(%d attending (%d))", getArtist().getName(), name, getDate(),
				getDescription(), getAttendees() + viewers, impact());
	}

	@Override
	public int impact() {
		return (getAttendees() + viewers) * 2;
	}

}
