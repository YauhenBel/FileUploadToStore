import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConToPHP {

    public static String GetPuth() throws IOException {
        String urllink = "http://127.0.0.1/my%20portable%20files/image/new%201.php?action=puth";
        System.out.println(urllink);
        URL url = new URL(urllink);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        System.out.println("sb.toString(): " + sb.toString());
        return sb.toString();
    }
}
