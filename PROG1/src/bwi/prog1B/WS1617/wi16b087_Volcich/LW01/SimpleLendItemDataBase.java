package bwi.prog1B.WS1617.wixxx.LW01;

import bwi.prog.utils.TextIO;

public class SimpleLendItemDataBase {

	public static void main(String[] args) {
		/** TODO open menu and display selections
		 *1) list - prints all items currently in the list in the currently selected format (full format by default)
		 *2) add -  (interactively) scans a lend item and adds it to the list
		 *3) remove - scans an ID and removes the lend item with that ID
		 *4) sort -  sorts all items with a stable sorting algorithm. Several sorting options are available
		 *5) filter - scans a search string and lists all lend items that contain that search string in their
		 *   		  description. The result list is displayed in tabular format
		 *6) set format - When a specific format is selected, all output uses that format. Default
		 *				  format is full format. The following formatting options are available:
		 *										1) full format
		 *										2) short format
		 *										3) csv format
		 *0) quit
		 **/
		
		LendItemArrayList list = new LendItemArrayList();
		int format = 1;
		//create demo data
		LendItemArrayListFunctions.initialisierung(list);
		
		while (true) {
			//Navigationsmenü
			TextIO.putln("\nOperationscodes: \n" + "1 list \n" + "2 add \n"
					+ "3 remove \n" + "4 sort \n" + "5 filter \n"
					+ "6 set format \n" + "0 quit");
			int operation = TextIO.getlnInt();
switch (operation){
case 1:
	TextIO.putf("your choice: 1\n");
	LendItemArrayListFunctions.list(list, format);
	break;
case 2:
	TextIO.putf("your choice: 2\n");
	//ask for item which should be added
	LendItem newItem = new LendItem ();
	newItem=LendItemFunctions.scanLendItem();
	//call method and display success message
	TextIO.putf("\n%sitem added.\n",LendItemArrayListFunctions.add(list, newItem)?"1 ":"no ");
	break;
case 3:
	TextIO.putf("your choice: 3\n");
	//ask for index position
	TextIO.putf("enter ID of LendItem to be removed:");
	int n=TextIO.getInt();
	//call operation and copy result to dummy variable
	LendItem dummyItem = new LendItem();
	dummyItem = LendItemArrayListFunctions.remove(list, n);
	//display success message for operation
	if (/*check whether operation returns null*/dummyItem != null) {
		TextIO.putf("\n1 LendItem (ID=%d) removed.\n", n);
	} else{
		TextIO.putf("\nLendItem not found (ID %d).\n", n);
	}
	break;
case 4:
	TextIO.putf("your choice: 4\n");
	TextIO.putln("\navailable sort options: \n" + "1) by lend date\n" + "2) by return date\n"
			+ "3) by by lender\n" + "4) by owner\n" + "0) by description\n");
	int order = TextIO.getlnInt();
	LendItemArrayListFunctions.sort(list, order);
	LendItemArrayListFunctions.list(list, format);
	break;
case 5:
	TextIO.putf("your choice: 5\n");
	TextIO.putf("\nenter description:\n");
	String description = TextIO.getlnString();
	LendItemArrayListFunctions.list(LendItemArrayListFunctions.filterByDescription(list, description), format);
	break;
case 6:
	TextIO.putf("your choice: 6\n");
	TextIO.putf("\navailable options:\n1) full format\n2) short format\n3) csv format\n");
	format=TextIO.getlnInt();
	break;
case 0:
	return;
}
	}

}}
