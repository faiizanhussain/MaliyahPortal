package view.managedBean;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.ViewObject;

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

public class News {
    private RichPopup bindNewsPopup;
    private RichTable bindNewsTable;
    private RichPopup bindNewsDetailsPopup;
    Map<String, InputStream> uploadedDocuments = new HashMap<String, InputStream>();
    private Map<String,String> fileNameAndFileContenttype = new HashMap<String, String>();
    private RichPopup successPopup;
    private RichInputFile bindInputFile;
    private RichPopup bindErrorsPopup;
    private RichSelectOneChoice bindCategory;
    private RichInputText bindTitle;
    private RichInputText bindSummary;
    private RichPopup bindDocTypePopup;
    private RichInputText bindEngHeadline;
    private RichSelectOneChoice bindTagEng;
    private RichInputText bindSummaryEng;
    private RichInputText bindHeadlineArabic;
    private RichSelectOneChoice bindTagArabic;
    private RichInputText bindSummaryArabic;

    public void setFileNameAndFileContenttype(Map<String, String> fileNameAndFileContenttype) {
        this.fileNameAndFileContenttype = fileNameAndFileContenttype;
    }

    public Map<String, String> getFileNameAndFileContenttype() {
        return fileNameAndFileContenttype;
    }

    public News() {
    }

    public void setUploadedDocuments(Map<String, InputStream> uploadedDocuments) {
        this.uploadedDocuments = uploadedDocuments;
    }

    public Map<String, InputStream> getUploadedDocuments() {
        return uploadedDocuments;
    }

    public void setBindNewsPopup(RichPopup bindNewsPopup) {
        this.bindNewsPopup = bindNewsPopup;
    }

    public RichPopup getBindNewsPopup() {
        return bindNewsPopup;
    }
    public void closePopup(RichPopup popup) {
      RichPopup.PopupHints hints = new RichPopup.PopupHints();
      popup.hide();

    }

    public void openPopup(RichPopup popup) {
      RichPopup.PopupHints hints = new RichPopup.PopupHints();
      popup.show(hints);
    }

    public void addNewsToTable(ActionEvent actionEvent) {
        // Add event code here...
        ArrayList<String> newsDetails = new ArrayList<String>();
        try {
            System.out.println("inside addNewsToTable");
            BindingContext bCtx = BindingContext.getCurrent();
            DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
            
         
           String headline_eng = (String) bindEngHeadline.getValue();
           String tab_eng = (String) bindTagEng.getValue();
           String newsDetails_eng = (String) bindSummaryEng.getValue();
            String headline_arabic = (String) bindHeadlineArabic.getValue();
            String tab_arabic  = (String) bindTagArabic.getValue();
            String newsDetails_arabic = (String) bindSummaryArabic.getValue();
            
            newsDetails.add(headline_eng);
            newsDetails.add(tab_eng);
            newsDetails.add(newsDetails_eng);
            newsDetails.add(headline_arabic);
            newsDetails.add(tab_arabic);
            newsDetails.add(newsDetails_arabic);
           // newsDetails.add("");
            
          
            OperationBinding oper2 = DcCon.getOperationBinding("addNews");
            oper2.getParamsMap().put("news", newsDetails);
            String updateFlag = (String) oper2.execute();

            OperationBinding oper3 = DcCon.getOperationBinding("getLatestNewsSerialNumber");
            Integer newsSerialNumber = (Integer) oper3.execute();
            System.out.println("latest newsSerialNumber ****=="+newsSerialNumber);
            
            uploadDocumentToContent(newsSerialNumber);
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            successPopup.show(hints);
            
            bindEngHeadline.resetValue();
            bindTagEng.resetValue();
            bindSummaryEng.resetValue();
            bindHeadlineArabic.resetValue();
            bindTagArabic.resetValue();
            bindSummaryArabic.resetValue();
            
            bindInputFile.resetValue();
            bindEngHeadline.setValue("");
            bindSummaryEng.setValue("");
            bindHeadlineArabic.setValue("");
            bindSummaryArabic.setValue("");
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindEngHeadline);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTagEng);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSummaryEng);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindHeadlineArabic);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTagArabic);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSummaryArabic);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindInputFile);
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }


    public void setBindNewsTable(RichTable bindNewsTable) {
        this.bindNewsTable = bindNewsTable;
    }

    public RichTable getBindNewsTable() {
        return bindNewsTable;
    }

    public void setBindNewsDetailsPopup(RichPopup bindNewsDetailsPopup) {
        this.bindNewsDetailsPopup = bindNewsDetailsPopup;
    }

    public RichPopup getBindNewsDetailsPopup() {
        return bindNewsDetailsPopup;
    }

    public void closeNewsDetailsPopup(ActionEvent actionEvent) {
        // Add event code here...
        closePopup(bindNewsDetailsPopup);
    }

    public void openNewsDetailsPopup(ActionEvent actionEvent) {
        // Add event code here...
        openPopup(bindNewsDetailsPopup);
    }

    public void fileUploadListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        UploadedFile newFile = (UploadedFile) valueChangeEvent.getNewValue();
         String contentType= newFile.getContentType();
         String fileName = newFile.getFilename();
        String regex = "[.]";
        String[] myArray = fileName.split(regex);
        System.out.println("myArray[0]="+myArray[0]+"myArray[1]"+myArray[1]);
        if(myArray[1].equalsIgnoreCase("png") || myArray[1].equalsIgnoreCase("PNG") || myArray[1].equalsIgnoreCase("jpeg") || myArray[1].equalsIgnoreCase("JPEG") || myArray[1].equalsIgnoreCase("jpg") || myArray[1].equalsIgnoreCase("JPG")){
          fileNameAndFileContenttype.put("bookContentType", newFile.getContentType());
          System.out.println("content Type=="+newFile.getContentType());
            try {
                uploadedDocuments.put("News_"+"."+myArray[1], newFile.getInputStream());
            } catch (IOException e) {
            }
          LibraryPojo.setUploadedDocuments(uploadedDocuments);
        }else{
           RichPopup.PopupHints hints = new RichPopup.PopupHints();
            bindDocTypePopup.show(hints);  
        }
    }
    public String uploadDocumentToContent(int newsSerialNumber) {
        // Add event code here...
        System.out.println("HI");
        String status = null;
        String authorName =null;
        String imageName="";
        //String folderPath = "http://10.161.9.17:8888/webcenter/content/conn/wccConnection/path/Enterprise%20Libraries/NewMOFPortalSecondDraft/MaliyahDocuments%2f/";
        String folderPath = "http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fMaliyah_Portal%2fMaliyahDocuments%2fNews";
        String folderId = null;
        String securityGroups = "Public";
       
            String folders =null;
              System.out.println("folder path=="+folderPath);
               folderId = folderListItemsMetadata(folderPath);
              System.out.println("folder id=="+folderId);

        System.out.println("size of book list=="+LibraryPojo.getUploadedDocuments().size());
        for (String filetype : LibraryPojo.getUploadedDocuments().keySet()) //iteration over keys
        {

           //if (filetype.contains("book")) {
           
               TransferFile primaryFile;
                   try {
                       primaryFile =
                           new TransferFile(LibraryPojo.getUploadedDocuments().get(filetype), filetype,
                                            LibraryPojo.getUploadedDocuments().get(filetype).available());
                  // if(filetype.contains("book")){
                   primaryFile.setContentType(fileNameAndFileContenttype.get("bookContentType"));  
              // }
                System.out.println("maliya News_"+newsSerialNumber);
            String regex = "[.]";
            String[] myArray = filetype.split(regex);
                       System.out.println("arrrr="+myArray);
            System.out.println("after split="+myArray[0]+"aa="+myArray[1]);
            String extension = "png";
     
           // imageName=myArray[0]+""+newsSerialNumber+"."+myArray[1]; 
            imageName=myArray[0]+""+newsSerialNumber+"."+extension;           
                       System.out.println("image name***"+imageName);
               primaryFile.setFileName(imageName);
               //primaryFile.setContentType("application/pdf");
                       primaryFile.setContentLength(LibraryPojo.getUploadedDocuments()
                                                               .get(filetype)
                                                               .available());
                  
                    status = uploadFiletoUCMS_NEWS(folderId, filetype, primaryFile, authorName,securityGroups,folderPath);  
                       System.out.println("Staatus of upload=="+status);
                       if(status.equals("success")){
                           RichPopup.PopupHints hints = new RichPopup.PopupHints();

                                  successPopup.show(hints); 
                           
                       }else{
                           RichPopup.PopupHints hints = new RichPopup.PopupHints();
                           bindErrorsPopup.show(hints);
                       }
        } catch (IOException e) {
        }
        
        //}
        }
        folderPath =null;
        return status;
    }
    
    
    public String uploadFiletoUCMS_NEWS(String FolderID, String fileName, TransferFile primaryFile, String authorName, String securityGroup, String MaliyahDocUrls) {
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
         //  dataBinder.putLocal("dDocName",fileName);
           dataBinder.putLocal("dDocTitle", authorName);
           dataBinder.putLocal("dDocType", "Document");
           dataBinder.putLocal("dDocAccount", "");
           dataBinder.putLocal("dSecurityGroup", "Public");
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

    public  String folderListItemsMetadata(String folderPath){
            String folderGuidString = "";
        try {
            IdcClientManager manager = new IdcClientManager();
            IdcClient idcClient = manager.createClient("idc://10.161.9.17:4444");

            // create an identity
            IdcContext userContext = new IdcContext("weblogic", "weblogic123");

            // get the binder
            DataBinder dataBinder = idcClient.createBinder();
            String path = "/Enterprise Libraries/Maliyah_Portal/MaliyahDocuments/News";
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
//    public static void main(String args[]){
//        News news = new News();
//       news.folderListItemsMetadata("");
//    }
    public void setSuccessPopup(RichPopup successPopup) {
        this.successPopup = successPopup;
    }

    public RichPopup getSuccessPopup() {
        return successPopup;
    }

    public void closeSuccessPopup(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        successPopup.hide();
        bindInputFile.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindInputFile);
    }

    public void setBindInputFile(RichInputFile bindInputFile) {
        this.bindInputFile = bindInputFile;
    }

    public RichInputFile getBindInputFile() {
        return bindInputFile;
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

    public void setBindCategory(RichSelectOneChoice bindCategory) {
        this.bindCategory = bindCategory;
    }

    public RichSelectOneChoice getBindCategory() {
        return bindCategory;
    }

    public void setBindTitle(RichInputText bindTitle) {
        this.bindTitle = bindTitle;
    }

    public RichInputText getBindTitle() {
        return bindTitle;
    }

    public void setBindSummary(RichInputText bindSummary) {
        this.bindSummary = bindSummary;
    }

    public RichInputText getBindSummary() {
        return bindSummary;
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

    public void getNewsData() {
        // Add event code here...
    }

    public void setBindEngHeadline(RichInputText bindEngHeadline) {
        this.bindEngHeadline = bindEngHeadline;
    }

    public RichInputText getBindEngHeadline() {
        return bindEngHeadline;
    }

    public void setBindTagEng(RichSelectOneChoice bindTagEng) {
        this.bindTagEng = bindTagEng;
    }

    public RichSelectOneChoice getBindTagEng() {
        return bindTagEng;
    }

    public void setBindSummaryEng(RichInputText bindSummaryEng) {
        this.bindSummaryEng = bindSummaryEng;
    }

    public RichInputText getBindSummaryEng() {
        return bindSummaryEng;
    }

    public void setBindHeadlineArabic(RichInputText bindHeadlineArabic) {
        this.bindHeadlineArabic = bindHeadlineArabic;
    }

    public RichInputText getBindHeadlineArabic() {
        return bindHeadlineArabic;
    }

    public void setBindTagArabic(RichSelectOneChoice bindTagArabic) {
        this.bindTagArabic = bindTagArabic;
    }

    public RichSelectOneChoice getBindTagArabic() {
        return bindTagArabic;
    }

    public void setBindSummaryArabic(RichInputText bindSummaryArabic) {
        this.bindSummaryArabic = bindSummaryArabic;
    }

    public RichInputText getBindSummaryArabic() {
        return bindSummaryArabic;
    }
}
