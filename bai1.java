import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

public class Clock implements Runnable {
    Timer timer = new Timer();
    JTextField enterHour = new JTextField();
    JLabel time = new JLabel("");
    JButton button = new JButton("Set Timezone");
    int timeZone;

    public Clock(String muiGio) {
        this.timeZone = Integer.parseInt(muiGio);
    }

    public void updateTime() {
        ZoneId customZone = ZoneId.of("GMT" + (timeZone >= 0 ? "+" : "") + timeZone);
        LocalTime currentTime = LocalTime.now(customZone);
        time.setText(String.format("%02d:%02d:%02d", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond()));
    }

    public void run() {
        JFrame jframe = new JFrame();
        jframe.setSize(300, 200);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.setLayout(new BorderLayout());

        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        clockPanel.add(time);
        jframe.add(clockPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(enterHour);
        controlPanel.add(button);
        jframe.add(controlPanel, BorderLayout.SOUTH);

        time.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        enterHour.setPreferredSize(new Dimension(100, 30));
        button.setPreferredSize(new Dimension(100, 30));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeZone = Integer.parseInt(enterHour.getText());
                updateTime();
            }
        });

        jframe.setVisible(true);

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                updateTime();
            }
        }, 0, 1000);

    }

    public static void main(String[] args) {
        Clock clock = new Clock("0"); // Múi giờ mặc định là 0
        Thread thread = new Thread(clock);
        thread.start();
    }
}
//