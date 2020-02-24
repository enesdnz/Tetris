import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public abstract class ACanvas extends Canvas {
	
	BufferedImage resim;
	Graphics2D grafik;
	
	ACanvas(short sze, short wys)
	{
		super();
		setSize(sze, wys);
		resim = new BufferedImage(sze, wys, BufferedImage.TYPE_INT_RGB);
		grafik = (Graphics2D) resim.getGraphics();
	}
	
	public abstract void drawImage();
	
	private void ekranGösterimi()
	{
		Graphics g = getGraphics();
		g.drawImage(resim, 0, 0, null);
		g.dispose();
	}
	
	public void run()
	{
		drawImage();
		ekranGösterimi();
	}

}
