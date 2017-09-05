package tp4;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class EmptyPlace extends Place {

	public EmptyPlace(int x, int y) {
		super(x, y);
		setBorder(new LineBorder(Color.gray));
		paintPlace();
		setOpaque(true);
	}
	
	public void paintPlace(){
		System.out.println("empty");
		setBackground(Color.green);
	}
	
	public boolean moveTo(Place destination){
		return false;
	}
}
