package Login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class SignUP extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField idField, nameField, petNameField, checkField, telField;
	private JPasswordField pwField, pwCheckField;
	private JButton signUpB,checkPW,idCheck ;
	private MemberDAO dao;
	private JComboBox yearcomboBox;
	private JComboBox monthcomboBox;
	private JComboBox daycomboBox;
	JComboBox comboBox;
	String birthday;
	public SignUP() {
		dao = new MemberDAO();
		ImageIcon img = new ImageIcon("./img/회원가입.png");
		ImageIcon buttonimg = new ImageIcon("./img/확인버튼.png");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 780);
		setTitle("회원가입");
		setResizable(false);
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//아이디 입력 창
		idField = new JTextField("",10);
		idField.setFont(new Font("Dialog", Font.PLAIN, 16));
		idField.setBounds(229, 133, 215, 33);
		idField.addActionListener(this);
		contentPane.add(idField);
		idField.setColumns(10);
		
		idCheck = new JButton(buttonimg);
		idCheck.setBounds(450, 133, 83, 32);
		contentPane.add(idCheck);
		idCheck.setBorderPainted(false);
		idCheck.setContentAreaFilled(false);
		idCheck.setFocusPainted(false);
		idCheck.addActionListener(this);

		//비밀번호 입력 창
		pwField = new JPasswordField("");
		pwField.setForeground(Color.DARK_GRAY);
		pwField.setFont(new Font("Dialog", Font.PLAIN, 16));
		pwField.setColumns(10);
		pwField.setBounds(229, 178, 304, 33);
		contentPane.add(pwField);

		checkPW = new JButton(buttonimg);
		checkPW.setFont(new Font("Dialog", Font.PLAIN, 16));
		checkPW.setBounds(450, 222, 83, 32);
		contentPane.add(checkPW);
		checkPW.addActionListener(this);
		checkPW.setBorderPainted(false);
		checkPW.setContentAreaFilled(false);
		checkPW.setFocusPainted(false);

		//비밀번호 확인 창
		pwCheckField = new JPasswordField("");
		pwCheckField.setColumns(10);
		pwCheckField.setBounds(229, 222, 215, 33);
		contentPane.add(pwCheckField); 
		pwCheckField.setEchoChar('*');

		nameField = new JTextField();
		nameField.setFont(new Font("Dialog", Font.PLAIN, 16));
		nameField.setColumns(10);
		nameField.setBounds(229, 272, 304, 33);
		contentPane.add(nameField);

		//반려동물 이름 창
		petNameField = new JTextField();
		petNameField.setColumns(10);
		petNameField.setBounds(229, 451, 304, 33);
		contentPane.add(petNameField);
		petNameField.setFont(new Font("Dialog", Font.PLAIN, 16));


		signUpB = new JButton("   ");
		signUpB.setBounds(53, 687, 426, 44);
		signUpB.addActionListener(this);
		signUpB.setBorderPainted(false);
		signUpB.setContentAreaFilled(false);
		signUpB.setFocusPainted(false);
		contentPane.add(signUpB);

		checkField = new JTextField();
		checkField.setForeground(Color.RED);
		checkField.setFont(new Font("Dialog", Font.PLAIN, 16));
		checkField.setColumns(10);
		checkField.setBounds(72, 616, 420, 44);
		contentPane.add(checkField);
		
		comboBox = new JComboBox();
		comboBox.setBounds(229, 548, 83, 34);
		contentPane.add(comboBox);
		comboBox.addItem("강아지");
		comboBox.addItem("고양이");
		comboBox.addItem("토끼");
		
		TextField pettelField_1_1 = new TextField();
		pettelField_1_1.setColumns(10);
		pettelField_1_1.setBounds(339, 549, 194, 33);
		contentPane.add(pettelField_1_1);
		pettelField_1_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		telField = new JTextField();
		telField.setColumns(10);
		telField.setBounds(229, 311, 304, 33);
		contentPane.add(telField);
		telField.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		String year[] = {"2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010",
				"2009","2008","2007","2006","2005","2003","2002","2001","2000","1999"};
		yearcomboBox = new JComboBox(year);
		yearcomboBox.setBounds(229, 503, 83, 33);
		contentPane.add(yearcomboBox);
		
		
		String month[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		monthcomboBox = new JComboBox(month);
		monthcomboBox.setBounds(339, 503, 83, 33);
		contentPane.add(monthcomboBox);
		
		
		String day[] =  {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30","31"};
		daycomboBox = new JComboBox(day);
		daycomboBox.setBounds(450, 503, 83, 33);
		contentPane.add(daycomboBox);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signUpB) {
			birthday = monthcomboBox.getSelectedItem()+"/"+daycomboBox.getSelectedItem();
			MemberVo vo = new MemberVo(idField.getText(), pwField.getText(), nameField.getText(), 
					petNameField.getText(),comboBox.getSelectedItem().toString(), birthday);
			boolean b = dao.signUp(vo);
			if(b==false) {
				dispose();
				System.out.println(birthday);
			}
		} 
		
		if(e.getSource() == idCheck) {
			if(dao.checkId(idField.getText())) {
				checkField.setText("중복된 아이디입니다.");
			} else {
				checkField.setText("사용가능한 아이디입니다.");
			}
			
			
		}
		if(e.getSource() == checkPW) {
			if(pwField.getText().equals(pwCheckField.getText())) {
				System.out.println("맞음");
				checkField.setText("비밀번호가 일치합니다.");
			}else {
				System.out.println("틀림");
				checkField.setText("비밀번호가 일치하지 않습니다.");

			}
		}
	}
}

class NotSignUp{
	JFrame frame;
	NotSignUp(){
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 700);
		frame.setVisible(true);
	}
}

