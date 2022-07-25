package Recipe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class RecipesDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;
	
	
	//������ �˻� ����Ʈ
	public ArrayList<RecipesVo> search(String foodName){
		ArrayList<RecipesVo> list = new ArrayList<RecipesVo>();
		try {
			connDB();
			String sql = "select * from Recipes "+"where foodname like '%'||?||'%'";
			ps = con.prepareStatement(sql);
			ps.setString(1, foodName);
			rs = ps.executeQuery();
			while(rs.next()) {
				RecipesVo vo = new RecipesVo();
				vo.setFoodName(rs.getString(1));
				vo.setPet(rs.getString(2));
				vo.setRecipeImg(rs.getString(3));
				vo.setIntro(rs.getString(4));
				vo.setHowto(rs.getString(5));
				vo.setLike_recipe(rs.getString(6));
				list.add(vo);
			}
			dbClose();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		} return list;
	}
	
	//���� ������ ���ƿ� �� �ֱ�
	public boolean likeRecipe(RecipesVo p) {
		boolean ok = false;
		try {
			
			connDB();
			String sql = "update recipes set like_recipe = '"+p.getLike_recipe()+"' where foodname ='"+SearchRecipe.foodname_info+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.getRow() == 0) {
			}else {
				return true;
			}
			
			dbClose();
		} catch(Exception e3) {
			e3.printStackTrace();
		} return ok;
	}
	
	//����ũ ������
	public ArrayList<RecipesVo> cake(){
		ArrayList<RecipesVo> list = new ArrayList<RecipesVo>();
		try {
			connDB();
			String sql = "select * from recipes where foodname = '���� ����ũ'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				RecipesVo vo = new RecipesVo();
				vo.setFoodName(rs.getString(1));
				vo.setPet(rs.getString(2));
				vo.setRecipeImg(rs.getString(3));
				vo.setIntro(rs.getString(4));
				vo.setHowto(rs.getString(5));
				vo.setLike_recipe(rs.getString(6));
				list.add(vo);

			}
			dbClose();
		}catch(Exception e2) {
			System.out.println(e2.getMessage());
		} return list;
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
	
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(stmt !=null) stmt.close();
			if(ps != null) ps.close();
		} catch(Exception e) {
			System.out.println("db close fail");
		}
	}
	
}
