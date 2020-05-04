import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import com.google.gson.Gson;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import java.util.logging.Logger;
import java.util.Properties;
import java.util.Scanner;

import java.text.ParseException;

import java.util.Calendar;

public class Waluty extends JFrame {

	JPanel panel = new JPanel();
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	double kwotaDoPrzeliczenia;
	double[] tZWalutami = new double[5];
	String dataZeStrony = new String("");
	int iRok;
	int iMiesiac;
	int iDzien;
	static String szukanaData = new String(getTodayDate());
	static String szukanaData2 = new String("");
	String cos = "cos";
	String cos2 = "cos";
	String cos3 = "cos";

	double kursWybranej;
	int indexWybranej;
	JLabel lInformator = new JLabel("");
	static boolean jestData = true;
	static boolean teSameDaty = false;
	String choosedate;
	String nbpJson;
	String nbpJson2;
	static int test0 = 0;
	JLabel l0 = new JLabel("");
	JLabel l1 = new JLabel("");
	JLabel l2 = new JLabel("");
	JLabel l3 = new JLabel("");
	JLabel l4 = new JLabel("");

	JLabel[] tLabeli = { l0, l1, l2, l3, l4 };

	JButton bPrzelicz = new JButton("Przelicz");
	JButton bZapiszDoBazy = new JButton("Zapisz");
	JButton bWczytajZBazy = new JButton("Wczytaj");
	JButton bPobierzZDaty = new JButton("Pobierz");
	JTextField ilePrzeliczyc = new JTextField("1.00");
	UtilDateModel model = new UtilDateModel();
	UtilDateModel model2 = new UtilDateModel();
	Properties p = new Properties();
	Properties p2 = new Properties();
	JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
//	JFreeChart fc = new JFreeChart (null);

	ButtonGroup bgWaluty = new ButtonGroup();

	public Waluty() throws IOException, InterruptedException {
		this.setTitle("Waluty");
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width - 265,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 250, 400);
		initComponents();
		this.setDefaultCloseOperation(3);
		this.getContentPane().add(panel);

		bZapiszDoBazy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					zapiszDoBazy();
				} catch (NumberFormatException e1) {
					System.out.println(e1);

				}

			}
		});

		bWczytajZBazy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					wczytajZBazy();

				} catch (NumberFormatException e1) {
					System.out.println("bWczytajZBazy - dupa");
					System.out.println(e1);

				}

			}
		});

		bPrzelicz.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					kwotaDoPrzeliczenia = Double.parseDouble(ilePrzeliczyc.getText());
					zrobLabele(indexWybranej);

				} catch (NumberFormatException e1) {
					System.out.println("bPrzelicz - dupa");
				}

			}
		});

		datePicker.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
				String choosedate = sdft.format(datePicker.getModel().getValue());
				// System.out.println("choosedate" + choosedate);
				szukanaData = choosedate;
				System.out.println("1szukanaData" + szukanaData);
				zPodanejDaty();
			}

		});

		datePicker2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
				String choosedate = sdft.format(datePicker2.getModel().getValue());
				// System.out.println("choosedate" + choosedate);
				szukanaData2 = choosedate;
				System.out.println("2szukanaData" + szukanaData2);
				// zPodanejDaty();
			}

		});

		bPobierzZDaty.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				do {

					do {
						nbpJson = downloadJsonFromURL(
								"http://api.nbp.pl/api/exchangerates/tables/a/" + szukanaData + "?format=json");

						if (!jestData) {
							try {

								szukanaData = minusOneDayCalendar(szukanaData);

							} catch (ParseException e4) {
								System.out.println("CATCH szukanaData = minusOneDayCalendar(choosedate)" + szukanaData);
								e4.printStackTrace();
							}
						}
					} while (!jestData);

					nbpJson2 = nbpJson.substring(1, nbpJson.length() - 1);
					Gson gson = new Gson();
					Currency usdCurrency = gson.fromJson(nbpJson2, Currency.class);
					Currency doDaty = gson.fromJson(nbpJson2, Currency.class);

					dataZeStrony = doDaty.effectiveDate;

					wypelnijIRMD();
					// model.setDate(iRok, iMiesiac - 1, iDzien);
					// model.setSelected(true);

					// tZWalutami[1] = usdCurrency.rates[1].mid;
					dataset.addValue(usdCurrency.rates[1].mid, "dolar", dataZeStrony);
					dataset.addValue(usdCurrency.rates[7].mid, "euro", dataZeStrony);
					dataset.addValue(usdCurrency.rates[10].mid, "funt", dataZeStrony);
					dataset.addValue(usdCurrency.rates[9].mid, "frank", dataZeStrony);

					// dataset.addValue( 300 , "schools" , "2014" );
					try {
						szukanaData = minusOneDayCalendar(szukanaData);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} while (!teSameDaty);

				LineChart_AWT chart = new LineChart_AWT("Wykresy kursu", "wykresy");

				chart.pack();
				// RefineryUtilities.centerFrameOnScreen( chart );
				chart.setVisible(true);

			}
		});
	}

	public void initComponents() throws IOException, InterruptedException {

		zPodanejDaty();

		System.out.println("getTodayDate()" + getTodayDate());
		p.put("text.today", "Dzis");
		p.put("text.month", "Miesiac");
		p.put("text.year", "Rok");

		kwotaDoPrzeliczenia = Double.parseDouble(ilePrzeliczyc.getText());
		panel.setLayout(null);
		panel.add(bPrzelicz).setBounds(5, 5, 80, 20);
		panel.add(bZapiszDoBazy).setBounds(5, 180, 80, 20);
		panel.add(bWczytajZBazy).setBounds(100, 180, 80, 20);
		panel.add(ilePrzeliczyc).setBounds(90, 5, 140, 20);
		ilePrzeliczyc.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(zrobRadioButton("zl", 0)).setBounds(5, 30, 40, 15);
		panel.add(zrobRadioButton("$", 1)).setBounds(5, 60, 40, 15);
		panel.add(zrobRadioButton("€", 2)).setBounds(5, 90, 40, 15);
		panel.add(zrobRadioButton("£", 3)).setBounds(5, 120, 40, 15);
		panel.add(zrobRadioButton("F", 4)).setBounds(5, 150, 40, 15);

		panel.add(tLabeli[0]).setBounds(50, 30, 100, 15);
		panel.add(tLabeli[1]).setBounds(50, 60, 100, 15);
		panel.add(tLabeli[2]).setBounds(50, 90, 100, 15);
		panel.add(tLabeli[3]).setBounds(50, 120, 100, 15);
		panel.add(tLabeli[4]).setBounds(50, 150, 100, 15);
		// jDC.setIcon(img);
		panel.add(datePicker).setBounds(5, 205, 120, 30);
		panel.add(datePicker2).setBounds(5, 240, 120, 30);
		panel.add(bPobierzZDaty).setBounds(130, 205, 100, 25);

		panel.add(lInformator).setBounds(5, 340, 240, 15);

	}

	public static void main(String[] args) throws IOException, InterruptedException {

		new Waluty().setVisible(true);

	}

	public JRadioButton zrobRadioButton(String text, int pozycja) {
		JRadioButton przelacznik = new JRadioButton(text);
		przelacznik.getName();

		przelacznik.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				kursWybranej = tZWalutami[pozycja];
				indexWybranej = pozycja;
				kwotaDoPrzeliczenia = Double.parseDouble(ilePrzeliczyc.getText());

				zrobLabele(pozycja);
				lInformator.setText("Kursywalut z dnia: " + dataZeStrony);
			}
		});

		bgWaluty.add(przelacznik);
		if (pozycja == 0)
			przelacznik.setSelected(true);

		return przelacznik;

	}

	private static double round(double value) {
		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();

	}

	public void zrobLabele(int pozycja) {

		for (int i = 0; i <= 4; i++) {
			tLabeli[i].setText("" + round(kwotaDoPrzeliczenia / tZWalutami[i] * tZWalutami[pozycja]));
		}

	}

	public static String downloadJsonFromURL(String urlText) {

		try {
			if (szukanaData.equals(szukanaData2))
				teSameDaty = true;
			URL myUrl = new URL(urlText);
			StringBuilder jsonText = new StringBuilder();
			try (InputStream myInputStream = myUrl.openStream(); Scanner scanner = new Scanner(myInputStream)) {
				while (scanner.hasNextLine()) {
					jsonText.append(scanner.nextLine());
				}
				jestData = true;
				return jsonText.toString();
			}
		} catch (IOException e) {
			System.out.println("Failed to get content from URL " + urlText + " due to exception: " + e.getMessage());
			test0 = test0 + 1;
			System.out.println("downloadJsonFromURL" + test0);
			jestData = false;
			return null;
		}
	}

	public class Currency {
		public String table;
		public String no;
		public String effectiveDate;
		public Rate[] rates;
	}

	public class Rate {
		public String currency;
		public String code;
		public double mid;
	}

	public static Connection Connect() {
		try {
			String url = "jdbc:mysql://serwer1936875.home.pl/31886298_pw?useSSL=false";
			String user = "31886298_pw";
			String password = "Kloda123";

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;

		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(Waluty.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void wczytajZBazy() {
		Connection conne = Waluty.Connect();
		Statement stmt;
		try {
			stmt = conne.createStatement();
			ResultSet rss = stmt.executeQuery("select * from waluty");
			System.out.println("z bazy danych");
			while (rss.next()) {
				System.out.println(rss.getDate(1) + "  " + rss.getDouble(2) + "  " + rss.getDouble(3) + "  "
						+ rss.getDouble(4) + "  " + rss.getDouble(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void zapiszDoBazy() {
		try {
			Connection conne = Waluty.Connect();

			String query = "INSERT INTO `waluty` (`data`, `usd`, `eur`, `gbp`, `chf`)" + "VALUES (?,?,?,?,?)";
			PreparedStatement preparedStmt = conne.prepareStatement(query);
			preparedStmt.setDate(1, Date.valueOf(dataZeStrony));
			preparedStmt.setDouble(2, tZWalutami[1]);
			preparedStmt.setDouble(3, tZWalutami[2]);
			preparedStmt.setDouble(4, tZWalutami[3]);
			preparedStmt.setDouble(5, tZWalutami[4]);
			preparedStmt.execute();
			conne.close();
			lInformator.setText("Kursy zapisane poprawnie");

		} catch (Exception el) {
			lInformator.setText("Ups. coœ posz³o nie tak.");
			System.out.println(el);

		}
	}

	public class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}

	public static String minusOneDayCalendar(String date) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date));
		c.add(Calendar.DATE, -1);
		return sdf.format(c.getTime());
	}

	public static String getTodayDate() {
		java.util.Date date = java.util.Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public void zPodanejDaty() {

		do {
			nbpJson = downloadJsonFromURL(
					"http://api.nbp.pl/api/exchangerates/tables/a/" + szukanaData + "?format=json");

			if (!jestData) {
				try {

					szukanaData = minusOneDayCalendar(szukanaData);

				} catch (ParseException e) {
					System.out.println("CATCH szukanaData = minusOneDayCalendar(choosedate)" + szukanaData);
					e.printStackTrace();
				}
			}
		} while (!jestData);

		nbpJson2 = nbpJson.substring(1, nbpJson.length() - 1);
		Gson gson = new Gson();
		Currency usdCurrency = gson.fromJson(nbpJson2, Currency.class);
		Currency doDaty = gson.fromJson(nbpJson2, Currency.class);

		dataZeStrony = doDaty.effectiveDate;

		wypelnijIRMD();
		model.setDate(iRok, iMiesiac - 1, iDzien);
		model.setSelected(true);

		tZWalutami[0] = 1;
		tZWalutami[1] = usdCurrency.rates[1].mid;
		tZWalutami[2] = usdCurrency.rates[7].mid;
		tZWalutami[3] = usdCurrency.rates[10].mid;
		tZWalutami[4] = usdCurrency.rates[9].mid;

		for (int i = 0; i <= 4; i++) {
			tLabeli[i].setText("" + round(1 / tZWalutami[i]));
		}

		lInformator.setText("Kursywalut z dnia: " + dataZeStrony);

	}

	public void wypelnijIRMD() {
		iRok = Integer.parseInt(dataZeStrony.substring(0, 4));
		iMiesiac = Integer.parseInt(dataZeStrony.substring(5, 7));
		iDzien = Integer.parseInt(dataZeStrony.substring(8, 10));
	}

	public class LineChart_AWT extends ApplicationFrame {

		public LineChart_AWT(String applicationTitle, String chartTitle) {
			super(applicationTitle);
			JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Czas", "Kurs", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			ChartPanel chartPanel = new ChartPanel(lineChart);
			chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
			setContentPane(chartPanel);
		}

	}
}
