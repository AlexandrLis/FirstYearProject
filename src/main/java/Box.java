import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Box extends JPanel {

    Logica logica;
    char[][] field;


    private static int cell_width;
    private static int cell_height;


    public Box(Logica logica) {

        this.logica = logica;
        field = logica.getField();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                userClickMethod(e);
            }
        });

    }




//  ПРИНИМАЕТ КООРДИНАТУ e ЩЕЛЧКА МЫШИ В ИГРОВОМ ПОЛЕ И ПЕРЕДАЕТ В SETTER LOGICA
    public void userClickMethod(MouseEvent e){

        logica.userClickMethod(e.getY(), e.getX());

        repaint();
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        printBox(g);
        printGamers(g);
        if(logica.userIsWinner()){
            printUserWin(g);
        }
        if(logica.computerIsWinner()){
            printComputerWin(g);
        }
    }

    public void printUserWin(Graphics g){

        try{
            g.setColor(Color.DARK_GRAY);
            g.fillRect(25, 150, 600, 300);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocation(525, 180);
            frame.setTitle("User Win");
            frame.setResizable(false);
            frame.setSize(620,400);
            frame.setVisible(true);
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/main/resources/picture/youwin.jpg"));

            frame.add(label);
        }catch (Exception e){

        }

    }

    public void printComputerWin(Graphics g){

        try{
            g.setColor(Color.DARK_GRAY);
            g.fillRect(25, 150, 600, 300);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocation(562, 220);
            frame.setTitle("Computer Win");
            frame.setResizable(false);
            frame.setSize(540,270);
            frame.setVisible(true);
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/main/resources/picture/you_lose.png"));

            frame.add(label);
        }catch (Exception e){

        }

    }


//  ПЕРЕРИСОВЫВАЕТ ДОСКУ ПРИ КАЖДОМ КЛИКЕ МЫШКОЙ
    public void printBox(Graphics g){
        cell_width = getWidth()/8;
        cell_height = getHeight()/8;

        g.setColor(Color.BLACK);

        for (int h = 0; h < 8; h++) {
            int x = h*cell_width;
            g.drawLine(0, x, getWidth(), x);
        }
        for (int w = 0; w < 8; w++) {
            int y = w*cell_height;
            g.drawLine(y, 0, y, getHeight());
        }


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.setColor(Color.GRAY);
                if(i%2 == 0 && j%2 != 0 || i%2 != 0 && j%2 == 0){
                    g.fillRect(i*cell_width, j*cell_height, cell_width, cell_height);
                }
            }
        }


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.setColor(Color.GREEN);
                if(logica.getField()[j][i] == '3'){
                    g.fillRect(i*cell_width, j*cell_height, cell_width, cell_height);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.setColor(Color.BLUE);
                if(logica.getField()[j][i] == '6'){
                    g.fillRect(i*cell_width, j*cell_height, cell_width, cell_height);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.setColor(Color.ORANGE);
                if(logica.getField()[j][i] == '9'){
                    g.fillRect(i*cell_width, j*cell_height, cell_width, cell_height);
                }
            }
        }
    }


//  ПЕРЕРИСОВЫВАЕТ ШАШКИ ИГРОКОВ ПРИ КАЖДОМ КЛИКЕ МЫШКИ
    public void printGamers(Graphics g){
//      ПОЛУЧАЕТ ПОЛОЖЕНИЕ ШАШЕК ИЗ ЛОГИКИ
        field = logica.getField();

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == '1'){
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(j*cell_width + 2, i*cell_height + 2, cell_width - 4, cell_height - 4);
                }
                else if(field[i][j] == '2' || field[i][j] == '3'){
                    g.setColor(Color.RED);
                    g.fillOval(j*cell_width + 2, i*cell_height + 2, cell_width - 4, cell_height - 4);
                }
                else if(field[i][j] == '4'){
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(j*cell_width + 2, i*cell_height + 2, cell_width - 4, cell_height - 4);
                    g.setColor(Color.WHITE);
                    g.fillOval(j*cell_width + 17, i*cell_height + 17, cell_width - 33, cell_height - 33);
                }
                else if(field[i][j] == '5' || field[i][j] == '6'){
                    g.setColor(Color.RED);
                    g.fillOval(j*cell_width + 2, i*cell_height + 2, cell_width - 4, cell_height - 4);
                    g.setColor(Color.WHITE);
                    g.fillOval(j*cell_width + 17, i*cell_height + 17, cell_width - 33, cell_height - 33);
                }
            }
        }
    }



}
