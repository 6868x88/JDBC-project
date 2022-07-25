package Login;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Diary.DiaryList;

public class MemberLogin extends JFrame implements ActionListener {

	MemberDAO dao;
	private JPanel contentPane;
	private JTextField idField;
	private JButton loginB;
	JPasswordField pwField;
	public static String id_info, name_info, petname_info, pettype_info, birthday_info;

	public MemberLogin() {
		ImageIcon img = new ImageIcon("./img/�α���.png");
		setBounds(700, 100, 415, 290);
		setTitle("�α���");
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		dao = new MemberDAO();
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(135, 110, 244, 37);
		contentPane.add(idField);
		
		pwField = new JPasswordField();
		pwField.setBounds(135, 157, 244, 37);
		contentPane.add(pwField);
		pwField.setColumns(10);
		pwField.setEchoChar('*');
		
		//�α��� ��ư
		loginB = new JButton("     ");
		loginB.setBounds(40, 204, 312, 37);
		contentPane.add(loginB);
		loginB.addActionListener(this);
		loginB.setBorderPainted(false);
		loginB.setContentAreaFilled(false);
		loginB.setFocusPainted(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == loginB) {
			
			MemberVo vo = new MemberVo(idField.getText(), pwField.getText());
			boolean b = dao.list(vo);
			
			if(b == true) {
				dispose();
				SelectMenu frame = new SelectMenu();
				frame.setVisible(true);
				
				//ȸ������ ����
				id_info = idField.getText();
				
				for(MemberVo info:dao.info(vo)) {
					String[] data = {info.getId(),info.getPassword(), info.getName(), info.getPetName(),info.getPetType(),info.getBirthday()};
					name_info = data[2];
					petname_info = data[3];
					pettype_info = data[4];
					birthday_info = data[5];
				}
				System.out.println(name_info);
				System.out.println(petname_info);
				System.out.println(pettype_info);
				System.out.println(birthday_info);
				
				
				//������ ��� ���� ��ư Ȱ��ȭ (ó�� �α��� �ÿ���)
				LocalDate now = LocalDate.now();
				int month = now.getMonthValue();
				int day = now.getDayOfMonth();
				String birth = month+"/"+day;
				
				if(birthday_info.equals(birth)) {
					frame.birthdayB.setVisible(true);
				}else {
				}
				
			} else {
				new LoginCheck();
			}
		}
	}

}


class LoginCheck{
	JFrame panel;
	LoginCheck(){
		ImageIcon falseimg = new ImageIcon("./img/�α��ν���.png");
		JButton falseB = new JButton(falseimg);
		falseB.setBounds(0, 0, 320, 150);
		falseB.setBorderPainted(false);
		falseB.setContentAreaFilled(false);
		panel = new JFrame();
		panel.setBounds(700, 100, 320, 150);
		panel.add(falseB);
		panel.setVisible(true);
		
	}
}