import com.fastcgi.FCGIInterface;
import org.apache.log4j.Logger;

import java.io.Console;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class NetworkManager {

    public static void sendRequest(){

        FCGIInterface fcgi = new FCGIInterface();
        while (fcgi.FCGIaccept()>=0){
            double startTime = System.currentTimeMillis();
            String content = """
                    {
                    "Status": %s,
                    "time": %s
                    }
                    """;

            String request = readRequestBody();
//            System.err.println("Request from server: "+request);

            double endTime = System.currentTimeMillis();
            content = content.formatted(Validator.isValid(request), (endTime - startTime)/1000);


            var httpResponse = """
                    HTTP/1.1 200 OK
                    Content-Type: application/json
                    Content-Length: %d
                    
                    
                    %s
                    """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
//            System.err.println(httpResponse);
//            Validator.isValid(httpResponse)
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
            return e.toString();
        }
    }
}