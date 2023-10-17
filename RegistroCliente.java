/**
 * 
 */
package aar;

import java.net.*;

/**
 * @author PC
 *
 */
public class RegistroCliente {

	private InetAddress ip;
    private int port;

    public RegistroCliente(InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "Cliente [ip=" + ip + ", puerto=" + port + "]";
    }
}