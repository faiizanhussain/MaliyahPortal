package view.managedBean;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

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

import org.apache.myfaces.trinidad.model.UploadedFile;

public class UploadDocuments {
    Map<String, InputStream> uploadedDocuments = new HashMap<String, InputStream>();
    private RichInputFile bindUploadDocuments;
    private Map<String,String> fileNameAndFileContenttype = new HashMap<String, String>();
    private RichSelectOneChoice bindDocumentType;
    private RichInputText bindDocumentDescription;
    private RichInputText bindDocumentTitle;
    private RichPopup successPopup;
    private RichPopup bindErrorsPopup;
    private RichPopup bindDocTypePopup;

    public UploadDocuments() {
    }

    public void setUploadedDocuments(Map<String, InputStream> uploadedDocuments) {
        this.uploadedDocuments = uploadedDocuments;
    }

    public Map<String, InputStream> getUploadedDocuments() {
        return uploadedDocuments;
    }

    public void setFileNameAndFileContenttype(Map<String, String> fileNameAndFileContenttype) {
        this.fileNameAndFileContenttype = fileNameAndFileContenttype;
    }

    public Map<String, String> getFileNameAndFileContenttype() {
        return fileNameAndFileContenttype;
    }

    public void setBindUploadDocuments(RichInputFile bindUploadDocuments) {
        this.bindUploadDocuments = bindUploadDocuments;
    }

    public RichInputFile getBindUploadDocuments() {
        return bindUploadDocuments;
    }

    public void documentChangeListener(ValueChangeEvent valueChangeEvent) {
        UploadedFile newFile = (UploadedFile) valueChangeEvent.getNewValue();
         String contentType= newFile.getContentType();
         String fileName = newFile.getFilename();
        LibraryPojo.getUploadedDocuments().clear();
        String regex = "[.]";
        String[] myArray = fileName.split(regex);
        if(myArray[1].equalsIgnoreCase("pdf") || myArray[1].equalsIgnoreCase("docx") || myArray[1].equalsIgnoreCase("pptx") ||  myArray[1].equalsIgnoreCase("xlxs")){
          fileNameAndFileContenttype.put("uploadedDocs", newFile.getContentType());
          
            try {
                uploadedDocuments.put("uploadedDocs"+"."+myArray[1], newFile.getInputStream());
            } catch (IOException e) {
            }
          LibraryPojo.setUploadedDocuments(uploadedDocuments);
        }else{
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            bindDocTypePopup.show(hints);  
        }
    }

    public void setBindDocumentType(RichSelectOneChoice bindDocumentType) {
        this.bindDocumentType = bindDocumentType;
    }

    public RichSelectOneChoice getBindDocumentType() {
        return bindDocumentType;
    }

    public void setBindDocumentDescription(RichInputText bindDocumentDescription) {
        this.bindDocumentDescription = bindDocumentDescription;
    }

    public RichInputText getBindDocumentDescription() {
        return bindDocumentDescription;
    }

    public void setBindDocumentTitle(RichInputText bindDocumentTitle) {
        this.bindDocumentTitle = bindDocumentTitle;
    }

    public RichInputText getBindDocumentTitle() {
        return bindDocumentTitle;
    }

    public void uploadDocumentListener(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("HI");
        String status = null;
        String folderPath = "http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fMaliyah_Portal%2fMaliyahDocuments%2fDocuments";
        String folderId = null;
        String securityGroups = "MaliyahDocument";
        folderId = folderListItemsMetadata();
        String docTitle = (String) bindDocumentTitle.getValue();
        String docDescription = (String) bindDocumentDescription.getValue();

         System.out.println("size of book list=="+LibraryPojo.getUploadedDocuments().size());
        for (String filetype : LibraryPojo.getUploadedDocuments().keySet()) //iteration over keys
        {

                TransferFile primaryFile;
                    try {
                        primaryFile =
                            new TransferFile(LibraryPojo.getUploadedDocuments().get(filetype), filetype,
                                             LibraryPojo.getUploadedDocuments().get(filetype).available());
                   // if(filetype.contains("book")){
                    primaryFile.setContentType(fileNameAndFileContenttype.get("uploadedDocs"));  
               // }
               String regex = "[.]";
               String[] myArray = filetype.split(regex);
               System.out.println("after split="+myArray[0]+"aa="+myArray[1]);
               docTitle = docTitle+"."+myArray[1];
                primaryFile.setFileName(docTitle);
                //primaryFile.setContentType("application/pdf");
                        primaryFile.setContentLength(LibraryPojo.getUploadedDocuments()
                                                                .get(filetype)
                                                                .available());
                   
                     status = uploadDocumentToWCContent(folderId, filetype, primaryFile,securityGroups,folderPath,docDescription);  
                        System.out.println("Staatus of upload=="+status);
                        if(status.equals("success")){
                            bindDocumentTitle.resetValue();
                            bindDocumentDescription.resetValue();
                            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentTitle);
                            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentDescription);
                            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                                  
                                   successPopup.show(hints); 
                            
                        }else{
                            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                            bindErrorsPopup.show(hints);
                        }
        } catch (IOException e) {
        }
        }
    }
    public String uploadDocumentToWCContent(String FolderID, String fileName, TransferFile primaryFile, String securityGroup, String MaliyahDocUrls, String documentDescription) {
     System.out.println("docu url"+MaliyahDocUrls);
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
        dataBinder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
       // dataBinder.putLocal("dDocName",fileName);
        dataBinder.putLocal("dDocTitle", documentDescription);
        dataBinder.putLocal("dDocType", "Document");
        dataBinder.putLocal("dDocAccount", "");
        dataBinder.putLocal("dSecurityGroup", securityGroup);
        dataBinder.addFile("primaryFile", primaryFile);
        dataBinder.putLocal("fParentGUID", FolderID);
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
    
    public  String folderListItemsMetadata(){
            String folderGuidString = "";
        try {
            IdcClientManager manager = new IdcClientManager();
            IdcClient idcClient = manager.createClient("idc://10.161.9.17:4444");

            // create an identity
            IdcContext userContext = new IdcContext("weblogic", "weblogic123");

            // get the binder
            DataBinder dataBinder = idcClient.createBinder();
            String path = "/Enterprise Libraries/Maliyah_Portal/MaliyahDocuments/Documents/";
            // String path = "/Enterprise Libraries/EEPortal/NI4";
           // path= path.concat(folderPath);


            dataBinder.putLocal("IdcService", "FLD_BROWSE");
            // dataBinder.putLocal("path", path);
            dataBinder.putLocal("path", path);
            ServiceResponse checkFolderResponse = idcClient.sendRequest(userContext, dataBinder);

            DataBinder checkFolderResponseBinderData = checkFolderResponse.getResponseAsBinder();

            System.out.println("Total number of sub-folders is : " +
                               checkFolderResponseBinderData.getLocal("TotalChildFoldersCount"));
            System.out.println("Total number of files in this folder is : " +
                               checkFolderResponseBinderData.getLocal("TotalChildFilesCount"));
            DataResultSet folderPP = checkFolderResponseBinderData.getResultSet("FolderInfo");

            for (DataObject db : folderPP.getRows()) {
                //           String folderParentGuid = db.get("fParentGUID");
                //            System.out.println("fParentGUID::" +folderParentGuid);
                folderGuidString = db.get("fFolderGUID");
                System.out.println("folderGuid::" + folderGuidString);

            }
        } catch (IdcClientException ice) {
            // TODO: Add catch code
            ice.printStackTrace();
        }
    return folderGuidString;
    }
    public void setSuccessPopup(RichPopup successPopup) {
        this.successPopup = successPopup;
    }

    public RichPopup getSuccessPopup() {
        return successPopup;
    }

    public void closeSuccessPopup(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        successPopup.hide();
        bindUploadDocuments.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindUploadDocuments);
    }

    public void setBindErrorsPopup(RichPopup bindErrorsPopup) {
        this.bindErrorsPopup = bindErrorsPopup;
    }

    public RichPopup getBindErrorsPopup() {
        return bindErrorsPopup;
    }

    public void closeErrorPopup(ActionEvent actionEvent) {
        bindErrorsPopup.hide(); 
    }

    public void setBindDocTypePopup(RichPopup bindDocTypePopup) {
        this.bindDocTypePopup = bindDocTypePopup;
    }

    public RichPopup getBindDocTypePopup() {
        return bindDocTypePopup;
    }

    public void closeDocTypePopup(ActionEvent actionEvent) {
        bindDocTypePopup.hide();
    }
}
