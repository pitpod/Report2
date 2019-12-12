package matsu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class PrintSample extends JFrame {
	private static final long serialVersionUID = -253897230418097538L;
	private String printTitle;
	private Object[] printData1;
	private Object[][] printData2;
	private JButton btnNext;
	private JButton btnPre;
	private int dataCount;
	private int pageCount;
	private int pageQuotient;
	private int pageRemainder;
	private int pageCounter = 1;
	private String kingakuTitle;
	private int type;
	private boolean divisionCheck;
	private boolean todayDateCheck;
	private boolean telfaxCheck;

	public PrintSample()
	{

	}

	public PrintSample(String Title,Object[] printDataArray1,Object[][] printDataArray2,int taxType,boolean division,boolean todayDate,boolean telfax)
	{
		type = taxType;
		divisionCheck = division;
		todayDateCheck = todayDate;
		telfaxCheck = telfax;
		initComponents(Title,printDataArray1,printDataArray2);
	}

	protected JMenuBar createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.add(new AbstractAction("印刷") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e1) {
				btnPrintActionPerformed(e1);
			}
		});
		menu.add(new AbstractAction("Exit") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e2) {
				dispose();
			}
		});
		mb.add(menu);
		return mb;
	}

	public void initComponents(String Title,Object[] printDataArray1,Object[][] printDataArray2) {
		printData1 = printDataArray1;
		printData2 = printDataArray2;
		printTitle = Title;
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(10,10,630,890);
		this.setTitle(Title);
		this.setVisible(true);
		setJMenuBar(createMenu());
		JPanel jPanel_0 = new JPanel();
		JScrollPane scpanel = new JScrollPane(jPanel_0,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		SpringLayout layout_0 = new SpringLayout();
		layout_0.putConstraint(SpringLayout.NORTH, scpanel, 0, SpringLayout.NORTH, getContentPane());
		layout_0.putConstraint(SpringLayout.SOUTH, scpanel, 0, SpringLayout.SOUTH, getContentPane());
		layout_0.putConstraint(SpringLayout.EAST, scpanel, 0, SpringLayout.EAST, getContentPane());
		layout_0.putConstraint(SpringLayout.WEST, scpanel, 0, SpringLayout.WEST, getContentPane());
		getContentPane().setLayout(layout_0);
		getContentPane().add(scpanel);
		JPanel jPanel = new JPanel();
		jPanel_0.add(jPanel);
		jPanel.setPreferredSize(new Dimension(600, 810));
		SpringLayout layout = new SpringLayout();
		jPanel.setLayout(layout);
		jPanel.setOpaque(true);
		MyCanvas printCanvas = new MyCanvas();
		layout.putConstraint(SpringLayout.NORTH, printCanvas,50, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.WEST, printCanvas, 50, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.SOUTH, printCanvas, -20, SpringLayout.SOUTH, jPanel);
		layout.putConstraint(SpringLayout.EAST, printCanvas, -50, SpringLayout.EAST, jPanel);
		jPanel.add(printCanvas);

		btnNext = new JButton("次のページ");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(pageCounter < page(printData2)){
					pageCounter += 1;
					printCanvas.nextpage(pageCounter);
				}
			}
		});
		jPanel.add(btnNext);

		btnPre = new JButton("前のページ");
		layout.putConstraint(SpringLayout.NORTH, btnPre, 10, SpringLayout.NORTH, jPanel);
		layout.putConstraint(SpringLayout.WEST, btnPre, 200, SpringLayout.WEST, jPanel);
		layout.putConstraint(SpringLayout.NORTH, btnNext, 0, SpringLayout.NORTH, btnPre);
		layout.putConstraint(SpringLayout.WEST, btnNext, 26, SpringLayout.EAST, btnPre);
		btnPre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(pageCounter > 1){
					pageCounter -= 1;
					printCanvas.nextpage(pageCounter);
				}
			}
		});
		jPanel.add(btnPre);
		btnNext.setEnabled(false);
		btnPre.setEnabled(false);
	}

// グラフィック画像を生成する。扇形や円、長方形など
	public void paintData(Graphics g){
		int fontW;
		Locale locale = new Locale("ja", "JP", "JP");
		Calendar calendar = Calendar.getInstance(locale);
		DateFormat japaseseFormat = new SimpleDateFormat("GGGG  y 年 M 月 d 日", locale);
		String dateStr = japaseseFormat.format(calendar.getTime());
		String atesaki = "";
		String keisyou = "";
		String fNo = "";
		String kaisyamei = "";
		String hizuke = "";
		String zipcode = "";
		String address = "";
		String division = "";
		String tel = "";
		String fax = "";
		String TB_Branch = "";
		String TB_Account = "";
		String TBA_Holder = "";
		String deliveryDate = "";
		String constructionSite = "";
		String paymentTerms = "";
		String expirationDate = "";
		String printTitle2 = null;
		String taxText = "";
		BasicStroke stroke1 = new BasicStroke(0.3f); //細線に変更
		((Graphics2D) g).setStroke(stroke1); //線の種類を設定
		if (printTitle.replaceAll("　", "").equals("納品書")){
			kaisyamei = printData1[17].toString();
		}else{
			kaisyamei = printData1[5].toString();
		}
		//System.out.println(printData1[17].toString());
		String[] dbreC_Data = new DbConnect().tmpC_DATA_Where("C_DATA",kaisyamei);
		String LICEN_NUMBER = dbreC_Data[13].toString();
		if(telfaxCheck == true)
		{
			if(tel.equals(fax)) {
				fax = "TEL/FAX\u0020" + dbreC_Data[6].toString();
			}else {
				fax = "TEL/FAX\u0020" + dbreC_Data[6].toString() + "/" + dbreC_Data[7].toString();
			}
			kaisyamei = LICEN_NUMBER;
			division = dbreC_Data[2].toString();
			tel = dbreC_Data[3].toString();
		}else {
			kaisyamei = dbreC_Data[2].toString();
			division = dbreC_Data[3].toString();
			tel = "TEL\u0020" + dbreC_Data[6].toString();
			fax = "FAX\u0020" + dbreC_Data[7].toString();
		}
		if(divisionCheck != true){
			division = "";
		}
		switch(printTitle.replaceAll("　", "")){
		case "請求書":
			kingakuTitle = "御 請 求 金 額";
			printTitle2 = printTitle;
			keisyou = printData1[3].toString();
			fNo = printData1[1].toString();
			atesaki = printData1[2].toString();
			//cNo = printData1[4].toString();
			//kaisyamei = printData1[5].toString();
			if(todayDateCheck == true){
				hizuke = dateStr;
			}else{
				hizuke = printData1[6].toString();
			}
			zipcode = printData1[18].toString();
			address = printData1[20].toString();
			TB_Branch = printData1[31].toString();
			TB_Account = printData1[32].toString();
			TBA_Holder = printData1[33].toString();
			//-----------請求先---------------------------------------------
			g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 11));
			g.drawString("下記の通りご請求いたします。", 10, 134);
			g.drawString("取引銀行", 13, 164);
			g.drawString(TB_Branch, 73, 164);
			g.drawString("口座番号", 13, 180);
			g.drawString(TB_Account, 73, 180);
			g.drawString("口座名義", 13, 196);
			g.drawString(TBA_Holder, 73, 196);
			break;
		case "見積書":
			kingakuTitle = "御 見 積 金 額";
			printTitle2 = "御　" + printTitle;
			atesaki = printData1[2].toString();
			keisyou = printData1[3].toString();
			fNo = printData1[1].toString();
			//kaisyamei = printData1[5].toString();
			if(todayDateCheck == true){
				hizuke = dateStr;
			}else{
				hizuke = printData1[7].toString();
			}
			zipcode = printData1[18].toString();
			address = printData1[20].toString();
			//-----------見積条件---------------------------------------------
			deliveryDate = printData1[9].toString();
			constructionSite = printData1[10].toString();
			paymentTerms = printData1[11].toString();
			expirationDate = printData1[12].toString();
			g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 11));
			g.drawString("下記の通り御見積り申し上げます。", 10, 134);
			g.drawString("1.引渡期日", 13, 156);
			g.drawString(deliveryDate, 73, 156);
			g.drawLine(9, 159, 280, 159);//------------------------請求書下線
			g.drawString("2.工事場所", 13, 172);
			g.drawString(constructionSite, 73, 172);
			g.drawLine(9, 175, 280, 175);//------------------------請求書下線
			g.drawString("3.支払条件", 13, 188);
			g.drawString(paymentTerms, 73, 188);
			g.drawLine(9, 191, 280, 191);//------------------------請求書下線
			g.drawString("4.有効期限", 13, 204);
			g.drawString(expirationDate, 73, 204);
			g.drawLine(9, 207, 280, 207);//------------------------請求書下線
			break;
		case "納品書":
			kingakuTitle = "御 請 求 金 額";
			printTitle2 = printTitle;
			atesaki = printData1[14].toString();
			keisyou = printData1[15].toString();
			fNo = printData1[13].toString();
			//kaisyamei = printData1[17].toString();
			if(todayDateCheck == true){
				hizuke = dateStr;
			}else{
				hizuke = printData1[8].toString();
			}
			zipcode = printData1[19].toString();
			address = printData1[21].toString();
			/*
			if(divisionCheck == true)division = printData1[23].toString();
			tel = printData1[25].toString();
			fax = printData1[27].toString();
			*/
			g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 12));
			g.drawString("下記の通り納品致しました。", 10, 134);
			break;
		default:
		}
		//FontMetrics fm = g.getFontMetrics();
		//-----------ファイルNo----------------------------------------
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 12));
		g.drawString("No." + printData1[0], 415, 42);
		g.drawLine(412, 44, 540, 44);//-------------------------No.下線
		//------------お客様コード------------------------------------------
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 9));
		g.drawString("お客様コードNo.", 31, 34);
		g.drawString(fNo, 99, 34);
		//-----------タイトル-------------------------------------------------
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 20));
		FontMetrics fm = g.getFontMetrics();
		int fontTitle = fm.stringWidth(printTitle2);
		g.drawString(printTitle2, (int) (155 + ((320 - 155) - fontTitle)/2), 34);
		g.drawLine(155, 37, 320, 37);//------------------------請求書下線
		//-----------宛先-------------------------------------------------
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 16));
		FontMetrics fm2 = g.getFontMetrics();
		fontW = fm2.stringWidth(atesaki);
		g.drawString(atesaki, 120 - (fontW / 2), 108);
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 16));
		FontMetrics fm3 = g.getFontMetrics();
		int fontW2 = fm3.stringWidth(keisyou);
		g.drawString(keisyou, (int) (250 - fontW2), 108);
		g.drawLine(9, 111, 247, 111);//------------------------請求書下線
		//------------作成年月日----------------------------------------
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 12));
		g.drawString(hizuke, 344, 65);
		//-----------郵便番号------------------------------------------------
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 12));
		g.drawString(zipcode, 285, 83);
		//-----------住所------------------------------------------------
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 12));
		g.drawString(address, 285, 96);
		//-----------会社名-----------------------------------------------
		g.drawString(kaisyamei, 285, 109);
		//-----------事業部名----------------------------------------------
		g.drawString(division, 285, 122);
		//-----------TEL----------------------------------------------
		g.drawString(tel, 285, 135);
		//-----------FAX----------------------------------------------
		g.drawString(fax, 285, 148);
		//------------検印----------------------------------------------
		BasicStroke stroke2 = new BasicStroke(0.9f); //太線に変更
    	((Graphics2D) g).setStroke(stroke2); //線の種類を設定
		g.drawRect(364, 154, 117, 59);
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 12));
		g.drawString("検　印", 380, 168);
		g.drawString("担当者印", 430, 168);
		g.drawLine(364, 170, 481, 170);
		g.drawLine(426, 154, 426, 213);
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 16));
		g.drawString(kingakuTitle, 90,230 );
		String seikyu = printData1[37].toString();
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 12));
		switch (type) {
		case 1:

			break;
		case 2:
			taxText = "（消費税別）";
			seikyu = printData1[35].toString();
			//g.drawString("（消費税別）", (int) (250 + fontW*0.8),230 );
			break;
		default:
			taxText = "（消費税込）";
			//g.drawString("（消費税込）", (int) (250 + fontW*0.8),230 );
			break;
		}
		String seikyu2 = "\\" + seikyu + "-";
		fontW = fm.stringWidth(seikyu2);
		g.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 16));
		g.drawString(seikyu2, 250,230 );
		g.drawString(taxText, (int) (250 + fontW*0.8),230 );
    	((Graphics2D) g).setStroke(stroke1); //線の種類を設定
		g.drawLine(80, 233, 431, 233);//---------------------請求金額
	}

    public void ListPaintData(Graphics2D g2,BasicStroke stroke1,BasicStroke stroke2){
    	int row = 0;
    	int rowHeight =17;
		g2.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 10));
		FontMetrics fm = g2.getFontMetrics();
		String discount = printData1[34].toString();
		String totalPrice = printData1[35].toString();
		String tax = printData1[36].toString();
		String seikyu = printData1[37].toString();
		String[] proviso = printData1[38].toString().split("↑");
		g2.setStroke(stroke2); //-------------------------線の種類を設定
		g2.drawRect(9, 239, 472, 491);
		g2.drawLine(9, 255, 481, 255);
		koumoku(g2,stroke1,255);
		for (int LineNo = 1;LineNo <= 20;LineNo++){
			row = 255 + rowHeight*LineNo;
			g2.drawLine(9, row, 481, row);
		}
		//g2.setStroke(stroke1); //-------------------------線の種類を設定
		g2.setStroke(stroke2); //-------------------------線の種類を設定
		g2.drawLine(9, row + rowHeight, 481, row + rowHeight);
		g2.drawLine(9, row + rowHeight*2, 481, row + rowHeight*2);
		g2.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 10));
		if(printTitle.replaceAll("　", "").equals("納品書")){
			g2.drawString("値　引　き",99,row + rowHeight*2 - 4);
			Rectangle wDiscount = fm.getStringBounds(discount, g2).getBounds();
			g2.drawString(discount,475 -  wDiscount.width,row + rowHeight*2 - 4);
		}
		g2.drawString("小　　　計",99,row + rowHeight*3 - 4);
    	Rectangle wTotalPrice = fm.getStringBounds(totalPrice, g2).getBounds();
		g2.drawString(totalPrice,475  - wTotalPrice.width,row + rowHeight*3 - 4);
		g2.drawLine(9, row + rowHeight*3, 481, row + rowHeight*3);
		Rectangle wTax = fm.getStringBounds(tax, g2).getBounds();
		switch (type) {
		case 1:

			break;
		case 4:

			break;
		case 5:
			g2.drawString("（うち消費税）",94,row + rowHeight*4 - 4);
			g2.drawString("（" + tax + "）",465 - wTax.width,row + rowHeight*4 - 4);
			break;
		default:
			g2.drawString("消　費　税（10％）",99,row + rowHeight*4 - 4);
			g2.drawString(tax,475 - wTax.width,row + rowHeight*4 - 4);
			break;
		}
		g2.drawLine(9, row + rowHeight*4, 481, row + rowHeight*4);
    	Rectangle wSeikyu = fm.getStringBounds(seikyu, g2).getBounds();
		g2.drawString("合　　　計",99,row + rowHeight*5 - 4);
		g2.drawString(seikyu,475 - wSeikyu.width,row + rowHeight*5 - 4);
		g2.setStroke(stroke2); //-------------------------線の種類を設定
		g2.drawLine(9, row + rowHeight*5, 481, row + rowHeight*5);
		g2.drawLine(284, 239, 284, row + rowHeight);
		g2.drawLine(329, 239, 329, row + rowHeight);
		g2.drawLine(357, 239, 357, row + rowHeight);
		g2.drawLine(408, 239, 408, row + rowHeight*5);
		g2.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 10));
		for (int LineNo = 0;LineNo < proviso.length;LineNo++){
			g2.drawString(proviso[LineNo], 10,675 + 17 + 12*LineNo);
		}
		for (int LineNo = 0;LineNo < printData2.length;LineNo++){
			row = 255 + 17 + rowHeight*LineNo;
			ListDataText(g2,LineNo,row,rowHeight);
		}
    }
    public void ListPaintData2(Graphics2D g2,BasicStroke stroke1,BasicStroke stroke2){
    	g2.setStroke(stroke2); //線の種類を設定
    	g2.drawRect(9, 239, 472, 491);
    	g2.drawLine(9, 255, 481, 255);
    	koumoku(g2,stroke1,255);
    	g2.setStroke(stroke1); //線の種類を設定
    	for (int LineNo1 = 1,row1 = 0,rowHeight1 = 17;LineNo1 <= 27;LineNo1++){
    	   row1 = 255 + rowHeight1*LineNo1;
    	   g2.drawLine(9, row1, 481, row1);
    	}
    	int endNo = printData2.length - 1;
    	for (int LineNo2 = 0,row2 = 0,rowHeight2 = 17;LineNo2 <= endNo;LineNo2++){
    	   row2 = 255 + 19 + rowHeight2*LineNo2;
			ListDataText(g2,LineNo2,row2,rowHeight2);
    	}
		g2.setStroke(stroke2); //-------------------------線の種類を設定
    	g2.drawLine(284, 239, 284, 731);
    	g2.drawLine(329, 239, 329, 731);
    	g2.drawLine(357, 239, 357, 731);
    	g2.drawLine(408, 239, 408, 731);
    }
    public void ListPaintData3(Graphics2D g2,BasicStroke stroke1,BasicStroke stroke2,int pageCount,int pageCounter){
    	int row = 0;
    	int rowHeight =17;
    	g2.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 10));
		FontMetrics fm = g2.getFontMetrics();
		String discount = printData1[34].toString();
		String totalPrice = printData1[35].toString();
		String tax = printData1[36].toString();
		String seikyu = printData1[37].toString();
    	int startNo = 28 + 42*(pageCounter -2);
    	int endNo = printData2.length;
    	g2.setStroke(stroke2); //線の種類を設定
    	g2.drawRect(9, 1, 472, 731);
    	g2.drawLine(9, 17, 481, 17);
    	koumoku(g2,stroke1,rowHeight);
    	g2.setStroke(stroke1); //線の種類を設定
    	for (int LineNo1 = 1;LineNo1 <= 34;LineNo1++){
    	   row = rowHeight*LineNo1;
    	   g2.drawLine(9, row, 481, row);
    	}
    	g2.setStroke(stroke2); //線の種類を設定
    	g2.drawLine(9, row + rowHeight, 481, row + rowHeight);
    	g2.drawLine(9, row + rowHeight*2, 481, row + rowHeight*2);
		if(printTitle.replaceAll("　", "").equals("納品書")){
			g2.drawString("値　引　き",99,row + rowHeight*2 - 4);
			Rectangle wDiscount = fm.getStringBounds(discount, g2).getBounds();
			g2.drawString(discount,475 - wDiscount.width,row + rowHeight*2 - 4);
		}
    	g2.drawString("小　　　計",99,row + rowHeight*3 - 4);
    	Rectangle wTotalPrice = fm.getStringBounds(totalPrice, g2).getBounds();
		g2.drawString(totalPrice,475 - wTotalPrice.width,row + rowHeight*3 - 4);
    	g2.drawLine(9, row + rowHeight*3, 481, row + rowHeight*3);
    	Rectangle wTax = fm.getStringBounds(tax, g2).getBounds();
		switch (type) {
		case 1:

			break;
		case 4:

			break;
		case 5:
			g2.drawString("（うち消費税）",94,row + rowHeight*4 - 4);
			g2.drawString("（" + tax + "）",465 - wTax.width,row + rowHeight*4 - 4);
			break;
		default:
			g2.drawString("消　費　税（10％）",99,row + rowHeight*4 - 4);
			g2.drawString(tax,475 - wTax.width,row + rowHeight*4 - 4);
			break;
		}
    	g2.drawLine(9, row + rowHeight*4, 481, row + rowHeight*4);
    	g2.drawString("合　　　計",99,row + rowHeight*5 - 4);
    	Rectangle wSeikyu = fm.getStringBounds(seikyu, g2).getBounds();
		g2.drawString(seikyu,475 - wSeikyu.width,row + rowHeight*5 - 4);
    	g2.drawLine(9, row + rowHeight*5, 481, row + rowHeight*5);
		g2.setStroke(stroke2); //-------------------------線の種類を設定
    	g2.drawLine(284, 1, 284, row + rowHeight);
    	g2.drawLine(329, 1, 329, row + rowHeight);
    	g2.drawLine(357, 1, 357, row + rowHeight);
    	g2.drawLine(408, 1, 408, row + rowHeight*5);
		g2.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 10));
		String[] proviso = printData1[38].toString().split("↑");
		for (int LineNo2 = 0;LineNo2 < proviso.length;LineNo2++){
			g2.drawString(proviso[LineNo2], 10,675 + 12*LineNo2);
		}
    	for(int LineNo3 = startNo,i = 1;LineNo3 < endNo;LineNo3++){
    		++i;
    	   row = 2 + rowHeight*i;
			ListDataText(g2,LineNo3,row,rowHeight);
    	}
    }
    public void ListPaintData4(Graphics g,BasicStroke stroke1,BasicStroke stroke2,int pageCount,int pageCounter){
    	final int rowHeight =17;
    	int startNo = 28 + 42*(pageCounter -2);
    	int endNo = startNo + 42;
    	if(endNo > printData2.length){
    		endNo = printData2.length;
    	}
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 10));
    	g2.setStroke(stroke2); //線の種類を設定
    	g.drawRect(9, 1, 472, 731);
    	g.drawLine(9, 17, 481, 17);
    	koumoku(g2,stroke1,rowHeight);
    	g2.setStroke(stroke1); //線の種類を設定
    	for (int LineNo1 = 1,rowHeight1 = 17;LineNo1 <= 42;LineNo1++){
    	   int row1 = rowHeight1*LineNo1;
    	   g.drawLine(9, row1, 481, row1);
    	}
    	for(int LineNo2 = startNo,i = 1,rowHeight2 = 17;LineNo2 < endNo;LineNo2++){
    		++i;
    	   int row2 = 2 + rowHeight2*i;
			ListDataText(g2,LineNo2,row2,rowHeight2);
    	}
    	g2.setStroke(stroke2); //線の種類を設定
    	g.drawLine(284, 1, 284, 731);
    	g.drawLine(329, 1, 329, 731);
    	g.drawLine(357, 1, 357, 731);
    	g.drawLine(408, 1, 408, 731);
    }
    public void ListDataText(Graphics g2,int LineNo,int row,int rowHeight){
		g2.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 10));
		DecimalFormat df2 = new DecimalFormat("#,###.##");
		df2.setRoundingMode(RoundingMode.HALF_UP);
		g2.drawString(printData2[LineNo][0].toString(),12, row - 5);
		//-----------------------------------------------------------------------------------------------------------------
		String numText1;
		if (!printData2[LineNo][1].equals("")){
			double num = Double.parseDouble(printData2[LineNo][1].toString());
			numText1 = df2.format(new BigDecimal(num));
		}else{
			numText1 = "";
		}
		FontMetrics fm = g2.getFontMetrics();
		Rectangle rectText1 = fm.getStringBounds(numText1, g2).getBounds();
		g2.drawString(numText1,325 - rectText1.width, row - 5);
		//-----------------------------------------------------------------------------------------------------------------
		Rectangle rectText2 = fm.getStringBounds(printData2[LineNo][2].toString(), g2).getBounds();
		g2.drawString(printData2[LineNo][2].toString(),355 - rectText2.width, row - 5);
		//-----------------------------------------------------------------------------------------------------------------
		String numText3;
		if (!printData2[LineNo][3].equals("")){
			double num = Double.parseDouble(printData2[LineNo][3].toString());
			numText3 = df2.format(new BigDecimal(num));
		}else{
			numText3 = "";
		}
		Rectangle rectText3 = fm.getStringBounds(numText3, g2).getBounds();
		g2.drawString(numText3,405 - rectText3.width, row - 5);
		//-----------------------------------------------------------------------------------------------------------------
		String numText4;
		if (!printData2[LineNo][4].equals("")){
			double num = Double.parseDouble(printData2[LineNo][4].toString());
			numText4 = df2.format(new BigDecimal(num));
		}else{
			numText4 = "";
		}
		Rectangle rectText4 = fm.getStringBounds(numText4, g2).getBounds();
		g2.drawString(numText4,475 - rectText4.width, row - 5);
    }

    /** 印刷ボタンの処理 */
    private void btnPrintActionPerformed(ActionEvent evt) {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);		//プリンタ一覧の取得
        if(services.length == 0){
            return;		//プリンタがない場合の処理
        }
        PrintService defaultService =  PrintServiceLookup.lookupDefaultPrintService();	//現在のプリンタの取得
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;							//印刷するデータの種類を指定
        float leftMargin = 20;
        float rightMargin = 20;
        float topMargin = 20;
        float bottomMargin = 6;
        MediaSize mediaSize = MediaSize.ISO.A4;
        float mediaWidth = mediaSize.getX(Size2DSyntax.MM);
        float mediaHight = mediaSize.getY(Size2DSyntax.MM);
        PrintRequestAttributeSet pras =  new HashPrintRequestAttributeSet();                  //印刷書式の指定
        pras.add(new JobName("印刷", null));
        pras.add(MediaSizeName.ISO_A4);
        pras.add(Chromaticity.COLOR);
        pras.add(OrientationRequested.PORTRAIT);
        pras.add(new MediaPrintableArea(leftMargin,topMargin,(mediaWidth - leftMargin - rightMargin),(mediaHight - topMargin -bottomMargin),Size2DSyntax.MM));
        //印刷ダイアログの表示
        PrintService ps = ServiceUI.printDialog(null, 50, 50,  services, defaultService, flavor, pras);
        if(ps == null) return;    //キャンセルされた場合
        DocPrintJob job = ps.createPrintJob();      //印刷実行
		pageCount = page(printData2);
        Printable print2 = new SamplePrint(pageCount);
        Doc doc = new SimpleDoc(print2, flavor, null);
        try{
        	job.print(doc, pras);
        } catch (PrintException e){
        	e.printStackTrace();
        }
    }
    public void koumoku(Graphics2D g2,BasicStroke stroke,int koumokuPointY){
    	g2.setStroke(stroke); //線の種類を設定
    	g2.setFont(new Font("ＭＳ 明朝", Font.PLAIN, 10));
    	g2.drawString("品　　　　　名",110,koumokuPointY - 4);
    	g2.drawString("数　量",292,koumokuPointY - 4);
    	g2.drawString("単位",333,koumokuPointY - 4);
    	g2.drawString("単　価",369,koumokuPointY - 4);
    	g2.drawString("金　　　額",420,koumokuPointY - 4);
    }
    public int page(Object[][] data){
			dataCount = printData2.length;
			if(dataCount <= 21){
				pageCount = 1;
				//System.out.println(pageCount);
			}else if(dataCount <= 28){
				pageCount = 2;
			}else if(dataCount >28){
				dataCount = dataCount - 28;
				pageQuotient = dataCount / 42;
				pageRemainder = dataCount % 42;
				if(pageRemainder <= 34){
					pageCount = pageQuotient + 1;
				}else if ((34 < pageRemainder) && (pageRemainder <= 42)){
					pageCount = pageQuotient + 2;
				}else{
					pageCount = pageQuotient + 1;
				}
				pageCount += 1;
			}
			return pageCount;
    }
    //-------------------------------------クラスファイル----------------------------------------------------------------
	class MyCanvas extends JPanel {
		private static final long serialVersionUID = 1L;
		private int pageCount;
		BasicStroke stroke1 = new BasicStroke(0.3f);
		BasicStroke stroke2 = new BasicStroke(0.9f);
		public MyCanvas() {
			this.setBackground(Color.white);
		}
		public Dimension getPreferredSize() {
			return new Dimension(600, 600);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setBackground(Color.white);
			pagePaint(pageCounter,g2d);
		}
		public void nextpage(int pageCounter) {
			Graphics2D g2d = (Graphics2D) this.getGraphics().create();
			g2d.setBackground(Color.white);
			btnPre.setEnabled(true);
			pagePaint(pageCounter,g2d);
		}
		public void pagePaint(int pageCounter,Graphics2D g2d){
			pageCount = page(printData2);
			if(pageCounter == 1){
				g2d.clearRect(0,0, getWidth(), getHeight());
				paintData(g2d);//---------------------------------------------
				if(pageCount == 1){
					ListPaintData(g2d,stroke1,stroke2);//-----------------------
				}else if(pageCount == 2){
					ListPaintData2(g2d,stroke1,stroke2);//-----------------------
					btnNext.setEnabled(true);
					btnPre.setEnabled(false);
				}else{
					ListPaintData2(g2d,stroke1,stroke2);//-----------------------
					btnNext.setEnabled(true);
					btnPre.setEnabled(false);
				}
			}else if(pageCounter == pageCount){
				g2d.clearRect(0,0, getWidth(), getHeight());
				ListPaintData3(g2d,stroke1,stroke2,pageCount,pageCounter);//-----------------------
				btnNext.setEnabled(false);
			}else if((pageCounter < pageCount) && (pageCounter != 0)){
				btnNext.setEnabled(true);
				g2d.clearRect(0,0, getWidth(), getHeight());
				ListPaintData4(g2d,stroke1,stroke2,pageCount,pageCounter);//-----------------------
			}else if((1 < pageCounter) && (pageCounter < pageCount)){
				btnNext.setEnabled(true);
				g2d.clearRect(0,0, getWidth(), getHeight());
				ListPaintData4(g2d,stroke1,stroke2,pageCount,pageCounter);//-----------------------
			}else if(pageCounter > pageCount){
			}else if(pageCounter < 1){
				btnPre.setEnabled(false);
			}else if(pageCounter == 0){
				btnPre.setEnabled(false);
			}
			g2d.dispose();
		}
	}
 /** 印刷データ */
	class SamplePrint implements Printable{
		BasicStroke stroke1 = new BasicStroke(0.3f);
		BasicStroke stroke2 = new BasicStroke(0.9f);
		int pageCount;
		SamplePrint(int pageCount){
			this.pageCount = pageCount;
		}
		public int print(Graphics g, PageFormat pf, int pageIndex){
			pageIndex = pageIndex + 1;
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			if(pageIndex > this.pageCount) return Printable.NO_SUCH_PAGE;
			double x = pf.getImageableX();
			double y = pf.getImageableY();
			g2d.translate(x, y);
			if(this.pageCount == 1){
				paintData(g2d);
				ListPaintData(g2d,stroke1,stroke2);
			}else if(this.pageCount == 2){
				if(pageIndex == 1){
					paintData(g2d);
					ListPaintData2(g2d,stroke1,stroke2);
				}else{
					ListPaintData3(g2d,stroke1,stroke2,this.pageCount,pageIndex);
				}
			}else if(this.pageCount >= 3){
				if(pageIndex == 1){
					paintData(g2d);
					ListPaintData2(g2d,stroke1,stroke2);
				}else if(pageIndex == this.pageCount){
					ListPaintData3(g2d,stroke1,stroke2,this.pageCount,pageIndex);
				}else{
					ListPaintData4(g2d,stroke1,stroke2,this.pageCount,pageIndex);
				}
			}
			return Printable.PAGE_EXISTS;
		}
	}
}