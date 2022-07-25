package Recipe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Login.FirstView;
import Login.SelectMenu;
import javax.swing.JTextArea;

public class SelectRecipe extends JFrame implements ActionListener{


	private JPanel contentPane;
	JButton timerB, mainB, checkB, backB, likeB;
	private JPanel panel;
	private JPanel panel_1,panel_2;
	public JLabel foodname,likeLabel;
	JButton foodImg;
	String str,str1;
	int count_like;
	public JTextArea textArea;
	private JPanel panel_3;
	private JButton howtoRecipe;
	private JLabel likeLabel2;

	public SelectRecipe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("./img/레시피창.png");
		ImageIcon main = new ImageIcon("./img/메인버튼.png");
		ImageIcon timer = new ImageIcon("./img/타이머버튼.png");
		ImageIcon check = new ImageIcon("./img/검사버튼.png");
		ImageIcon back = new ImageIcon("./img/뒤로가기버튼.png");
		ImageIcon like = new ImageIcon("./img/like.png");

		setTitle("레시피");

		setBounds(100, 100, 565, 790);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//타이머
		timerB = new JButton(timer);
		timerB.setBounds(103, 646, 335, 36);
		contentPane.add(timerB);
		timerB.setBorderPainted(false);
		timerB.setContentAreaFilled(false);
		timerB.setFocusPainted(false);
		timerB.addActionListener(this);

		//뒤로가기
		ImageIcon backimg = new ImageIcon("./img/투명버튼.png");
		backB = new JButton();
		backB.setBounds(476, 621, 73, 39);
		contentPane.add(backB);
		backB.setBorderPainted(false);
		backB.setContentAreaFilled(false);
		backB.setFocusPainted(false);
		backB.addActionListener(this);


		//사진
		panel = new JPanel();
		panel.setBounds(145, 106, 272, 150);
		panel.setBackground(new Color(192,65,81,255));
		contentPane.add(panel);
		panel.setLayout(null);


		//레시피 내용
		panel_1 = new JPanel();
		panel_1.setBounds(63, 284, 423, 342);
		panel_1.setBackground(new Color(0,0,0,0));
		contentPane.add(panel_1);
		panel_1.setLayout(null);


		//재료 소개
		ImageIcon introimg = new ImageIcon("./img/재료소개.png");
		panel_2 = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(introimg.getImage(), 0,0,null);
			}
		};
		panel_2.setBounds(0, 0, 135, 342);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(12, 47, 111, 262);
		textArea.setBackground(new Color(250,249,235,255));
		textArea.setEditable(false);
		panel_2.add(textArea);

		//레시피 내용
		panel_3 = new JPanel();
		panel_3.setBounds(147, 0, 278, 304);
		panel_3.setBackground(new Color(243,229,216,255));
		panel_1.add(panel_3);
		panel_3.setLayout(null);


		//요리 이름
		foodname = new JLabel();
		foodname.setBounds(305, 64, 147, 32);
		foodname.setAlignmentX(JLabel.RIGHT);
		foodname.setFont(new Font("Jalnan", Font.BOLD, 14));
		foodname.setForeground(new Color(192,65,81,255));
		contentPane.add(foodname);

		likeB = new JButton(like);
		likeB.setBounds(429, 107, 73, 57);
		likeB.setBorderPainted(false);
		likeB.setContentAreaFilled(false);
		likeB.setFocusPainted(false);
		likeB.addActionListener(this);
		contentPane.add(likeB);
		
		likeLabel = new JLabel("");
		likeLabel.setBounds(429, 200, 73, 15);
		likeLabel.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(likeLabel);
		
		likeLabel2 = new JLabel("좋아요 수");
		likeLabel2.setBounds(433, 174, 57, 15);
		likeLabel2.setFont(new Font("Jalnan", Font.BOLD, 10));
		likeLabel2.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(likeLabel2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RecipesDAO dao = new RecipesDAO();
		RecipesVo v = new RecipesVo(likeLabel.getText());
		if(e.getSource() == timerB) {
			GUITimer frame = new GUITimer();
			frame.setVisible(true);
		}  else if(e.getSource() == backB) {
			dispose();
			SearchRecipe frame = new SearchRecipe();
			frame.setVisible(true);

		} 
		
		//좋아요 버튼 누르기
		if(e.getSource() == likeB) {
			count_like = count_like+1;
			String lkc = Integer.toString(count_like);
			v.setLike_recipe(lkc);
			likeLabel.setText(lkc);
			
			dao.likeRecipe(v);
			
		}
	
	}

	//선택한 레시피 이미지 불러오기
	public void roadimg(String str) {
		this.str = str;
		ImageIcon recipe = new ImageIcon(str);
		foodImg = new JButton(recipe);
		foodImg.setBounds(1, 1, 270, 148);
		foodImg.setBorderPainted(false);
		foodImg.setContentAreaFilled(false);
		foodImg.setFocusPainted(false);

		panel.add(foodImg);
	}

	//레시피 내용 이미지
	public void roadHowto(String str1) {
		this.str1 = str1;
		ImageIcon how = new ImageIcon(str1);
		howtoRecipe = new JButton(how);
		howtoRecipe.setBounds(0, 0, 278, 296);
		howtoRecipe.setBorderPainted(false);
		howtoRecipe.setContentAreaFilled(false);
		howtoRecipe.setFocusPainted(false);
		panel_3.add(howtoRecipe);


	}
}
