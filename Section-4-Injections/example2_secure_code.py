import subprocess

class NetworkManagement:
    def check_service_status(self, service_name):
        # Secure against Command Injection
        try:
            response = subprocess.check_output(["systemctl", "status", service_name], universal_newlines=True)
            return response
        except subprocess.CalledProcessError as e:
            return f"Error checking service status: {e}"

# Example usage
network_management = NetworkManagement()
print(network_management.check_service_status("ssh"))

