import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;

//Name: Khaled Ras Guerriche
//Date: started 09/2020
//Assignment: 05

public class Fireball extends Sprite
{
	static BufferedImage image;
	Model model;
	double speed;
	int direction;
	int curr_direction;
	int marioToEdge; //this is the distance from mario.x to the end of the screen
	public Fireball(int x, int y, Model m)
	{	direction=1;
		marioToEdge=720;
		this.x = x;
		this.y = y;
		w = 47;
		h = 47;
		model = m;	
		vert_vel =2;
		speed =10;		
		
		loadFireball();
	}
	
	
	void loadFireball()
	{
		if(image == null)
		{
			image = View.loadImage("fireball.png");
		}
	}
	
	public  void update()
	{
		vert_vel += 1.7;
		y += vert_vel;
		
		// the ground level
		if(y>(400-h))
		{
			vert_vel = -20.0;
			y=400-h;
				
		 }
		if(y<0)
		{
			y=0;
			vert_vel = 7;
			
		}
		this.x+=speed*direction;
		//to remove the fireball after it goes off the screen	
		//first looping throught the sprites
		for(int i=0; i<model.sprites.size();i++) 
			
		{  
		//if the sprite is fireball
		 if(model.sprites.get(i).isFireball())
		  {
             //if the fireball.x isoff screen, do: remove it
			if ( this.x==(model.mario.x+marioToEdge))
			  {
				  
				  model.sprites.remove(i);
				  

			  }
		  }
		}


	}
	
	//to draw the fireball image
	public void draw(Graphics g)
	{
		g.drawImage(image,x-model.mario.x+model.mario.marioViewLocation,y, null);

	}
	public boolean isFireball(){return true;}

}