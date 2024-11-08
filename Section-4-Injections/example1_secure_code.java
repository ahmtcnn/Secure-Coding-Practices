import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerProfile {
    public String getCustomerProfileById(String customerId) {
        String query = "SELECT * FROM Customers WHERE CustomerId = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, "username", "password");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();

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

