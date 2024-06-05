
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.SocketException;

public class UDPServer {
    private static City Frankfurt = new City("Frankfurt");
    private static City Berlin = new City("Berlin");
    public static void main(String[] args) {
        Frankfurt.addInhabitant("hoang", "08-06-2003", "Single");
        Frankfurt.addInhabitant("phi", "07-03-2003", "Married");
        Frankfurt.addInhabitant("bao", "31-12-2003", "Single");


        Berlin.addInhabitant("tammy", "21-03-2003", "Married");
        Berlin.addInhabitant("huy", "12-06-2003", "Single");
        Berlin.addInhabitant("hieu", "01-01-2003", "Married");
        //create socket with port 12345
        try (DatagramSocket socket = new DatagramSocket(2223)) {
            //create buffer used to store data of received UDP packet
            byte[] buffer = new byte[1000];
            //main loop for receiving and processing packets
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);


                ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Message message = (Message) ois.readObject();

                //debug

                Object result = handleMessage(message);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(result);
                byte[] responseBytes = baos.toByteArray();

                DatagramPacket responsePacket = new DatagramPacket(
                        responseBytes, responseBytes.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        } catch (IOException e){System.out.println("IO: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static Object handleMessage(Message message) {
        City city=null;
        if(message.getCity().equals("Frankfurt")){
            city=Frankfurt;
        }
        else city=Berlin;
        String methodName = message.method_name;
        Object[] params = message.params;
        switch (methodName) {
            case "addInhabitant":
                city.addInhabitant((String) params[0], (String) params[1], (String) params[2]);
                return "Inhabitant added: name: "+(String) params[0]+" date of Birth: "+(String) params[1]+" status: "+(String) params[2];
            case "getMaritalStatus":
                return city.getMaritalStatus((String) params[0]);
            case "BirthOfAllInhabitants":
                return city.getBirthOfAllInhabitants();
            default:
                break;

        }
        return "Unknown method";
    }
}