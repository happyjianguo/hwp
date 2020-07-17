package com.hwp.admin.app.event;

import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class JsxmsqSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189241214429149L;

    private Xmxxgl xmxxgl;

    public JsxmsqSendEmailEvent(Object source) {
        super(source);
    }

    public Xmxxgl getXmxxgl() {
        return xmxxgl;
    }

    public void setXmxxgl(Xmxxgl xmxxgl) {
        this.xmxxgl = xmxxgl;
    }
}
