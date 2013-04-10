/**
 * @author Daniel Bell
 *
 */
public class Pixel {
	//the values of the three colors that produces the pixel's color
	//alpha is for future expansion to include alpha values
	private int red;
	private int green;
	private int blue;
	private int alpha;
	
	/**
	 * @return red int value
	 */
	public int getRed() {
		return red;
	}
	/**
	 * @param red , the red value to set
	 */
	public void setRed(int red) {
		if (red<256 & red>=0) this.red = red; 
		if (red>255) this.red=255;
		if (red<0) this.red=0;
	}
	/**
	 * 
	 * @param inP , object of class Pixel
	 */
	public void setPixel(Pixel inP)
	{
		this.red = inP.getRed();
		this.green = inP.getGreen();
		this.blue = inP.getBlue();
		this.alpha = inP.getAlpha();
	}
	/**
	 * @return green int value
	 */
	public int getGreen() {
		return green;
	}
	/**
	 * @param green , the green value to set
	 */
	public void setGreen(int green) {
		if (green<256 & green>=0) this.green = green;
		if (green>255) this.green=255;
		if (green<0) this.green=0;
	}
	/**
	 * @return blue int value
	 */
	public int getBlue() {
		return blue;
	}
	/**
	 * @param blue , the blue value to set
	 */
	public void setBlue(int blue) {
		if (blue<256 & blue>=0) this.blue = blue;
		if (blue>255) this.blue=255;
		if (blue<0) this.blue=0;
	}
	/**
	 * @return alpha int value
	 */
	public int getAlpha() {
		return alpha;
	}
	/**
	 * @param alpha , the alpha value to set
	 */
	public void setAlpha(int alpha) {
		if (alpha<256 & alpha>=0) this.alpha = alpha;
		if (alpha>255) this.alpha=255;
		if (alpha<0) this.alpha=0;
	}
/**
 * default constructor
 * sets the values of the pixel to 0
 */
	public Pixel(){
		setRed(0);
		setGreen(0);
		setBlue(0);
		setAlpha(0);
	}
	
	//a constructor that takes the three color numbers of type int as argument 
	public Pixel(int red, int green, int blue){
		setRed(red);
		setGreen(green);
		setBlue(blue);
	}
	
	//a copy constructor that does deep copying from input pixel to new pixel. 
	public Pixel(Pixel p){
		setRed(p.red);
		setGreen(p.green);
		setBlue(p.blue);
	}


}
