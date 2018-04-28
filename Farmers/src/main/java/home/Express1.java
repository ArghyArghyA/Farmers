package home;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import repository.EAgent.ADE;
import repository.Home;
import repository.Home.Express1.Coverage;
import repository.Home.Express1.DwellingInformation;
import repository.Home.Express1.ECMS;
import repository.Home.Express1.HouseholdInformation;
import repository.Home.Express1.ProtectionClass;
import repository.Home.Express1.Three60Value;
import repository.UnknownException;
import utilities.Utilities;

public class Express1 extends Utilities
{
	Home h = new Home();

	public Express1() throws FileNotFoundException, IOException
	{
		super();
	}

	public boolean goToECMS()
	{
		ADE dom = h.ADE;
		try
		{
			if (increaseCounter("goToECMS") == 3)
			{
				throw new UnknownException("Tried twice to open ECMS but failled");
			}
			System.out.println("openning ecms " + getCounter("goToECMS"));
			if (debugging)
				System.out.println(driver.findElement(By.className("button_indicator")).getAttribute("class"));
			wait.until(ExpectedConditions.elementToBeClickable(dom.StartQuoting));
			driver.findElement(dom.StartQuoting).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(dom.Home));
			driver.findElement(dom.Home).click();
			Thread.sleep(2000);
			switchToTab("Quote");
			System.out.println(driver.getTitle());

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("goToECMS") < 3)
			{
				return goToECMS();
			}
			else if (!debugging)
				driver.quit();
			return false;
		}
		System.out.println("ecms openned");
		return true;

	}

	public boolean fillECMS(HashMap<String, String> ECMS)
	{

		// waitForPageToLoad();
		ECMS dom = h.Express1.ECMS;
		try
		{
			WebElement ele;
			if (increaseCounter("fillECMS") == 3)
			{
				throw new UnknownException("Tried twice to Fill ECMS but failed");
			}
			System.out.println("filling ECMS data " + getCounter("fillECMS"));
			// check the title of current tab.
			// If it is "Account Summary"
			// then switch to "Express Home" tab
			if (driver.getTitle().contains("Account Summary"))
			{
				switchToTab("Express Home");
				return true;
			}

			// fill effective date
			// EffectiveDate must in MM/DD/YYYY fromat
			if (ECMS.containsKey("EffectiveDate"))
			{
				
				try
				{
					String[] date = ECMS.get("EffectiveDate").split("/");
					ele = wait.until(ExpectedConditions.elementToBeClickable(dom.EffectiveDateDD));
					ele.clear();
					ele.sendKeys(date[1]);
					
					ele = wait.until(ExpectedConditions.elementToBeClickable(dom.EffectiveDateMM));
					ele.clear();
					ele.sendKeys(date[0]);
					
					ele = wait.until(ExpectedConditions.elementToBeClickable(dom.EffectiveDateYYYY));
					ele.clear();
					ele.sendKeys(date[2]);
				} catch (IndexOutOfBoundsException e)
				{
					throw new InvalidArgumentException("EffectiveDate should be in 'MM/DD/YYYY' format");
				}
			}

			// fill first name
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.FirstName));
			if (ECMS.containsKey("FirstName"))
			{
				ele.clear();
				ele.sendKeys(ECMS.get("FirstName"));
			} else
			{
				ele.clear();
				ele.sendKeys("Test");
			}
			// fill middle name
			if (ECMS.containsKey("MiddleName"))
			{
				ele = wait.until(ExpectedConditions.elementToBeClickable(dom.MiddleName));
				ele.sendKeys(ECMS.get("MiddleName"));
			}
			// fill last name
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.LastName));
			if (ECMS.containsKey("LastName"))
			{
				ele.clear();
				ele.sendKeys(ECMS.get("LastName"));
			} else
			{
				ele.clear();
				ele.sendKeys("test");
			}
			// fill street name
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.Street));
			ele.clear();
			if (ECMS.containsKey("Street"))
			{
				ele.sendKeys(ECMS.get("Street"));
			} else
			{
				ele.sendKeys("11 Green RD");
			}
			// fill city
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.City));
			ele.clear();
			if (ECMS.containsKey("City"))
			{
				
				ele.sendKeys(ECMS.get("City"));
			} else
			{
				ele.sendKeys("Green");
			}
			// fill state, should be selected by default
			if (ECMS.containsKey("State"))
			{
				ele = wait.until(ExpectedConditions.elementToBeClickable(dom.State));
				select = new Select(ele);
				select.selectByVisibleText("State");
			}
			// fill ZIP
			// Must be filled

			if (ECMS.containsKey("ZIP"))
			{
				ele = wait.until(ExpectedConditions.elementToBeClickable(dom.ZIP));
				ele.clear();
				ele.sendKeys(ECMS.get("ZIP"));
			} else
			{
				throw new InvalidArgumentException("ZIP must be provided");
			}
			// fill date of birth
			// date should be in format MM/DD/YYYY
			// fill only if the field is not link
			if (driver.findElements(dom.DOBLink).size() == 0)
			{
				if (ECMS.containsKey("BirthDate"))
				{
					try
					{
						String[] date = ECMS.get("BirthDate").split("/");
						
						ele = wait.until(ExpectedConditions.elementToBeClickable(dom.DD));
						ele.clear();
						ele.sendKeys(date[1]);
						
						ele = wait.until(ExpectedConditions.elementToBeClickable(dom.MM));
						ele.clear();
						ele.sendKeys(date[0]);
						
						ele = wait.until(ExpectedConditions.elementToBeClickable(dom.YYYY));
						ele.clear();
						ele.sendKeys(date[2]);
						
					} catch (IndexOutOfBoundsException e)
					{
						throw new InvalidArgumentException("BirthDate should be in 'MM/DD/YYYY' format");
					}
				} else
				{
					ele = wait.until(ExpectedConditions.elementToBeClickable(dom.DD));
					ele.clear();
					ele.sendKeys("10");
					
					ele = wait.until(ExpectedConditions.elementToBeClickable(dom.MM));
					ele.clear();
					ele.sendKeys("10");
					
					ele = wait.until(ExpectedConditions.elementToBeClickable(dom.YYYY));
					ele.clear();
					ele.sendKeys("1975");
				}
			}
			// fill marital status
			// select = new Select(driver.findElement(By.name("martialStatus")));
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.MarritalStatus));
			select = new Select(ele);
			if (ECMS.containsKey("MarritalStatus"))
			{
				// select.selectByVisibleText(ECMS.get("MarritalStatus"));
				int counter = 0;

				while (!select.getFirstSelectedOption().getText().equalsIgnoreCase(ECMS.get("MarritalStatus")))
				{
					js.executeScript("document.getElementsByName('martialStatus')[0].selectedIndex = " + counter + ";");
					counter += 1;
					if (counter > select.getOptions().size())
						throw new InvalidArgumentException("Invalid MarritalStatus");
				}

			} else
			{
				js.executeScript("document.getElementsByName('martialStatus')[0].selectedIndex = 4;"); // single = 4
			}
			// fill source category
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.SourceCategory));
			select = new Select(ele);
			if (ECMS.containsKey("SourceCategory"))
			{
				// select.selectByVisibleText(ECMS.get("SourceCategory"));
				int counter = 0;

				while (!select.getFirstSelectedOption().getText().equalsIgnoreCase(ECMS.get("SourceCategory")))
				{
					js.executeScript("document.getElementsByName('leadSourceCategory')[0].selectedIndex = " + counter
							+ ";");
					counter += 1;
					if (counter > select.getOptions().size())
						throw new InvalidArgumentException("Invalid SourceCategory");
				}
			} else
			{
				// select.selectByIndex(0);
				js.executeScript("document.getElementsByName('leadSourceCategory')[0].selectedIndex = 1;");

			}
			// fill lead source
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.LeadSource));
			select = new Select(ele);
			if (ECMS.containsKey("LeadSource"))
			{
				// select.selectByVisibleText(ECMS.get("LeadSource"));
				int counter = 0;
				while (!select.getFirstSelectedOption().getText().equalsIgnoreCase(ECMS.get("LeadSource")))
				{
					js.executeScript("document.getElementsByName('leadSource')[0].selectedIndex = " + counter + ";");
					counter += 1;
					if (counter > select.getOptions().size())
						throw new InvalidArgumentException("Invalid LeadSource");
				}
			} else
			{
				// select.selectByIndex(0);
				js.executeScript("document.getElementById('leadSource').selectedIndex = 3;");

			}
			// fill phone
			// should be 10 digit numeric
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.Phone));
			ele.clear();
			if (ECMS.containsKey("Phone"))
			{
				ele.sendKeys(ECMS.get("Phone"));
			} else
			{
				ele.sendKeys("2222222222");
			}

			// fill email id
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.eMail));
			ele.clear();
			if (ECMS.containsKey("eMail"))
			{
				ele.sendKeys(ECMS.get("email"));
			} else
			{
				int id = (int) (Math.random() * 1000);
				ele.sendKeys("testesign." + id + "@yopmail.com");
			}

			// fill ssn
			// fill only if SSN field is not link
			if (driver.findElements(dom.SSNLink).size() == 0)
			{
				ele = wait.until(ExpectedConditions.elementToBeClickable(dom.SSN));
				ele.clear();
				if (ECMS.containsKey("SSN"))
				{
					ele.sendKeys(ECMS.get("SSN"));
				} else
				{
					int ssn = 666666666 + (int) (Math.random() * 100000000);
					ele.sendKeys(Integer.toString(ssn));
				}
			}
			// fill license
			if (ECMS.containsKey("License"))
			{
				ele = wait.until(ExpectedConditions.elementToBeClickable(dom.License));
				ele.clear();
				ele.sendKeys(ECMS.get("License"));
			}
			// fill license state
			if (ECMS.containsKey("LicenseState"))
			{
				ele = wait.until(ExpectedConditions.elementToBeClickable(dom.LicenseState));
				select = new Select(ele);
				select.selectByVisibleText(ECMS.get("LicenseState"));
			}
			// fill gender
			wait.until(ExpectedConditions.elementToBeClickable(dom.Male));
			wait.until(ExpectedConditions.elementToBeClickable(dom.Female));
			if (ECMS.containsKey("Gender"))
			{
				if (ECMS.get("Gender").equalsIgnoreCase("Male"))
				{
					driver.findElement(dom.Male).click();
				} else
				{
					driver.findElement(dom.Female).click();
				}
			} else
			{
				driver.findElement(dom.Male).click();
			}
			// fill occupation group
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.OccupationGroup));
			select = new Select(ele);
			if (ECMS.containsKey("OccupationGroup"))
			{
				select.selectByVisibleText(ECMS.get("OccupationGroup"));
			} else
			{
				// select.selectByVisibleText("All");
				js.executeScript("document.getElementsByName('occupationGroup')[0].selectedIndex = 0;");
				// js.executeScript("document.getElementsByName('occupationGroup')[0].selectedIndex
				// = 2;");
				// js.executeScript("document.getElementsByName('occupationGroup')[0].selectedIndex
				// = 3;");
				// js.executeScript("document.getElementsByName('occupationGroup')[0].selectedIndex
				// = 4;");
			}
			Thread.sleep(500);
			// fill occupation DOES NOT WORK since Occupation is not populated.
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.Occupation));
			select = new Select(ele);
			if (ECMS.containsKey("Occupation"))
			{
				select.selectByVisibleText(ECMS.get("Occupation"));
			} else
			{
				// select.selectByIndex(1);
				js.executeScript("document.getElementsByName('occupation')[0].selectedIndex = 2;");
			}

			// fill product type
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.PolicyType));
			select = new Select(ele);
			if (ECMS.containsKey("PolicyType"))
			{
				select.selectByVisibleText(ECMS.get("PolicyType"));
			} else
			{
				// select.selectByVisibleText("Homeowners");
				js.executeScript("document.getElementsByName('policyTypeHome')[0].selectedIndex = 1;");
			}
			// click 'continue quote'
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.ContinueQuote));
			ele.click();
			wait.until(ExpectedConditions.numberOfWindowsToBe(3));
			wait.until(ExpectedConditions.titleContains("Account Summary"));
			// waitForPageToLoad();
			closeAndSwitchToTab("Express Home");
			waitForPageToLoad();
			System.out.println(driver.getTitle());

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("fillECMS") < 3)
			{
				return fillECMS(ECMS);
			}
			else if (!debugging)
				driver.quit();
			return false;
		}
		System.out.println("ecms filled");
		return true;

	}

	public boolean fillHouseholdInformation(HashMap<String, String> HI)
	{

		HouseholdInformation dom = h.Express1.HouseholdInformation;

		System.out.println("filling Household Information");

		try
		{
			alert = getAlert();
			alert.dismiss();
		} catch (NullPointerException e)
		{
			// TODO: handle exception
			// do nothing
		}
		try
		{
			if (increaseCounter("fillHouseholdInformation") == 3)
			{
				throw new UnknownException("Tried twice to Fill Household information but failed");
			}
			// fill effective date
			// Effective Date must be in MM/DD/YYYY
			wait.until(ExpectedConditions.elementToBeClickable(dom.EffectiveDateDD));
			if (HI.containsKey("EffectiveDate"))
			{

				try
				{
					String[] date = HI.get("EffectiveDate").split("/");
					driver.findElement(dom.EffectiveDateMM).clear();
					driver.findElement(dom.EffectiveDateMM).sendKeys(date[0]);
					driver.findElement(dom.EffectiveDateDD).clear();
					driver.findElement(dom.EffectiveDateDD).sendKeys(date[1]);
					driver.findElement(dom.EffectiveDateYYYY).clear();
					driver.findElement(dom.EffectiveDateYYYY).sendKeys(date[2]);
				} catch (IndexOutOfBoundsException e)
				{
					throw new InvalidArgumentException("EffectiveDate should be in 'MM/DD/YYYY' format");
				}
			}
			// Put Current Year in Global Map
			Global.put("CurrentYear", driver.findElement(dom.EffectiveDateYYYY).getAttribute("value"));

			// select policy type
			// policy type can be SPHO format
			wait.until(ExpectedConditions.elementToBeClickable(dom.PolicyType));
			WebElement policyType = driver.findElement(dom.PolicyType);
			if (HI.containsKey("PolicyType"))
			{
				policyType.clear();
				policyType.sendKeys(HI.get("PolicyType"));
				policyType.sendKeys(Keys.TAB);
				waitForPageToLoad();
			}
			// Set Policy type in Global Map
			Global.put("PolicyType", driver.findElement(dom.PolicyType).getAttribute("value"));

			System.out.println(driver.findElement(dom.PolicyType).getAttribute("value"));

			// fill phone details
			// should be in xxx-xxx-xxxx format
			if (HI.containsKey("Phone"))
			{
				try
				{
					String[] phone = HI.get("Phone").split("-");
					wait.until(ExpectedConditions.elementToBeClickable(dom.PhoneArea));
					wait.until(ExpectedConditions.elementToBeClickable(dom.PhoneMiddle));
					wait.until(ExpectedConditions.elementToBeClickable(dom.PhoneLast));

					driver.findElement(dom.PhoneArea).sendKeys(phone[0]);
					driver.findElement(dom.PhoneMiddle).sendKeys(phone[1]);
					driver.findElement(dom.PhoneLast).sendKeys(phone[2]);
				} catch (IndexOutOfBoundsException e)
				{
					throw new InvalidArgumentException("Phone should be in XXX-XXX-XXXX format");
				}

			}

			// fill email details
			if (HI.containsKey("eMail"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.eMail));
				driver.findElement(dom.eMail).sendKeys(HI.get("eMail"));
			}

			// prior carrier
			wait.until(ExpectedConditions.elementToBeClickable(dom.PriorCarrier));
			if (HI.containsKey("PriorCarrier"))
			{
				driver.findElement(dom.PriorCarrier).sendKeys(HI.get("PriorCarrier"));
			} else
			{
				driver.findElement(dom.PriorCarrier).clear();
				driver.findElement(dom.PriorCarrier).sendKeys("FARMERS INS GRP");
			}
			// Occupation group
			if (HI.containsKey("OccupationGroup"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.OccupationGroup));
				driver.findElement(dom.OccupationGroup).clear();
				driver.findElement(dom.OccupationGroup).sendKeys(HI.get("OccupationGroup"));
			}
			// Occupation
			if (HI.containsKey("Occupation"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.Occupation));
				driver.findElement(dom.Occupation).clear();
				driver.findElement(dom.Occupation).sendKeys(HI.get("Occupation"));
			}

			// Related Household
			if (HI.containsKey("RelatedHousehold"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.RelatedHousehold));
				driver.findElement(dom.RelatedHousehold).clear();
				driver.findElement(dom.RelatedHousehold).sendKeys(HI.get("RelatedHousehold"));
			}

			// Number of Adults
			if (HI.containsKey("NumberOfAdults"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.NumberOfAdults));
				Select numberOfAdults = new Select(driver.findElement(dom.NumberOfAdults));
				numberOfAdults.selectByVisibleText(HI.get("NumberOfAdults"));
			}

			// campaign source
			if (HI.containsKey("CampaignSource"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.CampaignSource));
				driver.findElement(dom.CampaignSource).clear();
				driver.findElement(dom.CampaignSource).sendKeys(HI.get("CampaignSource"));
			}

			// Auto discount
			if (HI.containsKey("Auto"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountAuto));
				if (!driver.findElement(dom.DiscountAuto).isSelected())
				{
					driver.findElement(dom.DiscountAuto).click();
				}
			} else
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountAuto));
				if (driver.findElement(dom.DiscountAuto).isSelected())
				{
					driver.findElement(dom.DiscountAuto).click();
				}
			}

			// CEA discount
			if (HI.containsKey("CEA"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountCEA));
				if (!driver.findElement(dom.DiscountCEA).isSelected())
				{
					driver.findElement(dom.DiscountCEA).click();
				}
			} else
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountCEA));
				if (driver.findElement(dom.DiscountCEA).isSelected())
				{
					driver.findElement(dom.DiscountCEA).click();
				}
			}

			// Umbrella discount
			if (HI.containsKey("Umbrella"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountUmbrella));
				if (!driver.findElement(dom.DiscountUmbrella).isSelected())
				{
					driver.findElement(dom.DiscountUmbrella).click();
				}
			} else
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountUmbrella));
				if (driver.findElement(dom.DiscountUmbrella).isSelected())
				{
					driver.findElement(dom.DiscountUmbrella).click();
				}
			}

			// life discount
			if (HI.containsKey("Life"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountLife));
				if (!driver.findElement(dom.DiscountLife).isSelected())
				{
					driver.findElement(dom.DiscountLife).click();
				}
			} else
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountLife));
				if (driver.findElement(dom.DiscountLife).isSelected())
				{
					driver.findElement(dom.DiscountLife).click();
				}
			}

			// Business discount
			if (HI.containsKey("Business"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountBusiness));
				if (!driver.findElement(dom.DiscountBusiness).isSelected())
				{
					driver.findElement(dom.DiscountBusiness).click();
				}
			} else
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountBusiness));
				if (driver.findElement(dom.DiscountBusiness).isSelected())
				{
					driver.findElement(dom.DiscountBusiness).click();
				}
			}

			// Business discount
			if (HI.containsKey("NonSmoker"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountNonSmoker));
				if (!driver.findElement(dom.DiscountNonSmoker).isSelected())
				{
					driver.findElement(dom.DiscountNonSmoker).click();
				}
			} else
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.DiscountNonSmoker));
				if (driver.findElement(dom.DiscountNonSmoker).isSelected())
				{
					driver.findElement(dom.DiscountNonSmoker).click();
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("fillHouseholdInformation") < 3)
			{
				return fillHouseholdInformation(HI);
			}
			else if (!debugging)
				driver.quit();
			return false;
		}
		System.out.println("Household Information filled");
		return true;

	}

	public boolean fillProtectionClass(HashMap<String, String> PC)
	{

		ProtectionClass dom = h.Express1.ProtectionClass;
		try
		{
			if (increaseCounter("fillProtectionClass") == 3)
			{
				throw new UnknownError("Tried twice to Fill Protection Class information but failed");
			}
			System.out.println("filling protection class" + getCounter("fillProtectionClass"));
			// fill Hydrant
			wait.until(ExpectedConditions.numberOfElementsToBe(dom.HydrantWithin1000Feet, 2));
			List<WebElement> radioButtions = driver.findElements(dom.HydrantWithin1000Feet);
			if (radioButtions.get(0).isEnabled())
			{
				if (PC.containsKey("Hydrant"))
				{
					radioButtions.get(0).click();
				} else
				{
					radioButtions.get(1).click();
				}

			}

			// fill uncleared brush
			wait.until(ExpectedConditions.numberOfElementsToBe(dom.UnclearedBrushWithin100Feet, 2));
			radioButtions.clear();
			radioButtions = driver.findElements(dom.UnclearedBrushWithin100Feet);
			if (radioButtions.get(0).isEnabled())
			{
				if (PC.containsKey("UnclearedBrush"))
				{
					radioButtions.get(0).click();
				} else
				{
					radioButtions.get(1).click();
				}
			}

			// fill designated brush
			wait.until(ExpectedConditions.numberOfElementsToBe(dom.DesignatedBrushWithin150Feet, 2));
			radioButtions.clear();
			radioButtions = driver.findElements(dom.DesignatedBrushWithin150Feet);
			if (radioButtions.get(0).isEnabled())
			{
				if (PC.containsKey("DesignatedBrush"))
				{
					radioButtions.get(0).click();
				} else
				{
					radioButtions.get(1).click();
				}
			}

			// fill teritory
			if (PC.containsKey("Territory"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.TerritoryText));
				driver.findElement(dom.TerritoryText).sendKeys(PC.get("Territory"));
			} else
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.TerritoryDropdown));
				driver.findElement(dom.TerritoryDropdown).click();
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dom.TerritoryValues));
				driver.findElement(dom.TerritoryValues).click();
			}

			// fill fire district name
			wait.until(ExpectedConditions.elementToBeClickable(dom.FireDistrictName));
			select = new Select(driver.findElement(dom.FireDistrictName));
			if (PC.containsKey("FireDistrictName"))
			{
				select.selectByVisibleText(PC.get("FireDistrictName"));
			} else
			{
				select.selectByIndex(1);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("fillProtectionClass") < 3)
				return fillProtectionClass(PC);
			else if (!debugging)
				driver.quit();
			return false;
		}
		System.out.println("protection class filled");
		return true;

	}

	public boolean fill360Value(HashMap<String, String> TV)
	{

		try
		{
			if (increaseCounter("fill360Value") == 3)
			{
				throw new UnknownError("Tried twice to Fill 360 Value information but failed");
			}
			System.out.println("filling 360 Value" + getCounter("fill360Value"));
			Three60Value D = h.Express1.Three60Value;
			
			if (driver.getWindowHandles().size() == 2)
			{
				wait.until(ExpectedConditions.numberOfElementsToBe(D.Q.FullQuote, 2)).get(0).click();
				wait.until(ExpectedConditions.numberOfWindowsToBe(3));
			}
			switchToTab("360Value");
			waitForPageToLoad();
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(D.AI.AddressInformation));
				System.out.println("non standardised addres");
				WebElement radio = driver.findElement(D.AI.AddressAsEntered);
				if (!radio.isSelected())
				{
					radio.click();
				}
				driver.findElement(D.AI.Confirm).click();
				waitForPageToLoad();
			} 
			catch (TimeoutException e)
			{
//				do nothing
			}
			finally
			{
				WebElement ele;
				// FILL Year Built
				// ele =
				// wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.YearBuilt));
				ele = driver.findElement(D.HI.YearBuilt);
				if (TV.containsKey("YearBuilt"))
				{
					if (ele.isDisplayed())
					{
						ele.clear();
						ele.sendKeys(TV.get("YearBuilt"));
					} else
					{
						Global.put("YearBuilt", TV.get("YearBuilt"));
					}
				} else
				{

					if (ele.isDisplayed())
					{
						if (Global.get("PolicyType").contains("COC5"))
						{
							ele.sendKeys(Global.get("CurrentYear"));
						} else
						{
							ele.clear();
							ele.sendKeys("2000");
						}
					}

				}
				// Fill Total Finished Square Area
//				ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.TotalFinishedSquareFeet));
				ele = driver.findElement(D.HI.TotalFinishedSquareFeet);
				if (TV.containsKey("SquareArea"))
				{
					if (ele.isDisplayed())
					{
						ele.sendKeys(TV.get("SquareArea"));
					} else
					{
						Global.put("SquareArea", TV.get("SquareArea"));
					}
				} else
				{
					if (ele.isDisplayed())
					{
						ele.clear();
						ele.sendKeys("950");
					}
				}

				// fill Quality Grade
//				ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.QualityGrade));
				ele = driver.findElement(D.HI.QualityGrade);
				if (TV.containsKey("QualityGrade"))
				{
					if (ele.isDisplayed())
					{
						select = new Select(ele);
						select.selectByVisibleText(TV.get("QualityGrade"));
					}
				} else
				{
					if (ele.isDisplayed())
					{
						select = new Select(ele);
						select.selectByVisibleText("Standard");
					}
				}
				ele.sendKeys(Keys.TAB);
				waitForPageToLoad();

				// Fill Use
				ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Use));
				select = new Select(ele);
				if (TV.containsKey("Use"))
				{
					select.selectByVisibleText(TV.get("Use"));
				} else
				{
					if (Global.get("PolicyType").contains("SPTH"))
					{
						select.selectByVisibleText("Single Family Attached End Unit");
					} else
					{
						select.selectByVisibleText("Single Family Detached");
					}
				}

				// fill Style
				if (TV.containsKey("Style"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Style));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("Style"));
				}

				// Fill Number of Stories
				if (TV.containsKey("NumberOfStories"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.NumberOfStories));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("NumberOfStories"));
				}

				// Fill Foundation Shape
				if (TV.containsKey("FoundationShape"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.FoundationShape));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("FoundationShape"));

				}

				// Fill Foundation Type
				if (TV.containsKey("FoundationType"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.FoundationType));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("FoundationType"));
					// wait.until(ExpectedConditions.elementToBeClickable(D.HI.FoundationTypePercentage)).sendKeys("100");
				}

				// Fill Exterior Wall Finish
				if (TV.containsKey("ExteriorWallFinish"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.ExteriorWallFinish));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("ExteriorWallFinish"));
					// wait.until(ExpectedConditions.elementToBeClickable(D.HI.ExteriorWallFinishPercentage)).sendKeys("100");
				}

				// fill Floor Coverings
				if (TV.containsKey("FloorCoverings"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.FloorCoverings));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("FloorCoverings"));
					// wait.until(ExpectedConditions.elementToBeClickable(D.HI.FloorCoveringsPercentage)).sendKeys("100");
				}

				// fill roof shape
				if (TV.containsKey("RoofShape"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.RoofShape));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("RoofShape"));
				}

				// fill Roof Cover
				if (TV.containsKey("RoofCover"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.RoofCover));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("RoofCover"));
					// wait.until(ExpectedConditions.elementToBeClickable(D.HI.RoofCoverPercentage)).sendKeys("100");
				}

				// fill Kitchens
				if (TV.containsKey("Kitchen"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Kitchen));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("Kitchen"));
					// wait.until(ExpectedConditions.elementToBeClickable(D.HI.KitchenNumber)).sendKeys("1");
				}

				// fill Bathroom
				if (TV.containsKey("Bathroom"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Bathroom));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("Bathroom"));
					// wait.until(ExpectedConditions.elementToBeClickable(D.HI.BathroomNumber)).sendKeys("1");
				}

				// fill Garage/Carport
				if (TV.containsKey("Garage"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Garage));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("Garage"));
				}

				// fill Exterior Wall Construction
				if (TV.containsKey("ExteriorWallConstruction"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.ExteriorWallConstruction));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("ExteriorWallConstruction"));
					// wait.until(ExpectedConditions.elementToBeClickable(D.HI.ExteriorWallConstructionPercentage)).sendKeys("100");
				}

				// fill Foundation Material
				if (TV.containsKey("FoundationMaterial"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.FoundationMaterial));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("FoundationMaterial"));
					// wait.until(ExpectedConditions.elementToBeClickable(D.HI.FoundationMaterialPercentage)).sendKeys("100");
				}

				// Fill Property Slope

				if (TV.containsKey("PropertySlope"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.PropertySlope));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("PropertySlope"));

				}

				// Fill Site Access
				if (TV.containsKey("SiteAccess"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.SiteAccess));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("SiteAccess"));
				}

				// Fill Average Wall Height
				if (TV.containsKey("AverageWallHeight"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.AverageWallHeight));
					ele.sendKeys(TV.get("AverageWallHeight"));
				}

				// Fill Interior Wall Material
				if (TV.containsKey("InteriorWallMaterial"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.InteriorWallMaterial));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("InteriorWallMaterial"));
				}

				// Fill Interior Wall Finish
				if (TV.containsKey("InteriorWallFinish"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.ExteriorWallFinish));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("InteriorWallFinish"));
				}

				// Heating System
				if (TV.containsKey("HeatingSystem"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.HeatingSystem));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("HeatingSystem"));
				}

				// Cooling System
				if (TV.containsKey("CoolingSystem"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.CoolingSystem));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("CoolingSystem"));
				}

				// Speciality System
				if (TV.containsKey("SpecialitySystem"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.SpecialitySystems));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("SpecialitySystem"));
				}

				// Fireplace
				if (TV.containsKey("Fireplaces"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Fireplaces));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("Fireplaces"));
				}

				// Interior Doors and Millwork
				if (TV.containsKey("InteriorDoor"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.InteriorDoors));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("InteriorDoor"));
				}

				// Alternative Energy
				if (TV.containsKey("AlternativeEnergy"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.AlternativeEnergy));
					select = new Select(ele);
					select.selectByVisibleText("AlternativeEnergy");
				}

				// User Defined Feature
				if (TV.containsKey("UserDefinedFeature"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.UserDefinedFeature));
					ele.sendKeys(TV.get("UserDefinedFeature"));
				}

				// Porches
				if (TV.containsKey("Porches"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Porches));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("Porches"));
				}

				// Decks
				if (TV.containsKey("Decks"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Decks));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("Decks"));
				}

				// Potios
				if (TV.containsKey("Potios"))
				{
					ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.HI.Patios));
					select = new Select(ele);
					select.selectByVisibleText(TV.get("Potios"));
				}

				// click on confirm
				ele = wait.until(ExpectedConditions.elementToBeClickable(D.HI.Calculate));
				ele.click();
				waitForPageToLoad();

				// push reconstruction value to Global
				ele = wait.until(ExpectedConditions.visibilityOfElementLocated(D.R.EstimatedReconstructionCost));
				Global.put("ReconstructionCost", ele.getText());

				// click on finish
				ele = wait.until(ExpectedConditions.elementToBeClickable(D.R.Finish));
				ele.click();
				waitForPageToLoad();

				// close 360 degree window
//				driver.close();
				closeAndSwitchToTab("Express Home");

			}

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("fill360Value") < 3)
			{
				switchToTab("Express Home");
				return fill360Value(TV);
			}
			else if (!debugging)
			{
				driver.quit();
			}
			return false;
		}
		System.out.println("360 Value filled");
		return true;

	}

	public boolean fillDwellingInformation(HashMap<String, String> DI)
	{
		DwellingInformation dom = h.Express1.DwellingInformation;

		try
		{
			if (increaseCounter("fillDwellingInformation") == 3)
			{
				throw new UnknownError("Tried twice to Fill Dwelling information but failed");
			}
			System.out.println("filling Dweling Information" + getCounter("fillDwellingInformation"));
			// expand dwelling section
			switchToTab("Express Home");
			wait.until(ExpectedConditions.elementToBeClickable(dom.Toggler));
			if (driver.findElement(By.id("SHOWDWELLING")).isDisplayed())
			{
				driver.findElement(dom.Toggler).click();
			}
			waitForPageToLoad();

			// fill Occupancy
			if (DI.containsKey("Occupancy"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(dom.Occupancy));
				driver.findElement(dom.Occupancy).sendKeys(DI.get("Occupancy"));
			}

			// fill Pool
			wait.until(ExpectedConditions.numberOfElementsToBe(dom.Pool, 2));
			if (DI.containsKey("Pool"))
			{
				driver.findElement(dom.Pool).click();
			} else
			{
				driver.findElements(dom.Pool).get(1).click();
			}

			// fill Roof Year
			wait.until(ExpectedConditions.elementToBeClickable(dom.roofYear));
			if (DI.containsKey("RoofYear"))
			{
				driver.findElement(dom.roofYear).clear();
				driver.findElement(dom.roofYear).sendKeys(DI.get("RoofYear"));
			} else
			{
				driver.findElement(dom.roofYear).clear();
				String policyType = Global.get("PolicyType");
				if (policyType.contains("COC5") || policyType.contains("COC6"))
				{
					Global.put("RoofYear", driver.findElement(By.id("effective_date_year")).getAttribute("value"));
				} else
				{
					Global.put("RoofYear", "2005");
				}
				driver.findElement(dom.roofYear).sendKeys(Global.get("RoofYear"));
			}
			// fill years warranted
			if (DI.containsKey("YearsWarranted"))
			{
				driver.findElement(dom.YearsWarranted).sendKeys(DI.get("YearsWarranted"));
			} else
			{
				driver.findElement(dom.YearsWarranted).sendKeys("20");
			}
			// fill Fuel
			if (DI.containsKey("Fuel"))
			{
				driver.findElement(dom.Fuel).sendKeys(DI.get("Fuel"));
				driver.findElement(dom.Fuel).sendKeys(Keys.TAB);
			} else
			{
				driver.findElement(dom.Fuel).sendKeys("ELECTRIC");
			}
			// fill renovation
			if (DI.containsKey("Renovated"))
			{
				boolean flag = false;
				driver.findElement(dom.Renovated).click();
				if (DI.containsKey("YrPlumb"))
				{
					driver.findElement(dom.YrPlumbing).sendKeys(DI.get("YrPlumb"));
					flag = true;
					// Global.put("YrPlumb", DI.get("YrPlumb"));
				}
				if (DI.containsKey("YrHeat"))
				{
					driver.findElement(dom.YrHeat).sendKeys(DI.get("YrHeat"));
					flag = true;
					// Global.put("YrHeat", DI.get("YrHeat"));
				}
				if (DI.containsKey("YrElect"))
				{
					driver.findElement(dom.YrElect).sendKeys(DI.get("YrElect"));
					flag = true;
					// Global.put("YrElect", DI.get("YrElect"));
				}
				if (!flag)
				{
					driver.findElements(dom.Renovated).get(1).click();
					System.out.println("Renovation Year is missing. Reverting back Renovated to No");
				}
			}
			// fill Home Safety
			if (DI.containsKey("HomeSafety"))
			{
				driver.findElement(dom.HomeSafetyText).sendKeys(DI.get("HomeSafety"));
				driver.findElement(dom.HomeSafetyText).sendKeys(Keys.TAB);
			}
			// fill central fire
			if (DI.containsKey("CentralFire"))
			{
				driver.findElement(dom.CentralFire).click();
			}
			// fill local burglar alarm
			if (DI.containsKey("LocalBurglar"))
			{
				if (DI.containsKey("CentralBurglar"))
				{
					System.out.println(
							"Either one can be selected from Local Burglar and Central Burglar. Local Burglar Selected");
				}
				driver.findElement(dom.LocalBurglar).click();
			}
			// fill central burglar
			if (DI.containsKey("CentralBurglar"))
			{
				if (DI.containsKey("LocalBurglar"))
				{
					System.out.println(
							"Either one can be selected from Local Burglar and Central Burglar. Local Burglar Selected");
					driver.findElement(dom.LocalBurglar).click();
				} else
				{
					driver.findElement(dom.CentralBurglar).click();
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("fillDwellingInformation") < 3)
			{
				return fillDwellingInformation(DI);
			}
			else if (!debugging)
			{
				driver.quit();
			}
			return false;
		}
		System.out.println("Dwelling Information filled");
		return true;

	}

	public boolean fillCoverage(HashMap<String, String> CV)
	{
		Coverage dom = h.Express1.Coverage;

		try
		{
			if (increaseCounter("fillCoverage") == 3)
			{
				throw new UnknownError("Tried twice to Fill Coverage but failed");
			}
			System.out.println("filling Coverage" + getCounter("fillDwellingInformation"));

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("fillCoverage") < 3)
			{
				return fillCoverage(CV);
			}
			else if (!debugging)
			{
				driver.quit();
			}
			return false;
		}
		System.out.println("Coverage Information filled");
		return true;
	}


	public boolean rate()
	{
//		Coverage dom = h.Express1.Coverage;
		WebElement ele = driver.findElement(By.tagName("body"));

		try
		{
			if (increaseCounter("rate") == 3)
			{
				throw new UnknownError("Tried twice to rate but failed");
			}
			System.out.println("Rating " + getCounter("rate"));
			WebElement tab = driver.findElements(h.Express1.Tab).get(1);
			if (tab.getText().equalsIgnoreCase("Quote"))
			{
				ele = driver.findElement(h.Express1.Rate);
			}
			else if (tab.getText().equalsIgnoreCase("Buy"))
			{
				ele = driver.findElement(h.Express1.Verify);
			}
			
			ele.click();
			waitForPageToLoad();
			Alert alert;
			if((alert = ExpectedConditions.alertIsPresent().apply(driver)) != null)
			{
				System.out.println(alert.getText());
				alert.accept();
			}
			wait.until(ExpectedConditions.invisibilityOfElementLocated(h.Express1.Rate));
			waitForPageToLoad();
			
			Global.put("HH", driver.getTitle().split("Household: ")[1]);

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("rate") < 3)
			{
				System.out.println(Integer.toString(getCounter("rate")+1) + " try");
				return rate();
			}
			else if (!debugging)
			{
				System.out.println("should close the driver");
				driver.quit();
			}
			return false;
		}
		System.out.println("rated successfully");
		return true;
	}
	
	public boolean proceedToBuy()
	{
//		Coverage dom = h.Express1.Coverage;

		try
		{
			if (increaseCounter("proceedToBuy") == 3)
			{
				throw new UnknownError("Tried twice to rate but failed");
			}
			System.out.println("proceed to buy " + getCounter("proceedToBuy"));
			
			js.executeScript("switchTab('Buy')");
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(h.Express1.Rate));
			waitForPageToLoad();

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("proceedToBuy") < 3)
			{
				System.out.println(Integer.toString(getCounter("proceedToBuy")+1) + " try");
				return rate();
			}
			else if (!debugging)
			{
				System.out.println("should close the driver");
				driver.quit();
			}
			return false;
		}
		System.out.println("moved to but tab successfully");
		return true;
	}

	public boolean saveNExit()
	{
		try
		{
			if (increaseCounter("saveNExit") == 3)
			{
				throw new UnknownError("Tried twice to save n exit but failed");
			}
			System.out.println("save and exit " + getCounter("saveNExit"));
			
			js.executeScript("saveAndExit()");
			js.executeScript("saveAndExitYesCall()");
			wait.until(ExpectedConditions.titleIs("Express"));
			
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(h.Express1.Rate));
			waitForPageToLoad();

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("saveNExit") < 3)
			{
				System.out.println(Integer.toString(getCounter("saveNExit")+1) + " try");
				return rate();
			}
			else if (!debugging)
			{
				System.out.println("should close the driver");
				driver.quit();
			}
			return false;
		}
		System.out.println("saved and exited successfully");
		return true;
		
	}

	public boolean goToMenu()
	{
		try
		{
			if (increaseCounter("goToMenu") == 3)
			{
				throw new UnknownError("Tried twice to go to menu but failed");
			}
			System.out.println("go to menu " + getCounter("goToMenu"));
			
			js.executeScript("gotoMenu()");
			driver.findElements(By.tagName("button")).get(2).click();
			
			wait.until(ExpectedConditions.titleIs("Express"));
			
			waitForPageToLoad();

		} catch (Exception e)
		{
			e.printStackTrace();
			if (getCounter("goToMenu") < 3)
			{
				System.out.println(Integer.toString(getCounter("goToMenu")+1) + " try");
				return rate();
			}
			else if (!debugging)
			{
				System.out.println("should close the driver");
				driver.quit();
			}
			return false;
		}
		System.out.println("gone to menu successfully");
		return true;
		
	}
}
