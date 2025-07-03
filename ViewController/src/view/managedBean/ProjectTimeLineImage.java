package view.managedBean;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;

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

import view.utils.JSFUtils;

public class ProjectTimeLineImage {
    private RichInputFile bindUploadDocuments;
    Map<String, InputStream> uploadedDocuments = new HashMap<String, InputStream>();
    private Map<String,String> fileNameAndFileContenttype = new HashMap<String, String>();
    private RichPopup successPopup;
    private RichPopup bindErrorsPopup;
    private RichPopup bindDocTypePopup;
    private RichPopup bindUploadDocumentPopup;

    public ProjectTimeLineImage() {
    }

    public void documentChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        UploadedFile newFile = (UploadedFile) valueChangeEvent.getNewValue();
         String contentType= newFile.getContentType();
         String fileName = newFile.getFilename();
        LibraryPojo.getUploadedDocuments().clear();
        String regex = "[.]";
        String[] myArray = fileName.split(regex);
            if(myArray[1].equalsIgnoreCase("png") || myArray[1].equalsIgnoreCase("PNG") || myArray[1].equalsIgnoreCase("jpeg") || myArray[1].equalsIgnoreCase("JPEG") || myArray[1].equalsIgnoreCase("jpg") || myArray[1].equalsIgnoreCase("JPG")){
          fileNameAndFileContenttype.put("documentType", newFile.getContentType());
          
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

    public void setBindUploadDocuments(RichInputFile bindUploadDocuments) {
        this.bindUploadDocuments = bindUploadDocuments;
    }

    public RichInputFile getBindUploadDocuments() {
        return bindUploadDocuments;
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

    public void uploadDocumentListener(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("HI");
        
        ArrayList<String> docList = new ArrayList<String>();
         docList = getDocumentFromContent();
         System.out.println("delete document list="+docList);
        deleteDocuments(docList.get(0), docList.get(1)); 
        
        String status = null;
        String folderPath = "http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fMaliyah_Portal%2fMaliyahDocuments%2fProjectTimeLine";
        String folderId = null;
        String securityGroups = "Public";
        folderId = folderListItemsMetadata();
       String imageName = "ProjectTimeLine.png";
        String imageDescription = "Project Timeline Image";
       // String docTitle = (String) bindDocumentTitle.getValue();
      //  String docDescription = (String) bindDocumentDescription.getValue();

         System.out.println("size of book list=="+LibraryPojo.getUploadedDocuments().size());
        for (String filetype : LibraryPojo.getUploadedDocuments().keySet()) //iteration over keys
        {

                TransferFile primaryFile;
                    try {
                        primaryFile =
                            new TransferFile(LibraryPojo.getUploadedDocuments().get(filetype), filetype,
                                             LibraryPojo.getUploadedDocuments().get(filetype).available());
                   // if(filetype.contains("book")){
                    primaryFile.setContentType(fileNameAndFileContenttype.get("documentType"));  
               // }
//               String regex = "[.]";
//               String[] myArray = filetype.split(regex);
//               imageName = imageName+"."+myArray[1];
                primaryFile.setFileName(imageName);
                        primaryFile.setContentLength(LibraryPojo.getUploadedDocuments()
                                                                .get(filetype)
                                                                .available());
                   
                     status = uploadDocumentToWCContent(folderId, filetype, primaryFile,securityGroups,folderPath,imageDescription);  
                        System.out.println("Staatus of upload=="+status);
                        if(status.equals("success")){

                            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                            bindUploadDocumentPopup.hide();
                            successPopup.show(hints); 
                            
                        }else{
                            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                            bindUploadDocumentPopup.hide();
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
            String path = "/Enterprise Libraries/Maliyah_Portal/MaliyahDocuments/ProjectTimeLine/";
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
        // Add event code here...
        successPopup.hide();
    }

    public void setBindErrorsPopup(RichPopup bindErrorsPopup) {
        this.bindErrorsPopup = bindErrorsPopup;
    }

    public RichPopup getBindErrorsPopup() {
        return bindErrorsPopup;
    }

    public void closeErrorPopup(ActionEvent actionEvent) {
        // Add event code here...
        bindErrorsPopup.hide();
    }

    public void setBindDocTypePopup(RichPopup bindDocTypePopup) {
        this.bindDocTypePopup = bindDocTypePopup;
    }

    public RichPopup getBindDocTypePopup() {
        return bindDocTypePopup;
    }

    public void closeDocTypePopup(ActionEvent actionEvent) {
        // Add event code here...
        bindDocTypePopup.hide();
    }

    public void setBindUploadDocumentPopup(RichPopup bindUploadDocumentPopup) {
        this.bindUploadDocumentPopup = bindUploadDocumentPopup;
    }

    public RichPopup getBindUploadDocumentPopup() {
        return bindUploadDocumentPopup;
    }
    public ArrayList<String> getDocumentFromContent() {
        // Add event code here...
        ArrayList<String> docInfo = new ArrayList<String>();
         String documentName ="";
       
         String dID ="";
         String documentOriginalName ="";
         String documentSize ="";
         String documentSecurity ="";
         String documentId ="";
         String docUrl ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fMaliyah_Portal%2fMaliyahDocuments%2fProjectTimeLine/";
         int numberofDocs = 0;
        //String documentUrl ="http://10.161.9.17:8888/webcenter/content/conn/wccConnection/path/Enterprise Libraries/NewMOFPortalFirst/MaliyahDocuments/";
        ArrayList<String> securityGroupList = new ArrayList<String>();
        securityGroupList.add("Public"); 
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
              String docId = dataObject.get("dID");
                
              if(securityGroupList.contains(dataObject.get("dSecurityGroup"))){
                
                  if(dataObject.get("dID")!=null){
                      dID = dataObject.get("dID");
                       docInfo.add(dID);
                      System.out.println("dID===***"+dID);
                   }
              if(dataObject.get("dDocName")!=null){
                documentName = dataObject.get("dDocName"); 
                  docInfo.add(documentName);
                  System.out.println("documentName===***"+documentName);
              }
            
             
              }
          }  
        } catch(IdcClientException ice) {
          // TODO: Add catch code
          ice.printStackTrace();
        }
        
        return docInfo;
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
}
