// Client-side of long polling
async function longPoll() {
    while (true) {
        try {
            const response = await fetch('/api/updates');
            const data = await response.json();

            // Handle data
            processData(data);
        } catch (error) {
            // Handle error
            console.error(error);

            // Add small delay before retrying on error
            await new Promise(resolve => setTimeout(resolve, 1000));
        }
    }
}