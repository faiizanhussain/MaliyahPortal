package view.managedBean;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

public class ProjectTimeLine {
    private RichPopup bindProjectTimelinePopup;
    private RichTable bindProjecttimelineTable;

    public ProjectTimeLine() {
    }

    public void addProjectTimeLine(ActionEvent actionEvent) {
        // Add event code here...
        try {
          BindingContext bCtx = BindingContext.getCurrent();
          DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
          OperationBinding oper = DcCon.getOperationBinding("CreateInsert");
          oper.execute();
          openPopup(bindProjectTimelinePopup);
        } catch(Exception e) {
          // TODO: Add catch code
          e.printStackTrace();
        }
    }

    public void updateProjectTimeline(ActionEvent actionEvent) {
        // Add event code here...
      //  if (bindAgentTable.getEstimatedRowCount() > 0) {
          openPopup(bindProjectTimelinePopup);
     //   }
    }

    public void setBindProjectTimelinePopup(RichPopup bindProjectTimelinePopup) {
        this.bindProjectTimelinePopup = bindProjectTimelinePopup;
    }

    public RichPopup getBindProjectTimelinePopup() {
        return bindProjectTimelinePopup;
    }
    public void closePopup(RichPopup popup) {
      RichPopup.PopupHints hints = new RichPopup.PopupHints();
      popup.hide();

    }

    public void openPopup(RichPopup popup) {
      RichPopup.PopupHints hints = new RichPopup.PopupHints();
      popup.show(hints);
    }

    public void addProjectDetails(ActionEvent actionEvent) {
        // Add event code here...
        BindingContext bCtx = BindingContext.getCurrent();
        DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
        OperationBinding oper = DcCon.getOperationBinding("Commit");
        oper.execute();
        closePopup(bindProjectTimelinePopup);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindProjecttimelineTable);
        
    }

    public void close(ActionEvent actionEvent) {
        // Add event code here...
        BindingContext bCtx = BindingContext.getCurrent();
        DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
        OperationBinding oper = DcCon.getOperationBinding("Rollback");
        oper.execute();
        closePopup(bindProjectTimelinePopup);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindProjecttimelineTable);
    }

    public void setBindProjecttimelineTable(RichTable bindProjecttimelineTable) {
        this.bindProjecttimelineTable = bindProjecttimelineTable;
    }

    public RichTable getBindProjecttimelineTable() {
        return bindProjecttimelineTable;
    }
}
