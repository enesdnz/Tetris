
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class Board extends ACanvas implements MouseListener, KeyListener{

	final static short SZE = Sekiller.BOYUT * 10;
	final static short WYS = Sekiller.BOYUT * 20;
	
	byte[][] tab = new byte[12][22];
	Random random = new Random();
	
	Block block = new Block();
	byte blokX, blokY;
	
	boolean sol, sag, yukari, asagi;
	
	short hiz, hizMax;
	boolean hizTus;
	
	boolean gamePlay, dur;
	Color renk;
	
	
	
	Board() {
		super(SZE, WYS);addMouseListener(this);addKeyListener(this);
		
		for (byte x=0; x<12; x++) {tab[x][0]=1;tab[x][21]=1;}
		for (byte y=0; y<22; y++) {tab[0][y]=1;tab[11][y]=1;}
		blokX = 4; 
		blokY = 0;
		hizMax = (short) (27 - Tetris.seviye);
		gamePlay = false;
		dur = false;
		grafik.setFont(new Font("Comic Sans MS", Font.BOLD, 29));
		renk = Color.white;
		
	}


	@Override
	public void drawImage() {
		if (gamePlay) 
		{
			key();
			cmpBoard();
			boardPrinting();
			yazdirBlok(blokX, blokY);
			if (!dur)
			{
				if (hiz<hizMax) hiz++;else 
					{
						hiz=0;
						if (isTouchGround(blokX, (byte)(blokY+1))) blokY++; else
						{
							endBlock();
							yeniBlok();
						}
					}
			}
			else
			{
				grafik.setColor(Color.BLACK);
				grafik.drawString("DEVAM", 73, 496);
				grafik.setColor(renk);
				
			}
	} 
		else 
		{
			
			grafik.setColor(Sekiller.RENK[0]);
			grafik.fillRect(0, 0, SZE, WYS);
			grafik.setColor(Color.white);
			grafik.drawString("BASLA", 80, 495);
			grafik.setColor(renk);
			Image img = new ImageIcon("res/tetris.png").getImage();
			grafik.drawImage(img, 25, -20, null);
			
			
		}
	}
	
	
	
	
	public void yeniBlok()
	{
		
		blokX = 4; 
		blokY = 0;
		hizMax = (short) (20 - Tetris.seviye);
		if (hizMax<0) hizMax=0;
		block.setBlok(Tetris.sonraki.blok);
		Tetris.sonraki.rastgeleSekil();
		Tetris.puan+=block.akBlok;
		Tetris.lpuan.setText(String.valueOf(Tetris.puan));
	}
	
	public void endBlock()
	{
		for (byte xx=0; xx<4; xx++)
			for (byte yy=0; yy<4; yy++)
				if (block.tab[xx][yy]) tab[xx+blokX][yy+blokY]=(byte)(block.akBlok+1);
	}
	
	private void boardPrinting()
	{
		for (byte x=1; x<11; x++)
			for (byte y=1; y<21; y++)
			{
				grafik.setColor(Sekiller.RENK[tab[x][y]]);
				grafik.fillRect((x*Sekiller.BOYUT)-Sekiller.BOYUT, (y*Sekiller.BOYUT)-Sekiller.BOYUT, Sekiller.BOYUT, Sekiller.BOYUT);
				grafik.setColor(Color.BLACK);
				if (tab[x][y] > 0)
					grafik.drawRect((x * Sekiller.BOYUT) - Sekiller.BOYUT, (y * Sekiller.BOYUT) - Sekiller.BOYUT, Sekiller.BOYUT - 1, Sekiller.BOYUT-1);
			}
	}
	private void cubePrinting(byte x, byte y, byte k)
	{
		grafik.setColor(Sekiller.RENK[k]);
		grafik.fillRect((x*Sekiller.BOYUT)-Sekiller.BOYUT, (y*Sekiller.BOYUT)-Sekiller.BOYUT, Sekiller.BOYUT, Sekiller.BOYUT);
		grafik.setColor(Color.BLACK);
		grafik.drawRect((x*Sekiller.BOYUT)-Sekiller.BOYUT, (y*Sekiller.BOYUT)-Sekiller.BOYUT, Sekiller.BOYUT-1, Sekiller.BOYUT-1);
	}
	
	private boolean isLine(byte y)
	{
		for (byte x=1; x<11; x++) {if (tab[x][y]==0) return false;}
		return true;
	}
	private boolean isFull()
	{
		for (byte x=1; x<11; x++) {if (tab[x][1]!=0) return true;}
		return false;
	}
	
	private void setLine(byte y)
	{
		
		for (byte x=1; x<11; x++) tab[x][y]=8;
		Tetris.satir++;Tetris.lsatir.setText(String.valueOf(Tetris.satir));
		Tetris.puan+=(Tetris.seviye * 10);
		Tetris.lpuan.setText(String.valueOf(Tetris.puan));
		if (Tetris.satir==(Tetris.seviye*Tetris.seviye)) 
		{
			Tetris.seviye++;
			Tetris.lseviye.setText(String.valueOf(Tetris.seviye));
			if (hizMax>0) hizMax--;
		} 
	}
	private void downBoard(byte y)
	{
		for (byte ty=y; ty>1; ty--)
			for (byte x=1; x<11; x++) tab[x][ty]=tab[x][ty-1];
		for (byte x=1; x<11; x++) tab[x][1]=0;
	}
	
	private void cmpBoard()
	{
		for (byte y=1; y<21; y++)
		{
			if (tab[1][y]==8) downBoard(y);
			if (isLine(y)) setLine(y);
		}
		if (isFull())
		{
			gamePlay = false;
			Tetris.seviye=1;
			Tetris.satir=0;
			Tetris.puan=0;
			blokX = 4; 
			blokY = 0;
			hizMax = (short) (21 - Tetris.seviye);
			for (byte x=1; x<11; x++) for (byte y=1; y<21; y++) tab[x][y]=0;
		}
	}
	
	public void yazdirBlok(byte x, byte y)
	{
		for (byte tx=0; tx<4; tx++)
			for (byte ty=0; ty<4; ty++)
				if (block.tab[tx][ty]) cubePrinting((byte)(tx+x),(byte) (ty+y), (byte) (block.akBlok+1));
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gamePlay) 
		{
			gamePlay=true;
			Tetris.lpuan.setText(String.valueOf(Tetris.puan));
			Tetris.lsatir.setText(String.valueOf(Tetris.satir));
			Tetris.lseviye.setText(String.valueOf(Tetris.seviye));
		}else
		dur=!dur;
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if (k==e.VK_UP || k==e.VK_W) yukari = true;
		if (k==e.VK_DOWN || k==e.VK_S) asagi = true;
		if (k==e.VK_LEFT || k==e.VK_A) sol = true;
		if (k==e.VK_RIGHT || k==e.VK_D) sag = true;
	}

	@SuppressWarnings("static-access")
	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if (k==e.VK_UP || k==e.VK_W) yukari = false;
		if (k==e.VK_DOWN || k==e.VK_S) asagi = false;
		if (k==e.VK_LEFT || k==e.VK_A) sol = false;
		if (k==e.VK_RIGHT || k==e.VK_D) sag = false;
	}
	
	private boolean isTouchGround(byte x, byte y)
	{
		for (byte xx=0; xx<4; xx++)
			for (byte yy=0; yy<4; yy++)
				if (block.tab[xx][yy] && tab[xx+x][yy+y]>0) return false;
		return true;
	}
	
	private boolean moveLeft()
	{
		if (!isTouchGround((byte)(blokX-1), blokY)) return false;
		return true;
	}
	private boolean moveRight()
	{
		if (!isTouchGround((byte)(blokX+1), blokY)) return false;
		return true;
	}
	
	private void key()
	{
		hizTus=!hizTus;
		if (yukari && hizTus) {block.döndürme();
		if (!isTouchGround(blokX, blokY)) block.geriDöndürme();}
		if (sol && moveLeft()) blokX--;
		if (sag && moveRight()) blokX++;
		if (asagi && hizMax>0) {hizMax=0;Tetris.puan+=5;Tetris.lpuan.setText(String.valueOf(Tetris.puan));}
	}

}
