import javax.swing.JFrame;


import java.awt.Toolkit;

//Name: Khaled Ras Guerriche
//Date: started 09/2020
//Assignment: 05
//Description: a class that extends JFrame
              // and inherits all the methods and member variables from it

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);
		this.setTitle("Side-Scroller"); //just a title for our display window
		this.setSize(900, 500); //the display window width and height
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller); //means the Controller is in charge of handling key events
		try 
		{
			Json j =Json.load("map.json");
			model.unmarshal(j);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Fatal error, check your map loading ^^");
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
	
		Game g = new Game();
		g.run();
	}
	
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen
           
			// Go to sleep for 50 miliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
