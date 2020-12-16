import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;

//Name: Khaled Ras Guerriche
//Date: started 09/2020
//Assignment: 05

public class Goombas extends Sprite
{
	static BufferedImage image; // goomba image
	static BufferedImage image2; //goombaFire image
	Model model;
	String curr_img;
	int curr_direction;
	double speed;
	int direction;
    public boolean isOnFire;
	int CountinFire ; // counter that it will be used to keep Goombafire.png for some time before I remove it
	
	public Goombas(int x, int y, Model m)
	{	isOnFire =false;
		CountinFire =0;
		curr_img = "gommba.png";
		this.x = x;
		this.y = y;
		w = 99;
		h = 118;
		speed =5;
		vert_vel =1.2;
		direction =1;
		this.model = m;
		loadGoombas();
	}
	public Goombas(Json ob, Model m)
	{
	    x = (int)ob.getLong("x");
	    y = (int)ob.getLong("y");
		w=99;
	    h=118;
		speed =5;
		vert_vel=1.2;
		direction =1;
		this.model = m;
		loadGoombas();
	}
	
	//to load goomba image
	void loadGoombas()
	{
		if(image == null)
		{ 
			image = View.loadImage("goomba.png");		
	
		}
	}
	void update()
	{
		vert_vel += 0.7;
		y += vert_vel;
		
		// to keep goombas on the ground level
		if(y>(400-h))
		{
			vert_vel = 0.0;
			y=400-h;
				
		}
		this.x+=speed*direction;
		
		
		// this for loop looping throught the sprites
		for(int i=0; i<model.sprites.size();i++) 
			
		{  
			if(model.sprites.get(i).isTube())
			{
				curr_direction = direction;
			if (model.detectSpritesCollusion(this,(Tube)model.sprites.get(i)) == true)
			{	
				if(curr_direction==1){
					direction = -1;
				}
				if(curr_direction==-1)
				{
					direction =1;
					
				}
			}
			
			}
					

			//if it is gommba with fireball
			else if(model.sprites.get(i).isFireball())
			{
				
			if (model.detectSpritesCollusion(this,(Fireball)model.sprites.get(i)) == true)
			{	//load goomba in fire image
						isOnFire =true;
					this.image2 = View.loadImage("goomba_fire.png");


			}

			
			}
			//to remove goombafire if it is in fire for 5 or more fram count
			if(this.isOnFire == true && CountinFire >=5)
			{ 
		       //if it is goombafire remove it
				if(model.sprites.get(i).isGoomba())
				{
					model.sprites.remove(i);
					CountinFire=0;
					

				}
			}
	    }
						


	}
	
	// to draw the Gooomba and Goombafire
	void draw(Graphics g)
	{
	if(isOnFire ==false){
			  g.drawImage(image, x-model.mario.x+model.mario.marioViewLocation,y, null);
	}
			  
			  else{
			  g.drawImage(image2, x-model.mario.x+model.mario.marioViewLocation,y, null);
			  CountinFire++;

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

	
	public boolean isGoomba(){return true;} 
	

}