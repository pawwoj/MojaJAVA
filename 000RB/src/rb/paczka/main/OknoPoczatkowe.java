package rb.paczka.main;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OknoPoczatkowe {
	
	static JFrame frame = new JFrame();
	static PanelOknoPoczatkowe panelOknoPoczatkowe = new PanelOknoPoczatkowe();
	static RBDodajProdukt rBDodajProdukt = new RBDodajProdukt();
	static RBDodajSkladnik rBDodajSkladnik = new RBDodajSkladnik();
	static TestB tb= new TestB();

	OknoPoczatkowe() {

		initComponents();
	}

	public static void initComponents() {
		

	//	PanelOknoPoczatkowe panelOknoPoczatkowe = new PanelOknoPoczatkowe();

		frame.setResizable(false);

		frame.setTitle("RB");
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 300, 500, 600);
		frame.setDefaultCloseOperation(3);
		
		dodajPanel();
		
//		if (RBMain.getiJPanel() == 0)
//			frame.add(new PanelOknoPoczatkowe());
//		
//		if (RBMain.getiJPanel() == 1)
//			frame.add(new TestB());
		
		frame.repaint();
		frame.revalidate();
		
		
		frame.setVisible(true);
	}
	public static void usynWszystko() {
		frame.remove(panelOknoPoczatkowe);
		frame.remove(rBDodajSkladnik);
		frame.remove(rBDodajProdukt);

// czemu nie dzia³a removeAll()	cos sie zawiesza/ nieodmalowje
		initComponents();	
		frame.repaint();
		frame.revalidate();
		


		
	}

	public static void dodajPanel() {
	if (RBMain.getiJPanel() == 0)

		frame.add(panelOknoPoczatkowe);
	
	if (RBMain.getiJPanel() == 1)
		frame.add(rBDodajProdukt);
	
	if (RBMain.getiJPanel() == 2)
		frame.add(rBDodajSkladnik);
	
	

	}
}
