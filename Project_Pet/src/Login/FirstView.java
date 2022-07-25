package Login;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FirstView extends JFrame implements ActionListener {

	private JPanel contentPane;
	JButton loginButton, joinButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstView frame = new FirstView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FirstView() {
		
		ImageIcon img = new ImageIcon("./img/초기화면0.png");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 750);
		setTitle("토리의 비법책");
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loginButton = new JButton("        ");
		loginButton.setBounds(151, 306, 97, 70);
		contentPane.add(loginButton);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(this);
		
		
		joinButton = new JButton("    ");
		joinButton.setBounds(333, 342, 97, 70);
		contentPane.add(joinButton);
		
		joinButton.setBorderPainted(false);
		joinButton.setContentAreaFilled(false);
		joinButton.setFocusPainted(false);
		
		joinButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			MemberLogin frame = new MemberLogin();
			frame.setVisible(true);
			System.out.println( "로그인 누름");
			
			
		} if(e.getSource() == joinButton) {
			SignUP frame = new SignUP();
			frame.setVisible(true);
		}
	}
}