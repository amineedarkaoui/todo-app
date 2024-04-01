package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import metier.Note;

public class AddNotePage extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Color transparent = new Color(0,0,0,0);
	static Color mainColor = new Color(0x161721);
	static Color secColor = new Color(0x272A50);

	public AddNotePage() {
		JLabel pageTitle = new JLabel();
		pageTitle.setText("Ecrire une note");
		pageTitle.setFont(new Font("Inter", Font.BOLD, 24));
		pageTitle.setForeground(Color.white);
		
		JTextArea text = new JTextArea();
		
		JTextField title = new JTextField();
		title.setText("Entrer le Titre");
		title.setFont(new Font("Inter", Font.BOLD, 24));
		title.setBackground(mainColor);
		title.setBorder(BorderFactory.createEmptyBorder());
		//title.setBorder(BorderFactory.createLineBorder(Color.green));
		title.setPreferredSize(new Dimension(530, 50));
		title.setForeground(Color.gray);
		title.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (title.getText().equals("Entrer le Titre")) {
					title.setText("");
					title.setForeground(Color.white);
				}
				
				if (text.getText().length() == 0) {
					text.setForeground(Color.gray);
					text.setText("De quoi pensez-vous?");
					
				}
			}
		});
		
		JPanel titleCont = new JPanel();
		titleCont.setLayout(new FlowLayout(FlowLayout.LEADING, 30 , 30));
		titleCont.setBackground(transparent);
		titleCont.add(title);
		
		text.setText("De quoi pensez-vous?");
		text.setFont(new Font("Inter", Font.PLAIN, 18));
		text.setBackground(mainColor);
		title.setBorder(BorderFactory.createEmptyBorder());
		//text.setBorder(BorderFactory.createLineBorder(Color.green));
		text.setPreferredSize(new Dimension(530, 400));
		text.setForeground(Color.gray);
		text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (text.getText().equals("De quoi pensez-vous?")) {
					text.setText("");
					text.setForeground(Color.white);
				}
				
				if (title.getText().length() == 0) {
					title.setForeground(Color.gray);
					title.setText("Entrer le Titre");
					
				}
			}
		});
		
		JPanel textCont = new JPanel();
		textCont.setLayout(new FlowLayout(FlowLayout.LEADING, 30 , 0));
		textCont.setBackground(transparent);
		textCont.add(text);
		
		JButton save = new JButton();
		save.setBackground(secColor);
		save.setForeground(Color.white);
		save.setText("Save");
		save.setBorder(BorderFactory.createEmptyBorder());
		save.setPreferredSize(new Dimension(80, 40));
		save.setFocusable(false);
		save.setFont(new Font("Inter", Font.PLAIN, 18));
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean isTitleEmpty = (title.getText().length() == 0 || title.getText().equals("Entrer le Titre"));
				boolean isTextEmpty = (text.getText().length() == 0 || text.getText().equals("De quoi pensez-vous?"));
				String titleContent = "";
				String textContent = "";
				
				if (!(isTitleEmpty && isTextEmpty)) {
					if (!isTitleEmpty) {
						titleContent = title.getText();
					}
					if (!isTextEmpty) {
						textContent = text.getText();
					}
					
					Note.addNote(titleContent, textContent);
					HomePage.mainTasksLay.removeAll();
					TasksPage.resetTaskLay();
					if (MainUI.getTab() == 0) 
						App.changePage(new HomePage());
					else if (MainUI.getTab() == 1)
						App.changePage(new TasksPage());
					else if (MainUI.getTab() == 2)
						App.changePage(new NotesPage());
					else
						App.changePage(App.timerPanel);
				}
			}
		});
		
		JButton cancel = new JButton();
		cancel.setBackground(mainColor);
		cancel.setForeground(Color.white);
		cancel.setText("cancel");
		cancel.setBorder(BorderFactory.createEmptyBorder());
		cancel.setPreferredSize(new Dimension(80, 40));
		cancel.setFocusable(false);
		cancel.setFont(new Font("Inter", Font.PLAIN, 18));
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (MainUI.getTab() == 0) 
					App.changePage(new HomePage());
				else if (MainUI.getTab() == 1)
					App.changePage(new TasksPage());
				else 
					App.changePage(new NotesPage());
			}
		});
		
		JPanel ButtCont = new JPanel();
		ButtCont.setLayout(new FlowLayout(FlowLayout.TRAILING, 20 , 20));
		ButtCont.setBackground(transparent);
		//ButtCont.setPreferredSize(new Dimension(100, 100));
		ButtCont.add(cancel);
		ButtCont.add(save);

		JPanel noteCont = new JPanel();
		noteCont.setLayout(new BorderLayout());
		noteCont.setBackground(transparent);
		noteCont.setBorder(BorderFactory.createLineBorder(Color.white));
		noteCont.setPreferredSize(new Dimension(600, 600));
		noteCont.add(titleCont, BorderLayout.NORTH);
		noteCont.add(textCont, BorderLayout.CENTER);
		noteCont.add(ButtCont, BorderLayout.SOUTH);
		
		JPanel noteLay = new JPanel();
		noteLay.setLayout(new FlowLayout(FlowLayout.CENTER, 10 ,10));
		noteLay.setBackground(transparent);
		noteLay.add(noteCont);
		
		JPanel titleLay = new JPanel();
		titleLay.setLayout(new FlowLayout(FlowLayout.CENTER, 10 ,10));
		titleLay.setBackground(transparent);
		titleLay.setPreferredSize(new Dimension(100, 70));
		titleLay.add(Box.createRigidArea(new Dimension(1500, 20)));
		titleLay.add(pageTitle);
		
		setLayout(new BorderLayout());
		add(titleLay, BorderLayout.NORTH);
		add(noteLay, BorderLayout.CENTER);
		setBackground(transparent);
	}
}
