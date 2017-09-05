package tp4;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

enum cores {
	_2(2, new Color(238, 238, 218)), _4(4, new Color(236, 224, 200)), _8(8, new Color(243, 176, 121)), _16(16,
			new Color(246, 148, 99)), _32(32, new Color(245, 124, 95)), _64(64, new Color(246, 94, 57)), _128(128,
					new Color(237, 206, 113)), _256(256, new Color(236, 203, 96)), _512(512,
							new Color(238, 199, 80)), _1024(1024, new Color(237, 197, 63)), _2048(2048,
									new Color(238, 194, 46));

	private Color color;
	private int num;

	private cores(int num, Color color) {
		this.color = color;
		this.num = num;
	}

	public Color getColor() {
		return color;
	}

	public int getNumb() {
		return num;
	}
}

public class NumericPlace extends OccupiedPlace {

	int num;

	public NumericPlace(int x, int y, int num) {
		super(x, y);
		this.num = num;
		setBorder(new LineBorder(Color.gray));
		setHorizontalAlignment(SwingConstants.CENTER);
		paintPlace();
		setOpaque(true);
	}

	public void paintPlace() {
		System.out.println("numeric");
		System.out.println(num);
		for (cores c : cores.values()) {
			if (num == c.getNumb()) {
				setBackground(c.getColor());
				setText(Integer.toString(num));
			}
		}

	}
	
	public int getNum(){
		return num;
	}

	public boolean moveTo(Place destination) {
		if (destination instanceof RockPlace) {
			return false;
		}
		if (destination instanceof EmptyPlace) {
			return false;
		}
		if (destination instanceof NumericPlace) {
			if(this.num == ((NumericPlace) destination).getNum()){
				
			}
		}

		return true;
	}

}
