from flask import Flask, request, jsonify
import bcrypt

app = Flask(__name__)

# Simulated user storage
users = []

@app.route('/register', methods=['POST'])
def register_user_bcrypt():
    data = request.json
    username = data['username']
    email = data['email']
    password = data['password']

    # Generate a salt and hash the password with bcrypt
    salt = bcrypt.gensalt()
    password_hash = bcrypt.hashpw(password.encode(), salt)

    # Store user information
    user_storage = {
        'username': username,
        'email': email,
        'password_hash': password_hash.decode('utf-8')
    }
    users.append(user_storage)
    return jsonify({"message": "User registered with bcrypt hash"}), 201

if __name__ == '__main__':
    app.run(debug=True)

