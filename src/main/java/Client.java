import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client extends JFrame {

    private final int LOCATION_X = 250;
    private final int LOCATION_Y = 150;
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private String Name;

    JButton login = new JButton("Login");
    JButton send = new JButton("Send");
    JTextField userName;
    JTextField lineMessage;
    JTextArea areaMessage;

    public Client(Server server){
        setLocation(LOCATION_X, LOCATION_Y);
        setSize(WIDTH, HEIGHT);
        setTitle("Client");

        userName = new JTextField();
        lineMessage = new JTextField();
        areaMessage = new JTextArea();

        addClient(server);

        JPanel panelLogin = new JPanel(new GridLayout(1, 2));
        panelLogin.add(userName);
        panelLogin.add(login);
        add(panelLogin, BorderLayout.NORTH);

        JPanel panelSendMessage = new JPanel(new GridLayout(1, 2));
        panelSendMessage.add(lineMessage);
        panelSendMessage.add(send);
        add(panelSendMessage, BorderLayout.SOUTH);

        add(areaMessage, BorderLayout.WEST);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(server.getIsWorking()){
                    setName(userName.getText());
                    panelLogin.setVisible(false);
                    getRegistration(server, Name);
                }
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lineMessage.getText().length() != 0 && server.startMessage() && !(Name.equals(null))){
                    getMessage(server, Name);
                }
            }
        });

        setVisible(true);
    }

    public void getRegistration(Server server, String name){
        server.yourRegistration(Name);
    }

    public void getMessage(Server server, String name){

        server.yourMessage(lineMessage.getText(), Name);
    }


    public void addClient(Server server){
        server.addClientList(this);
    }

    public void setName(String textName){
        Name = textName;
    }

    public String getName(){
        return Name;
    }



}
