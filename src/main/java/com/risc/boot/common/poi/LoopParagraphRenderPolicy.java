package com.risc.boot.common.poi;

import com.deepoove.poi.data.ParagraphRenderData;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.ParagraphStyle;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.AbstractRenderPolicy;
import com.deepoove.poi.render.RenderContext;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;

import java.util.List;

/**
 * 插入多个段落，每段段落可能包含多个文本块（含样式）
 */
public class LoopParagraphRenderPolicy extends AbstractRenderPolicy<List<ParagraphRenderData>> {

    @Override
    public void doRender(RenderContext<List<ParagraphRenderData>> context) throws Exception {
        List<ParagraphRenderData> data = context.getData();
        if (data == null || data.isEmpty()) return;

        XWPFRun placeholderRun = context.getRun();
        XWPFParagraph refParagraph = placeholderRun.getParagraph();
        IBody body = refParagraph.getBody();

        XmlCursor cursor = refParagraph.getCTP().newCursor();

        for (ParagraphRenderData paraData : data) {
            XWPFParagraph newPara = body.insertNewParagraph(cursor);
            newPara.getCTP().setPPr(refParagraph.getCTP().getPPr()); // 可选：复制样式

            ParagraphStyle paraStyle = paraData.getParagraphStyle();

            if (paraStyle != null) {
                // 复制基础段落样式
                newPara.getCTP().setPPr(refParagraph.getCTP().getPPr());

                // 设置段落的额外样式
                applyParagraphStyle(newPara, paraData.getParagraphStyle());
            }


            List<RenderData> contents = paraData.getContents();
            for (RenderData renderData : contents) {
                if (renderData instanceof TextRenderData) {
                    TextRenderData text = (TextRenderData) renderData;
                    XWPFRun run = newPara.createRun();
                    run.setText(text.getText());
                    applyTextStyle(run, text.getStyle());
                }
                // 可扩展其他类型 RenderData
            }
        }

        // 删除原占位段落
        int pos = getParagraphIndex(body, refParagraph);
        if (pos >= 0) {
            if (body instanceof XWPFDocument) {
                ((XWPFDocument) body).removeBodyElement(pos);
            }
        }
        clearPlaceholder(context, true);
    }

    @Override
    protected boolean validate(List<ParagraphRenderData> data) {
        return data != null && !data.isEmpty();
    }

    // 获取指定段落在 body 中的位置
    private int getParagraphIndex(IBody body, XWPFParagraph target) {
        List<IBodyElement> elements = body.getBodyElements();
        for (int i = 0; i < elements.size(); i++) {
            IBodyElement e = elements.get(i);
            if (e.getElementType() == BodyElementType.PARAGRAPH &&
                    e instanceof XWPFParagraph &&
                    e == target) {
                return i;
            }
        }
        return -1;
    }

    // 设置文本样式
    private void applyTextStyle(XWPFRun run, Style style) {
        if (style == null) return;
        if (Boolean.TRUE.equals(style.isBold())) run.setBold(true);
        if (Boolean.TRUE.equals(style.isItalic())) run.setItalic(true);
        if (Boolean.TRUE.equals(style.isStrike())) run.setStrikeThrough(true);
        run.setFontSize(style.getFontSize());
        if (style.getFontFamily() != null) run.setFontFamily(style.getFontFamily());
        if (style.getColor() != null) run.setColor(style.getColor());

        // 可扩展支持下划线、突出显示、缩进等
        if (style.getUnderlinePatterns() != null) {
            run.setUnderline(style.getUnderlinePatterns());
        }
    }

    // 设置段落样式
    private void applyParagraphStyle(XWPFParagraph paragraph, ParagraphStyle paraStyle) {
        if (paraStyle == null) return;
        if (paraStyle.getStyleId() != null) {
            paragraph.setStyle(paraStyle.getStyleId());
        }
        if (paraStyle.getAlign() != null) {
            paragraph.setAlignment(paraStyle.getAlign());
        }
        if (paraStyle.getIndentFirstLineChars() != null) {
            int indentTwips =(int) ( Math.round(paraStyle.getIndentFirstLineChars()) * 200) ; // 每个字符约等于 200 twip
            paragraph.setIndentationFirstLine(indentTwips);
        }
        if (paraStyle.getIndentLeftChars() != null) {
            int indentTwips =(int) ( Math.round(paraStyle.getIndentLeftChars()) * 200) ; // 每个字符约等于 200 twip
            paragraph.setIndentationLeft(indentTwips);
        }
        if (paraStyle.getIndentRightChars() != null) {
            int indentTwips =(int) ( Math.round(paraStyle.getIndentRightChars()) * 200) ; // 每个字符约等于 200 twip
            paragraph.setIndentationRight(indentTwips);
        }
        if (paraStyle.getIndentHangingChars() != null) {
            int indentTwips =(int) ( Math.round(paraStyle.getIndentHangingChars()) * 200) ; // 每个字符约等于 200 twip
            paragraph.setIndentationHanging(indentTwips);
        }
    }

}
