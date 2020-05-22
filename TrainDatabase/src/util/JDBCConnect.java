package util;

import java.sql.*;
import com.mysql.jdbc.Driver;

public class JDBCConnect {
	
	//��ȡĬ�����ݿ�����
	public static Connection getConnection() throws SQLException {
		return getConnection("ManageTrain", "root", "123456"); //���ݿ��� Ĭ���û� ����
	}
	
	//�������ݿ�   ����:���ݿ��� root��¼�� ����
	public static Connection getConnection(String dbName, String userName,
			String password) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/" + dbName 
				+ "?useUnicode=true&characterEncoding=utf-8";
		
		//����MySQL"com.mysql.jdbc.Driver"
		DriverManager.registerDriver(new Driver());
		return DriverManager.getConnection(url, userName, password);
	}

	//���� PreparedStatement ���� 
	public static void setParams(PreparedStatement preStmt, Object... params)
			throws SQLException {

		if (params == null || params.length == 0)
			return;

		for (int i = 1; i <= params.length; i++) {
			Object param = params[i - 1];
			if (param == null) {
				preStmt.setNull(i, Types.NULL);
			} else if (param instanceof Integer) {
				preStmt.setInt(i, (Integer) param);
			} else if (param instanceof String) {
				preStmt.setString(i, (String) param);
			} else if (param instanceof Double) {
				preStmt.setDouble(i, (Double) param);
			} else if (param instanceof Long) {
				preStmt.setDouble(i, (Long) param);
			} else if (param instanceof Timestamp) {
				preStmt.setTimestamp(i, (Timestamp) param);
			} else if (param instanceof Boolean) {
				preStmt.setBoolean(i, (Boolean) param);
			} else if (param instanceof Date) {
				preStmt.setDate(i, (Date) param);
			}
		}
	}

	//ִ�� SQL������Ӱ������� �쳣����
	public static int executeUpdate(String sql) throws SQLException {
		return executeUpdate(sql, new Object[] {});
	}

	//������ִ��SQL������Ӱ������� �쳣����
	public static int executeUpdate(String sql, Object... params)
			throws SQLException {

		Connection conn = null;
		PreparedStatement preStmt = null;

		try {
			conn = getConnection();
			preStmt = conn.prepareStatement(sql);
			setParams(preStmt, params);
			return preStmt.executeUpdate(); //ִ��SQL����
		} finally {
			if (preStmt != null)
				preStmt.close();
			if (conn != null)
				conn.close();
		}
	}

}
