package org.example.musicplayer;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.event.EventHandler;


import java.io.File;
import java.io.IOException;

public class MusicPlayer extends Application {

    Media med = new Media(new File("Rocky.mp3").toURI().toString());
    MediaPlayer player = new MediaPlayer(med);

//    FileChooser f = new FileChooser();
//        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("music player","*mp3"));
    FileChooser f = new FileChooser();
    ListView<String> list = new ListView<>();
    ObservableList<String> items = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws IOException {

        Menu menu = new Menu("My Menu");
        MenuItem mt= new MenuItem("Upload");
            Slider volume = new Slider();
            volume.setValue(player.getVolume()*100);


            volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    player.setVolume(volume.getValue()/100);
                }
            });


        mt.setOnAction(e->{
            FileChooser f = new FileChooser();


            f.getExtensionFilters().add(new FileChooser.ExtensionFilter("music player","*mp3"));
            File t = f.showOpenDialog(stage);
            try{
            items.add(t.toURL().getFile().toString());}catch (Exception x){x.printStackTrace();}
            list.setItems(items);
            System.out.println(t.toURI());
//            player = new MediaPlayer(new Media(t.toURI().toString()));
//            player.play();

        });
        menu.getItems().add(mt);
        MenuBar mb = new MenuBar(menu);
//        list.getSelectionModel().selectionModeProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String selectionMode, String t1) {
//                player = new MediaPlayer(new Media(t1));
//                player.
//            }
//        }
//    });

        Button b = new Button("Play Music");
        b.setOnAction(play);
        Button b2 = new Button("Next Music");
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                player.stop();
            }
        });
        TilePane platform = new TilePane(mb);
        platform.getChildren().addAll(b,b2,volume,list);


        Scene scene = new Scene(platform);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    EventHandler<ActionEvent> play = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            player.play();
        }
    };




    public static void main(String[] args) {
        launch();
    }
}