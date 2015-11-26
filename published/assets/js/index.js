$(document).ready(function() {
  adjust();
});

$(window).resize(function() {
  adjust();
});

function adjust() {
  var h = $(window).height();
  var w = $(window).width();
  var tiled = $('.tiled');
  var num = tiled.size();

  if (h > w) {
    tiled.removeClass('col-md-3');
    tiled.css('height', h / num + 'px');
    tiled.css('width', w + 'px');
  } else {
    tiled.addClass('col-md-3');
    tiled.css('height', h + 'px');
    tiled.css('width', 100 / num + '%');
  }

  $("#blog-bt").position({
    my: "center",
    at: "center",
    of: "#blog"
  });

  $("#rss-bt").position({
    my: "center",
    at: "center",
    of: "#rss"
  });

  $("#weibo-bt").position({
    my: "center",
    at: "center",
    of: "#weibo"
  });

  $("#github-bt").position({
    my: "center",
    at: "center",
    of: "#github"
  });
}
