function search() {
  var input = document.getElementById("search-input");
  if (input.value.length == 0) {
    alert("请输入搜索关键字");
    return;
  }
  var site = window.location.host;

  window.open("http://www.google.com/search?q=site:" + site + "+" + input.value);
}

function shareToWeibo() {
  var site = window.location.href;
  var title = document.title;
  window.open('http://service.weibo.com/share/share.php?url=' + site + '&title=' + title);
}

function shareToWechat() {
  var url = window.location.href;

  BootstrapDialog.show({
    size: BootstrapDialog.SIZE_SMALL,
    message: '<p>微信扫一扫二维码分享</p><div id="url-qr"></div>',
    onshown: function(dialogRef) {
      $("#url-qr").qrcode(url);
    },
  });
}

function openMyWechat(code) {
  BootstrapDialog.show({
    size: BootstrapDialog.SIZE_SMALL,
    message: '<p>微信扫一扫加我好友</p><div id="url-qr"></div>',
    onshown: function(dialogRef) {
      $("#url-qr").qrcode("http://weixin.qq.com/r/" + code);
    },
  });
}
