import com.fastcgi.FCGIInterface;
import org.apache.log4j.Logger;

import java.io.Console;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class NetworkManager {

    public static void sendRequest(){
        double startTime = System.currentTimeMillis();
        FCGIInterface fcgi = new FCGIInterface();
        System.err.println(fcgi);
        while (fcgi.FCGIaccept()>=0){
            String content = """
                    {
                    "status": %s,
                    }
                    """;
            String request = readRequestBody();
            content = content.formatted("true");

            var httpResponse = """
                    HTTP/1.1 200 OK
                    Content-Type: application/json
                    Content-Length: %d
                    %s
                    """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content );
            System.out.println(httpResponse);

        }
    }
    private static String readRequestBody() {
        try {
            FCGIInterface.request.inStream.fill();
            var contentLength = FCGIInterface.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes =
                    FCGIInterface.request.inStream.read(buffer.array(), 0,
                            contentLength);
            var requestBodyRaw = new byte[readBytes];
            buffer.get(requestBodyRaw);
            buffer.clear();
            return new String(requestBodyRaw, StandardCharsets.UTF_8);
        }catch (Exception e) {
            return "";
        }
    }
}