import java.util.Scanner;
interface Billable {
    double calculateTotal(double units);
}

class ElectricityBill implements Billable {
    public double calculateTotal(double units) {
        if(units <= 100) {
            return units * 1.0;
        }else if(units <= 300){ 
            return units * 2.0;
        }else{
            return units * 5.0;
        }
    }
}

class WaterBill implements Billable {
    public double calculateTotal(double units) {
        if(units <= 100){
            return units * 0.5;
        }else if(units <= 300){
            return units * 1.5;
        } 
        else{
            return units * 3.0;
        }
    }
}
public class SmartPay{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Billable electricity = new ElectricityBill();
        Billable water = new WaterBill();

        while (true){
            try{
                System.out.print("Enter Customer Name (or type Exit): ");
                String name = sc.nextLine();
                if (name.equalsIgnoreCase("Exit")) {
                    System.out.println("Exiting...");
                    break;
                }
                System.out.println("\n--- Electricity Bill ---");
                System.out.print("Enter Previous Reading: ");
                double ePrev = sc.nextDouble();
                System.out.print("Enter Current Reading: ");
                double eCurr = sc.nextDouble();

                System.out.println("\n--- Water Bill ---");
                System.out.print("Enter Previous Reading: ");
                double wPrev = sc.nextDouble();
                System.out.print("Enter Current Reading: ");
                double wCurr = sc.nextDouble();

                sc.nextLine();

                if(ePrev < 0 || eCurr < 0 || wPrev < 0 || wCurr < 0){
                    System.out.println("Error: Readings cannot be negative\n");
                    continue;
                }
                if (eCurr < ePrev || wCurr < wPrev) {
                    System.out.println("Error: Current reading cannot be less than previous reading\n");
                    continue;
                }
                
                double eUnits = eCurr - ePrev;
                double wUnits = wCurr - wPrev;
 
                double eTotal = electricity.calculateTotal(eUnits);
                double wTotal = water.calculateTotal(wUnits);

                double total = eTotal + wTotal;
                double tax = total * 0.1;
                double finalAmount = total + tax;

                
                System.out.println("\n----- DIGITAL RECEIPT -----");
                System.out.println("Customer Name      : " + name);

                System.out.println("\nElectricity Units  : " + eUnits);
                System.out.println("Electricity Bill   : $" + eTotal);

                System.out.println("\nWater Units        : " + wUnits);
                System.out.println("Water Bill         : $" + wTotal);

                System.out.println("\nCombined Bill      : $" + total);
                System.out.println("Tax (10%)          : $" + tax);
                System.out.println("Final Amount       : $" + finalAmount);
                System.out.println("---------------------------\n");

            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numeric values.\n");
                sc.nextLine();
            }
        }
        sc.close();
    }
}