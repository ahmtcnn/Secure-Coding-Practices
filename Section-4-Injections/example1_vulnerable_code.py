import sqlite3

class SocialMedia:
    def get_profile_by_id(self, user_id):
        connection = sqlite3.connect("social_media.db")
        cursor = connection.cursor()

        # Vulnerable to SQL Injection
        query = f"SELECT * FROM users WHERE user_id = '{user_id}'"
        cursor.execute(query)
        result = cursor.fetchone()

        connection.close()

        if result:
            return f"Name: {result[1]}, Email: {result[2]}"
        else:
            return "User not found."

# Example usage
social_media = SocialMedia()
print(social_media.get_profile_by_id("1"))

