package bwi.prog2B.SS2017.wixxx.LW.entities;

import bwi.prog.utils.TextIO;
import bwi.prog2B.SS2017.wixxx.LW.util.ConsoleScanable;

public class Track implements ConsoleScanable {
	private int duration;
	private Artist performer;
	private String title;
	private Artist writer;
	private int year;

	public Track() {
		duration = 0;
		year = 1900;
		performer = new Artist();
		writer = new Artist();
	}

	public Track(String title) {
		this(); // default Werte auch bei diesem Konstruktor setzen
		this.title = title;
	}

	public Track(Track t) {
		this();
		if (t != null) {
			title = t.title;
			duration = t.duration;
			writer = new Artist(t.writer);
			performer = new Artist(t.performer);
			year = t.year;
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year >= 1900 && year < 3000) {
			this.year = year;
		}
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if (duration >= 0) {
			this.duration = duration;
		}
	}

	public String getTitle() {
		if (title == null) {
			return "unknown title";
		}
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Artist getWriter() {
		return writer;
	}

	public void setWriter(Artist writer) {
		if (writer != null) {
			this.writer = writer;
		}
	}

	public Artist getPerformer() {
		return performer;
	}

	public void setPerformer(Artist performer) {
		if (performer != null) {
			this.performer = performer;
		}
	}

	public boolean writerIsKnown() {
		if (writer != null && writer.getName() != null) {
			return true;
		}
		return false;
	}

	public String getString() {
		/*
		 * String writer, performer, title;
		 * 
		 * if (this.title == null) { title = "unknown"; } else { title =
		 * this.title; }
		 * 
		 * if (this.writer == null || this.writer.getName() == null) { writer =
		 * "unknown"; } else { writer = this.writer.getName(); }
		 * 
		 * if (this.performer == null || this.performer.getName() == null) {
		 * performer = "unknown"; } else { performer = this.performer.getName();
		 * }
		 * 
		 * return
		 * String.format("%10.10s by %10.10s performed by %10.10s (%02d:%02d)",
		 * title, writer, performer, getDuration() / 60, getDuration() % 60);
		 */

		// Using conditional operator instead instead
		return String.format("%10.10s by %10.10s performed by %10.10s (%02d:%02d)", title == null ? "unknown" : title,
				writer == null ? "unknown" : (writer.getName() == null ? "unknown" : writer.getName()),
				performer == null ? "unknown" : (performer.getName() == null ? "unknown" : performer.getName()),
				duration / 60, duration % 60);
	}

	@Override
	public String toString() {
		return getString();
	}

	@Override
	public boolean scan() {
		boolean fieldChanged = false, objectChanged = false;
		String input;

		// scanning title
		do {
			TextIO.putf("current title: %s\n", this.title);
			TextIO.putf("enter new title (leave empty to keep):");
			input = TextIO.getlnString();

			if (input.length() == 0) { // keep old value? fieldChanged = false;
				break;
			}

			/*
			 * if (!validateTitle(title)) {
			 * TextIO.putf("not a valid title (%s).\n", title); continue; }
			 */

			fieldChanged = true;
			break;
		} while (true);

		if (fieldChanged) {
			setTitle(input);
		}
		objectChanged = objectChanged || fieldChanged;
		fieldChanged = false; // set up for next field
		// scan next field(s)

		// scanning duration
		int duration = 0;
		do {
			TextIO.putf("current duration: %s\n", this.duration);
			TextIO.putf("enter new duration (write current again to keep):");
			input = TextIO.getlnString();
			
			if (input.length() == 0) { // keep old value? fieldChanged = false;
				break;
			}
			
			duration = Integer.parseInt(input);
			
			if (duration == this.duration) { // keep old value? fieldChanged = false;
				break;
			}
			
			if(duration >= 0) {
				fieldChanged = true;
				break;
			}
		} while (true);

		if (fieldChanged) {
			setDuration(duration);
		}
		objectChanged = objectChanged || fieldChanged;
		fieldChanged = false; // set up for next field
		// scan next field(s)

		// scanning performer
		do {
			TextIO.putf("current performer: %s\n", this.performer.getName());
			TextIO.putf("enter new performer (leave empty to keep):");
			input = TextIO.getlnString();

			if (input.length() == 0) { // keep old value? fieldChanged = false;
				break;
			}

			/*
			 * if (!validateTitle(title)) {
			 * TextIO.putf("not a valid title (%s).\n", title); continue; }
			 */

			fieldChanged = true;
			break;
		} while (true);

		if (fieldChanged) {
			this.getPerformer().setName(input);
		}
		objectChanged = objectChanged || fieldChanged;
		fieldChanged = false; // set up for next field
		// scan next field(s)
		
		// scanning writer
				do {
					TextIO.putf("current writer: %s\n", this.writer.getName());
					TextIO.putf("enter new writer (leave empty to keep):");
					input = TextIO.getlnString();

					if (input.length() == 0) { // keep old value? fieldChanged = false;
						break;
					}

					/*
					 * if (!validateTitle(title)) {
					 * TextIO.putf("not a valid title (%s).\n", title); continue; }
					 */

					fieldChanged = true;
					break;
				} while (true);

				if (fieldChanged) {
					this.getWriter().setName(input);
				}
				objectChanged = objectChanged || fieldChanged;
				fieldChanged = false; // set up for next field
				// scan next field(s)
		
				// scanning year
				int year = 0;
				do {
					TextIO.putf("current year: %s\n", this.getYear());
					TextIO.putf("enter new year (leave empty to keep):");
					input = TextIO.getlnString();

					if (input.length() == 0) { // keep old value? fieldChanged = false;
						break;
					}
					
					year = Integer.parseInt(input);
					
					if(year >= 0) {
						fieldChanged = true;
						break;
					}

					/*
					 * if (!validateTitle(title)) {
					 * TextIO.putf("not a valid title (%s).\n", title); continue; }
					 */

					fieldChanged = true;
					break;
				} while (true);

				if (fieldChanged) {
					this.setYear(year);
				}
				objectChanged = objectChanged || fieldChanged;
				fieldChanged = false; // set up for next field
				// scan next field(s)
				
		return objectChanged;
	}

}
