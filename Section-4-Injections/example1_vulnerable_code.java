import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerProfile {
    public String getCustomerProfileById(String customerId) {
        String query = "SELECT * FROM Customers WHERE CustomerId = '" + customerId + "'";

        try (Connection connection = DriverManager.getConnection(connectionString, "username", "password");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                return "Name: " + resultSet.getString("Name") + ", Email: " + resultSet.getString("Email");
            } else {
                return "Customer not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving customer profile.";
        }
    }

    public static void main(String[] args) {
        CustomerProfile profile = new CustomerProfile();
        System.out.println(profile.getCustomerProfileById("123"));
    }
}

