package Hospital;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HospitalJson {

	public static void main(String[] args) throws IOException, ParseException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\work\\Project_Pet\\img\\petHospital.json"), "UTF-8"));
		HospitalDAO dao = new HospitalDAO();
		
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(reader);
			JSONArray jsonArr = (JSONArray)obj;
			
			if(jsonArr.size()>0) {
				for(int i=1800; i<jsonArr.size(); i++) {
					JSONObject jsonObj =(JSONObject)jsonArr.get(i);
					
					
					System.out.println("�ñ���: "+(String)jsonObj.get("SIGUN_NM"));
					System.out.println("����ڸ�: "+(String)jsonObj.get("BIZPLC_NM"));
					System.out.println("�������ü���ȭ��ȣ: "+(String)jsonObj.get("LOCPLC_FACLT_TELNO_DTLS"));
					System.out.println("���������θ��ּ�: "+(String)jsonObj.get("REFINE_ROADNM_ADDR"));
					System.out.println("�������¸�: "+(String)jsonObj.get("BSN_STATE_NM"));

					HospitalDTO dto = new HospitalDTO((String)jsonObj.get("SIGUN_NM"),(String)jsonObj.get("BIZPLC_NM"),(String)jsonObj.get("LOCPLC_FACLT_TELNO_DTLS"),(String)jsonObj.get("REFINE_ROADNM_ADDR"),
							(String)jsonObj.get("BSN_STATE_NM"));
					dao.saveDB(dto);
					
				}
			}
			
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	
	}

}
