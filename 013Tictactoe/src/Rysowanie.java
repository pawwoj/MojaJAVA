import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Rysowanie extends JPanel {

	void liniePola(Graphics g2) {
		Graphics2D g2d = (Graphics2D) g2;

		g2d.drawLine(0, 100, 300, 100);
		g2d.drawLine(0, 200, 300, 200);
		g2d.drawLine(100, 0, 100, 300);
		g2d.drawLine(200, 0, 200, 300);
	}

	void drawO(Graphics g2) {
		Graphics2D o2d = (Graphics2D) g2;
		// System.out.println( (int)Tictactoe.getTabPoint(1).getX());
	//	o2d.drawOval(25,25 , 50,50);
		for (int i = 0; i < 5; i++) {
			if (!(Tictactoe.getTabPoint(i) == null)) {
				o2d.drawOval((int) Tictactoe.getTabPoint(i).getX(), (int) Tictactoe.getTabPoint(i).getY(), 50,50);
			//	o2d.drawOval((int) Tictactoe.getTabPoint(i).getX() - 30, (int) Tictactoe.getTabPoint(i).getY() - 50, 50,
			//			50);
			}
		}
	}

	public void paint(Graphics g2) {
		super.paint(g2);
		liniePola(g2);
		drawO(g2);
	}
}
