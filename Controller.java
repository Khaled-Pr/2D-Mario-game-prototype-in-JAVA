import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

//Name: Khaled Ras Guerriche
//Date: started 09/2020 
//Assignment: 05
//Description: a class to handle ActionEvents
             //and it implements ActionListener for that

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view; // a member variable to reference the View
	Model model; // a member variable to reference the Model object
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spacebar;
	boolean ctrl;
	boolean tubesAdder = false;
	boolean goombasAdder = false;
	
	
	Controller(Model m)
	{
		model =m; // initializing the "model" member variable
		
	}
	
//a method to that lets the caller set the object that "view" references
	void setView(View v) {
		view = v;
	}

//the method that will handle the event
	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		if(tubesAdder == true)
		{
		    if(model.removeTube(e.getX()+model.mario.x-view.marioViewLocation, e.getY())==false) {
			model.addTube(e.getX()+model.mario.x- view.marioViewLocation, e.getY());
		    }
		}else if(goombasAdder == true)
		{
			if(model.removeTube(e.getX()+model.mario.x-view.marioViewLocation, e.getY())==false)
				{
			model.addGoomba(e.getX()+model.mario.x- view.marioViewLocation, e.getY());
				}
		}
			
	}
	
	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) 
	{

		
	}
	

	

// a method for keyboard controls	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: spacebar = true; break;
			case KeyEvent.VK_CONTROL: ctrl = true; break;

		}
		char ch=e.getKeyChar();
		// if s is pressed
		if (ch=='s') {
		  
		  model.marshal().save("map.json");

		}
		//if l is pressed
		if(ch=='l') {
			
			Json j =Json.load("map.json");
			model.unmarshal(j);
			
		}
		//if t pressed add tube
		if(ch == 't')
		{
			goombasAdder=false;
			tubesAdder = !tubesAdder;
		}
		//if g pressed add Gummba
		if (ch == 'g')
		{
			tubesAdder=false;
			goombasAdder =! goombasAdder;
		}
		//throw fireballs
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) 
		{
			model.addFireball(model.mario); 

		}

	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE:spacebar = false; break;

		}

	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		//calling the function that will save  the previews cordinates of Mario
		 model.mario.marioWasHere();
		 
		

		//to move Mario
		if(keyRight)
		{   
	       	model.mario.x=model.mario.x+6;
			model.mario.flipMario = false;
			model.mario.image++;
		}
		if(keyLeft) 
		{	
			model.mario.x=model.mario.x-6;
			model.mario.flipMario = true;
			model.mario.image++;
			
		}


		if(spacebar) model.mario.jump();
//		if(keyDown) model.dest_y++;
//		if(keyUp) model.dest_y--;
	
		
		if (model.mario.vert_vel==0) model.mario.frameCount=0;
		
			
		

	}
	

}
