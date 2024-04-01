package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import metier.Note;
import metier.TodoList;

public class NotesPage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Color transparent = new Color(0,0,0,0);
	static Color mainColor = new Color(0x161721);
	static Color secColor = new Color(0x272A50);
	
	public NotesPage() {
		init();
	}
	
	public void init() {
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
		
		JLabel notes = new JLabel();
		notes.setText("Mes Notes");
		notes.setFont(new Font("Inter", Font.BOLD, 24));
		notes.setForeground(Color.white);
		
		JPanel notesTitLay = new JPanel();
		notesTitLay.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 20));
		notesTitLay.setBackground(mainColor);
		notesTitLay.setPreferredSize(new Dimension(50, 70));
		notesTitLay.add(notes);
		
		JLabel pinnedText = new JLabel();
		pinnedText.setText("épinglés");
		pinnedText.setPreferredSize(new Dimension(500, 24));
		pinnedText.setFont(new Font("Inter", Font.BOLD, 16));
		pinnedText.setForeground(Color.white);
		
		JLabel otherText = new JLabel();
		otherText.setText("autres");
		otherText.setPreferredSize(new Dimension(500, 24));
		otherText.setFont(new Font("Inter", Font.BOLD, 16));
		otherText.setForeground(Color.white);
		
		JPanel pinnedTitLay = new JPanel();
		pinnedTitLay.setLayout(new FlowLayout(FlowLayout.LEADING, 40, 5));
		pinnedTitLay.setBackground(mainColor);
		pinnedTitLay.setPreferredSize(new Dimension(100, 30));
		pinnedTitLay.add(pinnedText);
		
		JPanel otherTitLay = new JPanel();
		otherTitLay.setLayout(new FlowLayout(FlowLayout.LEADING, 40, 5));
		otherTitLay.setBackground(mainColor);
		otherTitLay.setPreferredSize(new Dimension(100, 30));
		otherTitLay.add(otherText);
		
		JPanel unpinnedNotesLay = new JPanel();
		unpinnedNotesLay.add(Box.createHorizontalStrut(30));
		JPanel pinnedNotesLay = new JPanel();
		pinnedNotesLay.add(Box.createHorizontalStrut(30));
		
		List<Note> notesList = Note.getNotes();
		List<Note> pinnedNotes = new ArrayList<Note>();
		List<Note> unpinnedNotes = new ArrayList<Note>();
		
		for (Note note : notesList) {
			if (note.isPin()) {
				pinnedNotes.add(note);
			}
			else {
				unpinnedNotes.add(note);
			}
		}
		
		for (Note note : pinnedNotes) {
			JLabel noteTitle = new JLabel();
			noteTitle.setText("<html><body style='width: 160px'>" + note.getTitle() + "</body></html>");
			noteTitle.setFont(new Font("Inter", Font.BOLD, 18));
			noteTitle.setForeground(Color.white);
			
			JPanel noteTitleLay = new JPanel();
			noteTitleLay.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
			noteTitleLay.setBackground(mainColor);
			noteTitleLay.add(noteTitle);
			
			ImageIcon xIcon = new ImageIcon("office-push-pin.png");
			ImageIcon pinIcon = new ImageIcon(xIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			ImageIcon pinnedicon = new ImageIcon("pin.png");
			ImageIcon pinnedIcon = new ImageIcon(pinnedicon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			JButton pinButt = new JButton();
			pinButt.setPreferredSize(new Dimension(32, 24));
			pinButt.setBackground(mainColor);
			pinButt.setBorder(BorderFactory.createEmptyBorder());
			pinButt.putClientProperty("id", note.getId());
			pinButt.putClientProperty("pinned", note.isPin());
			pinButt.setFocusable(false);
			if (note.isPin()) {
				pinButt.setIcon(pinnedIcon);
			}
			else {
				pinButt.setIcon(pinIcon);
			}
			pinButt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Note.updatePin((int)pinButt.getClientProperty("id"), (boolean)pinButt.getClientProperty("pinned"));
					App.changePage(new NotesPage());
				}
			});
			
			JPanel noteTopLay = new JPanel();
			noteTopLay.setLayout(new BorderLayout());
			noteTopLay.setBackground(mainColor);
			noteTopLay.add(noteTitleLay, BorderLayout.CENTER);
			noteTopLay.add(pinButt, BorderLayout.EAST);
			
			//-------------------------------------------------------------------------
			
			JLabel noteText = new JLabel();
			noteText.setText("<html><body style='width: 180px'>" + note.toString() + "</body></html>");
			noteText.setFont(new Font("Inter", Font.PLAIN, 14));
			noteText.setForeground(Color.white);
			
			JPanel noteTextLay = new JPanel();
			noteTextLay.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
			noteTextLay.setBackground(mainColor);
			noteTextLay.add(noteText); 
			
			//-------------------------------------------------------
			
			ImageIcon editicon = new ImageIcon("pencil (1).png");
			ImageIcon editIcon = new ImageIcon(editicon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			JButton editButt = new JButton();
			editButt.setPreferredSize(new Dimension(20, 20));
			editButt.setBackground(mainColor);
			editButt.setBorder(BorderFactory.createEmptyBorder());
			editButt.setIcon(editIcon);
			editButt.putClientProperty("id", note.getId());
			editButt.setFocusable(false);
			editButt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Note note = Note.getNote((int)editButt.getClientProperty("id"));
					App.changePage(new EditNotePage(note));
				}
			});
			
			ImageIcon trashicon = new ImageIcon("delete (3).png");
			ImageIcon trashIcon = new ImageIcon(trashicon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			JButton deleteButton = new JButton();
			deleteButton.setPreferredSize(new Dimension(20, 20));
			deleteButton.setBackground(mainColor);
			deleteButton.setBorder(BorderFactory.createEmptyBorder());
			deleteButton.setIcon(trashIcon);
			deleteButton.putClientProperty("id", note.getId());
			deleteButton.setFocusable(false);
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Note.deleteNote((int)deleteButton.getClientProperty("id"));
					App.changePage(new NotesPage());
				}
			});
			
			JPanel noteBottom = new JPanel();
			noteBottom.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
			noteBottom.setBackground(mainColor);
			noteBottom.add(editButt);
			noteBottom.add(deleteButton);
			
			//-----------------------------------------------------------------
			
			JPanel noteCont = new JPanel();
			noteCont.setLayout(new BorderLayout());
			noteCont.setBackground(mainColor);
			noteCont.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			noteCont.setPreferredSize(new Dimension(250, 240));
			noteCont.add(noteTopLay, BorderLayout.NORTH);
			noteCont.add(noteTextLay, BorderLayout.CENTER);
			noteCont.add(noteBottom, BorderLayout.SOUTH);
			
			pinnedNotesLay.add(noteCont);
		}
		
		for (Note note : unpinnedNotes) {
			JLabel noteTitle = new JLabel();
			noteTitle.setText("<html><body style='width: 160px'>" + note.getTitle() + "</body></html>");
			noteTitle.setFont(new Font("Inter", Font.BOLD, 18));
			noteTitle.setForeground(Color.white);
			
			JPanel noteTitleLay = new JPanel();
			noteTitleLay.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
			noteTitleLay.setBackground(mainColor);
			noteTitleLay.add(noteTitle);
			
			ImageIcon xIcon = new ImageIcon("office-push-pin.png");
			ImageIcon pinIcon = new ImageIcon(xIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			ImageIcon pinnedicon = new ImageIcon("pin.png");
			ImageIcon pinnedIcon = new ImageIcon(pinnedicon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			JButton pinButt = new JButton();
			pinButt.setPreferredSize(new Dimension(32, 24));
			pinButt.setBackground(mainColor);
			pinButt.setBorder(BorderFactory.createEmptyBorder());
			pinButt.putClientProperty("id", note.getId());
			pinButt.putClientProperty("pinned", note.isPin());
			pinButt.setFocusable(false);
			if (note.isPin()) {
				pinButt.setIcon(pinnedIcon);
			}
			else {
				pinButt.setIcon(pinIcon);
			}
			pinButt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Note.updatePin((int)pinButt.getClientProperty("id"), (boolean)pinButt.getClientProperty("pinned"));
					App.changePage(new NotesPage());
				}
			});
			
			JPanel noteTopLay = new JPanel();
			noteTopLay.setLayout(new BorderLayout());
			noteTopLay.setBackground(mainColor);
			noteTopLay.add(noteTitleLay, BorderLayout.CENTER);
			noteTopLay.add(pinButt, BorderLayout.EAST);
			
			//-------------------------------------------------------------------------
			
			JLabel noteText = new JLabel();
			noteText.setText("<html><body style='width: 180px'>" + note.toString() + "</body></html>");
			noteText.setFont(new Font("Inter", Font.PLAIN, 14));
			noteText.setForeground(Color.white);
			
			JPanel noteTextLay = new JPanel();
			noteTextLay.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
			noteTextLay.setBackground(mainColor);
			noteTextLay.add(noteText); 
			
			//-------------------------------------------------------
			
			ImageIcon editicon = new ImageIcon("pencil (1).png");
			ImageIcon editIcon = new ImageIcon(editicon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			JButton editButt = new JButton();
			editButt.setPreferredSize(new Dimension(20, 20));
			editButt.setBackground(mainColor);
			editButt.setBorder(BorderFactory.createEmptyBorder());
			editButt.setIcon(editIcon);
			editButt.putClientProperty("id", note.getId());
			editButt.setFocusable(false);
			editButt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Note note = Note.getNote((int)editButt.getClientProperty("id"));
					App.changePage(new EditNotePage(note));
				}
			});
			
			ImageIcon trashicon = new ImageIcon("delete (3).png");
			ImageIcon trashIcon = new ImageIcon(trashicon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			JButton deleteButton = new JButton();
			deleteButton.setPreferredSize(new Dimension(20, 20));
			deleteButton.setBackground(mainColor);
			deleteButton.setBorder(BorderFactory.createEmptyBorder());
			deleteButton.setIcon(trashIcon);
			deleteButton.putClientProperty("id", note.getId());
			deleteButton.setFocusable(false);
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Note.deleteNote((int)deleteButton.getClientProperty("id"));
					App.changePage(new NotesPage());
				}
			});
			
			JPanel noteBottom = new JPanel();
			noteBottom.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
			noteBottom.setBackground(mainColor);
			noteBottom.add(editButt);
			noteBottom.add(deleteButton);
			
			//-----------------------------------------------------------------
			
			JPanel noteCont = new JPanel();
			noteCont.setLayout(new BorderLayout());
			noteCont.setBackground(mainColor);
			noteCont.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			noteCont.setPreferredSize(new Dimension(250, 240));
			noteCont.add(noteTopLay, BorderLayout.NORTH);
			noteCont.add(noteTextLay, BorderLayout.CENTER);
			noteCont.add(noteBottom, BorderLayout.SOUTH);
			
			unpinnedNotesLay.add(noteCont);
		}
		
		unpinnedNotesLay.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 0));
		unpinnedNotesLay.setPreferredSize(new Dimension(400, 250));
		//unpinnedNotesLay.setBorder(BorderFactory.createLineBorder(Color.red));
		unpinnedNotesLay.setBackground(mainColor);
		
		pinnedNotesLay.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 0));
		pinnedNotesLay.setPreferredSize(new Dimension(400, 250));
		//unpinnedNotesLay.setBorder(BorderFactory.createLineBorder(Color.red));
		pinnedNotesLay.setBackground(mainColor);
		
		JPanel mainNotesLay = new JPanel();
		mainNotesLay.setLayout(new BoxLayout(mainNotesLay, BoxLayout.Y_AXIS));
		mainNotesLay.setBackground(mainColor);
		if (pinnedNotes.size() > 0) {
			mainNotesLay.add(pinnedTitLay);
			mainNotesLay.add(pinnedNotesLay);
			mainNotesLay.add(otherTitLay);
		}
		mainNotesLay.add(unpinnedNotesLay);
		
		JPanel notesLay = new JPanel();
		notesLay.setBackground(mainColor);
		notesLay.setLayout(new BorderLayout());
		notesLay.add(notesTitLay, BorderLayout.NORTH);
		notesLay.add(mainNotesLay, BorderLayout.CENTER);
				
        
		this.setLayout(new BorderLayout());
		this.setBackground(transparent);
		this.add(newNoteLay, BorderLayout.NORTH);
		this.add(notesLay, BorderLayout.CENTER);
	}
}
