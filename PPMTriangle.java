
public class PPMTriangle extends PPMFigure {


	private int altitude;
	private int vertexRow;
	private int vertexColumn;
	
	/**
	 * @return the altitude
	 */
	public int getAltitude() {
		return altitude;
	}

	/**
	 * @param altitude the altitude to set
	 */
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	/**
	 * @return the vertexRow
	 */
	public int getVertexRow() {
		return vertexRow;
	}

	/**
	 * @param vertexRow the vertexRow to set
	 */
	public void setVertexRow(int vertexRow) {
		this.vertexRow = vertexRow;
	}

	/**
	 * @return the vertexColumn
	 */
	public int getVertexColumn() {
		return vertexColumn;
	}

	/**
	 * @param vertexColumn the vertexColumn to set
	 */
	public void setVertexColumn(int vertexColumn) {
		this.vertexColumn = vertexColumn;
	}

	public PPMTriangle() {
		super();
		setAltitude(1);
		setVertexRow(1);
		setVertexColumn(1);
	}

	public PPMTriangle(PPMFigure image) {
		super(image);
		setAltitude(1);
		setVertexRow(1);
		setVertexColumn(1);
	}


	PPMTriangle(int width, int height, String color, int altitude, int vertexRow, int vertexColumn) {
		super(width, height, color.trim());
		if (getVertexColumn() - ((getAltitude()*2-1) / 2)<-1){
			System.exit(-1);
		}
		setAltitude(altitude);
		setVertexRow(vertexRow);
		setVertexColumn(vertexColumn);
		Pixel newcolor =colorStringtoPixel(color.trim());

		while((getVertexColumn()+getAltitude()*2)>getWidth()-getVertexColumn()-1)
		{setVertexColumn(getVertexColumn()-1);}
		while(getAltitude()+2>getHeight()-getVertexRow()){
			setVertexRow(getVertexRow()-1);
		}
		int spaces=0;
		for (int i=1; i<getAltitude()*2; i += 2){
			spaces=1;
		    for (int k=0; k < (getVertexColumn() - (i / 2)); k++){
		        spaces++;
		    }
		    for (int j=0; j<i; j++){
		    	getPixelArray()[getVertexRow()+i/2][spaces].setBlue(newcolor.getBlue());
		    	getPixelArray()[getVertexRow()+i/2][spaces+i-1].setBlue(newcolor.getBlue());
		    	getPixelArray()[getVertexRow()+i/2][spaces].setRed(newcolor.getRed());
		    	getPixelArray()[getVertexRow()+i/2][spaces+i-1].setRed(newcolor.getRed());
		    	getPixelArray()[getVertexRow()+i/2][spaces].setGreen(newcolor.getGreen());
		    	getPixelArray()[getVertexRow()+i/2][spaces+i-1].setGreen(newcolor.getGreen());

		    }
		}
		for(int k=0;k<getAltitude()*2-1;k++){
			int i=getAltitude()*2-1;
	    	getPixelArray()[getVertexRow()+i/2][spaces+k].setBlue(newcolor.getBlue());
	    	getPixelArray()[getVertexRow()+i/2][spaces+k].setRed(newcolor.getRed());
	    	getPixelArray()[getVertexRow()+i/2][spaces+k].setGreen(newcolor.getGreen());
	    	}
	}

	@Override
	public void fillIn(String color) {
		Pixel newcolor =colorStringtoPixel(color.trim());
		boolean area=false;
		Pixel oldcolor =colorStringtoPixel(getColor());
		int x1=0, x2=0, x3=0;
		for(int i=0;i<getHeight();i++)
		{
			for(int j=0;j<getWidth();j++){
				for(int k=0;k<getWidth();k++){
				if(getPixelArray()[i][k].getBlue()==oldcolor.getBlue()&&
				                      getPixelArray()[i][k].getGreen()==oldcolor.getGreen()&&
				                      getPixelArray()[i][k].getRed()==oldcolor.getRed()){
					if(k<=getVertexColumn()){
						x1=k;
					}else if(k>getVertexColumn()){
						if(k<x2){
							x3=k;
						}
						x2=k;
						}
					}
				}
				if((x2-x1>1&&x1!=0)&&i<getVertexRow()+getAltitude()){
				for(int l=x1+1;l<(x2);l++)
				{
					getPixelArray()[i][l].setPixel(newcolor);
				}}if((x3-x1>1&&x1!=0)&&i<getVertexRow()+2*getAltitude()){
					for(int l=x1+1;l<(x3);l++)
					{
						getPixelArray()[i][l].setPixel(newcolor);
					}	
				}
		}}
	}

	@Override
	public boolean doubleIt() {
		setAltitude(getAltitude()*2);
		if ((getAltitude()>getVertexColumn())||(getAltitude()>getWidth()-getVertexColumn())||((getAltitude())>(getHeight()-getVertexRow()))){
			System.out.println("("+getAltitude()+">"+getVertexColumn()+")||("+getAltitude()+">"+getWidth()+"-"+getVertexColumn()+")||(("+getAltitude()+")>("+getHeight()+"-"+getVertexRow()+"))");
			return false;
		}else{
			
			PPMTriangle newTri = new PPMTriangle(getWidth(),getHeight(),getColor(),getAltitude(),getVertexRow(),getVertexColumn());
			if((getAltitude()<3)||(getPixelArray()[getVertexRow()+3][getVertexColumn()].getRed()==255
					&&getPixelArray()[getVertexRow()+3][getVertexColumn()].getBlue()==255
					&&getPixelArray()[getVertexRow()+3][getVertexColumn()].getGreen()==255)){
				setPixelArray(newTri.getPixelArray());
				return true;
				
			}else{
				
				String fillCol =
				getPixelArray()[getVertexRow()+2][getVertexColumn()].getRed()+" "+
				getPixelArray()[getVertexRow()+2][getVertexColumn()].getBlue()+" "+
				getPixelArray()[getVertexRow()+2][getVertexColumn()].getGreen();

				newTri.fillIn(fillCol);
				setPixelArray(newTri.getPixelArray());
				return true;
		}
		}
	}

	@Override
	public boolean rotate90() {
		if((getVertexColumn()-getAltitude()<0)||(getAltitude()>getVertexRow())||(getVertexRow()+getAltitude()>getHeight()-getVertexRow())){
			return false;
		}else{
			Pixel newcolor=colorStringtoPixel(getColor());

			PPMTriangle newShape = new PPMTriangle();
			newShape.setPixelArray(getHeight(), getWidth());

			int r=getAltitude();
			int c;
			for(int i=0;i<getAltitude();i++){
				r--;
				c=getAltitude()-2;
				for(int j=0; j<=i; j++){
					c--;
					if(j==0||j==i){
					newShape.getPixelArray()[getVertexRow()-r][getVertexColumn()-c].setBlue(newcolor.getBlue());
					newShape.getPixelArray()[getVertexRow()-r][getVertexColumn()-c].setRed(newcolor.getRed());
					newShape.getPixelArray()[getVertexRow()-r][getVertexColumn()-c].setGreen(newcolor.getGreen());
					}
				}
			}
			for(int i=1;i<getAltitude();i++){
				r++;
				c=getAltitude()-2;
				for(int j=i; j<getAltitude(); j++){
					c--;
					if(j==i||j==getAltitude()-1){
						newShape.getPixelArray()[getVertexRow()+r][getVertexColumn()-c].setBlue(newcolor.getBlue());
						newShape.getPixelArray()[getVertexRow()+r][getVertexColumn()-c].setRed(newcolor.getRed());
						newShape.getPixelArray()[getVertexRow()+r][getVertexColumn()-c].setGreen(newcolor.getGreen());
						}
				}
			}	
			setPixelArray(newShape.getPixelArray());
			setVertexRow(getVertexRow()-getAltitude()+2);
			setVertexColumn(getVertexColumn()-getAltitude()+3);
			return true;
			}

	}

	@Override
	public boolean shiftRight(int dist) {
		if((getAltitude()>getWidth()-getVertexColumn())){
			return false;
		} else{
			setVertexColumn(getVertexColumn()+dist);
			PPMTriangle newTri = new PPMTriangle(getWidth(),getHeight(),getColor(),getAltitude(),getVertexRow(),getVertexColumn());
			if((getAltitude()<3)||(getPixelArray()[getVertexRow()+1][getVertexColumn()].getRed()==255
					&&getPixelArray()[getVertexRow()+1][getVertexColumn()].getBlue()==255
					&&getPixelArray()[getVertexRow()+1][getVertexColumn()].getGreen()==255)){
				setPixelArray(newTri.getPixelArray());
				return true;
				
			}else{
				String fillCol =
				getPixelArray()[getVertexRow()+2][getVertexColumn()+1].getRed()+" "+
				getPixelArray()[getVertexRow()+2][getVertexColumn()+1].getBlue()+" "+
				getPixelArray()[getVertexRow()+2][getVertexColumn()+1].getGreen();
				newTri.fillIn(fillCol);
				setPixelArray(newTri.getPixelArray());
				return true;
			}
			}
	}

	@Override
	public boolean shiftDown(int dist) {
		if(getVertexRow()+getAltitude()+dist>getPixelArray().length){
			return false;
		} else{
			setVertexRow(getVertexRow()+dist);
			PPMTriangle newTri = new PPMTriangle(getWidth(),getHeight(),getColor(),getAltitude(),getVertexRow(),getVertexColumn());
			if((getAltitude()<4)||(getPixelArray()[getVertexRow()+4][getVertexColumn()].getRed()==255
				&&getPixelArray()[getVertexRow()+4][getVertexColumn()].getBlue()==255
				&&getPixelArray()[getVertexRow()+4][getVertexColumn()].getGreen()==255)){
			setPixelArray(newTri.getPixelArray());
			return true;
			
		}else{
			String fillCol =
			getPixelArray()[getVertexRow()+3][getVertexColumn()].getRed()+" "+
			getPixelArray()[getVertexRow()+3][getVertexColumn()].getBlue()+" "+
			getPixelArray()[getVertexRow()+3][getVertexColumn()].getGreen();
			newTri.fillIn(fillCol);
			setPixelArray(newTri.getPixelArray());
			return true;
		
		}
		}
	}

}
