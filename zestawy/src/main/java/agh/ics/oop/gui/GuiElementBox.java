package agh.ics.oop.gui;

import agh.ics.oop.model.mapobjects.WorldMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.util.Objects;

public class GuiElementBox {
    private final VBox vBox;

    public GuiElementBox(WorldMapElement worldMapElement) throws ImageNotFoundException {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(worldMapElement.getViewName())));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            Label label = new Label(worldMapElement.getPosition().toString());
            this.vBox = new VBox(imageView, label);
            vBox.setAlignment(Pos.CENTER);
        } catch (NullPointerException e) {
            throw new ImageNotFoundException("Image not found in resources!", e);
        }
    }

    public VBox getvBox() {
        return vBox;
    }
}
