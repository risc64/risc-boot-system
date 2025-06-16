package com.risc.boot.common.util;

import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * 富文本输出word
 * @author 李良发
 * @version 1.0.0
 * @since 2022/4/19 14:42
 */
public class HtmlToWordUtil {

    public static void main(String[] args) {


    }

    /**
     * 富文本复杂html生成word
     * @param html html内容
     * @param fileHost 图片域名
     * @return
     */
    public static XWPFDocument htmlToDocument(String html, String fileHost) {
        try {
            // 解析HTML
            Document document = Jsoup.parse(html);
            // 创建Word文档
            XWPFDocument wordDocument = new XWPFDocument();
            // 遍历 HTML 元素，逐个处理
            List<Element> elements = document.body().children();
            for (Element element : elements) {
                // 处理段落
                if (element.tagName().equals("p") || element.tagName().matches("h[1-6]")) {
                    XWPFParagraph para = wordDocument.createParagraph();
                    // 检查该段落是否有子元素（如 <font> 标签），否则直接处理文本内容
                    if (element.children().isEmpty()) {
                        // 如果没有子元素，直接创建一个XWPFRun并添加文本
                        XWPFRun xwpfRun = para.createRun();
                        xwpfRun.setText(element.text(), 0); // 设置纯文本
                        // **标题字体大小**
                        if (element.tagName().equals("h1")) {
                            xwpfRun.setBold(true);
                            xwpfRun.setFontSize(24);
                        } else if (element.tagName().equals("h2")) {
                            xwpfRun.setBold(true);
                            xwpfRun.setFontSize(20);
                        } else if (element.tagName().equals("h3")) {
                            xwpfRun.setBold(true);
                            xwpfRun.setFontSize(18);
                        } else if (element.tagName().equals("h4")) {
                            xwpfRun.setBold(true);
                            xwpfRun.setFontSize(16);
                        } else if (element.tagName().equals("h5")) {
                            xwpfRun.setBold(true);
                            xwpfRun.setFontSize(16);
                        } else if (element.tagName().equals("h6")) {
                            xwpfRun.setBold(true);
                            xwpfRun.setFontSize(16);
                        }
                    } else {
                        // 如果有子元素，按之前的逻辑处理
                        List<Node> children = element.childNodes();

                        // 遍历所有子元素
                        for (Node child : children) {
                            XWPFRun xwpfRun = para.createRun();

                            if (child.nodeName().equals("#text")) {
                                // 如果是文本节点，直接设置文本内容
                                xwpfRun.setText(child.toString(), 0);
                            } else if (child instanceof Element) {
                                // 如果是元素节点，比如 <font> 标签
                                Element elementChild = (Element) child;
                                if (elementChild.tagName().equals("font") && elementChild.hasAttr("color")) {
                                    // 如果是 <font> 标签且有 color 属性，设置字体颜色
                                    String color = elementChild.attr("color");
                                    xwpfRun.setColor(color.substring(1));  // 去掉 "#" 字符
                                    xwpfRun.setText(elementChild.text(), 0);  // 设置带颜色的文本
                                }
                            }
                        }
                    }
                }
                // 处理水平线 <hr/>
                else if (element.tagName().equals("hr")) {
                    XWPFParagraph para = wordDocument.createParagraph();
                    para.setBorderBottom(Borders.THICK);
                }
                // 处理图片
                else if (element.tagName().equals("img")) {
                    String src = element.attr("src");
                    if (!src.startsWith("http")) {
                        src = fileHost + src;  // 拼接域名
                    }
                    URL imageUrl = new URL(src);
                    InputStream inputStream = imageUrl.openStream();
                    byte[] imageBytes = IOUtils.toByteArray(inputStream);
                    InputStream inputStream1 = imageUrl.openStream();
                    // 使用 ImageIO 读取图片
                    BufferedImage bufferedImage = ImageIO.read(inputStream1);
                    // 获取图片的宽度和高度
                    int imageWidth = bufferedImage.getWidth();
                    int imageHeight = bufferedImage.getHeight();
                    // 定义 Word 页面尺寸 (A4: 11906 x 16838 EMU, 8.27 x 11.69 inches)
                    int pageWidthEMU = Units.toEMU(6.5 * 72);  // 6.5 英寸（假设页边距各 0.9 英寸）
                    int maxWidth = pageWidthEMU; // 最大允许宽度

                    int scaledWidthEMU = Units.toEMU(imageWidth);
                    int scaledHeightEMU = Units.toEMU(imageHeight);

                    // **如果图片超出页面宽度，则按比例缩放**
                    if (scaledWidthEMU > maxWidth) {
                        double scale = (double) maxWidth / scaledWidthEMU;
                        scaledWidthEMU = maxWidth;
                        scaledHeightEMU = (int) (scaledHeightEMU * scale); // 高度按比例缩放
                    }
                    XWPFParagraph para = wordDocument.createParagraph();
                    XWPFRun run = para.createRun();
                    run.addPicture(new ByteArrayInputStream(imageBytes), XWPFDocument.PICTURE_TYPE_PNG, src, scaledWidthEMU, scaledHeightEMU);  // 设置图片大小
                }
                // 处理表格
                else if (element.tagName().equals("table")) {
                    XWPFTable wordTable = wordDocument.createTable();
                    // 处理表格行
                    List<Element> rows = element.select("tr");
                    for (int i = 0; i < rows.size(); i++) {
                        Element e = rows.get(i);
                        XWPFTableRow wordRow = wordTable.getRow(i);
                        if (wordRow == null) {
                            wordRow = wordTable.createRow();
                        }
                        List<Element> cells = e.select("th, td");
                        for (int j = 0; j < cells.size(); j++) {
                            Element cell = cells.get(j);
                            XWPFTableCell wordCell = wordRow.getCell(j);;
                            if (wordCell == null) {
                                wordCell = wordRow.createCell();
                            }
                            if (cell.tagName().equals("th")) {
                                // **背景色（灰色）**
                                wordCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER); // 垂直居中
                                wordCell.setColor("E0E0E0"); // 灰色背景 (HEX 颜色码)
                                // 设置单元格文本
                                XWPFParagraph para = wordCell.getParagraphs().get(0);
                                XWPFRun run = para.createRun();
                                run.setText(cell.text());
                                // **字体加粗**
                                run.setBold(true);
                            } else {
                                wordCell.setText(cell.text());
                            }
                        }

                    }

                }
            }

            // 保存Word文档
            return wordDocument;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}

