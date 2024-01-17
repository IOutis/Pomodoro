import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class App extends JFrame implements ActionListener {
    private JButton start, restart;
    private JLabel  time, title, seccLabel;
    private Timer timer;
    int[] s = {25*60,5*60};
    int j=0, i=0;
    JFrame f2;
    String[] quotes = {
        "Embrace the suck. It's in the struggle that you find your true strength.",
        "Don't count the days, make the days count. Push beyond your limits and redefine what's possible.",
        "Your mind is your most powerful weapon. Master it, and you'll conquer any challenge life throws at you.",
        "Get comfortable being uncomfortable. Growth and greatness reside on the other side of your comfort zone.",
        "The only easy day was yesterday. Every day is a new battle. Embrace the grind.",
        "Don't let the circumstances dictate your effort. Show up, give 100%, and leave no room for excuses.",
        "Be uncommon among uncommon. Rise above mediocrity and refuse to settle for anything less than your best.",
        "When you think you're done, you're only 40% into what your body is capable of doing. Embrace the 60% and redefine your limits.",
        "Your past does not define you. It prepares you. Use it as fuel to drive your future success.",
        "Don't fear failure. Fear being in the same place a year from now. Push forward relentlessly.",
        "Mental toughness is not about being the strongest; it's about being the most resilient.",
        "Be the hero of your own story. No one else is coming to save you.",
        "Take souls with your work ethic. Let your actions speak louder than your words.",
        "Greatness is earned, not given. It's forged through adversity, commitment, and an unrelenting will.",
        "Stay hard. Stay hungry. Stay alive. You have the power to change your narrative."
    };
    public int secc=15;
    public int seconds = s[0];
    private JTextArea distractions, quotesArea;
    public Timer secondTimer = new Timer(40000,this);
    public boolean f2on = true;
    public App() {
        seccLabel = new JLabel();
        secondTimer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                secc--;
                
                seccLabel.setText("Starting in : "+secc);
                seccLabel.setBounds(200,300,100,20);
                f2.add(seccLabel);
            }
        });
        secondTimer.start();
       if(secc<=0){
        mainWindow();
       }
       else{
        Window();
       }
        
        
        
    
}
    public void mainWindow(){
        setSize(600, 600);
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //setLayout(new BorderLayout());
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        start = new JButton("START / PAUSE");
        restart = new JButton("RESTART");
        
        title = new JLabel();
        Font font = new Font("New Times Roman", Font.BOLD, 18);
        title.setText("POMODORO TIMER");
        title.setFont(font);
        title.setBounds(200, 10, 200, 20); 
        add(title);
        time = new JLabel("00:00:00");
        time.setFont(font);
        restart.setBounds(90, 50, 160, 20);
        start.setBounds(290, 50, 160, 20);
        time.setBounds(250, 80, 160, 20);
        
        distractions = new JTextArea(100,10);
        distractions.setText("Distraction Log:\n\n The pomodoro = 25 mins timer then 5 mins break and repeat");
        distractions.setLineWrap(true);
        distractions.setBackground(Color.LIGHT_GRAY);
        distractions.setBounds(150,120,300,300);
        
        quotesArea = new JTextArea(30, 100);
        
        quotesArea.setForeground(Color.BLUE);
        quotesArea.setBounds(80,450,450,50);
        quotesArea.setText("When you think you're done, you're only 40% into what your body is capable of doing. Embrace the 60% and redefine your limits.");
        quotesArea.setLineWrap(true);
        quotesArea.setEditable(false);
        add(quotesArea);
        
        add(start);
        add(restart);
        
        add(time);
        add(distractions);
        
        start.addActionListener(this);
        restart.addActionListener(this);
        
        // Initialize the Timer with 1000 ms delay and the ActionListener
        timer = new Timer(1000, this);
    }
    public  void Window(){
        f2 = new JFrame();
        f2.setLayout(null);
        f2.setSize(600,600);
        f2.setVisible(true);
        JLabel warning = new JLabel();
        JLabel title = new JLabel();
        JLabel title2 = new JLabel();
        Font font2 = new Font("Serif", Font.BOLD, 20);
        title.setText("ACHIEVO");
        title2.setText("BY MMH ✌️");
        Font fontWarning = new Font("Arial", Font.BOLD, 11);
        warning.setFont(fontWarning);
        warning.setText("PS: I ONLY DID BECAUSE I AM BORED AS HECK !!!");
        title.setFont(font2);
        title2.setFont(font2);
        f2.add(title);
        f2.add(title2);
       // f2.add(warning);
        title.setBounds(200, 30, 200, 30);
        title2.setBounds(200, 70, 200, 30);
        warning.setBounds(200, 130, 300, 30);
        JButton special= new JButton("CLICK ME");
        special.setBounds(200, 200, 60, 20);
        special.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        
        Thread f2CloserThread = new Thread(() -> {
            try {
                Thread.sleep(15000);
                SwingUtilities.invokeLater(() -> {
                    f2.dispose();
                    //System.out.println("In thread");
                    mainWindow();
                    // Once f2 is closed, continue with the remaining part of the program
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        f2CloserThread.start();
    }
        
    


    public void break_time(){
        JLabel notify = new JLabel();
        notify.setText("Break time!!!");
        add(notify);
        seconds = 5;
        timer.start();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            // Start or stop the timer based on the button click
            if (!timer.isRunning()) {
                timer.start();
            } else {
                timer.stop();
            }
        } else if (e.getSource() == timer) {
            // Update the time label
            if(seconds>0){
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            int secs = seconds % 60;
            if(secs%5==0){j++;}
            if(j>=quotes.length){j=0;}
            String quote = quotes[j];
            quotesArea.setText(quote);
            
            
            
            time.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));

            // Increment the seconds counter
            seconds--;
            }
            else{
                try{
                i=(i+1)%2;
                seconds = s[i];
                System.out.println(i);
                timer.start();
                }
                catch(Exception x){
                    System.out.println(x);
                }
            }
        } else if(e.getSource() == restart){
            seconds = s[i];
        }
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new App();
        });
    }
}


