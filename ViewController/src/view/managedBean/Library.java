package view.managedBean;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

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

public class Library {
    private RichInputText bindBookName;
    private RichInputFile bindbookDocs;
    private RichPopup documentUploadedConfirmationPopup;
    private RichInputText bindAuthorName;
    private Map<String,String> fileNameAndFileContenttype = new HashMap<String, String>();
    Map<String, InputStream> uploadedDocuments = new HashMap<String, InputStream>();
    private RichPopup bindErrorsPopup;
    private RichInputFile bindUploadBooks;
    private RichPopup bindDocTypePopup;
    private RichSelectOneChoice bindMainDocumentType;
    private RichSelectOneChoice bindSubDocumentType;
    private RichSelectOneChoice bindDocumentList;
    private  List<SelectItem> documentSubType = new ArrayList<SelectItem>();
    private  List<SelectItem> listDocuments = new ArrayList<SelectItem>();

    public void setListDocuments(List<SelectItem> listDocuments) {
        this.listDocuments = listDocuments;
    }

    public List<SelectItem> getListDocuments() {
        return listDocuments;
    }

    public Library() { 
    }

    public void setBindBookName(RichInputText bindBookName) {
        this.bindBookName = bindBookName;
    }

    public RichInputText getBindBookName() {
        return bindBookName;
    }

    public void setBindbookDocs(RichInputFile bindbookDocs) {
        this.bindbookDocs = bindbookDocs;
    }

    public RichInputFile getBindbookDocs() {
        return bindbookDocs;
    }

    public void setDocumentUploadedConfirmationPopup(RichPopup documentUploadedConfirmationPopup) {
        this.documentUploadedConfirmationPopup = documentUploadedConfirmationPopup;
    }

    public RichPopup getDocumentUploadedConfirmationPopup() {
        return documentUploadedConfirmationPopup;
    }

    public void setBindAuthorName(RichInputText bindAuthorName) {
        this.bindAuthorName = bindAuthorName;
    }

    public RichInputText getBindAuthorName() {
        return bindAuthorName;
    }

    public void setFileNameAndFileContenttype(Map<String, String> fileNameAndFileContenttype) {
        this.fileNameAndFileContenttype = fileNameAndFileContenttype;
    }

    public Map<String, String> getFileNameAndFileContenttype() {
        return fileNameAndFileContenttype;
    }

    public void setUploadedDocuments(Map<String, InputStream> uploadedDocuments) {
        this.uploadedDocuments = uploadedDocuments;
    }

    public Map<String, InputStream> getUploadedDocuments() {
        return uploadedDocuments;
    }

    public void setBindErrorsPopup(RichPopup bindErrorsPopup) {
        this.bindErrorsPopup = bindErrorsPopup;
    }

    public RichPopup getBindErrorsPopup() {
        return bindErrorsPopup;
    }

    public void setBindUploadBooks(RichInputFile bindUploadBooks) {
        this.bindUploadBooks = bindUploadBooks;
    }

    public RichInputFile getBindUploadBooks() {
        return bindUploadBooks;
    }
    public void bookChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        String bookName = bindBookName.getValue().toString();
        System.out.println("bookName=="+bookName);
        
        UploadedFile newFile = (UploadedFile) valueChangeEvent.getNewValue();
         String contentType= newFile.getContentType();
         String fileName = newFile.getFilename();
        String regex = "[.]";
        String[] myArray = fileName.split(regex);
        if(myArray[1].equalsIgnoreCase("PDF") || myArray[1].equalsIgnoreCase("word") || myArray[1].equalsIgnoreCase("excel") || myArray[1].equalsIgnoreCase("pptx")){
          fileNameAndFileContenttype.put("bookContentType", newFile.getContentType());
          
            try {
                uploadedDocuments.put(bookName+"."+myArray[1], newFile.getInputStream());
            } catch (IOException e) {
            }
          LibraryPojo.setUploadedDocuments(uploadedDocuments);
        }else{
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            bindDocTypePopup.show(hints);  
        }
    }

    public String uploadDocumentToContent() {
        // Add event code here...
        System.out.println("HI");
        String status = null;
        String authorName =null;
        String folderPath = "http://10.161.9.17:8888/webcenter/content/conn/wccConnection/path/Enterprise%20Libraries/NewMOFPortalSecondDraft/MaliyahDocuments%2f/";
        String folderId = null;
        String securityGroups = null;
        if(bindAuthorName.getValue()!=null){
          authorName = bindAuthorName.getValue().toString();  
        }
        String mainDocType = (String) ADFContext.getCurrent().getPageFlowScope().get("mainDocumentList");
        String subDocType = (String) ADFContext.getCurrent().getPageFlowScope().get("SubDocumentType");
        String docList = (String) ADFContext.getCurrent().getPageFlowScope().get("DocumentList");
        
        if(mainDocType!=null && subDocType!=null && docList!=null){
            String folders =null;
            String secGroup = null;
             folders = mainDocType+"/"+subDocType+"/"+docList; 
             secGroup =  mainDocType.concat(subDocType);
            System.out.println("folders=="+folders+"secGroup=="+secGroup);
               // folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fLegislations";      
               folderPath = folderPath.concat("/"+mainDocType+"/"+subDocType+"/"+docList);
              System.out.println("folder path=="+folderPath);
               //folderId = getFolderIds(folders);
               folderId = folderListItemsMetadata(folders);
              System.out.println("folder id=="+folderId);
              if(secGroup.equalsIgnoreCase("Maliyah Guides Manuals and TutorialsMaliyah")){
                  securityGroups="MalGuidesManualsTuto_Maliyah";   
              }else if(secGroup.equalsIgnoreCase("Maliyah Guides Manuals and TutorialsMaliyah Planning and Budgeting")) {
                  securityGroups="MalGuidesManualsTuto_MaliPLBD";  
                  }if(secGroup.equalsIgnoreCase("Maliyah Guides Manuals and TutorialsMaliyah Financials")){
                      securityGroups="MalGuidesManualsTuto_MalFin";   
                  }if(secGroup.equalsIgnoreCase("Maliyah Guides Manuals and TutorialsMaliyah Analytics")){ 
                       securityGroups="MalGuidesManualsTuto_MalAnal";  
                     }if(secGroup.equalsIgnoreCase("Maliyah Guides Manuals and TutorialsMaliyah Access Control")){
                         securityGroups="MalGuidesManualsTuto_AccCont";   
                         }
                
        }else{
            String folders = mainDocType+"/"+subDocType; 
            System.out.println("folders=="+folders);
            folderPath = folderPath.concat("/"+mainDocType+"/"+subDocType); 
            System.out.println("folder path=="+folderPath);
            //folderId = getFolderIds(folders);
            folderId = folderListItemsMetadata(folders);
            System.out.println("folder id=="+folderId);
            if(mainDocType.equalsIgnoreCase("Maliyah Advisories")){
            securityGroups="MaliyahAdvisories";   
            }
        }
       // securityGroups="Legislations";  
        
//        String docType = (String) ADFContext.getCurrent().getPageFlowScope().get("userSelectedDocuments");
//        if(docType.equals("Legislations")){
//          folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fLegislations";   
//          folderId = "03C20A297E2A13655FAD598D81D6C885";
//          securityGroups="Legislations";
//        }
//        if(docType.equals("Policies")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fPolicies";      
//            folderId = "A8519970CEFAEAC7ADEF55BE53735792";
//            securityGroups="Policies";
//        }
//        if(docType.equals("Chart of Accounts")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fChart%20of%20Accounts";        
//            folderId = "000B29A3BFEE6C0069DB89AC3B9ECFF9";
//            securityGroups="ChartOfAccounts";
//        }
//        if(docType.equals("State Final Accounts")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fState%20Final%20Accounts";       
//            folderId = "FC9A4B097B70C4154F724D4CACA4C780";
//            securityGroups="StateFinalAccounts";
//        }
//        if(docType.equals("State General Budget")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fState%20General%20Budget";       
//            folderId = "1F8FDD7126825319BFC67333B3263F61";
//            securityGroups="StateGeneralBudget";
//        }
//        if(docType.equals("Guides and Manuals")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fGuides%20and%20Manuals";        
//            folderId = "D7DA4926D0320579059A757F4EB4B12E";
//            securityGroups="GuidesAndManuals";
//        }
//        if(docType.equals("Presentations")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fPresentations";       
//            folderId = "B9492AC5CD636D210F2EB069E08F443B";
//            securityGroups="Presentations";
//        }
//        if(docType.equals("Test Scripts")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fTest%20Scripts";       
//            folderId = "85423B6F047549B7890309905C665B34";
//            securityGroups="TestScripts";
//        }
//        if(docType.equals("Deliverables")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fDeliverables";       
//            folderId = "575C6B85D7BFF1BC8EAD251FE5DF4695";
//            securityGroups="Deliverables";
//        }
//        if(docType.equals("Reports")){
//            folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fMaliyahDocuments%2fReports";       
//            folderId = "4FE4AEE278430DE5547DFD8C9EAB2763";
//            securityGroups="Reports";
//        }
        
         //String folderPath ="http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalFirstDraft%2fLibrary";
        //String folderPath = "http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fweblogic%2fLibrary";
         //String folderId = "B8566B1F3533CFCE6803B76273054814";
        //String folderId = "0292F88D32AB38F31D5A619EBED543B8";
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
                primaryFile.setFileName(filetype);
                //primaryFile.setContentType("application/pdf");
                        primaryFile.setContentLength(LibraryPojo.getUploadedDocuments()
                                                                .get(filetype)
                                                                .available());
                   
                     status = uploadFiletoUCMS_NI1(folderId, filetype, primaryFile, authorName,securityGroups,folderPath);  
                        System.out.println("Staatus of upload=="+status);
                        if(status.equals("success")){
                            RichPopup.PopupHints hints = new RichPopup.PopupHints();

                                   documentUploadedConfirmationPopup.show(hints); 
                            
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
//    public String getFolderIds(String pfolderId){
//        String folderId = "";
//        Map<String, String> folderIds = new HashMap<String,String>();
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah/Maliyah Handbook", "CB5EB0A45E221961C654BAA27CD71455");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah/Maliyah at a Glance", "34EA70033159329A21FF064EDCEFECFD");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Planning and Budgeting/User�s Manual", "C7388751B87A57C01AB77ABEAAD69AC6");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Planning and Budgeting/Training Guide", "0C25DF466A6F51C689B06D59B2FB5F7D");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Planning and Budgeting/Handbook", "943F8EC1E11B4D882730A3C5DAE5D0B0");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Planning and Budgeting/Managing Physical Performance Tutorial", "D101CBB1C2B88D945D4E21860DF61740");
//       
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Financials/User�s Manual", "B70A9B7A0AFEBB61BB7176060051DF1E");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Financials/Training Guide", "E742723347765EC66F156922B8B2B891");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Financials/Handbook", "C56FA99D7917CBAB625C617F2AB0CB69");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Financials/How to issue a Purchase Order Tutorial", "0D27E221BCEF1BEA0249E3F6654A5117");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Financials/How to issue a Payment Voucher Tutorial", "798800844AD5F25CEB7698A6CA8EA30C");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Analytics/User�s Manual", "7D8E0481F64446F5B42805E2105D8867");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Analytics/Training Guide", "5457F038FD67BB4413B6FAB31FA7F854");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Analytics/Handbook", "2981B282E93DDF5A3139A97B0722E22C");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Analytics/How to create an ad hoc report tutorial", "39E49AA844325DC1F9341E36A2BC3CD2");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Analytics/How to create a dashboard tutorial", "82DC588438366CE7EAD7F11BD48B4AF3");
//        folderIds.put("Maliyah Guides Manuals and Tutorials/Maliyah Access Control/User�s Manual", "BAFA270F7550C1642D18056E13B7333E");
//        folderIds.put("Maliyah Advisories/Advisory 11-2024", "32C7E9CA7BA88CE17C5CB31DFCDB6664");
//        folderIds.put("Maliyah Advisories/Advisory 12-2024", "376F1835A0E13559767A700ABE3D735B");
//        folderIds.put("Maliyah Advisories/Advisory 01-2025", "14E5133F4301DE2EB22C25248D682714");
//        folderIds.put("Maliyah Advisories/Advisory 02-2025", "1F41AD0F063A70E9C9519B1887FB42ED");
//        folderIds.put("Maliyah Advisories/Advisory 03-2025", "E73D628B927385A07C7979CDDA132FAD");
//        folderIds.put("Maliyah Advisories/Advisory 04-2025", "65DC988FBAEC3F420946A3238735E9DA");
//        folderIds.put("Maliyah Advisories/Advisory 05-2025", "3B27A65368BB7194BA46C2D74CADA2BA");
//        folderIds.put("Maliyah Advisories/Advisory 06-2025", "4CF8C3EFD3BB6D93911526CC91CAEB6F");
//        folderIds.put("Maliyah Advisories/Advisory 07-2025", "D05255E4C46BBF6FB308170F77FD0B98");
//        folderIds.put("Maliyah Advisories/Advisory 08-2025", "EC6ABF921FE2AFC2C85A9CCD2A0B78E4");
//        folderIds.put("Maliyah Advisories/Advisory 09-2025", "F46FEB7619362948FF4EE77814118978");
//        folderIds.put("Maliyah Advisories/Advisory 10-2025", "65444D891F0AC8D1212CC4C95B649BB0");
//        folderIds.put("Maliyah Advisories/Advisory 11-2025", "19418949F660C0C8E354B75BAE65BD96");
//        folderIds.put("Maliyah Advisories/Advisory 12-2025", "B577D6AF222158177B116300414D9104");
//        folderId = folderIds.get(pfolderId);
//        
//         return folderId;
//    }
    public String uploadFiletoUCMS_NI1(String FolderID, String fileName, TransferFile primaryFile, String authorName, String securityGroup, String MaliyahDocUrls) {
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
        dataBinder.putLocal("dSecurityGroup", securityGroup);
        dataBinder.addFile("primaryFile", primaryFile);
        dataBinder.putLocal("fParentGUID", FolderID);
        dataBinder.putLocal("xMaliyahDocUrl", MaliyahDocUrls); 
          
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
    public void closeDocumentuploadPopup(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        documentUploadedConfirmationPopup.hide();
        bindBookName.resetValue();
        bindAuthorName.resetValue();
        bindbookDocs.resetValue();
        bindDocumentList.resetValue();
        bindSubDocumentType.resetValue();
        bindMainDocumentType.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBookName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAuthorName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindbookDocs);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSubDocumentType);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindMainDocumentType);
        
               
    }
    public void closeErrorPopup(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindErrorsPopup.hide();
        bindBookName.resetValue();
        bindAuthorName.resetValue();
        bindbookDocs.resetValue();
        bindDocumentList.resetValue();
        bindSubDocumentType.resetValue();
        bindMainDocumentType.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBookName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAuthorName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindbookDocs);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSubDocumentType);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindMainDocumentType);
    }

    public void setBindDocTypePopup(RichPopup bindDocTypePopup) {
        this.bindDocTypePopup = bindDocTypePopup;
    }

    public RichPopup getBindDocTypePopup() {
        return bindDocTypePopup;
    }

    public void closeDocTypePopup(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindDocTypePopup.hide();
        bindBookName.resetValue();
        bindAuthorName.resetValue();
        bindbookDocs.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindBookName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindAuthorName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindbookDocs);
    }

    public void mainDocumentListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        String docsName = (String) valueChangeEvent.getNewValue();
        if(docsName!=null && docsName.equalsIgnoreCase("Maliyah Guides Manuals and Tutorials")){
           documentSubType.clear();
           documentSubType.add(new SelectItem("Maliyah","Maliyah"));
           documentSubType.add(new SelectItem("Maliyah Planning and Budgeting","Maliyah Planning and Budgeting"));
           documentSubType.add(new SelectItem("Maliyah Financials","Maliyah Financials"));
           documentSubType.add(new SelectItem("Maliyah Analytics","Maliyah Analytics"));
           documentSubType.add(new SelectItem("Maliyah Access Control","Maliyah Access Control"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSubDocumentType);     
        }else if(docsName!=null && docsName.equalsIgnoreCase("Maliyah Advisories")){
            documentSubType.clear();
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
                        documentSubType.add(new SelectItem("Advisory 01-"+year,"Advisory 01-"+year));
                        documentSubType.add(new SelectItem("Advisory 02-"+year,"Advisory 02-"+year));
                        documentSubType.add(new SelectItem("Advisory 03-"+year,"Advisory 03-"+year));
                        documentSubType.add(new SelectItem("Advisory 04-"+year,"Advisory 04-"+year));
                        documentSubType.add(new SelectItem("Advisory 05-"+year,"Advisory 05-"+year));
                        documentSubType.add(new SelectItem("Advisory 06-"+year,"Advisory 06-"+year));
                        documentSubType.add(new SelectItem("Advisory 07-"+year,"Advisory 07-"+year));
                        documentSubType.add(new SelectItem("Advisory 08-"+year,"Advisory 08-"+year));
                        documentSubType.add(new SelectItem("Advisory 09-"+year,"Advisory 09-"+year));
                        documentSubType.add(new SelectItem("Advisory 10-"+year,"Advisory 10-"+year));
                        documentSubType.add(new SelectItem("Advisory 11-"+year,"Advisory 11-"+year));
                        documentSubType.add(new SelectItem("Advisory 12-"+year,"Advisory 12-"+year)); 
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSubDocumentType);    
        }
        
    }

    public void setBindMainDocumentType(RichSelectOneChoice bindMainDocumentType) {
        this.bindMainDocumentType = bindMainDocumentType;
    }

    public RichSelectOneChoice getBindMainDocumentType() {
        return bindMainDocumentType;
    }

    public void setBindSubDocumentType(RichSelectOneChoice bindSubDocumentType) {
        this.bindSubDocumentType = bindSubDocumentType;
    }

    public RichSelectOneChoice getBindSubDocumentType() {
        return bindSubDocumentType;
    }

    public void setBindDocumentList(RichSelectOneChoice bindDocumentList) {
        this.bindDocumentList = bindDocumentList;
    }

    public RichSelectOneChoice getBindDocumentList() {
        return bindDocumentList;
    }

    public void SubDocumentTypeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("indside SubDocumentTypeListener");
        String docsName = (String) valueChangeEvent.getNewValue();
        System.out.println("sub docsName==="+docsName);
        if(docsName!=null && docsName.equalsIgnoreCase("Maliyah")){
            listDocuments.clear();
            listDocuments.add(new SelectItem("Maliyah Handbook","Maliyah Handbook"));
            listDocuments.add(new SelectItem("Maliyah at a Glance","Maliyah at a Glance"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentList);     
        }else if(docsName!=null && docsName.equalsIgnoreCase("Maliyah Planning and Budgeting")){
            listDocuments.clear();
            listDocuments.add(new SelectItem("User�s Manual","User�s Manual"));
            listDocuments.add(new SelectItem("Training Guide","Training Guide"));
            listDocuments.add(new SelectItem("Handbook","Handbook"));
            listDocuments.add(new SelectItem("Managing Physical Performance Tutorial","Managing Physical Performance Tutorial"));;
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentList);    
        }else if(docsName!=null && docsName.equalsIgnoreCase("Maliyah Financials")){
             listDocuments.clear();
             listDocuments.add(new SelectItem("User�s Manual","User�s Manual"));
             listDocuments.add(new SelectItem("Training Guide","Training Guide"));
             listDocuments.add(new SelectItem("Handbook","Handbook"));
             listDocuments.add(new SelectItem("How to issue a Purchase Order Tutorial","How to issue a Purchase Order Tutorial"));
             listDocuments.add(new SelectItem("How to issue a Payment Voucher Tutorial","How to issue a Payment Voucher Tutorial"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentList);    
        }else if(docsName!=null && docsName.equalsIgnoreCase("Maliyah Analytics")){
            listDocuments.clear();
            listDocuments.add(new SelectItem("User�s Manual","User�s Manual"));
            listDocuments.add(new SelectItem("Training Guide","Training Guide"));
            listDocuments.add(new SelectItem("Handbook","Handbook"));
            listDocuments.add(new SelectItem("How to create an ad hoc report tutorial","How to create an ad hoc report tutorial"));
            listDocuments.add(new SelectItem("How to create a dashboard tutorial","How to create a dashboard tutorial"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentList);    
        }else if(docsName!=null && docsName.equalsIgnoreCase("Maliyah Access Control")){
            listDocuments.clear();
            listDocuments.add(new SelectItem("User�s Manual","User�s Manual"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindDocumentList);    
        }
    }

    public void DocumentListListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("document list=="+valueChangeEvent.getNewValue());
    }


    public void setDocumentSubType(List<SelectItem> documentSubType) {
        this.documentSubType = documentSubType;
    }

    public List<SelectItem> getDocumentSubType() {
        return documentSubType;
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
            String path = "/Enterprise Libraries/NewMOFPortalSecondDraft/MaliyahDocuments/";
            // String path = "/Enterprise Libraries/EEPortal/NI4";
            path= path.concat(folderPath);

            

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
    
}
