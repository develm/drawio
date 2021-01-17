package com.sql.er.shape;

import com.sql.er.canvas.mxGraphics2DCanvas;
import com.sql.er.util.mxPoint;
import com.sql.er.view.mxCellState;

public interface mxIMarker
{
	/**
	 * 
	 */
	mxPoint paintMarker(mxGraphics2DCanvas canvas, mxCellState state, String type,
                        mxPoint pe, double nx, double ny, double size, boolean source);

}
