package matsu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;

public class FileSelect extends JDialog {
	private static final long serialVersionUID = -4466426843845567510L;
	String filepath;
	/**
	 * Create the dialog.
	 */
	public FileSelect(int fm){
	}

	public FileSelect() {
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setBounds(100, 100, 750, 291);
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
				ii =210;
				//celRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				break;
			default:
				ii =290;
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
//---------------------------------------------------------------------------------------------------------------------
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT,40,1));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
//---------------------------------------------------------------------------------------------------------------------
		JPanel buttonPane1 = new JPanel();
		buttonPane.add(buttonPane1);
//---------------------------------------------------------------------------------------------------------------------
		Object[] yearFolder = readFolder().toArray();
		JComboBox<Object> yearFolderList = new JComboBox<Object>(yearFolder);
		yearFolderList.setPreferredSize(new Dimension(100,25));
		yearFolderList.setFont(new Font("MS UI Gothic", Font.PLAIN, 16));
		yearFolderList.setToolTipText("フォルダーを選んでください。");
		yearFolderList.setMaximumRowCount(18);
		buttonPane1.add(yearFolderList);
//---------------------------------------------------------------------------------------------------------------------
		readFile(yearFolderList, tableModel);
//---------------------------------------------------------------------------------------------------------------------
		JButton FolderSelect = new JButton("フォルダ変更");
		FolderSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readFile(yearFolderList, tableModel);
			}
		});
		buttonPane1.add(FolderSelect);
//---------------------------------------------------------------------------------------------------------------------
		JPanel buttonPane2 = new JPanel();
		buttonPane.add(buttonPane2);
//---------------------------------------------------------------------------------------------------------------------
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane2.add(okButton);
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
		buttonPane2.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				return;
			}
		});
	}
	public ArrayList<String> readFolder(){
		ArrayList<String> folderList = new ArrayList<String>();
		folderList.add("");
		File dir = new File("./");
		Object files[] = dir.listFiles();
		Pattern pt = Pattern.compile("\\.\\\\"+ "files_" + "([0-9]{4})");
		for(int i = 0;i < files.length;i++){
			Matcher m = pt.matcher(files[i].toString());
			if(m.find()){
				folderList.add(m.group(1));
			}
		}
		this.dispose();
		return folderList;
	}
	public void readFile(JComboBox<Object> yearFolderList,DefaultTableModel tableModel){
		String changeFolderName = "files";
		if(!yearFolderList.getSelectedItem().equals("")){
			changeFolderName = "files_" + yearFolderList.getSelectedItem();
		}
		ArrayList<String[]> listData2 = createList(changeFolderName);
		String[][] listDataB =listData2.toArray(new String[0][]);
		TheComparator comparator = new TheComparator();
		comparator.setIndex(0);
		Arrays.sort(listDataB,comparator);
		tableModel.setRowCount(0);
		for(int ii = 0; ii < listDataB.length; ii++){
			tableModel.addRow(listDataB[ii]);
		}
	}
	public ArrayList<String[]> createList(String folderName){
		ArrayList<String[]> listData = new ArrayList<String[]>();
		String[] filenameArray = new String[4];
		File dir = new File("./" + folderName);
		Object files[] = dir.listFiles();
		Pattern pt = Pattern.compile("\\.\\\\"+ folderName + "\\\\([^(?:number)]*\\.DAT)");
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
					String line;
					String regex = ".*SAVE.*\\.DAT";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(matchString);
					int ii = 0;
					if(m.find()){//-------------------------------------------------旧データファイル用------------------------------
						readFileUtf = new InputStreamReader(readFile,"MS932");
						BufferedReader  filereader = new BufferedReader(readFileUtf);
						while((line = filereader.readLine()) != null){
							ii++;
							Pattern sp = Pattern.compile("[\\s]+");
							String[] sptext = sp.split(line);
							if(ii == 1){
								filenameArray[0] = matchString;
								filenameArray[1] = sptext[1];
							}else if(ii == 4){
								filenameArray[2] = sptext[1];
							}else if(ii ==13){
								String regexline = "(^D2\\s)(.*[\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?([\\S]+?)[\\s]+?(\\S+[\\s]+$)";
								Pattern pline = Pattern.compile(regexline);
								Matcher mline = pline.matcher(line);
								if(mline.find()){
									if(mline.group(3).equals("0")){
										filenameArray[3] = mline.group(2);
									}else{
										String regexitem = "(.*\\S)[\\s]+?([\\S]+?)$";
										Pattern pitem = Pattern.compile(regexitem);
										Matcher mitem = pitem.matcher(mline.group(2));
										if(mitem.find()){
											filenameArray[3] = mline.group(2);
										}
									}
								}
								listData.add(filenameArray.clone());
							}else if(ii >16){
								break;
							}
						}
						filereader.close();
					}else{
						BufferedReader  filereader = new BufferedReader(readFileUtf);
						while((line = filereader.readLine()) != null){
							ii++;
							if(ii == 1){
								filenameArray[0] = matchString;
								filenameArray[1] = line;
							}else if(ii == 3){
								filenameArray[2] = line;
							}else if(ii ==20){
								if(!line.equals("")) {
									filenameArray[3] = line;
									listData.add(filenameArray.clone());
									break;
								}
							}else if(ii ==21){
								filenameArray[3] = line;
								listData.add(filenameArray.clone());
							}else if(ii >21){
								break;
							}
						}
						filereader.close();
					}
					//filereader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//------------------------------------------------------------------------------------------
		//dispose();
		return listData;
	}
	public class TheComparator implements Comparator<Object> {
		private int index;
	    /** ソート対象のカラムの位置 */
	    /**private int index = 0;*/
	    /** ソートするためのカラム位置をセット */
	    public void setIndex( int index ) {
	        this.index = index;
	    }
	    public int compare( Object a, Object b ) {
	    	String[] strA = ( String[] ) a;
	        String[] strB = ( String[] ) b;
	        return ( strA[index ].compareTo( strB[index ] ) );
	    }
	}
}
