import java.util.List;

public class Controller {
    private final Repos_Interface repos_Interface;

    public Controller(Repos_Interface repos_Interface) {
        this.repos_Interface = repos_Interface;
    }

    public void saveToy(Toy toy) throws Exception {
        repos_Interface.CreateToy(toy);
    }

    public void game() throws Exception {
        repos_Interface.game();
    }

    public Toy readToy(String toyId) throws Exception {
        List<Toy> toys = repos_Interface.getAllToys();
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                return toy;
            }
        }
        throw new Exception("Toy not found");
    }

    public Toy deleteToy(String toyId) throws Exception { // удаление из списка игрушек
        List<Toy> toys = repos_Interface.getAllToys();
        System.out.println("Работает контроллер: запись удаляется из списка...");
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                toys.remove(toy);
                repos_Interface.deleteToy(toys); // вызываем интрефейс репозиторий
                return toy;
            }
        }
        throw new Exception("Toy not found");
    }

    public List<Toy> getToys() throws Exception {
        return repos_Interface.getAllToys();
    }
}
