$(window).on('load pageshow', function () {
    $('body').fadeIn();
});
$("a:not([href*=\\#])").click(function() {
    if (!$(this).attr('target')) {
        $('body').fadeOut();
        let url = $(this).attr('href');
        window.setTimeout(function() {
            window.location.href = url;
        }, 500);
        return false;
    }
});