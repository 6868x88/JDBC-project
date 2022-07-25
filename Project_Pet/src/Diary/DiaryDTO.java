package Diary;

import Login.MemberLogin;

public class DiaryDTO {
	private String today;
	private String foodname;
	private String satisfaction;
	private String writing_Dairy;
	private String diary_id = MemberLogin.id_info;
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public String getWriting_Dairy() {
		return writing_Dairy;
	}
	public void setWriting_Dairy(String writing_Dairy) {
		this.writing_Dairy = writing_Dairy;
	}
	
	public DiaryDTO(String today, String foodname, String satisfaction, String writing_Dairy){
		this.today = today;
		this.foodname = foodname;
		this.satisfaction = satisfaction;
		this.writing_Dairy = writing_Dairy;
	}
	public DiaryDTO() {}
	
	
	public String getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(String diary_id) {
		this.diary_id = diary_id ;
	};
	
	
	
	

}
