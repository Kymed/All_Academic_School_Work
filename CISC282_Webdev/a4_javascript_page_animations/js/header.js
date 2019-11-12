images = ["bee", "tractor", "meadow"];
current = 0;
mobile = true;

/**
 *  A function to switch the hero image in the header
 */
const switchImage = function() {
    current += 1;
    if (current == images.length) {
        current = 0;
    }

    $("#hero-container").attr("class", images[current]);
}

/**
 *  A function to fade in an overlay over the hero image, which triggers an image swap
 *  and an image fade out after 100ms
 */
const fadeInOverlay = function() {
    $(".overlay").fadeIn(2000, function() {
        setTimeout(function() {
            switchImage();
            fadeOutOverlay();
        }, 100);
    });
}

/**
 *  An function to fade an overlay out, that triggers the overlay fade in when done
 */
const fadeOutOverlay = function() {
    $(".overlay").fadeOut(2000, function() {
        fadeInOverlay();
    });
}

/**
 *  A function to turn the animation on and off depending on the width
 */
const activateAnimation = function() {
    if ($(this).width() >= 576 && mobile === true) {
        $(".overlay").css("visibility", "visible");
        fadeInOverlay();
        mobile = false;
    } 
    if ($(this).width() < 576 && mobile === false) {
        $(".overlay").css("visibility", "hidden");
        mobile = true;
    }
}

/**
 *  Begin the header animation
 */
$(document).ready(function() {
    $(".overlay").hide();
    activateAnimation();

    // Should the animation turn off when the screen is resized to a mobile width
    $(window).on('resize', function() {
        activateAnimation();
    });
})