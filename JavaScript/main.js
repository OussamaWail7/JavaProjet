let navbar = $(".navbar");

$(window).scroll(function () {
    let oTop = $(".nav-point").offset().top - window.innerHeight;
    if ($(window).scrollTop() > oTop) {
        navbar.addClass("sticky");
    } else {
        navbar.removeClass("sticky");
    }
});