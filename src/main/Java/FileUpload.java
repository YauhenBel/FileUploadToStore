import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUpload extends HttpServlet {
    String save_dir = "";
    static final int BUFFER_SIZE = 4096;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        save_dir = ConToPHP.GetPuth() +"\\image\\";
        save_dir = save_dir.replace("\"", "");
        System.out.println("save_dir: "+ save_dir);
        //Этот метода принимает и сохраняет файл на сервере
        // Gets file name for HTTP header
        String fileName = request.getHeader("fileName");
        File saveFile = new File(save_dir + fileName);
        // prints out all header values
        System.out.println("===== Begin headers =====");
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String headerName = names.nextElement();
            System.out.println(headerName + " = " + request.getHeader(headerName));
        }
        System.out.println("===== End headers =====\n");

        // opens input stream of the request for reading dataf
        InputStream inputStream = request.getInputStream();

        // opens an output stream for writing file
        FileOutputStream outputStream = new FileOutputStream(saveFile);

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        System.out.println("Receiving data...");

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        System.out.println("Data received.");
        outputStream.close();
        inputStream.close();

        System.out.println("File written to: " + saveFile.getAbsolutePath());
        System.out.println("fileName = " + fileName);
        //Process process = Runtime.getRuntime().exec("cmd /c "
        //+ saveFile.getAbsolutePath());
        // sends response to client
        response.getWriter().print("UPLOAD DONE");

    }
}
