

public class Position {
	public int x;
	public int y;
	
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX(Position p){
		return p.x;
	}
	
	public int getY(Position p){
		return p.y;
	}
	
	// Overriding equals() to compare two objects
    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Position or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Position)) {
            return false;
        }
         
        // typecast o to CoordinateCreator so that we can compare data members 
        Position c = (Position) o;
         
        // Compare the data members and return accordingly 
        return Integer.compare(x, c.x) == 0
                && Integer.compare(y, c.y) == 0;
    }
	

}
