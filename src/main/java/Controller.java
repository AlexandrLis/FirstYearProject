public class Controller {


    public Controller() {

    }

    public void startProject(){
        Logica logica = new Logica();
        Graphica graphica = new Graphica(logica);
    }
}
