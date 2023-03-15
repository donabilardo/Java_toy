import java.util.List;

public interface Repos_Interface {
    List<Toy> getAllToys();
    String CreateToy(Toy toy);
    void deleteToy(List toys);
    void game();
}
