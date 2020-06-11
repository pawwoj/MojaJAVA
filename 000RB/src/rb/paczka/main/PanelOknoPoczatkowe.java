package rb.paczka.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

class PanelOknoPoczatkowe extends JPanel {

	JButton bDodajBOM = new JButton("Dodaj BOM");
	JButton bDodajSkladnik = new JButton("Dodaj Skladnik");
	JButton bWyszukajProdukty = new JButton("Wyszukaj Produkty");
	JButton bWyeksportujSkladniki = new JButton("Wyeksportuj Skladniki");
	JTextField jt = new JTextField();
	int i =0;
	
	final Class[] columnClass = new Class[] {
            Integer.class, String.class, Integer.class,Integer.class, Boolean.class};
	Object[][] daneDoWierszy = new Object[][] { {i++, "p01", 4031, 2,true },
			 {i++, "p02", 6014, 45,true  },
			 {i++, "p02fsrfsdgsdfgsdfgsdfg", 6014, 45 ,true },
			 {i++, "p02", 6014, 45 ,true },
			 {i++, "p02", 6014, 45,true  },
			 {i++, "p02", 6014,45 ,true },
 };
	String[] nazwyKolumn = { "Lp.","Nazwa produktu", "kod", "Ilosc", "X" };
	
    DefaultTableModel model = new DefaultTableModel(daneDoWierszy, nazwyKolumn) {
        @Override
        public boolean isCellEditable(int row, int column)
        {
        	if (column==4)
        		return true;
        	
        	else
            return false;
            
        }
        @Override
        public Class<?> getColumnClass(int columnIndex)
        {
            return columnClass[columnIndex];
        }
    };
//	JTable tablica = new JTable(daneDoWierszy, nazwyKolumn);
    JTable tablica = new JTable(model);

//	JScrollPane sp = new JScrollPane(tablica); 


	PanelOknoPoczatkowe() {
	//	tablica.setCellSelectionEnabled(false);
	//	tablica.setColumnSelectionAllowed(true);
	//	tablica.removeColumnSelectionInterval(0, 2);
	//	tablica.
		tablica.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablica.getColumnModel().getColumn(0).setPreferredWidth(35);
		tablica.getColumnModel().getColumn(1).setPreferredWidth(250);
		tablica.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablica.getColumnModel().getColumn(3).setPreferredWidth(55);
		tablica.getColumnModel().getColumn(4).setPreferredWidth(32);
		
		this.setLayout(null);
		this.add(bDodajBOM).setBounds(5, 5, 150, 20);
		this.add(bDodajSkladnik).setBounds(160, 5, 150, 20);
		this.add(bWyszukajProdukty).setBounds(5, 30, 150, 20);
		this.add(bWyeksportujSkladniki).setBounds(5, 460, 475, 20);
	//	tablica.setBounds(15, 80, 400, 80);
	//	this.add(tablica);
		this.add(new JScrollPane(tablica)).setBounds(5, 55, 475, 400);
		// tablica.

		bDodajBOM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					// kwotaDoPrzeliczenia = Double.parseDouble(ilePrzeliczyc.getText());
					// zrobLabele(indexWybranej);
					// TestB ts = new TestB();

//					okno.dodajKlase();
					RBMain.setiJPanel(1);
					OknoPoczatkowe.usynWszystko();
					System.out.println("klik");
				} catch (NumberFormatException e1) {
					System.out.println("bPrzelicz - dupa");
				}

			}
		});
		bDodajSkladnik.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					// kwotaDoPrzeliczenia = Double.parseDouble(ilePrzeliczyc.getText());
					// zrobLabele(indexWybranej);
					// TestB ts = new TestB();

//					okno.dodajKlase();
					RBMain.setiJPanel(2);
					OknoPoczatkowe.usynWszystko();
					System.out.println("klik");
				} catch (NumberFormatException e1) {
					System.out.println("bPrzelicz - dupa");
				}

			}
		});
	}
}
