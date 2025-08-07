package com.risc.boot.common.poi;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.policy.RenderPolicy;
import com.deepoove.poi.template.ElementTemplate;
import com.deepoove.poi.template.run.RunTemplate;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @auhtor 李良发
 * @since 2025/7/10 17:33
 */
public class StaticTextPolicy implements RenderPolicy {
    private final String placeholder;

    public StaticTextPolicy(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
        RunTemplate runTemplate = (RunTemplate) eleTemplate;
        XWPFRun run = runTemplate.getRun();
        run.setText(data == null ? "" : data.toString(), 0);
    }
}
