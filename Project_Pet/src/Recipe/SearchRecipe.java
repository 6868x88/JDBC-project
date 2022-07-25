package Recipe;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Diary.DiaryList;
import Hospital.HospitalView;
import Ingredient.SearchIngredient;
import Login.MemberLogin;
import Login.SelectMenu;

public class SearchRecipe extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane,panel1;
	private JTextField textField;
	DefaultTableModel model;
	JButton searchB;
	JTable table;
	ImageIcon temp ;
	String recipeImgsrc, howtoRecipe;
	static String foodname_info;
	private JButton backB;
	private JButton ingredientB;
	private JButton diaryB;
	private JButton hospitalB;

	public SearchRecipe() {
		ImageIcon img = new ImageIcon("./img/검색창.png");
		ImageIcon searchimg = new ImageIcon("./img/검색.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("레시피 검색");
		setBounds(100, 100, 565, 790);
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
		textField.setBounds(149, 86, 258, 39);
		contentPane.add(textField);
		textField.setColumns(10);

		searchB = new JButton(searchimg);
		searchB.setBounds(419, 85, 85, 40);
		contentPane.add(searchB);
		searchB.setBorderPainted(false);
		searchB.setContentAreaFilled(false);
		searchB.setFocusPainted(false);
		searchB.addActionListener(this);

		//데이터 표 창
		String[] col= {"요리 이름","먹여도 되는 반려동물","","재료","","좋아요 수"};
		String[][] row=new String[0][6];

		model=new DefaultTableModel(row,col) {
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};

		table=new JTable(model);
		table.setRowHeight(35);
		table.setBackground(new Color(255,255,255,255));
		JScrollPane js=new JScrollPane(table);
		js.setBounds(12, 5, 409, 468);
		js.setPreferredSize(new Dimension(400,450));
		table.addMouseListener(this);

//		table.getColumnModel().getColumn(5).setMinWidth(0);
//		table.getColumnModel().getColumn(5).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.removeColumn(table.getColumnModel().getColumn(2));    
		//        table.removeColumn(table.getColumnModel().getColumn(3));


		panel1 = new JPanel();
		panel1.setBounds(47, 198, 429, 483);
		contentPane.add(panel1);
		panel1.setBackground(new Color(0,0,0,0));
		panel1.setLayout(null);
		panel1.add(js);


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
		ingredientB = new JButton(backimg);
		ingredientB.setBounds(487, 53, 62, 39);
		contentPane.add(ingredientB);
		ingredientB.setBorderPainted(false);
		ingredientB.setContentAreaFilled(false);
		ingredientB.setFocusPainted(false);
		ingredientB.addActionListener(this);

		hospitalB = new JButton(backimg);
		hospitalB.setBounds(487, 267, 62, 39);
		contentPane.add(hospitalB);
		hospitalB.setBorderPainted(false);
		hospitalB.setContentAreaFilled(false);
		hospitalB.setFocusPainted(false);
		hospitalB.addActionListener(this);
		

		diaryB = new JButton(backimg);
		diaryB.setBounds(496, 336, 53, 39);
		contentPane.add(diaryB);
		diaryB.setBorderPainted(false);
		diaryB.setContentAreaFilled(false);
		diaryB.setFocusPainted(false);
		diaryB.addActionListener(this);


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchB) {
			String foodName=textField.getText();
			if(foodName.length()<1)//입력이 안된 경우
			{
				textField.setText("검색어를 입력해주세요.");
				return;
			}
			
			RecipesDAO dao =new RecipesDAO();
			ArrayList<RecipesVo> list=dao.search(foodName);

			// 출력
			for(RecipesVo v:list) {
				String[] data= {v.getFoodName(), v.getPet(),v.getRecipeImg(),v.getIntro(),v.getHowto(),v.getLike_recipe()};
				model.addRow(data);
				
			
			}    
		}
		if(e.getSource() == backB) {
			dispose();
			SelectMenu frame = new SelectMenu();
			frame.setVisible(true);
		} if(e.getSource() == diaryB) {
			dispose();
			DiaryList frame = new DiaryList();
			frame.setVisible(true);
		} if(e.getSource() == ingredientB) {
			dispose();
			SearchIngredient frame = new SearchIngredient();
			frame.setVisible(true);
		} if(e.getSource() == hospitalB) {
			dispose();
			HospitalView frame= new HospitalView();
			frame.setVisible(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		dispose();
		SelectRecipe frame = new SelectRecipe();
		int row = table.getSelectedRow();
		TableModel data = table.getModel();
		
		if(data.getValueAt(row,1).toString().contains(MemberLogin.pettype_info)) {
			System.out.println("가능");
		}else {
			cautionRecipe frame2 = new cautionRecipe();
			frame2.petName.setText(MemberLogin.petname_info);
			frame2.setVisible(true);
		}
		
		//좋아요 값 넘기기
		String count = (String)data.getValueAt(row, 5);
		frame.count_like = Integer.parseInt(count);
		frame.likeLabel.setText(count);

		//레시피 이름 설정
		String name = (String)data.getValueAt(row, 0);
		frame.foodname.setText(name);
		foodname_info = name;

		//레시피 사진 보내기
		recipeImgsrc = (String)data.getValueAt(row, 2);
		System.out.println(recipeImgsrc);
		frame.roadimg(recipeImgsrc);

		howtoRecipe = (String)data.getValueAt(row, 4);
		frame.roadHowto(howtoRecipe);

		String introfood = (String)data.getValueAt(row, 3);
		frame.textArea.setText(introfood);

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
}
