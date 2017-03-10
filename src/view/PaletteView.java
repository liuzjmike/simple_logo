package view;

import javafx.scene.control.ChoiceBox;
import model.info.PaletteInfo;
import util.SLogoObserver;

public class PaletteView implements SLogoObserver<PaletteInfo> {
    
    private ChoiceBox myBox;
    
    public PaletteView() {
        myBox = new ChoiceBox();
    }
    
    public ChoiceBox getChoices() {
        return myBox;
    }

    @Override
    public void update(PaletteInfo arg) {
        
    }

}
