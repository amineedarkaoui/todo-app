package metier;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RappelNotificationService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void start() { 
    	scheduler.scheduleAtFixedRate(this::verifierRappels, 0, 1, TimeUnit.MINUTES);
    }

    private void verifierRappels() {

        SwingUtilities.invokeLater(() -> {
            LocalDateTime maintenant = LocalDateTime.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            ResultSet rs = RappelOperation.getRappelsAujourdhui();
            try {
                while (rs.next()) {
                    LocalDateTime dateRappel = rs.getTimestamp("date").toLocalDateTime();
                    if (heureMinuteCompatibles(maintenant, dateRappel)) {
                        afficherNotification("Rappel: " + rs.getString("title"), dateFormat.format(dateRappel));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }
    
    private boolean heureMinuteCompatibles(LocalDateTime maintenant, LocalDateTime dateRappel) {
        return maintenant.getHour() == dateRappel.getHour() && maintenant.getMinute() == dateRappel.getMinute();
    }



    private void afficherNotification(String titre, String texte) {
    	if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("document.png");
            TrayIcon trayIcon = new TrayIcon(image, "Rappel App");
            trayIcon.setImageAutoSize(true);
            try {
                tray.add(trayIcon);
            } catch (AWTException ex) {
                return;
            }
            trayIcon.displayMessage(titre, texte, MessageType.INFO);
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
