package bwi.prog2B.SS2017.wixxx.LW.util.io;

import java.io.FileWriter;
import java.io.IOException;
import bwi.prog2B.SS2017.wixxx.LW.util.MyFormatter;

/**
 * Generic class for writing objects to files in a specific format.
 * @author alessandro
 *
 */
public class MyWriter<T>{

	/**
	 * The underlying stream. 
	 * Cannot be null.
	 */
	protected FileWriter out;
	
	/**
	 * The format in which data is written to file. 
	 * Cannot be null.
	 */
	private MyFormatter<T> theFormat;

	/**
	 * Constructs a MyWriter with a specific target file and format.
	 * In case null objects are passed to this constructor IllegalArgumentException is thrown.
	 * 
	 * @param file
	 * @param theFormat
	 */
	public MyWriter(FileWriter file, MyFormatter<T> theFormat){
		if (file == null) throw new IllegalArgumentException("expected non-null FileWriter");
		if (theFormat == null) throw new IllegalArgumentException("expected non-null MyFormatter");
		out = file;
		this.theFormat = theFormat;
	}
	
	/**
	 * Writes a single object to the underlying file.
	 * The object passed to this method is written to file in the format of this MyWriter.
	 * A newline character is appended at the end of data. 
	 * This method handles all IOExceptions that might occur and returns false in such a case.
	 * 
	 * @param t - the object to be written to file
	 * @return true if the object was written to file successfully, false otherwise.
	 */
	public final boolean put(T t){
		try {
			out.write(theFormat.format(t) + "\n");
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * Closes the underlying stream.
	 * All exceptions are ducked.
	 * @throws IOException
	 */
	public void close() throws IOException{
		out.close();
	}

	
}
