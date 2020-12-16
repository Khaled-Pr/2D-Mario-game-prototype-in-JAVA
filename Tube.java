import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;


//Name: Khaled Ras Guerriche
//Date: started 09/2020
//Assignment: 05

public class Tube extends Sprite {

	static BufferedImage image = null; //a member variable to reference our tube image
	Model model;

	//constructor
	Tube(int x, int y, Model m) {
		this.x = x;
		this.y = y;
		w=55;
	    h=400;
		model = m;		


		tubeImageLading();
	}
	
	//// Unmarshaling the object
	Tube (Json ob, Model m)
	{
	    x = (int)ob.getLong("x");
	    y = (int)ob.getLong("y");
		w=55;
	    h=400;
		model = m;
		tubeImageLading();
	}
	
	//we need because of the abstract class
	public void update()
	{
		
	}
	//methode toload the Tube image
	void tubeImageLading()
	 {
		if(image == null )
		{
			
		this.image = View.loadImage("tube.png");
		}
			
	 }
	

	//marshal the object
	  Json marshal()
	  {
		  Json ob = Json.newObject();
		  ob.add("x", x);
		  ob.add("y", y);
		  

		  return ob;
	  }
  




	//a method to detect if there is a tube where we click

	boolean detectTube(int mouse_x, int mouse_y) {
		if(mouse_x < x) {
			return false;
		}if(mouse_x > x + w) {
			return false;
		}
		if(mouse_y < y) {
			return false;
		}else if(mouse_y > y + h) {
			return false;
		}else {
			return true;
		}
	}
	public boolean isTube(){return true;}
	//to draw the tubes
	 public void draw(Graphics g)
	  {  
		
		 g.drawImage(image, x-model.mario.x+model.mario.marioViewLocation,y, null);
		
	  }
	
	 
	 

}

