package data;

import data.Tool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.Month;

public class ToolTest {
    @Test
    public void testChargeWeekdays() {
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,true,true,false);
        var expectedCharge = 1.99F*5.0F;
        expectedCharge = Math.round(expectedCharge * 100.0F) / 100.0F;
        var startDate = LocalDate.of(2021,4,5);
        var actualCharge = tool.calculateCharge(startDate,5);
        Assertions.assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void testNoChargeWeekdays() {
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,false,true,true);
        var expectedCharge = 1.99F*0;
        expectedCharge = Math.round(expectedCharge * 100.0F) / 100.0F;
        var startDate = LocalDate.of(2021,4,5);
        var actualCharge = tool.calculateCharge(startDate,5);
        Assertions.assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void testChargeWeekends() {
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,true,true,true);
        var expectedCharge = 1.99F*5.0F;
        expectedCharge = Math.round(expectedCharge * 100.0F) / 100.0F;
        var startDate = LocalDate.of(2021,4,8);
        var actualCharge = tool.calculateCharge(startDate,5);
        Assertions.assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void testNoChargeWeekends() {
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,true,false,true);
        var expectedCharge = 1.99F*3.0F;
        expectedCharge = Math.round(expectedCharge * 100.0F) / 100.0F;
        var startDate = LocalDate.of(2021,4,8);
        var actualCharge = tool.calculateCharge(startDate,5);
        Assertions.assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void testChargeHolidays() {
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,true,true,true);
        var expectedCharge = 1.99F*5.0F;
        expectedCharge = Math.round(expectedCharge * 100.0F) / 100.0F;
        var startDate = LocalDate.of(2021,4,5);
        var actualCharge = tool.calculateCharge(startDate,5);
        Assertions.assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void testNoChargeHolidays() {
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,true,true,false);
        var expectedCharge = 1.99F*4;
        expectedCharge = Math.round(expectedCharge * 100.0F) / 100.0F;
        var startDate = LocalDate.of(2021,7,4);
        var actualCharge = tool.calculateCharge(startDate,5);
        Assertions.assertEquals(expectedCharge, actualCharge);

        startDate = LocalDate.of(2021, Month.SEPTEMBER,6);
        actualCharge = tool.calculateCharge(startDate,5);
        Assertions.assertEquals(expectedCharge, actualCharge);

    }

    @Test
    public void testCalculateChargeDays() {
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,true,true,false);
        var startDate = LocalDate.of(2021,7,4);
        var expectedDays = 4;
        var actualDays = tool.calculateChargeDays(startDate, 5);
        Assertions.assertEquals(expectedDays, actualDays);

        var expectedCharge = 1.99F*expectedDays;
        expectedCharge = Math.round(expectedCharge * 100.0F) / 100.0F;
        var actualCharge = tool.calculateCharge(actualDays);
        Assertions.assertEquals(expectedCharge, actualCharge);

    }
}
