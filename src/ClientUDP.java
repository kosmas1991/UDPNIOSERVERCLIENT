import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientUDP {
    public static void main(String[] args) throws IOException {
        DatagramChannel client = DatagramChannel.open();
        client.bind(null);
        String message = "Hello Mr Server";
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        InetSocketAddress srvAddress = new InetSocketAddress("localhost",8081);
        client.send(byteBuffer,srvAddress);   //////
        byteBuffer.clear();
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
        client.receive(byteBuffer2);    //////
        byte[] bytes = new byte[byteBuffer2.limit()];
        byteBuffer2.rewind();
        byteBuffer2.get(bytes,0,byteBuffer2.limit());
        String message2 = new String(bytes);
        System.out.println("Server says " + message2);
        client.close();
    }
}
