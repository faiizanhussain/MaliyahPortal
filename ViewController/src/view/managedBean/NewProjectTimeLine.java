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
public class NewProjectTimeLine {
    private RichPanelGroupLayout bindNews1PG;
    private RichButton bindNextButton;
    private RichButton bindPreviousButton;
    private RichOutputText header1;
   // private RichOutputText bindText1_1;
   // private RichOutputText bindText1_2;
    private RichOutputText bindText1_3;
    private RichOutputText header2;
   // private RichOutputText bindText2_1;
 //   private RichOutputText bindText2_2;
    private RichOutputText bindText2_3;
    private RichOutputText header3;
   // private RichOutputText bindText3_1;
  //  private RichOutputText bindText3_2;
    private RichOutputText bindText3_3;
    private RichOutputText header4;
   // private RichOutputText bindText4_1;
   // private RichOutputText bindText4_2;
    private RichOutputText bindText4_3;
    private String languageFlag = "ar";
    private int timeLineCount = 0;
    private int recordCount = 0;
    private RichButton bindEnglishLangBtn;
    private RichButton bindArabicLangBtn;

    public NewProjectTimeLine() {
        
    }
    ArrayList<Integer> serialNumList = new ArrayList<>();
    Map<Number, ArrayList<String>> timeLineMap = new HashMap<Number, ArrayList<String>>();
   
   
   
    public String TimelinefromBackend() {
        System.out.println("***="+recordCount);
        serialNumList.clear();
        recordCount = 0;
        timeLineCount = 0;
        timeLineMap.clear();

        BindingContext bCtx = BindingContext.getCurrent();
        DCBindingContainer DcCon = (DCBindingContainer) bCtx.getCurrentBindingsEntry();
        OperationBinding oper = DcCon.getOperationBinding("getTimeLineFromDB");
        timeLineMap = (Map<Number, ArrayList<String>>) oper.execute();
        System.out.println("timeLineMap=="+timeLineMap);
        ArrayList data = null;
        if (!timeLineMap.isEmpty() && timeLineMap != null) {
            serialNumList = getNewsSerialNumbers(timeLineMap);
            System.out.println("serialNumList==" + serialNumList);
            timeLineCount = serialNumList.size() + 1;
            timeLineCount = timeLineCount / 4;
            for (int i = recordCount; i <= recordCount + 3; i++) {
                if (i == recordCount) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo1_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName1_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails1_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate1_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate1_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo1_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName1_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails1_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate1_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate1_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase1_1", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase1_1", data.get(11));
                    
                }
                if (i == recordCount + 1 && serialNumList.size()>=2) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo2_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName2_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails2_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate2_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate2_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo2_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName2_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails2_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate2_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate2_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase2_1", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase2_1", data.get(11));
                }
                if (i == recordCount + 2 && serialNumList.size()>=3) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo3_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName3_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails3_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate3_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate3_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo3_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName3_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails3_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate3_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate3_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase3_1", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase3_1", data.get(11));
                }
                if (i == recordCount + 3 && serialNumList.size()>=4) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo4_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName4_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails4_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate4_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate4_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo4_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName4_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails4_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate4_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate4_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase4_1", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase4_1", data.get(11));
                }
            }
            }
            recordCount += 3;
        
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
        System.out.println("recordCount in next=="+recordCount +"timeLineCount=="+timeLineCount);
        ArrayList data = null;
        timeLineCount = serialNumList.size() + 1;
        timeLineCount = timeLineCount / 4;
        int j = recordCount;

        if(recordCount < serialNumList.size()){
        for (int i = recordCount + 4; i > j; i--) {
            if (timeLineCount >= 0 && serialNumList.size() > i) {
                if (i == recordCount + 1) {

                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo1_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName1_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails1_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate1_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate1_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo1_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName1_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails1_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate1_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate1_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                }
                if (i == recordCount + 2) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo2_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName2_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails2_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate2_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate2_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo2_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName2_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails2_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate2_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate2_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                }

                if (i == recordCount + 3) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo3_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName3_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails3_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate3_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate3_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo3_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName3_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails3_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate3_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate3_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                }
                if (i == recordCount + 4) {
                    String srNO = serialNumList.get(i).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo4_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName4_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails4_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate4_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate4_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo4_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName4_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails4_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate4_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate4_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));

                }
            }else{
                if(serialNumList.size()== 5){
                    String srNO = serialNumList.get(4).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo1_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName1_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails1_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate1_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate1_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo1_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName1_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails1_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate1_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate1_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                } 
                if(serialNumList.size()== 6){
                    String srNO = serialNumList.get(5).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo2_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName2_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails2_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate2_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate2_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo2_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName2_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails2_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate2_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate2_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                } 
                if(serialNumList.size()== 7){
                    String srNO = serialNumList.get(6).toString();
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo3_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName3_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails3_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate3_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate3_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo3_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName3_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails3_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate3_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate3_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                } 
                break;
            }
            timeLineCount--;
//            bindPreviousButton.setDisabled(false);
//            AdfFacesContext.getCurrentInstance().addPartialTarget(bindPreviousButton);
        }
        }else{
            bindNextButton.setDisabled(true);
            bindPreviousButton.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindNextButton);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindPreviousButton);
        }
        if (serialNumList.size() >= recordCount) {
            recordCount = recordCount + 4;
            if(recordCount>serialNumList.size()){
            recordCount = serialNumList.size();
                
            }
        }
        
        if(recordCount>3){
        
            bindPreviousButton.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindPreviousButton);
        }else{
            bindPreviousButton.setDisabled(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindPreviousButton);  
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(header1);
     //   AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_1);
    //    AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_3); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(header2);
     //   AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_1);
     //   AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header3);
     //   AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_1);
     //   AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header4);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_3);
        System.out.println("recordCount in next 1=="+recordCount);
        
    }
    
    

    public void displayPreviousNews(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Inside displayPreviousTimeline");
        ArrayList data = null;
        timeLineCount = serialNumList.size() + 1;
        timeLineCount = timeLineCount / 4;
        System.out.println("recordCount==" + recordCount);
        recordCount = recordCount-4;
        int j = recordCount;

        if(recordCount>=3){
            
        for (int i = recordCount - 4; i < j; i++) {
            
            if (timeLineCount >= 0 && serialNumList.size() > i && recordCount > 0) {
                if (i == recordCount - 1) {

                    String srNO =null;
                    if(i==3){
                         srNO = serialNumList.get(0).toString(); 
                    }else{
                     srNO = serialNumList.get(i-1).toString();
                    }
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo1_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName1_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails1_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate1_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate1_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo1_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName1_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails1_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate1_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate1_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                    //recordCount = recordCount - 4;
                }
                if (i == recordCount - 2) {
                    String srNO =null;
                    if(i==2){
                         srNO = serialNumList.get(1).toString(); 
                    }else{
                     srNO = serialNumList.get(i-1).toString();
                    }
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO)) {
                            data = (ArrayList) entry.getValue();
                        }
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo2_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName2_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails2_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate2_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate2_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo2_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName2_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails2_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate2_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate2_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                   // recordCount = recordCount - 1;
                }

                if (i == recordCount - 3) {
                    String srNO =null;
                    if(i==1){
                         srNO = serialNumList.get(2).toString(); 
                    }else{
                     srNO = serialNumList.get(i-1).toString();
                    }
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo3_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName3_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails3_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate3_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate3_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo3_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName3_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails3_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate3_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate3_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                }
                if (i == recordCount - 4) {
                    String srNO =null;
                    if(i==0){
                         srNO = serialNumList.get(3).toString(); 
                    }else{
                     srNO = serialNumList.get(i-1).toString();
                    }
                    System.out.println("srNO==" + srNO);
                    for (Map.Entry entry : timeLineMap.entrySet()) {
                        String key = (String) entry.getKey().toString();
                        if (key.equalsIgnoreCase(srNO))
                            data = (ArrayList) entry.getValue();
                    }
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseNo4_1", data.get(0));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseName4_1", data.get(1));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicPhaseDetails4_1", data.get(2));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicStartDate4_1", data.get(3));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicEndDate4_1", data.get(4));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseNo4_1", data.get(5));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseName4_1", data.get(6));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishPhaseDetails4_1", data.get(7));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishStartDate4_1", data.get(8));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishEndDate4_1", data.get(9));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("arabicCurrentPhase", data.get(10));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put("englishCurrentPhase", data.get(11));
                   // recordCount = recordCount - 1;
                }
            }
            timeLineCount--;
            bindNextButton.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(bindNextButton);

        }
       
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(header1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_3); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(header2);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header3);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header4);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_1);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_3);
    }
  

    public void setBindNews1PG(RichPanelGroupLayout bindNews1PG) {
        this.bindNews1PG = bindNews1PG;
    }

    public RichPanelGroupLayout getBindNews1PG() {
        return bindNews1PG;
    }


    public void setTimeLineCount(int timeLineCount) {
        this.timeLineCount = timeLineCount;
    }

    public int getTimeLineCount() {
        return timeLineCount;
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

    public void setHeader1(RichOutputText header1) {
        this.header1 = header1;
    }

    public RichOutputText getHeader1() {
        return header1;
    }

//    public void setBindText1_1(RichOutputText bindText1_1) {
//        this.bindText1_1 = bindText1_1;
//    }
//
//    public RichOutputText getBindText1_1() {
//        return bindText1_1;
//    }
//
//    public void setBindText1_2(RichOutputText bindText1_2) {
//        this.bindText1_2 = bindText1_2;
//    }
//
//    public RichOutputText getBindText1_2() {
//        return bindText1_2;
//    }

    public void setBindText1_3(RichOutputText bindText1_3) {
        this.bindText1_3 = bindText1_3;
    }

    public RichOutputText getBindText1_3() {
        return bindText1_3;
    }

    public void setHeader2(RichOutputText header2) {
        this.header2 = header2;
    }

    public RichOutputText getHeader2() {
        return header2;
    }

//    public void setBindText2_1(RichOutputText bindText2_1) {
//        this.bindText2_1 = bindText2_1;
//    }
//
//    public RichOutputText getBindText2_1() {
//        return bindText2_1;
//    }
//
//    public void setBindText2_2(RichOutputText bindText2_2) {
//        this.bindText2_2 = bindText2_2;
//    }
//
//    public RichOutputText getBindText2_2() {
//        return bindText2_2;
//    }

    public void setBindText2_3(RichOutputText bindText2_3) {
        this.bindText2_3 = bindText2_3;
    }

    public RichOutputText getBindText2_3() {
        return bindText2_3;
    }

    public void setHeader3(RichOutputText header3) {
        this.header3 = header3;
    }

    public RichOutputText getHeader3() {
        return header3;
    }

//    public void setBindText3_1(RichOutputText bindText3_1) {
//        this.bindText3_1 = bindText3_1;
//    }
//
//    public RichOutputText getBindText3_1() {
//        return bindText3_1;
//    }
//
//    public void setBindText3_2(RichOutputText bindText3_2) {
//        this.bindText3_2 = bindText3_2;
//    }
//
//    public RichOutputText getBindText3_2() {
//        return bindText3_2;
//    }

    public void setBindText3_3(RichOutputText bindText3_3) {
        this.bindText3_3 = bindText3_3;
    }

    public RichOutputText getBindText3_3() {
        return bindText3_3;
    }

    public void setHeader4(RichOutputText header4) {
        this.header4 = header4;
    }

    public RichOutputText getHeader4() {
        return header4;
    }

//    public void setBindText4_1(RichOutputText bindText4_1) {
//        this.bindText4_1 = bindText4_1;
//    }
//
//    public RichOutputText getBindText4_1() {
//        return bindText4_1;
//    }
//
//    public void setBindText4_2(RichOutputText bindText4_2) {
//        this.bindText4_2 = bindText4_2;
//    }
//
//    public RichOutputText getBindText4_2() {
//        return bindText4_2;
//    }

    public void setBindText4_3(RichOutputText bindText4_3) {
        this.bindText4_3 = bindText4_3;
    }

    public RichOutputText getBindText4_3() {
        return bindText4_3;
    }

    public void setLanguageFlag(String languageFlag) {
        this.languageFlag = languageFlag;
    }

    public String getLanguageFlag() {
        return languageFlag;
    }

    public void setSerialNumList(ArrayList<Integer> serialNumList) {
        this.serialNumList = serialNumList;
    }

    public ArrayList<Integer> getSerialNumList() {
        return serialNumList;
    }

    public void setTimeLineMap(Map<Number, ArrayList<String>> timeLineMap) {
        this.timeLineMap = timeLineMap;
    }

    public Map<Number, ArrayList<String>> getTimeLineMap() {
        return timeLineMap;
    }

    public String updateEnglish() {
        // Add event code here...
        setLanguageFlag("en");
        bindEnglishLangBtn.setVisible(false);
        bindArabicLangBtn.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_3); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(header2);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_1);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header3);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_1);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header4);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_1);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindArabicLangBtn);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEnglishLangBtn);
        return null;
    }

    public String updateArabic() {
        // Add event code here...
        setLanguageFlag("ar");
        bindEnglishLangBtn.setVisible(true);
        bindArabicLangBtn.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_1);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText1_3); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(header2);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_1);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText2_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header3);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText3_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(header4);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_1);
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindText4_3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindEnglishLangBtn);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindArabicLangBtn);
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
}


