package rb.paczka.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class RBDodajSkladnik extends JPanel {
	JTextField textFNazwa = new JTextField("Wpisz nazwe");
	JTextField textFKod = new JTextField("Wpisz kod");
	String strJednostki[] = { "", "Sztuka", "Kilogram" };
	JComboBox coBoxJednostka = new JComboBox(strJednostki);
	String strRodzaj[] = { "", "Butelka", "Karton", "Surowiec" };
	JComboBox coBoxRodzaj = new JComboBox(strRodzaj);
	JButton butDodaj = new JButton("Dodaj");
	JButton butWstecz = new JButton("Wstecz");
	JTextArea textAOpis = new JTextArea("Wpisz opis");

	RBDodajSkladnik() {
		this.setLayout(null);
		this.add(textFKod).setBounds(5, 5, 100, 20);
		this.add(textFNazwa).setBounds(110, 5, 145, 20);
		this.add(coBoxJednostka).setBounds(260, 5, 100, 20);
		this.add(coBoxRodzaj).setBounds(365, 5, 100, 20);
		this.add(textAOpis).setBounds(5, 30, 250, 250);
		textAOpis.setBorder(BorderFactory.createLineBorder(Color.gray));
		this.add(butDodaj).setBounds(355, 260, 100, 20);
		this.add(butWstecz).setBounds(355, 290, 100, 20);
		zaznacz(textFKod);
		zaznacz(textFNazwa);
		zaznacz(textAOpis);

		butDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IOdoBazy zapiszdobazy = new IOdoBazy();
				zapiszdobazy.zapiszDoBazySkladnik
				(Integer.parseInt(textFKod.getText()), textFNazwa.getText(),
						coBoxJednostka.getSelectedItem().toString(), textAOpis.getText(),
						coBoxRodzaj.getSelectedItem().toString());
			}

		});

		butWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RBMain.setiJPanel(0);
				OknoPoczatkowe.usynWszystko();
			}

		});
	}

	void zaznacz(JTextField nazwa) // zaznacza aktualny tekst
	{
		nazwa.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {

				nazwa.selectAll();
			}

			public void focusLost(FocusEvent arg0) {

			}
		});
	}

	void zaznacz(JTextArea nazwa) // zaznacza aktualny tekst
	{
		nazwa.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {

				nazwa.selectAll();
			}

			public void focusLost(FocusEvent arg0) {

			}
		});
	}

}
