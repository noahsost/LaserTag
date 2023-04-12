import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedList;
import java.util.Queue;

public class Server extends Thread {
    private DatagramSocket socket;
    private byte[] receive = new byte[65535];
    private DatagramPacket packet;
    private Queue<String> actionLog = new LinkedList<String>();
    boolean isAcceptingData = false;
    BroadcastClient client;

    public Server(){
        this.initializeSocket();
        client = new BroadcastClient();
    }

    private void initializeSocket(){
        try{
            socket = new DatagramSocket(7501);
        }
        catch(Exception e){
            System.out.println("Socket Error");
        }
    }

    public void run(){
        boolean isRunning = true;
        while(isRunning){
            
            packet = new DatagramPacket(receive, receive.length);
            
            if(isAcceptingData){
                try{
                    socket.receive(packet);
                    String receivedPacket = data(receive).toString();
                    actionLog.add(receivedPacket);
                    client.send(getHitPlayerID(receivedPacket));
                }
                catch(Exception e){
                    System.out.println("Error receiving packet");
                }

                if(data(receive).toString().equals("bye")){
                    System.out.println("Server Exit");
                    isRunning = false;
                }
            }
            // flush buffer
            receive = new byte[65535];
        }
    }

    private String getHitPlayerID(String receivedString){
        String[] decodedStringArray = receivedString.split(":", 2);
        return decodedStringArray[1];
    }

    public static StringBuilder data(byte[] a)
	{
		if (a == null)
			return null;
		StringBuilder newString = new StringBuilder();
		int i = 0;
		while (a[i] != 0)
		{
			newString.append((char) a[i]);
			i++;
		}
		return newString;
	}

    // returns and removes head of actionLog
    public String pollActionLog(){
        return actionLog.poll();
    }
    
}