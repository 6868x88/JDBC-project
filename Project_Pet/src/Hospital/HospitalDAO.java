package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Diary.DiaryDTO;

public class HospitalDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;
//	private boolean check;


	//DB에 오픈 API 파싱한 것 저장
	public boolean saveDB(HospitalDTO p) {
		boolean ok= false;

		try {
			connDB();
			String sql = "INSERT INTO pet_hospital (city,name,tel,address,state)" + "VALUES('"+p.getCity()+"','"+p.getName()+"','"
					+p.getTel()+"','"+p.getAddress()+"','"+p.getState()+"')";
			System.out.println("SQL : " + sql);
			rs =stmt.executeQuery(sql);

		}catch(Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		return ok;
	}

	// 병원 검색 리스트
	public ArrayList<HospitalDTO> search(String city){
		ArrayList<HospitalDTO> list = new ArrayList<HospitalDTO>();
		try {
			connDB();
			String sql = "select * from pet_hospital "+"where address like '%'||?||'%'";
			ps = con.prepareStatement(sql);
			ps.setString(1, city);
			rs = ps.executeQuery();
			while(rs.next()) {
				HospitalDTO vo = new HospitalDTO();
				vo.setCity(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setTel(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setState(rs.getString(5));
				
				list.add(vo);
			}
			dbClose();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		} return list;
	}
	
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
