public class Block {
	
	public boolean[][] tab = new boolean[4][4];
	private boolean[][] tabE = new boolean[4][4];
	byte akBlok;
	
	Block()
	{
		setBlok((byte) 0);
	}
	
	public void setBlok(byte k)
	{
		akBlok = k;
		for (byte x=0; x<4; x++)
			for (byte y=0; y<4; y++)
				tab[y][x] = Sekiller.BLOKLAR[akBlok][x][y];
	}
	
	public void döndürme()
	{
		for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tabE[x][y] = tab[x][y];
		for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tab[3-y][x] = tabE[x][y];
	}
	
	public void geriDöndürme()
	{
		for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tab[x][y] = tabE[x][y];
	}

}
