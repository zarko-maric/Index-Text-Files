import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter a number to choose an action: \n" +
                " (1) - Specify indexed files and directories. \n " +
                " (2) - Query files which contain a given word.\n");
        String option = System.console().readLine();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the directory path:");
        String path = scanner.nextLine();

        File folder = new File(path);
        if (folder.exists() && folder.isDirectory()) {
            IndexFiles(folder);
        } else {
            System.out.println("Invalid directory!");
        }

        if(option.equals("1")) {
            IndexFiles(folder);
        }else if(option.equals("2")) {
            QueryFiles();
        }else System.out.println("Please enter a valid option");

    }

    static void IndexFiles(final File folder){
        for(final File file : folder.listFiles()){
            if(file.isDirectory()){
                IndexFiles(file);
            }else {
                System.out.println(file.getName());
            }
        }
    }

    static void QueryFiles(){

    }
}
