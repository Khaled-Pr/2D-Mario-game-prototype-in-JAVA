import java.awt.Graphics;
abstract class Sprite
{
	int x;
	int y;
	int w;
	int h;
	double vert_vel; //gravity variable

	int image; 
	abstract void update();
	abstract void draw(Graphics g);
	public boolean isTube(){return false;}
	public boolean isMario(){return false;}
	public boolean isGoomba(){return false;}
	public boolean isFireball(){return false;}


	
}