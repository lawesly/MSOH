package rmi;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * RMI远程连接助手
 * <p>
 * Created by zqh on 2016/11/13.
 */
public class RemoteHelper {

    public RemoteHelper() {
        initServer();
    }

    public void initServer() {
        String ipAddress;
        DataRemoteObject dataRemoteObject;
        try {
            ipAddress=InetAddress.getLocalHost().getHostAddress().toString();
            System.setProperty("java.rmi.server.hostname",ipAddress);

            dataRemoteObject = new DataRemoteObject();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://"+ipAddress+":1099/DataRemoteObject", dataRemoteObject);
            System.out.println("Octopus: 连接客户端成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
