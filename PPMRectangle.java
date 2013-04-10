
public class PPMRectangle extends PPMFigure {

	private int rectangleHeight;
	private int rectangleWidth;
	private int thickness;
	private int startRow;
	private int startColumn;

	
	/**
	 * @return the rectangleHeight
	 */
	public int getRectangleHeight() {
		return rectangleHeight;
	}

	/**
	 * @param rectangleHeight the rectangleHeight to set
	 */
	public void setRectangleHeight(int rectangleHeight) {
		this.rectangleHeight = rectangleHeight;
	}

	/**
	 * @return the rectangleWidth
	 */
	public int getRectangleWidth() {
		return rectangleWidth;
	}

	/**
	 * @param rectangleWidth the rectangleWidth to set
	 */
	public void setRectangleWidth(int rectangleWidth) {
		this.rectangleWidth = rectangleWidth;
	}

	/**
	 * @return the thickness
	 */
	public int getThickness() {
		return thickness;
	}

	/**
	 * @param thickness the thickness to set
	 */
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the startColumn
	 */
	public int getStartColumn() {
		return startColumn;
	}

	/**
	 * @param startColumn the startColumn to set
	 */
	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}

	public PPMRectangle() {
		super();
		setRectangleHeight(1);
		setRectangleWidth(1);
		setThickness(1);
		setStartRow(0);
		setStartColumn(0);
	}

	public PPMRectangle(PPMRectangle image) {
		super(image);
		setRectangleHeight(image.getRectangleHeight());
		setRectangleWidth(image.getRectangleWidth());
		setThickness(image.getThickness());
		setStartRow(image.getStartRow());
		setStartColumn(image.getStartColumn());
	}


	public PPMRectangle(int width, int height, String color,int rectangleHeight, int rectangleWidth, int thickness, int startRow, int startColumn) {
		super(width, height, color);
		setRectangleHeight(rectangleHeight);
		setRectangleWidth(rectangleWidth);
		setThickness(thickness);
		setStartRow(startRow);
		setStartColumn(startColumn);
		Pixel newcolor =colorStringtoPixel(color.trim());
	
		int M = rectangleHeight;
		int N = rectangleWidth;
		for(int r=0;r<M;r++){
			for(int w=0;w<thickness;w++){
				for(int c=0;c<=N;c++){
					if(r==(0+w)||(r==(M-1-w))){
						getPixelArray()[r+startRow][c+startColumn].setPixel(newcolor);
						}
					}
				getPixelArray()[r+startRow][startColumn+w].setPixel(newcolor);
				getPixelArray()[r+startRow][startColumn+N-w].setPixel(newcolor);
				}}
		}

	@Override
	public void fillIn(String color) {
		int[] topleft= new int[2];
		int[] bottomright= new int[2];
		topleft[0]=getStartRow()+getThickness();
		topleft[1]=getStartColumn()+getThickness();
		bottomright[0]=getStartRow()+getRectangleHeight()-getThickness();
		bottomright[1]=getStartColumn()+getRectangleWidth()-getThickness();
		
		Pixel newcolor =colorStringtoPixel(color.trim());
			
		int M = bottomright[0];
		int N = bottomright[1];
		for(int r=topleft[0];r<M;r++){
			for(int c=topleft[1];c<=N;c++){
				getPixelArray()[r][c].setPixel(newcolor);
				getPixelArray()[r][c].setPixel(newcolor);
				}}

	}

	@Override
	public boolean doubleIt() {
		if(((getStartColumn()+(getRectangleWidth()+getRectangleWidth())>getPixelArray()[0].length))||((getStartRow()+2*getRectangleHeight())>getPixelArray().length)){
			return false;
			}else{
				
				setRectangleWidth(getRectangleWidth()*2);
				setRectangleHeight(getRectangleHeight()*2);
				
				PPMRectangle newrec = new PPMRectangle(getWidth(),getHeight(),getColor(),getRectangleHeight(),getRectangleWidth(),getThickness(),getStartRow(),getStartColumn());
			if(getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getRed()==255
					&&getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getBlue()==255
					&&getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getGreen()==255){
				setPixelArray(newrec.getPixelArray());
				return true;
				
			}else{
				
				String fillCol =
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getRed()+" "+
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getBlue()+" "+
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getGreen();

				newrec.fillIn(fillCol);
				setPixelArray(newrec.getPixelArray());
				return true;
			}
			}
	}

	@Override
	public boolean rotate90() {
		
		if((getStartColumn()+getRectangleWidth()>getPixelArray().length)||(getStartColumn()-getRectangleHeight()<0)){
			return false;
		} else{
//			setStartRow(getStartRow()-getRectangleWidth());
			setStartColumn(getStartColumn()-getRectangleHeight());
			setRectangleWidth(rectangleHeight);
			setRectangleHeight(rectangleWidth);
			PPMRectangle newrec = new PPMRectangle(getWidth(),getHeight(),getColor(),getRectangleHeight(),getRectangleWidth(),getThickness(),getStartRow(),getStartColumn());
			if(getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getRed()==255
					&&getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getBlue()==255
					&&getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getGreen()==255){
				setPixelArray(newrec.getPixelArray());
				return true;
				
			}else{
				String fillCol =
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getRed()+" "+
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getBlue()+" "+
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getGreen();
				newrec.fillIn(fillCol);
				setPixelArray(newrec.getPixelArray());
				return true;
			}
			}



	}

	@Override
	public boolean shiftRight(int dist) {
		if(getStartColumn()+getRectangleWidth()+dist>getPixelArray()[0].length-1){
			return false;
		} else{
			setStartColumn(getStartColumn()+dist);
			PPMRectangle newrec = new PPMRectangle(getWidth(),getHeight(),getColor(),getRectangleHeight(),getRectangleWidth(),getThickness(),getStartRow(),getStartColumn());
			if(getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getRed()==255
					&&getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getBlue()==255
					&&getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getGreen()==255){
				setPixelArray(newrec.getPixelArray());
				return true;
				
			}else{
				String fillCol =
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getRed()+" "+
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getBlue()+" "+
				getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getGreen();
				newrec.fillIn(fillCol);
				setPixelArray(newrec.getPixelArray());
				return true;
			}
			}
		}

	@Override
	public boolean shiftDown(int dist) {
		if(getStartRow()+getRectangleHeight()+dist>getPixelArray().length){
			return false;
		} else{
			setStartRow(getStartRow()+dist);
			PPMRectangle newrec = new PPMRectangle(getWidth(),getHeight(),getColor(),getRectangleHeight(),getRectangleWidth(),getThickness(),getStartRow(),getStartColumn());
			if(getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getRed()==255
				&&getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getBlue()==255
				&&getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getGreen()==255){
			setPixelArray(newrec.getPixelArray());
			return true;
			
		}else{
			String fillCol =
			getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getRed()+" "+
			getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getBlue()+" "+
			getPixelArray()[getStartRow()+getThickness()+1][getStartColumn()+getThickness()+1].getGreen();
			newrec.fillIn(fillCol);
			setPixelArray(newrec.getPixelArray());
			return true;
		
		}
		}
	}

}
