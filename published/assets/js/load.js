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


