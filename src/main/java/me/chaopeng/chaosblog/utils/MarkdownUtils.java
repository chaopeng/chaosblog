package me.chaopeng.chaosblog.utils;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.io.File;
import java.io.IOException;

/**
 * @author chaopeng
 */
public class MarkdownUtils {

    public static String toHTML(String md) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MULTI_MARKDOWN);
        options.set(Parser.EXTENSIONS,
                Lists.newArrayList(TocExtension.create(), TablesExtension.create()));
        options.set(HtmlRenderer.FENCED_CODE_LANGUAGE_CLASS_PREFIX, "");

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        return renderer.render(parser.parse(md));
    }

}
