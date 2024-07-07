import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    
    // taking filename
    String filename = "content.txt";

    // creating arrays for types
    Order orders[] = new Order[100] ;
    Customer customers[] = new Customer[100];
    Operator operators[] = new Operator[100];

    // taking input from user
    Scanner scanner = new Scanner(System.in);
    System.out.println( "Please enter your ID...");
    System.out.print("\033[32m");

    int searchId = -1;  // a flag for input

    // controlling input whether it is integer or not
    if (scanner.hasNextInt()) {
        searchId = scanner.nextInt();
        System.out.print("\033[0m");
        // ID'nin geçerli aralıkta olup olmadığını kontrol et
        if (searchId > 0 && searchId < Integer.MAX_VALUE) {
            
            //System.out.println(searchId);

            fileRead(filename, orders, customers, operators);   // file reading function
            //displayData(orders,customers, operators);

            int flag = -1;  // flag for operator if exist

            for(Operator operator : operators){

                // checking the operators in operator array
                if(operator != null && operator.ID == searchId){

                    Customer[] temp = new Customer[100];    // temp array for matched customers
                    System.out.println("*** Operator Screen ***");
                    System.out.println("----------------------------");
                    operator.print_operator();
                    int index = 0;  // index for temp array

                    for (Customer customer : customers) {
                        if (customer != null && customer.operator_ID == operator.ID && customer.ID != operator.ID) {
                            
                            Order[] temp2 = new Order[100]; // temp array for matched orders
                            int index2 = 0; // index for temp2 array

                            for(Order order : orders){

                                // controlling the customers if they have same id with orders
                                if(order != null && customer.ID== order.customer_ID){
                                    
                                    temp2[index2++] = order;

                                }
                            }
                            
                            customer.define_orders(temp2);  // creating actual customers order
                            temp[index++] = customer; // Eşleşen müşteriyi temp dizisine ekle
                        }
                    }
                    
                    if(index == 0){
                        System.out.println("This operator doesn't have any customer.\r\n" +
                                           "----------------------------\r");
                    }


                    operator.define_customers(temp);    // creating operators customers
                    operator.print_customers(); // printing operator customers

                    flag = 1;
                    break;
                    
                }
            
            }

            // same logic here if user input doesnt match with operator id
            for(Customer customer : customers){

                if (customer != null && customer.ID == searchId && customer.ID != customer.operator_ID) {
                    
                    System.out.println("*** Customer Screen ***");
                    customer.print_customer();
                    Order[] temp2 = new Order[100];
                    int index2 = 0;

                    for(Order order : orders){

                        if(order != null && customer.ID== order.customer_ID){
                            
                            temp2[index2++] = order;

                        }
                    }
                    
                    customer.define_orders(temp2);
                    customer.print_orders();

                    flag = 1;
                    break;
                }

            }


            if(flag==-1){
                System.out.println("No operator/customer was found with ID "+ searchId + ". Please try again.");
            }


        } else {
            System.out.println("Number should be higher than 0 and less than " + Integer.MAX_VALUE);
        }
    } else {
        System.out.println("Please enter valid number");
        scanner.next(); // Geçersiz girişi atla
    }

    scanner.close();

    }
    
    // function for reading file
    public static void fileRead(String filename, Order orders[], Customer customers[], Operator operators[]){

try {
    File file = new File(filename);
    Scanner scanner = new Scanner(file);

    int orderIndex = 0, customerIndex = 0, operatorIndex = 0;   // variables for count of types

    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(";", -1);   // to divide into smaller parts of txt file

        // try-catch part for separating types for right matching
        try {
            switch (parts[0]) {
                case "order":
                    if (parts.length == 6 && !parts[1].isEmpty() && !parts[2].isEmpty() && !parts[3].isEmpty() && !parts[4].isEmpty() && !parts[5].isEmpty()) {
                        int count = Integer.parseInt(parts[2]);
                        int total_price = Integer.parseInt(parts[3]);
                        int status = Integer.parseInt(parts[4]);
                        int customer_ID = Integer.parseInt(parts[5]);

                        if (count > 0 && count < Integer.MAX_VALUE && total_price > 0 && total_price < Integer.MAX_VALUE && status >= 0 && status < 4 && customer_ID > 0 && customer_ID < Integer.MAX_VALUE) {
                            String product_name = parts[1];
                            orders[orderIndex++] = new Order(product_name, count, total_price, status, customer_ID);
                        }
                    }
                    break;
                case "retail_customer":
                    if (parts.length == 7 && !parts[1].isEmpty() && !parts[2].isEmpty() && !parts[3].isEmpty() && !parts[4].isEmpty() && !parts[5].isEmpty() && !parts[6].isEmpty()) {

                        int ID = Integer.parseInt(parts[5]);
                        int operator_ID = Integer.parseInt(parts[6]);

                        if (ID > 0 && ID < Integer.MAX_VALUE  && operator_ID > 0 && operator_ID < Integer.MAX_VALUE) {
                            String name = parts[1];
                            String surname = parts[2];
                            String city = parts[3];
                            String phone = parts[4];
                            customers[customerIndex++] = new RetailCustomer(name, surname, city, phone, ID, operator_ID);
                        }
                    }
                    break;
                case "corporate_customer":
                    if (parts.length == 8 && !parts[1].isEmpty() && !parts[2].isEmpty() && !parts[3].isEmpty() && !parts[4].isEmpty() && !parts[5].isEmpty() && !parts[6].isEmpty() && !parts[7].isEmpty()) {
                        
                        int ID = Integer.parseInt(parts[5]);
                        int operator_ID = Integer.parseInt(parts[6]);
                        
                        if (ID > 0 && ID < Integer.MAX_VALUE && operator_ID > 0 && operator_ID < Integer.MAX_VALUE) {
                            
                            String name = parts[1];
                            String surname = parts[2];
                            String city = parts[3];
                            String phone = parts[4];
                            String company_name = parts[7];

                            customers[customerIndex++] = new CorporateCustomer(name, surname, city, phone, ID, operator_ID, company_name);
                        }
                    }
                    break;
                case "operator":
                    if (parts.length == 7 && !parts[1].isEmpty() && !parts[2].isEmpty() && !parts[3].isEmpty() && !parts[4].isEmpty() && !parts[5].isEmpty() && !parts[6].isEmpty()) {
                        int ID = Integer.parseInt(parts[5]);
                        int wage = Integer.parseInt(parts[6]);
                        if (ID > 0 && ID < Integer.MAX_VALUE && wage > 0 && wage < Integer.MAX_VALUE) {
                            String name = parts[1];
                            String surname = parts[2];
                            String city = parts[3];
                            String phone = parts[4];
                            operators[operatorIndex++] = new Operator(name, surname, city, phone, ID, wage);
                        }
                    }
                    break;
                default:
                    // Tanımlanmamış veya bozuk satır
                    break;
            }
        } catch (NumberFormatException e) {
            // Sayısal değerler için hatalı girişleri burada yakala ve atla
            //System.out.println("Sayısal dönüşüm hatası: " + line);
            continue; // Hatalı satırı atla
        }
    }
    scanner.close();
} catch (Exception e) {
    System.out.println("File not found.");
}

    }

    // function for file reading checking
    public static void displayData(Order[] orders, Customer[] customers, Operator[] operators) {
        System.out.println("\nOrders:");
        for (Order order : orders) {
            if (order != null) {
                System.out.println("Product Name: " + order.product_name + ", Count: " + order.count + ", Total Price: " + order.total_price + ", Status: " + order.status + ", Customer ID: " + order.customer_ID);
            }
        }
    
        System.out.println("\nCustomers:");
        for (Customer customer : customers) {
            if (customer instanceof RetailCustomer) {
                // customer nesnesi RetailCustomer türünden, özel işlemleri burada gerçekleştir
                RetailCustomer retail = (RetailCustomer) customer; // Güvenli tür dönüşümü
                System.out.println("RetailCustomer Name: " + retail.name + " Surname: " + retail.surname + " Address: " + retail.address + " Phone: " + retail.phone + " ID: " + retail.ID + " operator_ID: " + retail.operator_ID);
                // RetailCustomer'a özgü işlemler...
            } else if (customer instanceof CorporateCustomer) {
                // customer nesnesi CorporateCustomer türünden, özel işlemleri burada gerçekleştir
                CorporateCustomer corporate = (CorporateCustomer) customer; // Güvenli tür dönüşümü
                System.out.println("CorporateCustomer Name: " + corporate.name + " Surname: " + corporate.surname + " Address: " + corporate.address + " Phone: " + corporate.phone + " ID: " + corporate.ID + " operator_ID: " + corporate.operator_ID + " company_name: " + corporate.company_name);
                // CorporateCustomer'a özgü işlemler...
            }
        }
        
        System.out.println("\nOperators:");
        for (Operator operator : operators) {
            if (operator != null) {
                System.out.println("Name: " + operator.name + ", Surname: " + operator.surname + ", City: " + ", Phone: " + operator.phone + ", ID: " + operator.ID + ", Wage: " + operator.wage);
            }
        }
    }
    

}
