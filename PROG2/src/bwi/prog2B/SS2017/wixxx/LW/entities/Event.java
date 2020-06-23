package bwi.prog2B.SS2017.wixxx.LW.entities;

import bwi.prog.utils.Date;
import bwi.prog.utils.Venue;

public class Event {
	private Artist artist;
	private int attendees;
	private Date date;
	private String description;
	private Venue venue;

	public Event() {
		artist = new Artist();
		description = "";
	}

	public Event(Event e) {
		artist = new Artist(e.artist);
		date = new Date(e.date);
		venue = new Venue(e.venue);
		attendees = e.attendees;
		description = e.description;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		if (artist != null) {
			this.artist = artist;
		}
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Date getDate() {
		if (date == null) {
			return null;
		}
		return new Date(date);
	}

	public void setDate(Date date) {
		if (date == null) {
			this.date = null;
		} else {
			this.date = new Date(date);
		}
	}

	public int getAttendees() {
		return attendees;
	}

	public void setAttendees(int attendees) {
		if (attendees >= 0) {
			this.attendees = attendees;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description != null) {
			this.description = description;
		}
	}

	public int impact() {
		return attendees * 2;
	}

	@Override
	public String toString() {
		return String.format("%s @ %s on %s\n%s\n(%d attending (%d))", artist.getName(),
				venue == null ? "unknown" : venue.getName(), date, description, attendees, impact());
	}

}
