package data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

/**
 * Tool object, stores tool related data and operations for charges related
 * to the RentalService
 *
 * @Author Michael Pascale
 */
public class Tool {

    private final String toolCode;
    private final String toolType;
    private final String brand;
    private final float dailyCharge;
    private final boolean weekdayCharge, weekendCharge, holidayCharge;

    public Tool(String toolCode, String toolType, String brand, float dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public float getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public int calculateChargeDays(LocalDate date, int duration) {
        int days = 0;
        for (int i = 0; i < duration; i++) {
            var day = date.getDayOfWeek();
            var isHolidayToday = isHoliday(date);
            if ((isHolidayToday && isHolidayCharge()) ||
                    (!isHolidayToday && isWeekday(day) && isWeekdayCharge()) ||
                    (!isHolidayToday && !isWeekday(day) && isWeekendCharge())) {
                days++;
            }
            date = date.plusDays(1);
        }
        return days;
    }

    /**
     * Calculates the total charge for the tool during a given period.
     *
     * @param date - The start date
     * @param duration - The amount of days to be charged
     * @return the total value charged for this tool based on the date range from date and duration
     */
    public float calculateCharge(LocalDate date, int duration) {
        return Math.round(this.dailyCharge * this.calculateChargeDays(date, duration) * 100.0F) / 100.0F;
    }

    /**
     * Calculates the total charge for the tool given the days to be charged
     *
     * @param chargeDays the number of days specifically to be charged, regardless of actual date
     * @return the total charge given the charge days, rounded to the nearest cents
     */
    public float calculateCharge(int chargeDays) {
        return Math.round(this.dailyCharge * chargeDays * 100.0F) / 100.0F;
    }


    /**
     * Determines if a given day is a week day (M-F)
     *
     * @param day A given day
     * @return true if the given day is a week day, false otherwise
     */
    private boolean isWeekday(DayOfWeek day) {
        return day.getValue() < DayOfWeek.SATURDAY.getValue();
    }

    /**
     * Determines if a given day is a holiday
     * Holidays include: Nearest weekday to July 4th, Labor Day(First monday in September).
     *
     * @param date A given date
     * @return true if the given date is a holiday, false otherwise
     */
    private boolean isHoliday(LocalDate date) {
        switch (date.getMonth()) {
            case JULY:
                return date.equals(weekEndToWeekday(
                        LocalDate.of(date.getYear(), Month.JULY, 4)));
            case SEPTEMBER:
                var firstOfMonth = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1);
                var firstMonday = firstOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
                return date.equals(firstMonday);
            default:
                return false;
        }
    }

    /**
     * Transforms a given LocalDate into a weekday by rounding to the nearest weekday
     * Saturday becomes the Friday before, Sunday becomes the Monday After.
     *
     * @param date The date to be transformed
     * @return A transformed date. If the date is not a weekend, returns original date provided.
     */
    private LocalDate weekEndToWeekday(LocalDate date) {
        var day = date.getDayOfWeek();
        if (isWeekday(day)) {
            return date;
        }
        if (day == DayOfWeek.SATURDAY) {
            date = date.minusDays(1);
        }else {
            date = date.plusDays(1);
        }
        return date;
    }

}
