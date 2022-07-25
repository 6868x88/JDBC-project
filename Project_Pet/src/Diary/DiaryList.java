package Diary;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Hospital.HospitalView;
import Ingredient.SearchIngredient;
import Login.MemberLogin;
import Login.MemberVo;
import Login.SelectMenu;
import Recipe.SearchRecipe;

public class DiaryList extends JFrame implements MouseListener,ActionListener {

	private JPanel contentPane;
	DefaultTableModel model;
	JTable table;
	JScrollPane pane;
	private JButton writeB;
	private JButton backB,ingredientB,recipeB,hospitalB;
	public JLabel test_id;
	public String str;
	public MemberVo p;


	public DiaryList() {
		ImageIcon img = new ImageIcon("./img/일기목록.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 780);
		setTitle("일기 목록");

		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		String[] col = {"작성일","요리 이름","만족도","내용","작성 아이디"};
		String [][] rows = new String[0][5];

		model=new DefaultTableModel(rows,col) {
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};

		DiaryDAO dao=new DiaryDAO();
		ArrayList<DiaryDTO> list = dao.gdiaryList(p);
		for(DiaryDTO dto : list) {
			String [] data = {
					dto.getToday(),dto.getFoodname(),dto.getSatisfaction(),dto.getWriting_Dairy(),dto.getDiary_id()
			};
			model.addRow(data);
		}
		
		
		
		contentPane.setLayout(null);
		table = new JTable(model);
		pane = new JScrollPane(table);
		pane.setBounds(59, 169, 415, 428);
		getContentPane().add(pane);
		table.addMouseListener(this);
		table.setRowHeight(35);
		
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.removeColumn(table.getColumnModel().getColumn(3));


		ImageIcon writeimg = new ImageIcon("./img/작성.png");
		writeB = new JButton(writeimg);
		writeB.setBounds(201, 616, 121, 52);
		contentPane.add(writeB);
		writeB.setBorderPainted(false);
		writeB.setContentAreaFilled(false);
		writeB.setFocusPainted(false);
		writeB.addActionListener(this);

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

		recipeB = new JButton(backimg);
		recipeB.setBounds(487, 140, 62, 39);
		contentPane.add(recipeB); 
		recipeB.setBorderPainted(false);
		recipeB.setContentAreaFilled(false);
		recipeB.setFocusPainted(false);
		recipeB.addActionListener(this);
		
		hospitalB = new JButton(backimg);
		hospitalB.setBounds(487, 267, 62, 39);
		contentPane.add(hospitalB);
		hospitalB.setBorderPainted(false);
		hospitalB.setContentAreaFilled(false);
		hospitalB.setFocusPainted(false);
		hospitalB.addActionListener(this);
		
		
		
	}



	
	@Override
	public void mouseClicked(MouseEvent e) {
		dispose();
		int row = table.getSelectedRow();
		TableModel data = table.getModel();

		String today = (String)data.getValueAt(row, 0);
		String foodname = (String)data.getValueAt(row, 1);
		String satisfaction = (String)data.getValueAt(row, 2);
		String writing_Dairy = (String)data.getValueAt(row, 3);

		Diary_View frame = new Diary_View();
		frame.dateField.setText(today);
		frame.foodnameField.setText(foodname);
		frame.satisfactionField.setText(satisfaction);
		frame.writeField.setText(writing_Dairy);

		frame.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == writeB) {
			dispose();
			Diary_View frame = new Diary_View();
			frame.setVisible(true);
		}
		if(e.getSource() == backB) {
			dispose();
			SelectMenu frame = new SelectMenu();
			frame.setVisible(true);
		} else if(e.getSource() == recipeB) {
			dispose();
			SearchRecipe frame = new SearchRecipe();
			frame.setVisible(true);
		} else if(e.getSource() == ingredientB) {
			dispose();
			SearchIngredient frame = new SearchIngredient();
			frame.setVisible(true);
		} else if(e.getSource() == hospitalB) {
			dispose();
			HospitalView frame = new HospitalView();
			frame.setVisible(true);
		}

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
