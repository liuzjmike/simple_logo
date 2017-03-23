package view;

import model.info.PaletteInfo;
import model.info.ShapeInfo;

public interface ViewSupplier {
	
	PaletteInfo getPalette();
	
	ShapeInfo getShapeInfo();

}
