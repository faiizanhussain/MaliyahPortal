package view.managedBean;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

public class ContactUs {
    private RichPopup bindContactusPopup;
    private RichInputText bindFirstName;
    private RichInputText bindEmail;
    private RichInputText bindPhone;
    private RichInputText bindQuery;
    private RichPanelFormLayout bindContactForm;
    private RichInputText bindNameOfMinistry;

    public ContactUs() {
    }

    public void setBindContactusPopup(RichPopup bindContactusPopup) {
        this.bindContactusPopup = bindContactusPopup;
    }

    public RichPopup getBindContactusPopup() {
        return bindContactusPopup;
    }

    public void closeContactusPopup(ActionEvent actionEvent) {
        // Add event code here...
        bindFirstName.setValue("");
        bindQuery.setValue("");
        bindFirstName.resetValue();
        bindQuery.resetValue();
        bindNameOfMinistry.setValue("");
        bindNameOfMinistry.resetValue();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindContactusPopup.hide();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindFirstName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindQuery);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNameOfMinistry);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindContactForm);
    }

    public String submit() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindContactusPopup.show(hints);
        return null;
    }

    public void setBindFirstName(RichInputText bindFirstName) {
        this.bindFirstName = bindFirstName;
    }

    public RichInputText getBindFirstName() {
        return bindFirstName;
    }

    public void setBindEmail(RichInputText bindEmail) {
        this.bindEmail = bindEmail;
    }

    public RichInputText getBindEmail() {
        return bindEmail;
    }

    public void setBindPhone(RichInputText bindPhone) {
        this.bindPhone = bindPhone;
    }

    public RichInputText getBindPhone() {
        return bindPhone;
    }

    public void setBindQuery(RichInputText bindQuery) {
        this.bindQuery = bindQuery;
    }

    public RichInputText getBindQuery() {
        return bindQuery;
    }

    public void setBindContactForm(RichPanelFormLayout bindContactForm) {
        this.bindContactForm = bindContactForm;
    }

    public RichPanelFormLayout getBindContactForm() {
        return bindContactForm;
    }

    public void setBindNameOfMinistry(RichInputText bindNameOfMinistry) {
        this.bindNameOfMinistry = bindNameOfMinistry;
    }

    public RichInputText getBindNameOfMinistry() {
        return bindNameOfMinistry;
    }
}
