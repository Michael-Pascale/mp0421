package data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Toolbox object, stores tools by tool code and has passthrough for charge calculation
 *
 * @Author Michael Pascale
 */
public class ToolBox {
    private Map<String, Tool> toolbox;

    public ToolBox() {
        toolbox = new HashMap<>();
    }

    public void addTool(Tool tool) {
        toolbox.put(tool.getToolCode(), tool);
    }

    public Tool getTool(String toolCode) {
        return toolbox.get(toolCode);
    }

    public void removeTool(String toolCode) {
        toolbox.remove(toolCode);
    }

    public float calculateCharge(String toolCode, LocalDate startDate, int duration) {
        return this.getTool(toolCode).calculateCharge(startDate, duration);
    }

    public float calculateCharge(String toolCode, int chargeDays) {
        return this.getTool(toolCode).calculateCharge(chargeDays);
    }

    public int calculateChargeDays(String toolCode, LocalDate startDate, int duration) {
        return this.getTool(toolCode).calculateChargeDays(startDate, duration);
    }
}
