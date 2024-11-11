package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private Double x;
	private Double y;
	private Double z;
	private Double result;

	@Inject
	FacesContext ctx;
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
	
	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	public Double getResult() {
		return result;
	}

	public String calc() {
		try {
			

			double mies = y * 12;
			double proc = (z / 100) / 12;
			result = (x * proc * Math.pow((1 + proc), mies))/(Math.pow((1 + proc), mies) - 1);
			
			result = (double) (Math.round(result * 100)/ 100.0);
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return "showresult"; 
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return null; 
		}
				
	}

	public String info() {
		return "info"; 
	}
}
