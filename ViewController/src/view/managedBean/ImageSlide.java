package view.managedBean;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.nav.RichLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.stellent.ridc.*;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.model.*;
import oracle.stellent.ridc.model.impl.*;
import oracle.stellent.ridc.protocol.ServiceResponse;


public class ImageSlide {
    private RichImage bindImageSlide;
    private static int count =1;
    private RichLink bindImageSlideLink;

    public ImageSlide() {
    }

    public void changePreviousImage(ActionEvent actionEvent) {
        // Add event code here...
        if(count>1)
        {
        count = count-1;
        if(count>0 && count<=8){
        System.out.println("/content/conn/wccConnection/path/Enterprise%20Libraries/NewMOFPortalSecondDraft/MaliyahaPortalImages/Slider/"+"Image_"+count+".jpg");
        bindImageSlideLink.setIcon("/content/conn/wccConnection/path/Enterprise%20Libraries/NewMOFPortalSecondDraft/MaliyahaPortalImages/Slider/"+"Image_"+count+".jpg");
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindImageSlideLink);
        }
        }
    }

    public void changeNextImage(ActionEvent actionEvent) {
        // Add event code here...
        if(count<=7)
        {
        count=count+1;
        if(count>0 && count<=8){
        System.out.println("/content/conn/wccConnection/path/Enterprise%20Libraries/NewMOFPortalSecondDraft/MaliyahaPortalImages/Slider/"+"Image_"+count+".jpg");
        bindImageSlideLink.setIcon("/content/conn/wccConnection/path/Enterprise%20Libraries/NewMOFPortalSecondDraft/MaliyahaPortalImages/Slider/"+"Image_"+count+".jpg");
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindImageSlideLink);
        }
        }
    }

    public void setBindImageSlide(RichImage bindImageSlide) {
        this.bindImageSlide = bindImageSlide;
    }

    public RichImage getBindImageSlide() {
        return bindImageSlide;
    }

    public void setBindImageSlideLink(RichLink bindImageSlideLink) {
        this.bindImageSlideLink = bindImageSlideLink;
    }

    public RichLink getBindImageSlideLink() {
        return bindImageSlideLink;
    }
    
//    public void getImagesFromContentserver(){
//        try {
//            System.out.println("inside getImagesFromContentserver");
//                    // Initialize RIDC Client
//                    IdcClientManager manager = new IdcClientManager();
//                    IdcClient client = manager.createClient("idc://10.161.9.17:4444");
//         
//                    // Setup connection credentials
//                    IdcContext userContext = new IdcContext("weblogic", "weblogic123");
//         
//                    // Define the folder path (e.g., `/Contribution Folders/MyFolder`)
//                    String folderPath = "http://10.161.9.17:16200/cs/idcplg?IdcService=FLD_BROWSE&path=%2fEnterprise%20Libraries%2fNewMOFPortalSecondDraft%2fMaliyahaPortalImages%2fSlider";
//         
//                    // Create request to fetch folder contents
//                    DataBinder binder = client.createBinder();
//                    binder.putLocal("IdcService", "FLD_BROWSE");
//                    binder.putLocal("path", folderPath);
//                    binder.putLocal("itemType", "documents");
//         
//                    // Send request to WCC server
//                    ServiceResponse response = client.sendRequest(userContext, binder);
//                    DataBinder responseBinder = response.getResponseAsBinder();
//         
//                    // Retrieve results
//                    DataResultSet resultSet = responseBinder.getResultSet("FolderContents");
//                    System.out.println("Documents in Folder: " + folderPath);
//         
//                    for (DataObject dataObj : resultSet.getRows()) {
//                        System.out.println("Document Name: " + dataObj.get("dDocName"));
//                        System.out.println("Title: " + dataObj.get("dDocTitle"));
//                        System.out.println("Content ID: " + dataObj.get("dID"));
//                        System.out.println("---------------------------------");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
    
    public  void folderListItemsMetadata(){

        try {
            IdcClientManager manager = new IdcClientManager();
            IdcClient idcClient = manager.createClient("idc://10.161.9.17:4444");

            // create an identity
            IdcContext userContext = new IdcContext("weblogic", "weblogic123");

            // get the binder
            DataBinder dataBinder = idcClient.createBinder();
            String path = "/Enterprise Libraries/NewMOFPortalSecondDraft/MaliyahaPortalImages/Slider";
            // String path = "/Enterprise Libraries/EEPortal/NI4";

            String folderGuidString = "";

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

//            dataBinder.putLocal("IdcService", "GET_SEARCH_RESULTS");
//            dataBinder.putLocal("ResultCount", "20");
//            String queryTextStr = "xParentFolders <matches> `" + folderGuidString + "`";
//            System.out.println("String of QueryText generated::" + queryTextStr);
//            dataBinder.putLocal("QueryText", queryTextStr);
//
//            // dataBinder.putLocal("QueryText", "xParentFolders <matches> `55F84291155F130CE6F9822F4858FA9F`");
//            dataBinder.putLocal("SortField", "dDocTitle");
//            dataBinder.putLocal("SortOrder", "Desc");
//            dataBinder.putLocal("isAddFolderMetadata", "1");
//            ServiceResponse  response = idcClient.sendRequest(userContext, dataBinder);
//            
//             dataBinder = response.getResponseAsBinder();
//
//            DataResultSet resultSet = dataBinder.getResultSet("SearchResults");
//            DataObject localDataObj = dataBinder.getLocalData();
//
//            for (DataObject dataObj : resultSet.getRows()) {
//                System.out.println("Data from GET_SEARCH_RESULTS call for content id::" + dataObj.get("dDocName"));
//                System.out.println(dataObj.get("dDocName"));
//                System.out.println(dataObj.get("dDocAuthor"));
//                //  pojoObj.setDocCreatedDate(dataObj.get("fCreateDate"));
//                System.out.println(dataObj.get("dDocCreatedDate"));
//                System.out.println(dataObj.get("dID"));
//                System.out.println(dataObj.get("dOriginalName"));
//                System.out.println(dataObj.get("dFileSize"));
//                System.out.println(dataObj.get("dDocTitle"));
//
//                System.out.println(dataObj.get("dFormat"));
//                System.out.println(dataObj.get("dDocLastModifier"));
//                System.out.println(dataObj.get("dDocLastModifiedDate"));
//                System.out.println(dataObj.get("dSecurityGroup"));
//            }
        } catch (IdcClientException ice) {
            // TODO: Add catch code
            ice.printStackTrace();
        }
        }

    
    
    
    
    public static void main(String args[]){
        ImageSlide aaaa = new ImageSlide();
        aaaa.folderListItemsMetadata();
    }
}
