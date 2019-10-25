package matsu;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedHashMap;

//import org.eclipse.swt.widgets.TaskItem;
//import org.apache.derby.impl.tools.ij.ParseException;

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
import javax.swing.JButton;

public class F_DATA extends JFrame {
	private static final long serialVersionUID = -4462381167446279622L;
	/**
	 * Create the frame.
	 */
	public F_DATA() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 600, 300);
		JPanel contentPane = new JPanel();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		setContentPane(contentPane);
		String[] columnNames = {"No.", "顧客番号", "顧客名"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
		JTable table = new JTable(tableModel);
		DefaultTableColumnModel columnModel = (DefaultTableColumnModel)table.getColumnModel();
		for (int i = 0 ,ii; i < columnModel.getColumnCount() ; i++){
			TableColumn column = columnModel.getColumn(i);
			DefaultTableCellRenderer celRenderer = new DefaultTableCellRenderer();
			switch (i){
			case 0:
				ii = 40;
				celRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				celRenderer.setEnabled(false);
				break;
			case 1:
				ii = 70;
				celRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				celRenderer.setEnabled(false);
				break;
			case 2:
				ii = 480;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			default:
				ii = 100;
				celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
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
		sp.setPreferredSize(new Dimension(1430, 70));
		table.setBackground(Color.WHITE);
		contentPane.add(sp);
		final String[] addTableData = {"","","",""};
		JButton btnNewButton = new JButton("新規入力");
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
					int rowCount = table.getRowCount() - 1;
					if(table.getValueAt(rowCount,0).toString().equals("")){
						Object rowValue = table.getValueAt(rowCount,2);
						new DbConnect().addData(rowValue);
					}
				} catch (SQLException e) {
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
					new DbConnect().delRow("F_DATA","F_NO",rowValue);
					dataRead(tableModel);
			}
		});
		layout.putConstraint(SpringLayout.NORTH, btnDelButton, 6, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.EAST, btnDelButton, -10, SpringLayout.EAST, sp);
		contentPane.add(btnDelButton);

		LinkedHashMap<String, Object> rowValueHashMap = new LinkedHashMap<String, Object>();
		String[] F_columnNames = {"F_NO", "F_CODE_NO", "F_NAME"};
		JButton btnUpdateButton = new JButton("変更");
		btnUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = table.getSelectedRow();
				for(int ii = 0;ii < 3;ii++){
					rowValueHashMap.put(F_columnNames[ii], table.getValueAt(rowCount, ii));
				}
				new DbConnect().updateRow("F_DATA","F_NO",rowValueHashMap);
		//----------------
		dataRead(tableModel);
		//-----------------------
			}
		});
		layout.putConstraint(SpringLayout.NORTH, btnUpdateButton, 6, SpringLayout.SOUTH, sp);
		layout.putConstraint(SpringLayout.EAST, btnUpdateButton, -100, SpringLayout.EAST, sp);
		contentPane.add(btnUpdateButton);

		this.setVisible(true);
	}

	public String[][] dataRead(DefaultTableModel tableModel){
		String[][] dbreF_Data = new DbConnect().tmpF_DATA("F_DATA");
		tableModel.setRowCount(0);
		for(int iii = 0; iii < dbreF_Data.length; iii++){
			tableModel.addRow(dbreF_Data[iii]);
		}
		return dbreF_Data;
	}
/*
	class ReplaceData{
		ReplaceData() throws ParseException{
			String sql_6 = "";
			for (int rowCount = 0;rowCount < table.getRowCount();rowCount++){
				String sql_5 = "";
				for (int celCount = 1;celCount <= 2;celCount++){
					Object rowValue = table.getValueAt(rowCount,celCount);
					if((celCount == 1) && (rowValue != null)){
						sql_5 =  ",(" + "\'" + rowValue.toString() + "\',";
					}else if((celCount == 2) && (rowValue != null)){
						sql_5 =  sql_5 + "\'" + rowValue.toString() + "\')";
					}else if((celCount == 2) && (rowValue == null)){
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
			sql_6 = "INSERT INTO C_DATA (F_CODE_NO,F_NAME) values " + sql_6;
			String table_6 = "F_DATA";
			String column_6 = "F_NO";
			DbConnect dbconnect = new DbConnect();
			dbconnect.dBInsert(sql_6,table_6,column_6);
		}
	}
	*/
}
