package aar;

import java.io.*;
import java.net.*;

public class NBClienteTCP {

	private String IP = "";
	private int puerto = 0;

	public NBClienteTCP(String IP, int puerto) {
		this.IP = IP;
		this.puerto = puerto;
	}

	public void run() {
		Socket socket = null;
		PrintWriter out_socket = null;
		BufferedReader in_socket = null;
		BufferedReader in = null;
		BufferedReader stdIn = null;

		in = new BufferedReader(new InputStreamReader(System.in));
		String resposta;
		boolean respIncorrecta = false;

		System.out.println("Benvingut!!");

		try {
			System.out.println("Prem 1 si vols connectarte amb el servidor");
			resposta = in.readLine();

			switch (resposta) {
			case "1":

				try {

					socket = new Socket(IP, puerto);
					out_socket = new PrintWriter(socket.getOutputStream(), true);
					in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				} catch (UnknownHostException e) {
					System.err.println("Don't know about host: " + IP);
					System.exit(1);
				} catch (IOException e) {
					System.err.println("Couldn't get I/O for the connection to: " + IP);
					System.exit(1);
				}

				stdIn = new BufferedReader(new InputStreamReader(System.in));
				String fromServer;

				System.out.println("Has seleccionat connectarte");
				try {
					while ((fromServer = in_socket.readLine()) != null) {
						System.out.println("Server: " + fromServer);
						break;
					}
				} catch (IOException e) {
					System.err.println(e.getCause());
					System.exit(1);
				}

				break;

			default:
				System.out.println("El número utilitzat es incorrecte, tancant comunicació...");
				respIncorrecta = true;
				in.close();
				break;

			}
			if (!respIncorrecta) {
				try {
					out_socket.close();
					in_socket.close();
					stdIn.close();
					in.close();
					socket.close();
				} catch (IOException e) {
					System.err.println("Close failed.");
					System.exit(1);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

}
