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
import metier.Task;
import metier.TodoList;

public class TasksPage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Color transparent = new Color(0,0,0,0);
	static Color mainColor = new Color(0x161721);
	static Color secColor = new Color(0x272A50);
	
	static JPanel mainTasksLay = new JPanel();
	static int listId = 0;

	public TasksPage() {
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
				App.changePage(new TasksPage());
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
					resetTaskLay();;
					App.changePage(new TasksPage());
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
				App.changePage(new TasksPage());
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
					App.changePage(new TasksPage());
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
		addUnchecked();
		mainTasksLay.add(addTaskLay);
		addChecked();
		
		JPanel tasksLay = new JPanel();
		tasksLay.setLayout(new BorderLayout());
		tasksLay.setBackground(mainColor);
		tasksLay.add(tachesLay, BorderLayout.NORTH);
		tasksLay.add(mainTasksLay, BorderLayout.CENTER);
		
		
		//--------------------------------------------------------------------------------------------------------------------------
		
		
		JLabel lists = new JLabel();
		lists.setText("Todo lists");
		lists.setFont(new Font("Inter", Font.BOLD, 24));
		lists.setForeground(Color.white);
		
		JPanel listsTitLay = new JPanel();
		listsTitLay.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 20));
		listsTitLay.setBackground(mainColor);
		listsTitLay.setPreferredSize(new Dimension(50, 70));
		listsTitLay.add(lists);
		
		JPanel mainListsLay = new JPanel();
		mainListsLay.setLayout(new BoxLayout(mainListsLay, BoxLayout.Y_AXIS));
		mainListsLay.setBackground(mainColor);
		
		List<TodoList> todoLists = TodoList.getLists();
		for (TodoList todoListItem : todoLists) {
			JLabel list1 = new JLabel();
			list1.setText(todoListItem.getTitle());
			list1.setFont(new Font("Inter", Font.PLAIN, 20));
			list1.setForeground(Color.white);
			
			ImageIcon xIcon = new ImageIcon("close (1).png");
			ImageIcon deleteIcon = new ImageIcon(xIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			
			JButton delete = new JButton();
			delete.setPreferredSize(new Dimension(20, 20));
			delete.setBackground(mainColor);
			delete.setBorder(BorderFactory.createEmptyBorder());
			delete.setIcon(deleteIcon);
			delete.putClientProperty("id", todoListItem.getId());
			delete.setFocusable(false);
			delete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
			        int id = (int) delete.getClientProperty("id");
			        TodoList.deleteList(id);
			        
			        resetTaskLay();
			        App.changePage(new TasksPage());
				}
			});
			
			JPanel listCont = new JPanel();
			listCont.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 0));
			listCont.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
			listCont.setBackground(mainColor);
			listCont.add(list1);
			listCont.add(Box.createHorizontalGlue());
			listCont.add(delete);
			
			mainListsLay.add(listCont);
		}
		
		//=========================================================================================
		
		JTextField addList = new JTextField();
		
		JButton saveList = new JButton();
		saveList.setBackground(secColor);
		saveList.setForeground(Color.white);
		saveList.setText("save");
		saveList.setBorder(BorderFactory.createLineBorder(Color.white));
		saveList.setPreferredSize(new Dimension(70, 30));
		saveList.setFocusable(false);
		saveList.setVisible(false);
		saveList.setFont(new Font("Inter", Font.PLAIN, 16));
		saveList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = addList.getText();
				if (text.length() > 0 && !text.equals(null)) {
					TodoList.addList(text);
					mainTasksLay.removeAll();
					App.changePage(new TasksPage());
				}
			}
		});
		
		JButton cancelList = new JButton();
		cancelList.setBackground(mainColor);
		cancelList.setForeground(Color.white);
		cancelList.setText("cancel");
		cancelList.setBorder(BorderFactory.createLineBorder(Color.white));
		cancelList.setPreferredSize(new Dimension(70, 30));
		cancelList.setFocusable(false);
		cancelList.setVisible(false);
		cancelList.setFont(new Font("Inter", Font.PLAIN, 16));
		cancelList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainTasksLay.removeAll();
				App.changePage(new TasksPage());
			}
		});
		
		addList.setText("Ajouter une liste ...");
		addList.setBackground(mainColor);
		addList.setForeground(Color.lightGray);
		addList.setFont(new Font("Inter", Font.PLAIN, 16));
		addList.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		addList.setPreferredSize(new Dimension(300, 30));
		addList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addList.setPreferredSize(new Dimension(380, 30));
				addList.setForeground(Color.white);
				addList.setFont(new Font("Inter", Font.PLAIN, 20));
				addList.setText("");
				saveList.setVisible(true);
				cancelList.setVisible(true);
			}
		});
		addList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = addList.getText();
				if (text.length() > 0 && !text.equals(null)) {
					TodoList.addList(text);
					mainTasksLay.removeAll();
					App.changePage(new TasksPage());
				}
			}
		});
		
		JPanel addListLay = new JPanel();
		addListLay.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 0));
		addListLay.setBackground(mainColor);
		addListLay.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
		addListLay.add(addList);
		addListLay.add(saveList);
		addListLay.add(cancelList);
		
		mainListsLay.add(addListLay);
		
		JPanel listsLay = new JPanel();
		listsLay.setLayout(new BorderLayout());
		listsLay.setBackground(mainColor);
		listsLay.add(listsTitLay, BorderLayout.NORTH);
		listsLay.add(mainListsLay, BorderLayout.CENTER);
		
		JPanel homeLay = new JPanel();
		homeLay.setBackground(secColor);
		homeLay.setLayout(new GridLayout(1, 2));
		homeLay.add(tasksLay);
		homeLay.add(listsLay);
        
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
				check.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
				        int id = (int)check.getClientProperty("id");
				        boolean checked = (boolean)check.getClientProperty("checked");
				        Task.updateCheck(id, checked);
				        
				        resetTaskLay();
				        App.changePage(new TasksPage());
					}
				});
				
				ImageIcon xIcon = new ImageIcon("close (1).png");
				ImageIcon deleteIcon = new ImageIcon(xIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
				
				JButton delete = new JButton();
				delete.setPreferredSize(new Dimension(20, 20));
				delete.setBackground(mainColor);
				delete.setBorder(BorderFactory.createEmptyBorder());
				delete.setIcon(deleteIcon);
				delete.putClientProperty("id", task.getId());
				delete.setFocusable(false);
				delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
				        int id = (int) delete.getClientProperty("id");
				        Task.deleteTask(id);
				        
				        resetTaskLay();
				        App.changePage(new TasksPage());
					}
				});
				
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
				check.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
				        int id = (int)check.getClientProperty("id");
				        boolean checked = (boolean)check.getClientProperty("checked");
				        Task.updateCheck(id, checked);
				        
				        resetTaskLay();
				        App.changePage(new TasksPage());
					}
				});
				
				ImageIcon xIcon = new ImageIcon("close (1).png");
				ImageIcon deleteIcon = new ImageIcon(xIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH));
				
				JButton delete = new JButton();
				delete.setPreferredSize(new Dimension(20, 20));
				delete.setBackground(mainColor);
				delete.setBorder(BorderFactory.createEmptyBorder());
				delete.setIcon(deleteIcon);
				delete.putClientProperty("id", task.getId());
				delete.setFocusable(false);
				delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
				        int id = (int) delete.getClientProperty("id");
				        Task.deleteTask(id);
				        
				        resetTaskLay();
				        App.changePage(new TasksPage());
					}
				});
				
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
