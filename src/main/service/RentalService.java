package service;

import data.RentalAgreement;
import data.Tool;
import service.exception.RentalServiceException;

/**
 * Rental service, provides functionality for adding and removing tools available for rent,
 * and for renting out a tool, generating a valid rental agreement
 *
 * @Author Michael Pascale
 */
public interface RentalService {
    /**
     * Generates a rental agreement for a given tool and duration, with added discount
     *
     * @param toolCode The tool code of the tool desired for rental
     * @param date The starting date for the rental
     * @param duration The duration of the rental, including weekends and holidays
     * @param discountPercent The discount given at checkout
     * @return a rental agreement with all of its data populated
     * @throws RentalServiceException If invalid duration or discountPercent
     */
    public RentalAgreement checkout(String toolCode, String date,
                                    int duration, int discountPercent) throws RentalServiceException;

    /**
     * Adds a tool to be made available for rent
     *
     * @param tool The tool being added
     */
    public void addTool(Tool tool);

    /**
     * Removes a tool to be made no longer available for rent
     *
     * @param toolCode The tool code for the tool desired for removal
     */
    public void removeTool(String toolCode);
}
