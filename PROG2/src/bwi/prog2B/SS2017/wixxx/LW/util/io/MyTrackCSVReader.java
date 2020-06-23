package bwi.prog2B.SS2017.wixxx.LW.util.io;

import java.io.BufferedReader;
import java.io.IOException;

import bwi.prog2B.SS2017.wixxx.LW.entities.*;

public class MyTrackCSVReader extends MyReader<Track>{

	/**
	 * these static fields define which information is found at which position in a line of the CSV. 
	 * You may find them useful to target a specific information when creating a new Track
	 */
	private static final int TITLE = 0, WRITER = 1, PERFORMER = 2, DURATION = 3, YEAR = 4;
	
	public MyTrackCSVReader(BufferedReader in) {
		super(in);
	}

	/**
	 * reads the current line of the BufferedReader, displays it as is at the console 
	 * and returns the contained Track of this line <br>
	 * 
	 * displays "Error reading." in case of an IOException<br> 
	 * displays "Error parsing" in case of any other exception
	 * 
	 * @return Track in case a new Track was created successfully, null otherwise
	 */
	@Override
	public Track get() {
		String input;
		try {
			input = this.in.readLine();
			if (input == null) return null;
			System.out.println(input);
			String[] temp = input.split(",");
			//for (int i=0;i<temp.length;i++) if (temp[i].isEmpty() || temp[i] == null) return null;
			Track newTrack = new Track(temp[0].trim());
			newTrack.setWriter(new Artist(temp[1].trim()));
			newTrack.setPerformer(new Artist(temp[2].trim()));
			newTrack.setDuration(Integer.parseInt(temp[3].trim()));
			newTrack.setYear(Integer.parseInt(temp[4].trim()));
			return newTrack;
		} catch (IOException e) {
			System.out.println("Error reading.");
		} catch (Exception e){
			System.out.println("Error parsing.");
		}
		return null;
	}

}
