package view.managedBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.model.TransferFile;
import oracle.stellent.ridc.model.serialize.HdaBinderSerializer;
import oracle.stellent.ridc.protocol.ServiceResponse;

import view.utils.JSFUtils;

public class DocumentLib implements Serializable {
    private static final long serialversionUID = 129348938L;
    private RichTable bindDocumentTable;
    private RichInputText bindDocumentName;
    private RichButton closeSuccessPopup;
    private RichPopup bindErrorsPopup;
    private RichButton closeErrorPopup;
    private RichPopup bindConfirmation;
    private RichButton closeConfirmationPopup;
    private RichPopup bindDeleteSuccessPopups;
    private RichPopup documentViewPopup;

    public DocumentLib() {
    }
    private ArrayList<DocumentListHandler> DocumentLists = new ArrayList<DocumentListHandler>();
   // private ArrayList<DocumentListHandler> DocumentList= new ArrayList<DocumentListHandler>();
    private ArrayList<DocumentListHandler> DuplicateDocumentList= new ArrayList<DocumentListHandler>();

    public void setDocumentLists(ArrayList<DocumentListHandler> DocumentLists) {
        this.DocumentLists = DocumentLists;
    }

    public ArrayList<DocumentListHandler> getDocumentLists() {
        return DocumentLists;
    }

    public ArrayList<DocumentListHandler> getDocumentFromContent() {
        // Add event code here...
         DocumentLists.clear();
         String documentName ="";
         String documentTitle ="";
         String docCreationDate ="";
         String dID ="";
         String documentOriginalName ="";
         String documentSize ="";
         String documentSecurity ="";
         String documentId ="";
         String docUrl ="http://10.161.9.17:8888/webcenter/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/Documents/";
         int numberofDocs = 0;
        //String documentUrl ="http://10.161.9.17:8888/webcenter/content/conn/wccConnection/path/Enterprise Libraries/NewMOFPortalFirst/MaliyahDocuments/";
        ArrayList<String> securityGroupList = new ArrayList<String>();
 //           securityGroupList.add("Legislations");
        securityGroupList.add("MaliyahDocument"); 
//        securityGroupList.add("MalGuidesManualsTuto_Maliyah");
//        securityGroupList.add("MalGuidesManualsTuto_MaliPLBD"); 
//        securityGroupList.add("MalGuidesManualsTuto_MalFin"); 
//        securityGroupList.add("MalGuidesManualsTuto_MalAnal");
//        securityGroupList.add("MalGuidesManualsTuto_AccCont");
        System.setProperty("file.encoding", "UTF-8");
          
        IdcClientManager manager = new IdcClientManager();

        try {
          // Create a new IdcClient Connection using idc protocol (i.e. socket connection to Content Server)
          IdcClient idcClient = manager.createClient("idc://10.161.9.17:4444");
          // Create new context using the 'sysadmin' user
          IdcContext userContext = new IdcContext("weblogic", "weblogic123");
          HdaBinderSerializer serializer = new HdaBinderSerializer("UTF-8", idcClient.getDataFactory());

          DataBinder binder = idcClient.createBinder();

          // populate the binder with the parameters
          binder.putLocal ("IdcService", "GET_SEARCH_RESULTS");
          binder.putLocal ("QueryText", "");
          binder.putLocal ("ResultCount", "20");

          ServiceResponse response = idcClient.sendRequest(userContext, binder);
          // Get the data binder for the response from Content Server
          DataBinder responseData = response.getResponseAsBinder();
          // Write the response data binder to stdout
          // get the binder
          DataResultSet resultSet = responseData.getResultSet ("SearchResults");

          // loop over the results
          for (DataObject dataObject : resultSet.getRows ()) {
              System.out.println("dataObject=="+dataObject);
              System.out.println("dDocName data =="+dataObject.get("dDocName"));
              System.out.println("dID data =="+dataObject.get("dID"));
              
              
              System.out.println("MaliyahDocUrl"+dataObject.get("MaliyahDocUrl"));
              System.out.println("WebfilePath  data =="+dataObject.get("WebfilePath"));
              String docId = dataObject.get("dID");
                
              if(securityGroupList.contains(dataObject.get("dSecurityGroup"))){
                
                  String documentUrl = JSFUtils.getDocURl(docId);
                  System.out.println("documet url=="+documentUrl);
                  
                  String securityGroup = dataObject.get("dSecurityGroup");
                  
                  
              if(dataObject.get("dDocName")!=null){
                documentName = dataObject.get("dDocName"); 
              }
              if(dataObject.get("dDocCreateDate")!=null){
              docCreationDate = dataObject.get("dDocCreateDate"); 
               }
              if(dataObject.get("dID")!=null){
                  dID = dataObject.get("dID");
                   documentId = dataObject.get("dID");
                  System.out.println("documentId===***"+documentId);
               }
              if(dataObject.get("dOriginalName")!=null){
                  documentOriginalName = dataObject.get("dOriginalName");  
               }
              if(dataObject.get("VaultFileSize")!=null){
                   documentSize = dataObject.get("VaultFileSize");   
               }
             
              if(dataObject.get("dDocTitle")!=null){
                  documentTitle = dataObject.get("dDocTitle");   
               }
              if(dataObject.get("dSecurityGroup")!=null){
                documentSecurity = dataObject.get("dSecurityGroup");   
               }
                  String docName = dataObject.get("dOriginalName");
                  String fileType = null;
                  String regex = "[.]";
                  String[] myArray = docName.split(regex);
                  fileType = myArray[1].toString();
                  String finaldocumentUrl = docUrl+""+dataObject.get("dOriginalName");
                  System.out.println ("docUrl: " + docUrl);
                  
              System.out.println ("Title is: " + dataObject.get ("dDocTitle"));
              System.out.println ("Author is: " + dataObject.get ("dDocAuthor"));
              System.out.println ("dID: " + dataObject.get ("dID"));
              DocumentLists.add(new DocumentListHandler(documentName,documentTitle,docCreationDate,dID,documentOriginalName,documentSize,documentSecurity,finaldocumentUrl,documentId,fileType));
            System.out.println ("DocumentLists: " + DocumentLists);
              }
          }  
            ADFContext.getCurrent().getPageFlowScope().put("DocumentLists", DocumentLists);
        } catch(IdcClientException ice) {
          // TODO: Add catch code
          ice.printStackTrace();
        }
        
        return DocumentLists;
        }
   
    public void setbindDocumentName(RichInputText bindDocumentName) {
        this.bindDocumentName = bindDocumentName;
    }

    public RichInputText getbindDocumentName() {
        return bindDocumentName;
    }


    public void searchBook(ActionEvent actionEvent) {
        // Add event code here...
        String bookName = "";
        ArrayList<DocumentListHandler> booklocalList = new ArrayList<DocumentListHandler>();
        if(bindDocumentName.getValue()!=null){
            bookName = bindDocumentName.getValue().toString();
        }
            for (DocumentListHandler bookList : DocumentLists) {
                if(bookList.getDocumentOriginalName().equals(bookName)){
                
                    booklocalList.add(bookList);
                    
                }
              }
            if(!booklocalList.isEmpty()){
                DocumentLists.clear();
                DocumentLists = booklocalList;
            }else{
                booklocalList.clear(); 
            }
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentTable);
    }

    public void clearBook(ActionEvent actionEvent) {
        // Add event code here...
        bindDocumentName.setValue("");
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentName);
          DocumentLists.clear();
         DocumentLists = getDocumentFromContent();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentTable);
    }

    public void setBindDocumentTable(RichTable bindDocumentTable) {
        this.bindDocumentTable = bindDocumentTable;
    }

    public RichTable getBindDocumentTable() {
        return bindDocumentTable;
    }

    public static void main(String args[]){
        DocumentLib aa = new DocumentLib();
       ArrayList<DocumentListHandler> aab= aa.getDocumentFromContent();
    }

    public void setBindDocumentName(RichInputText bindDocumentName) {
        this.bindDocumentName = bindDocumentName;
    }

    public RichInputText getBindDocumentName() {
        return bindDocumentName;
    }

    public void deleteDocuments(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getBindConfirmation(). show(hints);   
    }
    
    public String deleteDocuments(String docID, String documentName) {
      String docsuploadStatus = null;
      InputStream inputStream = null;
      OutputStream outputStream = null;
      System.setProperty("file.encoding", "UTF-8");
        
      IdcClientManager manager = new IdcClientManager();

      try {
        // Create a new IdcClient Connection using idc protocol (i.e. socket connection to Content Server)
        IdcClient idcClient = manager.createClient("idc://10.161.9.17:4444");
        // Create new context using the 'sysadmin' user
        IdcContext userContext = new IdcContext("weblogic");
        // Create an HdaBinderSerializer; this is not necessary, but it allows us to serialize the request and response data binders
        HdaBinderSerializer serializer = new HdaBinderSerializer("UTF-8", idcClient.getDataFactory());

        // Databinder for checkin request
        DataBinder dataBinder = idcClient.createBinder();
        dataBinder.putLocal("IdcService", "DELETE_DOC");
       // dataBinder.putLocal("dDocName",fileName);
        dataBinder.putLocal("dID", docID);
        dataBinder.putLocal("dDocName", documentName);
//        dataBinder.putLocal("dDocAccount", "");
//        dataBinder.putLocal("dSecurityGroup", securityGroup);
//        dataBinder.addFile("primaryFile", primaryFile);
//        dataBinder.putLocal("fParentGUID", FolderID);
       // dataBinder.putLocal("xMaliyahDocUrl", MaliyahDocUrls); 
          
        serializer.serializeBinder(System.out, dataBinder);
        // Send the request to Content Server
        ServiceResponse response = idcClient.sendRequest(userContext, dataBinder);
        // Get the data binder for the response from Content Server
        DataBinder responseData = response.getResponseAsBinder();
        // Write the response data binder to stdout
        serializer.serializeBinder(System.out, responseData);
        docsuploadStatus = "success";
      } catch(IOException ioe) {
        // TODO: Add catch code
        docsuploadStatus = "fail";
        ioe.printStackTrace();
      } catch(IdcClientException ice) {
        // TODO: Add catch code
        docsuploadStatus = "fail";
        ice.printStackTrace();
      }
      return docsuploadStatus;

    }

    public void setCloseSuccessPopup(RichButton closeSuccessPopup) {
        this.closeSuccessPopup = closeSuccessPopup;
    }

    public RichButton getCloseSuccessPopup() {
        return closeSuccessPopup;
    }

    public void setBindErrorsPopup(RichPopup bindErrorsPopup) {
        this.bindErrorsPopup = bindErrorsPopup;
    }

    public RichPopup getBindErrorsPopup() {
        return bindErrorsPopup;
    }

    public void setCloseErrorPopup(RichButton closeErrorPopup) {
        this.closeErrorPopup = closeErrorPopup;
    }

    public RichButton getCloseErrorPopup() {
        return closeErrorPopup;
    }

    public void setBindConfirmation(RichPopup bindConfirmation) {
        this.bindConfirmation = bindConfirmation;
    }

    public RichPopup getBindConfirmation() {
        return bindConfirmation;
    }

    public void setCloseConfirmationPopup(RichButton closeConfirmationPopup) {
        this.closeConfirmationPopup = closeConfirmationPopup;
    }


    public void deleteConfirmationListener(ActionEvent actionEvent) {
        // Add event code here...
        String docId = (String) ADFContext.getCurrent().getPageFlowScope().get("documehtId");
        String docName = (String) ADFContext.getCurrent().getPageFlowScope().get("documentName");
        System.out.println("docId=="+docId+"docName=="+docName);
        String str =  deleteDocuments(docId, docName);
        System.out.println("str=="+str);
        if(str.equalsIgnoreCase("success")){
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getBindConfirmation().hide();
            bindDeleteSuccessPopups.show(hints);
        }else{
            RichPopup.PopupHints hints1 = new RichPopup.PopupHints();
            getBindConfirmation().hide();
            bindErrorsPopup.show(hints1);
            
        }
        getDocumentFromContent();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentTable);
    }

    public void closeConfirmationPopup(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindConfirmation.hide();
    }

    public void setBindDeleteSuccessPopups(RichPopup bindDeleteSuccessPopups) {
        this.bindDeleteSuccessPopups = bindDeleteSuccessPopups;
    }

    public RichPopup getBindDeleteSuccessPopups() {
        return bindDeleteSuccessPopups;
    }

    public void setDocumentViewPopup(RichPopup documentViewPopup) {
        this.documentViewPopup = documentViewPopup;
    }

    public RichPopup getDocumentViewPopup() {
        return documentViewPopup;
    }

    public void viewDocument(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        documentViewPopup.show(hints);
    }
}
