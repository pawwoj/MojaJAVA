package podpisdok;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Podpis extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9083823300361583081L;

	public Podpis() {
		this.setTitle("Podpis dokumentu");
		this.setBounds(300, 0, 415, intOpuszczenie+260);
		initComponents();
		this.setDefaultCloseOperation(3);

	}

	public static void main(String[] args) {
		new Podpis().setVisible(true);

	}

	static void focus(JTextField nazwa) {
		nazwa.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {
				nazwa.selectAll();
			}

			public void focusLost(FocusEvent e) {

			}
		});
	}

	public void initComponents() {
		this.getContentPane().add(panEkranStartowy);

		panEkranStartowy.setLayout(null);
		panEkranStartowy.setBackground(Color.WHITE);
		panEkranStartowy.add(butWybierzObrazek);
		panEkranStartowy.add(butZapiszJakoPDF);
		panEkranStartowy.add(butResetWpisu);
		panEkranStartowy.add(butWstawTlo);
		panEkranStartowy.add(labImie);
		panEkranStartowy.add(labNazwisko);
		panEkranStartowy.add(labPesel);
		
		butResetWpisu.setBounds(320, intOpuszczenie+110, 70, 30);
		butWstawTlo.setBounds(320, intOpuszczenie+150, 70, 30);
		butWybierzObrazek.setBounds(10, 10, 185, 30);
		butZapiszJakoPDF.setBounds(205, 10, 185, 30);
		labImie.setBounds(10, intOpuszczenie, 185, 20);
		labNazwisko.setBounds(205, intOpuszczenie, 185, 20);
		labPesel.setBounds(10, intOpuszczenie+60, 185, 20);
		
		panEkranStartowy.add(jTFimie);
		panEkranStartowy.add(jTFnazwisko);
		panEkranStartowy.add(jTFpesel);
		panEkranStartowy.add(jspScroll);
		
		wypisInformacji.setLineWrap(true);

		jspScroll.setBounds(10, intOpuszczenie+110, 300, 100);
		
		jTFimie.setBounds(10, intOpuszczenie+20, 185, 30);
		jTFimie.setToolTipText("Wpisz imiê pacjenta");
		
		jTFnazwisko.setBounds(205, intOpuszczenie+20, 185, 30);
		jTFnazwisko.setToolTipText("Wpisz nazwisko pacjenta");
		
		jTFpesel.setBounds(10, intOpuszczenie+80, 185, 30);
		jTFpesel.setToolTipText("Wpisz PESEL pacjenta");
		
		wypisInformacji.setText("");
		wypisInformacji.setEditable(false);
		focus(jTFimie);
		focus(jTFnazwisko);
		focus(jTFpesel);

		LocalDate localDate = LocalDate.now();
		data = localDate.toString();
		
		wstawTlo();
		otworzLabelZObrazkiem();
		fraOknoZObrazkiem.add(labWczytanyObrazek);

		butZapiszJakoPDF.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(labWczytanyObrazek.getImg(), "PNG", new File("C:\\podpisane\\" + jTFimie.getText()
							+ " " + jTFnazwisko.getText() + " " + data + " plikGraficzny.png"));
					wypisInformacji.setText("Plik zapisany");
					System.out.println("Plik zapisany");
				} catch (Exception i1) {
					wypisInformacji.setText("b³¹d i1: " + i1);
					System.out.println("b³¹d i1: " + i1);
					wypisInformacji.setText("b³¹d e: " + e);
					System.out.println("b³¹d e: " + e);
					
					i1.printStackTrace();
				}

			}

		});

		butResetWpisu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				labWczytanyObrazek.czysciArrayList();
				fraOknoZObrazkiem.repaint();
				wypisInformacji.setText("obraz zresetowany");
				System.out.println("obraz zresetowany");
			}

		});

		butWybierzObrazek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wczytajObrazek();
			}
		});
		
		butWstawTlo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

//				labWczytanyObrazek.czysciArrayList();
//				fraOknoZObrazkiem.repaint();
				wstawTlo();
				
			}

		});
	}
	
	public void wstawTlo() {
		try {
			imageWybranyObrazek = ImageIO.read(new File("C:\\graficzne\\AGL.png"));
		    wypisInformacji.setText("t³o jest aktywne");
			System.out.println("t³o jest aktywne");
			jTFimie.setText("");
			jTFnazwisko.setText("");
			jTFpesel.setText("");
			
			labWczytanyObrazek.czysciArrayList();
			fraOknoZObrazkiem.repaint();
		} catch (IOException e2) {
			wypisInformacji.setText("b³¹d: " + e2);
			System.out.println("b³¹d: " + e2);
		}
	}

	public void wczytajObrazek() {
	//	System.out.println("konsola dzia³a");
		JFileChooser wyborObrazka = new JFileChooser();
		wyborObrazka.setCurrentDirectory(new File("C:\\graficzne"));
		wyborObrazka.showOpenDialog(null);
		filWybranyPlik = wyborObrazka.getSelectedFile();
		try {
			imageWybranyObrazek = ImageIO.read(filWybranyPlik);
		//	bufImObrazek = ImageIO.read(filWybranyPlik);

		} catch (IOException e1) {
			System.out.println("b³¹d e1: "+ e1);
			e1.printStackTrace();
			wypisInformacji.setText(" " + e1);
		}
	//	fraOknoZObrazkiem.removeAll();
		otworzLabelZObrazkiem();
		try {
			fraOknoZObrazkiem.add(labWczytanyObrazek);
		} catch (Exception e8) {
			System.out.println("b³¹d e8: " + e8);
			e8.printStackTrace();
		}
		fraOknoZObrazkiem.repaint();

	}
	
	public void otworzLabelZObrazkiem() {
		
		try {
	if (fraOknoZObrazkiem.isVisible() ==false) {
			fraOknoZObrazkiem.setUndecorated(true);
			fraOknoZObrazkiem.setLocation(1920, 0);	
			fraOknoZObrazkiem.setExtendedState(JFrame.MAXIMIZED_BOTH);	
			fraOknoZObrazkiem.setVisible(true);	
	}

		} catch (Exception e0) {
			System.out.println("b³¹d e0: " + e0);
			e0.printStackTrace();
		}
	}

	String data;
	File filWybranyPlik;
	Image imageWybranyObrazek;
	Image imageTlo;
//	BufferedImage bufImObrazek;
//	BufferedImage bImagezImage = (BufferedImage) image;
	
	JPanel panEkranStartowy = new JPanel();
	JFrame fraOknoZObrazkiem = new JFrame("Dokument dla pacjenta");

	LabWczytanyObrazek labWczytanyObrazek = new LabWczytanyObrazek();
	
	JTextField jTFimie = new JTextField("Imiê");
	JTextField jTFnazwisko = new JTextField("Nazwisko");
	JTextField jTFpesel = new JTextField("PESEL");
	
	JTextArea wypisInformacji = new JTextArea("Info");
	JScrollPane jspScroll = new JScrollPane(wypisInformacji);
	
	JButton butWybierzObrazek = new JButton("Wybierz dokument");
	JButton butZapiszJakoPDF = new JButton("Zapisz dokument");
	JButton butWstawTlo = new JButton("t³o");
	JButton butResetWpisu = new JButton("reset");
	JButton butWczytajAnkiete = new JButton("Wczytaj Ankietê");
	JLabel labImie = new JLabel("Imiê:");
	JLabel labNazwisko = new JLabel("Nazwisko:");
	JLabel labPesel = new JLabel("PESEL:");
	int intOpuszczenie = 100;

	public class LabWczytanyObrazek extends JLabel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5059719565432802908L;
		private List<List<Point>> curves = new ArrayList<>();

		public void czysciArrayList() {
			this.curves.clear();
		}

		BufferedImage img;

		public BufferedImage getImg() {
			return img;
		}

		public void setImg(BufferedImage img) {
			this.img = img;
		}

		int licznik = 0;

		public LabWczytanyObrazek() {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					List<Point> newCurve = new ArrayList<Point>();
					newCurve.add(new Point(e.getX(), e.getY()));
					curves.add(newCurve);
				}
			});

			labWczytanyObrazek = this;
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					curves.get(curves.size() - 1).add(new Point(e.getX(), e.getY()));
					repaint(0, 0, getWidth(), getHeight());
				}
			});
			addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {

					System.out.println("x-" + e.getXOnScreen() + "// y-" + e.getYOnScreen());

					try {

						img = new BufferedImage(labWczytanyObrazek.getWidth(), labWczytanyObrazek.getHeight(),
								BufferedImage.TYPE_INT_RGB);
						Graphics2D g2d = img.createGraphics();
						labWczytanyObrazek.printAll(g2d);
						g2d.dispose();
						ImageIO.write(img, "JPG", new File("C:\\podpisane\\" + jTFimie.getText() + " "
								+ jTFnazwisko.getText() + " " + data + " plik.JPG"));

						setImg(img);

						System.out.println("Zapis roboczy udany");
					} catch (IOException e2) {
						System.out.println("Zapis roboczy NIEUDANY");
						e2.printStackTrace();
						wypisInformacji.setText(" " + e2);
					}

				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imageWybranyObrazek, 0, 0, null);

			g.setFont(new Font("TimesRoman", Font.BOLD, 24));
			g.drawString(jTFimie.getText() + " " + jTFnazwisko.getText(), 220, 120);
			g.drawString(jTFpesel.getText(), 110, 170);
			g.drawString(data, 130, 985);

			for (List<Point> curve : curves) {
				Point previousPoint = curve.get(0);
				for (Point point : curve) {
					g.drawLine(previousPoint.x, previousPoint.y, point.x, point.y);
					previousPoint = point;
				}

			}

		}

	}

}