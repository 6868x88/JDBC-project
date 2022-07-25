package Login;
public class MemberVo {
	private String id;
	private String password;
	private String name;
	private String petName;
	private String petType;
	private String birthday;




	public MemberVo() {

	}

	public MemberVo(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public MemberVo(String id, String password, String name, String petName, String petType, String birthday) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.petName = petName;
		this.petType = petType;
		this.setBirthday(birthday);
	}
	
	
	
	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	
	public String getPetName() {
		return petName;
	}

	public String getPetType() {
		return petType;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
