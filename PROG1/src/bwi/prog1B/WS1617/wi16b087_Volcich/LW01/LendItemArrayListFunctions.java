package bwi.prog1B.WS1617.wixxx.LW01;

import bwi.prog.utils.TextIO;

public class LendItemArrayListFunctions {

	public static void initialisierung(LendItemArrayList list){
		
		for (int i = 0; i < 25; i++) {
			LendItem li = new LendItem();
			li.id = list.next;
			li.description = String.format("%c_description", ((int) (i
			* Math.PI * 10000)) % 15 + 'A');
			li.lender = String.format("Gustav_%02d", ((int) (i
			* Math.PI * 1000000)) % 10);
			li.lendDate = new Date();
			li.lendDate.year = 2010 - ((int) (i * Math.PI * 100)) % 100;
			li.lendDate.month = ((int) (i * Math.PI * 1000000)) % 12 + 1;
			li.lendDate.day = ((int) (i * Math.PI * 100000000)) % 28 + 1;
			LendItemArrayListFunctions.add(list, li);
			}
		
		/*LendItem p = new LendItem(), q = new LendItem(), r = new LendItem();
		p.description="X_Battlestar Galactica";
		Date date = new Date();
		date.day=date.month=1;
		date.year=2016;
		p.lendDate=date;
		p.returnDate=date;
		p.lender=p.owner="Alessandro";
		LendItemArrayListFunctions.add(list, p);
		q.description="C_Battlestar Galactica II";
		Date date1 = new Date();
		date1.day=date.month=2;
		date1.year=2017;
		q.lendDate=date1;
		q.returnDate=date1;
		q.lender=q.owner="Martin";
		LendItemArrayListFunctions.add(list, q);
		r.description="F_Battlestar Galactica 3";
		Date date3 = new Date();
		date3.day=date.month=3;
		date3.year=2018;
		r.lendDate=date3;
		r.returnDate=date3;
		r.lender=r.owner="Maria";
		LendItemArrayListFunctions.add(list, r);*/
	}
	
	public static boolean add(LendItemArrayList list, LendItem p){
		
		if (/* free slot available */list.next<list.lendItems.length){
			//on next free slot, copy item
			list.lendItems[list.next]=p;
			//next free slot index becomes ID of item
			list.lendItems[list.next].id=list.next;
			//increment occupied slots
			list.next++;
			//return true for successful adding
			return true;
		}
		//if above didn't succeed, because all slots occupied, check whether list is resizable
		if (list.resizeable==true){
			//copy array and double its length
			LendItem[] newData = new LendItem[list.lendItems.length*2];
			//copy original array items to copy
			for (int i=0;i<list.lendItems.length;i++)
				newData[i] = list.lendItems[i];
			//overwrite original with the (longer) copy array
			list.lendItems = newData;
			//add new item to longer array
			list.lendItems[list.next]=p;
			//next free slot index becomes ID of item
			list.lendItems[list.next].id=list.next;
			//increment occupied slots
			list.next++;
			//return true for successful adding
			return true;
		}
		//if none of above succeeded, return false and do nothing
		return false;
	}
	
	public static LendItem remove(LendItemArrayList list, int n){
		LendItem removedItem;
		if(/*correct index value?*/n<0 || /*exceeding index?*/n>list.next-1) return removedItem=null;
		//create a copy of the to-remove lenditem
		removedItem=list.lendItems[n];
		//shift all following objects after index position one position down; do this until one slot before 
		for (int i = n; i < list.next-1; i++) {
				list.lendItems[i] = list.lendItems[i + 1];
		}
		//decrease free slot indicator by one
		list.next--;
		list.lendItems[list.next]=null;
		//return copy of removed item
		return removedItem;
	}
	
	public static int list(LendItemArrayList list, int format){
		TextIO.putf("%s\n", LendItemFunctions.lendItemHeadings(format));
		TextIO.putf("%s\n", LendItemFunctions.lendItemSeparator(format));
		//go through the entire list and output each existing item
		for(int i=0;i<list.next;i++)
			TextIO.putf("%s\t(%02d)\n", LendItemFunctions.lendItemString(list.lendItems[i], format),i);
		TextIO.putf("%s\n", LendItemFunctions.lendItemSeparator(format));
		//check if list is empty or not
		if (list.next > 0){
		TextIO.putf("%d LendItem(s) in list, %d free.\n", list.next,list.lendItems.length-list.next);
		} else{
			TextIO.putf("List empty.\n");
		}
		return list.next;
	}
	
	public static void sort(LendItemArrayList list, int order){
		//use bubble sort
			boolean swapped;
			int i=0;
			do {
				swapped = false;
				//Schleife, die jedes Element des Arrays abklopft
				for (int j=1;j<list.next-i;j++)
					//Kontrolle, wenn erstes Element größer als zweites, Rückgabewert = 1
					if (LendItemFunctions.compare(list.lendItems[j-1], list.lendItems[j], order) > 0){
						//swap function; Weil das erste Element größer ist, wird es gegen das zweite getauscht
						LendItem tmp = new LendItem();
						tmp = list.lendItems[j-1];
						list.lendItems[j-1]=list.lendItems[j];
						list.lendItems[j]=tmp;
						swapped=true;
					}
				i++;
			} while (swapped);
	}
	
	public static LendItemArrayList filterByDescription(LendItemArrayList list,String desc){
		
		LendItemArrayList result = new LendItemArrayList();
		int j=0;
		
		for (int i=0;i<list.next;i++) {
			if (list.lendItems[i].description.contains(desc)) { //does array item contain desc string?
				result.lendItems[j] = list.lendItems[i]; //collect results in result array
				j++;
				result.next++;
			}
		}
		return result; 
	}
	
	public static int findByID(LendItemArrayList list, int id){
		
		for (int i=0;i<list.next;i++) {
			if (id == list.lendItems[i].id) {
				return i;
			}
		}
		return -1;
	}
	
}
