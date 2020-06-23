package bwi.prog2B.SS2017.wixxx.LW.util.comparators;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
/**
 * This class represents the concept of comparison of two track by title. 
 * 
 * @author TeM, JS
 * @ProgrammingProblem.Category concrete subclass
 * @ProgrammingProblem.Introduced ExerciseSheet04
 */
public class MyDurationComparator extends MyTrackComparator {
	/**
	 * Compares two tracks by duration.<br>
	 * 
	 * This comparator assumes non-null arguments.
	 * 
	 * @ProgrammingProblem.Aspect concrete method implementation
	 */
	@Override
	public int compare(Track t1, Track t2) {
		return t1.getDuration()-t2.getDuration();
	}

}
