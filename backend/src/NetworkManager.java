import com.fastcgi.FCGIInterface;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NetworkManager {

    public static void sendRequest(){

        FCGIInterface fcgi = new FCGIInterface();
        while (fcgi.FCGIaccept()>=0){
            double startTime = System.currentTimeMillis();
            String content = """
                    {
                    "Status": %s,
                    "Etime": "%s",
                    "Ctime": "%s"
                    }
                    """;

            String request = GetClientRequest.readRequestBody();
            double endTime = System.currentTimeMillis();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(LocalTime.now().format(formatter));
            System.err.println("#############################"+time);

            content = content.formatted(Validator.isValid(request), (endTime - startTime)/1000, time.toString());


                var httpResponse = """
                        HTTP/1.1 200 OK
                        Content-Type: application/json
                        Content-Length: %d
                        
                        
                        %s
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
                System.out.println(httpResponse);



        }
    }
//    private static String readRequestBody() {
//        try {
//            FCGIInterface.request.inStream.fill();
//            var contentLength = FCGIInterface.request.inStream.available();
//            var buffer = ByteBuffer.allocate(contentLength);
//            var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
//            var requestBodyRaw = new byte[readBytes];
//            buffer.get(requestBodyRaw);
//            buffer.clear();
//            return new String(requestBodyRaw, StandardCharsets.UTF_8);
//        }catch (Exception e) {
//            return e.toString();
//        }
//    }
}