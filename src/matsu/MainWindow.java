package matsu;

import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//import javax.swing.table.TableModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField label_fileNo_1;
	private JTextField label_fileNo_2;
	private JLabel lblCustomerNo_1;
	private JLabel lblCustomerNo_2;
	private JLabel lblOwnCompanyNo_1;
	private JLabel lblOwnCompanyNo_2;
	private JComboBox<String> comboBox_C_1;
	private DefaultComboBoxModel<String> model_C_1;
	private JComboBox<String> comboBox_F_1;
	private DefaultComboBoxModel<String> model_F_1;
	private JComboBox<String> comboBox_Honorific_1;
	private DefaultComboBoxModel<String> model_H_1;
	private JComboBox<String> comboBox_C_2;
	private DefaultComboBoxModel<String> model_C_2;
	private JComboBox<String> comboBox_F_2;
	private DefaultComboBoxModel<String> model_F_2;
	private JComboBox<String> comboBox_Honorific_2;
	private DefaultComboBoxModel<String> model_H_2;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblTradingBank;
	private JLabel label_T_B_A_No;
	private JLabel label_T_B_A_Hol;
	private JLabel label_T_B_BRANCH;
	private JLabel label_T_B_ACCOUNT;
	private JLabel label_T_B_A_HOLDER;
	private JLabel lblAddress_1;
	private JLabel lblAddress_2;
	private JLabel lblZipCode_1;
	private JLabel lblZipCode_2;
	private JLabel lblDivision_1;
	private JLabel lblDivision_2;
	private JLabel lblTel_1;
	private JLabel lblTel_2;
	private JLabel lblFax_1;
	private JLabel lblFax_2;
	private JTextArea textArea_Proviso;
	private JTextField textField_Discount;
	private JLabel label_Discount;
	private JTextField textField_deliveryDate;
	private JTextField textField_constructionSite;
	private JTextField textField_paymentTerms;
	private JTextField textField_expirationDate;
	private JTextField file_date_1;
	private JTextField file_date_2;
	private JTextField file_date_3;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel label_TotalPrice;
	private JTextField textField_TotalPrice;
	public  JLabel label_Tax;
	private JTextField textField_Tax;
	private JLabel label_BeforeTax;
	private JTextField textField_BeforeTax;
	private JLabel lbl_title;
	private String[] F_Array;
	private String[] C_Array;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private Ad ad;
	public static	JComboBox<String> constructionPart;
	public static	JComboBox<String> constructionName;
	public static	DefaultComboBoxModel<String> constpartModel;
	public static	DefaultComboBoxModel<String> constnameModel;
	public PropSet setconst;
	//--------------------------------------------
	org.eclipse.swt.graphics.Color yellow;
	//--------------------------------------------
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mw = new MainWindow();
					mw.initial();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public MainWindow() {
		URL iconUrl = getClass().getResource("clearmaint-Icon.png");
		ImageIcon icon = new ImageIcon(iconUrl);
		setIconImage(icon.getImage());
	}

	/**
	 * Initialize the contents of the frame.
	 * @return
	 */

	public void initial() {
		final Display display = Display.getDefault();
		final Rectangle dispRect = display.getBounds();
		//-----------スプラッシュウィンドウの表示--------------------------------------------//
		final String splashImagePath = "clearmaint.png";
		File file1 = new File(splashImagePath);
		if(file1.exists()){
			new OpenSplashWindow(splashImagePath,display,dispRect);
		}
		int dispHeight = dispRect.height;
		int dispWidth = dispRect.width;
		if(dispHeight != 0){//-------------------------------------Designチェックでレイアウトが崩れないように
			this.setBounds(dispWidth/2 -400, 10, 800, dispHeight - 60);
		}
		//-------------------------------------------------------------
		String honorificData[] = {"","様","御中","宛","殿"};
		comboBoxList(); //--------各コンボボックスの値を取得---------------------
		//---------------------------------------------------------------------------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("帳票印刷");
		setJMenuBar(createMenu());
		JPanel jPanel = new JPanel();
		//----------------------------------------------------------------------------------------------
		JScrollPane scpanel = new JScrollPane(jPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.NORTH, scpanel, 0, SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, scpanel, 0, SpringLayout.SOUTH, getContentPane());
		layout.putConstraint(SpringLayout.EAST, scpanel, 0, SpringLayout.EAST, getContentPane());
		layout.putConstraint(SpringLayout.WEST, scpanel, 0, SpringLayout.WEST, getContentPane());
		getContentPane().add(scpanel);
		//----------------------------------------------------------------------------------------------
		jPanel.setLayout(layout);
		jPanel.setOpaque(true);
		jPanel.setBackground(Color.decode("#dddddd"));
		//----------------------------------------------------------------------------------------------
		JLabel lblCustomer = new JLabel("お客様コードNo.");
		lblCustomer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomer.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		layout.putConstraint(SpringLayout.NORTH, lblCustomer, 80, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, lblCustomer, 100, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.EAST, lblCustomer, 110, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.WEST, lblCustomer, 20, SpringLayout.WEST, jPanel);
		jPanel.add(lblCustomer);
		//----------------------------------------------------------------------------------------------
		JLabel lblOwnCompany = new JLabel("自社コードNo.");
		lblOwnCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOwnCompany.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		layout.putConstraint(SpringLayout.NORTH, lblOwnCompany, 0, SpringLayout.SOUTH, lblCustomer);
		layout.putConstraint(SpringLayout.SOUTH, lblOwnCompany, 20, SpringLayout.SOUTH, lblCustomer);
		layout.putConstraint(SpringLayout.EAST, lblOwnCompany, 110, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.WEST, lblOwnCompany, 20, SpringLayout.WEST, jPanel);
		jPanel.add(lblOwnCompany);
		//----------------------------------------------------------------------------------------------
		JButton btnNewButton = new JButton("印刷");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						PrintData printData = new PrintData();
						new PrintSample(lbl_title.getText(),printData.printDataList1(),printData.printDataList2(),ad.taxType,checkBox_1.isSelected(),checkBox_2.isSelected(),checkBox_3.isSelected());
					}
				});
			}
		});
		layout.putConstraint(SpringLayout.WEST, btnNewButton, 10, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.EAST, btnNewButton, 100, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.NORTH, btnNewButton, 5, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnNewButton, 35, SpringLayout.NORTH, jPanel);
		jPanel.add(btnNewButton);
		//----------------------------------------------------------------------------------------------
		label_fileNo_1 = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, label_fileNo_1, 5, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.WEST, label_fileNo_1, -110, SpringLayout.EAST, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, label_fileNo_1, 25, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.EAST, label_fileNo_1, -60, SpringLayout.EAST, jPanel);
		label_fileNo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_fileNo_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
		jPanel.add(label_fileNo_1);
		//----------------------------------------------------------------------------------------------
		label_fileNo_2 = new JTextField();
		label_fileNo_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_fileNo_2.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
		layout.putConstraint(SpringLayout.WEST, label_fileNo_2, 20, SpringLayout.EAST, label_fileNo_1);
		layout.putConstraint(SpringLayout.SOUTH, label_fileNo_2, 0, SpringLayout.SOUTH, label_fileNo_1);
		layout.putConstraint(SpringLayout.EAST, label_fileNo_2, -5, SpringLayout.EAST, jPanel);
		layout.putConstraint(SpringLayout.NORTH, label_fileNo_2, 0, SpringLayout.NORTH, label_fileNo_1);
		jPanel.add(label_fileNo_2);
		//----------------------------------------------------------------------------------------------
		lbl_title = new JLabel("");
		layout.putConstraint(SpringLayout.NORTH, lbl_title, 80, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.WEST, lbl_title, 200, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, lbl_title, 110, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.EAST, lbl_title, -200, SpringLayout.EAST, jPanel);
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("MS UI Gothic", Font.PLAIN, 24));
		jPanel.add(lbl_title);
		//----------------------------------------------------------------------------------------------
		JLayeredPane layeredPane = new JLayeredPane();
		layout.putConstraint(SpringLayout.NORTH, layeredPane, 50, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, layeredPane, 260, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.WEST, layeredPane, 0, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.EAST, layeredPane, 0, SpringLayout.EAST, jPanel);
		jPanel.add(layeredPane);
		//----------------------------------------------------------------------------------------------
		JButton btnNewButton_1 = new JButton("データ読込");
		layout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
		layout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 0, SpringLayout.SOUTH, btnNewButton);
		layout.putConstraint(SpringLayout.EAST, btnNewButton_1, 105, SpringLayout.EAST, btnNewButton);
		layout.putConstraint(SpringLayout.WEST, btnNewButton_1, 5, SpringLayout.EAST, btnNewButton);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FileSelect dialog = new FileSelect();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					if(dialog.filepath != null){
						new ReadFile(dialog.filepath);
					}
					set_position(3,lbl_title,layeredPane,true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		jPanel.add(btnNewButton_1);
		//----------------------------------------------------------------------------------------------
		JButton btnNewButton_2 = new JButton("請求書");
		layout.putConstraint(SpringLayout.WEST, btnNewButton_2, 220, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.EAST, btnNewButton_2, 320, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.NORTH, btnNewButton_2, 5, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnNewButton_2, 35, SpringLayout.NORTH, jPanel);
		btnNewButton_2.setEnabled(true);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				set_position(2,lbl_title,layeredPane,true);
				new SetTotal();
				divisionCheck();
				ad.taxTypeSet(ad.taxTypeText);
			}
		});
		jPanel.add(btnNewButton_2);
		//----------------------------------------------------------------------------------------------
		JButton btnNewButton_3 = new JButton("見積書");
		layout.putConstraint(SpringLayout.EAST, btnNewButton_3, 110, SpringLayout.EAST, btnNewButton_2);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				set_position(3,lbl_title,layeredPane,true);
				new SetTotal();
				divisionCheck();
				ad.taxTypeSet(ad.taxTypeText);
			}
		});
		layout.putConstraint(SpringLayout.NORTH, btnNewButton_3, 0, SpringLayout.NORTH, btnNewButton_2);
		layout.putConstraint(SpringLayout.WEST, btnNewButton_3, 10, SpringLayout.EAST, btnNewButton_2);
		layout.putConstraint(SpringLayout.SOUTH, btnNewButton_3, 0, SpringLayout.SOUTH, btnNewButton_2);
		btnNewButton_3.setEnabled(true);
		jPanel.add(btnNewButton_3);
		//----------------------------------------------------------------------------------------------
		JButton btnNewButton_4 = new JButton("納品書");
		layout.putConstraint(SpringLayout.EAST, btnNewButton_4, 110, SpringLayout.EAST, btnNewButton_3);
		btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					set_position(1,lbl_title,layeredPane,true);
					new SetTotal();
					divisionCheck();
					ad.taxTypeSet(ad.taxTypeText);
				}
			});
		layout.putConstraint(SpringLayout.NORTH, btnNewButton_4, 0, SpringLayout.NORTH, btnNewButton_3);
		layout.putConstraint(SpringLayout.WEST, btnNewButton_4, 10, SpringLayout.EAST, btnNewButton_3);
		layout.putConstraint(SpringLayout.SOUTH, btnNewButton_4, 0, SpringLayout.SOUTH, btnNewButton_3);
		btnNewButton_4.setEnabled(true);
		jPanel.add(btnNewButton_4);
		//----------------------------------------------------------------------------------------------
		JButton button_new = new JButton("新規作成");
		button_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String reg = "^[^0-9]*([0-9]*$)";
				Pattern p = Pattern.compile(reg);
				try {
					File file = new File("number.DAT");
					if(checkBeforeReadfile(file)){
						FileInputStream brFile = new FileInputStream(file);
						InputStreamReader brFileUtf = new InputStreamReader(brFile,"UTF-8");
						BufferedReader  br = new BufferedReader(brFileUtf);
						String str = null;
						Matcher m = p.matcher(br.readLine());
						if(m.find())str = m.group(1);
						int intNo = Integer.valueOf(str).intValue();
						intNo += 1;
						br.close();
						String intStr = String.valueOf(intNo);
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));
						bw.write(intStr);
						bw.close();
						newData(intStr);
					}else{
						System.out.println("ファイルが見つからないか開けません");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e){
					System.out.println(e);
				}

				String titleText = lbl_title.getText();
				switch (titleText) {
				case "見　積　書":
					set_position(3,lbl_title,layeredPane,false);
					break;
				case "請　求　書":
					set_position(2,lbl_title,layeredPane,false);
					break;
				case "納　品　書":
					set_position(1,lbl_title,layeredPane,false);
					break;
				default:
					break;
				}
			}
		});
		layout.putConstraint(SpringLayout.WEST, button_new, 20, SpringLayout.EAST, btnNewButton_4);
		layout.putConstraint(SpringLayout.EAST, button_new, 120, SpringLayout.EAST, btnNewButton_4);
		layout.putConstraint(SpringLayout.NORTH, button_new, 0, SpringLayout.NORTH, btnNewButton_4);
		layout.putConstraint(SpringLayout.SOUTH, button_new, 0, SpringLayout.SOUTH, btnNewButton_4);
		jPanel.add(button_new);
		//----------------------------------------------------------------------------------------------
		JButton button_new_number = new JButton("新規番号");
		button_new_number.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String reg = "^[^0-9]*([0-9]*$)";
				Pattern p = Pattern.compile(reg);
				try {
					File file = new File("number.DAT");
					if(checkBeforeReadfile(file)){
						FileInputStream brFile = new FileInputStream(file);
						InputStreamReader brFileUtf = new InputStreamReader(brFile,"UTF-8");
						BufferedReader  br = new BufferedReader(brFileUtf);
						String str = null;
						Matcher m = p.matcher(br.readLine());
						if(m.find())str = m.group(1);
						int intNo = Integer.valueOf(str).intValue();
						label_fileNo_1.setText(String.valueOf(intNo));
						label_fileNo_2.setText("");
						intNo += 1;
						br.close();
						String intStr = String.valueOf(intNo);
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));
						bw.write(intStr);
						bw.close();
					}else{
						System.out.println("ファイルが見つからないか開けません");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e){
					System.out.println(e);
				}

				String titleText = lbl_title.getText();
				switch (titleText) {
				case "見　積　書":
					set_position(3,lbl_title,layeredPane,false);
					break;
				case "請　求　書":
					set_position(2,lbl_title,layeredPane,false);
					break;
				case "納　品　書":
					set_position(1,lbl_title,layeredPane,false);
					break;
				default:
					break;
				}
			}
		});
		layout.putConstraint(SpringLayout.NORTH, button_new_number, 2, SpringLayout.SOUTH, label_fileNo_1);
		layout.putConstraint(SpringLayout.WEST, button_new_number, 0, SpringLayout.WEST, label_fileNo_1);
		layout.putConstraint(SpringLayout.SOUTH, button_new_number, 22, SpringLayout.SOUTH, label_fileNo_1);
		layout.putConstraint(SpringLayout.EAST, button_new_number, 0, SpringLayout.EAST, label_fileNo_2);
		jPanel.add(button_new_number);
		//----------------------------------------------------------------------------------------------
		SpringLayout layout2 = new SpringLayout();
		layeredPane.setLayout(layout2);
		//-------------------------------------------------備考欄-----------------------------------------------------------------
		textArea_Proviso = new JTextArea();
		layout2.putConstraint(SpringLayout.NORTH, textArea_Proviso, 121, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.SOUTH, textArea_Proviso, 209, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.WEST, textArea_Proviso, 480, SpringLayout.WEST, layeredPane);
		layout2.putConstraint(SpringLayout.EAST, textArea_Proviso, -1, SpringLayout.EAST, layeredPane);
		layeredPane.add(textArea_Proviso);
		layeredPane.setPosition(textArea_Proviso, 0);

		JLabel bikouLabel = new JLabel();
		bikouLabel.setText("備考");
		layout2.putConstraint(SpringLayout.NORTH, bikouLabel, 0, SpringLayout.NORTH, textArea_Proviso);
		layout2.putConstraint(SpringLayout.SOUTH, bikouLabel, 20, SpringLayout.NORTH, textArea_Proviso);
		layout2.putConstraint(SpringLayout.WEST, bikouLabel, -30, SpringLayout.WEST, textArea_Proviso);
		layout2.putConstraint(SpringLayout.EAST, bikouLabel, 0, SpringLayout.WEST, textArea_Proviso);
		layeredPane.add(bikouLabel);
		layeredPane.setPosition(bikouLabel, 1);
		//---------------------------------------------------納品書用レイアウト-------------------------------------------------
		panel_5 = new JPanel();
		layout2.putConstraint(SpringLayout.NORTH, panel_5, 25, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.WEST, panel_5, 0, SpringLayout.WEST, layeredPane);
		layout2.putConstraint(SpringLayout.SOUTH, panel_5, 120, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.EAST, panel_5, 0, SpringLayout.EAST, layeredPane);
		panel_5.setBackground(Color.lightGray);
		layeredPane.add(panel_5);

		SpringLayout layout2_2 = new SpringLayout();
		panel_5.setLayout(layout2_2);
		//----------------------------------------------------------------------------------------------
		lblCustomerNo_2 = new JLabel();
		layout2_2.putConstraint(SpringLayout.NORTH, lblCustomerNo_2, 6, SpringLayout.NORTH, panel_5);
		layout2_2.putConstraint(SpringLayout.WEST, lblCustomerNo_2, 115, SpringLayout.WEST, panel_5);
		layout2_2.putConstraint(SpringLayout.SOUTH, lblCustomerNo_2, 24, SpringLayout.NORTH, panel_5);
		layout2_2.putConstraint(SpringLayout.EAST, lblCustomerNo_2, 205, SpringLayout.WEST, panel_5);
		panel_5.add(lblCustomerNo_2);
		//----------------------------------------------------------------------------------------------
		lblOwnCompanyNo_2 = new JLabel();
		layout2_2.putConstraint(SpringLayout.WEST, lblOwnCompanyNo_2, 0, SpringLayout.WEST, lblCustomerNo_2);
		layout2_2.putConstraint(SpringLayout.EAST, lblOwnCompanyNo_2, 0, SpringLayout.EAST, lblCustomerNo_2);
		layout2_2.putConstraint(SpringLayout.NORTH, lblOwnCompanyNo_2, 0, SpringLayout.SOUTH, lblCustomerNo_2);
		layout2_2.putConstraint(SpringLayout.SOUTH, lblOwnCompanyNo_2, 20, SpringLayout.SOUTH, lblCustomerNo_2);
		panel_5.add(lblOwnCompanyNo_2);
		//----------------------------------------------------------------------------------------------
		model_F_2 = new DefaultComboBoxModel<String>(F_Array);
		comboBox_F_2 = new JComboBox<String>(model_F_2);
		comboBox_F_2.setEditable(true);
		comboBox_F_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//------------------------クライアント名が見つからない場合は登録するかたずねる--------------------------------------------------------------
				if(e.getSource() == comboBox_F_2){
					clientName(comboBox_F_2,comboBox_F_1,lblCustomerNo_2,e);
				}
			}
		});
		layout2_2.putConstraint(SpringLayout.NORTH, comboBox_F_2, 50, SpringLayout.NORTH, panel_5);
		layout2_2.putConstraint(SpringLayout.SOUTH, comboBox_F_2, 70, SpringLayout.NORTH, panel_5);
		layout2_2.putConstraint(SpringLayout.WEST, comboBox_F_2, 0, SpringLayout.WEST, panel_5);
		layout2_2.putConstraint(SpringLayout.EAST, comboBox_F_2, 200, SpringLayout.WEST, panel_5);
		panel_5.add(comboBox_F_2);
		//----------------------------------------------------------------------------------------------
		model_H_2 = new DefaultComboBoxModel<String>(honorificData);
		comboBox_Honorific_2 = new JComboBox<String>(model_H_2);
		layout2_2.putConstraint(SpringLayout.NORTH, comboBox_Honorific_2, 0, SpringLayout.NORTH, comboBox_F_2);
		layout2_2.putConstraint(SpringLayout.SOUTH, comboBox_Honorific_2, 0, SpringLayout.SOUTH, comboBox_F_2);
		layout2_2.putConstraint(SpringLayout.EAST, comboBox_Honorific_2, 60, SpringLayout.EAST, comboBox_F_2);
		layout2_2.putConstraint(SpringLayout.WEST, comboBox_Honorific_2, 0, SpringLayout.EAST, comboBox_F_2);
		panel_5.add(comboBox_Honorific_2);
		//----------------------------------------------------------------------------------------------
		model_C_2 = new DefaultComboBoxModel<String>(C_Array);
		comboBox_C_2 = new JComboBox<String>(model_C_2);
		comboBox_C_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboBox_C_2.getSelectedItem().equals("")){
					String[] dbreData = new DbConnect().dBReText("C_DATA","C_NAME",(String)comboBox_C_2.getSelectedItem());
					lblOwnCompanyNo_2.setText(dbreData[1]);
					lblZipCode_2.setText("〒 "+dbreData[4]);
					lblAddress_2.setText(dbreData[5]);
					lblDivision_2.setText(dbreData[3]);
					lblTel_2.setText("TEL\u0020"+dbreData[6]);
					lblFax_2.setText("FAX\u0020"+dbreData[7]);
					//lblTel_2.setText(dbreData[6]);
					//lblFax_2.setText(dbreData[7]);
				}else{
					lblOwnCompanyNo_2.setText("");
					lblZipCode_2.setText("");
					lblAddress_2.setText("");
					lblDivision_2.setText("");
					lblTel_2.setText("");
					lblFax_2.setText("");
				}
			}
		});
		layout2_2.putConstraint(SpringLayout.NORTH, comboBox_C_2, 38, SpringLayout.NORTH, panel_5);
		layout2_2.putConstraint(SpringLayout.SOUTH, comboBox_C_2, 58, SpringLayout.NORTH, panel_5);
		layout2_2.putConstraint(SpringLayout.WEST, comboBox_C_2, -220, SpringLayout.EAST, panel_5);
		layout2_2.putConstraint(SpringLayout.EAST, comboBox_C_2, -20, SpringLayout.EAST, panel_5);
		panel_5.add(comboBox_C_2);
		//----------------------------------------------------------------------------------------------
		lblZipCode_2 = new JLabel();
		layout2_2.putConstraint(SpringLayout.NORTH, lblZipCode_2, 2, SpringLayout.NORTH, panel_5);
		layout2_2.putConstraint(SpringLayout.WEST, lblZipCode_2, -220, SpringLayout.EAST, panel_5);
		layout2_2.putConstraint(SpringLayout.SOUTH, lblZipCode_2, 20, SpringLayout.NORTH, panel_5);
		layout2_2.putConstraint(SpringLayout.EAST, lblZipCode_2, -50, SpringLayout.EAST, panel_5);
		lblZipCode_2.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		panel_5.add(lblZipCode_2);
		//----------------------------------------------------------------------------------------------
		lblAddress_2 = new JLabel();
		layout2_2.putConstraint(SpringLayout.NORTH, lblAddress_2, 0, SpringLayout.SOUTH, lblZipCode_2);
		layout2_2.putConstraint(SpringLayout.WEST, lblAddress_2, -220, SpringLayout.EAST, panel_5);
		layout2_2.putConstraint(SpringLayout.SOUTH, lblAddress_2, 18, SpringLayout.SOUTH, lblZipCode_2);
		layout2_2.putConstraint(SpringLayout.EAST, lblAddress_2, 0, SpringLayout.EAST, panel_5);
		lblAddress_2.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		panel_5.add(lblAddress_2);
		//----------------------------------------------------------------------------------------------
		lblDivision_2 = new JLabel();
		layout2_2.putConstraint(SpringLayout.NORTH, lblDivision_2, 20, SpringLayout.SOUTH, lblAddress_2);
		layout2_2.putConstraint(SpringLayout.WEST, lblDivision_2, -220, SpringLayout.EAST, panel_5);
		layout2_2.putConstraint(SpringLayout.SOUTH, lblDivision_2, 38, SpringLayout.SOUTH, lblAddress_2);
		layout2_2.putConstraint(SpringLayout.EAST, lblDivision_2, 0, SpringLayout.EAST, panel_5);
		lblDivision_2.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		panel_5.add(lblDivision_2);
		//----------------------------------------------------------------------------------------------
		lblTel_2 = new JLabel();
		layout2_2.putConstraint(SpringLayout.NORTH, lblTel_2, 0, SpringLayout.SOUTH, lblDivision_2);
		layout2_2.putConstraint(SpringLayout.WEST, lblTel_2, 0, SpringLayout.WEST, lblDivision_2);
		layout2_2.putConstraint(SpringLayout.SOUTH, lblTel_2, 18, SpringLayout.SOUTH, lblDivision_2);
		layout2_2.putConstraint(SpringLayout.EAST, lblTel_2, -100, SpringLayout.EAST, panel_5);
		lblTel_2.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		panel_5.add(lblTel_2);
		//----------------------------------------------------------------------------------------------
		lblFax_2 = new JLabel();
		layout2_2.putConstraint(SpringLayout.NORTH, lblFax_2, 0, SpringLayout.SOUTH, lblDivision_2);
		layout2_2.putConstraint(SpringLayout.WEST, lblFax_2, 0, SpringLayout.EAST, lblTel_2);
		layout2_2.putConstraint(SpringLayout.SOUTH, lblFax_2, 18, SpringLayout.SOUTH, lblDivision_2);
		layout2_2.putConstraint(SpringLayout.EAST, lblFax_2, 100, SpringLayout.EAST, lblTel_2);
		lblFax_2.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		panel_5.add(lblFax_2);
		//----------------------------------------------------------------------------------------------
		panel_3 = new JPanel();
		layout2.putConstraint(SpringLayout.NORTH, panel_3, 0, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.SOUTH, panel_3, 0, SpringLayout.SOUTH, layeredPane);
		layout2.putConstraint(SpringLayout.WEST, panel_3, 0, SpringLayout.WEST, layeredPane);
		layout2.putConstraint(SpringLayout.EAST, panel_3, 0, SpringLayout.EAST, layeredPane);
		panel_3.setBackground(Color.GRAY);
		layeredPane.add(panel_3);

		SpringLayout layout5 = new SpringLayout();
		panel_3.setLayout(layout5);
		//-----------------------------------請求書用日付--------------------------------------------------------------------------------
		file_date_3 = new JTextField();
		file_date_3.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
		layout5.putConstraint(SpringLayout.NORTH, file_date_3, 0, SpringLayout.NORTH, panel_3);
		layout5.putConstraint(SpringLayout.WEST, file_date_3, -206, SpringLayout.EAST, panel_3);
		layout5.putConstraint(SpringLayout.SOUTH, file_date_3, 25, SpringLayout.NORTH, panel_3);
		layout5.putConstraint(SpringLayout.EAST, file_date_3, 0, SpringLayout.EAST, panel_3);
		panel_3.add(file_date_3);
		Locale locale = new Locale("ja","JP","JP");
		DateFormat sdf = new SimpleDateFormat("GGGGy年MM月dd日",locale);
		file_date_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newText3 = file_date_3.getText();
				if (newText3.indexOf("/") >= 0){
					try {
						file_date_3.setText(sdf.format(DateFormat.getDateInstance().parse(newText3)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else{
					file_date_3.setText(newText3);
				}
			}
		});
		file_date_3.setColumns(10);
		//--------------------------------------------------請求書及び見積書用レイアウト-------------------------------------------------
		panel_4 = new JPanel();
		layout2.putConstraint(SpringLayout.NORTH, panel_4, 25, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.SOUTH, panel_4, 120, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.WEST, panel_4, 0, SpringLayout.WEST, layeredPane);
		layout2.putConstraint(SpringLayout.EAST, panel_4, 0, SpringLayout.EAST, layeredPane);
		panel_4.setBackground(Color.LIGHT_GRAY);
		layeredPane.add(panel_4);

		SpringLayout layout2_1 = new SpringLayout();
		panel_4.setLayout(layout2_1);
		//----------------------------------------------------------------------------------------------
		lblCustomerNo_1 = new JLabel();
		layout2_1.putConstraint(SpringLayout.NORTH, lblCustomerNo_1, 6, SpringLayout.NORTH, panel_5);
		layout2_1.putConstraint(SpringLayout.WEST, lblCustomerNo_1, 115, SpringLayout.WEST, panel_5);
		layout2_1.putConstraint(SpringLayout.SOUTH, lblCustomerNo_1, 24, SpringLayout.NORTH, panel_5);
		layout2_1.putConstraint(SpringLayout.EAST, lblCustomerNo_1, 205, SpringLayout.WEST, panel_5);
		panel_4.add(lblCustomerNo_1);
		//----------------------------------------------------------------------------------------------
		lblOwnCompanyNo_1 = new JLabel();
		layout2_1.putConstraint(SpringLayout.WEST, lblOwnCompanyNo_1, 0, SpringLayout.WEST, lblCustomerNo_1);
		layout2_1.putConstraint(SpringLayout.EAST, lblOwnCompanyNo_1, 0, SpringLayout.EAST, lblCustomerNo_1);
		layout2_1.putConstraint(SpringLayout.NORTH, lblOwnCompanyNo_1, 0, SpringLayout.SOUTH, lblCustomerNo_1);
		layout2_1.putConstraint(SpringLayout.SOUTH, lblOwnCompanyNo_1, 20, SpringLayout.SOUTH, lblCustomerNo_1);
		panel_4.add(lblOwnCompanyNo_1);
		//----------------------------------------------------------------------------------------------
		model_F_1 = new DefaultComboBoxModel<String>(F_Array);
		comboBox_F_1 = new JComboBox<String>(model_F_1);
		comboBox_F_1.setEditable(true);
		comboBox_F_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//------------------------クライアント名が見つからない場合は登録するかたずねる--------------------------------------------------------------
				if(e.getSource() == comboBox_F_1){
					clientName(comboBox_F_1,comboBox_F_2,lblCustomerNo_1,e);
				}
			}
		});
		layout2_1.putConstraint(SpringLayout.NORTH, comboBox_F_1, 50, SpringLayout.NORTH, panel_4);
		layout2_1.putConstraint(SpringLayout.SOUTH, comboBox_F_1, 70, SpringLayout.NORTH, panel_4);
		layout2_1.putConstraint(SpringLayout.WEST, comboBox_F_1, 0, SpringLayout.WEST, panel_4);
		layout2_1.putConstraint(SpringLayout.EAST, comboBox_F_1, 200, SpringLayout.WEST, panel_4);
		panel_4.add(comboBox_F_1);
		//----------------------------------------------------------------------------------------------
		model_H_1 = new DefaultComboBoxModel<String>(honorificData);
		comboBox_Honorific_1 = new JComboBox<String>(model_H_1);
		layout2_1.putConstraint(SpringLayout.NORTH, comboBox_Honorific_1, 0, SpringLayout.NORTH, comboBox_F_1);
		layout2_1.putConstraint(SpringLayout.SOUTH, comboBox_Honorific_1, 0, SpringLayout.SOUTH, comboBox_F_1);
		layout2_1.putConstraint(SpringLayout.EAST, comboBox_Honorific_1, 60, SpringLayout.EAST, comboBox_F_1);
		layout2_1.putConstraint(SpringLayout.WEST, comboBox_Honorific_1, 0, SpringLayout.EAST, comboBox_F_1);
		panel_4.add(comboBox_Honorific_1);
		//----------------------------------------------------------------------------------------------
		model_C_1 = new DefaultComboBoxModel<String>(C_Array);
		comboBox_C_1 = new JComboBox<String>(model_C_1);
		comboBox_C_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboBox_C_1.getSelectedItem().equals("")){
					String[] dbreData = new DbConnect().dBReText("C_DATA","C_NAME",(String)comboBox_C_1.getSelectedItem());
					lblOwnCompanyNo_1.setText(dbreData[1]);
					label_T_B_BRANCH.setText(dbreData[8]+ "\u3000" + dbreData[9]);
					label_T_B_ACCOUNT.setText(dbreData[10]+ "\u3000" + dbreData[11]);
					label_T_B_A_HOLDER.setText(dbreData[12]);
					lblZipCode_1.setText("〒 "+dbreData[4]);
					lblAddress_1.setText(dbreData[5]);
					lblDivision_1.setText(dbreData[3]);
					lblTel_1.setText("TEL\u0020"+dbreData[6]);
					lblFax_1.setText("FAX\u0020"+dbreData[7]);
					//lblTel_1.setText(dbreData[6]);
					//lblFax_1.setText(dbreData[7]);
				}else{
					lblOwnCompanyNo_1.setText("");
					label_T_B_BRANCH.setText("");
					label_T_B_ACCOUNT.setText("");
					label_T_B_A_HOLDER.setText("");
					lblZipCode_1.setText("");
					lblAddress_1.setText("");
					lblDivision_1.setText("");
					lblTel_1.setText("");
					lblFax_1.setText("");
				}
			}
		});
		layout2_1.putConstraint(SpringLayout.NORTH, comboBox_C_1, 38, SpringLayout.NORTH, panel_4);
		layout2_1.putConstraint(SpringLayout.SOUTH, comboBox_C_1, 58, SpringLayout.NORTH, panel_4);
		layout2_1.putConstraint(SpringLayout.WEST, comboBox_C_1, -220, SpringLayout.EAST, panel_4);
		layout2_1.putConstraint(SpringLayout.EAST, comboBox_C_1, -20, SpringLayout.EAST, panel_4);
		panel_4.add(comboBox_C_1);
		//----------------------------------------------------------------------------------------------
		lblZipCode_1 = new JLabel();
		lblZipCode_1.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		layout2_1.putConstraint(SpringLayout.NORTH, lblZipCode_1, 2, SpringLayout.NORTH, panel_4);
		layout2_1.putConstraint(SpringLayout.WEST, lblZipCode_1, -220, SpringLayout.EAST, panel_4);
		layout2_1.putConstraint(SpringLayout.SOUTH, lblZipCode_1, 20, SpringLayout.NORTH, panel_4);
		layout2_1.putConstraint(SpringLayout.EAST, lblZipCode_1, -50, SpringLayout.EAST, panel_4);
		panel_4.add(lblZipCode_1);
		//----------------------------------------------------------------------------------------------
		lblAddress_1 = new JLabel();
		lblAddress_1.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		layout2_1.putConstraint(SpringLayout.NORTH, lblAddress_1, 0, SpringLayout.SOUTH, lblZipCode_1);
		layout2_1.putConstraint(SpringLayout.WEST, lblAddress_1, -220, SpringLayout.EAST, panel_4);
		layout2_1.putConstraint(SpringLayout.SOUTH, lblAddress_1, 18, SpringLayout.SOUTH, lblZipCode_1);
		layout2_1.putConstraint(SpringLayout.EAST, lblAddress_1, 0, SpringLayout.EAST, panel_4);
		panel_4.add(lblAddress_1);
		//----------------------------------------------------------------------------------------------
		lblDivision_1 = new JLabel();
		layout2_1.putConstraint(SpringLayout.NORTH, lblDivision_1, 20, SpringLayout.SOUTH, lblAddress_1);
		layout2_1.putConstraint(SpringLayout.WEST, lblDivision_1, -220, SpringLayout.EAST, panel_4);
		layout2_1.putConstraint(SpringLayout.SOUTH, lblDivision_1, 38, SpringLayout.SOUTH, lblAddress_1);
		layout2_1.putConstraint(SpringLayout.EAST, lblDivision_1, 0, SpringLayout.EAST, panel_4);
		lblDivision_1.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		panel_4.add(lblDivision_1);
		//----------------------------------------------------------------------------------------------
		lblTel_1 = new JLabel();
		lblTel_1.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		layout2_1.putConstraint(SpringLayout.NORTH, lblTel_1, 0, SpringLayout.SOUTH, lblDivision_1);
		layout2_1.putConstraint(SpringLayout.WEST, lblTel_1, 0, SpringLayout.WEST, lblDivision_1);
		layout2_1.putConstraint(SpringLayout.SOUTH, lblTel_1, 18, SpringLayout.SOUTH, lblDivision_1);
		layout2_1.putConstraint(SpringLayout.EAST, lblTel_1, -100, SpringLayout.EAST, panel_4);
		panel_4.add(lblTel_1);
		//----------------------------------------------------------------------------------------------
		lblFax_1 = new JLabel();
		lblFax_1.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		layout2_1.putConstraint(SpringLayout.NORTH, lblFax_1, 0, SpringLayout.SOUTH, lblDivision_1);
		layout2_1.putConstraint(SpringLayout.WEST, lblFax_1, 0, SpringLayout.EAST, lblTel_1);
		layout2_1.putConstraint(SpringLayout.SOUTH, lblFax_1, 18, SpringLayout.SOUTH, lblDivision_1);
		layout2_1.putConstraint(SpringLayout.EAST, lblFax_1, 100, SpringLayout.EAST, lblTel_1);
		panel_4.add(lblFax_1);
		//-----------------------------------------見積書用レイアウト------------------------------------------------------------------
		panel_2 = new JPanel();
		layout2.putConstraint(SpringLayout.NORTH, panel_2, 0, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, layeredPane);
		layout2.putConstraint(SpringLayout.SOUTH, panel_2, 0, SpringLayout.SOUTH, layeredPane);
		layout2.putConstraint(SpringLayout.EAST, panel_2, 0, SpringLayout.EAST, layeredPane);
		layeredPane.add(panel_2);
		panel_2.setBackground(Color.ORANGE);

		SpringLayout layout4 = new SpringLayout();
		panel_2.setLayout(layout4);
		//-----------------------------------見積書用日付--------------------------------------------------------------------------------
		file_date_2 = new JTextField();
		file_date_2.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
		layout4.putConstraint(SpringLayout.NORTH, file_date_2, 0, SpringLayout.NORTH, panel_2);
		layout4.putConstraint(SpringLayout.WEST, file_date_2, -206, SpringLayout.EAST, panel_2);
		layout4.putConstraint(SpringLayout.SOUTH, file_date_2, 25, SpringLayout.NORTH, panel_2);
		layout4.putConstraint(SpringLayout.EAST, file_date_2, 0, SpringLayout.EAST, panel_2);
		panel_2.add(file_date_2);
		file_date_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newText2 = file_date_2.getText();
				if (newText2.indexOf("/") >= 0){
					try {
						file_date_2.setText(sdf.format(DateFormat.getDateInstance().parse(newText2)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else{
					file_date_3.setText(newText2);
				}
			}
		});
		file_date_2.setColumns(10);
		//-------------------------------------------------------------------------------------------------------------------
		JLabel label_deliveryDate = new JLabel("１．引渡期日");
		layout4.putConstraint(SpringLayout.NORTH, label_deliveryDate, 120, SpringLayout.NORTH, panel_2);
		layout4.putConstraint(SpringLayout.WEST, label_deliveryDate, 20, SpringLayout.WEST, panel_2);
		layout4.putConstraint(SpringLayout.SOUTH, label_deliveryDate, 140, SpringLayout.NORTH, panel_2);
		layout4.putConstraint(SpringLayout.EAST, label_deliveryDate, 100, SpringLayout.WEST, panel_2);
		panel_2.add(label_deliveryDate);
		//----------------------------------------------------------------------------------------------
		textField_deliveryDate = new JTextField();
		layout4.putConstraint(SpringLayout.NORTH, textField_deliveryDate, 0, SpringLayout.NORTH, label_deliveryDate);
		layout4.putConstraint(SpringLayout.SOUTH, textField_deliveryDate, 0, SpringLayout.SOUTH, label_deliveryDate);
		layout4.putConstraint(SpringLayout.WEST, textField_deliveryDate, 0, SpringLayout.EAST, label_deliveryDate);
		layout4.putConstraint(SpringLayout.EAST, textField_deliveryDate, 300, SpringLayout.EAST, label_deliveryDate);
		panel_2.add(textField_deliveryDate);
		textField_deliveryDate.setColumns(30);
		//----------------------------------------------------------------------------------------------
		JLabel label_constructionSite = new JLabel("２．工事場所");
		layout4.putConstraint(SpringLayout.NORTH, label_constructionSite, 3, SpringLayout.SOUTH, label_deliveryDate);
		layout4.putConstraint(SpringLayout.WEST, label_constructionSite, 20, SpringLayout.WEST, panel_2);
		layout4.putConstraint(SpringLayout.SOUTH, label_constructionSite, 23, SpringLayout.SOUTH, label_deliveryDate);
		layout4.putConstraint(SpringLayout.EAST, label_constructionSite, 100, SpringLayout.WEST, panel_2);
		panel_2.add(label_constructionSite);
		//----------------------------------------------------------------------------------------------
		textField_constructionSite = new JTextField();
		layout4.putConstraint(SpringLayout.NORTH, textField_constructionSite, 0, SpringLayout.NORTH, label_constructionSite);
		layout4.putConstraint(SpringLayout.SOUTH, textField_constructionSite, 0, SpringLayout.SOUTH, label_constructionSite);
		layout4.putConstraint(SpringLayout.WEST, textField_constructionSite, 0, SpringLayout.EAST, label_constructionSite);
		layout4.putConstraint(SpringLayout.EAST, textField_constructionSite, 300, SpringLayout.EAST, label_constructionSite);
		panel_2.add(textField_constructionSite);
		textField_constructionSite.setColumns(30);
		//----------------------------------------------------------------------------------------------
		JLabel label_paymentTerms = new JLabel("３．支払条件");
		layout4.putConstraint(SpringLayout.NORTH, label_paymentTerms, 3, SpringLayout.SOUTH, label_constructionSite);
		layout4.putConstraint(SpringLayout.WEST, label_paymentTerms, 20, SpringLayout.WEST, panel_2);
		layout4.putConstraint(SpringLayout.SOUTH, label_paymentTerms, 23, SpringLayout.SOUTH, label_constructionSite);
		layout4.putConstraint(SpringLayout.EAST, label_paymentTerms, 100, SpringLayout.WEST, panel_2);
		panel_2.add(label_paymentTerms);
		//----------------------------------------------------------------------------------------------
		textField_paymentTerms = new JTextField();
		layout4.putConstraint(SpringLayout.NORTH, textField_paymentTerms, 0, SpringLayout.NORTH, label_paymentTerms);
		layout4.putConstraint(SpringLayout.SOUTH, textField_paymentTerms, 0, SpringLayout.SOUTH, label_paymentTerms);
		layout4.putConstraint(SpringLayout.WEST, textField_paymentTerms, 0, SpringLayout.EAST, label_paymentTerms);
		layout4.putConstraint(SpringLayout.EAST, textField_paymentTerms, 300, SpringLayout.EAST, label_paymentTerms);
		panel_2.add(textField_paymentTerms);
		textField_paymentTerms.setColumns(30);
		//----------------------------------------------------------------------------------------------
		JLabel label_expirationDate = new JLabel("４．有効期限");
		layout4.putConstraint(SpringLayout.NORTH, label_expirationDate, 3, SpringLayout.SOUTH, label_paymentTerms);
		layout4.putConstraint(SpringLayout.WEST, label_expirationDate, 20, SpringLayout.WEST, panel_2);
		layout4.putConstraint(SpringLayout.SOUTH, label_expirationDate, 23, SpringLayout.SOUTH, label_paymentTerms);
		layout4.putConstraint(SpringLayout.EAST, label_expirationDate, 100, SpringLayout.WEST, panel_2);
		panel_2.add(label_expirationDate);
		//----------------------------------------------------------------------------------------------
		textField_expirationDate = new JTextField();
		layout4.putConstraint(SpringLayout.NORTH, textField_expirationDate, 0, SpringLayout.NORTH, label_expirationDate);
		layout4.putConstraint(SpringLayout.SOUTH, textField_expirationDate, 0, SpringLayout.SOUTH, label_expirationDate);
		layout4.putConstraint(SpringLayout.WEST, textField_expirationDate, 0, SpringLayout.EAST, label_expirationDate);
		layout4.putConstraint(SpringLayout.EAST, textField_expirationDate, 300, SpringLayout.EAST, label_expirationDate);
		panel_2.add(textField_expirationDate);
		textField_expirationDate.setColumns(30);
		//--------------------------------------------請求書用レイアウト----------------------------------------------------------------------------------------------------------
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		layout2.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, layeredPane);
		layout2.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, layeredPane);
		layout2.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, layeredPane);
		layout2.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, layeredPane);
		layeredPane.add(panel_1);

		SpringLayout layout3 = new SpringLayout();
       panel_1.setLayout(layout3);
		//-----------------------------------請求書用日付--------------------------------------------------------------------------------
		file_date_1 = new JTextField();
		file_date_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
		layout3.putConstraint(SpringLayout.NORTH, file_date_1, 0, SpringLayout.NORTH, panel_1);
		layout3.putConstraint(SpringLayout.WEST, file_date_1, -206, SpringLayout.EAST, panel_1);
		layout3.putConstraint(SpringLayout.SOUTH, file_date_1, 25, SpringLayout.NORTH, panel_1);
		layout3.putConstraint(SpringLayout.EAST, file_date_1, 0, SpringLayout.EAST, panel_1);
		panel_1.add(file_date_1);
		file_date_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newText1 = file_date_1.getText();
				if (newText1.indexOf("/") >= 0){
					try {
						file_date_1.setText(sdf.format(DateFormat.getDateInstance().parse(newText1)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else{
					file_date_3.setText(newText1);
				}
			}
		});
		file_date_1.setColumns(10);
		//-------------------------------------------------------------------------------------------------------------------
		lblTradingBank = new JLabel("取引銀行");
		layout3.putConstraint(SpringLayout.NORTH, lblTradingBank, 130, SpringLayout.NORTH, panel_1);
		layout3.putConstraint(SpringLayout.WEST, lblTradingBank, 20, SpringLayout.WEST, panel_1);
		layout3.putConstraint(SpringLayout.SOUTH, lblTradingBank, 150, SpringLayout.NORTH, panel_1);
		layout3.putConstraint(SpringLayout.EAST, lblTradingBank, 80, SpringLayout.WEST, panel_1);
		panel_1.add(lblTradingBank);
		//----------------------------------------------------------------------------------------------
		label_T_B_A_No = new JLabel("口座番号");
		layout3.putConstraint(SpringLayout.NORTH, label_T_B_A_No, 3, SpringLayout.SOUTH, lblTradingBank);
		layout3.putConstraint(SpringLayout.WEST, label_T_B_A_No, 20, SpringLayout.WEST, panel_1);
		layout3.putConstraint(SpringLayout.SOUTH, label_T_B_A_No, 23, SpringLayout.SOUTH, lblTradingBank);
		layout3.putConstraint(SpringLayout.EAST, label_T_B_A_No, 80, SpringLayout.WEST, panel_1);
		panel_1.add(label_T_B_A_No);
		//----------------------------------------------------------------------------------------------
		label_T_B_A_Hol = new JLabel("口座名義");
		layout3.putConstraint(SpringLayout.NORTH, label_T_B_A_Hol, 3, SpringLayout.SOUTH, label_T_B_A_No);
		layout3.putConstraint(SpringLayout.WEST, label_T_B_A_Hol, 20, SpringLayout.WEST, panel_1);
		layout3.putConstraint(SpringLayout.SOUTH, label_T_B_A_Hol, 23, SpringLayout.SOUTH, label_T_B_A_No);
		layout3.putConstraint(SpringLayout.EAST, label_T_B_A_Hol, 80, SpringLayout.WEST, panel_1);
		panel_1.add(label_T_B_A_Hol);
		//----------------------------------------------------------------------------------------------
		label_T_B_BRANCH = new JLabel();
		layout3.putConstraint(SpringLayout.NORTH, label_T_B_BRANCH, 0, SpringLayout.NORTH, lblTradingBank);
		layout3.putConstraint(SpringLayout.WEST, label_T_B_BRANCH, 0, SpringLayout.EAST, lblTradingBank);
		layout3.putConstraint(SpringLayout.SOUTH, label_T_B_BRANCH, 0, SpringLayout.SOUTH, lblTradingBank);
		panel_1.add(label_T_B_BRANCH);
		//----------------------------------------------------------------------------------------------
		label_T_B_ACCOUNT = new JLabel();
		layout3.putConstraint(SpringLayout.NORTH, label_T_B_ACCOUNT, 0, SpringLayout.NORTH, label_T_B_A_No);
		layout3.putConstraint(SpringLayout.WEST, label_T_B_ACCOUNT, 0, SpringLayout.EAST, label_T_B_A_No);
		layout3.putConstraint(SpringLayout.SOUTH, label_T_B_ACCOUNT, 0, SpringLayout.SOUTH, label_T_B_A_No);
		panel_1.add(label_T_B_ACCOUNT);
		//----------------------------------------------------------------------------------------------
		label_T_B_A_HOLDER = new JLabel();
		layout3.putConstraint(SpringLayout.NORTH, label_T_B_A_HOLDER, 0, SpringLayout.NORTH, label_T_B_A_Hol);
		layout3.putConstraint(SpringLayout.WEST, label_T_B_A_HOLDER, 0, SpringLayout.EAST, label_T_B_A_Hol);
		layout3.putConstraint(SpringLayout.SOUTH, label_T_B_A_HOLDER, 0, SpringLayout.SOUTH, label_T_B_A_Hol);
		panel_1.add(label_T_B_A_HOLDER);
		//-----------工事名入力-----------------------------------------------------------------------------------
		JTextField constructionSite = new JTextField();
		constructionSite.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe){
				constructionSite.getInputContext().setCompositionEnabled(true);
			}
			public void focusLost(FocusEvent fe){
				//constructionSite.getInputContext().setCompositionEnabled(false);
			}
		});
		layout.putConstraint(SpringLayout.NORTH, constructionSite, 280, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, constructionSite, 300, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.WEST, constructionSite, 5, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.EAST, constructionSite, 300, SpringLayout.WEST, jPanel);
		jPanel.add(constructionSite);

//-------------------------工事名combobox---------------------------------------------------------------------------------------//
		setconst = new PropSet();
		String constpart = " ," + setconst.PropGet("set.properties","constpart");
		String[] constpartdata = constpart.split(",");
		constpartModel = new DefaultComboBoxModel<String>(constpartdata);
		constructionPart = new JComboBox<String>();
		layout.putConstraint(SpringLayout.NORTH, constructionPart, 0, SpringLayout.NORTH, constructionSite);
		layout.putConstraint(SpringLayout.SOUTH, constructionPart, 0, SpringLayout.SOUTH, constructionSite);
		layout.putConstraint(SpringLayout.WEST, constructionPart, 5, SpringLayout.EAST, constructionSite);
		layout.putConstraint(SpringLayout.EAST, constructionPart, 150, SpringLayout.EAST, constructionSite);
		jPanel.add(constructionPart);
		constructionPart.setModel(constpartModel);

		String constname = " ," + setconst.PropGet("set.properties","constname");
		String[] constnamedata = constname.split(",");
		constnameModel = new DefaultComboBoxModel<String>(constnamedata);
		constructionName = new JComboBox<String>();
		layout.putConstraint(SpringLayout.NORTH, constructionName, 0, SpringLayout.NORTH, constructionPart);
		layout.putConstraint(SpringLayout.SOUTH, constructionName, 0, SpringLayout.SOUTH, constructionPart);
		layout.putConstraint(SpringLayout.WEST, constructionName, 5, SpringLayout.EAST, constructionPart);
		layout.putConstraint(SpringLayout.EAST, constructionName, 150, SpringLayout.EAST, constructionPart);
		jPanel.add(constructionName);
		constructionName.setModel(constnameModel);

		JButton button_const = new JButton("工事名入力");
		button_const.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sbuf = constructionSite.getText() + " " + constructionPart.getSelectedItem().toString() + " " + constructionName.getSelectedItem().toString();
				tableModel.setValueAt(sbuf, 0, 0);
			}
		});
		layout.putConstraint(SpringLayout.NORTH, button_const, 0, SpringLayout.NORTH, constructionName);
		layout.putConstraint(SpringLayout.SOUTH, button_const, 0, SpringLayout.SOUTH, constructionName);
		layout.putConstraint(SpringLayout.WEST, button_const, 5, SpringLayout.EAST, constructionName);
		layout.putConstraint(SpringLayout.EAST, button_const, 140, SpringLayout.EAST, constructionName);
		jPanel.add(button_const);
//-------------------------------------------------------------------------------------------------------------------------------//
		String[] columnNames = {"品名", "数量", "単位", "単価","金額"};
		tableModel = new DefaultTableModel(columnNames,0);
		table = new JTable(tableModel){
			private static final long serialVersionUID = 2027201606573012804L;
			/**
			 * フォーカスの移動先セルを編集可能にする
			 */
			@Override
			protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) {
			boolean retValue = super.processKeyBinding(ks, e, condition, pressed);
		    if (KeyStroke.getKeyStroke('\t').equals(ks) || KeyStroke.getKeyStroke('\n').equals(ks)) {
		      return retValue;
		    }
		    if (getInputContext().isCompositionEnabled() && !isEditing() && !pressed && !ks.isOnKeyRelease()) {
		      int selectedRow = getSelectedRow();
		      int selectedColumn = getSelectedColumn();
		      if (selectedRow != -1 && selectedColumn != -1 && !editCellAt(selectedRow, selectedColumn)) {
		        return retValue;
		      }
		    }
		    return retValue;
		  }
		};
		table.setSurrendersFocusOnKeystroke(true);
	    //-----------------------セルをワンクリックで編集できるようにする------------------------------
   		DefaultCellEditor ce = (DefaultCellEditor)table.getDefaultEditor(Object.class);
   		ce.setClickCountToStart(1);
	    //------------------------テーブルの作成-----------------------------------------------------------------
		DefaultTableColumnModel columnModel = (DefaultTableColumnModel)table.getColumnModel();
		for (int i = 0,ii ; i < columnModel.getColumnCount() ; i++){
			TableColumn column = columnModel.getColumn(i);
			DefaultTableCellRenderer celRenderer = new DefaultTableCellRenderer();
			switch (i){
			case 0:
				ii = 315;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 2:
				ii =50;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 4:
				ii =60;
				celRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				break;
			default:
				ii = 50;
				celRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			column.setCellRenderer(celRenderer);
			column.setPreferredWidth(ii);
		}
		//----------------------------------------------------------------------------------------------
		JScrollPane sp = new JScrollPane(table);
		layout.putConstraint(SpringLayout.NORTH, sp, 300, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, sp, -95, SpringLayout.SOUTH, jPanel);
		layout.putConstraint(SpringLayout.WEST, sp, 95, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.EAST, sp, -95, SpringLayout.EAST, jPanel);
		table.setBackground(Color.WHITE);
		jPanel.add(sp);
		//----------------------------------------------------------------------------------------------
		textField_Discount = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, textField_Discount, 5, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.SOUTH, textField_Discount, 25, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.EAST, textField_Discount, -15, SpringLayout.EAST, jPanel);
		layout.putConstraint(SpringLayout.WEST, textField_Discount, -115, SpringLayout.EAST, jPanel);
		jPanel.add(textField_Discount);
		textField_Discount.setHorizontalAlignment(JTextField.RIGHT);
		textField_Discount.setColumns(10);
		//----------------------------------------------------------------------------------------------
		label_Discount = new JLabel("値引き");
		label_Discount.setHorizontalAlignment(SwingConstants.LEFT);
		label_Discount.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 12));
		layout.putConstraint(SpringLayout.NORTH, label_Discount, 0, SpringLayout.NORTH, textField_Discount);
		layout.putConstraint(SpringLayout.SOUTH, label_Discount, 0, SpringLayout.SOUTH, textField_Discount);
		layout.putConstraint(SpringLayout.EAST, label_Discount, -5, SpringLayout.WEST,textField_Discount);
		layout.putConstraint(SpringLayout.WEST, label_Discount, -50, SpringLayout.WEST, textField_Discount);
		jPanel.add(label_Discount);
		//----------------------------------------------------------------------------------------------
		textField_TotalPrice = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, textField_TotalPrice, 2, SpringLayout.SOUTH, textField_Discount);
		layout.putConstraint(SpringLayout.SOUTH, textField_TotalPrice, 22, SpringLayout.SOUTH, textField_Discount);
		layout.putConstraint(SpringLayout.WEST, textField_TotalPrice, 0, SpringLayout.WEST, textField_Discount);
		layout.putConstraint(SpringLayout.EAST, textField_TotalPrice, 0, SpringLayout.EAST, textField_Discount);
		textField_TotalPrice.setHorizontalAlignment(JTextField.RIGHT);
		jPanel.add(textField_TotalPrice);
		textField_TotalPrice.setColumns(10);
		//----------------------------------------------------------------------------------------------
		label_TotalPrice = new JLabel("小　計");
		label_TotalPrice.setHorizontalAlignment(SwingConstants.LEFT);
		layout.putConstraint(SpringLayout.NORTH, label_TotalPrice, 0, SpringLayout.NORTH, textField_TotalPrice);
		layout.putConstraint(SpringLayout.SOUTH, label_TotalPrice, 0, SpringLayout.SOUTH, textField_TotalPrice);
		layout.putConstraint(SpringLayout.EAST, label_TotalPrice, -5, SpringLayout.WEST, textField_TotalPrice);
		layout.putConstraint(SpringLayout.WEST, label_TotalPrice, -50, SpringLayout.WEST, textField_TotalPrice);
		label_TotalPrice.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 12));
		jPanel.add(label_TotalPrice);
		//----------------------------------------------------------------------------------------------
		textField_Tax = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, textField_Tax, 2, SpringLayout.SOUTH, textField_TotalPrice);
		layout.putConstraint(SpringLayout.SOUTH, textField_Tax, 22, SpringLayout.SOUTH, textField_TotalPrice);
		layout.putConstraint(SpringLayout.WEST, textField_Tax, 0, SpringLayout.WEST, textField_TotalPrice);
		layout.putConstraint(SpringLayout.EAST, textField_Tax, 0, SpringLayout.EAST, textField_TotalPrice);
		textField_Tax.setHorizontalAlignment(JTextField.RIGHT);
		jPanel.add(textField_Tax);
		textField_Tax.setColumns(10);
		//----------------------------------------------------------------------------------------------
		label_Tax = new JLabel("消費税");
		label_Tax.setHorizontalAlignment(SwingConstants.LEFT);
		label_Tax.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 12));
		layout.putConstraint(SpringLayout.NORTH, label_Tax, 0, SpringLayout.NORTH, textField_Tax);
		layout.putConstraint(SpringLayout.SOUTH, label_Tax, 0, SpringLayout.SOUTH, textField_Tax);
		layout.putConstraint(SpringLayout.EAST, label_Tax, -5, SpringLayout.WEST, textField_Tax);
		layout.putConstraint(SpringLayout.WEST, label_Tax, -50, SpringLayout.WEST, textField_Tax);
		jPanel.add(label_Tax);
		//----------------------------------------------------------------------------------------------
		textField_BeforeTax = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, textField_BeforeTax, 2, SpringLayout.SOUTH, textField_Tax);
		layout.putConstraint(SpringLayout.SOUTH, textField_BeforeTax, 22, SpringLayout.SOUTH, textField_Tax);
		layout.putConstraint(SpringLayout.WEST, textField_BeforeTax, 0, SpringLayout.WEST, textField_Tax);
		layout.putConstraint(SpringLayout.EAST, textField_BeforeTax, 0, SpringLayout.EAST, textField_Tax);
		textField_BeforeTax.setHorizontalAlignment(JTextField.RIGHT);
		jPanel.add(textField_BeforeTax);
		textField_BeforeTax.setColumns(10);
		//----------------------------------------------------------------------------------------------
		label_BeforeTax = new JLabel("合　計");
		label_BeforeTax.setHorizontalAlignment(SwingConstants.LEFT);
		label_BeforeTax.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 12));
		layout.putConstraint(SpringLayout.NORTH, label_BeforeTax, 0, SpringLayout.NORTH, textField_BeforeTax);
		layout.putConstraint(SpringLayout.SOUTH, label_BeforeTax, 0, SpringLayout.SOUTH, textField_BeforeTax);
		layout.putConstraint(SpringLayout.EAST, label_BeforeTax, -5, SpringLayout.WEST, textField_BeforeTax);
		layout.putConstraint(SpringLayout.WEST, label_BeforeTax, -50, SpringLayout.WEST, textField_BeforeTax);
		jPanel.add(label_BeforeTax);
		//----------------------------------------------------------------------------------------------
		JButton button_clear = new JButton("一行クリア");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lineclear();
			}
		});
		layout.putConstraint(SpringLayout.NORTH, button_clear, 10, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.SOUTH, button_clear, 35, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.WEST, button_clear, 10, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.EAST, button_clear, 110, SpringLayout.WEST, jPanel);
		jPanel.add(button_clear);
		//----------------------------------------------------------------------------------------------
		JButton button = new JButton("一行追加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(table.getCellEditor() != null){
						table.getCellEditor().stopCellEditing();
					}
				final String[] addTableData = {"","","","",""};
				int delRowTable = table.getSelectedRow() + 1;
				tableModel.insertRow(delRowTable,addTableData);
			}
		});

		//----------------------------------------------------------------------------------------------
		set_position(3,lbl_title,layeredPane,true);
		layout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, button_clear);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, button_clear);
		layout.putConstraint(SpringLayout.WEST, button, 10, SpringLayout.EAST, button_clear);
		layout.putConstraint(SpringLayout.EAST, button, 110, SpringLayout.EAST, button_clear);
		jPanel.add(button);
		//----------------------------------------------------------------------------------------------
		JButton button_3 = new JButton("一行削除");
		button_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				linedel();
			}
		});
		layout.putConstraint(SpringLayout.NORTH, button_3, 0, SpringLayout.NORTH, button);
		layout.putConstraint(SpringLayout.SOUTH, button_3, 0, SpringLayout.SOUTH, button);
		layout.putConstraint(SpringLayout.WEST, button_3, 10, SpringLayout.EAST, button);
		layout.putConstraint(SpringLayout.EAST, button_3, 110, SpringLayout.EAST, button);
		jPanel.add(button_3);
		//------------------------------------------------------------------------------------------------------------
		JButton button_1 = new JButton("確　定");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(table.getCellEditor() != null){
						table.getCellEditor().stopCellEditing();
					}
					new ReplaceData();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		layout.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, button_3);
		layout.putConstraint(SpringLayout.SOUTH, button_1, 0, SpringLayout.SOUTH, button_3);
		layout.putConstraint(SpringLayout.WEST, button_1, 10, SpringLayout.EAST, button_3);
		layout.putConstraint(SpringLayout.EAST, button_1, 110, SpringLayout.EAST, button_3);
		jPanel.add(button_1);
		//----------------------------------------------------------------------------------------------
		JButton button_2 = new JButton("データ保存");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getCellEditor() != null){
					table.getCellEditor().stopCellEditing();
				}
				saveFile(arg0);
			}
		});
		layout.putConstraint(SpringLayout.NORTH, button_2, 0, SpringLayout.NORTH, button_1);
		layout.putConstraint(SpringLayout.SOUTH, button_2, 0, SpringLayout.SOUTH, button_1);
		layout.putConstraint(SpringLayout.WEST, button_2, 10, SpringLayout.EAST, button_1);
		layout.putConstraint(SpringLayout.EAST, button_2, 110, SpringLayout.EAST, button_1);
		jPanel.add(button_2);

		JLabel label = new JLabel("-");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.EAST, label_fileNo_1);
		layout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, label_fileNo_1);
		layout.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, label_fileNo_1);
		layout.putConstraint(SpringLayout.EAST, label, 15, SpringLayout.EAST, label_fileNo_1);
		jPanel.add(label);

		//----------------------------------------------------------------------------------------------

		JRadioButton radioButton_1 = new JRadioButton("無し");
		radioButton_1.setBackground(new Color(221, 221, 221));
		layout.putConstraint(SpringLayout.NORTH, radioButton_1, 10, SpringLayout.SOUTH, button_clear);
		layout.putConstraint(SpringLayout.WEST, radioButton_1, 6, SpringLayout.WEST, jPanel);
		jPanel.add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("税別");
		radioButton_2.setBackground(new Color(221, 221, 221));
		layout.putConstraint(SpringLayout.NORTH, radioButton_2, 0, SpringLayout.NORTH, radioButton_1);
		layout.putConstraint(SpringLayout.WEST, radioButton_2, 6, SpringLayout.EAST, radioButton_1);
		jPanel.add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("税込");
		radioButton_3.setBackground(new Color(221, 221, 221));
		layout.putConstraint(SpringLayout.NORTH, radioButton_3, 0, SpringLayout.NORTH, radioButton_2);
		layout.putConstraint(SpringLayout.WEST, radioButton_3, 6, SpringLayout.EAST, radioButton_2);
		jPanel.add(radioButton_3);

		JRadioButton radioButton_4 = new JRadioButton("総額");
		radioButton_4.setBackground(new Color(221, 221, 221));
		layout.putConstraint(SpringLayout.NORTH, radioButton_4, 2, SpringLayout.SOUTH, radioButton_1);
		layout.putConstraint(SpringLayout.WEST, radioButton_4, 6, SpringLayout.WEST, jPanel);
		jPanel.add(radioButton_4);

		JRadioButton radioButton_5 = new JRadioButton("総額+税");
		radioButton_5.setBackground(new Color(221, 221, 221));
		layout.putConstraint(SpringLayout.NORTH, radioButton_5, 0, SpringLayout.NORTH, radioButton_4);
		layout.putConstraint(SpringLayout.WEST, radioButton_5, 6, SpringLayout.EAST, radioButton_4);
		jPanel.add(radioButton_5);

		ButtonGroup group = new ButtonGroup();
		group.add(radioButton_1);
		group.add(radioButton_2);
		group.add(radioButton_3);
		group.add(radioButton_4);
		group.add(radioButton_5);

		ad = new Ad();
		radioButton_1.addActionListener(ad);
		radioButton_2.addActionListener(ad);
		radioButton_3.addActionListener(ad);
		radioButton_4.addActionListener(ad);
		radioButton_5.addActionListener(ad);

		checkBox_1 = new JCheckBox("代表者名を印刷");
		checkBox_1.setSelected(true);
		checkBox_1.setBackground(new Color(221, 221, 221));
		checkBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				divisionCheck();
			}
		});
		layout.putConstraint(SpringLayout.NORTH, checkBox_1, 0, SpringLayout.NORTH, radioButton_3);
		layout.putConstraint(SpringLayout.WEST, checkBox_1, 40, SpringLayout.EAST, radioButton_3);
		jPanel.add(checkBox_1);

		checkBox_2 = new JCheckBox("本日付けで印刷");
		checkBox_2.setBackground(new Color(221, 221, 221));
		checkBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		layout.putConstraint(SpringLayout.WEST, checkBox_2, 0, SpringLayout.WEST, checkBox_1);
		layout.putConstraint(SpringLayout.NORTH, checkBox_2, -4, SpringLayout.SOUTH, checkBox_1);
		layout.putConstraint(SpringLayout.EAST, checkBox_2, 0, SpringLayout.EAST, checkBox_1);
		jPanel.add(checkBox_2);

		checkBox_3 = new JCheckBox("TEL/FAX");
		checkBox_3.setBackground(new Color(221, 221, 221));
		checkBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//telfaxCheck();
			}
		});
		layout.putConstraint(SpringLayout.WEST, checkBox_3, 20, SpringLayout.EAST, checkBox_1);
		layout.putConstraint(SpringLayout.NORTH, checkBox_3, 0, SpringLayout.NORTH, checkBox_1);
		layout.putConstraint(SpringLayout.EAST, checkBox_3, 100, SpringLayout.EAST, checkBox_1);
		jPanel.add(checkBox_3);
		//---------------------------------------------------------------------------------
		int taxType = new ReadFile().dbData();
		switch (taxType) {
		case 1:
			ad.taxTypeSet("無し");
			radioButton_1.setSelected(true);
			break;
		case 2:
			ad.taxTypeSet("税抜き");
			radioButton_2.setSelected(true);
			break;
		case 3:
			ad.taxTypeSet("税込");
			radioButton_3.setSelected(true);
			break;
		case 4:
			ad.taxTypeSet("総額");
			radioButton_4.setSelected(true);
			break;
		case 5:
			ad.taxTypeSet("総額+税");
			radioButton_5.setSelected(true);
			break;
		default:
			ad.taxTypeSet("税込");
			radioButton_3.setSelected(true);
			break;
		}
		setVisible(true);
		toFront();
	}
	//----------------------------------------------------------------------------------------------------------------------
	//}
	private void lineclear(){
		int clearRow = table.getSelectedRow();
		int clearColumn = table.getSelectedColumn();
		if(table.isEditing()){
			table.getCellEditor().stopCellEditing();
		}
		for (int i = 0; i < 4;i++) {
			tableModel.setValueAt("", clearRow,i);
		}
		table.changeSelection(clearRow, clearColumn, true, false);
	}
	private void linedel(){
		int delRowTable = table.getSelectedRow();
		if(table.isEditing()){
			table.getCellEditor().stopCellEditing();
		}
		tableModel.removeRow(delRowTable);
	}
	private void saveFile(ActionEvent e1){
		try {
			new ReplaceData();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		OutData savefile = new OutData();
		String label_fileNo_2Text = label_fileNo_2.getText();
		if(!label_fileNo_2Text.equals("")){
			label_fileNo_2Text = "-" + label_fileNo_2Text;
		}
		File savefileName = new File(".\\files\\CMF" + label_fileNo_1.getText() + label_fileNo_2Text + ".DAT");
		if(savefileName.getPath() != null){
			int fileExits = 0;
			if (savefileName.exists()) {
				switch (JOptionPane.showConfirmDialog(this,
					savefileName.getName() + " は既に存在します。\n上書きしますか？",
					"上書き保存の確認", JOptionPane.YES_NO_OPTION)) {
				case JOptionPane.YES_OPTION:
					fileExits = 0;
					break;
				case JOptionPane.NO_OPTION:
					fileExits = 2;
					break;
				}
			}
			if(fileExits == 0){
				try {
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savefileName.getPath()), "UTF-8"));
					pw.println(savefile.saveData());
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void saveFileName(ActionEvent e1){
		FileDialog sfd = new FileDialog(this, "ファイルを保存" , FileDialog.SAVE);//OSXでの日本語ファイル名表示の為、FileDialogを使用
		String label_fileNo_2Text = label_fileNo_2.getText();
		if(!label_fileNo_2Text.equals("")){
			label_fileNo_2Text = "-" + label_fileNo_2Text;
		}
		sfd.setFile("CMF" + label_fileNo_1.getText() + label_fileNo_2Text + ".DAT" );
		sfd.setDirectory(".\\files");
		try {
			new ReplaceData();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		OutData savefile = new OutData();
		sfd.setVisible(true);
		if(sfd.getFile() != null){
			String savefileName = sfd.getDirectory() + sfd.getFile();
			try {
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savefileName), "UTF-8"));
				pw.println(savefile.saveData());
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//---------------------------------------Menu---------------------------------------------------------------------------------
	protected JMenuBar createMenu() {
		JMenu menu = new JMenu("File");
		menu.add(new AbstractAction("名前をつけて保存") {
			private static final long serialVersionUID = 736917941484596380L;
			public void actionPerformed(ActionEvent e1) {
				saveFileName(e1);
			}
		});
		menu.add(new AbstractAction("終了") {
			private static final long serialVersionUID = -4991244605063978980L;
			public void actionPerformed(ActionEvent e2) {
				dispose();
			}
		});

		JMenu menu2 = new JMenu("設定");
		menu2.add(new AbstractAction("自社データ入力") {
			private static final long serialVersionUID = -3822926881247998784L;
			public void actionPerformed(ActionEvent e3) {
				new C_DATA();
			}
		});
		menu2.add(new AbstractAction("顧客データ入力") {
			private static final long serialVersionUID = 6277554258787553620L;
			public void actionPerformed(ActionEvent e3) {
				new F_DATA();
			}
		});
		menu2.add(new AbstractAction("新規No.再設定") {
			private static final long serialVersionUID = -1361133806568243558L;
			public void actionPerformed(ActionEvent e3) {
				new Config(1);
			}
		});
		menu2.add(new AbstractAction("フォルダ作成") {
			private static final long serialVersionUID = -6790984078225055802L;
			public void actionPerformed(ActionEvent e3) {
				new Config(2);
			}
		});
		menu2.add(new AbstractAction("消費税設定") {
			private static final long serialVersionUID = -6453335322617400235L;
			public void actionPerformed(ActionEvent e3) {
				new Config(3);
			}
		});
		menu2.add(new AbstractAction("工事名設定") {
			private static final long serialVersionUID = 4819489267594845320L;
			public void actionPerformed(ActionEvent e3) {
				new Config(4);
			}
		});

		JMenu menu3 = new JMenu("編集");
		menu3.add(new AbstractAction("一行クリア") {
			private static final long serialVersionUID = -1361133806568243558L;
			public void actionPerformed(ActionEvent e4) {
				lineclear();
			}
		});
		menu3.add(new AbstractAction("一行追加") {
			private static final long serialVersionUID = -8565379083961098908L;
			public void actionPerformed(ActionEvent e4) {
					if(table.getCellEditor() != null){
						table.getCellEditor().stopCellEditing();
					}
				final String[] addTableData = {"","","","",""};
				int delRowTable = table.getSelectedRow() + 1;
				tableModel.insertRow(delRowTable,addTableData);
			}
		});
		menu3.add(new AbstractAction("一行削除") {
			private static final long serialVersionUID = -4013311944425341107L;
			public void actionPerformed(ActionEvent e4) {
				linedel();
			}
		});
		JMenu menu4 = new JMenu("検索");
		menu4.add(new AbstractAction("キーワード検索") {
			private static final long serialVersionUID = -1361133806568243558L;
			public void actionPerformed(ActionEvent e5) {
				FileSearch keysearch = new FileSearch();
					keysearch.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					keysearch.setVisible(true);
					if(keysearch.filepath != null){
						new ReadFile(keysearch.filepath);
						set_position(3,lbl_title,getLayeredPane(),true);
					}
			}
		});
		JMenu menu5 = new JMenu("ファイル操作");
		menu5.add(new AbstractAction("移動") {
			private static final long serialVersionUID = -1361133806568243558L;
			public void actionPerformed(ActionEvent e6) {
				FileMove filemove = new FileMove();
				filemove.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				filemove.setVisible(true);
				if(filemove.moveFilepath != null){
					set_position(3,lbl_title,getLayeredPane(),true);
				}
			}
		});
		JMenuBar mb = new JMenuBar();
		mb.add(menu);
		mb.add(menu2);
		mb.add(menu3);
		mb.add(menu4);
		mb.add(menu5);
		return mb;
	}
	//----------------------------------------------------------------------------------------------------------------------------
	private void set_position(int po,JLabel lbltitle,JLayeredPane layeredPane,boolean titleset){
		boolean tf;
		boolean F1;
		boolean F2;
		switch(po){
		case 1:
			layeredPane.setPosition(panel_5,2);
			layeredPane.setPosition(panel_3,3);
			layeredPane.setPosition(panel_4,5);
			if(titleset)lbltitle.setText("納　品　書");
			tf = false;
			F1 = false;
			F2 = true;
			break;
		case 2:
			layeredPane.setPosition(panel_4,2);
			layeredPane.setPosition(panel_1,3);
			layeredPane.setPosition(panel_3,4);
			if(titleset)lbltitle.setText("請　求　書");
			tf = false;
			F1 = true;
			F2 = false;
			break;
		case 3:
			layeredPane.setPosition(panel_2,3);
			layeredPane.setPosition(panel_4,2);
			layeredPane.setPosition(panel_3,5);
			if(titleset)lbltitle.setText("見　積　書");
			tf = true;
			F1 = true;
			F2 = false;
			break;
		default:
			tf = false;
			F1 = false;
			F2 = false;
		}
		comboBox_F_1.setVisible(F1);
		comboBox_F_2.setVisible(F2);
		textField_deliveryDate.setVisible(tf);
		textField_constructionSite.setVisible(tf);
		textField_paymentTerms.setVisible(tf);
		textField_expirationDate.setVisible(tf);
	}
	private void clientName(JComboBox<String> comboBoxName,JComboBox<String> otherComboBoxName,JLabel CustomerNo,ActionEvent e){
		Object sel = comboBoxName.getSelectedItem();
		if(sel != null){
			Object selItem = sel;
			String[] dbreData = new DbConnect().dBReText("F_DATA","F_NAME",(String)comboBoxName.getSelectedItem());
			CustomerNo.setText(dbreData[1]);
			if(dbreData[2] == null && !sel.equals("")){
				int option = JOptionPane.showConfirmDialog(null, "未登録です、登録しますか？","未登録顧客名",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
				if (option == 0){
					try {
						String newFcodeNo = new DbConnect().addData(sel);
						comboBoxList();
						DefaultComboBoxModel<String> model_F = new DefaultComboBoxModel<String>(F_Array);
						DefaultComboBoxModel<String> otherModel_F = new DefaultComboBoxModel<String>(F_Array);
						comboBoxName.setModel(model_F);
						comboBoxName.setSelectedItem(selItem);
						CustomerNo.setText(newFcodeNo);
						Object selItem2 = otherComboBoxName.getSelectedItem();
						otherComboBoxName.setModel(otherModel_F);
						otherComboBoxName.setSelectedItem(selItem2);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	private void comboBoxList(){
		DbConnect dbconnect = new DbConnect();
		ArrayList<String> F_List = dbconnect.combobox("F_DATA", "F_NAME");
		F_List.add(0, "");
		F_Array = F_List.toArray(new String[0]);
		ArrayList<String> C_List = dbconnect.combobox("C_DATA", "C_NAME");
		C_List.add(0, "");
		C_Array = C_List.toArray(new String[0]);
		try {
			dbconnect.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private boolean checkBeforeReadfile(File file){
		if (file.exists()){
			if (file.isFile() && file.canRead()){
				return true;
			}
		}
		return false;
	}
	//-----------------------------新規作成-------------------------------------------------------------
	private void newData(String newFileNo){
		comboBox_F_1.setSelectedItem("");
		comboBox_F_2.setSelectedItem("");
		comboBox_C_1.setSelectedItem("");
		comboBox_C_2.setSelectedItem("");
		comboBox_Honorific_1.setSelectedItem("");
		comboBox_Honorific_2.setSelectedItem("");
		textField_deliveryDate.setText("");
		textField_constructionSite.setText("");
		textField_expirationDate.setText("");
		textField_paymentTerms.setText("");
		textArea_Proviso.setText("");
		file_date_1.setText("");
		file_date_2.setText("");
		file_date_3.setText("");
		label_fileNo_1.setText(newFileNo);
		label_fileNo_2.setText("");
		textField_Discount.setText("");
		tableModel.setRowCount(0);
		String[] tabledata = {"","","","",""};
		for(int i = 0; i < 10; i++){
			tableModel.addRow(tabledata);
		}
	}
	//---------------------------------------------------------------------------------------------------
	private void divisionCheck(){
		if(lbl_title.getText().replace("　","").equals("納品書")){
			lblDivision_2.setVisible(checkBox_1.isSelected());
		}else{
			lblDivision_1.setVisible(checkBox_1.isSelected());
		}
	}
	private void telfaxCheck(){
		if(lbl_title.getText().replace("　","").equals("納品書")){
			//lblDivision_2.setVisible(checkBox_3.isSelected());
		}else{
			//lblDivision_1.setVisible(checkBox_3.isSelected());
		}
	}
//------------------------クラス---------------------------------------------------------------------------//
	//--------------------------------------------------------------------------------------------//
	//                                          クラス                                                                             //
	//--------------------------------------------------------------------------------------------//
	public class PropSet{
		Properties properties = new Properties();

		PropSet() {
		}

		public void PropReset(String strpass, String chprop, String newText){
			Pattern p = Pattern.compile("\n");
			Matcher m = p.matcher(newText);
			newText = m.replaceAll(",");
			try {
				// 読み込み
				InputStream istream = new FileInputStream(strpass);
				InputStreamReader isr = new InputStreamReader(istream, "UTF-8");
				properties.load(isr);
				istream.close();
				// Mapに格納
				Map<String, String> propMap = new HashMap<>();
				for(Map.Entry<Object, Object> e1 : properties.entrySet()) {
					propMap.put(e1.getKey().toString(), e1.getValue().toString());
				}

				// 書き込み
				propMap.put(chprop,newText);
				for(Entry<String, String> e2 : propMap.entrySet()) {
					properties.setProperty(e2.getKey(), e2.getValue());
				}
				OutputStream ostream = new FileOutputStream(strpass);
				OutputStreamWriter osw = new OutputStreamWriter(ostream, "UTF-8");
				properties.store(osw, "Comments");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		public String PropGet(String strpass,String chprop){
			File propFile = new File(strpass);
			String intStr = null;
			if(checkBeforeReadfile(propFile)){
				try {
					Properties prop = new Properties();
					//prop.load(new FileReader(propFile));
					InputStream istream = new FileInputStream(strpass);
					InputStreamReader isr = new InputStreamReader(istream, "UTF-8");
					prop.load(isr);
					istream.close();
					intStr = String.valueOf(prop.getProperty(chprop));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("ファイルが見つからないか開けません");
			}
			return intStr;
		}
		public String PropGetRegex(String strpass,String chprop,String regexText, String replaceText){
			File propFile = new File(strpass);
			String intStr = null;
			if(checkBeforeReadfile(propFile)){
				try {
				InputStream istream = new FileInputStream(strpass);
				InputStreamReader isr = new InputStreamReader(istream, "UTF-8");
					Properties prop = new Properties();
					//prop.load(new FileReader(propFile));
					prop.load(isr);
					intStr = String.valueOf(prop.getProperty(chprop));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("ファイルが見つからないか開けません");
			}
			Pattern p = Pattern.compile(",");
			Matcher m = p.matcher(intStr);
			intStr = m.replaceAll("\n");
			return intStr;
		}

	}
	class LineDel{//-------------一行削除------------------------------------------------------
		LineDel(){
		}
		public Boolean isHalfWidthAlphanumeric(String value) {
		    if ( value == null || value.length() == 0 ) return false;
		   	int len = value.length();
		   	byte[] bytes = value.getBytes();
		    if ( len != bytes.length ) return true;
		    return false;
		}
	}
	class OutData {
		private String outData;
		OutData(){
			DbConnect dbconnect = new DbConnect();
			String[] tmpData = dbconnect.tmpReText("TMP_1");
			String[][] tmpData2 = dbconnect.tmpReData("CONST_DATA","OUT");
			String outData1 = "";
			try {
				dbconnect.disconnect();
				for(int i = 0; i < tmpData.length; i++){
					outData1 += tmpData[i].toString() + "\r\n";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//---------------------------------------------------------------------------
			String proviso = tmpData2[0][0].toString().replaceAll("\r\n","↑");
			String[] DiscountArray = tmpData2[1][0].split("\t",-1);
			String DiscountText = DiscountArray[0].toString();
			String Discount = "";
			if (DiscountArray[3] != ""){
				Discount = DiscountArray[3].toString();
			}
			String outData2 = "";
			String regex = "^\\[(.+?)\\]";
			Pattern rep = Pattern.compile(regex);
			for(int ii = 2; ii < tmpData2.length; ii++){
				Matcher match = rep.matcher(Arrays.toString(tmpData2[ii]));
				if(match.find()){
					outData2 += "\r\n" + match.group(1);
				}
			}
			outData2 = proviso + "\r\n" + DiscountText + "\t\t\t" + Discount + outData2;
			outData = outData1 + "\r\n" + outData2;
		}
		private String saveData(){
			return outData;
		}
	}
	class PrintData{
		private String wDate1 = "";
		private String wDate2 = "";
		private String wDate3 = "";
		private Matcher match1;
		private Matcher match2;
		private Matcher match3;
		private DateFormat sdf2;
		private DateFormat japaneseFormat;
		private Calendar cal;
		private Pattern rep;

		PrintData(){
			Locale localeJP = new Locale("ja","JP","JP");
			DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd",Locale.JAPAN);
			sdf2 = new SimpleDateFormat("GGGG  y 年 M 月 d 日",localeJP);
			japaneseFormat = new SimpleDateFormat("GGGGy年M月d日", localeJP);
			//sdf2 = new SimpleDateFormat("GGGGy年M月d日",localeJP);
			cal = Calendar.getInstance(localeJP);
			cal.set(Calendar.ERA, 4);
			String regex = "^.+?(\\d\\d+?).+?(\\d\\d?).+?(\\d\\d?)";
			rep = Pattern.compile(regex);
			Date jDate1;
			try {
				jDate1 = japaneseFormat.parse(file_date_1.getText());
				match1 = rep.matcher(sdf1.format(jDate1));
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			//match1 = rep.matcher(file_date_1.getText());
			if(match1.find()){
				cal.set(Integer.parseInt(match1.group(1)),Integer.parseInt(match1.group(2)) -1,Integer.parseInt(match1.group(3)));
				wDate1 = sdf2.format(cal.getTime());
			}
			Date jDate2 = null;
			try {
				jDate2 = japaneseFormat.parse(file_date_2.getText());
				match2 = rep.matcher(sdf1.format(jDate2));
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			//Matcher match2 = rep.matcher(file_date_2.getText());
			if(match2.find()){
				//if(!date_1.equals(""))file_date_1.setText(sdf.format(DateFormat.getDateInstance().parse(date_1)));
				cal.set(Integer.parseInt(match2.group(1)),Integer.parseInt(match2.group(2)) -1,Integer.parseInt(match2.group(3)));
				//wDate2 = sdf2.format(cal.getTime());
				wDate2 = sdf2.format(jDate2);
			}
			Date jDate3;
			try {
				jDate3 = japaneseFormat.parse(file_date_3.getText());
				match3 = rep.matcher(sdf1.format(jDate3));
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			//Matcher match3 = rep.matcher(file_date_3.getText());
			if(match3.find()){
				cal.set(Integer.parseInt(match3.group(1)),Integer.parseInt(match3.group(2)) -1,Integer.parseInt(match3.group(3)));
				wDate3 = sdf2.format(cal.getTime());
			}
		}

		private Object[] printDataList1(){
			String label_fileNo_2Text = label_fileNo_2.getText();
			if(!label_fileNo_2Text.equals("")){
				label_fileNo_2Text = "-" + label_fileNo_2Text;
			}
			Object array_1[] = {label_fileNo_1.getText() + label_fileNo_2Text,			//0
				lblCustomerNo_1.getText(),																		//1
				comboBox_F_1.getSelectedItem(),															//2
				comboBox_Honorific_1.getSelectedItem(),												//3
				lblOwnCompanyNo_1.getText(),																//4
				comboBox_C_1.getSelectedItem(),															//5
				wDate1,wDate2,wDate3,																			//6,7,8
				textField_deliveryDate.getText(),																//9
				textField_constructionSite.getText(),														//10
				textField_paymentTerms.getText(),															//11
				textField_expirationDate.getText(),															//12
				lblCustomerNo_2.getText(),																		//13
				comboBox_F_2.getSelectedItem(),															//14
				comboBox_Honorific_2.getSelectedItem(),												//15
				lblOwnCompanyNo_2.getText(),																//16
				comboBox_C_2.getSelectedItem(), 														//17
				lblZipCode_1.getText(),lblZipCode_2.getText(),										//18,19
				lblAddress_1.getText(),lblAddress_2.getText(),										//20,21
				lblDivision_1.getText(),lblDivision_2.getText(),										//22,23
				lblTel_1.getText(),lblTel_2.getText(),														//24,25
				lblFax_1.getText(),lblFax_2.getText(),														//26,27
				lblTradingBank.getText(),																			//28
				label_T_B_A_No.getText(),																		//29
				label_T_B_A_Hol.getText(),																		//30
				label_T_B_BRANCH.getText(),																	//31
				label_T_B_ACCOUNT.getText(),																//32
				label_T_B_A_HOLDER.getText(),																//33
				textField_Discount.getText(),																	//34
				textField_TotalPrice.getText(),																	//35
				textField_Tax.getText(),																			//36
				textField_BeforeTax.getText(),																	//37
				textArea_Proviso.getText().replaceAll("\n", "↑"),									//38
				label_Discount.getText(),																			//39
				textField_Discount.getText().replaceAll(",", ""),										//40
			};
			return array_1;
		}
		private Object[][] printDataList2(){
			Object array_2[][] = new Object[table.getRowCount()][5];
			for (int rowCount = 0;rowCount < table.getRowCount();rowCount++){
				array_2[rowCount][0] =  table.getValueAt(rowCount, 0).toString();
				array_2[rowCount][1] =  table.getValueAt(rowCount, 1).toString().replaceAll(",", "");
				array_2[rowCount][2] =  table.getValueAt(rowCount, 2).toString();
				array_2[rowCount][3] =  table.getValueAt(rowCount, 3).toString().replaceAll(",", "");
				array_2[rowCount][4] =  table.getValueAt(rowCount, 4).toString().replaceAll(",", "");
			}
			return array_2;
		}
	}
	class ReplaceData{
		private DateFormat japaneseFormat;
		//private Date date;
		ReplaceData() throws ParseException{
			String wDate1 = "";
			String wDate2 = "";
			String wDate3 ="";
			Locale localeJP = new Locale("ja","JP","JP");
			Calendar cal = Calendar.getInstance(localeJP);
			cal.set(Calendar.ERA, 4);
			DateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd",Locale.JAPAN);
			japaneseFormat = new SimpleDateFormat("GGGGy年M月d日", localeJP);
			String regex = "^.+?(\\d\\d+?).+?(\\d\\d?).+?(\\d\\d?)";
			Pattern rep = Pattern.compile(regex);
			Date jDate1 = japaneseFormat.parse(file_date_1.getText());
			Matcher match1 = rep.matcher(sdf2.format(jDate1));
			cal.setLenient(false);
			if(match1.find()){
				//date = japaneseFormat.parse(file_date_1.getText());
				wDate1 = sdf2.format(jDate1);
			}
			Date jDate2 = japaneseFormat.parse(file_date_2.getText());
			Matcher match2 = rep.matcher(sdf2.format(jDate2));
			if(match2.find()){
				//date = japaneseFormat.parse(file_date_2.getText());
				wDate2 = sdf2.format(jDate2);
			}
			Date jDate3;
			if(file_date_3.getText().equals("")) {
				jDate3 = japaneseFormat.parse(file_date_1.getText());
			}else {
				jDate3 = japaneseFormat.parse(file_date_3.getText());
			}
			Matcher match3 = rep.matcher(sdf2.format(jDate3));
			if(match3.find()){
				//date = japaneseFormat.parse(file_date_3.getText());
				wDate3 = sdf2.format(jDate3);
			}
			String label_fileNo_2Text = label_fileNo_2.getText();
			if(!label_fileNo_2Text.equals("")){
				label_fileNo_2Text = "-" + label_fileNo_2Text;
			}
			String sql_3 = "(\'" + label_fileNo_1.getText() + label_fileNo_2Text + "\'),(\'"
				+ lblCustomerNo_1.getText() + "\'),(\'"
				+ comboBox_F_1.getSelectedItem() + "\t" + comboBox_Honorific_1.getSelectedItem() + "\'),(\'"
				+ lblOwnCompanyNo_1.getText() + "\'),(\'"
				+ comboBox_C_1.getSelectedItem() + "\'),(\'"
				+ wDate1 + "\t" + wDate2 + "\t" +wDate3 + "\'),(\'"
				+ textField_deliveryDate.getText() + "\'),(\'"
				+ textField_constructionSite.getText() + "\'),(\'"
				+ textField_paymentTerms.getText() + "\'),(\'"
				+ textField_expirationDate.getText() + "\'),(\'"
				+ comboBox_F_2.getSelectedItem()+ "\t" + comboBox_Honorific_2.getSelectedItem()+ "\'),(\'"
				+ comboBox_C_2.getSelectedItem() + "\'),(\""
				+ checkBox_1.isSelected() + "\"),(\""
				+ checkBox_2.isSelected() + "\"),(\""
				+ checkBox_3.isSelected() + "\"),(\'"
				+ ad.taxType + "\')";
			String sql_4 = "(\'" + textArea_Proviso.getText().replaceAll("\n", "↑") + "\',\'\',\'\',\'\'),(\'" + label_Discount.getText() + "\',\'\',\'\','" + textField_Discount.getText().replaceAll(",", "") + "\')";
			for (int rowCount = 0;rowCount < table.getRowCount();rowCount++){
				sql_4 += ",(" + "\'" + table.getValueAt(rowCount, 0).toString() + "\',\'"
					+ table.getValueAt(rowCount, 1).toString().replaceAll(",", "") + "\',\'"
					+ table.getValueAt(rowCount, 2).toString() + "\',\'"
					+ table.getValueAt(rowCount, 3).toString().replaceAll(",", "") + "\')";
			}
			new ReadFile(sql_3, sql_4);
		}
	}
	class ReadFile{
		ReadFile(){
			dbData();
		}
		ReadFile(String sql_1,String sql_2){//-----ウィンドウのデータを書き込み、データベースからデータを取得------
			sql_1 = "INSERT INTO TMP_1 (TMP_1_DATA) values " + sql_1;
			String table_1 = "TMP_1";
			String column_1 = "DATA_NO";
			sql_2 = "INSERT INTO CONST_DATA (ITEM,QUANTITY,UNIT,UNIT_PRICE) values " + sql_2;
			String table_2 = "CONST_DATA";
			String column_2 = "KEY1";
			try {
				DbConnect dbconnect = new DbConnect();
				dbconnect.dBInsert(sql_1,table_1,column_1);
				dbconnect.dBInsert(sql_2,table_2,column_2);
				dbconnect.disconnect();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			dbData();
		}
		ReadFile(String filePath){//---------------データファイルから読込み----------------------------------------
			int lineNo = 0;
			String sql_1 = "";
			String sql_2 = "";
			try {
				FileInputStream readFile = new FileInputStream(filePath);
				FileCharDetecter fd = new FileCharDetecter(filePath);
				InputStreamReader readFileUtf;
				String line;
				String regex = ".*SAVE.*\\.DAT";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(filePath);
				if(m.find()){//-------------------------------------------------旧データファイル用------------------------------
					String yearmonthday = "";
					String clientname = "";
					String fileno = "";
					String keisyou = "";
					String cnumber = "";
					String nouki = "";
					String koujibasho = "";
					String shiharai = "";
					String kigen = "";
					String bikou = "";
					readFileUtf = new InputStreamReader(readFile,"MS932");
					BufferedReader  filereader = new BufferedReader(readFileUtf);
					while((line = filereader.readLine()) != null){
						lineNo++;
						Pattern sp = Pattern.compile("[\\s]+");
						String[] sptext = sp.split(line);
						if(lineNo == 1){
							fileno = sptext[1];
							String regnal_yearmonthday;
							if(sptext[3].equals("0")){
								regnal_yearmonthday = sptext[2];
							}else if(sptext[4].equals("0")){
								regnal_yearmonthday = sptext[2] + sptext[3];
							}else{
								regnal_yearmonthday = sptext[2] + sptext[3] + sptext[4];
							}
							String[] regnal_year = regnal_yearmonthday.split("\\/",2);
							yearmonthday = String.valueOf((Integer.parseInt(regnal_year[0]) + 1988)) + "/" + regnal_year[1];
						}else if((lineNo == 4) && (sptext.length !=1)){
							clientname = sptext[1];
							keisyou = sptext[2];
							cnumber = sptext[3];
						}else if((lineNo == 5) && (sptext.length !=1)){
							nouki = sptext[1];
						}else if((lineNo == 6) && (sptext.length !=1)){
							koujibasho = sptext[1];
						}else if((lineNo == 7) && (sptext.length !=1)){
							shiharai = sptext[1];
						}else if((lineNo == 8) && (sptext.length !=1)){
							kigen = sptext[1];
						}else if((lineNo == 9) && (sptext.length !=1)){
							String regexline = "(^H2\\s)(\\S.+\\S)([\\s]+$)";
							Pattern pline = Pattern.compile(regexline);
							Matcher mline = pline.matcher(line);
							if(mline.find()){
								  bikou = mline.group(2);
							}
						}else if((lineNo == 10) && (sptext.length != 1)){
							String regexline = "(^H2\\s)(\\S.+\\S)([\\s]+$)";
							Pattern pline = Pattern.compile(regexline);
							Matcher mline = pline.matcher(line);
							if(mline.find()){
								  bikou = bikou + "↑" + mline.group(2);
							}
						}else if((lineNo == 11) && (sptext.length != 1)){
							String regexline = "(^H2\\s)(\\S.+\\S)([\\s]+$)";
							Pattern pline = Pattern.compile(regexline);
							Matcher mline = pline.matcher(line);
							if(mline.find()){
								  bikou = bikou + "↑" + mline.group(2);
							}
						}else if(lineNo >= 13){
							String regexline = "(^D2\\s)(.*[\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?(\\S+[\\s]+$)";
							Pattern pline = Pattern.compile(regexline);
							Matcher mline = pline.matcher(line);
							if(mline.find()){
								if(mline.group(3).equals("0")){
									sql_2 += ",(\'" + mline.group(2) + "\',\'\',\'\',\'\')";
								}else{
									String regexitem = "(.*\\S)[\\s]+?([\\S]+?)$";
									Pattern pitem = Pattern.compile(regexitem);
									Matcher mitem = pitem.matcher(mline.group(2));
									if(mitem.find()){
										sql_2 += ",(\'" + mitem.group(1) + "\',\'" + mline.group(3) + "\',\'" + mitem.group(2) + "\',\'" + mline.group(4) + "\')";
									}
								}
							}
						}
					}
					filereader.close();
					yearmonthday = yearmonthday + "\t" +yearmonthday + "\t" + yearmonthday;
					clientname = clientname + "\t" + keisyou;
					sql_1 = ",(\'" + fileno + "\')," + "(\'" + cnumber + "\')," + "(\'" + clientname + "\')," + "(\'\'),(\'\')," + "(\'" + yearmonthday + "\'),"
					+ "(\'"+ nouki + "\')," + "(\'"+ koujibasho + "\')," + "(\'" + shiharai + "\')," + "(\'" + kigen + "\')," + "(\'\t \')," + "(\'\')";
					sql_2 = ",(\'" + bikou + "\',\'\',\'\',\'\'),(\'値引き\',\'\',\'\',\'\')" + sql_2;
				}else{//-------------------------------------------------------新データファイル用--------------------------------
					if(fd.detector() !=null){
						readFileUtf = new InputStreamReader(readFile,fd.detector());
					}else{
						readFileUtf = new InputStreamReader(readFile,"SHIFT_JIS");
					}
					BufferedReader  filereader = new BufferedReader(readFileUtf);
					while((line = filereader.readLine()) != null){
						lineNo++;
						if(lineNo <= 16){
							sql_1 += "," + "(\'" + line + "\')";
						}else if(lineNo >= 18){
							sql_2 += ",(" + "\'" + line.replaceAll("\t", "\',\'") + "\')";
						}
					}
					filereader.close();
				}
				sql_1 = "INSERT INTO TMP_1 (TMP_1_DATA) values " + sql_1.replaceFirst(",", "");
				String table_1 = "TMP_1";
				String column_1 = "DATA_NO";
				sql_2 = "INSERT INTO CONST_DATA (ITEM,QUANTITY,UNIT,UNIT_PRICE) values " + sql_2.replaceFirst(",", "");
				String table_2 = "CONST_DATA";
				String column_2 = "KEY1";
				DbConnect dbconnect = new DbConnect();
				dbconnect.dBInsert(sql_1,table_1,column_1);
				dbconnect.dBInsert(sql_2,table_2,column_2);
				try {
					dbconnect.disconnect();
				}catch (SQLException e) {
					e.printStackTrace();
				}
				readFile.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			dbData();
		}
		private int dbData() {
			int taxType = 0;
			DbConnect dbconnect = new DbConnect();
			String[] tmpData = dbconnect.tmpReText("TMP_1");
			String[][] tmpData2 = dbconnect.tmpReData("CONST_DATA","IN");
			try {
				dbconnect.disconnect();
				String[] date_0 = tmpData[5].split("\t",-1);
				String date_1 = date_0[0];
				Locale localeJP = new Locale("ja","JP","JP");
				DateFormat sdf = new SimpleDateFormat("GGGGy年M月d日",localeJP);
				if(!date_1.equals(""))file_date_1.setText(sdf.format(DateFormat.getDateInstance().parse(date_1)));
				if(date_0.length > 1){
					String date_2 = date_0[1];
					if(!date_2.equals(""))file_date_2.setText(sdf.format(DateFormat.getDateInstance().parse(date_2)));
					String date_3 = date_0[2];
					if(!date_3.equals(""))file_date_3.setText(sdf.format(DateFormat.getDateInstance().parse(date_3)));
				}
			} catch (SQLException e) {
				e.printStackTrace(); } catch (ParseException e) {
			}
			//--------------------------------------------------------------------------
			model_F_1.setSelectedItem(tmpData[2].split("\t", 0)[0]);
			model_H_1.setSelectedItem(tmpData[2].split("\t", 2)[1]);
			model_C_1.setSelectedItem(tmpData[4]);
			model_F_2.setSelectedItem(tmpData[10].split("\t",2)[0]);
			model_H_2.setSelectedItem(tmpData[10].split("\t", 2)[1]);
			model_C_2.setSelectedItem(tmpData[11]);
			if(tmpData.length > 12){
				checkBox_1.setSelected(Boolean.valueOf(tmpData[12]));
				checkBox_2.setSelected(Boolean.valueOf(tmpData[13]));
				checkBox_3.setSelected(Boolean.valueOf(tmpData[14]));
				taxType = Integer.valueOf(tmpData[15]);
			}

			//------------------------見積書用データ----------------------------------
			if(tmpData[0].indexOf("-") != -1){
				label_fileNo_1.setText(tmpData[0].split("-",0)[0]);
				label_fileNo_2.setText(tmpData[0].split("-",0)[1]);
			}else{
				label_fileNo_1.setText(tmpData[0]);
				label_fileNo_2.setText("");
			}
			textField_deliveryDate.setText(tmpData[6]);
			textField_constructionSite.setText(tmpData[7]);
			textField_paymentTerms.setText(tmpData[8]);
			textField_expirationDate.setText(tmpData[9]);
			//---------------------------------------------------------------------------
			textArea_Proviso.setText(tmpData2[0][0].replaceAll("↑", "\n"));
			textField_Discount.setText(tmpData2[1][3]);
			tableModel.setRowCount(0);
			for(int ii = 2; ii < tmpData2.length; ii++){
				tableModel.addRow(tmpData2[ii]);
			}
			NumberFormat nf = NumberFormat.getInstance();
			nf.setRoundingMode(RoundingMode.HALF_UP);
			//DecimalFormat df = new DecimalFormat(",##0");
			DecimalFormat df = new DecimalFormat(",###.##");
			df.setRoundingMode(RoundingMode.HALF_UP);
			new SetTotal();
			table.setColumnSelectionAllowed(false);
		    table.setRowSelectionAllowed(false);
		    //-----------------------セルをワンクリックで編集できるようにする------------------------------
		    /*
    		DefaultCellEditor ce = (DefaultCellEditor)table.getDefaultEditor(Object.class);
    		ce.setClickCountToStart(1);
    		*/
		    //---------------------------------------------------------------------------------------------------
		    table.addKeyListener(new MyKeyAdapter());
		    table.addFocusListener(new FocusAdapter(){
		    	public void focusGained(FocusEvent fe){
		    		int cel = table.getSelectedColumn();
		    		if((cel == 1) || (cel == 3) || (cel == 4)) {
		    			table.getInputContext().setCompositionEnabled(false);
		    		}else {
		    			table.getInputContext().setCompositionEnabled(true);
		    		}
		    	}
		    	//public void focusLost(FocusEvent fe){
					//table.getInputContext().setCompositionEnabled(false);
		    	//	}
		    });
		    //-----------------------------------------------------
			table.getModel().addTableModelListener(
				new TableModelListener(){
		    	public void tableChanged(TableModelEvent te){
						int row = te.getLastRow();
						int col = te.getColumn();
						if((table.getRowCount() !=0) && (col != 4)){
							//CalcTotal price = new CalcTotal(table,row,col);
							CalcTotal price = new CalcTotal();
							if((col == 1) || (col == 3)){
								boolean hz = isHalfWidthAlphanumeric(table.getValueAt(row, col).toString());
								if(hz){
									//double row1num = price.chNumber(col);
									//table.setValueAt(df.format(new BigDecimal(row1num)), row, col);
									BigDecimal row1num = price.chNumber(row,col);
									table.setValueAt(df.format(row1num), row, col);
									//table.setValueAt(df.format(price.row_S), row, col);
								}
							}
							if(!table.getValueAt(row, 1).equals("") && !table.getValueAt(row, 3).equals("")){
								BigDecimal row4Price = price.rePrice(row);
								table.setValueAt(df.format(row4Price), row, 4);
								//table.setValueAt(df.format(new BigDecimal(row4Price)), row, 4);
							}else{
								table.setValueAt("", row, 4);
							}
							new SetTotal();
						}else{
						}
					}
				}
			);
			return taxType;
		}
		public Boolean isHalfWidthAlphanumeric(String value) {
		    if ( value == null || value.length() == 0 )
		        return false;
		    int len = value.length();
		    byte[] bytes = value.getBytes();
		    if ( len != bytes.length ) return true;
		    return false;
		}
	}
    class MyKeyAdapter extends KeyAdapter {
    	public void keyPressed(KeyEvent ev){
    		int row = table.getSelectedRow();
    		int column = table.getSelectedColumn();
    		int kcod = ev.getKeyCode();
    		table.editCellAt(row, column);
    		if(kcod == 10){
    			try {
    				if(table.isEditing()){
    					table.getCellEditor().stopCellEditing();
    				}
    			}catch(Exception e){
    				return;
    			}
    		}
    	}
    }
	class SetTotal{//----------------------消費税と合計金額の計算
		SetTotal(){
			this(6);
		}
		SetTotal(int type){
			CalcTotal price = new CalcTotal();
			BigDecimal total_Price = price.reTotal();
			final DecimalFormat df = new DecimalFormat(",##0");
			df.setRoundingMode(RoundingMode.HALF_UP);
			textField_Discount.setVisible(false);
			label_Discount.setVisible(false);
			if(lbl_title.getText().equals("納　品　書")){
				if(!textField_Discount.getText().equals("")){
					total_Price = total_Price.add(new BigDecimal(textField_Discount.getText().replaceAll(",", "")));
				}
				textField_Discount.setVisible(true);
				label_Discount.setVisible(true);
			};
			textField_TotalPrice.setText(df.format(total_Price));
			float getTax = Float.parseFloat(new PropSet().PropGet("set.Properties", "tax")) / 100;
			String getTaxRate = String.valueOf(getTax);
			String getTaxIncluded = String.valueOf(getTax + 1);
			//String getTaxS = Integer.toString(getTax);
			//BigDecimal getTax = (new BigDecimal((new PropSet().PropGet("set.Properties", "tax")))).divide(new BigDecimal("100"));
			switch (type) {
			case 1:
				textField_Tax.setVisible(false);
				label_Tax.setVisible(false);
				textField_BeforeTax.setText(df.format(total_Price));
				break;
			case 4:
				textField_Tax.setVisible(false);
				label_Tax.setVisible(false);
				textField_BeforeTax.setText(df.format(total_Price));
				break;
			case 5:
				textField_Tax.setVisible(true);
				label_Tax.setVisible(true);
				BigDecimal noTax = total_Price.divide(new BigDecimal(getTaxIncluded),1,RoundingMode.HALF_UP);
				textField_Tax.setText(df.format(noTax.multiply(new BigDecimal(getTaxRate))));
				textField_BeforeTax.setText(df.format(total_Price));
				break;
			default:
				textField_Tax.setText(df.format(total_Price.multiply(new BigDecimal(getTaxRate))));
				textField_BeforeTax.setText(df.format(total_Price.multiply(new BigDecimal(getTaxIncluded))));
				break;
			}
		}
	}
	class CalcTotal{
		//private String row_S;
		//private String row1_S;
		//private String row2_S;
		private int rowCount;
		//private JTable calcTable;

		CalcTotal(JTable table){
			//calcTable = table;
			rowCount  = table.getRowCount();
		}
		CalcTotal(){
			//calcTable = table;
			rowCount  = table.getRowCount();
		}
/*
		CalcTotal(JTable table,int row ,int col){
			calcTable = table;
			rowCount  = table.getRowCount();

			row1_S = table.getValueAt(row, 1).toString().replaceAll(",", "");
			row2_S = table.getValueAt(row, 3).toString().replaceAll(",", "");
			row1_S = Normalizer.normalize(row1_S, Normalizer.Form.NFKC);
			row2_S = Normalizer.normalize(row2_S, Normalizer.Form.NFKC);
			//row_S = table.getValueAt(row, col).toString().replaceAll(",", "");
			//row_S = Normalizer.normalize(row_S, Normalizer.Form.NFKC);
		}
*/
		public BigDecimal chNumber(int row,int col){
			String row_S = table.getValueAt(row, col).toString().replaceAll(",", "");
			row_S = Normalizer.normalize(row_S, Normalizer.Form.NFKC);
			BigDecimal rowN = new BigDecimal("0");
			if(col == 1){
				rowN = new BigDecimal(row_S);
				//row = Double.parseDouble(row1_S);
			}else if(col == 3){
				rowN = new BigDecimal(row_S);
				//row = Double.parseDouble(row2_S);
			}
			return rowN;
		}

		public BigDecimal rePrice(int row){
			String row1_S = table.getValueAt(row, 1).toString().replaceAll(",", "");
			String row2_S = table.getValueAt(row, 3).toString().replaceAll(",", "");
			row1_S = Normalizer.normalize(row1_S, Normalizer.Form.NFKC);
			row2_S = Normalizer.normalize(row2_S, Normalizer.Form.NFKC);
			BigDecimal row1 = new BigDecimal(row1_S);
			BigDecimal row2 = new BigDecimal(row2_S);
			BigDecimal row4 = row1.multiply(row2);
			return row4;
		}

		public BigDecimal reTotal(){
			BigDecimal total = new BigDecimal("0.0");
			for(int i = 0; i < rowCount; i++){
				if(!table.getValueAt(i,4).equals("")){
					total = total.add(new BigDecimal(table.getValueAt(i, 4).toString().replaceAll(",", "")));
				}
			}
			return total;
		}

	}
	class MyCellRenderer extends JLabel implements ListCellRenderer<Object>{
		private static final long serialVersionUID = 1L;
		MyCellRenderer(){
			setOpaque(true);
		}
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
			String data = value.toString();
			setText(data.toUpperCase());
			if (isSelected){
				setForeground(Color.white);
				setBackground(Color.black);
			}else{
				setForeground(Color.black);
				setBackground(Color.white);
			}
			return this;
		}
	}
	class Ad extends WindowAdapter implements ActionListener {
		public int taxType;
		public String taxTypeText;
		public Ad(){
		};
		public Ad(String taxtypeText){
			taxTypeSet(taxtypeText);
		};
		public void actionPerformed(ActionEvent e) {
			taxTypeSet(e.getActionCommand());
			return;
		}
		private String taxTypeSet(String taxTypeText){
			switch (taxTypeText) {
			case "無し":
				taxType = 1;
				this.taxTypeText = taxTypeText;
				new SetTotal(taxType);
				break;
			case "税別":
				textField_Tax.setVisible(true);
				label_Tax.setVisible(true);
				new SetTotal();
				taxType = 2;
				this.taxTypeText = taxTypeText;
				break;
			case "税込":
				textField_Tax.setVisible(true);
				label_Tax.setVisible(true);
				taxType = 3;
				this.taxTypeText = taxTypeText;
				new SetTotal(taxType);
				break;
			case "総額":
				textField_Tax.setVisible(false);
				label_Tax.setVisible(false);
				taxType = 4;
				this.taxTypeText = taxTypeText;
				new SetTotal(taxType);
				break;
			case "総額+税":
				textField_Tax.setVisible(true);
				label_Tax.setVisible(true);
				taxType = 5;
				this.taxTypeText = taxTypeText;
				new SetTotal(taxType);
				break;
			default:
				taxType = 0;
				this.taxTypeText = taxTypeText;
				break;
			}
			return taxTypeText;
		}
	}
	//--------------------スプラッシュウィンドウの表示----------------------------------------------
	class OpenSplashWindow extends Thread {
		public OpenSplashWindow(String spImage,Display display,Rectangle dispRect) {
			// TODO 自動生成されたコンストラクター・スタブ
			Shell splash = new Shell(SWT.ON_TOP);
			splash.setLayout(new GridLayout(1, false));
			Label label = new Label(splash, SWT.NONE);
			Image img = new Image(display, spImage);
			label.setImage(img);
			Label loadLabel = new Label(splash, SWT.NONE);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			loadLabel.setLayoutData(gd);

			splash.pack();

			//スプラッシュウィンドウを中央に配置
			Rectangle shellRect = splash.getBounds();
			int x = (dispRect.width - shellRect.width) / 2;
			int y = (dispRect.height - shellRect.height) / 2;
			//位置の指定はpack()のあとに呼ぶ必要がある
			splash.setLocation(x, y);
			splash.open();

			for (int i = 0; i < 10; i++) {
				loadLabel.setText("読み込み中 ... " + i * 10 + "%");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			loadLabel.setText("完了");
			img.dispose();
			splash.dispose();
		}
		public void run(){
		}
	}
}