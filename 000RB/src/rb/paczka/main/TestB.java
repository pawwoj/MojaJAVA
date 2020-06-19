package rb.paczka.main;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

class TestB extends JPanel {

	
		int i = 0;
		JCheckBox polprodukt = new JCheckBox();
		JTextField kod = new JTextField("wpisz kod");
		JTextField nazwa = new JTextField("wpisz nazw");
		String[] jednostki = {"", "Paleta", "Karton", "Opakowanie", "Sztuka"};
		JComboBox jednostka = new JComboBox(jednostki);
		JButton dodaj = new JButton("Wicej");
		

		
		TestB()
		{
			this.setLayout(null);
			this.add(polprodukt).setBounds(5, 5, 20, 20);
			this.add(kod).setBounds(30, 5, 100, 20);
			this.add(nazwa).setBounds(135, 5, 240, 20);
			this.add(jednostka).setBounds(380, 5, 100, 20);
			this.add(dodaj).setBounds(180, 40+25*9, 100, 20);
			while (i<10)
			{
				dodaj(i);
				i++;
			}

			dodaj.addActionListener (new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dodaj(i);
					dodaj.setBounds(180,  40+25*i,  100, 20);
					i++;
				}
			});
		}
		
		void dodaj(int i)
		{

			JCheckBox skladany = new JCheckBox();
			JTextField kod = new JTextField();
			JTextField ilosc = new JTextField();
			kod.setToolTipText("Tu wpisz kod skladnika");
			skladany.setToolTipText("Zaznacz, jeli skadnik nie jest skadnikiem podstawowym");
			ilosc.setToolTipText("Tu wpisz ilo danego skadnika");
			this.add(skladany).setBounds(5, 40+25*i, 20, 20);
			this.add(kod).setBounds(30, 40+25*i, 100, 20);
			this.add(ilosc).setBounds(135, 40+25*i, 40, 20);
			kod.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					System.out.println(kod.getName());
				}
				
			});
		}
		


	}


