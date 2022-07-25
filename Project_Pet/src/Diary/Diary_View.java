package Diary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Diary_View extends JFrame implements ActionListener {

	private JPanel contentPane,panel;
	JTextField dateField;
	JTextField foodnameField;
	JTextField satisfactionField;
	JTextArea writeField;
	JButton saveB,deleteB;

	ImageIcon img = new ImageIcon("./img/�ϱ�â.png");
	ImageIcon textimg = new ImageIcon("./img/����.png");
	ImageIcon saveimg = new ImageIcon("./img/�����ư.png");
	ImageIcon deleteimg = new ImageIcon("./img/������ư.png");
	ImageIcon satisimg = new ImageIcon("./img/����.png");
	ImageIcon notsatisimg = new ImageIcon("./img/�Ҹ���.png");
	ImageIcon normalimg = new ImageIcon("./img/����.png");
	private JButton satisB;
	private JButton normalB;
	private JButton notsatisB;
	private JButton backB, editB;
	JTextField idField;
	
	
	

	public Diary_View() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 790);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("�ϱ� �ۼ�");

		//���� ��¥ �Է� �ʵ�
		dateField = new JTextField();
		dateField.setBounds(158, 140, 173, 21);
		contentPane.add(dateField);
		dateField.setColumns(10);
		dateField.setBackground(new Color(243,229,216,255));
		dateField.setBorder(null);


		//���� �丮 �Է� �ʵ�
		foodnameField = new JTextField();
		foodnameField.setBounds(158, 171, 173, 21);
		contentPane.add(foodnameField);
		foodnameField.setColumns(10);
		foodnameField.setBackground(new Color(243,229,216,255));
		foodnameField.setBorder(null);


		//������ �Է� �ʵ�
		satisfactionField = new JTextField();
		satisfactionField.setBounds(158, 199, 45, 21);
		contentPane.add(satisfactionField);
		satisfactionField.setColumns(10);
		satisfactionField.setEditable(false);


		//�ϱ� �ۼ� �ʵ�
		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(textimg.getImage(), 0,0,null);
			}
		};;
		panel.setBounds(87, 275, 372, 323);
		contentPane.add(panel);
		panel.setLayout(null);
		
		writeField = new JTextArea();
		writeField.setBounds(24, 81, 314, 232);
		panel.add(writeField);
		writeField.setColumns(10);
		writeField.setLineWrap(true);
		writeField.setFont(new Font("Dialog", Font.PLAIN, 16));
		writeField.setBackground(new Color(246,249,247,255));
		
		//����
		saveB = new JButton(saveimg);
		saveB.setBounds(97, 628, 97, 40);
		contentPane.add(saveB);
		saveB.setBorderPainted(false);
		saveB.setContentAreaFilled(false);
		saveB.setFocusPainted(false);
		saveB.addActionListener(this);

		//����
		deleteB = new JButton(deleteimg);
		deleteB.setBounds(341, 628, 97, 40);
		contentPane.add(deleteB);
		deleteB.setBorderPainted(false);
		deleteB.setContentAreaFilled(false);
		deleteB.setFocusPainted(false);
		deleteB.addActionListener(this);


		//������ ��ư
		satisB = new JButton(satisimg);
		satisB.setBounds(245, 196, 30, 31);
		contentPane.add(satisB);
		satisB.setBorderPainted(false);
		satisB.setContentAreaFilled(false);
		satisB.setFocusPainted(false);
		satisB.addActionListener(this);

		normalB = new JButton(normalimg);
		normalB.setFocusPainted(false);
		normalB.setContentAreaFilled(false);
		normalB.setBorderPainted(false);
		normalB.setBounds(301, 196, 30, 31);
		contentPane.add(normalB);
		normalB.addActionListener(this);

		notsatisB = new JButton(notsatisimg);
		notsatisB.setFocusPainted(false);
		notsatisB.setContentAreaFilled(false);
		notsatisB.setBorderPainted(false);
		notsatisB.setBounds(357, 196, 30, 31);
		contentPane.add(notsatisB);
		notsatisB.addActionListener(this);
		
		//�ڷΰ���
		ImageIcon back = new ImageIcon("./img/�����ư.png");
		backB = new JButton(back);
		backB.setBounds(440, 628, 97, 60);
		contentPane.add(backB);
		backB.setBorderPainted(false);
		backB.setContentAreaFilled(false);
		backB.setFocusPainted(false);
		backB.addActionListener(this);
		
//		//����
//		ImageIcon edit = new ImageIcon("./img/������ư.png");
//		editB = new JButton(edit);
//		editB.setBounds(221, 628, 97, 40);
//		editB.setBorderPainted(false);
//		editB.setContentAreaFilled(false);
//		editB.setFocusPainted(false);
//		contentPane.add(editB);
//		editB.addActionListener(this);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DiaryDAO dao = new DiaryDAO();
		if(e.getSource() == saveB) {
			DiaryDTO dto = new DiaryDTO(dateField.getText(), foodnameField.getText(), satisfactionField.getText(), writeField.getText());
			dao.writeDiary(dto);
			dispose();
			DiaryList frame = new DiaryList();
			frame.setVisible(true);
		}
		if(e.getSource() == deleteB) {
			dao.deleteDiary(dateField.getText());
			dispose();
			DiaryList frame = new DiaryList();
			frame.setVisible(true);
		}

		if(e.getSource()==backB) {
			dispose();
			DiaryList frame =new DiaryList();
			frame.setVisible(true);
		}
		
//		if(e.getSource() == editB) {
//			DiaryDTO dto = new DiaryDTO(dateField.getText(), foodnameField.getText(), satisfactionField.getText(), writeField.getText());
//			dao.editDairy(dto);
//			dispose();
//			DiaryList frame = new DiaryList();
//			frame.setVisible(true);
//		}
		if(e.getSource( )== satisB) {
			satisfactionField.setText("����");
		} else if(e.getSource() == normalB) {
			satisfactionField.setText("����");
		} else if(e.getSource() == notsatisB) {
			satisfactionField.setText("�Ҹ���");
		}


	}
}
