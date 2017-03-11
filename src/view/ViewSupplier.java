package view;

import java.util.List;

import javafx.scene.image.ImageView;
import model.info.PaletteInfo;

public interface ViewSupplier {
	
	PaletteInfo getPalette();
	
	List<ImageView> getShapes();

}
