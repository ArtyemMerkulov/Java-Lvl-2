package homework2;

public enum DayOfWeek {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private String rusDef;

    DayOfWeek(String rusDef) {
        this.rusDef = rusDef;
    }

    public String getRusDef() {
        return rusDef;
    }
}
