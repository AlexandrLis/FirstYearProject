import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphica extends JFrame {

    Logica logica;

    private static final int GRAPHICA_LOCATION_X = 500;
    private static final int GRAPHICA_LOCATION_Y = 20;
    private static final int GRAPHICA_WIDTH = 664;
    private static final int GRAPHICA_HEIGHT = 713;


    Box box;
//    UserWin userWin;

    JButton btnStart = new JButton("Start Game");
    JButton btnExit = new JButton("Exit");

    public Graphica(Logica logica) {
        this.logica = logica;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(GRAPHICA_LOCATION_X, GRAPHICA_LOCATION_Y);
        setSize(GRAPHICA_WIDTH, GRAPHICA_HEIGHT);
        setTitle("Shaski");
        setResizable(false);

        setVisible(true);

        JPanel buttons = new JPanel(new GridLayout(1, 2));
        buttons.add(btnStart);
        buttons.add(btnExit);
        add(buttons, BorderLayout.SOUTH);

        box = new Box(logica);
        add(box);
        box.setVisible(false);

//        userWin = new UserWin(logica);
//        add(userWin);
//        userWin.setVisible(false);


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                box.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }


}
