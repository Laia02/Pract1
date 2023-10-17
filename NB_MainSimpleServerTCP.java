package aar;

public class NB_MainSimpleServerTCP {
    public static void main(String[] args) {
        NB_SimpleServerTCPInteractivo objetoServer= new NB_SimpleServerTCPInteractivo(4444);
        objetoServer.run();
    }   
}
