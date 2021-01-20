package com.sql.er.imports.shape;

import com.sql.er.imports.canvas.mxGraphics2DCanvas;
import com.sql.er.imports.util.mxPoint;
import com.sql.er.imports.view.mxCellState;

public interface mxIMarker
{
	/**
	 * 
	 */
	mxPoint paintMarker(mxGraphics2DCanvas canvas, mxCellState state, String type,
                        mxPoint pe, double nx, double ny, double size, boolean source);

}
