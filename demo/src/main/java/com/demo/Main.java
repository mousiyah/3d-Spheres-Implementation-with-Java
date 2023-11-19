package com.demo;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Main class is to set up the scene.
 * @author Muslima Karimova (2130288) & Fahim Samady (2035827)
 * @version 1.0
 */
public class Main extends Application {

  public final int SCENE_WIDTH = 1300;
  public final int SCENE_HEIGHT = 650;
  public static View view;

  @Override
  public void start(Stage stage) {
    stage.setTitle("Ray Tracing");

    view = new View(SCENE_WIDTH/2, SCENE_HEIGHT);

    BorderPane menubar = new BorderPane();
    try {
      menubar.setCenter(FXMLLoader.load(
              Objects.requireNonNull(getClass().getResource("menubar.fxml"))));
    } catch (IOException e) {
      e.printStackTrace();
    }

    GridPane root = new GridPane();
    root.add(view.getImageView(), 0, 0);
    root.add(menubar, 1, 0);

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
  }


  public static void main(String[] args) {
    launch();
  }

}