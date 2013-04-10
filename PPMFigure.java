import java.io.*;
import java.util.*;
import java.awt.*;
import java.lang.*;


/**
 * @author danielbell
 *
 */

public abstract class PPMFigure {
	private int width;
	private int height;
	private Pixel[][] image;
	private String color;

	

/*
PPMFigure(): Is the default constructor. Initializes all the instance variables to their zero. 
It initializes the Pixel[][] to white pixels.
*/
	public PPMFigure(){
		setWidth(0);
		setHeight(0);
		setColor("255 255 255");
		setPixelArray();
		
	}

/*
PPMFigure(PPMFigure image): Is the copy constructor which needs to copy one image to another. 
Make sure you do “deep copying”.
 */

	public PPMFigure(PPMFigure image){
		setWidth(image.width);
		setHeight(image.height);
		setPixelArray(image.image);
	}

/*
PPMFigure(int width, int height, String color): it initializes the dimensions using width and height, and the color of the figure using the parameter color. 
It initializes all the pixels to white.	
 */
	
	public PPMFigure(int width, int height, String color){
		setWidth(width);
		setHeight(height);
		String tempcolor;
		tempcolor=color.trim();
		color=tempcolor;
		if (color.equalsIgnoreCase("red")||color.equalsIgnoreCase("green")||color.equalsIgnoreCase("blue")||color.equalsIgnoreCase("black")||color.equalsIgnoreCase("white")){
			if (color.equalsIgnoreCase("red")){setColor("255 0 0");}
			else if(color.equalsIgnoreCase("green")){setColor("0 255 0");}
			else if(color.equalsIgnoreCase("blue")){setColor("0 0 255");}
			else if(color.equalsIgnoreCase("black")){setColor("0 0 0");}
			else {setColor("255 255 255");}
		}else if (Character.isDigit(color.charAt(0))){
			setColor(color);
		}

		setPixelArray(height, width);

	}

	
	/**
	 * @return height  of image
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * @param height , of image
	 */
	public void setHeight(int height){
		this.height=height;
	}
	
	/**
	 * @return width , of image
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * @param width , of image
	 */
	public void setWidth(int width){
		this.width=width;
	}

	/**
	 * @return image , the Pixel matrix of the image
	 */
	public Pixel[][] getPixelArray(){
		return image;
	}
	
	/**
	 * @param image , of type Pixel[][]
	 */
	public void setPixelArray(Pixel[][] image){
		int M = image.length;
		int N = image[0].length;
		this.image= new Pixel[M][N];
		for(int i = 0;i < M;i++){
			for(int j = 0;j < N;j++){
				this.image[j][i] = new Pixel();
			}
		}

		for(int r=0;r<M;r++){
			for(int c=0;c<N;c++){
				this.image[r][c].setPixel(image[r][c]);
			}
		}
	}
	
	/**
	 * @param hight
	 * @param width
	 * generates default array with dimentions height and width
	 */
	public void setPixelArray(int hight, int width){
		this.image = new Pixel[hight][width];
		int M = image.length;
		int N = image[0].length;

		for(int i = 0;i < M;i++){
			for(int j = 0;j < N;j++){
				this.image[j][i] = new Pixel();
			}
		}
		Pixel p= new Pixel(255,255,255);
		for(int r=0;r<M;r++){
			for(int c=0;c<N;c++){
				this.image[r][c].setPixel(p);
			}
		}
	}

	public void setPixelArray(){
		this.image = new Pixel[this.height][width];
		Pixel p= new Pixel(255,255,255);
		for(int r=0;r<this.height;r++){
			for(int c=0;c<width;c++){
				this.image[r][c].setPixel(p);
			}
		}
	}
	
	/**
	 * @param colorNum , sets what the max value is for the RGB elements of the pixels of the image
	 */
	public void setColor(String color){
		String tempcolor;
		tempcolor=color.trim();
		color=tempcolor;
		if (color.equalsIgnoreCase("red")||color.equalsIgnoreCase("green")||color.equalsIgnoreCase("blue")||color.equalsIgnoreCase("black")||color.equalsIgnoreCase("white")){
			if (color.equalsIgnoreCase("red")){this.color="255 0 0";}
			else if(color.equalsIgnoreCase("green")){this.color="0 255 0";}
			else if(color.equalsIgnoreCase("blue")){this.color="0 0 255";}
			else if(color.equalsIgnoreCase("black")){this.color="0 0 0";}
			else {setColor("255 255 255");}
		}else if (Character.isDigit(color.charAt(0))){
			this.color=color;
		}

	}
	
	/**
	 * @return maxColor , returns what the value is for the RGB elements of the pixels of the image
	 */
	public String getColor(){
		return this.color;
	}



	/**
	 * @param filename , it writes out the PPMFigure to an image file of type PPM
	 * @throws IOException
	 * @exception possible from writing the file
	 */
	public void writeImage(String filename){
		PrintWriter outFile=null;
		try{
			outFile=new PrintWriter(new FileOutputStream(filename));
		}catch(IOException e){
			System.out.println("error creating image: " + filename);
		}
		try{
		outFile.println("P3");
		outFile.println(getWidth()+" "+getHeight());
		outFile.println(255);
		for(int i=0;i<getPixelArray().length;i++){
			for(int j=0;j<this.getPixelArray()[i].length;j++){
				outFile.print(getPixelArray()[i][j].getRed()+" "+getPixelArray()[i][j].getGreen()+" "+getPixelArray()[i][j].getBlue()+" ");
				if(j+1==getPixelArray()[i].length){
					outFile.print("\n");
				}
			}
		}}finally{
			outFile.close();
			
		}
	}

	
	public abstract void fillIn(String color); 
	public abstract boolean doubleIt();
	public abstract boolean rotate90();
	public abstract boolean shiftRight(int dist);
	public abstract boolean shiftDown(int dist);


	public void changeColor(String color){
		String changecolor;
		changecolor=color.trim();
		color=changecolor;
		Pixel newcolor = new Pixel();
		if (color.equalsIgnoreCase("red")||color.equalsIgnoreCase("green")||color.equalsIgnoreCase("blue")||color.equalsIgnoreCase("black")||color.equalsIgnoreCase("white")){
			if (color.equalsIgnoreCase("red")){
				newcolor.setRed(255);
				newcolor.setGreen(0);
				newcolor.setBlue(0);}
			else if(color.equalsIgnoreCase("green")){
				newcolor.setRed(0);
				newcolor.setGreen(255);
				newcolor.setBlue(0);}
			else if(color.equalsIgnoreCase("blue")){
				newcolor.setRed(0);
				newcolor.setGreen(0);
				newcolor.setBlue(255);}
			else if(color.equalsIgnoreCase("black")){
				newcolor.setRed(0);
				newcolor.setGreen(0);
				newcolor.setBlue(0);}
		}else if (Character.isDigit(color.charAt(0))){
			String delims = "[ ,]+";
			String[] tokens = color.split(delims);
			newcolor.setRed(Integer.parseInt(tokens[0]));
			newcolor.setGreen(Integer.parseInt(tokens[1]));
			newcolor.setBlue(Integer.parseInt(tokens[2]));
		}
		
		Pixel oldcolor = new Pixel();
		if (getColor().equalsIgnoreCase("red")||getColor().equalsIgnoreCase("green")||getColor().equalsIgnoreCase("blue")||getColor().equalsIgnoreCase("black")||getColor().equalsIgnoreCase("white")){
			if (getColor().equalsIgnoreCase("red")){
				oldcolor.setRed(255);
				oldcolor.setGreen(0);
				oldcolor.setBlue(0);}
			else if(getColor().equalsIgnoreCase("green")){
				oldcolor.setRed(0);
				oldcolor.setGreen(255);
				oldcolor.setBlue(0);}
			else if(getColor().equalsIgnoreCase("blue")){
				oldcolor.setRed(0);
				oldcolor.setGreen(0);
				oldcolor.setBlue(255);}
			else if(getColor().equalsIgnoreCase("black")){
				oldcolor.setRed(0);
				oldcolor.setGreen(0);
				oldcolor.setBlue(0);}
		}else if (Character.isDigit(getColor().charAt(0))){
			String delims = "[ ,]+";
			String[] tokens = getColor().split(delims);
			oldcolor.setRed(Integer.parseInt(tokens[0]));
			oldcolor.setGreen(Integer.parseInt(tokens[1]));
			oldcolor.setBlue(Integer.parseInt(tokens[2]));
		}

		Pixel[][] tempImage=new Pixel[getHeight()][getWidth()];
		tempImage=getPixelArray();
		int M = tempImage.length;
		int N = tempImage[0].length;

		for(int r=0;r<M;r++){
			for(int c=0;c<N;c++){	
				if(tempImage[r][c].getBlue()==oldcolor.getBlue()&&
						tempImage[r][c].getRed()==oldcolor.getRed()&&
						tempImage[r][c].getGreen()==oldcolor.getGreen()
						){
					tempImage[r][c].setBlue(newcolor.getBlue());
					tempImage[r][c].setRed(newcolor.getRed());
					tempImage[r][c].setGreen(newcolor.getGreen());
				}
			}
		}
		setPixelArray(tempImage);

		
		

	}
	
	public Pixel colorStringtoPixel(String color){
		String changecolor;
		changecolor=color.trim();
		color=changecolor;
		Pixel newcolor = new Pixel();
		if (color.equalsIgnoreCase("red")||color.equalsIgnoreCase("green")||color.equalsIgnoreCase("blue")||color.equalsIgnoreCase("black")||color.equalsIgnoreCase("white")){
			if (color.equalsIgnoreCase("red")){
				newcolor.setRed(255);
				newcolor.setGreen(0);
				newcolor.setBlue(0);}
			else if(color.equalsIgnoreCase("green")){
				newcolor.setRed(0);
				newcolor.setGreen(255);
				newcolor.setBlue(0);}
			else if(color.equalsIgnoreCase("blue")){
				newcolor.setRed(0);
				newcolor.setGreen(0);
				newcolor.setBlue(255);}
			else if(color.equalsIgnoreCase("black")){
				newcolor.setRed(0);
				newcolor.setGreen(0);
				newcolor.setBlue(0);}
		}else if (Character.isDigit(color.charAt(0))){
			String delims = "[ ,]+";
			String[] tokens = color.split(delims);
			newcolor.setRed(Integer.parseInt(tokens[0]));
			newcolor.setGreen(Integer.parseInt(tokens[1]));
			newcolor.setBlue(Integer.parseInt(tokens[2]));
		}
		return newcolor;
		
	}




}

