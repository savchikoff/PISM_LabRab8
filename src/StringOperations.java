import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class StringOperations {
    private static ArrayList<String> stringsList = new ArrayList<>();
    private static final int STATIC_COLLECTION_SIZE = 5; // Задаем статическую размерность коллекции

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавление строки");
            System.out.println("2. Удаление строки");
            System.out.println("3. Поиск одинаковых элементов с подсчетом совпадений");
            System.out.println("4. Выгрузка в XML-файл");
            System.out.println("5. Реверс строк");
            System.out.println("6. Статистика по символам");
            System.out.println("7. Поиск подстроки");
            System.out.println("8. Инициализация из файла и вывод на экран");
            System.out.println("9. Сравнение элементов");
            System.out.println("10. Длины строк в коллекции");
            System.out.println("11. Добавление с учетом статической размерности");
            System.out.println("12. Вывод коллекции");
            System.out.println("0. Выход");

            choice = scanner.nextInt();
            scanner.nextLine(); // Чтобы считать символ новой строки после ввода числа

            switch (choice) {
                case 1:
                    System.out.println("Введите строку для добавления:");
                    String newString = scanner.nextLine();
                    addString(newString);
                    break;
                case 2:
                    System.out.println("Введите индекс строки для удаления:");
                    int indexToRemove = scanner.nextInt();
                    removeString(indexToRemove);
                    break;
                case 3:
                    findDuplicateStrings();
                    break;
                case 4:
                    exportToXML();
                    break;
                case 5:
                    reverseStrings();
                    break;
                case 6:
                    charactersStatistics();
                    break;
                case 7:
                    System.out.println("Введите подстроку для поиска:");
                    String substring = scanner.nextLine();
                    findSubstring(substring);
                    break;
                case 8:
                    initializeFromFileAndDisplay();
                    break;
                case 9:
                    System.out.println("Введите первый индекс:");
                    int firstIndex = scanner.nextInt();
                    System.out.println("Введите второй индекс:");
                    int secondIndex = scanner.nextInt();
                    compareInnerObjects(firstIndex, secondIndex);
                    break;
                case 10:
                    lengthsOfStrings();
                    break;
                case 11:
                    System.out.println("Введите строку для добавления с учетом статической размерности:");
                    String newStaticString = scanner.nextLine();
                    addWithStaticSize(newStaticString);
                    break;
                case 12:
                    displayStrings();
                case 0:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void addString(String newString) {
        stringsList.add(newString);
        System.out.println("Строка добавлена: " + newString);
    }

    private static void removeString(int indexToRemove) {
        if (indexToRemove >= 0 && indexToRemove < stringsList.size()) {
            String removedString = stringsList.remove(indexToRemove);
            System.out.println("Удалена строка с индексом " + indexToRemove + ": " + removedString);
        } else {
            System.out.println("Неверный индекс строки для удаления.");
        }
    }

    private static void findDuplicateStrings() {
        Map<String, Integer> occurrences = new HashMap<>();
        for (String str : stringsList) {
            occurrences.put(str, occurrences.getOrDefault(str, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            System.out.println("Строка '" + entry.getKey() + "' встречается " + entry.getValue() + " раз(а)");
        }
    }

    private static void exportToXML() {
        try {
            FileWriter writer = new FileWriter("strings.xml");
            writer.write("<strings>\n");
            for (String str : stringsList) {
                writer.write("\t<string>" + str + "</string>\n");
            }
            writer.write("</strings>");
            writer.close();
            System.out.println("Коллекция строк успешно выгружена в XML-файл 'strings.xml'");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл.");
            e.printStackTrace();
        }
    }

    private static void reverseStrings() {
        for (int i = 0; i < stringsList.size(); i++) {
            stringsList.set(i, new StringBuilder(stringsList.get(i)).reverse().toString());
        }
        System.out.println("Строки в коллекции были реверснуты.");
    }

    private static void charactersStatistics() {
        Map<Character, Integer> charCount = new HashMap<>();
        for (String str : stringsList) {
            for (char c : str.toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            System.out.println("'" + entry.getKey() + "' встречается " + entry.getValue() + " раз(а)");
        }
    }

    private static void findSubstring(String substring) {
        for (String str : stringsList) {
            if (str.contains(substring)) {
                System.out.println("Подстрока '" + substring + "' найдена в строке: " + str);
            }
        }
    }

    private static void initializeFromFileAndDisplay() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                stringsList.add(line);
            }
            reader.close();
            System.out.println("Коллекция успешно инициализирована из файла 'input.txt'");

            displayStrings();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла.");
            e.printStackTrace();
        }
    }

    private static void compareInnerObjects(int firstIndex, int secondIndex) {
        if (firstIndex >= 0 && firstIndex < stringsList.size() && secondIndex >= 0 && secondIndex < stringsList.size()) {
            String firstString = stringsList.get(firstIndex);
            String secondString = stringsList.get(secondIndex);

            if (firstString.equals(secondString)) {
                System.out.println("Элементы с индексами " + firstIndex + " и " + secondIndex + " равны.");
            } else {
                System.out.println("Элементы с индексами " + firstIndex + " и " + secondIndex + " НЕ равны.");
            }
        } else {
            System.out.println("Неверные индексы для сравнения элементов.");
        }
    }

    private static void lengthsOfStrings() {
        if (stringsList.isEmpty()) {
            System.out.println("Коллекция строк пуста.");
            return;
        }

        System.out.println("Длины строк в коллекции:");
        for (int i = 0; i < stringsList.size(); i++) {
            String str = stringsList.get(i);
            System.out.println("Индекс: " + i + ", Строка: '" + str + "', Длина: " + str.length());
        }
    }


    private static void addWithStaticSize(String newStaticString) {
        if (stringsList.size() < STATIC_COLLECTION_SIZE) {
            stringsList.add(newStaticString);
            System.out.println("Строка добавлена с учетом статической размерности.");
        } else {
            stringsList.remove(0); // Удаляем первый элемент, если коллекция достигла статического размера
            stringsList.add(newStaticString);
            System.out.println("Статическая размерность достигнута, старая строка удалена, новая добавлена.");
        }
    }

    private static void displayStrings() {
        System.out.println("Содержимое коллекции строк:");
        if (stringsList.isEmpty()){
            System.out.println("Коллекция пуста");
        } else {
            for (String str : stringsList) {
                System.out.println(str);
            }
        }
    }
}