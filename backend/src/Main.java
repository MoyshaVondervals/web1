import com.fastcgi.FCGIInterface;


import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server trying to start...");

        NetworkManager.sendRequest();
    }

}