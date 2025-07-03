package view.managedBean;

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

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichImage;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;
import java.io.Serializable;
public class DisplayNews implements Serializable {
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
    private RichButton bindNextButton;
    private RichButton bindPreviousButton;
    private String languageFlag = "ar";
    private RichButton bindEnglishLangBtn;
    private RichButton bindArabicLangBtn;
    private RichButton bindViewMore1;
    private RichButton bindViewMore2;
    private RichButton bindViewMore3;
    private RichButton bindViewMore4;

    public DisplayNews() {
        //getNewsfromBackend();
    }
    ArrayList<Integer> serialNumList = new ArrayList<>();
    Map<Number, ArrayList<String>> newsMap = new HashMap<Number, ArrayList<String>>();
    public int newsImagePositon = 0;
    public int newsCount = 0;
    public String imagesUrl = "http://10.161.9.17/webcenter/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
    public String getNewsfromBackend() {
        serialNumList.clear();
        newsImagePositon = 0;
        newsCount = 0;
        newsMap.clear();

        BindingContext bCtx = BindingContext.getCurrent();
        DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
        OperationBinding oper = DcCon.getOperationBinding("getNewsFromDB");
        newsMap = (Map<Number, ArrayList<String>>) oper.execute();
        ArrayList data = null;
        System.out.println("jayanand=="+newsMap);
        if (!newsMap.isEmpty() && newsMap != null) {
            serialNumList = getNewsSerialNumbers(newsMap);
            System.out.println("serialNumList==" + serialNumList);
            newsCount = serialNumList.size() + 1;
            newsCount = newsCount / 4;
            for (int i = newsImagePositon; i <= newsImagePositon + 3; i++) {
                if (i == newsImagePositon) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUrl1 = imagesUrl;
                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUrl1 = newImageUrl1.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_1", newImageUrl1);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr1", data.get(5));
                   
                }
                if (i == newsImagePositon + 1 && serialNumList.size()>=2) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUr12 = imagesUrl;
                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr12 = newImageUr12.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_2", newImageUr12);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng2", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng2", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng2", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr2", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr2", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr2", data.get(5));
                }
                if (i == newsImagePositon + 2 && serialNumList.size()>=3) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    String newImageUr13 = imagesUrl;
                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr13 = newImageUr13.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_3", newImageUr13);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng3", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng3", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng3", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr3", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr3", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr3", data.get(5));
                }
                if (i == newsImagePositon + 3 && serialNumList.size()>=4) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    String newImageUr14 = imagesUrl;
                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr14 = newImageUr14.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_4", newImageUr14);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng4", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng4", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng4", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr4", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr4", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr4", data.get(5));
                }
            }
            newsImagePositon += 3;
        }

        return "goToDisplay";
    }


    public ArrayList<Integer> getNewsSerialNumbers(Map newsList) {
        ArrayList<Integer> serialNum = new ArrayList<>();
        Set set = newsList.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            int srNo = Integer.parseInt(me.getKey().toString());
            serialNum.add(srNo);
            System.out.print(me.getKey() + ": ");
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
    public void displayNextNews(ActionEvent actionEvent) {
        // Add event code here...
        ArrayList data = null;
        newsCount = serialNumList.size() + 1;
        newsCount = newsCount / 4;
        int j = newsImagePositon;

        if(newsImagePositon < serialNumList.size()){
        for (int i = newsImagePositon + 4; i > j; i--) {
            if (newsCount >= 0 && serialNumList.size() > i) {
                if (i == newsImagePositon + 1) {

                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUrl1 = imagesUrl;
                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUrl1 = newImageUrl1.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_1", newImageUrl1);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr1", data.get(5));
                }
                if (i == newsImagePositon + 2) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUr12 = imagesUrl;
                     //   "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr12 = newImageUr12.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_2", newImageUr12);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_2", newImageUr12);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng2", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng2", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng2", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr2", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr2", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr2", data.get(5));
                }

                if (i == newsImagePositon + 3) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    String newImageUr13 = imagesUrl;
                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr13 = newImageUr13.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_3", newImageUr13);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng3", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng3", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng3", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr3", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr3", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr3", data.get(5));
                }
                if (i == newsImagePositon + 4) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    String newImageUr14 = imagesUrl;
                      //  "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr14 = newImageUr14.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_4", newImageUr14);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng4", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng4", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng4", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr4", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr4", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr4", data.get(5));

                }
            }else{
                if(serialNumList.size()== 5){
                    String srNO = serialNumList.get(4).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUrl1 = imagesUrl;
                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUrl1 = newImageUrl1.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_1", newImageUrl1);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("title1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summary1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("category1", data.get(2));                    
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr1", data.get(5));
                } 
                if(serialNumList.size()== 6){
                    String srNO = serialNumList.get(5).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUrl2 = imagesUrl;
                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUrl2 = newImageUrl2.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_2", newImageUrl2);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng2", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng2", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng2", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr2", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr2", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr2", data.get(5));
                } 
                if(serialNumList.size()== 7){
                    String srNO = serialNumList.get(6).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUrl3 = imagesUrl;
                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUrl3 = newImageUrl3.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_3", newImageUrl3);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng3", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng3", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng3", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr3", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr3", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr3", data.get(5));
                } 
                bindPreviousButton.setDisabled(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindPreviousButton);
                
                break;
            }
            newsCount--;
            bindPreviousButton.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindPreviousButton);
        }
        }else{
            bindNextButton.setDisabled(true);
            bindPreviousButton.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindNextButton);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindPreviousButton);
        }
        if (serialNumList.size() >= newsImagePositon) {
            newsImagePositon = newsImagePositon + 4;
            if(newsImagePositon>serialNumList.size()){
            newsImagePositon = serialNumList.size();
                
            }
        }
        refreshComponent();
    }
    
    

    public void displayPreviousNews(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Inside displayPreviousNews");
        ArrayList data = null;
        newsCount = serialNumList.size() + 1;
        newsCount = newsCount / 4;
        System.out.println("newsImagePositon==" + newsImagePositon);
        newsImagePositon = newsImagePositon-4;
        int j = newsImagePositon;

        if(newsImagePositon>=3){
            
        for (int i = newsImagePositon - 4; i < j; i++) {
            
            if (newsCount >= 0 && serialNumList.size() > i && newsImagePositon > 0) {
                if (i == newsImagePositon - 1) {

                    String srNO =null;
                    if(i==3){
                         srNO = serialNumList.get(0).toString(); 
                    }else{
                     srNO = serialNumList.get(i-1).toString();
                    }
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUrl1 = imagesUrl;
                        //"/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUrl1 = newImageUrl1.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_1", newImageUrl1);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr1", data.get(5));
                }
                if (i == newsImagePositon - 2) {
                    String srNO =null;
                    if(i==2){
                         srNO = serialNumList.get(1).toString(); 
                    }else{
                     srNO = serialNumList.get(i-1).toString();
                    }
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    String newImageUr12 = imagesUrl;
                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr12 = newImageUr12.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_2", newImageUr12);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_2", newImageUr12);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng2", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng2", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng2", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr2", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr2", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr2", data.get(5));
                }

                if (i == newsImagePositon - 3) {
                    String srNO =null;
                    if(i==1){
                         srNO = serialNumList.get(2).toString(); 
                    }else{
                     srNO = serialNumList.get(i-1).toString();
                    }
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    String newImageUr13 = imagesUrl;
                       // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr13 = newImageUr13.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_3", newImageUr13);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng3", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng3", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng3", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr3", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr3", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr3", data.get(5));
                }
                if (i == newsImagePositon - 4) {
                    String srNO =null;
                    if(i==0){
                         srNO = serialNumList.get(3).toString(); 
                    }else{
                     srNO = serialNumList.get(i-1).toString();
                    }
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : newsMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    String newImageUr14 = imagesUrl;
                      //  "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                    newImageUr14 = newImageUr14.concat(srNO + ".png");
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("imageURl_4", newImageUr14);
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerEng4", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagEng4", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryEng4", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("headerAr4", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("tagAr4", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("summaryAr4", data.get(5));
                }
            }
            newsCount--;
            bindNextButton.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindNextButton);

        }
       
        }else{
                  String srNO = null;
                  srNO = serialNumList.get(0).toString();
                System.out.println("srNO==" + srNO);
                for (Map.Entry entry : newsMap.entrySet()) {
                    String key = (String) entry.getKey().toString();
                    if (key.equalsIgnoreCase(srNO))
                        data = (ArrayList) entry.getValue();
                }
                String newImageUr11 = imagesUrl;
                   // "/content/conn/wccConnection/path/Enterprise%20Libraries/Maliyah_Portal/MaliyahDocuments/News/News_";
                newImageUr11 = newImageUr11.concat(srNO + ".png");
                ADFContext.getCurrent()
                          .getPageFlowScope()
                          .put("imageURl_1", newImageUr11);
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("headerEng1", data.get(0));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("tagEng1", data.get(1));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("summaryEng1", data.get(2));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("headerAr1", data.get(3));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("tagAr1", data.get(4));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("summaryAr1", data.get(5));
            
             srNO = serialNumList.get(1).toString();
            System.out.println("srNO==" + srNO);
            for (Map.Entry entry : newsMap.entrySet()) {
               String key = (String) entry.getKey().toString();
               if (key.equalsIgnoreCase(srNO))
                   data = (ArrayList) entry.getValue();
            }
            String newImageUr12 = imagesUrl;
            newImageUr12 = newImageUr12.concat(srNO + ".png");
            ADFContext.getCurrent()
                     .getPageFlowScope()
                     .put("imageURl_2", newImageUr12);
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("imageURl_2", newImageUr12);
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("headerEng2", data.get(0));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("tagEng2", data.get(1));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("summaryEng2", data.get(2));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("headerAr2", data.get(3));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("tagAr2", data.get(4));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("summaryAr2", data.get(5));


            srNO = serialNumList.get(2).toString();
            System.out.println("srNO==" + srNO);
            for (Map.Entry entry : newsMap.entrySet()) {
              String key = (String) entry.getKey().toString();
              if (key.equalsIgnoreCase(srNO))
                  data = (ArrayList) entry.getValue();
            }
            String newImageUr13 = imagesUrl;
            newImageUr13 = newImageUr13.concat(srNO + ".png");
            ADFContext.getCurrent()
                    .getPageFlowScope()
                    .put("imageURl_3", newImageUr13);
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("headerEng3", data.get(0));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("tagEng3", data.get(1));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("summaryEng3", data.get(2));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("headerAr3", data.get(3));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("tagAr3", data.get(4));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("summaryAr3", data.get(5));
            
            srNO = serialNumList.get(3).toString();
            System.out.println("srNO==" + srNO);
            for (Map.Entry entry : newsMap.entrySet()) {
              String key = (String) entry.getKey().toString();
              if (key.equalsIgnoreCase(srNO))
                  data = (ArrayList) entry.getValue();
            }
            String newImageUr14 = imagesUrl;
            newImageUr14 = newImageUr14.concat(srNO + ".png");
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("headerEng4", data.get(0));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("tagEng4", data.get(1));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("summaryEng4", data.get(2));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("headerAr4", data.get(3));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("tagAr4", data.get(4));
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .put("summaryAr4", data.get(5));
            
            
            
            bindPreviousButton.setDisabled(true);
            bindNextButton.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindPreviousButton);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindNextButton);  
        }
        refreshComponent();
    }
    public void setBindNewsImage1(RichImage bindNewsImage1) {
        this.bindNewsImage1 = bindNewsImage1;
    }

    public RichImage getBindNewsImage1() {
        return bindNewsImage1;
    }

    public void setBindNewsImage2(RichImage bindNewsImage2) {
        this.bindNewsImage2 = bindNewsImage2;
    }

    public RichImage getBindNewsImage2() {
        return bindNewsImage2;
    }

    public void setBindNewsImage3(RichImage bindNewsImage3) {
        this.bindNewsImage3 = bindNewsImage3;
    }

    public RichImage getBindNewsImage3() {
        return bindNewsImage3;
    }

    public void setBindNewsImage4(RichImage bindNewsImage4) {
        this.bindNewsImage4 = bindNewsImage4;
    }

    public RichImage getBindNewsImage4() {
        return bindNewsImage4;
    }

    public void setBindNewsTitle1(RichOutputText bindNewsTitle1) {
        this.bindNewsTitle1 = bindNewsTitle1;
    }

    public RichOutputText getBindNewsTitle1() {
        return bindNewsTitle1;
    }

    public void setBindNewsTitle2(RichOutputText bindNewsTitle2) {
        this.bindNewsTitle2 = bindNewsTitle2;
    }

    public RichOutputText getBindNewsTitle2() {
        return bindNewsTitle2;
    }

    public void setBindNewsTitle3(RichOutputText bindNewsTitle3) {
        this.bindNewsTitle3 = bindNewsTitle3;
    }

    public RichOutputText getBindNewsTitle3() {
        return bindNewsTitle3;
    }

    public void setBindNewsTitle4(RichOutputText bindNewsTitle4) {
        this.bindNewsTitle4 = bindNewsTitle4;
    }

    public RichOutputText getBindNewsTitle4() {
        return bindNewsTitle4;
    }

    public void setBindNewsSummary4(RichOutputText bindNewsSummary4) {
        this.bindNewsSummary4 = bindNewsSummary4;
    }

    public RichOutputText getBindNewsSummary4() {
        return bindNewsSummary4;
    }

    public void setBindNewsSummary3(RichOutputText bindNewsSummary3) {
        this.bindNewsSummary3 = bindNewsSummary3;
    }

    public RichOutputText getBindNewsSummary3() {
        return bindNewsSummary3;
    }

    public void setBindNewsSummary2(RichOutputText bindNewsSummary2) {
        this.bindNewsSummary2 = bindNewsSummary2;
    }

    public RichOutputText getBindNewsSummary2() {
        return bindNewsSummary2;
    }

    public void setBindNewsSummary1(RichOutputText bindNewsSummary1) {
        this.bindNewsSummary1 = bindNewsSummary1;
    }

    public RichOutputText getBindNewsSummary1() {
        return bindNewsSummary1;
    }

    public void setBindNews1PG(RichPanelGroupLayout bindNews1PG) {
        this.bindNews1PG = bindNews1PG;
    }

    public RichPanelGroupLayout getBindNews1PG() {
        return bindNews1PG;
    }

    public void setNewCount(int newCount) {
        this.newsCount = newCount;
    }

    public int getNewCount() {
        return newsCount;
    }

    public void setBindCategory1(RichButton bindCategory1) {
        this.bindCategory1 = bindCategory1;
    }

    public RichButton getBindCategory1() {
        return bindCategory1;
    }

    public void setBindCategory2(RichButton bindCategory2) {
        this.bindCategory2 = bindCategory2;
    }

    public RichButton getBindCategory2() {
        return bindCategory2;
    }

    public void setBindCategory3(RichButton bindCategory3) {
        this.bindCategory3 = bindCategory3;
    }

    public RichButton getBindCategory3() {
        return bindCategory3;
    }

    public void setBindCategory4(RichButton bindCategory4) {
        this.bindCategory4 = bindCategory4;
    }

    public RichButton getBindCategory4() {
        return bindCategory4;
    }


    public void setBindNextButton(RichButton bindNextButton) {
        this.bindNextButton = bindNextButton;
    }

    public RichButton getBindNextButton() {
        return bindNextButton;
    }

    public void setBindPreviousButton(RichButton bindPreviousButton) {
        this.bindPreviousButton = bindPreviousButton;
    }

    public RichButton getBindPreviousButton() {
        return bindPreviousButton;
    }

    public void setLanguageFlag(String languageFlag) {
        this.languageFlag = languageFlag;
    }

    public String getLanguageFlag() {
        return languageFlag;
    }

    public String updateEnglish() {
        // Add event code here...
        languageFlag = "en";
        bindEnglishLangBtn.setVisible(false);
        bindArabicLangBtn.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEnglishLangBtn);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindArabicLangBtn);
        refreshComponent();
        return null;
    }

    public String updateArabic() {
        // Add event code here...
        languageFlag = "ar";
        bindEnglishLangBtn.setVisible(true);
        bindArabicLangBtn.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEnglishLangBtn);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindArabicLangBtn);
        refreshComponent();
        return null;
    }

    public void setBindEnglishLangBtn(RichButton bindEnglishLangBtn) {
        this.bindEnglishLangBtn = bindEnglishLangBtn;
    }

    public RichButton getBindEnglishLangBtn() {
        return bindEnglishLangBtn;
    }

    public void setBindArabicLangBtn(RichButton bindArabicLangBtn) {
        this.bindArabicLangBtn = bindArabicLangBtn;
    }

    public RichButton getBindArabicLangBtn() {
        return bindArabicLangBtn;
    }
    
    public void refreshComponent(){
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsImage4);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindViewMore1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindViewMore2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindViewMore3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindViewMore4);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsTitle4);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary4);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNewsSummary1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindCategory4);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindNews1PG);
    }

    public void setBindViewMore1(RichButton bindViewMore1) {
        this.bindViewMore1 = bindViewMore1;
    }

    public RichButton getBindViewMore1() {
        return bindViewMore1;
    }

    public void setBindViewMore2(RichButton bindViewMore2) {
        this.bindViewMore2 = bindViewMore2;
    }

    public RichButton getBindViewMore2() {
        return bindViewMore2;
    }

    public void setBindViewMore3(RichButton bindViewMore3) {
        this.bindViewMore3 = bindViewMore3;
    }

    public RichButton getBindViewMore3() {
        return bindViewMore3;
    }

    public void setBindViewMore4(RichButton bindViewMore4) {
        this.bindViewMore4 = bindViewMore4;
    }

    public RichButton getBindViewMore4() {
        return bindViewMore4;
    }
}


