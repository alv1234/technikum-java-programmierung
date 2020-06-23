package bwi.prog2B.SS2017.wixxx.LW.container;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import bwi.prog2B.SS2017.wixxx.LW.entities.Track;
import bwi.prog2B.SS2017.wixxx.LW.util.MyMatcher;

/**
 * Represents a set of tracks and a (possibly empty) subset of those tracks which are selected.
 * This class is a container for unique tracks. It does not accept null tracks, nor can a track which 
 * is already contained be added again.<br>
 * Additionally, it supports the notion of selection, meaning that some tracks can be selected. The selection 
 * is a subset of all tracks currently held by the container. The selection may at times be empty and it may at 
 * other times contain all tracks.<br>
 * The container provides methods to filter, sort, and retrieve the selection.<br>
 * Tracks can only be removed from this container by removing all currently selected tracks. 
 * Removing tracks therefore requires the creation of a proper selection first. The usual process 
 * of creating a selection is:
 * - select ALL tracks (by resetting the selection)
 * - (possibly repeatedly) filter the selection with a matcher. The filter is applied to the current selection!
 * - if desired, sort the selection.
 * - remove selected tracks from container OR retrieve the selection as an array of tracks.
 * 
 * @author alessandro
 *
 */
public class MyTrackContainer {

	private java.util.Set<Track> tracks = new HashSet<Track>();
	private java.util.List<Track> selection = new LinkedList<Track>();
	
	/**
	 * Creates a default MyTrackContainer.
	 * A default container has no tracks and an empty selection.
	 */
	public MyTrackContainer(){
		
	}

	/**
	 * Creates a container from an iterable object of tracks.
	 * All tracks of the argument are added to this container. Initially, all tracks are selected.
	 * 
	 * @param t - the iterable object of tracks to be added to this container.
	 */
	public MyTrackContainer(Iterable<Track> t){
		for (Track track : t){
			this.add(track);
		}
		this.reset();
	}
	
	/**
	 * Creates a container from an array of tracks.
	 * All tracks of the argument are added to this container. Initially, all tracks are selected.
	 * 
	 * @param t - the array of tracks to be added to this container.
	 */
	public MyTrackContainer(Track[] t){
		this.addAll(t);
		this.reset();
	}

	
	/**
	 * Add a single track.
	 * The argument is attempted to be added to this container. 
	 * If successfully added, it is NOT added to the selection. 
	 * Tracks already added cannot be added again. Null tracks cannot be added either.
	 * @param t
	 * @return whether the argument could be added
	 */
	public boolean add(Track t){
		if (t!= null && tracks.add(t)) return true;
		return false;
	}
	
	/**
	 * Bulk operation to add tracks.
	 * All tracks of the argument are added to this container.
	 * 
	 * @param t - the tracks to add
	 * @return the number of tracks added
	 */
	public int addAll(Track[] t){
		/*int counter = tracks.size();
		if (t!= null && Collections.addAll(tracks, t)) return tracks.size() - counter;
		return tracks.size()-counter;*/
		int counter = 0;
		for (Track track : t){
			if(this.add(track)) counter++;
		}
		return counter;
	}
	
	/**
	 * Resets the selection, thereby selecting ALL tracks in this container.
	 */
	public void reset(){
		selection.clear();
		selection.addAll(tracks);
	}
	
	/**
	 * Filters the selection.<br>
	 * Applies the filter defined by the argument to the selection, keeping only those elements that match.
	 * The filter is applied to the selection and the selection only, i.e. the selection cannot grow in 
	 * size during this operation. If all elements of a selection match the specified filter, the selection remains unchanged.
	 * 
	 * @param matcher - the filter defining which of the tracks of the selection to keep.
	 * @return the number of elements removed from the selection during this operation.
	 */
	public int filter(MyMatcher<Track> matcher){
		int counter = 0;
		for (Iterator<Track> it = selection.iterator(); it.hasNext();)
			if (!matcher.matches(it.next())){
				it.remove();
				counter++;
			}
		return counter;
	}
	
	/**
	 * Sorts the selection of tracks of this container.
	 * The currently selected tracks are sorted in the sense defined by the first argument. 
	 * The second argument controls the scheme (ascending/descending order).
	 * 
	 * @param theComp - the comparator defining the sorting order
	 * @param asc - the sorting scheme. true stands for ascending (from smallest to highest element) false for descending.
	 */
	public void sort(java.util.Comparator<Track> theComp, boolean asc){
		Collections.sort(selection, theComp);
		if (asc == false) Collections.reverse(selection);
	}
	
	/**
	 * Removes the selected tracks from this container.
	 * All currently selected tracks are removed from this container. 
	 * After this operation all remaining tracks are selected (the selection is reset).
	 * 
	 * @return the number of removed tracks
	 */
	public int remove(){
		int counter = 0;
		for (Track t : selection){
			tracks.remove(t);
			counter++;
		}
		this.reset();
		return counter;
	}

	/**
	 * The number of tracks currently held by this container.
	 * Note: this is not the size of the selection.
	 * 
	 * @return the number of tracks
	 */
	public int size(){
		return this.tracks.size();
	}
	
	/**
	 * Gets the selected tacks.
	 * The currently selected tracks of this container are returned as an array of tracks. 
	 * The tracks are returned in their current order.
	 * If the selection is empty an array of size 0 is returned.
	 * 
	 * @return the selected tracks.
	 */
	public Track[] selection(){
		return selection.toArray(new Track[selection.size()]);
	}

}
