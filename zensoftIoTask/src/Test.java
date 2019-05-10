import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {

        // Осуществляем пользовательский ввод
        Scanner in = new Scanner(System.in);
        System.out.println("Приложение выполняет поиск в 2 режимах");
        System.out.println("Выберите в каком режиме хотите выполнить поиск. Введите число от 1 - 2");
        int num = in.nextInt();
        in.close();

        ArrayList<String> inputArray = new ArrayList<>();
        ArrayList<String> patternArray = new ArrayList<>();

        // Чтение из файла
        try (FileReader inputReader = new FileReader("input.txt");
             FileReader patternsReader = new FileReader("patterns.txt")) {

            Scanner input = new Scanner(inputReader);
            Scanner patterns = new Scanner(patternsReader);
            String inputText = new String(Files.readAllBytes(Paths.get("input.txt")), StandardCharsets.UTF_8);

            // Получаем список строк из файла patterns.txt
            while (patterns.hasNext()) {
                patternArray.add(patterns.nextLine());
            }

            // Полцчаем список строк из файла input.txt
            while (input.hasNext()) {
                inputArray.add(input.nextLine());
            }

            // Вход в цикл 1 - поиск строк котрые абсолютно точно совпадают с паттерном
            if (num == 1) {
                for (String s: patternArray) {
                    if (inputArray.contains(s)) {
                        System.out.println(s);
                    }
                }
            }

            // Вход в цикл 2 - поиск всех строк в которых есть совпадение с паттерном
            else if (num == 2) {
                for (String s : patternArray) {
                    Pattern pattern = Pattern.compile(s);
                    Matcher matcher = pattern.matcher(inputText);
                    if (matcher.find()) {
                        System.out.println(matcher.group());
                    }
                }
            } else {
                System.out.println("Вы ввели не верное число! Повторите попытку после запуска программы");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
