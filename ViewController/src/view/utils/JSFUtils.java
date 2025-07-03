package view.utils;

import java.io.IOException;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.model.serialize.HdaBinderSerializer;
import oracle.stellent.ridc.protocol.ServiceResponse;

public class JSFUtils {
    public JSFUtils() {
        super();
    }
    public static String getDocumentURL(String securityGroup){
        String documentUrl ="http://10.161.9.17:8888/webcenter/content/conn/wccConnection/path/Enterprise Libraries/NewMOFPortalSecondDraft/MaliyahDocuments/";
        if(securityGroup.equalsIgnoreCase("ChartOfAccounts")){
            documentUrl=documentUrl+"Chart of Accounts/"; 
        }
        if(securityGroup.equalsIgnoreCase("Deliverables")){
            documentUrl=documentUrl+"Deliverables/"; 
        }
        if(securityGroup.equalsIgnoreCase("GuidesAndManuals")){
            documentUrl=documentUrl+"Guides and Manuals/"; 
        }
        if(securityGroup.equalsIgnoreCase("Legislations")){
            documentUrl=documentUrl+"Legislations/"; 
        }
        if(securityGroup.equalsIgnoreCase("Policies")){
            documentUrl=documentUrl+"Policies/"; 
        }
        if(securityGroup.equalsIgnoreCase("Presentations")){
            documentUrl=documentUrl+"Presentations/"; 
        }
        if(securityGroup.equalsIgnoreCase("Reports")){
            documentUrl=documentUrl+"Reports/"; 
        }
        if(securityGroup.equalsIgnoreCase("StateFinalAccounts")){
            documentUrl=documentUrl+"State Final Accounts/"; 
        }
        if(securityGroup.equalsIgnoreCase("StateGeneralBudget")){
            documentUrl=documentUrl+"State General Budget/"; 
        }
        if(securityGroup.equalsIgnoreCase("TestScripts")){
            documentUrl=documentUrl+"Test Scripts/"; 
        }
       return documentUrl; 
    }
    
    public static String getDocURl(String did){
        IdcClientManager manager = new IdcClientManager ();
        String dourl = null;
         try{
          // Create a new IdcClient Connection using idc protocol (i.e. socket connection to Content Server)
             IdcClient idcClient = manager.createClient("idc://10.161.9.17:4444");
             // Create new context using the 'sysadmin' user
             IdcContext userContext = new IdcContext("weblogic", "weblogic123");
             HdaBinderSerializer serializer = new HdaBinderSerializer("UTF-8", idcClient.getDataFactory());

             DataBinder dataBinder = idcClient.createBinder();
              dataBinder.putLocal("IdcService", "DOC_INFO");
               dataBinder.putLocal("dID",did);

          // Write the data binder for the request to stdout
          serializer.serializeBinder (System.out, dataBinder);

          // Send the request to Content Server
          ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);

          // Get the data binder for the response from Content Server
          DataBinder responseData = response.getResponseAsBinder();
          // Write the response data binder to stdout
          serializer.serializeBinder (System.out, responseData);

          // Retrieve the DOC_INFO ResultSet from the response
          DataResultSet resultSet = responseData.getResultSet("DOC_INFO");

          // Iterate over the ResultSet, retrieve properties from the content item (should only be one row)
          for (DataObject dataObject : resultSet.getRows ()) {
          System.out.println ("Title is: " + dataObject.get ("dDocTitle"));
           System.out.println ("Author is: " + dataObject.get ("dDocAuthor"));
         System.out.println ("MaliyahDocUrl: " + dataObject.get ("xMaliyahDocUrl"));
               dourl = dataObject.get ("xMaliyahDocUrl");
             System.out.println ("fParentPath is: " + dataObject.get ("fParentPath"));
           //  System.out.println ("Author is: " + dataObject.get ("dDocAuthor"));
            // System.out.println ("Author is: " + dataObject.get ("dDocAuthor"));
         }

         } catch (IdcClientException ice){
           ice.printStackTrace();
         } catch (IOException e) {
        }
         return dourl;
    }
}
