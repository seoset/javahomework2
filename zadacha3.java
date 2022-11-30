// 3**** Дана json строка (можно сохранить в файл и читать из файла)
// [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
// {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
// {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
//
// Написать метод(ы), который распарсит json и, используя StringBuilder,
// создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
//
// Пример вывода:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.

package ru.geekbrains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task003 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        File file = new File("src/main/java/ru/geekbrains/jsonString2.json");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String jsonString = br.readLine();
//            System.out.println(jsonString);
            String[] rows = jsonString.split("},");
            String[][] matrix = new String[rows.length][];
            int r = 0;
            for (String row : rows) {
                row = row.replace("{", "");
                row = row.replace("}", "");
                row = row.replace("[", "");
                row = row.replace("]", "");
                row = row.replace(":", ",");
                row = row.replace(" ", "");
                row = row.replace("\"", "");
                matrix[r++] = row.split(",");
            }
//            System.out.println(Arrays.deepToString(matrix));
            for (int i = 0; i < matrix.length; i++) {
                sb.append("Студент ");
                sb.append(matrix[i][1]);
                sb.append(" получил ");
                sb.append(matrix[i][3]);
                sb.append(" по предмету ");
                sb.append(matrix[i][5]);
                if (i != matrix.length - 1) {
                    sb.append("\n");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        String resultString = new String(sb);
        System.out.println(resultString);
    }
}
