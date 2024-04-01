package ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class App {
	static MainUI main = new MainUI();
	static JPanel mainPage = new HomePage();
	static TimerPanel timerPanel = new TimerPanel();
	
	public static void main(String args[]) {
		main.initTab();
		main.add(mainPage, BorderLayout.CENTER);
		main.setVisible(true);
	}
	
	public static void changePage(JPanel page) {
		main.remove(mainPage);
	
		if (page instanceof HomePage) {	
			mainPage = (HomePage) page;
		}
		if (page instanceof AddNotePage) {
			mainPage = (AddNotePage) page;
		}
		if (page instanceof EditNotePage) {
			mainPage = (EditNotePage) page;
		}
		if (page instanceof TasksPage) {
			mainPage = (TasksPage) page;
		}
		if (page instanceof NotesPage) {
			mainPage = (NotesPage) page;
		}
		if(page instanceof TimerPanel) {
			mainPage = (TimerPanel) page;
		}
		if(page instanceof RappelPanel) {
			mainPage = (RappelPanel) page;
		}
		main.add(mainPage, BorderLayout.CENTER);
		
		main.revalidate();
	    main.repaint();
	}
}
