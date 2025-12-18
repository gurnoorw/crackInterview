// Client-side
const ws = new WebSocket('ws://api.example.com/socket');

ws.onmessage = (event) => {
    const data = JSON.parse(event.data);
    handleUpdate(data);
};

ws.onclose = () => {
    // Implement reconnection logic
    setTimeout(connectWebSocket, 1000);
};

// Server-side (Node.js/ws example)
const WebSocket = require('ws');
const wss = new WebSocket.Server({ port: 8080 });

wss.on('connection', (ws) => {
    ws.on('message', (message) => {
        // Handle incoming messages
        const data = JSON.parse(message);
        processMessage(data);
    });

    // Send updates to client
    dataSource.on('update', (data) => {
        ws.send(JSON.stringify(data));
    });
});





