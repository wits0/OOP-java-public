package ru.nsu.wits.Task141.model;

/**
 * Класс, представляющий одну запись в зачетной книжке.
 */
public class Record {
    private final String subjectName;
    private final int semester;
    private final ControlType controlType;
    private final Integer grade;

    /**
     * Конструктор записи.
     *
     * @param subjectName название дисциплины
     * @param semester    номер семестра
     * @param controlType тип контроля
     * @param grade       оценка (может быть null для зачета)
     */
    public Record(String subjectName, int semester, ControlType controlType, Integer grade) {
        this.subjectName = subjectName;
        this.semester = semester;
        this.controlType = controlType;
        this.grade = grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getSemester() {
        return semester;
    }

    public ControlType getControlType() {
        return controlType;
    }

    public Integer getGrade() {
        return grade;
    }
}