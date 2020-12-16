
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;

//Name: Khaled Ras Guerriche
//Date: started 10/2020
//Assignment: 05

class Mario extends Sprite
{ 

	
    
	//cordination for previews mario cordination 
	int pre_x;
	int pre_y;
	boolean flipMario;
	int marioViewLocation=180;// indicates where mario is in the view
	Model model;
	
	int frameCount;
	
    static BufferedImage[] mario_images; // a member variable to reference our mario image
	                             // for mario we are using array bkoz we have more than one image


	//Constructor
	public Mario(int x, int y, Model m)
	{ 	model =m;
		this.x = x;
		this.y = y;
		image = 0; 
		
		h = 95;
		w = 60;
		vert_vel=-2.0; 
		frameCount =0;
	if(mario_images ==null)
		{
			
		    //to load our image (just loading not enough to get the image in screen)

		    this.mario_images = new BufferedImage[5];			
	        this.mario_images[0] = View.loadImage("mario1.png");			
			this.mario_images[1] = View.loadImage("mario2.png");
			this.mario_images[2] = View.loadImage("mario3.png");
			this.mario_images[3] = View.loadImage("mario4.png");
			this.mario_images[4] = View.loadImage("mario5.png");
		}


	}

	public boolean isMario(){return true;}

	public void update()
	{
		vert_vel += 0.7;
		y += vert_vel;
		
		frameCount++; // incrreamenting the frame counter

		// to keep mario on the ground level
		if(y>(400-h))
		{
			vert_vel = 0.0;
			y=400-h;
				
		}
		if(y<0)
		{
			y=0;
			vert_vel = 7;// will push mario back down, right after he touch y=0
			
		}
		
		if(image>4) // we have only five images 
		{
			image =0;
		}
	
	}
	
	//Jumping method
	void jump()
	{    
		
			if(frameCount<10)
		vert_vel += -10;

 
	}
	
	//save Mario's previews cordinations
	void marioWasHere()
	{
		pre_x =x;
		pre_y =y;
	}
	//to get mario bounce from the tube
	void bounceFromTube(Tube t)
	{
			//to detect if there is a tube where Mario is
		
		if(x+w >= t.x && pre_x < t.x)
		{			
			x = t.x - w;
			
		}

		if(x < t.x+t.w)
		{
			x = pre_x;
			
		}
		if(y+h > t.y && x+w > t.x && x < t.x+ t.w ) 
		{   
			y =t.y-h-1;
			vert_vel =0.0;
		
			
		}

		if(pre_y>t.y+t.h && y>t.y ) 
		{
			y = t.y-t.h;
		}
		

		
	}
	

	
	public void draw(Graphics g)
	{  
		//to draw Mario Image
		//filpMario is to flip the image 
		if(model.mario.image<1)
		{
		g.drawImage(model.mario.mario_images[0], model.mario.x-model.mario.x+marioViewLocation, model.mario.y, null);

		}else
		{
		if(model.mario.flipMario)
		{
		g.drawImage(model.mario.mario_images[model.mario.image], model.mario.x-model.mario.x+marioViewLocation+model.mario.w, model.mario.y,-model.mario.w,model.mario.h, null);

		}else
		{
		g.drawImage(model.mario.mario_images[model.mario.image], model.mario.x-model.mario.x+marioViewLocation, model.mario.y, null);
		}
		
		}
	}
	
}