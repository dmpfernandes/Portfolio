package tp4;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public abstract class Place extends JLabel{
	private int x,y;
	
	
	public Place(int x, int y){
		this.x=x;
		this.y=y;
		
		

	}
	
	public abstract void paintPlace();
	
	public int getBoard_x(){
		return this.x;
	}
	
	public int getBoard_y(){
		return this.y;
	}
	
	public void setBoard_x(int x){
		this.x = x;
	}
	
	public void setBoard_y(int y){
		this.y = y;
	}
	
	public abstract boolean moveTo(Place destination);
	
	
}
