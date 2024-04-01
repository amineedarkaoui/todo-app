package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class TimerPanel extends JPanel implements ActionListener{
	
	ImageIcon preStart = new ImageIcon("play.png");
	ImageIcon prePause = new ImageIcon("pause (1).png");
	ImageIcon preReset = new ImageIcon("undo-arrow (1).png");
	
	ImageIcon startIcon = new ImageIcon(preStart.getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));
	ImageIcon pauseIcon = new ImageIcon(prePause.getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));
	ImageIcon resetIcon = new ImageIcon(preReset.getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));
	
	private JButton startbtn = new JButton("");
	private JButton resetbtn = new JButton("");
	private JButton Break = new JButton("Break");
	private JLabel timerLabel = new JLabel();
	int elapsedTime = 1500000;
	int seconds = 0;
	int minutes =25;
	boolean isStarted = false;
	boolean isPomodoro = true;
	boolean isBreak = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	
	Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			elapsedTime-=1000;
			seconds = (elapsedTime/1000) % 60;
			minutes = (elapsedTime/60000) % 60;
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			timerLabel.setText(minutes_string+" : "+seconds_string);
			
		}
	});
	
	static Color transparent = new Color(0,0,0,0);
	static Color mainColor = new Color(0x161721);
	static Color secColor = new Color(0x272A50);
	
	public TimerPanel() {
		
		JLabel notes = new JLabel();
		notes.setText("Pomodoro Timer");
		notes.setFont(new Font("Inter", Font.BOLD, 24));
		notes.setForeground(Color.white);
		
		timerLabel.setText(minutes_string+" : "+seconds_string);
		timerLabel.setFont(new Font("Inter", Font.BOLD, 90));
		timerLabel.setForeground(new Color(0xFFFFFF));
		timerLabel.setHorizontalAlignment(JTextField.CENTER);


		startbtn.setPreferredSize(new Dimension(50, 50));
		startbtn.setBackground(new Color(0x272A50));
		startbtn.setForeground(new Color(0xFFFFFF));
		startbtn.setFocusable(false);
		startbtn.setBorderPainted(false);
		startbtn.setIcon(startIcon);
		startbtn.addActionListener(this);
		
		resetbtn.setPreferredSize(new Dimension(50, 50));
		resetbtn.setBackground(new Color(0x272A50));
		resetbtn.setForeground(new Color(0xFFFFFF));
		resetbtn.setFocusable(false);
		resetbtn.addActionListener(this);
		resetbtn.setBorderPainted(false);
		resetbtn.setIcon(resetIcon);

		Break.setPreferredSize(new Dimension(120, 50));
		Break.setBackground(new Color(0x272A50));
		Break.setForeground(new Color(0xFFFFFF));
		Break.setFont(new Font("Inter", Font.BOLD, 18));
		Break.setFocusable(false);
		Break.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
		
        Break.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	startbtn.setIcon(startIcon);
            	if (Break.getText().equals("Break")) {
            		isPomodoro = false;
            		isBreak = true;
            		Break.setText("Pomodoro");
            		setTimerDuration(5);
                    stop();
        			if(startbtn.getText().equals("PAUSE")) {
        				startbtn.setText("START");
        			}
//        			resetbtn.setVisible(false);
            	}
            	else {
            		isPomodoro = true;
                	isBreak = false;
                	Break.setText("Break");
                	setTimerDuration(25);
                    stop();
        			if(startbtn.getText().equals("PAUSE")) {
        				startbtn.setText("START");
        			}
//        			resetbtn.setVisible(true);
            	}
            }
        });
        
        JTextField newNote = new JTextField();
		newNote.setText("   Ajouter une note ...");
		newNote.setFont(new Font("Inter", Font.PLAIN, 16));
		newNote.setPreferredSize(new Dimension(460,50));
		newNote.setBackground(mainColor);
		newNote.setForeground(Color.LIGHT_GRAY);
		newNote.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		newNote.setHorizontalAlignment(JTextField.LEADING);
		newNote.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App.changePage(new AddNotePage());
			}
		});
        
        JPanel newNoteLay = new JPanel();
		newNoteLay.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		newNoteLay.setBackground(transparent);
		newNoteLay.setPreferredSize(new Dimension(60,80));
		newNoteLay.add(newNote);
		
		JPanel notesTitLay = new JPanel();
		notesTitLay.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 20));
		notesTitLay.setBackground(mainColor);
		notesTitLay.setPreferredSize(new Dimension(50, 70));
		notesTitLay.add(notes);
		
		JPanel topButLay = new JPanel();
		topButLay.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		topButLay.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		topButLay.setBackground(mainColor);
		topButLay.add(Break);
		
		JPanel timerLay = new JPanel();
		timerLay.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
		timerLay.setBackground(mainColor);
		timerLay.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
		timerLay.add(timerLabel);
		
		JPanel butsLay = new JPanel();
		butsLay.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
		butsLay.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		butsLay.setBackground(mainColor);
		butsLay.add(resetbtn);
		butsLay.add(startbtn);
		
		JPanel mainTimerLayout = new JPanel();
		mainTimerLayout.setLayout(new BoxLayout(mainTimerLayout, BoxLayout.Y_AXIS));
		mainTimerLayout.setBackground(mainColor);
		mainTimerLayout.add(topButLay);
		mainTimerLayout.add(timerLay);
		mainTimerLayout.add(butsLay);
        
        JPanel notesLay = new JPanel();
		notesLay.setBackground(mainColor);
		notesLay.setLayout(new BorderLayout());
		notesLay.add(notesTitLay, BorderLayout.NORTH);
		notesLay.add(mainTimerLayout, BorderLayout.CENTER);
        
		this.setLayout(new BorderLayout());
		this.setBackground(mainColor);
		this.add(newNoteLay, BorderLayout.NORTH);
		this.add(notesLay, BorderLayout.CENTER);
    }
	
	//********************************************************************************************8

    private void setTimerDuration(int minutes) {
        this.minutes = minutes;
        this.elapsedTime = minutes * 60 * 1000;
        updateTimerLabel();
    }



    private void updateTimerLabel() {
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        timerLabel.setText(minutes_string + " : " + "00");
    
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startbtn) {
			if(!isStarted) {
				start();
				startbtn.setIcon(pauseIcon);
			}else {
				stop();
				startbtn.setIcon(startIcon);
			}
		}else if(e.getSource()==resetbtn) {
			reset();
			if(startbtn.getText().equals("PAUSE")) {
				startbtn.setText("START");
			}
		}
		
	}
	
	private void start() {
		timer.start();
		isStarted = true;
	}
	private void stop() {
        timer.stop();
        isStarted = false;
	}
	private void reset() {
		startbtn.setIcon(startIcon);
		 timer.stop();
	    isStarted = false;
	    if (isPomodoro) {
	        elapsedTime = 1500000;
	        minutes = 25;
	    } else {
	        elapsedTime = 300000;
	        minutes = 5;
	    }
	    seconds = 0;
	    updateTimerLabel();
	}

	
}
