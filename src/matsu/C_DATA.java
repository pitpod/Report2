package matsu;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import javafx.scene.control.ComboBox;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

//import org.apache.derby.impl.tools.ij.ParseException;

public class C_DATA extends JFrame {
	private static final long serialVersionUID = -5825633345181669572L;
	/*
	 * Create the frame.
	 */
	public C_DATA() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 1430, 300);
		JPanel contentPane = new JPanel();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		setContentPane(contentPane);
		String[] columnNames = {"No.", "コードNo.", "会社名", "部署名","郵便番号","住所","TEL","FAX","取引銀行","支店名","口座名","口座番号","口座名義","許可番号"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
		JTable table = new JTable(tableModel);
		DefaultTableColumnModel columnModel = (DefaultTableColumnModel)table.getColumnModel();
		for (int i = 0 ,ii; i < columnModel.getColumnCount() ; i++){
			TableColumn column = columnModel.getColumn(i);
			DefaultTableCellRenderer celRenderer = new DefaultTableCellRenderer();
			switch (i){
			case 0:
				ii = 30;
				celRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				celRenderer.setEnabled(false);
				break;
			case 1:
				ii = 80;
				celRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				break;
			case 2:
				ii = 160;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 3:
				ii = 140;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 4:
				ii = 80;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 5:
				ii = 200;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 6:
				ii = 100;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 7:
				ii = 100;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 8:
				ii = 80;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 9:
				ii = 80;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 10:
				ii = 60;
				celRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				break;
			case 11:
				ii = 80;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 12:
				ii = 240;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 13:
				ii = 240;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			default:
				ii = 100;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				celRenderer.setEnabled(false);
			}
			column.setCellRenderer(celRenderer);
			column.setPreferredWidth(ii);
		}
		dataRead(tableModel);

		JScrollPane sp = new JScrollPane(table);
		layout.putConstraint(SpringLayout.NORTH, sp, 5, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, sp, -40, SpringLayout.SOUTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, sp, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, sp, -5, SpringLayout.EAST, contentPane);
		sp.setPreferredSize(new Dimension(1670, 70));
		table.setBackground(Color.WHITE);
		contentPane.add(sp);

		JButton btnNewButton = new JButton("新規入力");
		String[] addTableData = {"","","","","","","","","","","","","",""};
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableModel.addRow(addTableData);
			}
		});
		layout.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.EAST, btnNewButton, -350, SpringLayout.EAST, sp);
		contentPane.add(btnNewButton);

		JButton btnInput = new JButton("登録");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new ReplaceData(table);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				dataRead(tableModel);
			}
		});
		layout.putConstraint(SpringLayout.NORTH, btnInput, 6, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.EAST, btnInput, -250, SpringLayout.EAST, sp);
		contentPane.add(btnInput);

		JButton btnDelButton = new JButton("削除");
		btnDelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					int rowCount = table.getSelectedRow();
					int rowValue = Integer.parseInt(table.getValueAt(rowCount, 0).toString());
					new DbConnect().delRow("C_DATA","KEY2",rowValue);
					dataRead(tableModel);
			}
		});
		layout.putConstraint(SpringLayout.NORTH, btnDelButton, 6, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.EAST, btnDelButton, -10, SpringLayout.EAST, sp);
		contentPane.add(btnDelButton);

		LinkedHashMap<String, Object> rowValueHashMap = new LinkedHashMap<String, Object>();
		JButton btnUpdateButton = new JButton("修正");
		btnUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] C_columnNames = {"KEY2","C_NO","C_NAME","DIVISION","C_ZIPCODE","C_ADDRESS","C_TEL","C_FAX","TRADING_BANK","T_B_BRANCH","T_B_ACCOUNT","T_B_ACCOUNT_NUMBER","T_B_ACCOUNT_HOLDER","LICEN_NUMBER"};
				int rowCount = table.getSelectedRow();
				for(int ii = 0;ii < 14;ii++){
					if((ii == 11) || (ii == 0)){
						if((table.getValueAt(rowCount,ii) == null) || (table.getValueAt(rowCount,ii).equals(""))){
							rowValueHashMap.put(C_columnNames[ii], 0);
						}else{
							rowValueHashMap.put(C_columnNames[ii], Integer.parseInt(table.getValueAt(rowCount, ii).toString()));
						}
					}else{
						rowValueHashMap.put(C_columnNames[ii], table.getValueAt(rowCount, ii));
					}
				}
				new DbConnect().updateRow("C_DATA","KEY2",rowValueHashMap);
				dataRead(tableModel);
				new MainWindow().comboBoxListCh();
			}
		});
		layout.putConstraint(SpringLayout.NORTH, btnUpdateButton, 6, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.EAST, btnUpdateButton, -100, SpringLayout.EAST, sp);
		contentPane.add(btnUpdateButton);

		this.setVisible(true);
	}
	public String[][] dataRead(DefaultTableModel tableModel){
		String[][] dbreF_Data = new DbConnect().tmpC_DATA("C_DATA");
		tableModel.setRowCount(0);
		for(int iii = 0; iii < dbreF_Data.length; iii++){
			tableModel.addRow(dbreF_Data[iii]);
		}
		return dbreF_Data;
	}

	class ReplaceData{
		ReplaceData(JTable table) throws ParseException{
			String sql_6 = "";
			for (int rowCount = 0;rowCount < table.getRowCount();rowCount++){
				String sql_5 = "";
				for (int celCount = 1;celCount <= 13;celCount++){
					Object rowValue = table.getValueAt(rowCount,celCount);
					if((celCount == 1) && (rowValue != null)){
						sql_5 =  ",(" + "\'" + rowValue.toString() + "\',";
					}else if((celCount == 11) && (rowValue != null)){
						if(rowValue.toString().equals("")){
							sql_5 =  sql_5 + null + ",";
						}else{
							sql_5 =  sql_5 + rowValue + ",";
						}
					}else if((celCount == 11) && (rowValue == null)){
						sql_5 =  sql_5 + null + ",";
					}else if((celCount == 12) && (rowValue != null)){
						sql_5 =  sql_5 + "\'" + rowValue.toString() + "\')";
					}else if((celCount == 12) && (rowValue == null)){
						sql_5 =  sql_5 + "" + "\'\')";
					}else if((celCount == 13) && (rowValue == null)){
						sql_5 =  sql_5 + "" + "\'\')";
					}else if(rowValue == null){
						sql_5 =  sql_5 + "\'\',";
					}else{
						sql_5 =  sql_5 + "\'" + rowValue.toString() + "\',";
					}
				}
				sql_6 += sql_5;
			}
			sql_6 = sql_6.replaceFirst(",","");
			sql_6 = "INSERT INTO C_DATA (C_NO,C_NAME,DIVISION,C_ZIPCODE,C_ADDRESS,C_TEL,C_FAX,TRADING_BANK,T_B_BRANCH,T_B_ACCOUNT,T_B_ACCOUNT_NUMBER,T_B_ACCOUNT_HOLDER,LICEN_NUMBER) values " + sql_6;
			String table_6 = "C_DATA";
			String column_6 = "KEY2";
			DbConnect dbconnect = new DbConnect();
			dbconnect.dBInsert(sql_6,table_6,column_6);
		}
	}
}
