import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.ArrayList;


//Name: Khaled Ras Guerriche
//Date: started 09/2020
//Assignment: 05
//Description: a class extends JPanel
              //which is the the big gray area inside the JFrame
              // (where the graphical components of the program go) 

class View extends JPanel
{

	Model model; // a member variable to reference the Model object
	int marioViewLocation;// indicates where mario is in the view
	View(Controller c, Model m)
	{
		marioViewLocation = 180;// this just where I want mario to be in the view

		model = m; // initializing the "model" member variable
		c.setView(this); // to call the the setView method
		
	}
// to handel images
	static BufferedImage loadImage(String filename)
	{
		BufferedImage img = null;

		try
		{
			img = ImageIO.read(new File(filename));

		}catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
        
		return img;
	}
	
	
//a method to draw the image	
	public void paintComponent(Graphics g) 
	{
		
		//set the color to our display window and fill it
		//sky part
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
				
		//to draw the ground for mario
		g.setColor(Color.green);
		g.fillRect(0,400,this.getWidth(),this.getHeight());
		
		//to draw the sprites
		for(int i = 0; i < model.sprites.size(); i++)
		{   	
			model.sprites.get(i).draw(g);
		}

	
		
	}
}
