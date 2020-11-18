import http.server
import socketserver
from urllib import parse


class Handler(http.server.SimpleHTTPRequestHandler):

    def do_OPTIONS(self):           
        self.send_response(200, "ok") 
        self.send_header('Access-Control-Allow-Origin', '*')                
        self.send_header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS')
        self.send_header("Access-Control-Allow-Headers", "Content-Type")  

    def do_POST(self):
        print('POST')
        content_length = int(self.headers['Content-Length'])
        post_data = parse.unquote(str(self.rfile.read(content_length))[11:][:-1])
        print(post_data)
        self.send_response(200, "ok")       


with socketserver.TCPServer(("0.0.0.0", 8080), Handler) as httpd:
    print("Serving at port", 8080)
    httpd.serve_forever()

