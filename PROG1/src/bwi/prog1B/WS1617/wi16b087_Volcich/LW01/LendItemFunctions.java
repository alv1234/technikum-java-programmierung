package bwi.prog1B.WS1617.wixxx.LW01;

import bwi.prog.utils.TextIO;

public class LendItemFunctions {
	
	public static String lendItemString(LendItem it, int format) {
		String lendItemString = null;
		switch (format) {
		case 1:
			lendItemString = String.format("%3d\t%-15.15s\t%-10.10s\t%s\t%s\t%-10.10s", it.id, it.description, it.lender,
					dateString(it.lendDate), dateString(it.returnDate), it.owner);
			break;
		case 2:
			lendItemString = String.format("%3d\t%-15.15s\t%-10.10s", it.id, it.description, it.lender);
			break;
		case 3:
			lendItemString = String.format("\"%d\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", it.id, it.description, it.lender,
					dateString(it.lendDate), dateString(it.returnDate), it.owner);
			break;
		}
		return lendItemString;
	}
	
	public static String lendItemHeadings(int format){
		String lendItemHeadings = null;
		switch (format) {
		case 1:
			lendItemHeadings = "ID\tdescription\tlender\t\tlend date\treturn date\towner";
			break;
		case 2:
			lendItemHeadings = "ID\tdescription\tlender";
			break;
		case 3:
			lendItemHeadings = "\"ID\",\"description\",\"lender\",\"lend date\",\"return date\",\"owner\"";
			break;
		}
		return lendItemHeadings;
	}
	
	public static String lendItemSeparator(int format){
		String lendItemSeparator = null;
		switch (format) {
		case 1:
			lendItemSeparator = "--------------------------------------------------------------------------------------------";
			break;
		case 2:
			lendItemSeparator = "--------------------------------------------";
			break;
		case 3:
			lendItemSeparator = "-------------------------------------------------------------";
			break;
		}
		return lendItemSeparator;
	}
	
	private static String dateString(Date d){
		if (d == null) {
			String date = "<not set>";
			return date;
		} else {
			String date = String.format("%4d.%02d.%02d", d.year, d.month, d.day);
			return date;
		}
	}
	
	public static int compare(LendItem it1, LendItem it2, int method){
		int compare;
		switch(method){
		case 1:
			compare=compareByLendDate(it1, it2);
			break;
		case 2:
			compare=compareByReturnDate(it1, it2);
			break;
		case 3:
			compare=compareByLender(it1, it2);
			break;
		case 4:
			compare=compareByOwner(it1, it2);
			break;
		default:
			compare=compareByDescription(it1,it2);
			break;
		}
		return compare;
	}
	
	public static int compareByLendDate(LendItem it1, LendItem it2){
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;

		return compare(it1.lendDate, it2.lendDate);
	}
	
	public static int compareByReturnDate(LendItem it1, LendItem it2){
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;

		return compare(it1.returnDate, it2.returnDate);
	}
	
	public static int compareByDescription(LendItem it1, LendItem it2){
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;

		int res=it1.description.compareTo(it2.description);
		if (res>0) return 1;
		if (res<0) return -1;
		return 0;
	}
	
	public static int compare(Date d1, Date d2){
		if (d1 == null && d2 == null)
			return 0;
		if (d1 == null)
			return -1;
		if (d2 == null)
			return 1;
		
		int res= (d1.day + d1.month * 100 + d1.year * 10000) - (d2.day + d2.month
				* 100 + d2.year * 10000);
		
		if (res>0) return 1;
		if (res<0) return -1;
		return 0;
	}
	
	public static int compareByLender(LendItem it1, LendItem it2){
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;
		
		int res=it1.lender.compareTo(it2.lender);
		if (res>0) return 1;
		if (res<0) return -1;
		return 0;
	}

	public static int compareByOwner(LendItem it1, LendItem it2){
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;
		
		int res=it1.owner.compareTo(it2.owner);
		if (res>0) return 1;
		if (res<0) return -1;
		return 0;
	}
	
	public static boolean equals(LendItem it1, LendItem it2){
		if (it1 == null && it2 == null)
			return true;
		if (it1 == null || it2 == null)
			return false;

		return it1.description == it2.description
				&& it1.lender.equals(it2.lender) && it1.owner.equals(it2.owner)
				&& equals(it1.lendDate, it2.lendDate)
				&& equals(it1.returnDate, it2.returnDate);
	}
	
	public static boolean equals(Date d1, Date d2){
		if (d1 == null && d2 == null)
			return true;
		if (d1 == null || d2 == null)
			return false;

		return d1.day == d2.day && d1.month == d2.month && d1.year == d2.year;
	}
	
	public static LendItem scanLendItem(){
		String desc = "", lender = "", owner = "";
		
		do {
			TextIO.putf("enter a description: ");
			desc = TextIO.getln();
			TextIO.putf("enter a lender: ");
			lender = TextIO.getln();
			TextIO.putf("enter an owner: ");
			owner = TextIO.getln();

			if (desc.isEmpty()) {
				TextIO.putf("description cannot be empty!\n");
				continue;
			}
			if (lender.isEmpty()) {
				TextIO.putf("lender cannot be empty!\n");
				continue;
			}

			break;
		} while (true);

		LendItem it = new LendItem();
		it.description = desc;
		it.lender = lender;
		it.owner = owner;
		TextIO.putf("configure lend date\n");
		it.lendDate = scanDate();
		TextIO.putf("configure return date\n");
		it.returnDate = scanDate();

		return it;
	}
	
	public static Date scanDate(){
		int y, m, d;
		do {
			TextIO.putf("enter a year: ");
			y = TextIO.getlnInt();
			TextIO.putf("enter a month: (must be a digit from 1 to 12)");
			m = TextIO.getlnInt();
			TextIO.putf("enter a day: (must be a digit from 1 to 31)");
			d = TextIO.getlnInt();

			if (y < 1582) {
				TextIO.putf("year cannot be before 1582!\n");
				continue;
			}
			if (m < 1 || m > 12) {
				TextIO.putf("month must be 1-12!\n");
				continue;
			}
			if (d < 1 || d > 31) {
				TextIO.putf("day must be 1-31!\n");
				continue;
			}

			break;
		} while (true);
		Date dat = new Date();
		dat.day = d;
		dat.month = m;
		dat.year = y;
		return dat;
	}
	
	public static boolean isOut(LendItem it1){
		return it1.returnDate == null;
	}
}
