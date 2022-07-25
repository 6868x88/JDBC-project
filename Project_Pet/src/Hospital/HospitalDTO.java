package Hospital;

public class HospitalDTO {
	private String city;
	private String name;
	private String tel;
	private String address;
	private String state;
	
	
	public HospitalDTO() {}
	
	public HospitalDTO(String city, String name, String tel, String address, String state ) {
		this.city = city;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.state = state;
	}
	
	
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
