package ru.nsu.wits.Task141.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.nsu.wits.Task141.model.ControlType;
import ru.nsu.wits.Task141.model.Record;

/**
 * Класс электронной зачетной книжки, реализующий бизнес-логику.
 */
public class GradeBook {
    private final List<Record> records;

    /**
     * Конструктор по умолчанию. Инициализирует пустой список записей.
     */
    public GradeBook() {
        this.records = new ArrayList<>();
    }

    /**
     * Добавляет новую запись в зачетную книжку.
     *
     * @param record запись для добавления
     */
    public void addRecord(Record record) {
        records.add(record);
    }

    /**
     * Вычисляет текущий средний балл за все время обучения.
     * Учитываются только дисциплины с числовой оценкой.
     *
     * @return средний балл или 0.0, если оценок нет
     */
    public double getAverageGrade() {
        return records.stream()
                .filter(r -> r.getGrade() != null)
                .mapToInt(Record::getGrade)
                .average()
                .orElse(0.0);
    }

    /**
     * Проверяет возможность перевода с платной на бюджетную форму обучения.
     * Условие: отсутствие троек на экзаменах за последние две сессии.
     *
     * @return true, если перевод возможен
     */
    public boolean canTransferToBudget() {
        List<Record> exams = records.stream()
                .filter(r -> r.getControlType() == ControlType.EXAM)
                .sorted(Comparator.comparingInt(Record::getSemester).reversed())
                .toList();

        if (exams.isEmpty()) return false;

        int lastSession = exams.get(0).getSemester();
        int secondLastSession = -1;
        for (Record exam : exams) {
            if (exam.getSemester() < lastSession) {
                secondLastSession = exam.getSemester();
                break;
            }
        }

        final int finalSecondLast = secondLastSession;
        return exams.stream()
                .filter(r -> r.getSemester() == lastSession || r.getSemester() == finalSecondLast)
                .noneMatch(r -> r.getGrade() != null && r.getGrade() == 3);
    }

    /**
     * Проверяет возможность получения красного диплома.
     * Учитывает прогноз, если ВКР еще не защищена.
     *
     * @return true, если диплом с отличием возможен
     */
    public boolean canGetHonorsDiploma() {
        boolean hasSatisfactory = records.stream()
                .anyMatch(r -> r.getGrade() != null && r.getGrade() == 3);
        if (hasSatisfactory) return false;

        boolean badThesis = records.stream()
                .anyMatch(r -> r.getControlType() == ControlType.THESIS
                        && r.getGrade() != null && r.getGrade() < 5);
        if (badThesis) return false;

        Map<String, Record> finalGrades = getFinalGrades();
        long totalGrades = finalGrades.values().stream().filter(r -> r.getGrade() != null).count();
        long excellentGrades = finalGrades.values().stream().filter(r -> r.getGrade() != null && r.getGrade() == 5).count();

        if (totalGrades == 0) return false;

        return (double) excellentGrades / totalGrades >= 0.75;
    }

    /**
     * Проверяет возможность получения повышенной стипендии в текущем семестре.
     * Условие: все оценки в последнем семестре 4 или 5.
     *
     * @return true, если стипендия возможна
     */
    public boolean canGetIncreasedScholarship() {
        int maxSemester = records.stream()
                .mapToInt(Record::getSemester)
                .max()
                .orElse(0);

        if (maxSemester == 0) return false;

        return records.stream()
                .filter(r -> r.getSemester() == maxSemester && r.getGrade() != null)
                .allMatch(r -> r.getGrade() >= 4);
    }

    /**
     * Вспомогательный метод для получения последних оценок по каждому предмету.
     * Используется для расчета красного диплома.
     */
    private Map<String, Record> getFinalGrades() {
        return records.stream()
                .collect(Collectors.toMap(
                        Record::getSubjectName,
                        r -> r,
                        (r1, r2) -> r1.getSemester() > r2.getSemester() ? r1 : r2
                ));
    }
}
