package ru.nsu.wits.Task141;

import ru.nsu.wits.Task141.model.ControlType;
import ru.nsu.wits.Task141.model.Record;
import ru.nsu.wits.Task141.service.GradeBook;

/**
 * Точка входа в программу. Демонстрация работы зачетной книжки.
 */
public class Main {

    /**
     * Главный метод программы.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();

        gradeBook.addRecord(new Record("Математический анализ", 1, ControlType.EXAM, 5));
        gradeBook.addRecord(new Record("Программирование", 1, ControlType.EXAM, 5));
        gradeBook.addRecord(new Record("История", 1, ControlType.DIFF_CREDIT, 4));
        gradeBook.addRecord(new Record("Физика", 2, ControlType.EXAM, 5));
        gradeBook.addRecord(new Record("Дискретная математика", 2, ControlType.EXAM, 5));
        gradeBook.addRecord(new Record("ООП", 3, ControlType.EXAM, 5));
        gradeBook.addRecord(new Record("Базы данных", 3, ControlType.DIFF_CREDIT, 5));
        gradeBook.addRecord(new Record("Физкультура", 3, ControlType.CREDIT, null));

        System.out.println("=== Электронная зачетная книжка ===");
        System.out.printf("sredni ball: %.2f%n", gradeBook.getAverageGrade());
        System.out.println("perevod na budget: " + (gradeBook.canTransferToBudget() ? "Доступен" : "Недоступен"));
        System.out.println("red dimplom: " + (gradeBook.canGetHonorsDiploma() ? "Возможен" : "Невозможен"));
        System.out.println("stipendiya povish: " + (gradeBook.canGetIncreasedScholarship() ? "Доступна" : "Недоступна"));
    }
}