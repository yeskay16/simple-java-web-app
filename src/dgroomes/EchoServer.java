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
        var html = """
                <html>
                <body>
                    <h1> This is a simple web app!</h1>
                """;
        var params = session.getParms();
        var echo = params.get("echo");
        if (echo == null) {
            html += """
                    <form action='?' method='get'>
                        <p>Say something: <input type='text' name='echo'></p>
                    </form>
                    """;
        } else {
            html += "<p>You said: %s</p>".formatted(echo);
        }
        return newFixedLengthResponse(html + "</body></html>\n");
    }
}
