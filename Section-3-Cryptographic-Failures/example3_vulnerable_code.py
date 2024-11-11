from flask import Flask, request, jsonify
import hashlib

app = Flask(__name__)

# Simulated user storage
users = []

@app.route('/register', methods=['POST'])
def register_user_md5():
    data = request.json
    username = data['username']
    email = data['email']
    password = data['password']

    # Hash the password with MD5
    hash_object = hashlib.md5(password.encode())
    password_hash = hash_object.hexdigest()

    # Store user information
    user_storage = {
        'username': username,
        'email': email,
        'password_hash': password_hash
    }
    users.append(user_storage)
    return jsonify({"message": "User registered with MD5 hash"}), 201

if __name__ == '__main__':
    app.run(debug=True)

