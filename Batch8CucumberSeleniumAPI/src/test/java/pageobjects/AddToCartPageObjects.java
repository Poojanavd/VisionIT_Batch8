package pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.Scenario;
import utils.ui.Interact;

public class AddToCartPageObjects extends Interact{

	private static final Logger logger = LogManager.getLogger(AddToCartPageObjects.class);
	Scenario scn;
	
	private By add_to_cart_btn = By.xpath("//input[@id='add-to-cart-button']");
	private By cart_btn = By.xpath("//a[@class='nav-a nav-a-2']");  
	private By delete_btn = By.xpath("//span[@data-action='delete']//span[@class='a-declarative']");
	private By subtotal_1 = By.xpath("//span[@id='sc-subtotal-label-activecart' and contains (text(),'Subtotal (1 item):')]");
	private By cart_empty_msg =By.xpath("//div[@class='a-row sc-your-amazon-cart-is-empty']//h2[contains(text(),'Your Amazon Basket is empty')]");
	
	public AddToCartPageObjects(WebDriver driver, Scenario s) {
		setDriver(driver);
		this.scn = s;
	}
	
	public void ClickOnAddToCartBtn() {
		clickElement(add_to_cart_btn);
		scn.write("Clicked on Add To Cart Button");
		logger.info("Clicked on Add To Cart Button");	
	}
	
	public void ClickOnCartBtn() {
		clickElement(cart_btn);
		scn.write("Clicked on Cart Button present in Header section");
		logger.info("Clicked on Cart Button present in Header section");	
	}
	
	public void DeleteProductLink(int productIndex) {
		List<WebElement> list = getListOfWebElements(delete_btn);
		clickElement(list.get(productIndex));
		scn.write("Deleted Product with index " + productIndex + " Product Link");
		
	}
	
	public void ClickOnDeleteBtn() {
		clickElement(delete_btn);
		scn.write("Clicked on Delete Button");
		logger.info("Clicked on Delete Button");
	}
	
	public void ValidateSubTotalAfterDeletion() {
		String expected="Subtotal (1 item):";
		String actual=getText(subtotal_1);
		Assert.assertEquals(expected, actual);
		scn.write("Subtotal Displayed as: "+ "(1 item)");
	}
	
	public void ValidateCartIsEmpty(String msg) {
		String actual=getText(cart_empty_msg);
		Assert.assertEquals(msg, actual);
		scn.write("After the product is deleted message displayed as: "+"Your Amazon cart is empty");
	}
	
}