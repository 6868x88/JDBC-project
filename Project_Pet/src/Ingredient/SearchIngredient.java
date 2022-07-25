package Ingredient;

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
import Hospital.HospitalView;
import Login.SelectMenu;
import Recipe.SearchRecipe;

public class SearchIngredient extends JFrame implements MouseListener, ActionListener {

	private JPanel contentPane, panel1;
	JTextField textField;
	JButton searchB,backB,btnNewButton,recipeB,recipeB_1,diaryB,hospitalB;
	DefaultTableModel model;
	JTable table;
	JTextArea textArea;
	public SearchIngredient() {
		ImageIcon img = new ImageIcon("./img/��� �˻�â.png");
		ImageIcon searchimg = new ImageIcon("./img/�˻�.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 790);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("��� �˻�");

		//�˻�â
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


		String[] col= {"��� �̸�","���̸� �ȵǴ� �ݷ�����","ȿ��","",""};
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


		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);

		panel1 = new JPanel();
		panel1.setBounds(55, 174, 429, 333);
		contentPane.add(panel1);
		panel1.setBackground(new Color(0,0,0,0));
		panel1.setLayout(null);
		panel1.add(js);


		//�޸�
		ImageIcon memo = new ImageIcon("./img/�޸�.png");
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

		//�ڷΰ���
		ImageIcon backimg = new ImageIcon("./img/�����ư.png");
		backB = new JButton();
		backB.setBounds(476, 621, 73, 39);
		contentPane.add(backB);
		backB.setBorderPainted(false);
		backB.setContentAreaFilled(false);
		backB.setFocusPainted(false);
		backB.addActionListener(this);

		
		
		//���� - �޴� �̵� ��ư
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
		SelectIngredient frame = new SelectIngredient();
		int row = table.getSelectedRow();
		TableModel data = table.getModel();

		//��� �̸�
		String name = (String)data.getValueAt(row, 0);
		frame.lblNewLabel.setText(name);

		//���̸� �ȵǴ� ����
		String pet1 = (String)data.getValueAt(row, 1);
		frame.pet.setText(pet1);

		//���� �ؾ� �� ����
		String pet2 = (String)data.getValueAt(row, 3);
		frame.carefulPet.setText(pet2);

		//ȿ��
		String detail_ingre = (String)data.getValueAt(row, 4);
		frame.textArea.setText(detail_ingre);
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
			if(ingreName.length()<1)//�Է��� �ȵ� ���
			{
				textField.setText("�˻�� �Է����ּ���.");
				return;
			}
			//ó��
			for(int i=model.getRowCount()-1;i>=0;i--) {
				model.removeRow(i);
			}
			IngredientDAO dao=new IngredientDAO();
			IngredientVo vo = new IngredientVo();
			ArrayList<IngredientVo> list=dao.search(ingreName);

			// ���
			for(IngredientVo v:list) {
				String[] data= {v.getIngredient(), v.getPet(), v.getEffect(),v.getCarefulpet(),v.getDetail()};
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
		} else if(e.getSource() == hospitalB) {
			dispose();
			HospitalView frame = new HospitalView();
			frame.setVisible(true);
		}


	}
}
