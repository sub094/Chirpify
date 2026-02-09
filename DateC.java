import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class DateC implements Serializable {
    private static final long serialVersionUID = 1L;

    private int day;
    private int month;
    private int year;

    public DateC() {
        LocalDate now = LocalDate.now();
        this.day = now.getDayOfMonth();
        this.month = now.getMonthValue();
        this.year = now.getYear();
    }

    public DateC(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }

    public void setDay(int day) { this.day = day; }
    public void setMonth(int month) { this.month = month; }
    public void setYear(int year) { this.year = year; }

    public boolean isBefore(DateC other) {
        if (year != other.year) return year < other.year;
        if (month != other.month) return month < other.month;
        return day < other.day;
    }

    public static DateC today() {
        LocalDate now = LocalDate.now();
        return new DateC(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateC)) return false;
        DateC dateC = (DateC) o;
        return day == dateC.day &&
                month == dateC.month &&
                year == dateC.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
