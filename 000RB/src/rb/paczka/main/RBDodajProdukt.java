package rb.paczka.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class RBDodajProdukt extends JPanel {
	int i = 0;
	JTextField textFKod = new JTextField("wpisz kod");
	JTextField textFNazwa = new JTextField("wpisz nazwe");
	String[] strJednostki = { "", "Paleta", "Karton", "Opakowanie", "Sztuka" };
	JComboBox coBoxJednostka = new JComboBox(strJednostki);
	JButton butWiecej = new JButton("Wiecej");
	JButton butTestbaza = new JButton("testbaza");
	JTextField[] textFKody = { new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""),
			new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""),
			new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""),
			new JTextField(""), new JTextField(""), new JTextField("") };
	JTextField[] textFIlosci = { new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""),
			new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""),
			new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""), new JTextField(""),
			new JTextField(""), new JTextField(""), new JTextField("") };
	JTextArea textAOpis = new JTextArea("Wpisz opis");
	JButton butZapisz = new JButton("Zapisz");
	JButton butWstecz = new JButton("Wstecz");
	JLabel labKod = new JLabel("Kody:");
	JLabel labIlosc = new JLabel("Ilosci:");

	RBDodajProdukt() {
		this.setLayout(null);
		this.add(textFKod).setBounds(5, 5, 100, 20);
		this.add(textFNazwa).setBounds(110, 5, 240, 20);
		this.add(coBoxJednostka).setBounds(355, 5, 100, 20);
		this.add(butWiecej).setBounds(50, 300, 100, 20);
		this.add(textAOpis).setBounds(205, 30, 250, 250);
		this.add(labKod).setBounds(25, 25, 100, 20);
		this.add(labIlosc).setBounds(130, 25, 100, 20);
		this.add(butZapisz).setBounds(355, 310, 100, 20);
		this.add(butWstecz).setBounds(355, 525, 100, 20);
		this.add(butTestbaza).setBounds(255, 525, 100, 20);
		textAOpis.setBorder(BorderFactory.createLineBorder(Color.gray));
		zaznacz(textFKod);
		zaznacz(textFNazwa);
		zaznacz(textAOpis);

		butWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RBMain.setiJPanel(0);
				OknoPoczatkowe.usynWszystko();
			}

		});

		butZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IOdoBazy zapiszdobazy = new IOdoBazy();
				zapiszdobazy.zapiszDoBazyProdukt(Integer.parseInt(textFKod.getText()), textFNazwa.getText(),
						coBoxJednostka.getSelectedItem().toString(), textAOpis.getText());

				for (int j = 0; j < 20; j++) {

					if (!(textFKody[j].getText().equals("")) && !(textFIlosci[j].getText().equals(""))) {
						System.out.println("sprawdza skladniki J= " + j);
				//		System.out.println("skladniki J= " + j + "// bomKod : " + bomKod);
						int bomKod = Integer.parseInt(textFKod.getText());
						int sklKod = Integer.parseInt(textFKody[j].getText());
						int sklIlosc = Integer.parseInt(textFIlosci[j].getText());
						System.out.println("skladniki J= " + j + "// bomKod : " + bomKod);
						zapiszdobazy.zapiszDoBazySkladnikDlaBom(bomKod, sklKod, sklIlosc);
					}

				}
			}

		});
		
		butTestbaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IOdoBazy zapiszdobazy = new IOdoBazy();

zapiszdobazy.wczytajProdukISkladniki();
				
			}

		});

		while (i < 10) {
			dodaj(i);
			i++;
		}

		butWiecej.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodaj(i);
				if (i < 19)
					butWiecej.setBounds(50, 75 + 25 * i, 100, 20);
				else
					butWiecej.setBounds(0, 0, 0, 0);
				i++;
			}
		});
	}

	void dodaj(int i) {
		JLabel labNumer = new JLabel();
		int numer = i + 1;
		labNumer.setText("" + numer);
		this.add(labNumer).setBounds(5, 50 + 25 * i, 15, 20);
		;
		textFKody[i].setToolTipText("Tu wpisz kod skladnika");
		textFIlosci[i].setToolTipText("Tu wpisz ilosc danego skladnika");
		this.add(textFKody[i]).setBounds(25, 50 + 25 * i, 100, 20);
		this.add(textFIlosci[i]).setBounds(130, 50 + 25 * i, 40, 20);
		zaznacz(textFKody[i]);
		zaznacz(textFIlosci[i]);
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
