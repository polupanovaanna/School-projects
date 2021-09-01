//Polupanova Anna 2020a
//class Hitable
//27.04.18
public class Hitable {
	double x;
	double y;
	int height;
	int width;
	public final int north = 1;
	public final int east = 2;
	public final int south = 3;
	public final int west = 4;
	

	int hitTest(double rabbitx, double rabbity, double rabbitR) {
		int p = 0;
		if ((rabbitx + rabbitR > x) && (rabbitx < x + width)) {
			if ((rabbity <= y) && (rabbity + rabbitR >= y)) {
				p = north;
			}
			if ((rabbity <= y + height) && (rabbity + rabbitR >= y + height)) {
				p = south;
			}
		} else {
			if ((rabbity + rabbitR >= y) && (rabbity <= y + height)) {
				if ((rabbitx <= x) && (rabbitx + rabbitR >= x)) {
					p = west;
				}
				if ((rabbitx <= x + width) && (rabbitx + rabbitR >= x + width)) {
					p = east;
				}
			}

		}
		return p;

	}
}
