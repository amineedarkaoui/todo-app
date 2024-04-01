package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

import metier.*;

import javax.swing.*;
import javax.swing.border.*;

public class HomePage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Color transparent = new Color(0,0,0,0);
	static Color mainColor = new Color(0x161721);
	static Color secColor = new Color(0x272A50);
	
	static JPanel mainTasksLay = new JPanel();
	static int listId = 0;

	public HomePage() {
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
				mainTasksLay.removeAll();
				App.changePage(new AddNotePage());
			}
		});
		
		JPanel newNoteLay = new JPanel();
		newNoteLay.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		newNoteLay.setBackground(transparent);
		newNoteLay.setPreferredSize(new Dimension(60,80));
		newNoteLay.add(newNote);
		
		JLabel taches = new JLabel();
		taches.setText("Mes Taches");
		taches.setFont(new Font("Inter", Font.BOLD, 24));
		taches.setForeground(Color.white);
		
		JPanel tachesLay = new JPanel();
		tachesLay.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 20));
		tachesLay.setBackground(mainColor);
		tachesLay.setPreferredSize(new Dimension(50, 70));
		tachesLay.add(taches);
		
		JComboBox<TodoList> todoList = new JComboBox<>(HomePage.comboList());
		todoList.setBackground(mainColor);
		todoList.setForeground(Color.white);
		todoList.setFocusable(false);
		int count = todoList.getItemCount();
		TodoList selectedItem = null;
		for (int i = 0; i<count; i++) {
			selectedItem = (TodoList)todoList.getItemAt(i);
			if (selectedItem.getId() == listId) {
				break;
			}
		}
		todoList.setSelectedItem(selectedItem);
		todoList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TodoList list = (TodoList) todoList.getSelectedItem();
				listId = list.getId();
				mainTasksLay.removeAll();
				App.changePage(new HomePage());
			}
		});
		
		JPanel comboLay = new JPanel();
		comboLay.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 10));
		comboLay.setBackground(mainColor);
		comboLay.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		//comboLay.setBorder(BorderFactory.createLineBorder(Color.green));
		comboLay.add(todoList);
		
		JTextField addTask = new JTextField();
		
		JButton save = new JButton();
		save.setBackground(secColor);
		save.setForeground(Color.white);
		save.setText("Save");
		save.setBorder(BorderFactory.createLineBorder(Color.white));
		save.setPreferredSize(new Dimension(70, 30));
		save.setFocusable(false);
		save.setVisible(false);
		save.setFont(new Font("Inter", Font.PLAIN, 16));
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = addTask.getText();
				if (text.length() > 0 && !text.equals(null)) {
					Task.addTask(text, listId);
					mainTasksLay.removeAll();
					App.changePage(new HomePage());
				}
			}
		});
		
		JButton cancel = new JButton();
		cancel.setBackground(mainColor);
		cancel.setForeground(Color.white);
		cancel.setText("cancel");
		cancel.setBorder(BorderFactory.createLineBorder(Color.white));
		cancel.setPreferredSize(new Dimension(70, 30));
		cancel.setFocusable(false);
		cancel.setVisible(false);
		cancel.setFont(new Font("Inter", Font.PLAIN, 16));
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainTasksLay.removeAll();
				App.changePage(new HomePage());
			}
		});
		
		addTask.setText("Ajouter une tache ...");
		addTask.setBackground(mainColor);
		addTask.setForeground(Color.lightGray);
		addTask.setFont(new Font("Inter", Font.PLAIN, 16));
		addTask.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		addTask.setPreferredSize(new Dimension(300, 30));
		addTask.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addTask.setPreferredSize(new Dimension(380, 30));
				addTask.setForeground(Color.white);
				addTask.setFont(new Font("Inter", Font.PLAIN, 20));
				addTask.setText("");
				save.setVisible(true);
				cancel.setVisible(true);
			}
		});
		addTask.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = addTask.getText();
				if (text.length() > 0 && !text.equals(null)) {
					Task.addTask(text, listId);
					mainTasksLay.removeAll();
					App.changePage(new HomePage());
				}
			}
		});
		
		JPanel addTaskLay = new JPanel();
		addTaskLay.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 0));
		addTaskLay.setBackground(mainColor);
		addTaskLay.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
		addTaskLay.add(addTask);
		addTaskLay.add(save);
		addTaskLay.add(cancel);
		
		mainTasksLay.setLayout(new BoxLayout(mainTasksLay, BoxLayout.Y_AXIS));
		mainTasksLay.setBackground(mainColor);
		mainTasksLay.add(comboLay);
		HomePage.addUnchecked();
		mainTasksLay.add(addTaskLay);
		HomePage.addChecked();
		
		JPanel tasksLay = new JPanel();
		tasksLay.setLayout(new BorderLayout());
		tasksLay.setBackground(mainColor);
		tasksLay.add(tachesLay, BorderLayout.NORTH);
		tasksLay.add(mainTasksLay, BorderLayout.CENTER);
		
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
		pinnedText.setText("épinglé");
		pinnedText.setPreferredSize(new Dimension(500, 24));
		pinnedText.setFont(new Font("Inter", Font.PLAIN, 20));
		pinnedText.setForeground(Color.white);
		
		JPanel mainNotesLay = new JPanel();
		
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
					mainTasksLay.removeAll();
					App.changePage(new HomePage());
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
					mainTasksLay.removeAll();
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
					mainTasksLay.removeAll();
					App.changePage(new HomePage());
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
			
			mainNotesLay.add(noteCont);
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
					mainTasksLay.removeAll();
					App.changePage(new HomePage());
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
					mainTasksLay.removeAll();
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
					mainTasksLay.removeAll();
					App.changePage(new HomePage());
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
			
			mainNotesLay.add(noteCont);
		}
		
		mainNotesLay.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
		mainNotesLay.setPreferredSize(new Dimension(400, 1300));
		//mainNotesLay.setBorder(BorderFactory.createLineBorder(Color.red));
		mainNotesLay.setBackground(mainColor);
		
		JPanel notesLay = new JPanel();
		notesLay.setBackground(mainColor);
		notesLay.setLayout(new BorderLayout());
		notesLay.add(notesTitLay, BorderLayout.NORTH);
		notesLay.add(mainNotesLay, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(notesLay);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
				
		JPanel homeLay = new JPanel();
		homeLay.setBackground(secColor);
		homeLay.setLayout(new GridLayout(1, 2));
		homeLay.add(tasksLay);
		homeLay.add(scrollPane);
        
		this.setLayout(new BorderLayout());
		this.setBackground(transparent);
		this.add(newNoteLay, BorderLayout.NORTH);
		this.add(homeLay, BorderLayout.CENTER);
	}
	
	public static TodoList[] comboList() {
		List<TodoList> lists = TodoList.getLists();
		TodoList all = new TodoList();
		all.setId(0);
		all.setTitle("All");
		lists.add(0, all);
		
		TodoList[] todoLists = new TodoList[lists.size()];
		lists.toArray(todoLists);
	
		
		return todoLists;
	}
	
	public static void addUnchecked() {
		List<Task> tasks;
		if (listId == 0) {
			tasks = Task.getTasks();
		} else {
			tasks = Task.getTasksByList(listId);
		}
		
		for (Task task : tasks) {
			if (!task.isChekcked()) {
				JLabel task1 = new JLabel();
				task1.setText(task.getTitle());
				task1.setFont(new Font("Inter", Font.PLAIN, 20));
				task1.setForeground(Color.white);
				
				JButton check = new JButton();
				check.setPreferredSize(new Dimension(20, 20));
				check.setBackground(mainColor);
				check.setBorder(BorderFactory.createLineBorder(Color.white, 1));
				check.putClientProperty("id", task.getId());
				check.putClientProperty("checked", false);
				check.addActionListener(new CheckClickListener());
				
				ImageIcon xIcon = new ImageIcon("close (1).png");
				ImageIcon deleteIcon = new ImageIcon(xIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
				
				JButton delete = new JButton();
				delete.setPreferredSize(new Dimension(20, 20));
				delete.setBackground(mainColor);
				delete.setBorder(BorderFactory.createEmptyBorder());
				delete.setIcon(deleteIcon);
				delete.putClientProperty("id", task.getId());
				delete.setFocusable(false);
				delete.addActionListener(new DeleteClickListener());
				
				JPanel taskCont = new JPanel();
				taskCont.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 0));
				taskCont.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
				taskCont.setBackground(mainColor);
				//taskCont.setBorder(BorderFactory.createLineBorder(Color.green));
				taskCont.add(check);
				taskCont.add(task1);
				taskCont.add(Box.createHorizontalGlue());
				taskCont.add(delete);
				
				mainTasksLay.add(taskCont);
			}
		}
	}
	
	public static void addChecked() {
		List<Task> tasks;
		if (listId == 0) {
			tasks = Task.getTasks();
		} else {
			tasks = Task.getTasksByList(listId);
		}
		
		for (Task task : tasks) {
			if (task.isChekcked()) {
				JLabel task1 = new JLabel();
				task1.setText("<html><strike>" + task.getTitle() + "</strike></html>");
				task1.setFont(new Font("Inter", Font.PLAIN, 20));
				task1.setForeground(Color.gray);
				
				ImageIcon checkIcon = new ImageIcon("checked.png");
				ImageIcon recheck = new ImageIcon(checkIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
				
				JButton check = new JButton(recheck);
				check.setPreferredSize(new Dimension(20, 20));
				check.setBackground(mainColor);
				check.setBorder(BorderFactory.createLineBorder(Color.white, 1));
				check.putClientProperty("id", task.getId());
				check.putClientProperty("checked", true);
				check.addActionListener(new CheckClickListener());
				
				ImageIcon xIcon = new ImageIcon("close (1).png");
				ImageIcon deleteIcon = new ImageIcon(xIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
				
				JButton delete = new JButton();
				delete.setPreferredSize(new Dimension(20, 20));
				delete.setBackground(mainColor);
				delete.setBorder(BorderFactory.createEmptyBorder());
				delete.setIcon(deleteIcon);
				delete.putClientProperty("id", task.getId());
				delete.setFocusable(false);
				delete.addActionListener(new DeleteClickListener());
				
				JPanel taskCont = new JPanel();
				taskCont.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 0));
				taskCont.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
				taskCont.setBackground(mainColor);
				taskCont.add(check);
				taskCont.add(task1);
				taskCont.add(Box.createHorizontalGlue());
				taskCont.add(delete);
				
				mainTasksLay.add(taskCont);
			}
		}
	}
	
	public static void resetTaskLay() {
		mainTasksLay.removeAll();
	}
}
