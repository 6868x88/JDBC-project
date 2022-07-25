package Recipe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class GUITimer extends JFrame implements ItemListener {

    private static final long serialVersionUID = 5924880907001755076L;

    JLabel jltime;
    JLabel jl;
    JComboBox<Integer> jcb;
    JButton jbt;
    JButton jbt2;
    NumberFormat format;
    JPanel contentPane;

    public Timer timer;
    public long initial;
    public long ttime2;
    public String ttime;
    public long remaining;
    private JPanel panel;
    private JPanel panel_1;

    public GUITimer() {
    	ImageIcon img = new ImageIcon("./img/타이머창.png");
    	ImageIcon startImg = new ImageIcon("./img/시작버튼.png");
    	ImageIcon resetImg = new ImageIcon("./img/초기화 버튼.png");
    	ImageIcon timerimg = new ImageIcon("./img/타이머0.png");
    	
    	setTitle("타이머");
		setBounds(700, 100, 415, 280);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0,0,null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		
		//시간 지정 창
		panel = new JPanel();
		panel.setBounds(-4, 189, 399, 42);
		contentPane.add(panel);
		panel.setBackground(new Color(255,0,0,0));
		
        jcb = new JComboBox<Integer>();
        jcb.setBounds(62, 5, 40, 30);
        for (int i = 15; i > 0; i--) {
            jcb.addItem(Integer.valueOf(i));
        }
        jcb.setSelectedIndex(0);
        ttime = "15";
        panel.setLayout(null);
        panel.add(jcb);
        jcb.setBackground(new Color(192,65,81,255));

        //시작버튼
        jbt = new JButton(startImg);
        jbt.setBounds(213, 5, 72, 30);
        jbt.setBackground(new Color(192,65,81,255));
        jbt.setBorderPainted(false);
        panel.add(jbt);
        
        
        //초기화버튼
        jbt2 = new JButton(resetImg);
        jbt2.setBounds(297, 5, 72, 30);
        jbt2.setBackground(new Color(192,65,81,255));
        jbt2.setBorderPainted(false);
        jbt2.setFocusPainted(false);
        panel.add(jbt2);
        
        JButton btnNewButton = new JButton(timerimg);
        btnNewButton.setEnabled(false);
        btnNewButton.setBounds(107, 5, 94, 30);
        panel.add(btnNewButton);
        btnNewButton.setBorderPainted(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setFocusPainted(false);
        
        panel_1 = new JPanel();
        panel_1.setBounds(12, 65, 375, 103);
        contentPane.add(panel_1);
        panel_1.setBackground(new Color(192,65,81,255));
        
        //시간 표시 창
        
        jltime = new JLabel("00:00");
        jltime.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jltime.setForeground(Color.WHITE);
        jltime.setBackground(new Color(192,65,81,255));
        jltime.setOpaque(true);
        jltime.setFont(new Font("Arial", Font.BOLD, 96));
        panel_1.add(jltime);

        Event e = new Event();
        jbt.addActionListener(e);
        jbt2.addActionListener(e);

        jcb.addItemListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUITimer();
            }

        });
    }

    // 시작버튼 눌렀을 때 타이머 스레드 시작
    void updateDisplay() {

        Timeclass tc = new Timeclass();
        timer = new Timer(1000, tc);
        initial = System.currentTimeMillis();
        timer.start();
    }

    // 시작/초기화 버튼 눌렀을 떄 액션
    public class Event implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	//시작
            if (e.getSource() == jbt) {
                updateDisplay();
            } 
            //초기화
            else {
                jltime.setText("00:00");
                timer.stop();
                remaining = convertTime();
            }
        }
    }

   
    public class Timeclass implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            remaining = convertTime();
            long current = System.currentTimeMillis();
            long elapsed = current - initial;
            remaining -= elapsed;
            // initial = current;

            format = NumberFormat.getNumberInstance();
            format.setMinimumIntegerDigits(2);

            if (remaining < 0)
                remaining = (long) 0;
            int minutes = (int) (remaining / 60000);
            int seconds = (int) ((remaining % 60000) / 1000);
            jltime.setText(format.format(minutes) + ":"
                    + format.format(seconds));

            if (remaining == 0) {
                jltime.setText("STOP");
                timer.stop();
            }
        }
    }

    
    public void itemStateChanged(ItemEvent ie) {

        ttime = (String) jcb.getSelectedItem().toString();
        convertTime();
    }

    
    public long convertTime() {

        ttime2 = Long.parseLong(ttime);
        long converted = (ttime2 * 60000) + 1000;
        return converted;
    }
}