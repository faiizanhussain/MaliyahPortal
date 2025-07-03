package view.managedBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.adf.share.ADFContext;

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
public class Document {
    public Document() {
    }
private List<Map> DocumentList= new ArrayList<Map>();
private Map<String,String> DocsMap;

    public String onLoad() {
        // Add event code here...
        String documentUrl ="http://10.161.9.17:8888/webcenter/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/Documents/";
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
          binder.putLocal ("ResultCount", "200");

          ServiceResponse response = idcClient.sendRequest(userContext, binder);
          // Get the data binder for the response from Content Server
          DataBinder responseData = response.getResponseAsBinder();
          // Write the response data binder to stdout
          // get the binder
          DataResultSet resultSet = responseData.getResultSet ("SearchResults");

          // loop over the results
          for (DataObject dataObject : resultSet.getRows ()) {
              DocsMap = new HashMap<String,String>();
              if(dataObject.get("dSecurityGroup").equalsIgnoreCase("Public")){
              if(dataObject.get("dDocName")!=null){
                DocsMap.put("Doccontentid", dataObject.get("dDocName"));   
              }
              if(dataObject.get("dDocAuthor")!=null){
                DocsMap.put("Doccreatedby", dataObject.get("dDocAuthor"));    
               }
              if(dataObject.get("dDocCreateDate")!=null){
                DocsMap.put("Doccreateddate", dataObject.get("dDocCreateDate")); 
               }
              if(dataObject.get("dID")!=null){
                   DocsMap.put("Docdid", dataObject.get("dID"));   
               }
              if(dataObject.get("dOriginalName")!=null){
                   DocsMap.put("Docoriginalname", dataObject.get("dOriginalName"));  
               }
              if(dataObject.get("VaultFileSize")!=null){
                   DocsMap.put("Docsize", dataObject.get("VaultFileSize"));   
               }
              if(dataObject.get("dFormat")!=null){
                DocsMap.put("Doccontenttype", dataObject.get("dFormat"));   
               }
              if(dataObject.get("dDocTitle")!=null){
                   DocsMap.put("Doctitle", dataObject.get("dDocTitle"));   
               }
              if(dataObject.get("dDocLastModifier")!=null){
                   DocsMap.put("Docmodifiedby", dataObject.get("dDocLastModifier"));   
               }
              if(dataObject.get("dDocLastModifiedDate")!=null){
                   DocsMap.put("Docmodifieddate", dataObject.get("dDocLastModifiedDate"));   
               }
              if(dataObject.get("dSecurityGroup")!=null){
                DocsMap.put("Docsecuritygroup", dataObject.get("dSecurityGroup"));   
               }
              if(dataObject.get("dSecurityGroup")!=null){
                   DocsMap.put("Docsourceaccount", dataObject.get("dSecurityGroup"));   
               }
                DocsMap.put("Documenturl", documentUrl+dataObject.get("dOriginalName"));
                System.out.println("doc url =="+documentUrl+dataObject.get("dOriginalName"));
              System.out.println ("Title is: " + dataObject.get ("dDocTitle"));
              System.out.println ("Author is: " + dataObject.get ("dDocAuthor"));
              System.out.println ("dID: " + dataObject.get ("dID"));
                  System.out.println ("date " + dataObject.get("dDocCreateDate"));
              DocumentList.add(DocsMap);
              }
          }  
        ADFContext.getCurrent().getPageFlowScope().put("documentList", DocumentList);
        } catch(IdcClientException ice) {
          // TODO: Add catch code
          ice.printStackTrace();
        }
        
        return "gotToNext";
    }
}
