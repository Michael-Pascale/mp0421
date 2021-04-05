package service;

import data.RentalAgreement;
import data.Tool;
import service.exception.InvalidDiscountException;
import service.exception.InvalidDurationException;
import service.exception.RentalServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RentalServiceTest {

    private static RentalService rentalService;

    @BeforeAll
    public static void initializeService() {
        rentalService = new DefaultRentalService();
        Tool tool = new Tool("LADW","Ladder","Werner",
                1.99F,true,true,false);
        rentalService.addTool(tool);
        tool = new Tool("CHNS","Chainsaw","Stihl",
                1.49F,true,false,true);
        rentalService.addTool(tool);
        tool = new Tool("JAKR","Jackhammer","Ridgid",
                2.99F,true,false,false);
        rentalService.addTool(tool);
        tool = new Tool("JAKD","Jackhammer","DeWalt",
                2.99F,true,false,false);
        rentalService.addTool(tool);
    }

    @Test
    public void testInvalidDiscount() {
        Assertions.assertThrows(InvalidDiscountException.class, () -> {
           rentalService.checkout("JAKR", "9/3/15", 5, 101);
        });
    }

    @Test
    public void testIndependenceDayNoCharge() throws RentalServiceException{
        RentalAgreement agreement = rentalService.checkout("LADW", "7/2/20",3,10);
        Assertions.assertEquals(3.98F, agreement.getPreDiscountCharge());
        Assertions.assertEquals(.40F, agreement.getDiscountAmount());
        Assertions.assertEquals(2, agreement.getChargeDays());
        Assertions.assertEquals(3.58F, agreement.getFinalCharge());
    }

    @Test
    public void testIndependanceDayChargeWeekendNoCharge() throws RentalServiceException {
        RentalAgreement agreement = rentalService.checkout("CHNS", "7/2/15",5,25);
        Assertions.assertEquals(4.47F, agreement.getPreDiscountCharge());
        Assertions.assertEquals(1.12F, agreement.getDiscountAmount());
        Assertions.assertEquals(3, agreement.getChargeDays());
        Assertions.assertEquals(3.35F, agreement.getFinalCharge());
    }

    @Test
    public void testLaborDayNoCharge() throws RentalServiceException {
        RentalAgreement agreement = rentalService.checkout("JAKD", "9/3/15",6,0);
        Assertions.assertEquals(8.97F, agreement.getPreDiscountCharge());
        Assertions.assertEquals(0, agreement.getDiscountAmount());
        Assertions.assertEquals(3, agreement.getChargeDays());
        Assertions.assertEquals(8.97F, agreement.getFinalCharge());
    }

    @Test
    public void testIndependenceDayNoChargeWeekendNoCharge() throws RentalServiceException {
        RentalAgreement agreement = rentalService.checkout("JAKR", "7/2/15",9,0);
        Assertions.assertEquals(17.94F, agreement.getPreDiscountCharge());
        Assertions.assertEquals(0, agreement.getDiscountAmount());
        Assertions.assertEquals(6, agreement.getChargeDays());
        Assertions.assertEquals(17.94F, agreement.getFinalCharge());
    }

    @Test
    public void testIndependenceDayNoChargeWeekendNoChargeWithDiscount() throws RentalServiceException {
        RentalAgreement agreement = rentalService.checkout("JAKR", "7/2/20",4,50);
        Assertions.assertEquals(2.99F, agreement.getPreDiscountCharge());
        Assertions.assertEquals(1.5F, agreement.getDiscountAmount());
        Assertions.assertEquals(1, agreement.getChargeDays());
        Assertions.assertEquals(1.49F, agreement.getFinalCharge());
    }

    @Test
    public void testInvalidDuration() {
        Assertions.assertThrows(InvalidDurationException.class, () -> {
            rentalService.checkout("JAKR", "9/3/15", 0, 0);
        });
    }

    @Test
    public void testNoTool() {
        Assertions.assertThrows(RentalServiceException.class, () -> {
            rentalService.checkout("NONE", "9/3/15", 5, 101);
        });
    }

    @Test
    public void testRemoveTool() {
        //remove tool
        rentalService.addTool(new Tool("LADD","Ladder","Werner",
                1.99F,true,true,false));
        Assertions.assertDoesNotThrow(() -> {
            rentalService.checkout("LADD", "9/3/15", 5, 0);
        });
        rentalService.removeTool("LADD");
        Assertions.assertThrows(RentalServiceException.class, () -> {
            rentalService.checkout("LADD", "9/3/15", 5, 0);
        });
    }

    @Test
    public void testInvalidDate() {
        Assertions.assertThrows(RentalServiceException.class, () -> {
            rentalService.checkout("LADW", "09/33/1915", 5, 0);
        });
    }

}
