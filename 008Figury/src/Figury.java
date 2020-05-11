
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

import javax.swing.*;

public class Figury extends JPanel{

//	JPanel panel = new JPanel();
	int wi;
	int pox;
	int poy;
	int rozm;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Polygon p = new Polygon();
		for (int i = 0; i < wi; i++)
			p.addPoint((int) (pox + rozm * Math.cos((i * 2 * Math.PI / wi) + 0.4)),
					(int) (poy + rozm * Math.sin((i * 2 * Math.PI / wi) + 0.4)));

		g.drawPolygon(p);
	}
	Figury(int w, int px, int py, int r){
		wi = w;
		pox = px;
		poy = py;
		rozm = r;
	}

	public void zrobFigure() {
		
	}

	public static void initComponents() {
		Figury figury = new Figury(6, 100, 100, 200);
		JFrame frame = new JFrame();
		frame.setTitle("Figury");
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 400, 400);
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
		frame.add(figury);
		
	}



	public static void main(String[] args) {
		initComponents();
		
		
		
	}
}
