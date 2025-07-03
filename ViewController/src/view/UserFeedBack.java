package view;

import java.util.Random;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.PollEvent;

public class UserFeedBack {
    private RichInputText feedbackComments;
    String Glad = "";
    String happy = "";
    String Neutral = "";
    String Dissatisfied = "";
    String ExtremelyDissatisfied = "";
    private RichPoll poll;
    private boolean popupShown = false;
    private Boolean feedbackFlag = false;
    private RichPopup feedbackPopup;
    private RichPopup bindSuccessPopup;
    private RichPopup mandatoryPopup;
    private RichButton bindGladButton;
    private RichButton bindSatisfiedButton;
    private RichButton bindNutralButton;
    private RichButton bindDissatisfiedButton;
    private RichButton bindExtremelyDissatisfiedButton;
    public UserFeedBack() {
    }
   
    public void submitUserFeedback(ActionEvent actionEvent) {
        
        // Add event code here...
        if(Glad.equalsIgnoreCase("") && happy.equalsIgnoreCase("") && Neutral.equalsIgnoreCase("") && Dissatisfied.equalsIgnoreCase("") && ExtremelyDissatisfied.equalsIgnoreCase("")){
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            mandatoryPopup.show(hints);
        }else {    
        int user_id = getRadomNumber();
        String userName = "TEST_USER";
        String feedbackComment = feedbackComments.getValue().toString();
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
                // Get the method binding for callGetFeedbackFlag
                OperationBinding methodBinding = bindings.getOperationBinding("addUserfeedback");

                // Set the parameter (userId) for the method call
        methodBinding.getParamsMap().put("user_id", user_id);
        methodBinding.getParamsMap().put("user_name", userName);
        methodBinding.getParamsMap().put("Glad", Glad);
        methodBinding.getParamsMap().put("happy", happy);
        methodBinding.getParamsMap().put("Neutral", Neutral);
        methodBinding.getParamsMap().put("Dissatisfied", Dissatisfied);
        methodBinding.getParamsMap().put("ExtremelyDissatisfied", ExtremelyDissatisfied);
        methodBinding.getParamsMap().put("user_comment", feedbackComment);

                // Execute the method binding (this will call the stored procedure)
        String result = (String) methodBinding.execute(); 
        System.out.println("result=="+result);
        if(result.equalsIgnoreCase("success")){
            feedbackPopup.hide();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            bindSuccessPopup.show(hints);
        }
        }
    }


    public int getRadomNumber(){
        Random r= new Random();
        int r1 = r.nextInt(1000);
      return r1;  
    }
    public void gladListener(ActionEvent actionEvent) {
        // Add event code here...
        Glad = "مسرور";
        bindGladButton.setIcon("/images/glad.svg");
        
        bindSatisfiedButton.setIcon("/images/sentiment_satisfied.svg");
        bindNutralButton.setIcon("/images/sentiment_neutral.svg");
        bindDissatisfiedButton.setIcon("/images/sentiment_dissatisfied.svg");
        bindExtremelyDissatisfiedButton.setIcon("/images/sentiment_extremely_dissatisfied.svg");
        
         happy = "";
        Neutral = "";
        Dissatisfied = "";
        ExtremelyDissatisfied = "";
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindGladButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNutralButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDissatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindExtremelyDissatisfiedButton);
    }

    public void happyListener(ActionEvent actionEvent) {
        // Add event code here...
        happy = "سعید";
        bindSatisfiedButton.setIcon("/images/Happy.svg");
        bindGladButton.setIcon("/images/mood.svg");
        bindNutralButton.setIcon("/images/sentiment_neutral.svg");
        bindDissatisfiedButton.setIcon("/images/sentiment_dissatisfied.svg");
        bindExtremelyDissatisfiedButton.setIcon("/images/sentiment_extremely_dissatisfied.svg");
        
        Glad = "";
        Neutral = "";
        Dissatisfied = "";
        ExtremelyDissatisfied = "";
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindGladButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNutralButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDissatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindExtremelyDissatisfiedButton);
    }

    public void neutralListener(ActionEvent actionEvent) {
        // Add event code here...
        Neutral = "حیادی";
        bindNutralButton.setIcon("/images/Neutral.svg");
        
        bindSatisfiedButton.setIcon("/images/sentiment_satisfied.svg");
        bindGladButton.setIcon("/images/mood.svg");
        bindDissatisfiedButton.setIcon("/images/sentiment_dissatisfied.svg");
        bindExtremelyDissatisfiedButton.setIcon("/images/sentiment_extremely_dissatisfied.svg");
        
        happy = "";
        Glad = "";
        Dissatisfied = "";
        ExtremelyDissatisfied = "";
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindGladButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNutralButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDissatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindExtremelyDissatisfiedButton);
    }

    public void dissatisfiedListener(ActionEvent actionEvent) {
        // Add event code here...
        Dissatisfied = "حزين";
        bindDissatisfiedButton.setIcon("/images/dissatisfied.svg");
        bindNutralButton.setIcon("/images/sentiment_neutral.svg");
        bindSatisfiedButton.setIcon("/images/sentiment_satisfied.svg");
        bindGladButton.setIcon("/images/mood.svg");
        bindExtremelyDissatisfiedButton.setIcon("/images/sentiment_extremely_dissatisfied.svg");
        
        Neutral = "";
        happy = "";
        Glad = "";
        ExtremelyDissatisfied = "";
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindGladButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNutralButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDissatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindExtremelyDissatisfiedButton);
    }

    public void extremelyDissatisfiedListener(ActionEvent actionEvent) {
        // Add event code here...
        ExtremelyDissatisfied = "غاضب";
        bindExtremelyDissatisfiedButton.setIcon("/images/extremelyDissatisfied.svg");
        bindDissatisfiedButton.setIcon("/images/sentiment_dissatisfied.svg");
        bindNutralButton.setIcon("/images/sentiment_neutral.svg");
        bindSatisfiedButton.setIcon("/images/sentiment_satisfied.svg");
        bindGladButton.setIcon("/images/mood.svg");
        
        Neutral = "";
        happy = "";
        Glad = "";
        Dissatisfied = "";
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindGladButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNutralButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDissatisfiedButton);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindExtremelyDissatisfiedButton);
    }

    public void setFeedbackComments(RichInputText feedbackComments) {
        this.feedbackComments = feedbackComments;
    }

    public RichInputText getFeedbackComments() {
        return feedbackComments;
    }

    public void setPoll(RichPoll poll) {
        this.poll = poll;
    }

    public RichPoll getPoll() {
        return poll;
    }

    public void handlePoll(PollEvent pollEvent) {
        Boolean isPopup = true;
           BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();

                   // Get the method binding for callGetFeedbackFlag
                   OperationBinding methodBinding = bindings.getOperationBinding("callGetFeedbackFlag");

                   // Set the parameter (userId) for the method call
                   methodBinding.getParamsMap().put("userId", "101");

                   // Execute the method binding (this will call the stored procedure)
                   String result = (String) methodBinding.execute();
                   System.out.println("result::::"+result);
                   if(result != null && result.equals("YES")){
                   // Set the result in the managed bean (this will be displayed on the UI)
                   this.feedbackFlag = true;
                       isPopup = false;
                       
                   }else{
                       this.feedbackFlag = true;
                       isPopup = true;
                   }
           if (!popupShown && feedbackPopup != null && feedbackFlag) {
               RichPopup.PopupHints hints = new RichPopup.PopupHints();
               feedbackPopup.show(hints);
               popupShown = true;

               // Stop the poll by updating the component
               AdfFacesContext.getCurrentInstance().addPartialTarget(feedbackPopup);
           }
    }

    public void setFeedbackPopup(RichPopup feedbackPopup) {
        this.feedbackPopup = feedbackPopup;
    }

    public RichPopup getFeedbackPopup() {
        return feedbackPopup;
    }

    public void setFeedbackFlag(Boolean feedbackFlag) {
        this.feedbackFlag = feedbackFlag;
    }

    public Boolean getFeedbackFlag() {
        return feedbackFlag;
    }

    public void setBindSuccessPopup(RichPopup bindSuccessPopup) {
        this.bindSuccessPopup = bindSuccessPopup;
    }

    public RichPopup getBindSuccessPopup() {
        return bindSuccessPopup;
    }

    public void closeSuccessPopup(ActionEvent actionEvent) {
        // Add event code here...
        bindSuccessPopup.hide();
    }

    public void setMandatoryPopup(RichPopup mandatoryPopup) {
        this.mandatoryPopup = mandatoryPopup;
    }

    public RichPopup getMandatoryPopup() {
        return mandatoryPopup;
    }

    public void closeMandatoryPopup(ActionEvent actionEvent) {
        // Add event code here...
        mandatoryPopup.hide();
    }
    public void setBindGladButton(RichButton bindGladButton) {
        this.bindGladButton = bindGladButton;
    }

    public RichButton getBindGladButton() {
        return bindGladButton;
    }

    public void setBindSatisfiedButton(RichButton bindSatisfiedButton) {
        this.bindSatisfiedButton = bindSatisfiedButton;
    }

    public RichButton getBindSatisfiedButton() {
        return bindSatisfiedButton;
    }

    public void setBindNutralButton(RichButton bindNutralButton) {
        this.bindNutralButton = bindNutralButton;
    }

    public RichButton getBindNutralButton() {
        return bindNutralButton;
    }

    public void setBindDissatisfiedButton(RichButton bindDissatisfiedButton) {
        this.bindDissatisfiedButton = bindDissatisfiedButton;
    }

    public RichButton getBindDissatisfiedButton() {
        return bindDissatisfiedButton;
    }

    public void setBindExtremelyDissatisfiedButton(RichButton bindExtremelyDissatisfiedButton) {
        this.bindExtremelyDissatisfiedButton = bindExtremelyDissatisfiedButton;
    }

    public RichButton getBindExtremelyDissatisfiedButton() {
        return bindExtremelyDissatisfiedButton;
    }
}
