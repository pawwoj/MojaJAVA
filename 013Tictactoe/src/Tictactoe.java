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
	static int tX;

//	static Point tabPoint[] = new Point[500];

	static Point tabPoint[] = { null, null, null, null, null };

	public static Point getTabPoint(int i) {
		return tabPoint[i];
	}

	int postawienie = 0;

	Rysowanie rys = new Rysowanie();
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	public Tictactoe() {

		initComponents();
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
		System.out.println(e.getX() + " - " + e.getY());
		sprawdzenieCzyZajete(kalibracjaPointa(e.getPoint()));

		frame.revalidate();
		frame.repaint();

	}

	public Point kalibracjaPointa(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();

		if (x < 130)
			x = 25;

		if (130 < x && x < 230)
			x = 125;

		else if (x > 230)
			x = 225;

		if (y < 110)
			y = 25;

		if (110 < y && y < 210)
			y = 125;

		else if (y > 210)
			y = 225;

		p.setLocation(x, y);

		return p;

	}

	public void sprawdzenieCzyZajete(Point p) {
		for (Point pt : tabPoint) {
			if (pt == null) {
				tabPoint[postawienie] = p;
				postawienie++;

				System.out.println(postawienie);
				break;
			} else if (pt.equals(p)) {
				System.out.println("To pole jest zajete");
				break;
			}
		}
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