import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Server extends JFrame {

    private final int LOCATION_X = 550;
    private final int LOCATION_Y = 150;
    private final int WIDTH = 300;
    private final int HEIGHT = 300;

    private boolean isWorking = false;

    private static final String REGISTRATION_PHRASE = "Registration new user: ";

    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    JButton clear = new JButton("Clear");
    JTextArea areaMessage;
    List<Client> listClient;

    public Server(){

        listClient = new ArrayList<>();

        setLocation(LOCATION_X, LOCATION_Y);
        setSize(WIDTH, HEIGHT);
        setTitle("Server");

        areaMessage = new JTextArea();
        add(areaMessage, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(start);
        panel.add(stop);
        panel.add(clear);
        add(panel, BorderLayout.SOUTH);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isWorking = true;
                printTextStartStop();
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isWorking = false;
                printTextStartStop();
            }
        });


        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaMessage.setText("");
                for (Client client : listClient) {
                    client.areaMessage.setText("");
                }
            }
        });


        setVisible(true);
    }

    public boolean startMessage(){
        return isWorking;
    }


    public void printTextStartStop(){
        if(isWorking){
            areaMessage.append("Server Working" + "\n");
            for (Client client : listClient) {
                client.areaMessage.append("Server Working" + "\n");
            }

        }else{
            areaMessage.append("Server Stopped" + "\n");
            for (Client client : listClient) {
                client.areaMessage.append("Server Stopped" + "\n");
            }
        }
    }

    public void yourMessage(String text, String name){
        areaMessage.append(name + ": " + text + "\n");
        for (Client client : listClient) {
            client.areaMessage.append(name + ": " + text + "\n");
        }
    }

    public void yourRegistration(String name){
        areaMessage.append(REGISTRATION_PHRASE + name + "\n");
        for (Client client : listClient) {
            client.areaMessage.append(REGISTRATION_PHRASE + name + "\n");
        }
    }

    public boolean getIsWorking(){
        return isWorking;
    }

    public void addClientList(Client client){
        listClient.add(client);
    }

}
