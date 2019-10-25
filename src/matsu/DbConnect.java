package matsu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
//import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

public class DbConnect {
	private static Statement stmt = null;
	private static Connection conn = null;

	DbConnect(){
		try {
			String USER = "";
			String PASS = "";
			//connect( USER, PASS);
			connectSQLite( USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[] tmpReText(String tableName,String fName){
		ArrayList<String> tmpArray = new ArrayList<String>();
		try {
			fName = convertToOiginal(fName);
			ResultSet rsTmp = execute( "select * from " + tableName + " WHERE F_Name = '" +  fName + "'");
			rsTmp.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] reTmp = tmpArray.toArray(new String[0]);
		return reTmp;
	}

	public String convertToOiginal(String unicode){
	    String[] codeStrs = unicode.split("\\\\u");
	    int[] codePoints = new int[codeStrs.length - 1]; // 最初が空文字なのでそれを抜かす
	    for (int i = 0; i < codePoints.length; i++) {
	        codePoints[i] = Integer.parseInt(codeStrs[i + 1], 16);
	    }
	    String encodedText = new String(codePoints, 0, codePoints.length);
	    return encodedText;
	}

	public String[] tmpReText(String tableName){
		ArrayList<String> tmpArray = new ArrayList<String>();
		try {
			ResultSet rsTmp = execute( "select * from " + tableName);
			while(rsTmp.next()){
				tmpArray.add(rsTmp.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] reTmp = tmpArray.toArray(new String[tmpArray.size()]);
		return reTmp;
	}

	public String[][] tmpC_DATA(String tableName){
		ArrayList<String[]> tmpArray = new ArrayList<String[]>();
		try {
			String[] dataList;
			ResultSet  rsTmp = execute( "select * from " + tableName);
			while(rsTmp.next()){
				String TYPE = rsTmp.getString(1);
				String C_NO = rsTmp.getString(2);
				String C_NAME = rsTmp.getString(3);
				String DIVISION = rsTmp.getString(4);
				String C_ZIPCODE = rsTmp.getString(5);
				String C_ADDRESS = rsTmp.getString(6);
				String C_TEL = rsTmp.getString(7);
				String C_FAX = rsTmp.getString(8);
				String TRADING_BANK = rsTmp.getString(9);
				String T_B_BRANCH = rsTmp.getString(10);
				String T_B_ACCOUNT = rsTmp.getString(11);
				String T_B_ACCOUNT_NUMBER = rsTmp.getString(12);
				String T_B_ACCOUNT_HOLDER = rsTmp.getString(13);
				String LICEN_NUMBER = rsTmp.getString(14);
				dataList = new String[]{TYPE,C_NO,C_NAME,DIVISION,C_ZIPCODE,C_ADDRESS,C_TEL,C_FAX,TRADING_BANK,T_B_BRANCH,T_B_ACCOUNT,T_B_ACCOUNT_NUMBER,T_B_ACCOUNT_HOLDER,LICEN_NUMBER};
				tmpArray.add(dataList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[][] reTmp = tmpArray.toArray(new String[tmpArray.size()][]);
		return reTmp;
	}
	public String[] tmpC_DATA_Where(String tableName,String kaisyaMei){
		String[] reData = null;
		try {
			//System.out.println(kaisyaMei);
			//ResultSet rs = execute( "select * from " + tableName + " WHERE " + columnName + " = \'"+ columnData + "\'");
			ResultSet  rsTmp = execute( "select * from " + tableName + " where C_NAME = '" + kaisyaMei + "'");
			int colCount = rsTmp.getMetaData().getColumnCount();
			reData = new String[colCount];
			if(rsTmp.next() != false){
				for (int ii = 0;ii < colCount; ii++){
					reData[ii] = rsTmp.getString(ii + 1);
				}
			}
			//データベースを切断
			rsTmp.close();
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return reData;
	}
	/*
	public ArrayList<String[]> tmpC_DATA_Where(String tableName,String kaisyaMei){
		ArrayList<String[]> tmpArray = new ArrayList<String[]>();
		try {
			String[] dataList;
			ResultSet  rsTmp = execute( "select * from " + tableName + " where C_NAME = '" + kaisyaMei + "'");
			while(rsTmp.next()){
				String TYPE = rsTmp.getString(1);
				String C_NO = rsTmp.getString(2);
				String C_NAME = rsTmp.getString(3);
				String DIVISION = rsTmp.getString(4);
				String C_ZIPCODE = rsTmp.getString(5);
				String C_ADDRESS = rsTmp.getString(6);
				String C_TEL = rsTmp.getString(7);
				String C_FAX = rsTmp.getString(8);
				String TRADING_BANK = rsTmp.getString(9);
				String T_B_BRANCH = rsTmp.getString(10);
				String T_B_ACCOUNT = rsTmp.getString(11);
				String T_B_ACCOUNT_NUMBER = rsTmp.getString(12);
				String T_B_ACCOUNT_HOLDER = rsTmp.getString(13);
				String LICEN_NUMBER = rsTmp.getString(14);
				System.out.println(LICEN_NUMBER);
				dataList = new String[]{TYPE,C_NO,C_NAME,DIVISION,C_ZIPCODE,C_ADDRESS,C_TEL,C_FAX,TRADING_BANK,T_B_BRANCH,T_B_ACCOUNT,T_B_ACCOUNT_NUMBER,T_B_ACCOUNT_HOLDER,LICEN_NUMBER};
				tmpArray.add(dataList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//String[] reTmp = tmpArray;
		return tmpArray;
	}
	*/

	public String[][] tmpF_DATA(String tableName){
		ArrayList<String[]> tmpArray = new ArrayList<String[]>();
		try {
			ResultSet rsTmp = execute( "select * from " + tableName);
			while(rsTmp.next()){
				String F_NO = rsTmp.getString(1);
				String F_CODE_NO = rsTmp.getString(2);
				String F_NAME = rsTmp.getString(3);
				String[] dataList = new String[]{F_NO,F_CODE_NO,F_NAME};
				tmpArray.add(dataList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[][] reTmp = tmpArray.toArray(new String[tmpArray.size()][]);
		return reTmp;
	}

	public String[][] tmpReData(String tableName,String inOut){
		ArrayList<String[]> tmpArray = new ArrayList<String[]>();
		try {
			String[] dataList;
			ResultSet rsTmp = execute( "select * from " + tableName);
			while(rsTmp.next()){
				//NumberFormat nf = NumberFormat.getInstance();
				String Item = rsTmp.getString(2);
				String Quantity_S = rsTmp.getString(3);
				String Unit = rsTmp.getString(4);
				String UnitPrice_S = rsTmp.getString(5);
				//double Price_D;
				//double UnitPrice_D;
				//double Quantity_D;
				String UnitPrice;
				String Price;
				String Quantity = null;
				//final DecimalFormat df = new DecimalFormat(",##0");
				final DecimalFormat df = new DecimalFormat(",###.##");
				df.setRoundingMode(RoundingMode.HALF_UP);
				if(!Quantity_S.equals("") && !UnitPrice_S.equals("")){
					//Quantity_D = Double.parseDouble(Quantity_S);
					//UnitPrice_D = Double.parseDouble(UnitPrice_S);
					//BigDecimal Price_D = Quantity_D * UnitPrice_D;
					BigDecimal Quantity_D = new BigDecimal(Double.parseDouble(Quantity_S));
					BigDecimal UnitPrice_D = new BigDecimal(Double.parseDouble(UnitPrice_S));
					BigDecimal Price_D = Quantity_D.multiply(UnitPrice_D);
					//BigDecimal Price_BD = new BigDecimal(Price_D);
					if(inOut.equals("IN")){
						Quantity = df.format(Quantity_D);
						//Price = nf.format(Price_BD);
						Price = df.format(Price_D);
						UnitPrice = df.format(UnitPrice_D);
					}else{
						Quantity = Quantity_S;
						UnitPrice = UnitPrice_S;
						//Price = String.valueOf(Price_BD);
						Price = String.valueOf(Price_D);
					}
				}else if(!UnitPrice_S.equals("")){
					//UnitPrice_D = Double.parseDouble(UnitPrice_S);
					BigDecimal UnitPrice_D = new BigDecimal(Double.parseDouble(UnitPrice_S));
					if(inOut.equals("IN")){
						UnitPrice = df.format(UnitPrice_D);
					}else{
						UnitPrice = UnitPrice_S;
					}
					Price = "";
				}else{
					Quantity = "";
					UnitPrice = "";
					 Price = "";
				}
				if(inOut.equals("IN")){
					dataList = new String[]{Item,Quantity,Unit,UnitPrice,Price};
				}else{
					dataList = new String[]{Item + "\t" + Quantity + "\t" + Unit + "\t" + UnitPrice};
				}
				tmpArray.add(dataList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[][] reTmp = tmpArray.toArray(new String[tmpArray.size()][]);
		return reTmp;
	}

	public String[] dBReText(String tableName, String columnName,String columnData){
		String[] reData = null;
		try {
			ResultSet rs = execute( "select * from " + tableName + " WHERE " + columnName + " = \'"+ columnData + "\'");
			int colCount = rs.getMetaData().getColumnCount();
			reData = new String[colCount];
			if(rs.next() != false){
				for (int ii = 0;ii < colCount; ii++){
					reData[ii] = rs.getString(ii + 1);
				}
			}
			//データベースを切断
			rs.close();
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return reData;
	}

	public void dBInsert(String sql_TEXT,String Table,String Column){
		try {
			Statement stmt = conn.createStatement();
			executeupdate(sql_TEXT,Table,Column);
			//データベースを切断
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}

	public ArrayList<String> combobox(String dbName,String columnName){
		ArrayList<String> comboList = new ArrayList<String>();
		String sql = "select " + columnName + " from " + dbName;
		try {
			System.out.println(columnName + "用データベース接続成功");
			ResultSet rs = execute(sql );
			while(rs.next()){
				comboList.add(rs.getString(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comboList;
	}
	/**
	 * execute
	 */
	public static ResultSet execute( String sql ) throws SQLException {
		try {
			//conn.setReadOnly(true);
			// Execute 'commit' Automatically after each SQL
			// conn.setAutoCommit(true);
			// Query Execution
			stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		}catch (Exception e) {
			// Error Message and Error Code
			System.out.print(e.toString());
			if (e instanceof SQLException) {
				System.out.println("Error Code:" + ((SQLException)e).getErrorCode());
			}
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
				conn.close();
			}
			return null;
		}
	}
	/**
	 * テーブルを削除して新しく作成
	 */
	public static void executeupdate( String sql, String Table,String Column) throws SQLException {
		String delSql = "delete from " + Table;
		//---------------SQLite用AutoInclementリセット-------------------------------------------------
		String resetSql = "delete from sqlite_sequence where name='" + Table + "'";
		//---------------derby用AutoInclementリセット-------------------------------------------------
		//String resetSql = "alter table " + Table + " alter column " + Column + " restart with 1";
		//--------------------------------------------------------------------------------------------------
		try {
			Statement stmt = conn.createStatement();
			stmt = conn.createStatement();
			stmt.executeUpdate(delSql);
			stmt.executeUpdate(resetSql);
			stmt.executeUpdate(sql);
		}catch (Exception e) {
			System.out.print(e.toString());
			if (e instanceof SQLException) {
				System.out.println("Error Code:" + ((SQLException)e).getErrorCode());
			}
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
				conn.close();
			}
		}
	}
	/**
	 * 顧客名に追加
	 */
	public String addData(Object fName) throws SQLException{
		String last_sql = "SELECT MAX (F_NO) FROM F_DATA";
		String fCodeNo = null;
		try {
			ResultSet rsMax = execute(last_sql);
			rsMax.next();
			int fNo = rsMax.getInt(1) + 1;
			fCodeNo = String.format("%1$04d", rsMax.getInt(1) + 1);
			String sql = "INSERT INTO F_DATA (F_NO,F_CODE_NO,F_NAME) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, fNo);
			ps.setString(2,"F-" + fCodeNo);
			ps.setString(3,fName.toString());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "F-" + fCodeNo;
	}
	/**
	 * 一行削除
	 */
	public void delRow(String tableName,String rowNo,int delrowNo){
		String delSql = "DELETE FROM " + tableName +  " WHERE " + rowNo + " = " + delrowNo;
		try {
			Statement stmt = conn.createStatement();
			stmt = conn.createStatement();
			stmt.executeUpdate(delSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 一行修正
	 */
	public int updateRow(String tableName,String rowNo,LinkedHashMap<String,Object> updaterowValue){
		try {
			Statement stmt = conn.createStatement();
			String updateString = "";
			int i = 0;
			for (String key : updaterowValue.keySet()){
				Object upValue = updaterowValue.get(key);
				Object upValueString = "";
				if((i != 0)){
					if(upValue instanceof java.lang.Integer){
						if(upValue.toString().equals("0")){
							upValueString = null;
						}else{
							upValueString = upValue;
						}
					}else{
						upValueString = "'"+ upValue + "'";
					}
					updateString = updateString + "," + key + "=" + upValueString;
				}
				i++;
			}
			updateString = updateString.replaceFirst(",", "");
			String updateSql = "UPDATE " + tableName + " SET " + updateString +  " WHERE " + rowNo + " = " + updaterowValue.get(rowNo);
			stmt = conn.createStatement();
			stmt.executeUpdate(updateSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@SuppressWarnings("deprecation")
	private static void connectSQLite(String user, String pass) throws SQLException,ClassNotFoundException {
		try {
			// Connection
			String driver = "org.sqlite.JDBC";
			Class.forName(driver).newInstance();
			Properties prop = new Properties();
			prop.put("create", "true");
			prop.put("user", user);
			prop.put("password", pass);
			String DBURL = "jdbc:sqlite:database.db";
			conn = DriverManager.getConnection(DBURL,prop);
		}catch (Exception e) {
			// Error Message and Error Code
			System.out.print(e.toString());
			if (e instanceof SQLException) {
				System.out.println("Error Code:" + ((SQLException)e).getErrorCode());
			}
			// Print Stack Trace
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
				conn.close();
			}
		}
	}
	//----------------------apache.derby用---------------------------------------------------------------------------------------//
	/*
	private static void connect(String user, String pass) throws SQLException,ClassNotFoundException {
		try {
			// Connection
			String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			Class.forName(driver).newInstance();
			Properties prop = new Properties();
			prop.put("create", "true");
			prop.put("user", user);
			prop.put("password", pass);
			String DBURL = "jdbc:derby:dbdir;";
			conn = DriverManager.getConnection(DBURL,prop);
		}catch (Exception e) {
			// Error Message and Error Code
			System.out.print(e.toString());
			if (e instanceof SQLException) {
				System.out.println("Error Code:" + ((SQLException)e).getErrorCode());
			}
			// Print Stack Trace
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
				conn.close();
			}
		}
	}
	*/
	//------------------------------------------------------------------------------------------------------------------------------//
	//
	// データベースのクローズ
	//
	public void disconnect() throws SQLException {
		try {
			conn.close();
		}catch (Exception e) {
			System.out.print(e.toString());
			if (e instanceof SQLException) {
				System.out.println("Error Code:" + ((SQLException)e).getErrorCode());
			}
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
				conn.close();
			}
		}
	}
}