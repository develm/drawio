package com.sql.er.imports.generatorfunction;

import com.sql.er.imports.view.mxCellState;

/**
 * @author Mate
 * A constant cost function that can be used during graph generation
 * All generated edges will have the weight <b>cost</b> 
 */
public class mxGeneratorConstFunction extends mxGeneratorFunction
{
	private double cost;
	
	public mxGeneratorConstFunction(double cost)
	{
		this.cost = cost;
	};
	
	public double getCost(mxCellState state)
	{
		return cost;
	};
}