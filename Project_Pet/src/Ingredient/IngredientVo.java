package Ingredient;

public class IngredientVo {
	private String ingredient;
	private String pet;
	private String effect;
	private String carefulpet;
	private String detail;
	
	public IngredientVo() {}

	public IngredientVo(String ingredient) {
		this.ingredient = ingredient;
	}
	
	public String getIngredient() {
		return ingredient;
	}

	public void setIngredinet(String ingredinet) {
		this.ingredient = ingredinet;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getCarefulpet() {
		return carefulpet;
	}

	public void setCarefulpet(String carefulpet) {
		this.carefulpet = carefulpet;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
