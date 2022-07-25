package Ingredient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class SelectIngredient extends JFrame {

	private JPanel contentPane;
	JLabel pet,carefulPet,lblNewLabel;
	JTextArea textArea;

	public SelectIngredient() {
		ImageIcon img = new ImageIcon("./img/재료설명창.png");
		setBounds(700, 100, 515, 415);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
			g.drawImage(img.getImage(), 0,0,null);
		}
	};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pet = new JLabel("");
		pet.setVerticalAlignment(SwingConstants.TOP);
		pet.setForeground(new Color(192,65,81,255));
		pet.setBounds(340, 168, 122, 67);
		contentPane.add(pet);
		setTitle("재료");
		
		carefulPet = new JLabel("");
		carefulPet.setVerticalAlignment(SwingConstants.TOP);
		carefulPet.setForeground(new Color(192,65,81,255));
		carefulPet.setBounds(340, 281, 122, 67);
		contentPane.add(carefulPet);
		
		textArea = new JTextArea();
		textArea.setBounds(33, 191, 239, 144);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(44, 52, 201, 40);
		lblNewLabel.setFont(new Font("Jalnan", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.white);
		contentPane.add(lblNewLabel);
	}
}
