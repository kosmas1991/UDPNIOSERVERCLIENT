import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerUDP {
    public static void main(String[] args) throws IOException {
        DatagramChannel server = DatagramChannel.open();
        InetSocketAddress srvAddress = new InetSocketAddress("localhost",8081);
        server.bind(srvAddress);
        System.out.println("Server started at " + srvAddress);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        SocketAddress clntAddress = server.receive(byteBuffer);
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.flip();
        byteBuffer.get(bytes,0,byteBuffer.limit());
        String message = new String(bytes);
        System.out.println("Client: " + clntAddress + " says " + message);
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("Hello client".getBytes());
        server.send(byteBuffer2,clntAddress);
        server.close();
    }
}
