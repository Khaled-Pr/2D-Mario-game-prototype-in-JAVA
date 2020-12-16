import java.util.ArrayList; //to import the arrayList class
import java.util.Comparator;
import java.util.Iterator;

//Name: Khaled Ras Guerriche
//Date: 09/09/2020
//Assignment: 04
//Description: a class to represent the state of the game and draw the tube 
 

class Model 
{

	ArrayList<Sprite> sprites; // adding the member variable of type Sprite as arryList
	Mario mario;  // create an instance of mario

	Model()
	{
		sprites= new ArrayList<Sprite>(); // initialize the sprites array list
		mario = new Mario(100,70,this); //initialization for mario position
		sprites.add(mario);


	}

	public void update()
	{
		
		
		for(int i=0; i<sprites.size();i++) 
			
		{  
			if(sprites.get(i).isTube())
			{
			if (detectSpritesCollusion(mario,(Tube)sprites.get(i)) == true)
			mario.bounceFromTube((Tube)sprites.get(i));
			}
			sprites.get(i).update();
	    }

	}

//Standard detection for sprites collusions
	boolean detectSpritesCollusion(Sprite spri, Sprite s) 
	{ 
		
			
		if(spri.x+spri.w < s.x) {
			return false;
		}if(spri.x > s.x + s.w) {
			return false;
		}
		if(spri.y + spri.h < s.y) {
			return false;
		}else if(spri.y > s.y + s.h) {
			return false;
		}else {
			return true;
		}
		
				
	}

	
	// a method to add a tube
	public void addTube(int mouse_x, int mouse_y)
	{
		Tube t =new Tube(mouse_x, mouse_y, this);
		sprites.add(t);
		//sprites.sort(tc);	
		
	}
	
	// a method to add Goombas
	public void addGoomba(int mouse_x, int mouse_y) 
	{
		Goombas g =new Goombas(mouse_x, mouse_y, this);
		sprites.add(g);
		
	}
	
	//a method to add Fireballs
		
	public void addFireball(Mario mario) 
	{
		Fireball f =new Fireball(mario.x+(mario.w/2),mario.y+(mario.h/2), this);
		sprites.add(f);
		
		
	}
	
	// a method to remove the tube
	public boolean removeTube(int mouse_x, int mouse_y) {
		
		
		//the for loop I have before using iterator
		 for(int i=0; i<sprites.size();i++) {
			 if(sprites.get(i).isTube()) {
				 Tube ti = (Tube)sprites.get(i);
				 if(ti.detectTube(mouse_x, mouse_y) == true) {
					 sprites.remove(i);
					 return true;
				 }
			 }

		}
		return false;
		
	}
	



	// the marshling
	  Json marshal()
	  {
		Json ob = Json.newObject();
		Json spritesOb = Json.newObject();
	    Json tmpList = Json.newList();
		ob.add("sprites", spritesOb);
	    spritesOb.add("tubes", tmpList);
	      for(int i = 0; i < sprites.size(); i++)
		  {
			  if(sprites.get(i).isTube())
			  {
				 tmpList.add(((Tube)sprites.get(i)).marshal());
			  }
		  }
		  tmpList = Json.newList();
	      spritesOb.add("goombas", tmpList);
	      for(int i = 0; i < sprites.size(); i++)
			  if(sprites.get(i).isGoomba())
			  {
				 tmpList.add(((Goombas)sprites.get(i)).marshal());

			  }
	    return ob;
	  }
	  
	  //the unmarshling
	  void unmarshal(Json ob) {
        sprites = new ArrayList<Sprite>();
		sprites.add(mario);
		Json jsonList = ob.get("sprites");
        Json tubesList = jsonList.get("tubes");
		Json goombasList = jsonList.get("goombas");
		//adding tubes
        for(int i = 0; i < tubesList.size(); i++)
            sprites.add(new Tube(tubesList.get(i), this));
		//adding Goombas
		for(int i = 0; i < goombasList.size(); i++)
            sprites.add(new Goombas(goombasList.get(i), this));
		  
	  }
		


}
