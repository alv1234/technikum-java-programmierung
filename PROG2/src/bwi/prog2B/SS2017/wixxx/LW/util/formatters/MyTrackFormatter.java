package bwi.prog2B.SS2017.wixxx.LW.util.formatters;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
/**
 * This class represents the concept of formatting a track. It has a
 * single abstract method that is to be implemented by concrete subclasses which
 * implement concrete formats of String representations of tracks.
 * 
 * 
 * @author TeM, JS
 * @ProgrammingProblem.Category abstract class
 * @ProgrammingProblem.Introduced ExerciseSheet04
 * 
 */
public abstract class MyTrackFormatter {
	/**
	 * Creates a String representation for a Track.
	 * 
	 * @param t
	 * 		the track to be formatted
	 * @return
	 * 		the formatted String representing the track
	 * @ProgrammingProblem.Aspect abstract method
	 * 
	 */
	public abstract String format(Track t);
}
