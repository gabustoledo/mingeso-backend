package com.mingeso.backend.testOperaciones;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
import java.util.concurrent.TimeUnit;

import com.mingeso.backend.models.AdministrativoTest;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
 
public class SeleniumTest{
 
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://104.131.39.116:3000");
		//driver.get("http://localhost:3000");
	}
	
	@Test
	public void login() {
		
		driver.findElement(By.name("btnLoginEncabezado")).click();

		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		driver.findElement(By.id("formBasicEmail")).sendKeys("correo@prueba");
		driver.findElement(By.id("formBasicPassword")).sendKeys("prueba");

		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		driver.findElement(By.name("btnLogin")).click();

		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		String msgBienvenida = driver.findElement(By.name("msgBienvenida")).getText();
		assertEquals("Bienvenido Usuario prueba!!", msgBienvenida);

		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		driver.findElement(By.name("cerrar")).click();
		
		driver.findElement(By.name("btnRegisterEncabezado")).click();

		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		driver.findElement(By.name("nombre")).sendKeys("Nombre prueba");
		driver.findElement(By.name("rut")).sendKeys("12345678-9");
		driver.findElement(By.name("email")).sendKeys("email@prueba");
		driver.findElement(By.name("pass")).sendKeys("prueba");
		driver.findElement(By.id("inline-radio-1")).click();

		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		driver.findElement(By.name("btnRegister")).click();

		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		msgBienvenida = driver.findElement(By.name("msgBienvenida")).getText();
		assertEquals("Te acabas de registrar Nombre prueba!!", msgBienvenida);

		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
	@AfterAll
	public static void tearDown() {driver.quit(); }
 
}