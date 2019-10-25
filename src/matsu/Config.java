package matsu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Config extends MainWindow{
	private static final long serialVersionUID = 5466080313645480240L;
	private JTextField newFolderName;
	//public Properties properties;
	/**
	 * Create the frame.
	 */
	public Config() {
	}
	public Config(int sett) {
		setTitle("設定");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 243);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		setVisible(true);

		JTabbedPane configTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		configTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		layout.putConstraint(SpringLayout.NORTH, configTabbedPane, 20, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, configTabbedPane, 26, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, configTabbedPane, -10, SpringLayout.SOUTH, contentPane);
		layout.putConstraint(SpringLayout.EAST, configTabbedPane, 0, SpringLayout.EAST, contentPane);
		contentPane.add(configTabbedPane,BorderLayout.CENTER);

//-------------------ファイルNo変更タブ-------------------------------------------------------------//
		JPanel tabPane1 = new JPanel();
		SpringLayout layoutTabbedPane1 = new SpringLayout();
		tabPane1.setLayout(layoutTabbedPane1);
		configTabbedPane.addTab("ファイルNo変更", tabPane1);

		JLabel label_No = new JLabel("新規No.");
		layoutTabbedPane1.putConstraint(SpringLayout.NORTH, label_No, 10, SpringLayout.NORTH, tabPane1);
		layoutTabbedPane1.putConstraint(SpringLayout.SOUTH, label_No, 40, SpringLayout.NORTH, tabPane1);
		layoutTabbedPane1.putConstraint(SpringLayout.WEST, label_No, 10, SpringLayout.WEST, tabPane1);
		layoutTabbedPane1.putConstraint(SpringLayout.EAST, label_No,60, SpringLayout.WEST, tabPane1);
		tabPane1.add(label_No);

		JTextField textField_No = new JTextField();
		textField_No.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe){
				textField_No.getInputContext().setCompositionEnabled(false);
			}
			public void focusLost(FocusEvent fe){
				//textArea_Constpart.getInputContext().setCompositionEnabled(false);
			}
		});
		layoutTabbedPane1.putConstraint(SpringLayout.NORTH, textField_No, 0, SpringLayout.NORTH, label_No);
		layoutTabbedPane1.putConstraint(SpringLayout.SOUTH, textField_No, 0, SpringLayout.SOUTH, label_No);
		layoutTabbedPane1.putConstraint(SpringLayout.WEST, textField_No, 0, SpringLayout.EAST, label_No);
		layoutTabbedPane1.putConstraint(SpringLayout.EAST, textField_No, 80, SpringLayout.EAST, label_No);
		tabPane1.add(textField_No);
		textField_No.setColumns(10);

		File file = new File("number.DAT");
		JButton btnNewButton = new JButton("設定");
		layoutTabbedPane1.putConstraint(SpringLayout.NORTH, btnNewButton, 0, SpringLayout.NORTH, textField_No);
		layoutTabbedPane1.putConstraint(SpringLayout.WEST, btnNewButton, 50, SpringLayout.EAST, textField_No);
		layoutTabbedPane1.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, textField_No);
		layoutTabbedPane1.putConstraint(SpringLayout.EAST, btnNewButton, 150, SpringLayout.EAST, textField_No);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newNo = textField_No.getText();
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(file));
					bw.write(newNo);
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		tabPane1.add(btnNewButton);

		if(checkBeforeReadfile(file)){
			String reg = "^[^0-9]*([0-9]*$)";
			Pattern p = Pattern.compile(reg);
			try {
				FileInputStream brFile = new FileInputStream(file);
				InputStreamReader brFileUtf = new InputStreamReader(brFile,"UTF-8");
				BufferedReader  br = new BufferedReader(brFileUtf);
				String str = null;
				Matcher m = p.matcher(br.readLine());
				if(m.find())str = m.group(1);
				int intNo = Integer.valueOf(str).intValue();
				br.close();
				String intStr = String.valueOf(intNo);
				textField_No.setText(intStr);
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

//-------------------フォルダの作成タブ--------------------------------------------------------------//
		JPanel tabPane2 = new JPanel();
		SpringLayout layoutTabbedPane2 = new SpringLayout();
		tabPane2.setLayout(layoutTabbedPane2);
		configTabbedPane.addTab("フォルダ作成", tabPane2);
		JLabel NewFolderNameLabel = new JLabel("フォルダ名　files_");

		layoutTabbedPane2.putConstraint(SpringLayout.NORTH, NewFolderNameLabel, 10, SpringLayout.NORTH, tabPane2);
		layoutTabbedPane2.putConstraint(SpringLayout.WEST, NewFolderNameLabel, 10, SpringLayout.WEST, tabPane2);
		tabPane2.add(NewFolderNameLabel);

		newFolderName = new JTextField();
		newFolderName.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe){
				newFolderName.getInputContext().setCompositionEnabled(false);
			}
			public void focusLost(FocusEvent fe){
				//textArea_Constpart.getInputContext().setCompositionEnabled(false);
			}
		});
		layoutTabbedPane2.putConstraint(SpringLayout.NORTH, newFolderName, 0, SpringLayout.NORTH, NewFolderNameLabel);
		layoutTabbedPane2.putConstraint(SpringLayout.WEST, newFolderName, 7, SpringLayout.EAST, NewFolderNameLabel);
		tabPane2.add(newFolderName);
		newFolderName.setColumns(10);

		JLabel statusLabel = new JLabel("");
		layoutTabbedPane2.putConstraint(SpringLayout.NORTH, statusLabel, 20, SpringLayout.SOUTH, newFolderName);
		layoutTabbedPane2.putConstraint(SpringLayout.WEST, statusLabel, 73, SpringLayout.WEST, tabPane2);
		layoutTabbedPane2.putConstraint(SpringLayout.SOUTH, statusLabel, 46, SpringLayout.SOUTH, newFolderName);
		layoutTabbedPane2.putConstraint(SpringLayout.EAST, statusLabel, 352, SpringLayout.WEST, tabPane2);
		tabPane2.add(statusLabel);;
		JButton newFolderButton = new JButton("作成");
		newFolderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newFolderNameText = newFolderName.getText();
				File newFolder = new File("./files_" + newFolderNameText);
				if(!newFolder.exists()){
					newFolder.mkdirs();
					statusLabel.setText("files_"+ newFolderNameText + "フォルダを作成しました。");
				}else{
					statusLabel.setText("files_"+ newFolderNameText + "はすでに存在します。");
				}
			}
		});
		layoutTabbedPane2.putConstraint(SpringLayout.WEST, newFolderButton, 20, SpringLayout.EAST, newFolderName);
		layoutTabbedPane2.putConstraint(SpringLayout.SOUTH, newFolderButton, 0, SpringLayout.SOUTH, newFolderName);
		tabPane2.add(newFolderButton);

//-------------------消費税設定タブ------------------------------------------------------------------//
		JPanel tabPane3 = new JPanel();
		SpringLayout layoutTabbedPane3 = new SpringLayout();
		tabPane3.setLayout(layoutTabbedPane3);
		configTabbedPane.addTab("消費税設定", tabPane3);

		JLabel label_Tax = new JLabel("消費税率");
		layoutTabbedPane3.putConstraint(SpringLayout.NORTH, label_Tax, 10, SpringLayout.NORTH, tabPane3);
		layoutTabbedPane3.putConstraint(SpringLayout.SOUTH, label_Tax, 40, SpringLayout.NORTH, tabPane3);
		layoutTabbedPane3.putConstraint(SpringLayout.WEST, label_Tax, 10, SpringLayout.WEST, tabPane3);
		layoutTabbedPane3.putConstraint(SpringLayout.EAST, label_Tax,70, SpringLayout.WEST, tabPane3);
		tabPane3.add(label_Tax);

		JTextField textField_Tax = new JTextField(10);
		textField_Tax.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe){
				textField_Tax.getInputContext().setCompositionEnabled(false);
			}
			public void focusLost(FocusEvent fe){
				//textArea_Constpart.getInputContext().setCompositionEnabled(false);
			}
		});
		textField_Tax.setHorizontalAlignment(JTextField.RIGHT);
		layoutTabbedPane3.putConstraint(SpringLayout.NORTH, textField_Tax, 0, SpringLayout.NORTH, label_Tax);
		layoutTabbedPane3.putConstraint(SpringLayout.SOUTH, textField_Tax, 0, SpringLayout.SOUTH, label_Tax);
		layoutTabbedPane3.putConstraint(SpringLayout.WEST, textField_Tax, 0, SpringLayout.EAST, label_Tax);
		layoutTabbedPane3.putConstraint(SpringLayout.EAST, textField_Tax, 40, SpringLayout.EAST, label_Tax);
		tabPane3.add(textField_Tax);
		//textField_Tax.setColumns(10);

		JLabel label_TaxPar = new JLabel("%");
		layoutTabbedPane3.putConstraint(SpringLayout.NORTH, label_TaxPar, 0, SpringLayout.NORTH, textField_Tax);
		layoutTabbedPane3.putConstraint(SpringLayout.SOUTH, label_TaxPar, 0, SpringLayout.SOUTH, textField_Tax);
		layoutTabbedPane3.putConstraint(SpringLayout.WEST, label_TaxPar, 0, SpringLayout.EAST, textField_Tax);
		layoutTabbedPane3.putConstraint(SpringLayout.EAST, label_TaxPar,40, SpringLayout.EAST, textField_Tax);
		tabPane3.add(label_TaxPar);

		JButton btnTax = new JButton("設定");
		layoutTabbedPane3.putConstraint(SpringLayout.NORTH, btnTax, 0, SpringLayout.NORTH, label_TaxPar);
		layoutTabbedPane3.putConstraint(SpringLayout.SOUTH, btnTax, 0, SpringLayout.SOUTH, label_TaxPar);
		layoutTabbedPane3.putConstraint(SpringLayout.WEST, btnTax, 0, SpringLayout.EAST,  label_TaxPar);
		layoutTabbedPane3.putConstraint(SpringLayout.EAST, btnTax, 100, SpringLayout.EAST,  label_TaxPar);

		String strpass = "set.properties";
		PropSet proptext = new PropSet();
		textField_Tax.setText(proptext.PropGet(strpass,"tax"));

		btnTax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBeforeReadfile(new File(strpass))){
					String newTax = textField_Tax.getText();
					proptext.PropReset(strpass, "tax",newTax);
				}else{
					System.out.println("ファイルが見つからないか開けません");
				}
			}
		});
		tabPane3.add(btnTax);
//-------------------工事名設定タブ------------------------------------------------------------------//
		JPanel tabPane4 = new JPanel();
		SpringLayout layoutTabbedPane4 = new SpringLayout();
		tabPane4.setLayout(layoutTabbedPane4);
		configTabbedPane.addTab("工事名設定", tabPane4);

		JLabel label_Constpart = new JLabel("工事部分");
		layoutTabbedPane4.putConstraint(SpringLayout.NORTH, label_Constpart, 0, SpringLayout.NORTH, tabPane4);
		layoutTabbedPane4.putConstraint(SpringLayout.SOUTH, label_Constpart, 20, SpringLayout.NORTH, tabPane4);
		layoutTabbedPane4.putConstraint(SpringLayout.WEST, label_Constpart, 10, SpringLayout.WEST, tabPane4);
		layoutTabbedPane4.putConstraint(SpringLayout.EAST, label_Constpart,100, SpringLayout.WEST, tabPane4);
		tabPane4.add(label_Constpart);

		JTextArea textArea_Constpart = new JTextArea(5,10);
		textArea_Constpart.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe){
				textArea_Constpart.getInputContext().setCompositionEnabled(true);
			}
			public void focusLost(FocusEvent fe){
				//textArea_Constpart.getInputContext().setCompositionEnabled(false);
			}
		});
		//textArea_Constpart.setHorizontalAlignment(JTextArea.RIGHT);
		layoutTabbedPane4.putConstraint(SpringLayout.NORTH, textArea_Constpart, 20, SpringLayout.NORTH, label_Constpart);
		layoutTabbedPane4.putConstraint(SpringLayout.SOUTH, textArea_Constpart, 80, SpringLayout.SOUTH, label_Constpart);
		layoutTabbedPane4.putConstraint(SpringLayout.WEST, textArea_Constpart, 0, SpringLayout.WEST, label_Constpart);
		layoutTabbedPane4.putConstraint(SpringLayout.EAST, textArea_Constpart, 0, SpringLayout.EAST, label_Constpart);
		tabPane4.add(textArea_Constpart);
		String constpartText = proptext.PropGetRegex(strpass, "constpart",",","\n");
		textArea_Constpart.setText(constpartText);

		JLabel label_Constname = new JLabel("工事内容");
		layoutTabbedPane4.putConstraint(SpringLayout.NORTH, label_Constname, 0, SpringLayout.NORTH, label_Constpart);
		layoutTabbedPane4.putConstraint(SpringLayout.SOUTH, label_Constname, 0, SpringLayout.SOUTH, label_Constpart);
		layoutTabbedPane4.putConstraint(SpringLayout.WEST, label_Constname, 5, SpringLayout.EAST, label_Constpart);
		layoutTabbedPane4.putConstraint(SpringLayout.EAST, label_Constname,105, SpringLayout.EAST, label_Constpart);
		tabPane4.add(label_Constname);

		JTextArea textArea_Constname = new JTextArea(5,10);
		textArea_Constname.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe){
				textArea_Constname.getInputContext().setCompositionEnabled(true);
			}
			public void focusLost(FocusEvent fe){
				//textArea_Constpart.getInputContext().setCompositionEnabled(false);
			}
		});
		//textArea_Constpart.setHorizontalAlignment(JTextArea.RIGHT);
		layoutTabbedPane4.putConstraint(SpringLayout.NORTH, textArea_Constname, 20, SpringLayout.NORTH, label_Constname);
		layoutTabbedPane4.putConstraint(SpringLayout.SOUTH, textArea_Constname, 80, SpringLayout.SOUTH, label_Constname);
		layoutTabbedPane4.putConstraint(SpringLayout.WEST, textArea_Constname, 0, SpringLayout.WEST, label_Constname);
		layoutTabbedPane4.putConstraint(SpringLayout.EAST, textArea_Constname, 0, SpringLayout.EAST, label_Constname);
		tabPane4.add(textArea_Constname);
		String constnameText = proptext.PropGetRegex(strpass, "constname",",","\n");
		textArea_Constname.setText(constnameText);

		JButton btnConst = new JButton("設定");
		layoutTabbedPane4.putConstraint(SpringLayout.NORTH, btnConst, 20, SpringLayout.NORTH, textArea_Constname);
		layoutTabbedPane4.putConstraint(SpringLayout.SOUTH, btnConst, -20, SpringLayout.SOUTH, textArea_Constname);
		layoutTabbedPane4.putConstraint(SpringLayout.WEST, btnConst, 10, SpringLayout.EAST,  textArea_Constname);
		layoutTabbedPane4.putConstraint(SpringLayout.EAST, btnConst, 100, SpringLayout.EAST,  textArea_Constname);
		btnConst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBeforeReadfile(new File(strpass))){
					String newConstPart = textArea_Constpart.getText();
					proptext.PropReset(strpass, "constpart",newConstPart);
					newConstPart = "\n" + newConstPart;
					String[] constpartdata = newConstPart.split("\n");
					DefaultComboBoxModel<String> newconstpartModel = new DefaultComboBoxModel<String>(constpartdata);
					MainWindow.constructionPart.setModel(newconstpartModel);

					String newConstName = textArea_Constname.getText();
					proptext.PropReset(strpass, "constname",newConstName);
					newConstName = "\n" + newConstName;
					String[] constnamedata = newConstName.split("\n");
					DefaultComboBoxModel<String> newconstnameModel = new DefaultComboBoxModel<String>(constnamedata);
					MainWindow.constructionName.setModel(newconstnameModel);

				}else{
					System.out.println("ファイルが見つからないか開けません");
				}
			}
		});
		tabPane4.add(btnConst);
//----------------------------------------------------------------------------------------------------//
		configTabbedPane.setSelectedIndex(sett -1);
	}
	boolean checkBeforeReadfile(File file){
		if (file.exists()){
			if (file.isFile() && file.canRead()){
				return true;
			}
		}
		return false;
	}
	//---------------------Class---------------------------------------------------//
	/*
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
					prop.load(new FileReader(propFile));
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
					Properties prop = new Properties();
					prop.load(new FileReader(propFile));
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
*/
}
