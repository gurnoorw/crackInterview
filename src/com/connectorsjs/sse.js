// Client-side
const eventSource = new EventSource('/api/updates');

eventSource.onmessage = (event) => {
    const data = JSON.parse(event.data);
    updateUI(data);
};

// Server-side (Node.js/Express example)
app.get('/api/updates', (req, res) => {
    res.setHeader('Content-Type', 'text/event-stream');
    res.setHeader('Cache-Control', 'no-cache');
    res.setHeader('Connection', 'keep-alive');

    const sendUpdate = (data) => {
        res.write(`data: ${JSON.stringify(data)}\n\n`);
    };

    // Send updates when data changes
    dataSource.on('update', sendUpdate);

    // Clean up on client disconnect
    req.on('close', () => {
        dataSource.off('update', sendUpdate);
    });
});