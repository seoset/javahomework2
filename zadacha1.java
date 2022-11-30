//1. Дана строка sql-запроса "select * from students where ".
// Сформируйте часть WHERE этого запроса, используя StringBuilder.
// Данные для фильтрации приведены ниже в виде json строки.
// Если значение null, то параметр не должен попадать в запрос.
// Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
//        Ответ: "select * from students where name = 'Ivanov' and country = 'Russia' and city = 'Moscow'"
//        для приведенного примера

package ru.geekbrains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task002 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("select * from students where ");
        File file = new File("src/main/java/ru/geekbrains/jsonString.json");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String jsonString = br.readLine();
//            System.out.println(jsonString);
            jsonString = jsonString.replace("{", "");
            jsonString = jsonString.replace("}", "");
            jsonString = jsonString.replace(":", ",");
            jsonString = jsonString.replace(" ", "");
            jsonString = jsonString.replace("\"", "");
//            System.out.println(jsonString);
            String[] arr = jsonString.split(",");
//            for (String i : arr) {
//                System.out.println(i);
//            }
            for (int i = 0; i < arr.length; i+=2) {
                if (!arr[i + 1].equals("null")) {
//                    System.out.println(arr[i + 1]);
                    if (i > 0) {
                        if (!arr[i-1].equals("null")) {
                            sb.append(" and ");
                        }
                    }
                    sb.append(arr[i]);
                    sb.append(" = ");
                    sb.append("'");
                    sb.append(arr[i+1]);
                    sb.append("'");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        String resultString = new String(sb);
        System.out.println(resultString);
    }
}
