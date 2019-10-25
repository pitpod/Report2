package matsu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import matsu.FileSelect.TheComparator;


public class FileSearch extends JDialog {
	private static final long serialVersionUID = -4466426843845567510L;
	String filepath;
	/**
	 * Create the dialog.
	 */
	public FileSearch() {
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setBounds(100, 100, 1060, 291);
		this.setTitle("ファイル選択");

		getContentPane().setLayout(new BorderLayout());

		String[] columnNames = {"ファイル名","書類番号", "検索結果","数量","単位","単価","金額"};
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
				ii =500;
				//celRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				break;
			default:
				ii =90;
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
		buttonPane1.setToolTipText("検索する年を選んでください。");
		buttonPane.add(buttonPane1);

		FileSelect fileSel = new FileSelect(1);
		Object[] yearFolder = fileSel.readFolder().toArray();
		JComboBox<Object> yearFolderList = new JComboBox<Object>(yearFolder);
		yearFolderList.setPreferredSize(new Dimension(80,25));
		yearFolderList.setFont(new Font("MS UI Gothic", Font.PLAIN, 16));
		yearFolderList.setToolTipText("フォルダーを選んでください。");
		yearFolderList.setMaximumRowCount(18);
		buttonPane1.add(yearFolderList);
//---------------------------------------------------------------------------------------------------------------------
		JPanel buttonPane2 = new JPanel();
		buttonPane.add(buttonPane2);

		JTextField searchTextField = new JTextField();
		searchTextField.setPreferredSize(new Dimension(100,25));
		searchTextField.setHorizontalAlignment(SwingConstants.LEFT);
		searchTextField.setToolTipText("検索ワードを入れてください。");
		searchTextField.setColumns(10);

		buttonPane2.add(searchTextField);

		JButton searchButton = new JButton("検索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFolder(yearFolderList,searchTextField, tableModel);
			}
		});
		buttonPane2.add(searchButton);
//---------------------------------------------------------------------------------------------------------------------
		JPanel buttonPane3 = new JPanel();
		buttonPane.add(buttonPane3);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane3.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowNumber = table.getSelectedRow();
				int modelRow = table.convertRowIndexToModel(rowNumber);
				String folderPath = "files";
				if(!yearFolderList.getSelectedItem().equals("")){
					folderPath = "files_" + yearFolderList.getSelectedItem();
				}
				filepath = (String) tableModel.getValueAt(modelRow, 0);
				filepath = ".\\" +folderPath + "\\" + filepath;
				setVisible(false);
				dispose();
				return;
			}
		});
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
	public void searchFolder(JComboBox<Object> yearFolderList,JTextField searchTextField,DefaultTableModel tableModel){
		String changeFolderName = "files";
		if(!yearFolderList.getSelectedItem().equals("")){
			changeFolderName = "files_" + yearFolderList.getSelectedItem();
		}
		ArrayList<String[]> listData2 = createList(changeFolderName,searchTextField.getText());
		String[][] listDataB =listData2.toArray(new String[0][]);
		FileSelect fileSel = new FileSelect(1);
		TheComparator comparator = fileSel.new TheComparator();
		comparator.setIndex(0);
		Arrays.sort(listDataB,comparator);
		tableModel.setRowCount(0);
		for(int ii = 0; ii < listDataB.length; ii++){
			tableModel.addRow(listDataB[ii]);
		}
	}
	public ArrayList<String[]> createList(String folderName,String searchText){
		ArrayList<String[]> listData = new ArrayList<String[]>();
		String[] searchTextArray = new String[7];
		File dir = new File("./" + folderName);
		Object files[] = dir.listFiles();
		Pattern pt = Pattern.compile("\\.\\\\"+ folderName + "\\\\([^(?:number)]*\\.DAT)");
		Pattern pt2 = Pattern.compile(".*"+ searchText + ".*");
		for(int i = 0; i<files.length; i++){
			Matcher matcher1 = pt.matcher(files[i].toString());
			if(matcher1.find()){
				String matchString = matcher1.group(1);
				try {
					FileInputStream readFile = new FileInputStream(files[i].toString());
					FileCharDetecter fd = new FileCharDetecter(files[i].toString());
					InputStreamReader readFileUtf;
					if(fd.detector() !=null){
						readFileUtf = new InputStreamReader(readFile,fd.detector());
					}else{
						readFileUtf = new InputStreamReader(readFile,"SHIFT_JIS");
					}
					String regex = ".*SAVE.*\\.DAT";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(matchString);
					int ii = 0;
					if(m.find()){//-------------------------------------------------旧データファイル用------------------------------
						readFileUtf = new InputStreamReader(readFile,"MS932");
						BufferedReader  filereader = new BufferedReader(readFileUtf);
						String line = filereader.readLine();
						String[] searchString = line.split("\\s",-1);
						searchTextArray[0] = matchString;
						searchTextArray[1] = searchString[1];
						while(line != null){
							if(ii >= 12){
								String regexline = "(^D2\\s)(.*[\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?(\\S+[\\s]+$)";
								Pattern pline = Pattern.compile(regexline);
								Matcher mline = pline.matcher(line);
								NumberFormat nfNum = NumberFormat.getNumberInstance();
								if(mline.find()){
									Matcher matcher2 = pt2.matcher(mline.group(2));
									if(matcher2.find()){
										if (mline.group(5).equals("0")){
											searchTextArray[2] = mline.group(2);
											searchTextArray[4] = mline.group(4);
										}else{
											String regexline2 = "(.*[\\S]+?)[\\s]+?([\\S]+)";
											Pattern pline2 = Pattern.compile(regexline2);
											Matcher mline2 = pline2.matcher(mline.group(2));
											if(mline2.find()){
												searchTextArray[2] = mline2.group(1);
												searchTextArray[4] = mline2.group(2);
											}
										}
										searchTextArray[3] = mline.group(3);
										searchTextArray[5] = nfNum.format(Float.parseFloat(mline.group(4)));
										searchTextArray[6] = nfNum.format(Float.parseFloat(mline.group(5)));
										listData.add(searchTextArray.clone());
									}
								}
							}
							line = filereader.readLine();
							ii++;
						}
						filereader.close();
					}else{
						BufferedReader  filereader = new BufferedReader(readFileUtf);
						String str = filereader.readLine();
						searchTextArray[0] = matchString;
						searchTextArray[1] = str;
						while(str != null){
							if(ii > 14){
								String[] searchString = str.split("\t",-1);
								Matcher matcher3 = pt2.matcher(searchString[0]);
								NumberFormat nfNum = NumberFormat.getNumberInstance();
								if(matcher3.find()){
									searchTextArray[2] = searchString[0];
									searchTextArray[3] = searchString[1];
									searchTextArray[4] = searchString[2];
									if(!(searchString[1].equals("")) && !(searchString[3].equals(""))){
										searchTextArray[5] = nfNum.format(Float.parseFloat(searchString[3]));
										searchTextArray[6] = String.valueOf(nfNum.format(Float.parseFloat(searchString[1]) * Float.parseFloat(searchString[3])));
									}
									listData.add(searchTextArray.clone());
								}
							}
							str = filereader.readLine();
							ii++;
						}
						filereader.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//------------------------------------------------------------------------------------------
		return listData;
	}
}
