package dgroomes;

import fi.iki.elonen.NanoHTTPD;

/**
 * Serves a basic HTML page with a form field to echo back the entered value.
 */
public class EchoServer extends NanoHTTPD {

    public EchoServer(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        var msg = "<html><body><h1>Hello from a Java HTTP server!</h1>\n";
        var params = session.getParms();
        var echo = params.get("echo");
        if (echo == null) {
            msg += "<form action='?' method='get'>\n  <p>Say something: <input type='text' name='echo'></p>\n</form>\n";
        } else {
            msg += String.format("<p>You said: %s</p>", echo);
        }
        return newFixedLengthResponse(msg + "</body></html>\n");
    }
}
