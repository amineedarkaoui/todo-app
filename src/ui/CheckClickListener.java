package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import metier.Task;

public class CheckClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        int id = (int) source.getClientProperty("id");
        boolean checked = (boolean) source.getClientProperty("checked");
        Task.updateCheck(id, checked);
        
        HomePage.resetTaskLay();
        App.changePage(new HomePage());
    }
}
