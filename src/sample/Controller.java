package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public int WhatChoose;
    @FXML
    ComboBox<String> urlChoose_box;

    @FXML
    ListView<String> listbox;

    @FXML WebView webbox;

    @FXML
    Button reloadbox;

    @FXML
    Button openurlbox;



    @FXML
    public void initialize(){
        urlChoose_box.getItems().addAll("UnderKG NEWS","NaverIT NEWS", "itnews NEWS");
        listbox.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        for(int i = 0;i<16;i++){
//            if(listbox.getSelectionModel().getSelectedItems().equals())
//
//        }

    }


    @FXML
    public void OpenWeb(){
        try {
            int index = listbox.getSelectionModel().getSelectedIndex();
            String tempStr;
            GetUnderKGUrl KG = new GetUnderKGUrl();
            List<String> list = new ArrayList<>();
            WebEngine webEngine = webbox.getEngine();
            if(WhatChoose == 1 && index != -1){
                list.clear();
                list = KG.getUnderKGurldata();
                java.awt.Desktop.getDesktop().browse(new URI(list.get(index)));
            }
            else if(WhatChoose == 2 && index != -1){
                list.clear();
                list = KG.getNaverurldata();
                tempStr = ("http://blog.naver.com").concat(list.get(index));
                java.awt.Desktop.getDesktop().browse(new URI(tempStr));
            }
            else if(WhatChoose ==3 && index != -1){
                list.clear();
                list = KG.getITnewsurldata();
                tempStr = (list.get(index));
                java.awt.Desktop.getDesktop().browse(new URI(tempStr));
            }
            else
                System.out.println("Nothing to Load");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void selectedURLbox(){
        int index = listbox.getSelectionModel().getSelectedIndex();
        String tempStr = null;
        GetUnderKGUrl KG = new GetUnderKGUrl();
        List<String> list = new ArrayList<>();
        WebEngine webEngine = webbox.getEngine();
        System.out.println(index);
        if(WhatChoose == 1){
            list.clear();
            list = KG.getUnderKGurldata();
            webEngine.reload();
            webEngine.load(list.get(index));
            System.out.println(index+"//");
        }
        else if(WhatChoose == 2){
            list.clear();
            list = KG.getNaverurldata();
            webEngine.reload();
            tempStr = ("http://blog.naver.com").concat(list.get(index));
//            System.out.println(tempStr);
            webEngine.load(tempStr);
            System.out.println(index+"///");

        }
        else if(WhatChoose ==3 ){
            list.clear();
            list = KG.getITnewsurldata();
            webEngine.reload();
            tempStr = (list.get(index));
//            System.out.println(tempStr);
            webEngine.load(tempStr);
            System.out.println(index+"////");
        }
        else{
            webEngine.reload();
        }

    }

    public void selected(ActionEvent event){
        GetUnderKG KG = new GetUnderKG();
        List<String> list = new ArrayList<>();
        listbox.getItems().clear();
        if(urlChoose_box.getValue().equals("UnderKG NEWS")){
            listbox.getItems().addAll(KG.getUnderKGdata(list));
            WhatChoose = 1;

        }
        else if(urlChoose_box.getValue().equals("NaverIT NEWS")){
            listbox.getItems().addAll(KG.getNaverdata(list));
            WhatChoose = 2;
        }
        else if(urlChoose_box.getValue().equals("itnews NEWS")){
            listbox.getItems().addAll(KG.getITnewsdata(list));
            WhatChoose = 3;
        }
        else{
            listbox.getItems().addAll();
            WhatChoose = 0;
        }

    }

    @FXML
    public void printcombobox(ActionEvent event){
        System.out.println(urlChoose_box.getValue());
    }
}
