package Recipe;

public class RecipesVo {
	private String foodName;
	private String pet;
	private String recipeImg;
	private String intro;
	private String howto;
	private String like_recipe;
	
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public RecipesVo(String foodName){
		this.foodName = foodName;
		
	}
	
	public RecipesVo() {
	}
	

	public String getFoodName() {
		return foodName;
	}
	public String getPet() {
		return pet;
	}

	public String getRecipeImg() {
		return recipeImg;
	}

	public void setRecipeImg(String recipeImg) {
		this.recipeImg = recipeImg;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getHowto() {
		return howto;
	}

	public void setHowto(String howto) {
		this.howto = howto;
	}

	public String getLike_recipe() {
		return like_recipe;
	}

	public void setLike_recipe(String like_recipe) {
		this.like_recipe = like_recipe;
	}
	

}
