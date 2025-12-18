async function poll() {
    const response = await fetch('/api/updates');
    const data = await response.json();
    processData(data);
}

// Poll every 2 seconds
setInterval(poll, 2000);