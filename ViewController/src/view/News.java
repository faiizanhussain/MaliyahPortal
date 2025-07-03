package view;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

public class News {
    private RichPanelGroupLayout bindNews1;
    private RichPanelGroupLayout bindNews5;

    public News() {
    }

    public String ForwardToNextImage() {
        // Add event code here...
        bindNews1.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNews1);
        return null;
    }

    public String backToPreviousImage() {
        // Add event code here...
        bindNews5.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNews5);
        return null;
    }

    public void setBindNews1(RichPanelGroupLayout bindNews1) {
        this.bindNews1 = bindNews1;
    }

    public RichPanelGroupLayout getBindNews1() {
        return bindNews1;
    }

    public void setBindNews5(RichPanelGroupLayout bindNews5) {
        this.bindNews5 = bindNews5;
    }

    public RichPanelGroupLayout getBindNews5() {
        return bindNews5;
    }
}
