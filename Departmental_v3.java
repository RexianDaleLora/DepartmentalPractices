import java.io.*;
import java.util.*;

public class Departmental_v3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input household details
        System.out.print("Enter Household Name: ");
        String houseHoldName = sc.next();
        System.out.print("Enter monthly consumption (in kWh): ");
        int monthlyConsumption = sc.nextInt();
        System.out.print("Enter Bill Amount: ");
        double billAmount = sc.nextDouble();

        // Process billing details
        String usageCategory = getUsageCategory(monthlyConsumption);
        double computedBill = computeBill(monthlyConsumption);
        double discountFactor = applyDiscount(computedBill, usageCategory);

        // Record bill details to a file
        try (FileWriter fw = new FileWriter("ElectricityBillReport.txt" , true)) {

            // Write details to the file
            fw.write("Household: " + houseHoldName + "\n");
            fw.write("Monthly Consumption: " + monthlyConsumption + " kWh\n");
            fw.write("Category: " + usageCategory + "\n");
            fw.write(String.format("Original Bill: ₱%.2f%n", computedBill));
            fw.write(String.format("Discounted Bill: ₱%.2f%n", discountFactor));
            fw.write("--------------------------------------------------\n");

            // Display summary to the user
            System.out.println("\nChange: ₱" + String.format("%.2f", billAmount - discountFactor));
            System.out.println("Bill details recorded successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while recording the bill details.");
            e.printStackTrace();
        }
        sc.close();
    }

    // Determine usage category based on consumption
    public static String getUsageCategory(int consumption) {
        if (consumption <= 199) {
            return "Low Usage";
        } else if (consumption >= 200 && consumption <= 300) {
            return "Medium Usage";
        } else {
            return "High Usage";
        }
    }

    // Compute bill based on consumption
    public static double computeBill(int consumption) {
        double rate;
        if (consumption <= 199) {
            rate = 8.00;
        } else if (consumption >= 200 && consumption <= 300) {
            rate = 10.00;
        } else {
            rate = 12.00;
        }
        return consumption * rate;
    }

    // Apply discount based on consumption
    public static double applyDiscount(double computeBill, String usageCategory) {
        double discount;
        switch (usageCategory) {
            case "Low Usage":
                discount = 0.90;
                break;
            default:
                discount = 1.00;
                break;
        }
        return computeBill * discount;
    }
}
