
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tetris extends JPanel implements Runnable {

	
	static Tetris tetris = new Tetris();
	static JFrame j = new JFrame("Tetris");
	static Thread thread = new Thread(tetris);
	
	static Board board = new Board();
	static Sonraki sonraki = new Sonraki();
	
	boolean basla = false;
	short op = 30;
	
	static int satir = 0, puan = 0, seviye = 1;
	static JLabel lpuan, lsatir, lseviye, lpuan2, lsatir2, lseviye2;
	static Color renkPuan = new Color(255,128,255);
	static Color renkSatir = new Color(255,255,0);
	static Color renkSeviye = new Color(0,255,255);
	
	
	
	Tetris()
	{
		super();
		setBackground(new Color(8,26,47));
		setLayout(null);
		basla = true;
		
	}
	
	
	
	public static void main(String[] args) {
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(tetris);
		j.setSize(380,550);
		j.setLocationRelativeTo(null);
		j.setResizable(false);

		lpuan = new JLabel("0", JLabel.RIGHT);
		lpuan.setForeground(renkPuan);
		lpuan.setBounds(270, 300, 100, 20);tetris.add(lpuan);
		lpuan2 = new JLabel("PUAN", JLabel.RIGHT);
		lpuan2.setBounds(270, 290, 100, 10);lpuan2.setForeground(renkPuan);
		tetris.add(lpuan2);
		
		lsatir = new JLabel("0", JLabel.RIGHT);
		lsatir.setForeground(renkSatir);
		lsatir.setBounds(270, 230, 100, 20);tetris.add(lsatir);
		lsatir2 = new JLabel("SATIR", JLabel.RIGHT);
		lsatir2.setBounds(270, 220, 100, 10);lsatir2.setForeground(renkSatir);
		tetris.add(lsatir2);
		
		lseviye = new JLabel("1", JLabel.RIGHT);
		lseviye.setForeground(renkSeviye);
		lseviye.setBounds(270, 160, 100, 20);tetris.add(lseviye);
		lseviye2 = new JLabel("SEVİYE", JLabel.RIGHT);
		lseviye2.setBounds(270, 150, 100, 10);lseviye2.setForeground(renkSeviye);
		tetris.add(lseviye2);
		
		board.setLocation(10, 10);tetris.add(board);
		sonraki.setLocation(270, 10);tetris.add(sonraki);
		j.setVisible(true);
		thread.start();
	}



	@SuppressWarnings("static-access")
	@Override
	public void run() {
		long bekle, baslamaZamani, sayacZaman;
		while (basla)
		{
			baslamaZamani = System.nanoTime();
				board.run();
				sonraki.run();
			sayacZaman = System.nanoTime() - baslamaZamani;
			bekle = op - sayacZaman / 1000000;
			if (bekle<=0) bekle = 5;
			try {thread.sleep(bekle);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

}

