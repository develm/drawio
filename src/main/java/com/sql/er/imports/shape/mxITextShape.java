/**
 * Copyright (c) 2010, Gaudenz Alder, David Benson
 */
package com.sql.er.imports.shape;

import java.util.Map;

import com.sql.er.imports.canvas.mxGraphics2DCanvas;
import com.sql.er.imports.view.mxCellState;

public interface mxITextShape
{
	/**
	 * 
	 */
	void paintShape(mxGraphics2DCanvas canvas, String text, mxCellState state,
                    Map<String, Object> style);

}
