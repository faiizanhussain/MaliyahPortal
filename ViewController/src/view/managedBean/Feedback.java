package view.managedBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

public class Feedback {
    private RichPopup bindFeedbackPopup;
    private RichPopup bindFeedbackMaiPopup;
    private List<SelectItem> allValuesList;
    private static Set<String> selectedValuesList = new HashSet<>();
    private RichSelectManyCheckbox bindMultiCheckbox;
    private RichPanelFormLayout bindFeedbackResultPF;
    private RichPanelFormLayout bindFeedbackPF;
    private RichButton bindSubmitButton;
    private RichButton bindBackButton;

    public Feedback() {
    }


    public void setAllValuesList(List<SelectItem> allValuesList) {
        this.allValuesList = allValuesList;
    }

    public List<SelectItem> getAllValuesList() {
        if (allValuesList == null) {
                    allValuesList = new ArrayList<SelectItem>();
                    allValuesList.add(new SelectItem("Satisfied", "Satisfied"));
                    allValuesList.add(new SelectItem("Neutral", "Neutral"));
                    allValuesList.add(new SelectItem("Not satisfied", "Not satisfied"));
                }
        return allValuesList;
    }

    public void setBindFeedbackPopup(RichPopup bindFeedbackPopup) {
        this.bindFeedbackPopup = bindFeedbackPopup;
    }

    public static Set<String> getSelectedValuesList() {
        return selectedValuesList;
    }

    public void setSelectedValuesList(Set<String> selectedValuesList) {
        this.selectedValuesList = selectedValuesList;
    }


    public RichPopup getBindFeedbackPopup() {
        return bindFeedbackPopup;
    }

    public void closeFeedbackPopup(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindFeedbackPopup.hide();
    }

//    public String openFeedback() {
//        // Add event code here...
//        RichPopup.PopupHints hints = new RichPopup.PopupHints();
//        bindFeedbackMaiPopup.hide();
//        bindFeedbackPopup.show(hints);
//        return null;
//    }

    public void setBindFeedbackMaiPopup(RichPopup bindFeedbackMaiPopup) {
        this.bindFeedbackMaiPopup = bindFeedbackMaiPopup;
    }

    public RichPopup getBindFeedbackMaiPopup() {
        return bindFeedbackMaiPopup;
    }

    public void openMainFeedbackPopup(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindFeedbackMaiPopup.show(hints);
    }

    public void selectFeedbackoptionListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        List<String> valuesList = new ArrayList<String>();
        System.out.println("valueChangeEvent=="+valueChangeEvent.getNewValue());
        valuesList = (List<String>) valueChangeEvent.getNewValue();
        System.out.println("valuesList=="+valuesList);
        for(int i=0;i<valuesList.size();i++){
            selectedValuesList.add(valuesList.get(i));    
        }
        System.out.println("Final values="+selectedValuesList);
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("selectedList", selectedValuesList);
    }

    public String submitFeedback() {
          BindingContext bCtx = BindingContext.getCurrent();
          DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
          OperationBinding oper = DcCon.getOperationBinding("insertFeedbackValues");
          oper.getParamsMap().put("selectValuesList", selectedValuesList);
          oper.execute();
        bindMultiCheckbox.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindMultiCheckbox);
        return null;
    }

    public void setBindMultiCheckbox(RichSelectManyCheckbox bindMultiCheckbox) {
        this.bindMultiCheckbox = bindMultiCheckbox;
    }

    public RichSelectManyCheckbox getBindMultiCheckbox() {
        return bindMultiCheckbox;
    }

    public void setBindFeedbackResultPF(RichPanelFormLayout bindFeedbackResultPF) {
        this.bindFeedbackResultPF = bindFeedbackResultPF;
    }

    public RichPanelFormLayout getBindFeedbackResultPF() {
        return bindFeedbackResultPF;
    }

    public void setBindFeedbackPF(RichPanelFormLayout bindFeedbackPF) {
        this.bindFeedbackPF = bindFeedbackPF;
    }

    public RichPanelFormLayout getBindFeedbackPF() {
        return bindFeedbackPF;
    }

    public void viewResult(ActionEvent actionEvent) {
        // Add event code here....
        bindFeedbackPF.setVisible(false);
        bindSubmitButton.setVisible(false);
        bindFeedbackResultPF.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSubmitButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFeedbackPF);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFeedbackResultPF);
        bindBackButton.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBackButton);
    }

    public void setBindSubmitButton(RichButton bindSubmitButton) {
        this.bindSubmitButton = bindSubmitButton;
    }

    public RichButton getBindSubmitButton() {
        return bindSubmitButton;
    }

    public void setBindBackButton(RichButton bindBackButton) {
        this.bindBackButton = bindBackButton;
    }

    public RichButton getBindBackButton() {
        return bindBackButton;
    }

    public void backActionListener(ActionEvent actionEvent) {
        // Add event code here...
        bindFeedbackPF.setVisible(true);
        bindSubmitButton.setVisible(true);
        bindFeedbackResultPF.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSubmitButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFeedbackPF);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFeedbackResultPF);
        bindBackButton.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBackButton);
    }
}
