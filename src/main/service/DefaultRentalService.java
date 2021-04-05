package service;

import data.RentalAgreement;
import data.Tool;
import data.ToolBox;
import service.exception.InvalidDiscountException;
import service.exception.InvalidDurationException;
import service.exception.RentalServiceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * Default implementation of the rental service. Provides base functionality,
 * with room for expandability.
 *
 * @Author Michael Pascale
 */
public class DefaultRentalService implements RentalService{
    private ToolBox toolBox;
    private DateTimeFormatter dateFormatter;
    private static final String DATE_FORMAT_SHORT = "M/d/yy";
    private static final String DATE_FORMAT_MEDIUM = "M/d/yyyy";

    public DefaultRentalService() {
        toolBox = new ToolBox();
        //The order that formatters are added matters.
        dateFormatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern(DATE_FORMAT_MEDIUM))
                .appendOptional(DateTimeFormatter.ofPattern(DATE_FORMAT_SHORT))
                .toFormatter();
    }

    @Override
    public RentalAgreement checkout(String toolCode, String date, int duration, int discountPercent) throws RentalServiceException {
        if (duration  < 1) {
            throw new InvalidDurationException("The duration of the rental must be more than one day." +
                    " The duration provided was: " + duration);
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidDiscountException("The discount must be a whole number between 0 and 100." +
                    " The discount provided was:" + discountPercent);
        }
        var tool = toolBox.getTool(toolCode);
        if (tool == null) {
            throw new RentalServiceException("The tool you are looking for with code " +
                    toolCode + " does not exist.");
        }
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date, dateFormatter);
        }catch(DateTimeParseException e) {
            throw new RentalServiceException("The date: " + date + " is in an unrecognized format." +
                    " Try m/d/yy or m/d/yyyy.");
        }

        return createAgreement(tool, parsedDate, duration, discountPercent);
    }

    /**
     * Generates a valid rental agreement given the parameters.
     *
     * @param tool the tool to be rented
     * @param date the checkout date for the rental
     * @param duration the duration for the rental
     * @param discountPercent the discount amount given by a clerk.
     * @return
     */
    private RentalAgreement createAgreement(Tool tool, LocalDate date, int duration, int discountPercent) {
        RentalAgreement agreement = new RentalAgreement(tool.getToolCode());
        agreement.setToolType(tool.getToolType());
        agreement.setToolBrand(tool.getBrand());
        agreement.setRentalDays(duration);
        agreement.setCheckoutDate(date);

        var chargeDays = tool.calculateChargeDays(date, duration);
        agreement.setChargeDays(chargeDays);

        agreement.setDailyRentalCharge(tool.getDailyCharge());

        var preDiscountCharge = tool.calculateCharge(chargeDays);
        agreement.setPreDiscountCharge(preDiscountCharge);

        agreement.setDiscountPercent(discountPercent);

        var discountPercentDecimal = discountPercent / 100.0F;
        var discountAmount = (discountPercentDecimal * preDiscountCharge);
        discountAmount = Math.round(discountAmount * 100.F) / 100.F;
        agreement.setDiscountAmount(discountAmount);

        agreement.setFinalCharge(preDiscountCharge-discountAmount);
        agreement.setDueDate(date.plusDays(duration));

        return agreement;

    }

    @Override
    public void addTool(Tool tool) {
        toolBox.addTool(tool);
    }

    @Override
    public void removeTool(String toolCode) {
        toolBox.removeTool(toolCode);
    }
}
