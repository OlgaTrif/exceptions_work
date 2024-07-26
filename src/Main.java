import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static final int FIELDS_NUM = 6;
    private static final String M = "m";
    private static final String F = "f";
    private static final String SPACE = " ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ввведите: Фамилия Имя Отчество датаРождения номерТелефона пол (через пробел)\n");
        String input = scanner.nextLine();
        scanner.close();

        String[] fields = input.split(SPACE);
        if (FIELDS_NUM != fields.length){
            System.out.println("Проверьте количество введённых данных. Вы ввели " + fields.length + " полей вместо 6.");
        }

        String surname = fields[0];
        String name = fields[1];
        String lastname = fields[2];

        LocalDate birthDate = LocalDate.now();
        try {
            birthDate = LocalDate.parse(fields[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            System.out.println("Проверьте, правильно ли вы ввели дату рождения: dd.mm.yyyy. Вы ввели: " + fields[3]);
        }

        long phoneNum = 0;
        try {
            phoneNum = Long.parseLong(fields[4]);
        } catch (NumberFormatException e) {
            System.out.println("Проверьте, правильно ли вы ввели номер. Вы ввели: " + fields[3]);
        }

        String sex = fields[5];
        if (!M.equals(sex) && !F.equals(sex)) {
            System.out.println("Проверьте, правильно ли вы ввели пол: m или f. Вы ввели: " + fields[3]);
        }

        String fileName = surname + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true)) ){
            //<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
            bw.write(surname + SPACE +
                    name + SPACE +
                    lastname + SPACE +
                    birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + SPACE +
                    phoneNum + SPACE +
                    sex);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }
}