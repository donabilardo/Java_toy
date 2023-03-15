public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperator("toys.txt");
        Repos_Interface repos_Interface = new Repository(fileOperation);
        Controller controller = new Controller(repos_Interface);
        View view = new View(controller);
        view.run();
    }
}