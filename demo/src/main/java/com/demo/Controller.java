package com.demo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * Controller lets the user interact with GUI.
 * @author Muslima Karimova (2130288) & Fahim Samady (2035827)
 * @version 1.0
 */
public class Controller {

    @FXML
    public Slider redSlider;
    @FXML
    public Slider greenSlider;
    @FXML
    public Slider blueSlider;
    @FXML
    public Slider xSlider;
    @FXML
    public Slider ySlider;
    @FXML
    public Slider zSlider;
    @FXML
    public Slider radiusSlider;
    @FXML
    public Slider azimuthSlider;
    @FXML
    public Slider altitudeSlider;
    @FXML
    ComboBox<Object> spheresList;
    @FXML
    ColorPicker colorPicker;

    private Sphere selectedSphere;

    @FXML
    public void initialize() {

        // RGB sliders
        redSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedSphere.getColor().setX(newValue.doubleValue()/255);
            setColorPicker();
            View.render();
        });
        greenSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedSphere.getColor().setY(newValue.doubleValue()/255);
            setColorPicker();
            View.render();
        });
        blueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedSphere.getColor().setZ(newValue.doubleValue()/255);
            setColorPicker();
            View.render();
        });

        // Coordinate Sliders
        xSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedSphere.getCenter().setX(newValue.doubleValue());
            View.render();
        });
        ySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedSphere.getCenter().setY(newValue.doubleValue());
            View.render();

        });
        zSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedSphere.getCenter().setZ(newValue.doubleValue());
            View.render();
        });

        // Radius slider
        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedSphere.setRadius(newValue.doubleValue());
            View.render();
        });

        // Camera sliders
        azimuthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            View.camera.VRP.setX(View.camera.DISTANCE * Math.sin(Math.toRadians(newValue.doubleValue())));
            View.camera.VRP.setZ(View.camera.DISTANCE * Math.cos(Math.toRadians(newValue.doubleValue())));
            View.render();
        });

        altitudeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            View.camera.VRP.setY(newValue.doubleValue());
            View.render();
        });

        setSphereList();
        spheresList.getSelectionModel().selectFirst();
        selectSphere();
    }

    ArrayList<String> spheresLabelList;
    public void setSphereList() {
        spheresLabelList = new ArrayList<>();
        for (int i = 1; i <= View.spheres.size(); i++) {
            spheresLabelList.add("Sphere " + i);
        }
        spheresList.setItems(FXCollections.observableArrayList(spheresLabelList));
    }

    @FXML
    public void selectColorPicker() {
        selectedSphere.getColor().setX(colorPicker.valueProperty().getValue().getRed());
        selectedSphere.getColor().setY(colorPicker.valueProperty().getValue().getGreen());
        selectedSphere.getColor().setZ(colorPicker.valueProperty().getValue().getBlue());

        setColorSliders();
        View.render();
    }

    @FXML
    public void selectSphere() {
        if (spheresList.getSelectionModel().getSelectedIndex() >= 0) {
            selectedSphere = View.spheres.get(spheresList.getSelectionModel().getSelectedIndex());
            setColorSliders();
            setCoordinateSliders();
            setRadiusSlider();
            setColorPicker();
        }
    }

    public void setColorSliders() {
        redSlider.setValue(selectedSphere.getColor().x*255);
        greenSlider.setValue(selectedSphere.getColor().y*255);
        blueSlider.setValue(selectedSphere.getColor().z*255);
    }

    public void setCoordinateSliders() {
        xSlider.setValue(selectedSphere.getCenter().x);
        ySlider.setValue(selectedSphere.getCenter().y);
        zSlider.setValue(selectedSphere.getCenter().z);
    }

    public void setRadiusSlider() {
        radiusSlider.setValue(selectedSphere.getRadius());
    }

    public void setColorPicker() {
        colorPicker.setValue(new Color(selectedSphere.getColor().x,
                selectedSphere.getColor().y,
                selectedSphere.getColor().z,
                1.0));
    }

    @FXML
    public void addNewSphere() {
        View.spheres.add(new Sphere(50, new Vector(-200, -200,0), new Vector(1,1,1)));
        setSphereList();
        spheresList.getSelectionModel().selectLast();
        View.render();
    }

    @FXML
    public void deleteSphere() {
        View.spheres.remove(selectedSphere);
        setSphereList();
        spheresList.getSelectionModel().selectFirst();
        View.render();
    }

    @FXML
    public void save() {
        FileIO.writeSpheres(View.spheres);
    }

}
