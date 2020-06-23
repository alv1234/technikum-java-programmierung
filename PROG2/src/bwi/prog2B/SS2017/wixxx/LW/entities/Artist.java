package bwi.prog2B.SS2017.wixxx.LW.entities;

public class Artist implements Comparable<Artist>{

	private String name;

	public Artist() {
		name = "unknown";
	}

	public Artist(Artist a) {
		this();
		if(a != null) {
			//http://stackoverflow.com/questions/16354728/why-have-class-level-access-modifiers-instead-of-object-level
			setName(a.name);
		}
	}

	public Artist(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null && !name.trim().isEmpty()) {
			this.name = name;
		}
	}
	
	@Override
	public String toString() {
		// unknown is already set in 
		return name;
	}

	@Override
	public int compareTo(Artist o) {
		return name.compareTo(o.getName());
	}
}
