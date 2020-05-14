
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

import javax.swing.*;

public class Figury extends JPanel {
	
	

//	JPanel panel = new JPanel();
	int wi;
	int pox;
	int poy;
	int rozm;

	public int getWi() {
		return wi;
	}

	public void setWi(int wi) {
		this.wi = wi;
	}

	public int getPox() {
		return pox;
	}

	public void setPox(int pox) {
		this.pox = pox;
	}

	public int getPoy() {
		return poy;
	}

	public void setPoy(int poy) {
		this.poy = poy;
	}

	public int getRozm() {
		return rozm;
	}

	public void setRozm(int rozm) {
		this.rozm = rozm;
	}
	
	


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Polygon p = new Polygon();
		for (int i = 0; i < wi; i++)
			p.addPoint((int) (pox + rozm * Math.cos((i * 2 * Math.PI / wi) + 0.4)),
					(int) (poy + rozm * Math.sin((i * 2 * Math.PI / wi) + 0.4)));

		g.drawPolygon(p);
	}

	public static Figury zrobFigure(int w, int px, int py, int r) {

		Figury n = new Figury();
		n.setBounds(0, 0, r*2, r*2);
		n.setBackground(Color.LIGHT_GRAY);
		n.setWi(w);
		n.setPox(px);
		n.setPoy(py);
		n.setRozm(r);

		return n;
	}

	public static void initComponents() {
	

		JFrame frame = new JFrame();
	//	GridLayout experimentLayout = new GridLayout(1,2);
		frame.setLayout(null);
		frame.setTitle("Figury");
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 400, 400);
		frame.setDefaultCloseOperation(3);
	//	

		frame.add(zrobFigure(5, 10, 10, 10));

		frame.setVisible(true);
		
		

	}

	public static void main(String[] args) {
		initComponents();

	}
}
