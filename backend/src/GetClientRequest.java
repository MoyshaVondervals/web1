import com.fastcgi.FCGIInterface;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class GetClientRequest {
    protected static String readRequestBody() {
        try {
            FCGIInterface.request.inStream.fill();
            var contentLength = FCGIInterface.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
            var requestBodyRaw = new byte[readBytes];
            buffer.get(requestBodyRaw);
            buffer.clear();
            return new String(requestBodyRaw, StandardCharsets.UTF_8);
        }catch (Exception e) {
            return e.toString();
        }
    }
}
