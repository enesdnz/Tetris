
import java.awt.Color;
import java.util.Random;

@SuppressWarnings("serial")
public class Sonraki extends ACanvas {

	byte blok;
	private Random random = new Random();
	
	Sonraki() {
		super((byte)100, (byte)100);
		rastgeleSekil();
	}

	@Override
	public void drawImage() {
		sekilBastir();
	}
	
	public void rastgeleSekil()
	{
		blok = (byte)(random.nextInt(6)+1);
	}
	
	private void küpYazdir(byte x, byte y, byte k)
	{
		grafik.setColor(Sekiller.RENK[k]);
		grafik.fillRect(x*Sekiller.BOYUT, y*Sekiller.BOYUT, Sekiller.BOYUT, Sekiller.BOYUT);
		grafik.setColor(Color.BLACK);
		grafik.drawRect(x*Sekiller.BOYUT, y*Sekiller.BOYUT, Sekiller.BOYUT-1, Sekiller.BOYUT-1);
	}

	private void sekilBastir()
	{
		grafik.setColor(Sekiller.RENK[0]);
		grafik.fillRect(0, 0, 4*Sekiller.BOYUT, 4*Sekiller.BOYUT);
		for (byte tx=0; tx<4; tx++)
			for (byte ty=0; ty<4; ty++)
				if (Sekiller.BLOKLAR[blok][ty][tx]) küpYazdir(tx,ty, (byte) (blok+1));
	}


}
