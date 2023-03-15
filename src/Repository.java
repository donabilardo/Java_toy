import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Repository implements Repos_Interface {

    private ToyMapper mapper = new ToyMapper();
    private FileOperation fileOperation;

    public Repository(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Toy> getAllToys() {
        List<String> lines = fileOperation.readAllLines();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    @Override
    public String CreateToy(Toy toy) {
        List<Toy> toys = getAllToys();
        int max = 0;
        for (Toy item : toys) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        toy.setId(id);
        toys.add(toy);
        List<String> lines = new ArrayList<>();
        for (Toy item : toys) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
        return id;
    }

    @Override
    public void deleteToy(List toys) {
        List<String> lines = new ArrayList<>();
        List<Toy> delToys = toys;
        System.out.println("Работает репозиторий:  маппер создает новыe lines для записи...");
        for (Toy item : delToys) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
        System.out.println("Удаление завершено успешно!");
    }

    public void save(Toy toy) {
        List<String> lines = new ArrayList<>();
        List<Toy> toys = getAllToys();
        for (Toy item : toys) {
            if (toy.getId().equals(item.getId())) {
                lines.add(mapper.map(toy));
            } else {
                lines.add(mapper.map(item));
            }
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public void game() {
        List<Toy> toys = getAllToys();
        List<Toy> prizes = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        for (Toy toy : toys) {
            int weight = Integer.parseInt(toy.getVin_weight());
            while (weight != 0) {
                prizes.add(toy); // засунем подарки столько раз в список сколько их вес в системе
                weight -= 1;
            }
        }
        Collections.shuffle(prizes); // перемешали список подарков
        Toy prizeToy = prizes.get(0);
        System.out.println("Мои поздравления! Вы выиграли игрушку: " + prizeToy.getName()); // вручаем победителю игрушку

        for (Toy toy : toys) { // уменьшаем количество игрушек на складе
            if (toy.getId().equals(prizeToy.getId())) {
                int toy_count = Integer.parseInt(toy.getCount());
                toy_count -= 1;
                String new_Count = String.format("%d", toy_count);
                toy.setCount(new_Count);
            }
        }

        for (Toy toy : toys) {   //   проверка склада на предмет пустых полок
            if (Integer.parseInt(toy.getCount()) > 0) {
                lines.add(mapper.map(toy));
            }
        }
        fileOperation.saveAllLines(lines);
    }
}
