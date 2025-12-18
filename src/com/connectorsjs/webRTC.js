// Simplified WebRTC setup
async function startCall() {
    const pc = new RTCPeerConnection();

    // Get local stream
    const stream = await navigator.mediaDevices.getUserMedia({
        video: true,
        audio: true
    });

    // Add tracks to peer connection
    stream.getTracks().forEach(track => {
        pc.addTrack(track, stream);
    });

    // Create and send offer
    const offer = await pc.createOffer();
    await pc.setLocalDescription(offer);

    // Send offer to signaling server
    signalingServer.send(offer);
}