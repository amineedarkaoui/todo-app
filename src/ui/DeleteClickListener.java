package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import metier.Task;


public class DeleteClickListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton) e.getSource();
        int id = (int) source.getClientProperty("id");
        Task.deleteTask(id);
        
        HomePage.resetTaskLay();
        App.changePage(new HomePage());
	}

}
