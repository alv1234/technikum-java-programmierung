package bwi.prog2B.SS2017.wixxx.LW.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import bwi.prog.utils.TextIO;
import bwi.prog2B.SS2017.wixxx.LW.container.MyTrackContainer;
import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
import bwi.prog2B.SS2017.wixxx.LW.util.MyFormatter;
import bwi.prog2B.SS2017.wixxx.LW.util.MyMatcher;
import bwi.prog2B.SS2017.wixxx.LW.util.comparators.*;
import bwi.prog2B.SS2017.wixxx.LW.util.formatters.*;
import bwi.prog2B.SS2017.wixxx.LW.util.io.*;
import bwi.prog2B.SS2017.wixxx.LW.util.matcher.*;

public class MainProvided {

	private MyTrackContainer db = new MyTrackContainer();
	private List<Comparator<Track>> comparators = new LinkedList<Comparator<Track>>();
	private List<MyFormatter<Track>> formatters = new LinkedList<MyFormatter<Track>>();
	private List<MyMatcher<Track>> matchers = new LinkedList<MyMatcher<Track>>();

	private Comparator<Track> theComp;
	private boolean asc = true;

	private MyFormatter<Track> theFormat;
	private MyMatcher<Track> placeboMatcher = new TitleMatcher("");
	private Menu menu = new Menu();

	{

		comparators.add(theComp = new TitleComparator());
		comparators.add(new DurationComparator());
		comparators.add(new WriterComparator());
		comparators.add(new PerformerComparator());
		comparators.add(new YearComparator());

		matchers.add(placeboMatcher);
		matchers.add(new DurationMatcher());

		formatters.add(theFormat = new LongTrackFormatter());
		formatters.add(new ShortTrackFormatter());
		formatters.add(new CSVTrackFormatter());

	}

	private static final String WELCOME_TEXT = "Welcome to the FinalTrackDataBase";
	private static final String GOOD_BYE_TEXT = "Thank you for using FinalTrackDataBase";

	private static abstract class MenuItem {
		String text;
		static int nextID = 0;
		final int id = nextID++;

		abstract void execute();

		MenuItem(String s) {
			text = s;
		};

		public String toString() {
			return id + "\t" + text;
		}
	}

	private class Menu {

		//Array containting menu entries
		private MenuItem[] menu = {

				new MenuItem("show menu") {
					void execute() {
						display();
					}
					// end of MenuItem
				},
				new MenuItem("display selection") {
					void execute() {
						TextIO.putf("displaying selection:\n");

						MainProvided.this.display(db);
					}
					// end of MenuItem
				},
				new MenuItem("edit") {
					void execute() {
						MainProvided.this.edit();
					}
					// end of MenuItem
				},
				new MenuItem("filter") {
					void execute() {
						MainProvided.this.filter();
					}
					// end of MenuItem
				},
				new MenuItem("reset") {
					void execute() {
						db.reset();
						TextIO.put("Selection reset\n");
					}
					// end of MenuItem
				},
				new MenuItem("remove selection") {
					void execute() {
						TextIO.putf("remove selection:\n");
					}
					// end of MenuItem
				},
				new MenuItem("add") {
					void execute() {
						TextIO.putf("select track to add:\n");
					}
					// end of MenuItem
				},
				new MenuItem("save selection") {
					void execute() {
						TextIO.putf("save selection:\n");
					}
					// end of MenuItem
				},
				new MenuItem("load") {
					void execute() {
						MainProvided.this.load();
					}
					// end of MenuItem
				},
				new MenuItem("reverse sorting order") {
					void execute() {
						MainProvided.this.sortOrder();
					}
					// end of MenuItem
				},
				new MenuItem("select sorting") {
					void execute() {
						MainProvided.this.sort();
					}
					// end of MenuItem
				},
				new MenuItem("select formatting") {
					void execute() {
						MainProvided.this.format();
					}
					// end of MenuItem
				} 
		};// end of array

		
		void display() {
			for (MenuItem m : menu) {
				TextIO.putln(m);
			}
		}

		//filters menu function by user input and then loads execute method of that function
		public boolean execute(int input) {
			for (MenuItem m : menu) {
				if (m != null && m.id == input) {
					m.execute();
					return true;
				}
			}
			return false;
		}

	}//end of menu class

	public void go() {

		TextIO.putln(WELCOME_TEXT);
		menu.execute(0);
		while (true) {
			// display(db);

			// get choice
			TextIO.put(": ");
			int input = TextIO.getInt();
			if (menu.execute(input))
				continue;

			TextIO.put("exit? (1=yes)");
			if (TextIO.getInt() == 1)
				break;
		}

		TextIO.putln(GOOD_BYE_TEXT);
	}

	public static void main(String[] args) {

		new MainProvided().go();

	}

	public void display(MyTrackContainer db) {

		if (db.size() == 0) {
			TextIO.put("no records stored.\n");
			return;
		}
		if (db.selection().length == 0) {
			TextIO.put("selection empty.\n");
			return;
		}

		TextIO.putln('\n' + theFormat.header());
		TextIO.putln(theFormat.topSeparator());
		for (Track tt : db.selection())
			TextIO.putln(theFormat.format(tt));
		TextIO.putln();

		TextIO.putf("%d out of %d records selected\n", db.selection().length,
				db.size());
	}

	public void load() {
		TextIO.put("enter file name: ");
		TextIO.getln();
		String filename = TextIO.getln();
		int i = 0;
		try {
			MyTrackCSVReader reader = new MyTrackCSVReader(new BufferedReader(new FileReader(filename)));
			while (true){
				Track t = reader.get();
				if (t == null) break;
				db.add(t);
				i++;
			}
			db.reset();
			TextIO.putf("%d tracks imported\n", i);
		} catch (FileNotFoundException e) {
			TextIO.putf("Error: cannot open file (%s).\n", filename);
		}
	}

	public void format() {
		int i = 1;
		for (MyFormatter<Track> format : formatters) TextIO.putf("%d: %s\n", 
				i++, format.toString());
		TextIO.put("select formatting: ");
		int selection = TextIO.getInt();
		if (selection > 0 && selection < i) {
		TextIO.putf("%s selected.\n", formatters.get(selection -1));
		theFormat = formatters.get(selection -1);
		} else {
			TextIO.put("invalid selection.\n");
		}
	}

	public void sort() {
		int i = 1;
		for (Comparator<Track> comparator : comparators) TextIO.putf("%d: %s\n", 
				i++, comparator.toString());
		TextIO.put("select sorting: ");
		int selection = TextIO.getInt();
		if (selection > 0 && selection < i) {
		TextIO.putf("selection sorted %s (%s).\n", 
				comparators.get(selection -1),
				(asc) ? "ascending" : "descending");
		theComp = comparators.get(selection -1);
		db.sort(theComp, asc);
		} else {
			TextIO.put("invalid selection.\n");
		}

	}

	public void sortOrder() {
		if (asc == true) {
			asc = false;
		} else {
			asc = true;
		}
		TextIO.putf("selection sorted %s (%s).\n", 
				theComp.toString(),
				(asc) ? "ascending" : "descending");
		db.sort(theComp, asc);
	}

	public void filter() {
		//matchers
		int i = 1;
		for (MyMatcher<Track> matcher : matchers) TextIO.putf("%d: %s\n", 
				i++, matcher.toString());
		TextIO.put("select filtering: ");
		int selection = TextIO.getInt();
		TextIO.put("enter pattern: ");
		TextIO.getln();
		String pattern = TextIO.getln();
		if (selection > 0 && selection < i) {
			placeboMatcher = matchers.get(selection -1);
			placeboMatcher.setPattern(pattern);
			TextIO.putf("\"%s\" filter applied (%d records filtered)\n", 
					matchers.get(selection -1),
					db.filter(placeboMatcher));
		}

	}

	public void edit() {
		MyShortTrackFormatter f = new MyShortTrackFormatter();
		Track[] tracks = db.selection();
		for (int i = 0; i < tracks.length; i++) {
			TextIO.putf("%d: %s\n", i + 1, f.format(tracks[i]));
		}
		TextIO.put("select track to edit: ");
		int index = TextIO.getlnInt();
		if (index >= 1 && index <= tracks.length) {
			TextIO.putln("enter new track information:");
			tracks[index - 1].scan();
			db = new MyTrackContainer(tracks);
		} else {
			TextIO.putln("invalid number.");
		}		
	}

}
