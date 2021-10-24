package dgroomes;

import fi.iki.elonen.NanoHTTPD;

public class Main {

    public static void main(String[] args) throws Exception {
        var server = new EchoServer(8080);
        server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("""
                                
                Running! Open http://localhost:8080/ in your browser
                """);
    }
}
