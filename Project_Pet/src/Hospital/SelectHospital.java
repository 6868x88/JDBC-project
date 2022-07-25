package Hospital;

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

public class SelectHospital extends JFrame {

	private JPanel contentPane;
	JLabel name, tel, address, state;
	public SelectHospital() {
		ImageIcon img = new ImageIcon("./img/병원선택창.png");
		setBounds(700, 100, 460, 370);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
			g.drawImage(img.getImage(), 0,0,null);
		}
	};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("병원");
		
		name = new JLabel("New label");
		name.setBounds(33, 33, 305, 87);
		name.setFont(new Font("Jalnan", Font.BOLD, 30));
		name.setForeground(Color.white);
		contentPane.add(name);
	
		tel = new JLabel("New label");
		tel.setBounds(33, 130, 379, 41);
		tel.setFont(new Font("Jalnan", Font.BOLD, 18));
		tel.setForeground(Color.white);
		contentPane.add(tel);
		
		address = new JLabel("New label");
		address.setBounds(33, 181, 399, 41);
		address.setFont(new Font("Jalnan", Font.BOLD, 15));
		address.setForeground(Color.white);
		contentPane.add(address);
		
		state = new JLabel("New label");
		state.setBounds(33, 255, 192, 41);
		state.setFont(new Font("Jalnan", Font.BOLD, 25));
		state.setForeground(Color.white);
		contentPane.add(state);
	}
}
