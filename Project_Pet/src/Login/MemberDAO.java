package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;
	private boolean check;
	
	//회원정보
	public ArrayList<MemberVo> info(MemberVo p){
		ArrayList<MemberVo> infoList = new ArrayList<MemberVo>();
		try {
			String sql = "select * from login where id='"+p.getId()+"'";
			ps=con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberVo vo = new MemberVo();
				vo.setId(rs.getString(1));
				vo.setPassword(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPetName(rs.getString(4));
				vo.setPetType(rs.getString(5));
				vo.setBirthday(rs.getString(6));
				
				infoList.add(vo);
			}
			
		}catch(Exception e3) {
			System.out.println(e3.getMessage());
		} return infoList;
	}
	
	//로그인
	public boolean list(MemberVo p) {
		try {
			connDB();
			
			String query = "SELECT * FROM login WHERE id='" + p.getId()
			+ "' AND password='" + p.getPassword() + "'";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			}else {
				return true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	//회원가입-정보 입력
	public boolean signUp(MemberVo p) {
		try {
			connDB();
			
			String query = "INSERT INTO LOGIN (id,password,name,petname,pettype,birthday)" +"VALUES('"+p.getId()+"','"+p.getPassword()+"','"
					+p.getName()+"','"+p.getPetName()+"','"+p.getPetType()+"','"+p.getBirthday()+"')";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			System.out.println("rs.getRow() : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			}else {
				return true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//중복확인
	public boolean checkId(String id) {
		try {
			connDB();
			String sql = "select * from login where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			check = rs.next();
			
		}catch(Exception e) {
			System.out.println("실패");
		} finally {
			dbClose();
		}
		return check;
	}
	
	
	//회원 탈퇴
	public boolean leave(MemberVo p) {
		try {
			connDB();
			String sql = "delete from login where id = '"+MemberLogin.id_info+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			dbClose();
			
			connDB();
			String sql2 = "delete from diary where diary_id = '"+MemberLogin.id_info+"'";
			ps = con.prepareStatement(sql2);
			rs = ps.executeQuery();
			dbClose();
			
		} catch(Exception e4) {
			e4.printStackTrace();
		} return false;
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
