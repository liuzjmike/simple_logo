package view;

import model.info.PaletteInfo;
import model.turtle.info.ShapeInfo;

public interface ViewSupplier {
	
	PaletteInfo getPalette();
	
	ShapeInfo getShapeInfo();

}
