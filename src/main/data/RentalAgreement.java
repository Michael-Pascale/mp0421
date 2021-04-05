package data;

import java.time.LocalDate;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

/**
 * Rental Agreement storage object, for storing data related to a rental agreement,
 * with ability to display data in a human readable format
 *
 * @Author Michael Pascale
 */
public class RentalAgreement {

    private String toolCode, toolType, toolBrand;
    private int rentalDays, chargeDays;
    private float dailyRentalCharge;
    private float preDiscountCharge;
    private int discountPercent;
    private float discountAmount;
    private float finalCharge;
    private LocalDate checkoutDate, dueDate;
    private DecimalFormat priceFormatter;
    private DateTimeFormatter dateFormatter;

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    private void initializeFormatters() {
        this.priceFormatter = new DecimalFormat("###,###,##0.00");
        this.dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    }

    public RentalAgreement(String toolCode) {
        this.toolCode = toolCode;
        initializeFormatters();
    }

    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, int chargeDays, float dailyRentalCharge, float preDiscountCharge, int discountPercent, float discountAmount, float finalCharge, LocalDate checkoutDate, LocalDate dueDate) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.chargeDays = chargeDays;
        this.dailyRentalCharge = dailyRentalCharge;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        initializeFormatters();
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public float getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(float dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public float getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(float preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(float finalCharge) {
        this.finalCharge = finalCharge;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Tool code: " + this.toolCode + "\n";
        result += "Tool type: " + this.toolType + "\n";
        result += "Tool brand: " + this.toolBrand + "\n";
        result += "Rental days: " + this.rentalDays + "\n";
        result += "Checkout date: " + this.dateFormatter.format(this.checkoutDate) + "\n";
        result += "Due date: " + this.dateFormatter.format(this.dueDate) + "\n";
        result += "Daily rental charge: $" + this.priceFormatter.format(this.dailyRentalCharge) + "\n";
        result += "Charge days: " + this.chargeDays + "\n";
        result += "Pre-discount charge: $" + this.priceFormatter.format(this.preDiscountCharge) + "\n";
        result += "Discount percent: " + this.discountPercent + "%\n";
        result += "Discount amount: $" + this.priceFormatter.format(this.discountAmount) + "\n";
        result += "Final charge: $" + this.priceFormatter.format(this.finalCharge);
        return result;
    }
}
