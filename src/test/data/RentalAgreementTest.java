package data;

import data.RentalAgreement;
import data.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class RentalAgreementTest {
    @Test
    public void testToString() {
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,true,true,false);
        RentalAgreement agreement = new RentalAgreement(tool.getToolCode());
        agreement.setToolBrand(tool.getBrand());
        agreement.setToolType(tool.getToolType());
        agreement.setRentalDays(5);
        agreement.setChargeDays(4);
        agreement.setCheckoutDate(LocalDate.of(2021, Month.JULY, 2));
        agreement.setDueDate(LocalDate.of(2021, Month.JULY, 7));
        agreement.setDailyRentalCharge(tool.getDailyCharge());

        var preDiscount = tool.calculateCharge(agreement.getCheckoutDate(), agreement.getRentalDays());
        preDiscount = Math.round(preDiscount * 100.0F) / 100.0F;
        agreement.setPreDiscountCharge(preDiscount);

        var discountPercent = .1F;
        agreement.setDiscountPercent((int)(discountPercent*100));

        var discountAmount = preDiscount * (discountPercent);
        discountAmount = Math.round(discountAmount * 100.0F) / 100.0F;
        agreement.setDiscountAmount(discountAmount);
        agreement.setFinalCharge(preDiscount-discountAmount);

        var expected = "Tool code: LADW\n" +
                "Tool type: Ladder\n" +
                "Tool brand: Werner\n" +
                "Rental days: 5\n" +
                "Checkout date: 07/02/2021\n" +
                "Due date: 07/07/2021\n" +
                "Daily rental charge: $1.99\n" +
                "Charge days: 4\n" +
                "Pre-discount charge: $7.96\n" +
                "Discount percent: 10%\n" +
                "Discount amount: $0.80\n" +
                "Final charge: $7.16";
        var actual = agreement.toString();
        Assertions.assertEquals(expected, actual);
    }
}
