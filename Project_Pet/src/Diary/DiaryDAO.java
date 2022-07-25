package Diary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import Login.MemberLogin;
import Login.MemberVo;

public class DiaryDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;
	DiaryList dList;


	//일기 등록
	public boolean writeDiary(DiaryDTO p) {
		boolean ok = false;
		try {
			connDB();
			
			String query = "INSERT INTO Diary (today,foodname,satisfaction,writing_Dairy,diary_id)" +"VALUES('"+p.getToday()+"','"
					+p.getFoodname()+"','"+p.getSatisfaction()+"','"+p.getWriting_Dairy()+"','"+p.getDiary_id()+"')";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			//			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());

			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			}else {
				return true;
			}
			
			dbClose();

		} catch(Exception e2) {
			e2.printStackTrace();
		}return ok;
	}

	//일기 삭제-날짜별 삭제
	public boolean deleteDiary(String today) {
		boolean ok = false;
		try {
			connDB();
			String sql = "delete from Diary where today = ? and diary_id = '"+MemberLogin.id_info+"'";
			ps = con.prepareStatement(sql);
			ps.setString(1, today);
			int r = ps.executeUpdate();
			if(r>0) ok= true;
			
			dbClose();

		} catch(Exception e3) {
			System.out.println(e3.getMessage());
		} return ok;
	}

	

	
	
	//일기 리스트 출력
	public ArrayList<DiaryDTO> gdiaryList(MemberVo p){
		ArrayList<DiaryDTO> list = new ArrayList<DiaryDTO>();
		try {
			connDB();
			String sql = "select * from Diary where diary_id = '"+MemberLogin.id_info+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); //실행
			
			while(rs.next()) {
				DiaryDTO dto = new DiaryDTO();
				dto.setToday(rs.getString(1));
				dto.setFoodname(rs.getString(2));
				dto.setSatisfaction(rs.getString(3));
				dto.setWriting_Dairy(rs.getString(4));
				dto.setDiary_id(rs.getString(5));
				list.add(dto);
			}
			
			dbClose();
		}catch(Exception e4) {
			System.out.println(e4.getMessage());
		}return list;
	}
	
	
	
	public DiaryDAO() {}
	
	
	
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(stmt !=null) stmt.close();
			if(ps != null) ps.close();
		} catch(Exception e) {
			System.out.println("db close fail");
		}
	}
	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.\n");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
