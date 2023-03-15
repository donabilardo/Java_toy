import java.util.Scanner;

public class View {
    private final Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }


    public void run() {
        Commands com = Commands.NONE;
        showHelp();
        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT)
                    return;
                switch (com) {
                    case CREATE:
                        create();
                        break;
                    case READ:
                        read();
                        break;
                    case DELETE:
                        delete(); 
                        break;  
                    case LIST:
                        list();
                        break;
                    case GAME:
                        game(); // что вызываем?
                        break;    
                    case HELP:
                        showHelp();
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Произошла ошибка " + ex.toString());
            }
        }
    }

    private void game() throws Exception{
        controller.game();
    }

    private void read() throws Exception {
        String id = prompt("Идентификатор игрушки: ");
        Toy toy = controller.readToy(id);
        System.out.println(toy);
    }

    private void delete() throws Exception {
        String toyid = prompt("Введите идентификатор игрушки для удаления: ");
        System.out.println(controller.readToy(toyid));
        String yes = prompt("Подтвердите удалениe командой YES, а если передумали, то введите любой символ. ")
                .toUpperCase();
        if (yes.equals("YES")) {
            System.out.println("Происходит удаление записи...");
            controller.deleteToy(toyid); // включаем удаление в контроллере
        } else {
            System.out.println("Удаление отменено.");
        }
    }

    private void list() throws Exception {
        for (Toy toy : controller.getToys()) {
            System.out.println(toy);
        }
    }

    private void create() throws Exception {
        String name = prompt("Наименование: ");
        String count = prompt("Количество: ");
        while(Integer.parseInt(count) < 1){
             count = prompt("Введите количество больше 0: ");
        }
        String vin_weight = prompt("Вероятность выпадения: ");
        controller.saveToy(new Toy(name, count, vin_weight));
    }

    private void showHelp() {
        System.out.println("Список команд:");
        for (Commands c : Commands.values()) {
            System.out.println(c);
        }
    }

    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
