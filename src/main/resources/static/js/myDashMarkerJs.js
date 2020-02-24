var player = null;
var seekBarEl = null;
var firstColor = null;
var durationMode = true;
$(document).ready(function () {

	seekBarEl = $('#seekbar');         

});

function startVideo() {
    const url = 'https://dash.akamaized.net/akamai/bbb_30fps/bbb_30fps.mpd';
    var videoElement = document.querySelector(".videoContainer video");

    player = dashjs.MediaPlayer().create();
    player.initialize(videoElement, url, true);
	console.log("TOTAL DURATION:" + player)
    var controlbar = new ControlBar(player);
    controlbar.initialize();
    
    getMarkers();
}

function getMarkers(){
    $.ajax({
        type: 'GET',
        url: '/markersForDashJs',
        // type of data we are expecting in return:
        dataType: 'json',
        success: function (data) {
            
            data.forEach(createAndAddMarkerDiv);             
        },
        error: function (xhr, type) {
            alert('Ajax error!')
        }
    });
}

function createAndAddMarkerDiv(marker, index){
	console.log(marker);
	
    if(firstColor == null){
        firstColor = marker.backgroundColor;
    }    

    var markerDiv = document.createElement('div');
    markerDiv.id = 'marker'+index;
    
    markerDiv.style.width = "5px";
    markerDiv.style.height = "10px";
    markerDiv.style.left = (marker.time/634)*100+'%';
    markerDiv.style.position = 'absolute';
    markerDiv.style.backgroundColor = marker.backgroundColor;
    
    if(durationMode){
    	markerDiv.style.width = (marker.duration/634)*100+'%';
    	markerDiv.style.backgroundImage = 'linear-gradient(to right, ' + firstColor + ', ' + marker.backgroundColor + ')';
    	firstColor = marker.backgroundColor;
    }    
    
    seekBarEl.append(markerDiv);
    
    
    
    	
    	
    	//text: "İkinci Video"
    	//time: 50
    	
}
