package com.risc.boot.test;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.ParagraphStyle;
import com.deepoove.poi.data.style.Style;



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.deepoove.poi.util.RegexUtils;
import com.deepoove.poi.xwpf.XWPFHighlightColor;
import com.risc.boot.common.poi.LoopParagraphRenderPolicy;
import com.risc.boot.common.poi.StaticTextPolicy;
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

        String str1 = "1.《人民币流动资金贷款合同》第四条约定：贷款利率为年利率5.22%；利率调整采用固定利率，贷款期内利率保持不变；逾期还款的，在前述贷款利率基础上加收50%作为罚息；" +
                "   2.《某某合作协议》项下的融资利率采用固定利率，为年化单利3.75%和3.95%，逾期还款的，在前述贷款利率基础上加收50%作为罚息；" +
                "   3.《某某银行电子银行承兑汇票承兑协议》项下第十条约定：某某公司未能按本协议约定及时支付票款/补足保证金致使某某福州分行发生垫款的，某某福州分行可以要求某某公司立即支付应付的票款，并自某某银行垫款之日起，以中信福州分行垫款金额为基数，按日利率0.05％的计息标准向某某银行计收罚息，直至某某公司付清某某福州分行全部垫款；\n" +
                "   4.《某某银行贷款合同》第十五条约定，自逾期之日起，按贷款合同总额5%的标准支付违约金。";

        List<ParagraphRenderData> list1 = getList(str1);

        String str2 = "2023年1月1日，被告福建某某股份有限公司与原告某某银行股份有限公司福州分行在福州市鼓楼区签订了编号为（2023）某某贷字第2023001号《综合授信合同》。\n" +
                "2023年1月1日，被告XXX与原告XXX在福州市鼓楼区签订了《XXX抵押合同》。";
        List<ParagraphRenderData> list2 = getList(str2);

        String str3 = "原告";

        // 数据替换
        Map<String, Object> data = new HashMap<>();
        data.put("name", Texts.of("张三").style(textStyle).create());
        data.put("sex", "男");
        data.put("date", "2025年6月13日");
        data.put("photo", picture);
        data.put("table", table);
        data.put("人员信息", personInfo);
        data.put("2.1利息、罚息、复利、违约金及其暂计日", "请求判令被告林某某、福建某某有限公司向原告支付截至2025年1月1日尚欠利息12345元、罚息1234元、复利123元、违约金4321元");
        data.put("81请求合同依据", str1);
        data.put("11合同签订情况", str2);
        data.put("12主债权合同名称", "编号为（2023）某某贷字第2023001号《综合授信合同》");
        data.put("原告", "福建某某股份有限公司");
        data.put("被告", "李四");
        data.put("案由", "22323");

        ConfigureBuilder builder = Configure.builder();
        builder.bind("人员信息", new LoopParagraphRenderPolicy());
//        builder.bind("81请求合同依据", new LoopParagraphRenderPolicy());
//        builder.bind("11合同签订情况", new LoopParagraphRenderPolicy());
//        builder.bind("@2.1利息、罚息、复利、违约金及其暂计日", new StaticTextPolicy("2.1利息、罚息、复利、违约金及其暂计日"));

        // 渲染模板
        XWPFTemplate template1 = XWPFTemplate.compile("/Users/liangfali/Desktop/template.docx", builder.build()).render(data);
//        XWPFTemplate template2 = XWPFTemplate.compile("/Users/liangfali/Desktop/template.docx", configure).render(data);
        try {
            template1.writeAndClose(new FileOutputStream("/Users/liangfali/Desktop/out.docx"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ParagraphRenderData> getList(String str) {
        List<ParagraphRenderData> list = new ArrayList<>();
        String[] split = str.split("\n");
        for (String s : split) {
            ParagraphRenderData paragraphRenderData = new ParagraphRenderData();
            paragraphRenderData.addText(s);
            list.add(paragraphRenderData);
        }
        return list;
    }
}
