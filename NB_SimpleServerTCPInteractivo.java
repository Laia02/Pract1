package aar;

import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class NB_SimpleServerTCPInteractivo {
    
    private int puerto = 0;
    
    	//CREEM ARRAYLIST GUARDAR TAULES
       private final ArrayList<RegistroCliente> clientes = new ArrayList<>();

    
    public NB_SimpleServerTCPInteractivo(int puerto) {
        this.puerto = puerto; 
    }
    
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+puerto);
            System.exit(1);
        }
        
        while(true) {
        	Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = null;
            BufferedReader in = null;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
    				new InputStreamReader(
    				clientSocket.getInputStream()));
            } catch (IOException e) {
                System.err.println("Create streams failed.");
                System.exit(1);
            }

            	System.out.println(clientSocket.getInetAddress());

            	 if(clientes.isEmpty()) {
                	 clientes.add(new RegistroCliente(clientSocket.getInetAddress(), clientSocket.getPort()));
            		 out.println("Succesfully connected. You are the first client in the P2P network");
            	 }else {
            		 if(estaIplista(clientes,clientSocket.getInetAddress())) {
            			 out.println("You are already in the P2P network");
            		 }else {
            			 clientes.add(new RegistroCliente(clientSocket.getInetAddress(), clientSocket.getPort()));
            			 out.println("Succesfully connected.");
            		 }
            	 }
            	 System.out.println(clientes);
            
            
            try {
                out.close();
                in.close();
                clientSocket.close();  
            } catch (IOException e) {
                System.err.println("Close failed.");
                System.exit(1);
            }     
        }
        
    } 
    
    private boolean estaIplista(ArrayList<RegistroCliente> clientes2, InetAddress inetAddress) {
		boolean estaElCliente=false;
		for (int i = 0; i < clientes.size(); i++) {
            if((clientes.get(i).getIp()).equals(inetAddress)) {
            	estaElCliente=true;
            }
        }
		return estaElCliente;
	}

}
