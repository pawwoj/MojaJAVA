package rb.paczka.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

class PanelOknoPoczatkowe extends JPanel {

	JButton bDodajBOM = new JButton("Dodaj BOM");
	JButton bTest2 = new JButton("Test");
	JButton bDodajSkladnik = new JButton("Dodaj Skladnik");
	JButton bWyszukajProdukty = new JButton("Wyszukaj Produkty");
	JButton bWyeksportujSkladniki = new JButton("Wyeksportuj Skladniki");
	JTextField textFKodDoWyszukania = new JTextField();

	int iLpWtabeli = 1;

//	final Class[] columnClass = new Class[] {
//            Integer.class, String.class, Integer.class,Integer.class, Boolean.class};
//	Object[][] daneDoWierszy = new Object[][] { {i++, "p01", 4031, 2,true },
//			 {i++, "p02", 6014, 45,true  },
//			 {i++, "p02fsrfsdgsdfgsdfgsdfg", 6014, 45 ,true },
//			 {i++, "p02", 6014, 45 ,true },
//			 {i++, "p02", 6014, 45,true  },
//			 {i++, "p02", 6014,45 ,true },
// };
//	String[] nazwyKolumn = { "Lp.","Nazwa produktu", "kod", "Ilosc", "X" };
//	
//    DefaultTableModel model = new DefaultTableModel(daneDoWierszy, nazwyKolumn) {

//        @Override
//        public Class<?> getColumnClass(int columnIndex)
//        {
//            return columnClass[columnIndex];
//        }
//    };
//	JTable tablica = new JTable(daneDoWierszy, nazwyKolumn);
//    JTable tablica = new JTable(model);

//	JScrollPane sp = new JScrollPane(tablica); 

	PanelOknoPoczatkowe() {
		DefaultTableModel tableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0)
					return true;

				else
					return false;

			}

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return Integer.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;

				default:
					return String.class;
				}
			}
		};

		JTable table = new JTable(tableModel);
		tableModel.addColumn("lp");
		tableModel.addColumn("Kod");
		tableModel.addColumn("Nazwa");
		tableModel.addColumn("jednostka");
		tableModel.addColumn("opis");
//		    tableModel.insertRow(0, new Object[] { "1","CSS" ,"1","1"});
//		    tableModel.insertRow(0, new Object[] { "1","HTML5","1","1" });
//		    tableModel.insertRow(0, new Object[] { "1","JavaScript" ,"1","1"});
//		    tableModel.insertRow(0, new Object[] {"1", "jQuery","1" });
//		    tableModel.insertRow(0, new Object[] {"1", "AngularJS","1","1" });
		// adding a new row
//table.setCellSelectionEnabled(false);
		// table.AUTO_RESIZE_LAST_COLUMN;
//		table.setCellSelectionEnabled(true);
//		table.setColumnSelectionAllowed(true);
		// tablica.removeColumnSelectionInterval(0, 2);
		// tablica.
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(132);
		;
//	table.getEditingRow();
//	table.valueChanged(ListSelectionEvent e);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			//	if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					if (table.isCellEditable(row, column)) {

						System.out.println(table.getValueAt(row, column) + " C-" + column + " row-" + row);
			//		}
				//		table.addRowSelectionInterval(index0, index1);
						tableModel.insertRow(tableModel.getRowCount(),
								new Object[] { false, "aa", "bb",
										"cc", "dd" });
				}
			}
		});
		System.out.println("C-" + table.getEditingColumn() + " r-" + table.getEditingRow());

		this.setLayout(null);
		this.add(bDodajBOM).setBounds(5, 5, 150, 20);
		this.add(bDodajSkladnik).setBounds(160, 5, 150, 20);
		this.add(bWyszukajProdukty).setBounds(5, 30, 150, 20);
		this.add(bWyeksportujSkladniki).setBounds(5, 460, 475, 20);
		this.add(bTest2).setBounds(5, 530, 475, 20);
		this.add(textFKodDoWyszukania).setBounds(5, 500, 475, 20);

		this.add(new JScrollPane(table)).setBounds(5, 55, 475, 400);

		bDodajBOM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

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

		bTest2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IOdoBazy zapiszdobazy4 = new IOdoBazy();
				// textFKodDoWyszukania
				//
				// zapiszdobazy.wczytajProdukZPodanymKodem(5);
				zapiszdobazy4.wczytajProdukZPodanymKodem(Integer.parseInt(textFKodDoWyszukania.getText()));
				zapiszdobazy4.getNazwaProduktu();
				try {
					tableModel.insertRow(tableModel.getRowCount(),
							new Object[] { false, zapiszdobazy4.getKodProduktu(), zapiszdobazy4.getNazwaProduktu(),
									zapiszdobazy4.getJednostkaProduktu(), zapiszdobazy4.getOpisProduktu() });
					System.out.println("klik");
				} catch (NumberFormatException e1) {
					System.out.println("bPrzelicz - dupa");
				}

			}
		});
	}

	public void rozwinPozycjeWTablicy() {

	}
}
