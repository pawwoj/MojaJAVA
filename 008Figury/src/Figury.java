
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Figury extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Polygon p = new Polygon();
		for (int i = 0; i < 2; i++)
			p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 2)),
					(int) (100 + 50 * Math.sin(i * 2 * Math.PI / 2)));
		
		g.drawPolygon(p);
	}
/*	
	Polygon p = new Polygon();
	for (int i = 0; i < 2; i++)
		p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 2)),
				(int) (100 + 50 * Math.sin(i * 2 * Math.PI / 2)));
	
	g.drawPolygon(p);
}
*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Waluty");
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 400, 400);
		// initComponents();
		frame.setDefaultCloseOperation(3);
		// frame.getContentPane().add(panel);
		// frame.setTitle("Polygon");
		// frame.setSize(500, 500);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container contentPane = frame.getContentPane();
		contentPane.add(new Figury());
		frame.setVisible(true);
	}
}
