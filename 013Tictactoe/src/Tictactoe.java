import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tictactoe extends JComponent implements MouseListener {
	
	

//	public Tictactoe() {
//
//	}
	@Override
	 public void paint(Graphics g) {
	        // Draw a simple line using the Graphics2D draw() method.
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setStroke(new BasicStroke(2f));
	        g2.setColor(Color.RED);
	        g2.draw(new Line2D.Double(50, 150, 250, 350));
	        g2.setColor(Color.GREEN);
	        g2.draw(new Line2D.Double(250, 350, 350, 250));
	        g2.setColor(Color.BLUE);
	        g2.draw(new Line2D.Double(350, 250, 150, 50));
	        g2.setColor(Color.YELLOW);
	        g2.draw(new Line2D.Double(150, 50, 50, 150));
	        g2.setColor(Color.BLACK);
	        g2.draw(new Line2D.Double(0, 0, 400, 400));
	    }

//	void drawLines(Graphics g2) {
//		Graphics2D g2d = (Graphics2D) g2;
//
//		g2d.drawLine(120, 50, 360, 50);
//
//	}
//
//	public void paint(Graphics g2) {
//		super.paint(g2);
//		drawLines(g2);
//	}

	public static void initComponents() {

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 100);
		frame.setLayout(null);
		frame.setTitle("TTT");
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 400, 400);
		frame.setDefaultCloseOperation(3);
		frame.add(panel);
		frame.setVisible(true);
		// JLabel label = new JLabel("jakis label do sprawdzenia mouselidtenera");
		// panel.add(label);
		// panel.addMouseListener(this);
	}

//	@Override
//	public void paint(Graphics g) {
//		// custom color
//		String hexColor = new String("0x45e5B");
//		g.setColor(Color.decode(hexColor));
//		// draw a line (starting x,y; ending x,y)
//		g.drawLine(10, 10, 40, 10);
//	}

	public static void main(String[] args) {
	//	Tictactoe ttt = new Tictactoe();
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
	//	panel.setBounds(0, 0, 400, 100);
	//	frame.setLayout(null);
		frame.setTitle("TTT");
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 400, 400);
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().add(new Tictactoe());
		//frame.add(panel);
		frame.setVisible(true);
	//	initComponents();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + " - " + e.getY());
		sprawdzKlik(e.getX(), e.getY());
	}

	public void sprawdzKlik(int x, int y) {
		if (x > 200)
			System.out.println("x wieksze od 200");

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
