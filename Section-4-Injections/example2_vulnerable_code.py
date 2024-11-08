import os

class NetworkManagement:
    def check_service_status(self, service_name):
        # Vulnerable to Command Injection
        command = f"systemctl status {service_name}"
        response = os.popen(command).read()
        return response

# Example usage
network_management = NetworkManagement()
print(network_management.check_service_status("ssh"))

