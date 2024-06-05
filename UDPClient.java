import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.*;

import com.sun.nio.sctp.MessageInfo;
import org.apache.commons.lang3.SerializationUtils;
public class UDPClient {
    public static void main(String[] args) {
        try {
            String method1="addInhabitant";
            String method2="getMaritalStatus";
            String method3="BirthOfAllInhabitants";
            Object[] param1={"vini","10-2-2003","Single"};
            Object[] param2={"tammy"};
            Object[] param3={};
            String city1="Berlin";
            String city2="Frankfurt";
            //write into stream
            Message message = new Message(city1, method2, param2);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);


            oos.writeObject(message);
            byte[] m = baos.toByteArray();
            DatagramSocket aSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 2223;
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            //receive the reply
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            ByteArrayInputStream bais = new ByteArrayInputStream(reply.getData(), reply.getOffset(), reply.getLength());
            ObjectInputStream ois = new ObjectInputStream(bais);

            Object response = ois.readObject();
//            Message recievedMessage = (Message) ois.readObject();
            System.out.println(" Reply: " + response);
            aSocket.close();

            bais.close();
            baos.close();
            oos.close();
            ois.close();

        } catch (SocketException e) {
            System.out.println(" Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(" IO: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}