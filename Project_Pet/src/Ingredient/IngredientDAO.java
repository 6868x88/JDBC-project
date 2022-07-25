package Ingredient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class IngredientDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;
	
	//DB연결
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
	
	//재료 검색 리스트
	public ArrayList<IngredientVo> search(String ingreName){
		ArrayList<IngredientVo> list = new ArrayList<IngredientVo>();
		try {
			connDB();
			String sql = "select * from check_ingre "+"where ingredient like '%'||?||'%'";
			ps = con.prepareStatement(sql);
			ps.setString(1, ingreName);
			rs = ps.executeQuery();
			while(rs.next()) {
				IngredientVo vo = new IngredientVo();
				vo.setIngredinet(rs.getString(1));
				vo.setPet(rs.getString(2));
				vo.setEffect(rs.getString(3));
				vo.setCarefulpet(rs.getString(4));
				vo.setDetail(rs.getString(5));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		} return list;
	}

}
