var player = null;
var seekBarEl = null;
var firstColor = null;
var durationMode = true;
var videoElement = null;
$(document).ready(function () {

	seekBarEl = $('#seekbar');
	console.log("Document is ready");

});

/*
 * First method to initialize player
 * */
function startVideo(url, isDurationMode) {
    videoElement = document.getElementById("myVideo");
    
    durationMode = isDurationMode;
    player = dashjs.MediaPlayer().create();
    player.initialize(videoElement, url, true);
    var controlbar = new ControlBar(player);
    controlbar.initialize();    
    
    /*
     * player duration could not be ready before meta data loaded. Son getting markers and using player.duratin() is triggerd by the onloadmetadata HTML5 event. 
     * */
    player.on('sourceInitialized',sourceInitialized,this);
}

function sourceInitialized(e) {
	this.getMarkers();
}


/*This method access markers. Each application needs to implement a Rest service for video related markers. 
 * Default URL: /markersForDashJs
 * 
 * */
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
            console.log('Error occured - markersForDashJs service!')
        }
    });
}

/*
 * Create specialized marker div for each marker data. It can be a icon mode or duraiton mode.
 * */
function createAndAddMarkerDiv(marker, index){
	//console.log(marker); 
	var videoDuration = player.duration();

    var markerDiv = document.createElement('div');
    markerDiv.id = 'marker'+index;
    markerDiv.style.height = "20px";
    markerDiv.style.left = (marker.time/videoDuration)*100+'%';    
    markerDiv.style.position = 'absolute';    

    //markerDiv.style.backgroundImage= "url('../img/"+(4-index)+".png')";
    
    
    if(durationMode){
        if(firstColor == null){
            firstColor = marker.backgroundColor;
        } 
    	markerDiv.style.width = (marker.duration/videoDuration)*100+'%';
    	markerDiv.style.backgroundImage = 'linear-gradient(to right, ' + firstColor + ', ' + marker.backgroundColor + ')';
    	firstColor = marker.backgroundColor;
    }else{
        markerDiv.style.width = "20px";
        markerDiv.style.backgroundSize = "20px 20px";
        

		var myUrl =  "url('../img/"+ marker.iconName + ".png')";
		markerDiv.style.backgroundImage= myUrl;
    }    
    
    seekBarEl.append(markerDiv);    	
}