
import java.awt.Color;

public class Sekiller {
	final static short BOYUT = 25;
	final static Color[] RENK = {new Color(49,126,208), Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.WHITE};
	final static boolean[][][] BLOKLAR = 
		{
				{
					{false, false, false, false},	//....
					{true , true , true , false},	//###.
					{false, false, true , false},	//..#.
					{false, false, false, false}	//....
				},
				{
					{false, false, false, false},	//....
					{true , true , true , false},	//###.
					{false, true , false, false},	//.#..
					{false, false, false, false}	//....
				},
				{
					{false, false, false, false},	//....
					{false, false, true , false},	//..#.
					{true , true , true , false},	//###.
					{false, false, false, false}	//....
				},
				{
					{false, false, false, false},	//....
					{true , true , true , true },	//####
					{false, false, false, false},	//....
					{false, false, false, false}	//....
				},
				{
					{false, false, false, false},	//....
					{false, true , true , false},	//.##.
					{false, true , true , false},	//.##.
					{false, false, false, false}	//....
				},
				{
					{false, false, false, false},	//....
					{false, true , true , false},	//.##.
					{true , true , false, false},	//##..
					{false, false, false, false}	//....
				},
				{
					{false, false, false, false},	//....
					{true , true , false, false},	//##..
					{false, true , true , false},	//.##.
					{false, false, false, false}	//....
				}
		};
}
