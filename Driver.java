
public class Driver {
public static void main(String [] args){
	PPMTriangle image1=new PPMTriangle(800, 800, "black", 100, 300, 300);
	image1.writeImage("/Users/danielbell/Downloads/A4/image1.ppm");
	boolean helpme;

//	helpme = image1.shiftDown(200);
//	image1.writeImage("/Users/danielbell/Downloads/A4/image2.ppm");
//	System.out.println(helpme);

//	helpme = image1.shiftRight(200);
//	image1.writeImage("/Users/danielbell/Downloads/A4/image3.ppm");
//	System.out.println(helpme);
	
//	helpme = image1.doubleIt();
//	image1.writeImage("/Users/danielbell/Downloads/A4/image4.ppm");
//	System.out.println(helpme);


	helpme = image1.rotate90();
	image1.writeImage("/Users/danielbell/Downloads/A4/image5.ppm");
	System.out.println(helpme);

	image1.fillIn("blue");
	image1.writeImage("/Users/danielbell/Downloads/A4/image6.ppm");
}
}
