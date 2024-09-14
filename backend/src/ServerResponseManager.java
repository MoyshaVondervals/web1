//import com.fastcgi.FCGIInterface;
//
//import java.nio.charset.StandardCharsets;
//
//public class ServerResponseManager {
//    public static void sendServerResponse() {
//        FCGIInterface fcgi = new FCGIInterface();
//        while (fcgi.FCGIaccept()>=0){
//            String httpResponse = readRequestBody(result);
//            System.out.println(httpResponse);
//
//        }
//
//    }
//    private static String readRequestBody(boolean result) {
//        String content = """
//                {
//                "Status": %s
//                """.formatted(result ? "true" : "false");
//        String httpResponse = """
//                 HTTP/1.1 200 OK
//                    Content-Type: application/json
//                    Content-Length: %d
//
//                    %s
//                """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content );
//        return httpResponse;
//    }
//}
