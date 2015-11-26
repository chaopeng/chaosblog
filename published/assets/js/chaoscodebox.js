function prettify() {
  $('pre>code').each(function(index) {
    var lang = $(this).attr('class');
    if (lang == null) lang = "";
    lang = lang.replace('{', '').replace('}', '');
    var preNode = $(this.parentNode);

    preNode.addClass('prettyprint linenums');
    preNode.attr('id', 'code' + index);
    preNode.wrap('<div class="codebox"></div>');

    var button = '<button style="float:right" class="copy" data-clipboard-target="#code' + index + '">COPY</button>';
    var s = '<span class="code-lang"><i class="fa fa-code"></i> ' + lang.toUpperCase() + '</span>';
    s += button;

    var codebox = $(this.parentNode.parentNode);
    codebox.prepend(s);
  });

  prettyPrint();
}
