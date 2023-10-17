package aar;

public class NBMainClienteTCP {

	public static void main(String[] args) {
        NBClienteTCP objetoCliente= new NBClienteTCP("127.0.0.1",4444);
        objetoCliente.run();
	}

}
