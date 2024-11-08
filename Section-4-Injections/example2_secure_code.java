import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.util.UUID;

@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = generateUniqueFileName();

        // Save the file
        File uploads = new File("/uploads");
        File file = new File(uploads, fileName);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath());
        }

        // Unzip the file (Vulnerable to Command Injection)
        String command = "unzip " + file.getAbsolutePath();
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        process.waitFor();
        response.getWriter().println(output.toString());
    }

    private String generateUniqueFileName() {
        // Generate a unique file name using UUID
        return UUID.randomUUID().toString() + ".zip"; // Assuming the uploaded file is a zip file
    }
}

