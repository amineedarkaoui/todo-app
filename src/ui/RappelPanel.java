package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import metier.RappelNotificationService;
import metier.RappelOperation;

public class RappelPanel extends JPanel implements ActionListener{
	
	public static int rappelCount = 0;
	
	private JLabel ProgrammeeBoxCount;
	JLabel todayRappelCount;
	JButton saveButton;
	JTextField titleField;
	JTextArea contentArea;
	JSpinner dateSpinner;
	public RappelPanel() {
		this.setLayout(null);
		this.setBackground(new Color(0x161721));
		//------ Title ---------
		JLabel rappelTitle = new JLabel("Rappels");
		rappelTitle.setBounds(20, 60, 160, 50);
		rappelTitle.setFont(new Font("Inter", Font.BOLD, 24));
		rappelTitle.setForeground(new Color(0xFFFFFF));
		//-------------------------------------------
		
		//-------- start boxes
		JLabel todayBox = new JLabel();
		todayBox.setText("        "+"Aujourd'hui");
		todayBox.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		todayBox.setBounds(450, 100, 250, 100);
		todayBox.setFont(new Font("Inter", Font.BOLD, 25));
		todayBox.setForeground(Color.white);
		todayBox.setHorizontalAlignment(JTextField.CENTER);
		JLabel timeIconLabel = new JLabel();
		Image rezisedIcon = new ImageIcon("sablier.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon timeIcon = new ImageIcon(rezisedIcon);
		timeIconLabel.setHorizontalAlignment(JLabel.CENTER);
		timeIconLabel.setBounds(460, 165, timeIcon.getIconWidth(), timeIcon.getIconHeight());
		timeIconLabel.setFont(new Font(TOOL_TIP_TEXT_KEY, ALLBITS, 10));
		timeIconLabel.setIcon(timeIcon);
		todayRappelCount = new JLabel();
		todayRappelCount.setText("0");
		todayRappelCount.setBounds(500, 163, 60, 30);
		todayRappelCount.setForeground(Color.white);
		todayRappelCount.setFont(new Font("Inter", Font.BOLD, 33));
		//---------
		//--------
		JLabel ProgrammeeBox = new JLabel();
		ProgrammeeBox.setText("          "+"Programée");
		ProgrammeeBox.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		ProgrammeeBox.setBounds(110, 100, 250, 100);
		ProgrammeeBox.setFont(new Font("Inter", Font.BOLD, 25));
		ProgrammeeBox.setForeground(Color.white);
		ProgrammeeBox.setHorizontalAlignment(JTextField.LEFT);
		JLabel timeIconLabel2 = new JLabel();
		Image rezisedIcon2 = new ImageIcon("document.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon timeIcon2 = new ImageIcon(rezisedIcon2);
		timeIconLabel2.setHorizontalAlignment(JLabel.CENTER);
		timeIconLabel2.setBounds(120, 165, timeIcon2.getIconWidth(), timeIcon2.getIconHeight());
		timeIconLabel2.setFont(new Font(TOOL_TIP_TEXT_KEY, ALLBITS, 10));
		timeIconLabel2.setIcon(timeIcon2);
		ProgrammeeBoxCount = new JLabel();
		ProgrammeeBoxCount.setText("0");
		ProgrammeeBoxCount.setBounds(160, 163, 60, 30);
		ProgrammeeBoxCount.setForeground(Color.white);
		ProgrammeeBoxCount.setFont(new Font("Inter", Font.BOLD, 33));
		//--------- End boxes
		
		JLabel AjouterTitle = new JLabel("Ajouter un rappel");
		AjouterTitle.setBounds(30, 230, 350, 50);
		AjouterTitle.setFont(new Font("Inter", Font.BOLD, 24));
		AjouterTitle.setForeground(new Color(0xFFFFFF));
		
		
		JLabel titleLabel = new JLabel("Titre :");
        titleLabel.setBounds(110, 300, 80, 30);
        titleLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        titleLabel.setForeground(Color.white);

        titleField = new JTextField();
        titleField.setBounds(210, 295, 205, 35);
        titleField.setBackground(new Color(0x161721));
        titleField.setForeground(Color.gray);
        titleField.setText(" Entrer le Titre");
        titleField.setFont(new Font("Inter", Font.BOLD, 17));
        titleField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        titleField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (titleField.getText().equals(" Entrer le Titre")) {
					titleField.setText("");
					titleField.setForeground(Color.white);
				}
				
				if (contentArea.getText().length() == 0) {
					contentArea.setForeground(Color.gray);
					contentArea.setText(" De quoi se rappeler?");
					
				}
			}
		});

        JLabel contentLabel = new JLabel("Contenu :");
        contentLabel.setBounds(110, 340, 80, 30);
        contentLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        contentLabel.setForeground(Color.white);

        contentArea = new JTextArea();
        contentArea.setBounds(210, 350, 400, 100);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBackground(new Color(0x161721));
        contentArea.setBorder(BorderFactory.createLineBorder(Color.white));
        contentArea.setForeground(Color.gray);
        contentArea.setText(" De quoi se rappeler?");
        contentArea.setFont(new Font("Inter", Font.PLAIN, 17));
        contentArea.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
				if (contentArea.getText().equals(" De quoi se rappeler?")) {
					contentArea.setText("");
					contentArea.setForeground(Color.white);
				}
				
				if (titleField.getText().length() == 0) {
					titleField.setForeground(Color.gray);
					titleField.setText(" Entrer le Titre");
					
				}
			}
		});

        JLabel dateLabel = new JLabel("Date et heure :");
        dateLabel.setBounds(110, 470, 120, 30);
        dateLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        dateLabel.setForeground(Color.white);

        SpinnerDateModel spinnerDateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(spinnerDateModel);
        dateSpinner.setBounds(240, 470, 180, 30);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy HH:mm");
        dateSpinner.setEditor(dateEditor);
        

        saveButton = new JButton("Enregistrer");
        saveButton.setBounds(450, 470, 120, 30);
        saveButton.setBorder(BorderFactory.createLineBorder(Color.white));
        saveButton.setBackground(new Color(0x272A50));
		saveButton.setForeground(new Color(0xFFFFFF));
		saveButton.setFont(new Font("Inter", Font.BOLD, 18));
		saveButton.setFocusable(false);
		saveButton.addActionListener(this);


        
        
        this.add(AjouterTitle);
        this.add(titleLabel);
        this.add(titleField);
        this.add(contentLabel);
        this.add(contentArea);
        this.add(dateLabel);
        this.add(dateSpinner);
        this.add(saveButton);
		
		
		this.add(ProgrammeeBox);
		this.add(todayRappelCount);
		this.add(ProgrammeeBoxCount);
		this.add(timeIconLabel);
		this.add(timeIconLabel2);
		this.add(todayBox);
		this.add(rappelTitle);
		
		setInfo();
		RappelNotificationService notification = new RappelNotificationService();
		notification.start();
		
	}
	
	private void setInfo() {
		ResultSet rs1 = RappelOperation.getTodayRappel();
		try {
			if(rs1.next()) {
				try {
					todayRappelCount.setText(rs1.getInt("number")+"");
					rappelCount=rs1.getInt("number");
					if(rs1.getInt("number")!=0) {
						MainUI.rappelBut.setText("Rappel ( "+rs1.getInt("number")+" )");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs2 = RappelOperation.getProgrameeRappel();
		try {
			if(rs2.next()) {
				try {
					ProgrammeeBoxCount.setText(rs2.getInt("number")+"");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==saveButton) {
			if(!titleField.getText().isEmpty() && !contentArea.getText().isEmpty() && !titleField.equals(" Entrer le Titre") && !contentArea.equals(" De quoi se rappeler?")) {
				String titre = titleField.getText();
				String content = contentArea.getText();
				java.util.Date utilDate = (java.util.Date) dateSpinner.getValue();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());	
				RappelOperation.ajouterRappel(titre, content, sqlDate);
				setInfo();
				titleField.setText("");
				contentArea.setText("");
			}
		}
		
	}
}
