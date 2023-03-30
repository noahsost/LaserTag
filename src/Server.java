import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private DatagramSocket socket;
    private byte[] receive = new byte[65535];
    private DatagramPacket packet;
    private List<String> log = new ArrayList<String>();

    public Server(){
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
            
            try{
                socket.receive(packet);

                log.add(data(receive).toString());
            }
            catch(Exception e){
                System.out.println("Error receiving packet");
            }

            if(data(receive).toString().equals("bye")){
                System.out.println("Server Exit");
                isRunning = false;
            }

            // flush buffer
            receive = new byte[65535];
        }
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

    public List<String> getLog(){
        return this.log;
    }

    public void clearLog(){
        this.log.clear();
    }

}