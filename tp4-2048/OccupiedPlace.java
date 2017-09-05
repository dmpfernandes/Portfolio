package tp4;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public abstract class OccupiedPlace extends Place{

	public OccupiedPlace(int x, int y) {
		super(x, y);
		
	}
	
	public abstract void paintPlace();
	

	public abstract boolean moveTo(Place destination);

}
