
$(document).ready(function () {

     var videoEl = $('video#my-player');
     var videoUrl = videoEl.data('url');
     var videoId = videoEl.data('id');
     
     var userId = Cookies.get('userId');
     if( userId == null){
    	 Cookies.set('userId', idGen.getId());
     }
     


    var options = {};
    var player = videojs('my-player', options, function onPlayerReady() {
        videojs.log('Your player is ready!');

        // In this context, `this` is the player that was created by Video.js.
//        this.play();

        // How about an event listener?
        this.on('ended', function() {
            videojs.log('Awww...over so soon?!');
        });
    });

    //load the marker plugin


    $.ajax({
        type: 'GET',
        url: '/markers',
        // data to be added to query string:
        data: {id: videoId}, // data-id
        // type of data we are expecting in return:
        dataType: 'json',
        success: function (data) {
            player.markers({
                markers: data
            });
            console.info(data);
        },
        error: function (xhr, type) {
            alert('Ajax error!')
        }
    });

});
