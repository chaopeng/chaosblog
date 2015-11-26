var duoshuoQuery = {short_name:"chaopeng"};
(function() {
  var ds = document.createElement('script');
  ds.type = 'text/javascript';ds.async = true;
  ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
  ds.charset = 'UTF-8';
  (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(ds);
})();

$(document).ready(function() {
  // code highlight
  prettifyCode();
  // table
  prettifyTable();
  // pin sidebar
  pinSidebar();
  // back to top
  backToTop();
});

function backToTop() {
  if ($('#back-to-top').length) {
    var scrollTrigger = 100,
      backToTop = function() {
        var scrollTop = $(window).scrollTop();
        if (scrollTop > scrollTrigger) {
          $('#back-to-top').addClass('show');
        } else {
          $('#back-to-top').removeClass('show');
        }
      };
    backToTop();
    $(window).on('scroll', function() {
      backToTop();
    });
    $('#back-to-top').on('click', function(e) {
      e.preventDefault();
      $('html,body').animate({
        scrollTop: 0
      }, 700);
    });
  }
}

function prettifyCode() {
  prettify();
  var clipboard = new Clipboard('.copy');
  $('.copy').qtip({
    position: {
      at: 'top center',
      my: 'bottom center'
    },
    style: {
      classes: 'qtip-dark'
    },
    content: 'copied!',
    show: {
      event: 'click'
    },
    hide: {
      event: 'mouseleave',
      inactive: 1000
    }
  });
  clipboard.on('success', function(e) {
    e.clearSelection();
  });
}

function prettifyTable() {
  $('table').addClass('table table-hover');
}

function pinSidebar() {
  $('.sidebar').stickThis({
    top: 70,
    minscreenwidth: 1000
  });
}


