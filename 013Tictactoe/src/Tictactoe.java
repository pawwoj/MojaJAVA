import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tictactoe implements MouseListener {

	static Point tabPointO[] = { null, null, null, null, null };
	static Point tabPointX[] = { null, null, null, null, null };

	public static Point getTabPointO(int i) {
		return tabPointO[i];
	}

	public static Point getTabPointX(int i) {
		return tabPointX[i];
	}

	int postawienie = 0;
	boolean terazO = true;

	Rysowanie rys = new Rysowanie();
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	public Tictactoe() {

		initComponents();
		czyjaKolej();
	}

	public void initComponents() {

		frame.setTitle("TTT");
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 317, 338);
		frame.setDefaultCloseOperation(3);
		frame.add(rys);
		frame.setVisible(true);
		frame.addMouseListener(this);

	}

	public static void main(String[] args) {

		Tictactoe ttt = new Tictactoe();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// TODO Auto-generated method stub
		// System.out.println(e.getX() + " - " + e.getY());
		if (terazO) {

			wstawPintWTab(kalibracjaPointa(e.getPoint()), tabPointO, tabPointX);

		}

		else if (!terazO) {

			wstawPintWTab(kalibracjaPointa(e.getPoint()), tabPointX, tabPointO);

		}

		frame.revalidate();
		frame.repaint();

	}

	public Point kalibracjaPointa(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();

		if (x <= 130)
			x = 25;

		if (130 < x && x < 230)
			x = 125;

		else if (x >= 230)
			x = 225;

		if (y <= 110)
			y = 25;

		if (110 < y && y < 210)
			y = 125;

		else if (y >= 210)
			y = 225;

		p.setLocation(x, y);

		return p;

	}

	public void sprawdzCzyWygrana(Point tab[]) {
		for (int i = 0; i < 5; i++) {
			int x = 0;
			int y = 0;

		}

	}

	public void wstawPintWTab(Point p, Point tab[], Point tab2[]) {
		if (sprawdzCzyZajete(p, tab, tab2)) {
			tab[postawienie] = p;

			if (terazO) {
				postawienie++;
				terazO = false;
				czyjaKolej();

			} else if (!terazO) {
				terazO = true;
				czyjaKolej();

			}
		}

		System.out.println(postawienie);
		czyjaKolej();

	}

	public boolean sprawdzCzyZajete(Point p, Point tab[], Point tab2[]) {
		boolean wolnePole = true;
		for (Point pt : tab) {
			if (pt != null) {
				if (pt.equals(p)) {
					System.out.println("To pole jest zajete");
					wolnePole = false;
				}
			}
		}
		for (Point pt2 : tab2) {
			if (pt2 != null) {
				if (pt2.equals(p)) {
					System.out.println("To pole jest zajete");
					wolnePole = false;
				}
			}
		}
		return wolnePole;
	}

	public void czyjaKolej() {
		if (terazO)
			System.out.println("Teraz: O");
		if (!terazO)
			System.out.println("Teraz: X");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
