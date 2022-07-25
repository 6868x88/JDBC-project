package Hospital;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Diary.DiaryList;
import Login.SelectMenu;
import Recipe.SearchRecipe;

public class HospitalView extends JFrame implements MouseListener, ActionListener {

	private JPanel contentPane, panel1;
	JTextField textField;
	JButton searchB,backB,btnNewButton,recipeB,recipeB_1,diaryB;
	DefaultTableModel model;
	JTable table;
	JTextArea textArea;
	public HospitalView() {
		ImageIcon img = new ImageIcon("./img/병원창.png");
		ImageIcon searchimg = new ImageIcon("./img/검색.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 790);
		setTitle("병원 검색");
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//검색창
		textField = new JTextField();
		textField.setBounds(155, 87, 243, 39);
		contentPane.add(textField);
		textField.setColumns(10);

		searchB = new JButton(searchimg);
		searchB.setBounds(399, 86, 85, 40);
		contentPane.add(searchB);
		searchB.setBorderPainted(false);
		searchB.setContentAreaFilled(false);
		searchB.setFocusPainted(false);
		searchB.addActionListener(this);


		String[] col= {"지역","병원 이름","전화번호","주소","영업상태"};
		String[][] row=new String[0][5];

		model=new DefaultTableModel(row,col) {
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};

		table=new JTable(model);
		table.setRowHeight(35);
		table.setBackground(new Color(255,255,255,255));
		JScrollPane js=new JScrollPane(table);
		js.setBounds(12, 5, 409, 315);
		js.setPreferredSize(new Dimension(400,450));
		table.addMouseListener(this);



		panel1 = new JPanel();
		panel1.setBounds(55, 174, 429, 333);
		contentPane.add(panel1);
		panel1.setBackground(new Color(0,0,0,0));
		panel1.setLayout(null);
		panel1.add(js);


		//메모
		ImageIcon memo = new ImageIcon("./img/메모.png");
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(memo.getImage(), 0,0,null);
			}
		};
		panel.setBounds(68, 556, 381, 111);
		contentPane.add(panel);
		panel.setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(12, 33, 357, 68);
		panel.add(textArea);

		//뒤로가기
		ImageIcon backimg = new ImageIcon("./img/투명버튼.png");
		backB = new JButton();
		backB.setBounds(476, 621, 73, 39);
		contentPane.add(backB);
		backB.setBorderPainted(false);
		backB.setContentAreaFilled(false);
		backB.setFocusPainted(false);
		backB.addActionListener(this);

		
		
		//우측 - 메뉴 이동 버튼
		//		btnNewButton = new JButton("New button");
		//		btnNewButton.setBounds(487, 53, 62, 39);
		//		contentPane.add(btnNewButton);

		recipeB = new JButton(backimg);
		recipeB.setBounds(487, 140, 62, 39);
		contentPane.add(recipeB); 
		recipeB.setBorderPainted(false);
		recipeB.setContentAreaFilled(false);
		recipeB.setFocusPainted(false);
		recipeB.addActionListener(this);

		//		recipeB_1 = new JButton("New button");
		//		recipeB_1.setBounds(496, 279, 53, 39);
		//		contentPane.add(recipeB_1);

		diaryB = new JButton(backimg);
		diaryB.setBounds(496, 336, 53, 39);
		contentPane.add(diaryB);
		diaryB.setBorderPainted(false);
		diaryB.setContentAreaFilled(false);
		diaryB.setFocusPainted(false);
		diaryB.addActionListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
			SelectHospital frame = new SelectHospital();
			int row = table.getSelectedRow();
			TableModel data = table.getModel();
			
			//병원 이름
			String h_name = (String)data.getValueAt(row, 1);
			frame.name.setText("<html>"+h_name+"</html>");
			
			//병원 전화번호
			String h_tel = (String)data.getValueAt(row, 2);
			frame.tel.setText(h_tel);
			
			//병원 주소
			String h_address = (String)data.getValueAt(row, 3);
			frame.address.setText("<html>"+h_address+"</html>");
			
			//개업상태
			String h_state = (String)data.getValueAt(row, 4);
			frame.state.setText(h_state);
			
			frame.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchB) {
			String ingreName=textField.getText();
			if(ingreName.length()<1)//입력이 안된 경우
			{
				textField.setText("검색어를 입력해주세요.");
				return;
			}
			//처리
			for(int i=model.getRowCount()-1;i>=0;i--) {
				model.removeRow(i);
			}
			HospitalDAO dao=new HospitalDAO();
			HospitalDTO vo = new HospitalDTO();
			ArrayList<HospitalDTO> list = dao.search(ingreName);

			// 출력
			for(HospitalDTO v:list) {
				String[] data= {v.getCity(), v.getName(), v.getTel(), v.getAddress(), v.getState()};
				model.addRow(data);
			}    
		}

		if(e.getSource() == backB) {
			dispose();
			SelectMenu frame = new SelectMenu();
			frame.setVisible(true);
		} else if(e.getSource() == recipeB) {
			dispose();
			SearchRecipe frame = new SearchRecipe();
			frame.setVisible(true);
		} else if(e.getSource() == diaryB) {
			dispose();
			DiaryList frame = new DiaryList();
			frame.setVisible(true);
		}


	}
}
