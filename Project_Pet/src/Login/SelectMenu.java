package Login;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Diary.DiaryList;
import Hospital.HospitalView;
import Ingredient.SearchIngredient;
import Recipe.RecipesDAO;
import Recipe.RecipesVo;
import Recipe.SearchRecipe;
import Recipe.SelectRecipe;

public class SelectMenu extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton recipeB,ingredientB,hospitalB,diaryB ;
	String id_info;
	private JButton infoB;
	JButton birthdayB;
	String[] data;
	
	public SelectMenu() {
		ImageIcon img = new ImageIcon("./img/선택창.png");
		ImageIcon recipeimg = new ImageIcon("./img/레시피검색_버튼.png");
		ImageIcon ingredientimg = new ImageIcon("./img/재료 검사_버튼.png");
		ImageIcon hospitalimg = new ImageIcon("./img/병원검색_버튼.png");
		ImageIcon diaryimg = new ImageIcon("./img/일기_버튼.png");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 790);
		setTitle("메뉴선택");
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		recipeB = new JButton(recipeimg);
		recipeB.setBounds(68, 110, 220, 240);
		contentPane.add(recipeB);
		recipeB.setBorderPainted(false);
		recipeB.setContentAreaFilled(false);
		recipeB.setFocusPainted(false);
		recipeB.addActionListener(this);


		ingredientB = new JButton(ingredientimg);
		ingredientB.setBounds(304, 131, 180, 240);
		contentPane.add(ingredientB);
		ingredientB.setBorderPainted(false);
		ingredientB.setContentAreaFilled(false);
		ingredientB.setFocusPainted(false);
		ingredientB.addActionListener(this);

		hospitalB = new JButton(hospitalimg);
		hospitalB.setBounds(79, 378, 200, 200);
		contentPane.add(hospitalB);
		hospitalB.setBorderPainted(false);
		hospitalB.setContentAreaFilled(false);
		hospitalB.setFocusPainted(false);
		hospitalB.addActionListener(this);

		diaryB = new JButton(diaryimg);
		diaryB.setBounds(304, 413, 200, 200);
		contentPane.add(diaryB);
		diaryB.setBorderPainted(false);
		diaryB.setContentAreaFilled(false);
		diaryB.setFocusPainted(false);
		diaryB.addActionListener(this);
		
		infoB = new JButton();
		infoB.setBounds(397, 623, 97, 80);
		contentPane.add(infoB);
		infoB.addActionListener(this);
		infoB.setBorderPainted(false);
		infoB.setContentAreaFilled(false);
		infoB.setFocusPainted(false);
		
		
		ImageIcon birthimg = new ImageIcon("./img/생일.png");

		birthdayB = new JButton(birthimg);
		birthdayB.setBounds(60, 30, 229, 94);
		birthdayB.setBorderPainted(false);
		birthdayB.setContentAreaFilled(false);
		birthdayB.setFocusPainted(false);
		birthdayB.addActionListener(this);
		birthdayB.setVisible(false);
		contentPane.add(birthdayB);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == recipeB) {
			dispose();
			SearchRecipe frame = new SearchRecipe();
			frame.setVisible(true);
		}
		if(e.getSource() == diaryB) {
			dispose();
			DiaryList frame= new DiaryList();
			frame.setVisible(true);
			
		}
		if(e.getSource() == ingredientB) {
			dispose();
			SearchIngredient frame= new SearchIngredient();
			frame.setVisible(true);
		}
		if(e.getSource() == hospitalB) {
			dispose();
			HospitalView frame = new HospitalView();
			frame.setVisible(true);
		}
		
		if(e.getSource() == infoB) {
			MemberInfo frame = new MemberInfo();
			frame.setVisible(true);
		}
		
		//생일 버튼 클릭시 생일 케이크 레시피 
		if(e.getSource() == birthdayB) {
			dispose();
			SelectRecipe frame = new SelectRecipe();
			RecipesDAO dao = new RecipesDAO();
			ArrayList<RecipesVo> list=dao.cake();
			
			for(RecipesVo v:list) {
				String[] data= {v.getFoodName(), v.getPet(),v.getRecipeImg(),v.getIntro(),v.getHowto(),v.getLike_recipe()};
				
				frame.foodname.setText(data[0]);
				System.out.println(data[0]);
				frame.roadimg(data[2]);
				frame.roadHowto(data[4]);
				frame.textArea.setText(data[3]);
				frame.likeLabel.setText(data[5]);
			
			}
			frame.setVisible(true);
		}
	} 
}
