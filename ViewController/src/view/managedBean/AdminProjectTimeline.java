package view.managedBean;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

public class AdminProjectTimeline {
    private RichPopup successPopup;
    private RichPopup bindProjectTimelinePopup;
    private RichTable bindProjectTimeLineTable;
    private RichPopup bindTimelineDeleteConfirmationPopup;
    private RichPopup bindDeleteSuccessPopup;

    public AdminProjectTimeline() {
    }

    public void submitTimeLine(ActionEvent actionEvent) {
        // Add event code here...
        try {
            BindingContext bCtx = BindingContext.getCurrent();
            DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
            OperationBinding oper = DcCon.getOperationBinding("Commit");
            oper.execute();
            bindProjectTimelinePopup.hide();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            successPopup.show(hints);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindProjectTimeLineTable);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void setSuccessPopup(RichPopup successPopup) {
        this.successPopup = successPopup;
    }

    public RichPopup getSuccessPopup() {
        return successPopup;
    }

    public void closeSuccessPopup(ActionEvent actionEvent) {
        // Add event code here...
        successPopup.hide();
    }

    public void addProjectTimeline(ActionEvent actionEvent) {
        // Add event code here...
        try {
            BindingContext bCtx = BindingContext.getCurrent();
            DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
            OperationBinding oper = DcCon.getOperationBinding("CreateInsert");
            oper.execute();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            bindProjectTimelinePopup.show(hints);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void setBindProjectTimelinePopup(RichPopup bindProjectTimelinePopup) {
        this.bindProjectTimelinePopup = bindProjectTimelinePopup;
    }

    public RichPopup getBindProjectTimelinePopup() {
        return bindProjectTimelinePopup;
    }

    public String closeTimeLinePopup() {
        // Add event code here...
        bindProjectTimelinePopup.hide();
        return null;
    }

    public void setBindProjectTimeLineTable(RichTable bindProjectTimeLineTable) {
        this.bindProjectTimeLineTable = bindProjectTimeLineTable;
    }

    public RichTable getBindProjectTimeLineTable() {
        return bindProjectTimeLineTable;
    }

    public void editProjectTimeline(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindProjectTimelinePopup.show(hints);
    }

    public String deleteTimeline() {
        // Add event code here...
        try {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            bindTimelineDeleteConfirmationPopup.show(hints);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return null;
    }

    public void closeTimelinepopup(ActionEvent actionEvent) {
        // Add event code here...
        try {
            String buttonFlag = (String) ADFContext.getCurrent()
                                                   .getPageFlowScope()
                                                   .get("clkaddButton");
            if (buttonFlag.equalsIgnoreCase("addButton")) {
                DCBindingContainer dcBinding =
                    (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding iterBind = (DCIteratorBinding) dcBinding.get("ProjectTimelineNewView1Iterator");
                Row r = iterBind.getCurrentRow();
                r.remove();
                iterBind.getViewObject().executeQuery();
            }
               bindProjectTimelinePopup.hide();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindProjectTimeLineTable);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        }

    public void setBindTimelineDeleteConfirmationPopup(RichPopup bindTimelineDeleteConfirmationPopup) {
        this.bindTimelineDeleteConfirmationPopup = bindTimelineDeleteConfirmationPopup;
    }

    public RichPopup getBindTimelineDeleteConfirmationPopup() {
        return bindTimelineDeleteConfirmationPopup;
    }

    public void closeTimelineDeleteConfirmationPopup(ActionEvent actionEvent) {
        // Add event code here...
        bindTimelineDeleteConfirmationPopup.hide();
    }

    public String deleteTimelinefromTable() {
        // Add event code here...
        try {
            BindingContext bCtx = BindingContext.getCurrent();
            DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
            OperationBinding oper = DcCon.getOperationBinding("Delete");
            oper.execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindProjectTimeLineTable);
            bindTimelineDeleteConfirmationPopup.hide();
            RichPopup.PopupHints hints = new RichPopup.PopupHints(); 
           bindDeleteSuccessPopup.show(hints);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return "commit";
    }

    public void setBindDeleteSuccessPopup(RichPopup bindDeleteSuccessPopup) {
        this.bindDeleteSuccessPopup = bindDeleteSuccessPopup;
    }

    public RichPopup getBindDeleteSuccessPopup() {
        return bindDeleteSuccessPopup;
    }

    public void closeDeleteSuccessPopup(ActionEvent actionEvent) {
        // Add event code here...
        bindDeleteSuccessPopup.hide();
    }
}
