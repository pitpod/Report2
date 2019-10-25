package matsu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class FileMove extends JDialog {
	private static final long serialVersionUID = -4466426843845567510L;
	String fromFilepath;
	String moveFilepath;
	/**
	 * Create the dialog.
	 */
	public FileMove() {
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setBounds(100, 100, 1060, 291);
		this.setTitle("ファイル選択");

		getContentPane().setLayout(new BorderLayout());

		String[] columnNames = {"ファイル名","書類番号", "顧客名", "工事内容"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames,11);
		JTable table = new JTable(tableModel);
		DefaultTableColumnModel columnModel = (DefaultTableColumnModel)table.getColumnModel();
		for (int i = 0 ,ii; i < columnModel.getColumnCount() ; i++){
			TableColumn column = columnModel.getColumn(i);
			//DefaultTableCellRenderer celRenderer = new DefaultTableCellRenderer();
			switch (i){
			case 0:
				ii = 120;
				//celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 1:
				ii =80;
				//celRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case 2:
				ii =190;
				//celRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				break;
			default:
				ii =400;
				//celRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			//column.setCellRenderer(celRenderer);
			column.setPreferredWidth(ii);
		}
		table.setAutoCreateRowSorter(true);

		SpringLayout layout = new SpringLayout();
		JPanel p = new JPanel();
		p.setLayout(layout);
		getContentPane().add(p, BorderLayout.CENTER);

		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(700,200));
		layout.putConstraint(SpringLayout.NORTH, sp, 5, SpringLayout.NORTH, p);
		layout.putConstraint(SpringLayout.SOUTH, sp, -5, SpringLayout.SOUTH, p);
		layout.putConstraint(SpringLayout.WEST, sp, 5, SpringLayout.WEST, p);
		layout.putConstraint(SpringLayout.EAST, sp, -5, SpringLayout.EAST, p);
		p.add(sp);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT,40,1));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
//---------------------------------------------------------------------------------------------------------------------
		JPanel buttonPane1 = new JPanel();
		buttonPane.add(buttonPane1);
		//int fm = 1;
		FileSelect fileSel = new FileSelect(1);

		Object[] yearFolder = fileSel.readFolder().toArray();
		JComboBox<Object> yearFolderList = new JComboBox<Object>(yearFolder);
		yearFolderList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileSel.readFile(yearFolderList, tableModel);
				System.gc();
			}
		});
		yearFolderList.setPreferredSize(new Dimension(80,25));
		yearFolderList.setFont(new Font("MS UI Gothic", Font.PLAIN, 16));
		yearFolderList.setToolTipText("移動元フォルダーを選んでください。");
		yearFolderList.setMaximumRowCount(18);
		buttonPane1.add(yearFolderList);
//---------------------------------------------------------------------------------------------------------------------
		JPanel buttonPane2 = new JPanel();
		buttonPane.add(buttonPane2);
		Object[] yearFolder2 = fileSel.readFolder().toArray();
		JComboBox<Object> yearFolderList2 = new JComboBox<Object>(yearFolder2);
		yearFolderList2.setPreferredSize(new Dimension(80,25));
		yearFolderList2.setFont(new Font("MS UI Gothic", Font.PLAIN, 16));
		yearFolderList2.setToolTipText("移動先フォルダーを選んでください。");
		yearFolderList2.setMaximumRowCount(18);
		buttonPane2.add(yearFolderList2);

		fileSel.readFile(yearFolderList, tableModel);
		System.gc();
		JButton searchButton = new JButton("移動");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowNumber = table.getSelectedRow();
				int modelRow = table.convertRowIndexToModel(rowNumber);
				String fromFolderPath = "files";
				if(!yearFolderList.getSelectedItem().equals("")){
					fromFolderPath = "files_" + yearFolderList.getSelectedItem();
				}
				String selectFile = (String) tableModel.getValueAt(modelRow, 0);
				fromFilepath = ".\\" +fromFolderPath + "\\" + selectFile;
				String moveFolderPath = "files";
				if(!yearFolderList2.getSelectedItem().equals("")){
					moveFolderPath = "files_" + yearFolderList2.getSelectedItem();
				}
				moveFilepath = ".\\" + moveFolderPath +"\\" + selectFile;
				File file1 = new File(fromFilepath); // 移動元
				File file2 = new File(moveFilepath); // 移動先
				Path file1Path = file1.toPath();
				Path file2Path = file2.toPath();
				try {
					Files.move(file1Path,file2Path);
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				//int fm = 1;
				FileSelect fileSel = new FileSelect(1);
				fileSel.readFile(yearFolderList, tableModel);
				System.gc();
			}
		});
		buttonPane2.add(searchButton);
//---------------------------------------------------------------------------------------------------------------------
		JPanel buttonPane3 = new JPanel();
		buttonPane.add(buttonPane3);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane3.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				return;
			}
		});
//---------------------------------------------------------------------------------------------------------------------
	}
}
