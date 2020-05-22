package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bean.TrainManage;
import util.JDBCConnect;

public class TrainManageDAO {
	
	//���복��
	public static int insert(TrainManage train) throws Exception {

		String sql = "INSERT INTO TrainManage (trainid,start,end,time,yzprice,rzprice,ywprice,rwprice,root) " +
				"VALUES ('" + train.getTrainid() +"','"+ train.getStart() +"','"+ train.getEnd() +
				"','"+ train.getTime() +"','"+ train.getYzprice() +"','"+ train.getRzprice() +
				"','"+ train.getYwprice() +"','"+ train.getRwprice() +"','"+ train.getRoot()  +"');";
		System.out.println(sql);
		return JDBCConnect.executeUpdate(sql);
		
		/**
		 * ���Ǳ���  No value specified for parameter 5
		 * �ܶ�ԭ���� insert into train (?,?,?) values (?,?,?) ǰ�治Ӧ�����ʺ� 
		 * ���ҵĻ����ǲ�������ʹ��executeUpdate(sql,?,?,?...)�����Ǵ���
		 * 
		 * String sql = "INSERT INTO TrainManage (trainid,start,end,time,yzprice,rzprice,ywprice,rwprice,root) VALUES (?,?,?,?,?,?,?,?,?);";
		 * System.out.println(sql);
		 * return JDBCConnect.executeUpdate(sql, train.getTrainid(), train.getStart(), train.getEnd(),
		 *		train.getTime(), train.getYzprice(), train.getRzprice(), train.getYwprice(), 
		 *		train.getRwprice(), train.getRoot());
		 */
	}
	
	
	//���³���
	public static int update(TrainManage train) throws Exception {

		/**
		 * String sql = "UPDATE TrainManage SET start = ?, end = ? WHERE trainid = ? ";
		 * return JDBCConnect.executeUpdate(sql, train.getStart(), train.getEnd(), train.getTrainid());
		 */
		/*
		String sql = "UPDATE TrainManage SET start = '"+ train.getStart() +
				"', end = '" + train.getEnd() + "' WHERE trainid = '" +
				train.getTrainid() +"';";
		System.out.println(sql);
		return JDBCConnect.executeUpdate(sql);
		*/
		String sql = "UPDATE TrainManage SET start = ?, end = ? WHERE trainid = ? ";
		return JDBCConnect.executeUpdate(sql, train.getStart(), train.getEnd(), train.getTrainid());
	}


	//ɾ������
	public static int delete(String id) throws Exception {

		String sql = "DELETE FROM TrainManage WHERE trainid = ? ";
		return JDBCConnect.executeUpdate(sql, id);
	}
	
	//���Ҽ�¼ ĳ����
	public static TrainManage find(String id) throws Exception {
		
		String sql = "SELECT * FROM TrainManage WHERE trainid = ? ";
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;

		try {
			//�������ݿ�ִ��SQL���
			conn = JDBCConnect.getConnection(); //����Ĭ�����ݿ�
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, id);
			rs = preStmt.executeQuery();
			//��ȡ��ѯ���
			if (rs.next()) {
				TrainManage train = new TrainManage();
				train.setTrainid(rs.getString("trainid"));
				train.setStart(rs.getString("start"));
				train.setEnd(rs.getString("end"));
				train.setTime(rs.getString("time"));
				train.setYzprice(rs.getFloat("yzprice"));
				train.setYwprice(rs.getFloat("ywprice"));
				train.setRzprice(rs.getFloat("rzprice"));
				train.setRwprice(rs.getFloat("rwprice"));
				train.setRoot(rs.getString("root"));
				return train;
			} else {
				return null;
			}

		} finally { //���ιر� ��¼�� ���� ���Ӷ���
			if (rs != null)
				rs.close();
			if (preStmt != null)
				preStmt.close();
			if (conn != null)
				conn.close();
		}
	}
	
	//���Ҽ�¼ ĳ����
	public static List<TrainManage> findStartEnd(String start,String end) throws Exception {
		List<TrainManage> list = new ArrayList<TrainManage>();
		String sql = null; 
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		//�ж�SQL���
		if(start==""&&end=="") {
			sql = "SELECT * FROM TrainManage;";
		} else if(end=="") {
			sql = "SELECT * FROM TrainManage WHERE start = '"+ start + "';";
		} else if(start=="") {
			sql = "SELECT * FROM TrainManage WHERE end = '"+ end + "';";
		} else {
			sql = "SELECT * FROM TrainManage WHERE start = '" 
					+ start + "' and end = '"+ end +"';"; 
		}
		//ִ��
		try {
			//�������ݿ�ִ��SQL���
			conn = JDBCConnect.getConnection(); //����Ĭ�����ݿ�
			statement = conn.createStatement();
			System.out.println(start+" "+end);
			System.out.println(sql);
			rs = statement.executeQuery(sql);
			
			//��ȡ��ѯ���
			while(rs.next()) {
				TrainManage train = new TrainManage();
				train.setTrainid(rs.getString("trainid"));
				train.setStart(rs.getString("start"));
				train.setEnd(rs.getString("end"));
				train.setTime(rs.getString("time"));
				train.setYzprice(rs.getFloat("yzprice"));
				train.setYwprice(rs.getFloat("ywprice"));
				train.setRzprice(rs.getFloat("rzprice"));
				train.setRwprice(rs.getFloat("rwprice"));
				train.setRoot(rs.getString("root"));
				list.add(train);
			}

		} catch (Exception e) {
			System.out.println("����"+e.getMessage());  
		}
		finally { //���ιر� ��¼�� ���� ���Ӷ���
			if (rs != null)
				rs.close();
			if (statement != null)
				statement.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}
	
	//��ѯ���г�����Ϣ
	public static List<TrainManage> listStudents() throws Exception {

		List<TrainManage> list = new ArrayList<TrainManage>();
		String sql = "SELECT * FROM TrainManage";
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnect.getConnection();
			preStmt = conn.prepareStatement(sql);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				//�������ݿ��б���� ���򱨴�java.sql.SQLException: Column 'id' not found.
				TrainManage train = new TrainManage();
				train.setTrainid(rs.getString("trainid"));      
				train.setStart(rs.getString("start"));
				train.setEnd(rs.getString("end"));
				train.setTime(rs.getString("time"));
				train.setYzprice(rs.getFloat("yzprice"));
				train.setYwprice(rs.getFloat("ywprice"));
				train.setRzprice(rs.getFloat("rzprice"));
				train.setRwprice(rs.getFloat("rwprice"));
				train.setRoot(rs.getString("root"));
				list.add(train);
			}
			
		} finally {
			if (rs != null)
				rs.close();
			if (preStmt != null)
				preStmt.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}
	
}
