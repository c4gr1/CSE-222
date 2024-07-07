public class CorporateCustomer extends Customer {
    
    // Company name for the corporate customer
    String company_name;

    // Constructor for the CorporateCustomer class
    public CorporateCustomer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
        super(name, surname, address, phone, ID, operator_ID); // Call the constructor of the superclass, Customer
        this.company_name = company_name; // Initialize the field unique to CorporateCustomer
    }

    // Overrides print_customer method to include company name
    @Override
    public void print_customer() {
        super.print_customer(); // Calls the superclass method to print common details
        System.out.println("Company name: " + company_name); // Prints the company name
    }

    // Getter for company name
    public String getCompanyName() {
        return company_name;
    }

    // Setter for company name
    public void setCompanyName(String company_name) {
        this.company_name = company_name; // Sets the company name
    }
}
