
public class PPMSquare extends PPMRectangle {
	
	/*
	 * the third constructor (with the same syntax) 
	 * should additionally check to see if the rectangleHeight and rectangleWidth are equal or not. 
	 * If not, it should exit the program.
	 */

	public PPMSquare() {
		super();
	}

	public PPMSquare(PPMRectangle image) {
		super(image);
	}

	public PPMSquare(int width, int height, String color, int rectangleHeight,
			int rectangleWidth, int thickness, int startRow, int startColumn) {
		super(width, height, color, rectangleHeight, rectangleWidth, thickness, startRow, startColumn);
		if(rectangleHeight!=rectangleWidth){
			System.exit(-1);
		}

		
	}

}
