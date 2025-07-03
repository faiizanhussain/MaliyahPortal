package view.managedBean;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Set;

import java.util.TreeMap;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichImage;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

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

public class UpdateAndDeleteNew {
    private RichImage bindNewsImage1;
    private RichImage bindNewsImage2;
    private RichImage bindNewsImage3;
    private RichImage bindNewsImage4;
    private RichOutputText bindNewsTitle1;
    private RichOutputText bindNewsTitle2;
    private RichOutputText bindNewsTitle3;
    private RichOutputText bindNewsTitle4;
    private RichOutputText bindNewsSummary4;
    private RichOutputText bindNewsSummary3;
    private RichOutputText bindNewsSummary2;
    private RichOutputText bindNewsSummary1;
    private RichPanelGroupLayout bindNews1PG;
    private RichButton bindCategory1;
    private RichButton bindCategory2;
    private RichButton bindCategory3;
    private RichButton bindCategory4;
    private RichPopup bindNewsDeleteConfirmationPopup;
    private RichPopup bindDeletedNewsSuccessPopup;
    private RichTable bindNewsTable;
    private RichPopup bindNewsUpdatePopup;
    private RichPopup bindEditNewsPopup;
    private RichInputFile bindUploadImage;
    private Map<String,String> fileNameAndFileContenttype = new HashMap<String, String>();
    Map<String, InputStream> uploadedDocuments = new HashMap<String, InputStream>();
    private RichPopup bindDocTypePopup;
    private RichSelectOneChoice bindCategory;
    private RichInputText bindTitle;
    private RichInputText bindSummary;
    private RichPopup bindErrorsPopup;
    private RichInputText bindSerialNumber;


    public UpdateAndDeleteNew() {
    }
    ArrayList<Integer> serialNumList = new ArrayList<>();
    Map<Number, ArrayList<String>> newsMap = new HashMap<Number, ArrayList<String>>();
    public int newsImagePositon = 0;
    public int newsCount = 0;
    public String imagesUrl = "http://10.161.9.17/webcenter/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
    public String AllNewsInformation(){
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("imageURl", imagesUrl);
            
//        serialNumList.clear();
//        newsImagePositon = 0;
//        newsCount = 0;
//        newsMap.clear();
//
//        BindingContext bCtx = BindingContext.getCurrent();
//        DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
//        OperationBinding oper = DcCon.getOperationBinding("getNewsFromDB");
//        newsMap = (Map<Number, ArrayList<String>>) oper.execute();
//        ArrayList data = null;
//        System.out.println("newsMap*******" + newsMap);
//        if (!newsMap.isEmpty() && newsMap != null) {
//            serialNumList = getNewsSerialNumbers(newsMap);
//            System.out.println("serialNumList==" + serialNumList);
//            newsCount = serialNumList.size() + 1;
//            newsCount = newsCount / 4;
//            for (int i = newsImagePositon; i <= newsImagePositon + 3; i++) {
//                if (i == newsImagePositon) {
//                    System.out.println("i ==" + i);
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        System.out.println("kay val" + entry.getKey());
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO)) {
//                            data = (ArrayList) entry.getValue();
//                        }
//                    }
//                    String newImageUrl1 = imagesUrl;
//                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUrl1 = newImageUrl1.concat(srNO + ".png");
//                    System.out.println("1 newImageUrl1==" + newImageUrl1);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("SerialNumber1", srNO);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_1", newImageUrl1);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title1", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary1", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category1", data.get(2));
//                }
//                if (i == newsImagePositon + 1 && serialNumList.size()>=2) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO)) {
//                            data = (ArrayList) entry.getValue();
//                        }
//                    }
//                    System.out.println("First data=" + data);
//                    String newImageUr12 = imagesUrl;
//                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr12 = newImageUr12.concat(srNO + ".png");
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("SerialNumber2", srNO);
//                    
//                    System.out.println("2 newImageUrl1==" + newImageUr12);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_2", newImageUr12);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title2", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary2", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category2", data.get(2));
//                }
//                if (i == newsImagePositon + 2 && serialNumList.size()>=3) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO))
//                            data = (ArrayList) entry.getValue();
//                    }
//                    String newImageUr13 = imagesUrl;
//                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr13 = newImageUr13.concat(srNO + ".png");
//                    System.out.println("3 newImageUrl1==" + newImageUr13);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("SerialNumber3", srNO);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_3", newImageUr13);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title3", data.get(0));
//                    System.out.println("data===" + data);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary3", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category3", data.get(2));
//                }
//                if (i == newsImagePositon + 3 && serialNumList.size()>=4) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO))
//                            data = (ArrayList) entry.getValue();
//                    }
//                    String newImageUr14 = imagesUrl;
//                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr14 = newImageUr14.concat(srNO + ".png");
//                    System.out.println("4 newImageUrl1==" + newImageUr14);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("SerialNumber4", srNO);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_4", newImageUr14);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title4", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary4", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category4", data.get(2));
//                }
//            }
//            newsImagePositon += 3;
//        }

        return "goToDisplay";
    }


    public ArrayList<Integer> getNewsSerialNumbers(Map newsList) {
        ArrayList<Integer> serialNum = new ArrayList<>();
        System.out.println("Before Sorting:");
        Set set = newsList.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            int srNo = Integer.parseInt(me.getKey().toString());
            serialNum.add(srNo);
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        Collections.sort(serialNum);
        Collections.reverse((List) serialNum);
        return serialNum;
    }
    //    public Map getNewsCategory(){
    //        Map<Integer, String> newsCategory = new HashMap<Integer, String>();
    //
    //
    //    }
//    public void displayNextNews(ActionEvent actionEvent) {
//        // Add event code here...
//        System.out.println("Inside displayNextNews");
//        ArrayList data = null;
//        newsCount = serialNumList.size() + 1;
//        newsCount = newsCount / 4;
//        System.out.println("newsImagePositon==" + newsImagePositon);
//        int j = newsImagePositon;
//
//
//        for (int i = newsImagePositon + 4; i > j; i--) {
//            if (newsCount >= 1 && serialNumList.size() > i) {
//                if (i == newsImagePositon + 4) {
//
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO)) {
//                            data = (ArrayList) entry.getValue();
//                        }
//                    }
//                    String newImageUrl1 = imagesUrl;
//                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUrl1 = newImageUrl1.concat(srNO + ".png");
//                    System.out.println("Next 1 newImageUrl1==" + newImageUrl1);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_1", newImageUrl1);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title1", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary1", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category1", data.get(2));
//                }
//                if (i == newsImagePositon + 3) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO)) {
//                            data = (ArrayList) entry.getValue();
//                        }
//                    }
//                    String newImageUr12 = imagesUrl;
//                     //   "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr12 = newImageUr12.concat(srNO + ".png");
//                    System.out.println("Next 2 newImageUrl1==" + newImageUr12);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_2", newImageUr12);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title2", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary2", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category2", data.get(2));
//                }
//
//                if (i == newsImagePositon + 2) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO))
//                            data = (ArrayList) entry.getValue();
//                    }
//                    System.out.println("Next data=" + data);
//                    String newImageUr13 = imagesUrl;
//                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr13 = newImageUr13.concat(srNO + ".png");
//                    System.out.println("Next 3 newImageUrl1==" + newImageUr13);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_3", newImageUr13);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title3", data.get(0));
//                    System.out.println("data===" + data);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary3", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category3", data.get(2));
//                }
//                if (i == newsImagePositon + 1) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO))
//                            data = (ArrayList) entry.getValue();
//                    }
//                    System.out.println("Next data=" + data);
//                    String newImageUr14 = imagesUrl;
//                      //  "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr14 = newImageUr14.concat(srNO + ".png");
//                    System.out.println("Next 4 newImageUrl1==" + newImageUr14);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_4", newImageUr14);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title4", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary4", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category4", data.get(2));
//
//                }
//            }
//            newsCount--;
//        }
//        if (serialNumList.size() >= newsImagePositon) {
//            newsImagePositon = newsImagePositon + 4;
//            if (serialNumList.size() == newsImagePositon - 1) {
//                newsImagePositon = newsImagePositon - 4;
//            }
//        }
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage2);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage3);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage4);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle2);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle3);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle4);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary4);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary3);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary2);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory2);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory3);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory4);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNews1PG);
//    }
//
//    public void displayPreviousNews(ActionEvent actionEvent) {
//        // Add event code here...
//        System.out.println("Inside displayPreviousNews");
//        ArrayList data = null;
//        newsCount = serialNumList.size() + 1;
//        newsCount = newsCount / 4;
//        System.out.println("newsImagePositon==" + newsImagePositon);
//        int j = newsImagePositon;
//
//
//        for (int i = newsImagePositon - 4; i < j; i--) {
//            if (newsCount >= 1 && serialNumList.size() > i && newsImagePositon > 3) {
//                if (i == newsImagePositon - 4) {
//
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO)) {
//                            data = (ArrayList) entry.getValue();
//                        }
//                    }
//                    String newImageUrl1 = imagesUrl;
//                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUrl1 = newImageUrl1.concat(srNO + ".png");
//                    System.out.println("Next 1 newImageUrl1==" + newImageUrl1);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_1", newImageUrl1);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title1", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary1", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category1", data.get(2));
//                }
//                if (i == newsImagePositon - 5) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO)) {
//                            data = (ArrayList) entry.getValue();
//                        }
//                    }
//                    String newImageUr12 = imagesUrl;
//                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr12 = newImageUr12.concat(srNO + ".png");
//                    System.out.println("Next 2 newImageUrl1==" + newImageUr12);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_2", newImageUr12);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title2", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary2", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category2", data.get(2));
//                }
//
//                if (i == newsImagePositon - 6) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO))
//                            data = (ArrayList) entry.getValue();
//                    }
//                    System.out.println("Next data=" + data);
//                    String newImageUr13 = imagesUrl;
//                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr13 = newImageUr13.concat(srNO + ".png");
//                    System.out.println("Next 3 newImageUrl1==" + newImageUr13);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_3", newImageUr13);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title3", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary3", data.get(1));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category3", data.get(2));
//                }
//                if (i == newsImagePositon - 7) {
//                    String srNO = serialNumList.get(i).toString();
//                    System.out.println("srNO==" + srNO);
//                    for (Map.Entry entry : newsMap.entrySet()) {
//                        String key = (String) entry.getKey().toString();
//                        if (key.equalsIgnoreCase(srNO))
//                            data = (ArrayList) entry.getValue();
//                    }
//                    String newImageUr14 = imagesUrl;
//                      //  "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
//                    newImageUr14 = newImageUr14.concat(srNO + ".png");
//                    System.out.println("Next 4 newImageUrl1==" + newImageUr14);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("imageURl_4", newImageUr14);
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("title4", data.get(0));
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("summary4", data.get(1));
//                    newsImagePositon = newsImagePositon - 4;
//                    ADFContext.getCurrent()
//                              .getPageFlowScope()
//                              .put("category4", data.get(2));
//                }
//            }
//            newsCount--;
//
//        }
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage2);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage3);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage4);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle2);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle3);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle4);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary4);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary3);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary2);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory2);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory3);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory4);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNews1PG);
//    }
//    public void setBindNewsImage1(RichImage bindNewsImage1) {
//        this.bindNewsImage1 = bindNewsImage1;
//    }
//
//    public RichImage getBindNewsImage1() {
//        return bindNewsImage1;
//    }
//
//    public void setBindNewsImage2(RichImage bindNewsImage2) {
//        this.bindNewsImage2 = bindNewsImage2;
//    }
//
//    public RichImage getBindNewsImage2() {
//        return bindNewsImage2;
//    }
//
//    public void setBindNewsImage3(RichImage bindNewsImage3) {
//        this.bindNewsImage3 = bindNewsImage3;
//    }
//
//    public RichImage getBindNewsImage3() {
//        return bindNewsImage3;
//    }
//
//    public void setBindNewsImage4(RichImage bindNewsImage4) {
//        this.bindNewsImage4 = bindNewsImage4;
//    }
//
//    public RichImage getBindNewsImage4() {
//        return bindNewsImage4;
//    }
//
//    public void setBindNewsTitle1(RichOutputText bindNewsTitle1) {
//        this.bindNewsTitle1 = bindNewsTitle1;
//    }
//
//    public RichOutputText getBindNewsTitle1() {
//        return bindNewsTitle1;
//    }
//
//    public void setBindNewsTitle2(RichOutputText bindNewsTitle2) {
//        this.bindNewsTitle2 = bindNewsTitle2;
//    }
//
//    public RichOutputText getBindNewsTitle2() {
//        return bindNewsTitle2;
//    }
//
//    public void setBindNewsTitle3(RichOutputText bindNewsTitle3) {
//        this.bindNewsTitle3 = bindNewsTitle3;
//    }
//
//    public RichOutputText getBindNewsTitle3() {
//        return bindNewsTitle3;
//    }
//
//    public void setBindNewsTitle4(RichOutputText bindNewsTitle4) {
//        this.bindNewsTitle4 = bindNewsTitle4;
//    }
//
//    public RichOutputText getBindNewsTitle4() {
//        return bindNewsTitle4;
//    }
//
//    public void setBindNewsSummary4(RichOutputText bindNewsSummary4) {
//        this.bindNewsSummary4 = bindNewsSummary4;
//    }
//
//    public RichOutputText getBindNewsSummary4() {
//        return bindNewsSummary4;
//    }
//
//    public void setBindNewsSummary3(RichOutputText bindNewsSummary3) {
//        this.bindNewsSummary3 = bindNewsSummary3;
//    }
//
//    public RichOutputText getBindNewsSummary3() {
//        return bindNewsSummary3;
//    }
//
//    public void setBindNewsSummary2(RichOutputText bindNewsSummary2) {
//        this.bindNewsSummary2 = bindNewsSummary2;
//    }
//
//    public RichOutputText getBindNewsSummary2() {
//        return bindNewsSummary2;
//    }
//
//    public void setBindNewsSummary1(RichOutputText bindNewsSummary1) {
//        this.bindNewsSummary1 = bindNewsSummary1;
//    }
//
//    public RichOutputText getBindNewsSummary1() {
//        return bindNewsSummary1;
//    }
//
//    public void setBindNews1PG(RichPanelGroupLayout bindNews1PG) {
//        this.bindNews1PG = bindNews1PG;
//    }
//
//    public RichPanelGroupLayout getBindNews1PG() {
//        return bindNews1PG;
//    }
//
//    public void setNewCount(int newCount) {
//        this.newsCount = newCount;
//    }
//
//    public int getNewCount() {
//        return newsCount;
//    }
//
//    public void setBindCategory1(RichButton bindCategory1) {
//        this.bindCategory1 = bindCategory1;
//    }
//
//    public RichButton getBindCategory1() {
//        return bindCategory1;
//    }
//
//    public void setBindCategory2(RichButton bindCategory2) {
//        this.bindCategory2 = bindCategory2;
//    }
//
//    public RichButton getBindCategory2() {
//        return bindCategory2;
//    }
//
//    public void setBindCategory3(RichButton bindCategory3) {
//        this.bindCategory3 = bindCategory3;
//    }
//
//    public RichButton getBindCategory3() {
//        return bindCategory3;
//    }
//
//    public void setBindCategory4(RichButton bindCategory4) {
//        this.bindCategory4 = bindCategory4;
//    }
//
//    public RichButton getBindCategory4() {
//        return bindCategory4;
//    }

    public void deleteCurrentNews(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindNewsDeleteConfirmationPopup.show(hints);
            
       
        
//       String serialNumber = (String) ADFContext.getCurrent().getPageFlowScope().get("SerialNumber1");
//        int srNo = Integer.parseInt(serialNumber);
//      String newsDeleteStatus = deleteNews(srNo);
    }
    
    public String deleteNews(int srNo){
        BindingContext bCtx = BindingContext.getCurrent();
        DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
        OperationBinding oper = DcCon.getOperationBinding("deleteNews");
        oper.getParamsMap().put("parameter_name", srNo);
        String flag = (String) oper.execute(); 
        return flag;
    }

    public void setBindNewsDeleteConfirmationPopup(RichPopup bindNewsDeleteConfirmationPopup) {
        this.bindNewsDeleteConfirmationPopup = bindNewsDeleteConfirmationPopup;
    }

    public RichPopup getBindNewsDeleteConfirmationPopup() {
        return bindNewsDeleteConfirmationPopup;
    }

    public void deleteNews(ActionEvent actionEvent) {
        BindingContext bCtx = BindingContext.getCurrent();
        DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
        OperationBinding oper = DcCon.getOperationBinding("Delete");
        oper.execute();
        OperationBinding oper1 = DcCon.getOperationBinding("Commit");
        oper1.execute();
        bindNewsDeleteConfirmationPopup.hide();
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTable);
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        bindDeletedNewsSuccessPopup.show(hints);
        
    }

    public void closeNewsDeleteConfirmationPopup(ActionEvent actionEvent) {
        // Add event code here...
        bindNewsDeleteConfirmationPopup.hide();
    }

    public void setBindDeletedNewsSuccessPopup(RichPopup bindDeletedNewsSuccessPopup) {
        this.bindDeletedNewsSuccessPopup = bindDeletedNewsSuccessPopup;
    }

    public RichPopup getBindDeletedNewsSuccessPopup() {
        return bindDeletedNewsSuccessPopup;
    }

    public void setBindNewsTable(RichTable bindNewsTable) {
        this.bindNewsTable = bindNewsTable;
    }

    public RichTable getBindNewsTable() {
        return bindNewsTable;
    }

    public void updateNewsListener(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("inside addNewsToTable");
        ArrayList<String> newsDetails = new ArrayList<String>();
        try {
            String newsTitle = (String) bindTitle.getValue();
            String newsSummary = (String) bindSummary.getValue();
            String catefory = (String) bindCategory.getValue();
            BigDecimal serialNum = (BigDecimal) bindSerialNumber.getValue();
            
            newsDetails.add(newsTitle);
            newsDetails.add(newsSummary);
            newsDetails.add(catefory);
            newsDetails.add(serialNum.toString());
            
            BindingContext bCtx = BindingContext.getCurrent();
            DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
            OperationBinding oper = DcCon.getOperationBinding("Delete");
            oper.execute();
            OperationBinding oper1 = DcCon.getOperationBinding("Commit");
            oper1.execute();
            
            System.out.println("reocord deleted");
//            BindingContext bCtx = BindingContext.getCurrent();
//            DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
            OperationBinding oper2 = DcCon.getOperationBinding("updateNews");
            oper2.getParamsMap().put("news", newsDetails);
            String updateFlag = (String) oper2.execute();
            
            
            System.out.println("updateFlag=="+updateFlag);
         
            
            OperationBinding oper3 = DcCon.getOperationBinding("getLatestNewsSerialNumber");
            Integer newsSerialNumber = (Integer) oper3.execute();
             System.out.println("newsSerialNumber=="+newsSerialNumber);
            uploadDocumentToContent(newsSerialNumber);
//            
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
           bindEditNewsPopup.hide();
           bindNewsUpdatePopup.show(hints);
            
            bindCategory.resetValue();
            bindTitle.resetValue();
            bindSummary.resetValue();
            bindUploadImage.resetValue();
            bindSummary.setValue("");
            bindTitle.setValue("");
            bindCategory.setValue("");
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindTitle);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindSummary);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindUploadImage);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTable);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }    
        
    }

    public void setBindNewsUpdatePopup(RichPopup bindNewsUpdatePopup) {
        this.bindNewsUpdatePopup = bindNewsUpdatePopup;
    }

    public RichPopup getBindNewsUpdatePopup() {
        return bindNewsUpdatePopup;
    }

    public void setBindEditNewsPopup(RichPopup bindEditNewsPopup) {
        this.bindEditNewsPopup = bindEditNewsPopup;
    }

    public RichPopup getBindEditNewsPopup() {
        return bindEditNewsPopup;
    }

    public void updateImage(ValueChangeEvent valueChangeEvent) {
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

    public void setBindUploadImage(RichInputFile bindUploadImage) {
        this.bindUploadImage = bindUploadImage;
    }

    public RichInputFile getBindUploadImage() {
        return bindUploadImage;
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

    public void setBindDocTypePopup(RichPopup bindDocTypePopup) {
        this.bindDocTypePopup = bindDocTypePopup;
    }

    public RichPopup getBindDocTypePopup() {
        return bindDocTypePopup;
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

                                  bindNewsUpdatePopup.show(hints); 
                           
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

    public void setBindErrorsPopup(RichPopup bindErrorsPopup) {
        this.bindErrorsPopup = bindErrorsPopup;
    }

    public RichPopup getBindErrorsPopup() {
        return bindErrorsPopup;
    }

    public void setBindSerialNumber(RichInputText bindSerialNumber) {
        this.bindSerialNumber = bindSerialNumber;
    }

    public RichInputText getBindSerialNumber() {
        return bindSerialNumber;
    }

    public void editNewsListener(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
               bindEditNewsPopup.show(hints); 
        
    }
}
