package com.sql.er.imports.costfunction;

import com.sql.er.imports.view.mxCellState;

/**
 * @author Mate
 * A constant cost function that returns <b>const</b> regardless of edge value
 */
public class mxConstCostFunction extends mxCostFunction
{
	private double cost;
	
	public mxConstCostFunction(double cost)
	{
		this.cost = cost;
	};
	
	public double getCost(mxCellState state)
	{
		return cost;
	};
}
