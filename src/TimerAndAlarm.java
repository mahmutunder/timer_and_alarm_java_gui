import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimerAndAlarm extends JFrame {

    private JLabel hourLabel;
    private SimpleDateFormat hourFormat;
    private String hour;

    private JLabel minuteLabel;
    private SimpleDateFormat minuteFormat;
    private String minute;

    private JLabel secondLabel;
    private SimpleDateFormat secondFormat;
    private String second;
    private JTextField setHour;
    private JTextField setMinute;
    private JButton setAlarm;

    private JTextField setted;
    private File musicFile;
    private AudioInputStream audioStream;
    private Clip clip;
    private int h;
    private int m;
    private int localH;
    private int localM;
    private int localS;


    public TimerAndAlarm() {
        setHour = new JTextField(10);
        setMinute = new JTextField(10);
        setAlarm = new JButton("Set Alarm");

        hourLabel = new JLabel();
        hourFormat = new SimpleDateFormat("k");
        hourLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        hourLabel.setBorder(BorderFactory.createTitledBorder(null, "Hour", 0, 0, new Font("Dialog", Font.BOLD, 12), Color.CYAN));
        hourLabel.setBackground(new Color(78, 18, 96));
        hourLabel.setForeground(Color.white);
        hourLabel.setOpaque(true);
        setted = new JTextField(10);
        setted.setEditable(false);

        musicFile = new File("Elfida.wav");
        try {
            audioStream = AudioSystem.getAudioInputStream(musicFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {

            e.printStackTrace();
        }


        minuteLabel = new JLabel();
        minuteFormat = new SimpleDateFormat("mm");
        minuteLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        minuteLabel.setBorder(BorderFactory.createTitledBorder(null, "Min", 0, 0, new Font("Dialog", Font.BOLD, 12), Color.CYAN));
        minuteLabel.setBackground(new Color(78, 18, 96));
        minuteLabel.setForeground(Color.white);
        minuteLabel.setOpaque(true);


        secondLabel = new JLabel();
        secondFormat = new SimpleDateFormat("ss");
        secondLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        secondLabel.setBorder(BorderFactory.createTitledBorder(null, "Sec", 0, 0, new Font("Dialog", Font.BOLD, 12), Color.CYAN));
        secondLabel.setBackground(new Color(78, 18, 96));
        secondLabel.setForeground(Color.white);
        secondLabel.setOpaque(true);

        setAlarm.addActionListener(new setAlarmNow());


        this.add(hourLabel);
        this.add(minuteLabel);
        this.add(secondLabel);
        this.add(setHour);
        this.add(setMinute);
        this.add(setAlarm);
        this.add(setted);


        this.setTitle("Timer and Alarm");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(250, 200);
        this.setLayout(new FlowLayout());
        this.setLocation(700, 300);
        this.setVisible(true);
        eachSecond();

    }

    public void Clock(int hr, int mn) {
        if (localH == hr && localM == mn && localS == 00) {
            setted.setText("cizz");

            clip.start();


        }


    }

    private void eachSecond() {

        while (true) {


            hour = hourFormat.format(Calendar.getInstance().getTime());
            hourLabel.setText(hour);

            minute = minuteFormat.format(Calendar.getInstance().getTime());
            minuteLabel.setText(minute);

            second = secondFormat.format(Calendar.getInstance().getTime());
            secondLabel.setText(second);
            // get from cpu
            localH = Integer.parseInt(hour);
            localM = Integer.parseInt(minute);
            localS = Integer.parseInt(second);
            Clock(h, m);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    public class setAlarmNow implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (e.getSource() == setAlarm) {
                String getH = setHour.getText();
                String getM = setMinute.getText();

                // set user
                h = Integer.parseInt(getH);
                m = Integer.parseInt(getM);
                setted.setText(getH + ":" + getM);


            }
        }


    }

}
