package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class MainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static int selectedTab;
	static JButton rappelBut;

	MainUI() {
		Color transparent = new Color(0,0,0,0);
		Color mainColor = new Color(0x161721);
		Color secColor = new Color(0x272A50);
		
		JLabel logo = new JLabel();
		logo.setText("To");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Inter", Font.BOLD, 34));
		
		JLabel dox = new JLabel();
		dox.setText("DOX");
		dox.setForeground(new Color(0x0D82AF));
		dox.setFont(new Font("Inter", Font.BOLD, 34));		
		
		JPanel logoMainCont = new JPanel();
		logoMainCont.setLayout(new BoxLayout(logoMainCont, BoxLayout.X_AXIS));
		logoMainCont.setPreferredSize(new Dimension(250,50));
		logoMainCont.setBackground(transparent);
		logoMainCont.add(Box.createVerticalGlue());
		logoMainCont.add(logo);
		logoMainCont.add(dox);
		logoMainCont.add(Box.createVerticalGlue());
				
		JPanel logoCont = new JPanel();
		logoCont.setLayout(new FlowLayout(FlowLayout.CENTER));
		logoCont.setPreferredSize(new Dimension(300, 30));
		logoCont.setBackground(transparent);
		logoCont.add(logoMainCont);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0x161721));
		header.setPreferredSize(new Dimension(100, 60));
		header.setLayout(new BorderLayout());
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		header.add(logoCont, BorderLayout.WEST);
		
		JButton mainBut = new JButton();
		JButton tasksBut = new JButton();
		JButton notesBut = new JButton();
		JButton pomodoroBut = new JButton();
		rappelBut = new JButton();
		mainBut.setPreferredSize(new Dimension(300, 70));
		mainBut.setText("Home");
		mainBut.setForeground(Color.white);
		mainBut.setFocusable(false);
		mainBut.setFont(new Font("Inter", Font.BOLD, 20));
		mainBut.setBorder(new EmptyBorder(0, 0, 0, 0));
		if (selectedTab == 0) {
			mainBut.setBackground(secColor);
		}else {
			mainBut.setBackground(mainColor);
		}
		mainBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedTab = 0;
				mainBut.setBackground(secColor);
				tasksBut.setBackground(mainColor);
				notesBut.setBackground(mainColor);
				pomodoroBut.setBackground(mainColor);
				rappelBut.setBackground(mainColor);
				TasksPage.resetTaskLay();
				HomePage.resetTaskLay();
				App.changePage(new HomePage());
			}
		});
		
		tasksBut.setPreferredSize(new Dimension(300, 70));
		tasksBut.setText("Taches");
		tasksBut.setForeground(Color.white);
		tasksBut.setFocusable(false);
		tasksBut.setFont(new Font("Inter", Font.BOLD, 20));
		tasksBut.setBorder(new EmptyBorder(0, 0, 0, 0));
		if (selectedTab == 1) {
			tasksBut.setBackground(secColor);
		}else {
			tasksBut.setBackground(mainColor);
		}
		tasksBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedTab = 1;
				mainBut.setBackground(mainColor);
				tasksBut.setBackground(secColor);
				notesBut.setBackground(mainColor);
				pomodoroBut.setBackground(mainColor);
				rappelBut.setBackground(mainColor);
				TasksPage.resetTaskLay();
				HomePage.resetTaskLay();
				App.changePage(new TasksPage());
			}
		});
		
		notesBut.setPreferredSize(new Dimension(300, 70));
		notesBut.setText("Notes");
		notesBut.setBackground(mainColor);
		notesBut.setForeground(Color.white);
		notesBut.setFocusable(false);
		notesBut.setFont(new Font("Inter", Font.BOLD, 20));
		notesBut.setBorder(new EmptyBorder(0, 0, 0, 0));
		notesBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedTab = 2;
				mainBut.setBackground(mainColor);
				tasksBut.setBackground(mainColor);
				notesBut.setBackground(secColor);
				pomodoroBut.setBackground(mainColor);
				rappelBut.setBackground(mainColor);
				TasksPage.resetTaskLay();
				HomePage.resetTaskLay();
				App.changePage(new NotesPage());
			}
		});

		pomodoroBut.setPreferredSize(new Dimension(300, 70));
		pomodoroBut.setText("Pomodoro");
		pomodoroBut.setBackground(mainColor);
		pomodoroBut.setForeground(Color.white);
		pomodoroBut.setFocusable(false);
		pomodoroBut.setFont(new Font("Inter", Font.BOLD, 20));
		pomodoroBut.setBorder(new EmptyBorder(0, 0, 0, 0));
		pomodoroBut.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        selectedTab = 3; 
		        mainBut.setBackground(mainColor);
		        tasksBut.setBackground(mainColor);
		        notesBut.setBackground(mainColor);
		        pomodoroBut.setBackground(secColor);
		        rappelBut.setBackground(mainColor);

		        TasksPage.resetTaskLay();
		        HomePage.resetTaskLay();
		        App.changePage(App.timerPanel);
		    }
		});
		
		
		rappelBut.setPreferredSize(new Dimension(300, 70));
		rappelBut.setText("Rappels");
		rappelBut.setBackground(mainColor);
		rappelBut.setForeground(Color.white);
		rappelBut.setFocusable(false);
		rappelBut.setFont(new Font("Inter", Font.BOLD, 20));
		rappelBut.setBorder(new EmptyBorder(0, 0, 0, 0));
		rappelBut.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        selectedTab = 4; 
		        mainBut.setBackground(mainColor);
		        tasksBut.setBackground(mainColor);
		        notesBut.setBackground(mainColor);
		        pomodoroBut.setBackground(mainColor);
		        rappelBut.setBackground(secColor);

		        TasksPage.resetTaskLay();
		        HomePage.resetTaskLay();
		        App.changePage(new RappelPanel()); 
		    }
		});
		
		
		JPanel navLayout = new JPanel();
		navLayout.setLayout(new GridLayout(5,1));
		navLayout.setBackground(secColor);
		navLayout.add(mainBut);
		navLayout.add(tasksBut);
		navLayout.add(notesBut);
		navLayout.add(pomodoroBut);
		navLayout.add(rappelBut);
		
		JPanel navBar = new JPanel();
		navBar.setBackground(mainColor);
		navBar.setPreferredSize(new Dimension(300, 300));
		navBar.add(navLayout);
				
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100, 700);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(mainColor);
		this.setTitle("ToDOX");
		
		this.add(header, BorderLayout.NORTH);
		this.add(navBar, BorderLayout.WEST);
	}
	
	public void initTab() {
		selectedTab = 0;
	}

	public static int getTab() {
		return selectedTab;
	}

	
}
