package Login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberInfo extends JFrame {

	private JPanel contentPane;
	JButton leaveB;
	JLabel idLabel, nameLabel, petNameLabel, petTypeLabel;

	public MemberInfo() {
		ImageIcon img = new ImageIcon("./img/Á¤º¸.png");
		setBounds(1200, 100, 400, 480);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("È¸¿øÁ¤º¸");
		
		idLabel = new JLabel(MemberLogin.id_info);
		idLabel.setBounds(198, 195, 57, 15);
		idLabel.setFont(new Font("Jalnan", Font.BOLD, 15));
		idLabel.setForeground(new Color(192,65,81,255));
		contentPane.add(idLabel);
		
		nameLabel = new JLabel(MemberLogin.name_info);
		nameLabel.setBounds(198, 230, 57, 15);
		nameLabel.setFont(new Font("Jalnan", Font.BOLD, 15));
		nameLabel.setForeground(new Color(192,65,81,255));
		contentPane.add(nameLabel);
		
		petNameLabel = new JLabel(MemberLogin.petname_info);
		petNameLabel.setBounds(198, 264, 57, 15);
		petNameLabel.setFont(new Font("Jalnan", Font.BOLD, 15));
		petNameLabel.setForeground(new Color(192,65,81,255));
		contentPane.add(petNameLabel);
		
		petTypeLabel = new JLabel(MemberLogin.pettype_info);
		petTypeLabel.setBounds(198, 297, 57, 15);
		petTypeLabel.setFont(new Font("Jalnan", Font.BOLD, 15));
		petTypeLabel.setForeground(new Color(192,65,81,255));
		contentPane.add(petTypeLabel);
		
		MemberDAO dao = new MemberDAO();
		MemberVo vo = new MemberVo();
		
		ImageIcon img2 = new ImageIcon("./img/Å»Åð.png");
		leaveB = new JButton(img2);
		leaveB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dao.leave(vo);
				System.exit(0);
			}
		});
		leaveB.setBorderPainted(false);
		leaveB.setContentAreaFilled(false);
		leaveB.setFocusPainted(false);
		
		leaveB.setBounds(143, 357, 97, 48);
		contentPane.add(leaveB);
	}
}
