package com.risc.boot.test;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.ParagraphStyle;
import com.deepoove.poi.data.style.Style;



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.deepoove.poi.policy.ParagraphRenderPolicy;
import com.deepoove.poi.policy.PictureRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.xwpf.XWPFHighlightColor;
import com.risc.boot.common.poi.LoopParagraphRenderPolicy;
import com.risc.boot.modules.system.bo.SysUser;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.junit.Test;

/**
 * @auhtor 李良发
 * @since 2025/6/13 15:01
 */
public class WordTest {

    @Test
    public void test() {
        // 构造文本样式
        Style textStyle = new Style();
        textStyle.setFontSize(12);
        textStyle.setColor("000000");
        textStyle.setHighlightColor(XWPFHighlightColor.YELLOW);

        // 构造图片数据
        PictureRenderData picture = Pictures.ofLocal("/Users/liangfali/Pictures/1.png")
                .size(100, 100) // 宽、高（像素）
                .create();

        // 表头
        ParagraphRenderData p1 = new ParagraphRenderData().addText("课程");
        ParagraphRenderData p2 = new ParagraphRenderData().addText("成绩");

        CellRenderData c1 = new CellRenderData().addParagraph(p1);
        CellRenderData c2 = new CellRenderData().addParagraph(p2);

        RowRenderData header = new RowRenderData();
        header.addCell(c1);
        header.addCell(c2);

        // 数据行1 - 文字+文字+图片
        ParagraphRenderData p4 = new ParagraphRenderData().addText("语文");
        ParagraphRenderData p5 = new ParagraphRenderData().addText("90");

        CellRenderData c4 = new CellRenderData().addParagraph(p4);
        CellRenderData c5 = new CellRenderData().addParagraph(p5);

        RowRenderData row1 = new RowRenderData();
        row1.addCell(c4);
        row1.addCell(c5);

        // 数据行2
        ParagraphRenderData p7 = new ParagraphRenderData().addText("数学");
        ParagraphRenderData p8 = new ParagraphRenderData().addText("95");

        CellRenderData c7 = new CellRenderData().addParagraph(p7);
        CellRenderData c8 = new CellRenderData().addParagraph(p8);

        RowRenderData row2 = new RowRenderData();
        row2.addCell(c7);
        row2.addCell(c8);

        // 表格数据
        TableRenderData table = new TableRenderData();
        table.addRow(header);
        table.addRow(row1);
        table.addRow(row2);

        List<String> personInfoList = new ArrayList<>();
        personInfoList.add("申请人：张三，性别：男，出生日期：2025年6月12日，地址：上海，联系电话：133888888888。");
        personInfoList.add("被申请人：李四，性别：女，出生日期：2025年6月12日，地址：上海，联系电话：133888888888。");

        ParagraphRenderData personInfo1 = new ParagraphRenderData().addText(personInfoList.get(0));
        ParagraphRenderData personInfo2 = new ParagraphRenderData().addText(personInfoList.get(1));


        // 创建样式
        ParagraphStyle style = new ParagraphStyle();
        // 设置首行缩进
        style.setIndentFirstLineChars(2.0);
        // 可选：设置对齐方式（如左对齐）
        style.setAlign(ParagraphAlignment.LEFT);
        // 应用样式
        personInfo1.setParagraphStyle(style);
        personInfo2.setParagraphStyle(style);

        List<ParagraphRenderData> personInfo = new ArrayList<>();
        personInfo.add(personInfo1);
        personInfo.add(personInfo2);

        // 数据替换
        Map<String, Object> data = new HashMap<>();
        data.put("name", Texts.of("张三").style(textStyle).create());
        data.put("sex", "男");
        data.put("date", "2025年6月13日");
        data.put("photo", picture);
        data.put("table", table);
        data.put("人员信息", personInfo);

        Configure configure = Configure.builder()
                .bind("人员信息", new LoopParagraphRenderPolicy())
                .build();

        // 渲染模板
        XWPFTemplate template = XWPFTemplate.compile("/Users/liangfali/Desktop/template.docx", configure).render(data);
        try {
            template.writeAndClose(new FileOutputStream("/Users/liangfali/Desktop/out.docx"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
