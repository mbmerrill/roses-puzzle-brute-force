package us.doryoku.roses;

/**
 * Represents a single edge of a tile.  An edge is decorated with an illustration of a rose, of a certain size and color.
 * 
 * @author Matt Merrill
 *
 */
public class TileEdge {

	public enum Color {
		WHITE,
		RED,
		PINK, 
		YELLOW
	}
	
	public enum Size {
		BIG,
		SMALL
	}
	
	private Color color;
	private Size size;
	
	public TileEdge(Color color, Size size) {
		this.color = color;
		this.size = size;
	}
	
	public TileEdge(TileEdge te) {
		this.color = te.color;
		this.size = te.size;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Size getSize() {
		return size;
	}
	
	/**
	 * @return The first letter of the color assigned to this TileEdge, in uppercase if the size is BIG, or in lowercase if the size is SMALL
	 */
	public String toString() {
		
		String retval = "";
		
		switch (size) {
			case BIG:
				retval = color.toString().toUpperCase().substring(0, 1);
				break;
			case SMALL:
				retval = color.toString().toLowerCase().substring(0, 1);
				break;
		}
		
		return retval;
		
	}
	
	/**
	 * According to the rules for a correct solution, a TileEdge matches another TileEdge if:
	 * <p>
	 * <ul>
	 * <li>They have the same color.
	 * <li>They have a different size.
	 * </ul>
	 * 
	 * @param te
	 * @return true if a match is detected; false otherwise
	 */
	public boolean matches(final TileEdge te) {
		
		boolean retval = false;
		
		if ((color == te.color) && (size != te.size)) {
			retval = true;
		}
		
		return retval;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TileEdge other = (TileEdge) obj;
		if (color != other.color)
			return false;
		if (size != other.size)
			return false;
		return true;
	}

}
