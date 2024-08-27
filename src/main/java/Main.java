public class Main {
    public static void main(String[] args) {


        new Controller().startProject();
        Server server = new Server();
        new Client(server);
        new Client(server);

    }
}
