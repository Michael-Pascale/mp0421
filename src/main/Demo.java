import data.RentalAgreement;
import data.Tool;
import service.DefaultRentalService;
import service.RentalService;
import service.exception.InvalidDiscountException;
import service.exception.InvalidDurationException;
import service.exception.RentalServiceException;

import java.util.Scanner;

/**
 * Demo of the rental service functionality
 *
 * @Author Michael Pascale
 */
public class Demo {
    public static void main(String[] args) {
        //Initialize rental service and demo data
        RentalService rentalService = new DefaultRentalService();
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

        Scanner inputScanner = new Scanner(System.in);

        System.out.println("---------------------------------------------------------\n" +
                             "Welcome to the rental service Demo. Within this demo,\n" +
                             "you can input some information to check out a tool\n" +
                             "for personal use. You can close this demo at any time\n" +
                             "by pressing Ctrl-C. Note to press enter to finalize\n" +
                             "selections.\n" +
                           "---------------------------------------------------------");
        System.out.print("Please enter the tool code you would like to rent out: ");
        var toolCode = inputScanner.nextLine();

        System.out.print("Please enter the date you would like to checkout. Use format (m/d/yy or m/d/yyyy): ");
        var checkoutDate = inputScanner.nextLine();

        System.out.print("Please enter the duration you would like the rental, in days. Please input a whole number: ");
        var duration = inputScanner.nextInt();

        System.out.print("Please enter the discount you would like, as a whole number representing the percent discount: ");
        var discount = inputScanner.nextInt();

        try {
            RentalAgreement agreement = rentalService.checkout(toolCode, checkoutDate, duration, discount);
            System.out.println("Here is a copy of the rental agreement, for your records: \n\n");
            System.out.println(agreement.toString());
        }catch(InvalidDiscountException e) {
            System.out.println("Unfortunately, the discount you provided, " + discount +
                    ", is invalid. Please run the program to try again. Below will be a detailed\n" +
                    "stack trace of the event.");
            e.printStackTrace();
        }catch(InvalidDurationException e) {
            System.out.println("Unfortunately, the rental duration you provided, " +
                    duration + ", is not valid. Please run the program to try again. Below\n" +
                    "will be a detailed stack trace of the event.");
            e.printStackTrace();
        }catch(RentalServiceException e) {
            System.out.println("Unfortunately, it appears that there was an error. Please\n" +
                    "consult the detailed stack trace below for more information.");
            e.printStackTrace();
        }

        System.out.println("\n\nThank you for trying this rental service!");
    }
}
