package com.sql.er.swing.util;

import com.sql.er.util.mxRectangle;
import com.sql.er.view.mxCellState;

public interface mxICellOverlay
{

	/**
	 * 
	 */
	mxRectangle getBounds(mxCellState state);

}
