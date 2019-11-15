window.onload = function(){
    // console.log("__image0:"+document.getElementById("__image0-cont0_1"));
    // document.getElementById("__image0-cont0_1").innerHTML =
    document.getElementsByClassName('sapUiRFLContainer')[2].innerHTML =
        "<div class=\"controls\">\n" +
        "      <button id=\"button\">Get camera</button>\n" +
        "      <select id=\"select\">\n" +
        "        <option></option>\n" +
        "      </select>\n" +
        "    </div>"+
        "<video id=\"video\"  width=\"640\" height=\"480\" autoplay playsinline></video>" +
        "<canvas width=\"640\" height=\"480\" id=\"outrec\"></canvas>";
    document.getElementById('button').addEventListener('click', event => {
        if (typeof currentStream !== 'undefined') {
            stopMediaTracks(currentStream);
        }
        const videoConstraints = {};
        if (select.value === '') {
            videoConstraints.facingMode = 'environment';
        } else {
            videoConstraints.deviceId = { exact: select.value };
        }
        const constraints = {
            video: videoConstraints,
            audio: false
        };
        navigator.mediaDevices.getUserMedia(constraints)
            .then(stream => {
                currentStream = stream;
                video.srcObject = stream;
                return navigator.mediaDevices.enumerateDevices();
            })
            .then(gotDevices)
            .catch(error => {
                console.error(error);
            });
    });
};