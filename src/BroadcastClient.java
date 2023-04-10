import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastClient extends Thread{
    private DatagramSocket socket;
    private InetAddress ip;
    private byte buffer[] = null;

    public BroadcastClient(){
        this.initializeSocket();
        this.setIP();
    }

    private void initializeSocket(){
        try{
            socket = new DatagramSocket();
        }
        catch(Exception e){
            System.out.println("Client Socket Error");
        }
    }

    private void setIP(){
        try{
            ip = InetAddress.getLocalHost();
        }
        catch(Exception e){
            System.out.println("IP Address Error");
        }
    }

    public Boolean send(String playerID){
        buffer = playerID.getBytes();

        DatagramPacket packetToSend = new DatagramPacket(buffer, buffer.length, ip, 7500);

        try{
            socket.send(packetToSend);
        }
        catch(Exception e){
            return false;
        }

        System.out.println("Sent: " + playerID);
        return true;
    }
}
