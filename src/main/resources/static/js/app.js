var player = dashjs.MediaPlayer().create();

$(document).ready(function () {

    var videoEl = $('video#myVideo');
    var videoUrl = videoEl.data('url');

    $.ajax({
        type: 'GET',
        url: '/markers',
        // data to be added to query string:
        data: {id: videoEl.data('id')}, // data-id
        // type of data we are expecting in return:
        dataType: 'json',
        success: function (data) {
            console.info("message received", data);

            player.initialize(videoEl[0], videoUrl, true);

            var controlbar = new ControlBar(player);
            controlbar.initialize();
        },
        error: function (xhr, type) {
            alert('Ajax error!')
        }
    });

});
