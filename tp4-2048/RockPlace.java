package tp4;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class RockPlace extends OccupiedPlace {
	int span;
	public RockPlace(int x, int y, int span) {
		super(x, y);
		this.span=span;
		setBorder(new LineBorder(Color.gray));
		setHorizontalAlignment(SwingConstants.CENTER);
		paintPlace();
		setOpaque(true);
		
	}

	public boolean moveTo(Place destination) {
		return false;
	}
	
	public void paintPlace(){
		setBackground(Color.blue);
		setText(Integer.toString(span));
	}
	
	public void decrementaSpan(){
		span--;
	}
	
	public int getSpan(){
		return span;
	}
}
