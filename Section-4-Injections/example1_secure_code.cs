using System;
using System.Data.SqlClient;
using System.Web;

public class Library
{
    public string GetBookById(string bookId)
    {
        string query = "SELECT * FROM Books WHERE BookId = @BookId";

        using (SqlConnection connection = new SqlConnection(connectionString))
        {
            SqlCommand command = new SqlCommand(query, connection);
            command.Parameters.AddWithValue("@BookId", bookId);
            connection.Open();
            SqlDataReader reader = command.ExecuteReader();

            if (reader.Read())
            {
                return $"Title: {reader["Title"]}, Author: {reader["Author"]}";
            }
            else
            {
                return "Book not found.";
            }
        }
    }
}

