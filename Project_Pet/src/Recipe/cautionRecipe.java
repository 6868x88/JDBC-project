package Recipe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class cautionRecipe extends JFrame {

	private JPanel contentPane;
	JLabel petName;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cautionRecipe frame = new cautionRecipe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public cautionRecipe() {
		ImageIcon img = new ImageIcon("./img/주의.png");
		setBounds(700, 100, 210, 270);
		setTitle("주의");
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		petName = new JLabel("");
		petName.setBounds(37, 122, 85, 33);
		petName.setFont(new Font("Jalnan", Font.BOLD, 20));
		contentPane.add(petName);
	}
}
