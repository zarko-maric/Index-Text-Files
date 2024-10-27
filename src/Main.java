import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPlease enter a number to choose an action: \n" +
                    "(1) - Specify indexed files and directories. \n" +
                    "(2) - Query files which contain a given word.\n" +
                    "(3) - Exit\n");

            String option = scanner.nextLine();

            if (option.equals("3")) {
                System.out.println("Exiting the program.");
                break;
            }

            System.out.println("Please enter the directory path:");
            String path = scanner.nextLine();

            File folder = new File(path);
            if (option.equals("1")) {
                IndexFiles(folder, 0);
            } else if (option.equals("2")) {
                QueryFiles(folder);
            } else {
                System.out.println("Please enter a valid option");
            }
        }
    }


    static void IndexFiles(final File folder, int level) {
        if (folder.exists() && folder.isDirectory()) {
            String indent = "    ".repeat(level);
            System.out.println(indent + "Folder: " + folder.getName());

            for (final File file : folder.listFiles()) {
                if (file.isFile()) {
                    System.out.println(indent + "    File: " + file.getName());
                }
            }

            for (final File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    IndexFiles(file, level + 1);
                }
            }
        } else {
            System.out.println("Invalid directory!");
        }
    }

    static void QueryFiles(File folder) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word to search for: ");
        String word = scanner.nextLine();

        searchWordInFiles(folder, word);
    }

    static void searchWordInFiles(File folder, String word) {
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    searchWordInFiles(file, word);
                } else if (file.isFile()) {
                    try {
                        String content = Files.readString(file.toPath());
                        if (content.contains(word)) {
                            System.out.println("Word found in: " + file.getPath());
                        }
                    } catch (IOException e) {
                        System.out.println("Could not read file: " + file.getPath());
                    }
                }
            }
        }
    }
}
