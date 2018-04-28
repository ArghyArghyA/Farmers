package repository;

import org.openqa.selenium.By;

public final class Home extends EAgent
{
	public final Express1 Express1 = new Express1();

	public final class Express1
	{
		public final By Rate = By.id("RATE");
		public final By Verify = By.id("VERIFY");
		public final By SaveNExit = By.id("SAVEEXIT");
		public final By GoToMenu = By.id("GOTOMENU");
		public final By Tab = By.tagName("strong");//will return Home and active tab
//		public final By ProceedToBuy = By.id("")
		
		public final ECMS ECMS = new ECMS();

		public final class ECMS
		{
			public final By EffectiveDateMM = By
					.id("effectiveDateMM");
			public final By EffectiveDateDD = By
					.id("effectiveDateDD");
			public final By EffectiveDateYYYY = By
					.id("effectiveDateYYYY");

			public final By PolicyType = By
					.name("policyTypeHome");
			public final By ContinueQuote = By
					.id("continueQuoteBtn");

			public final By Agent = By
					.name("agentforAccount");
			public final By Series = By
					.name("commisionSeries");

			public final By FirstName = By
					.name("firstName");
			public final By MiddleName = By
					.name("middleName");
			public final By LastName = By
					.name("lastNameField");

			public final By Street = By.name("street1");
			public final By City = By.name("city");
			public final By State = By.name("state");
			public final By ZIP = By.name("zip");

			public final By MM = By.name("dateOfBirthMM");
			public final By DD = By.name("dateOfBirthDD");
			public final By YYYY = By
					.name("dateOfBirthYYYY");
			public final By DOBLink = By.id("DOBLink");

			public final By MarritalStatus = By.cssSelector(
					"select.longdropDownSecond.martialStatus");

			public final By SourceCategory = By
					.name("leadSourceCategory");
			public final By LeadSource = By
					.name("leadSource");

			public final By Phone = By
					.name("phoneNumberCombined");
			public final By eMail = By.name("email");

			public final By SSN = By.name("ssn1");
			public final By SSNLink = By.id("ssnLink");
			public final By License = By
					.name("driverLicenseNumber");
			public final By LicenseState = By
					.name("driverLicenseState");

			public final By Male = By.id("MALE_RADIO");
			public final By Female = By.id("FMALE_RADIO");

			public final By OccupationGroup = By
					.name("occupationGroup");
			public final By Occupation = By
					.name("occupation");
		}

		public final HouseholdInformation HouseholdInformation = new HouseholdInformation();

		public final class HouseholdInformation
		{
			public final By EffectiveDateMM = By
					.id("effective_date_month");
			public final By EffectiveDateDD = By
					.id("effective_date_day");
			public final By EffectiveDateYYYY = By
					.id("effective_date_year");

			public final By PolicyType = By
					.id("_lblpolicy_type");

			public final By PhoneArea = By.id("phone_area");
			public final By PhoneMiddle = By
					.id("phone_middle");
			public final By PhoneLast = By.id("phone_last");

			public final By eMail = By.id("email_address");

			public final By FirstName = By.id("first_name");
			public final By MiddleName = By
					.id("middle_name");
			public final By LastName = By.id("last_name");

			public final By DOBHidden = By.id("hrefLink1");
			public final By DOBMM = By
					.id("date_of_birth_month");
			public final By DOBDD = By
					.id("date_of_birth_day");
			public final By DOBYYYY = By
					.id("date_of_birth_year");

			public final By LeadSource = By
					.id("lead_Source");

			public final By PriorCarrier = By
					.id("_lblprior_Carrier");

			public final By OccupationGroup = By
					.id("_lbloccupation_group");
			public final By Occupation = By
					.id("_lbloccupation");

			public final By RelatedHousehold = By
					.id("related_household");

			public final By NumberOfAdults = By
					.id("number_of_adults");

			public final By CampaignSource = By
					.id("_lblcampaignSource");

			public final By DiscountAuto = By
					.id("auto_discount_credit");
			public final By DiscountCEA = By
					.id("cea_discount_credit");
			public final By DiscountUmbrella = By
					.id("umbrella_discount_credit");
			public final By DiscountLife = By
					.id("life_discountCredit");
			public final By DiscountBusiness = By
					.id("business");
			public final By DiscountNonSmoker = By
					.id("Non_Smoker");

		}
		
		public final DwellingInformation DwellingInformation = new DwellingInformation();
		public final class DwellingInformation
		{
			public final By Toggler = By.id("dwellingtoogler");
			
			public final By FullQuote = By.id("fullQuote360");//should return both full quote with and without 360
			
			public final By Occupancy = By.id("_lbloccupancy");//should return the text field
			
			public final By Pool = By.id("pool");//should return both Y and N button
			
			public final By roofYear = By.id("roof_age");
			public final By YearsWarranted = By.id("yearsRoofWarranted");
			
			public final By Fuel = By.id("_lblfuel_type");
			
			public final By Renovated = By.id("renovated");//should return both button
			
			public final By YrPlumbing = By.id("yr_renov_plumb");
			public final By YrHeat = By.id("yr_renov_heat");
			public final By YrElect = By.id("yr_renov_elect");
			
			public final By HomeSafetyText = By.id("_lblhomesafety");
			public final By HomeSafetyDropdown = By.id("_imghomesafety");
//			public final By HomeSafetyValues = By.id("")
			
			public final By CentralFire = By.id("fire");
			public final By LocalBurglar = By.id("localAlarm");
			public final By CentralBurglar = By.id("burglar");
		}

		public final Three60Value Three60Value = new Three60Value();
		public final class Three60Value
		{
			public final AddressInformation AI = new AddressInformation();
			public final class AddressInformation
			{
				public final By AddressInformation = By.id("help_link_AddressStandardIV");
				
				public final By Verified = By.id("select_radio_GlossStandardizedAddressIV");
				public final By AddressAsEntered = By.id("select_radio_GlossAddressasEntered");
				
				public final By Edit = By.id("select_radio_AdvancedEditResIV");
				public final By StreetNumber = By.id("addressUpdateForm_w[PRI][XT_STREET_NUM].value");
				public final By PreDirectional = By.id("addressUpdateForm_w[PRI][XT_STREET_PRE].value");
				public final By StreetName = By.id("addressUpdateForm_w[PRI][XT_STREET_NAME].value");
				public final By PostDirectional = By.id("addressUpdateForm_w[PRI][XT_STREET_POST].value");
				public final By Unit = By.id("addressUpdateForm_w[PRI][XT_UNIT_NUMBER].value");
				public final By City = By.id("addressUpdateForm_w[PRI][XT_CITY].value");
				public final By State = By.id("addressUpdateForm_w[PRI][XT_STATE].value");
				public final By ZIP = By.id("addressUpdateForm_w[PRI][XT_ZIP].value");
				public final By Country = By.id("addressUpdateForm_w[PRI][XT_COUNTRY].value");
			
				public final By Confirm = By.id("confirm_anchor");
			}
			
			public final HomeInformation HI = new HomeInformation();
			public final class HomeInformation
			{
				public final By YearBuilt = By.id("xtolform_w[PRI][XT_YEAR_BUILT].value");
				public final By TotalFinishedSquareFeet = By.id("xtolform_w[PRI][XT_USER_TOTAL_SF].value");
				public final By QualityGrade = By.id("xtolform_w[PRI][XT_STRUCT_QUALITY].value");
				public final By Use = By.id("xtolform_w[PRI][XTOL_PU].fdeList[0].id");
				public final By Style = By.id("xtolform_w[PRI][XTOL_S].fdeList[0].id");
				public final By NumberOfStories = By.id("xtolform_w[PRI][XTOL_P].fdeList[0].id");
				public final By FoundationShape = By.id("xtolform_w[PRI][XTOL_SH].fdeList[0].id");
				public final By FoundationType = By.id("xtolform_w[PRI][XTOL_F].fdeList[0].id");
				public final By FoundationTypePercentage = By.id("xtolform_w[PRI][XTOL_F].fdeList[0].value");
				public final By ExteriorWallFinish = By.id("xtolform_w[PRI][XTOL_EWF].fdeList[0].id");
				public final By ExteriorWallFinishPercentage = By.id("xtolform_w[PRI][XTOL_EWF].fdeList[0].value");
				public final By FloorCoverings = By.id("xtolform_w[PRI][XTOL_FC].fdeList[0].id");
				public final By FloorCoveringsPercentage = By.id("xtolform_w[PRI][XTOL_FC].fdeList[0].value");
				public final By RoofShape = By.id("xtolform_w[PRI][XTOL_RO].fdeList[0].id");
				public final By RoofCover = By.id("xtolform_w[PRI][XTOL_RC].fdeList[0].id");
				public final By RoofCoverPercentage = By.id("xtolform_w[PRI][XTOL_RC].fdeList[0].value");
				public final By Kitchen = By.id("xtolform_w[PRI][XTOL_KITCHEN].fdeList[0].id");
				public final By KitchenNumber = By.id("xtolform_w[PRI][XTOL_KITCHEN].fdeList[0].value");
				public final By Bathroom =By.id("xtolform_w[PRI][XTOL_BATHROOM].fdeList[0].id");
				public final By BathroomNumber = By.id("xtolform_w[PRI][XTOL_BATHROOM].fdeList[0].value");
				public final By Garage = By.id("xtolform_w[PRI][XT_GARAGE_CARS0].value");
				public final By ExteriorWallConstruction = By.id("xtolform_w[PRI][XTOL_EWT].fdeList[0].id");
				public final By ExteriorWallConstructionPercentage = By.id("xtolform_w[PRI][XTOL_EWT].fdeList[0].value");
				public final By FoundationMaterial = By.id("xtolform_w[PRI][XTOL_FM].fdeList[0].id");
				public final By FoundationMaterialPercentage = By.id("xtolform_w[PRI][XTOL_FM].fdeList[0].value");
				public final By PropertySlope = By.id("xtolform_w[PRI][XTOL_SL].fdeList[0].id");
				public final By SiteAccess = By.id("xtolform_w[PRI][XTOL_SA].fdeList[0].id");
				public final By AverageWallHeight = By.id("xtolform_w[PRI][XT_AVG_INT_HEIGHT].value");
				public final By InteriorWallMaterial = By.id("xtolform_w[PRI][XTOL_WM].fdeList[0].id");
				public final By InteriorWallMaterialPercentage = By.id("xtolform_w[PRI][XTOL_WM].fdeList[0].value");
				public final By InteriorWallFinish = By.id("xtolform_w[PRI][XTOL_IWF].fdeList[0].id");
				public final By InteriorWallFinishPercentage = By.id("xtolform_w[PRI][XTOL_IWF].fdeList[0].value");
				public final By HeatingSystem = By.id("xtolform_w[PRI][XTOL_HA].fdeList[0].id");
				public final By HeatingSystemNumber = By.id("xtolform_w[PRI][XTOL_HA].fdeList[0].value");
				public final By CoolingSystem = By.id("xtolform_w[PRI][XTOL_AC].fdeList[0].id");
				public final By CoolingSystemNumber = By.id("xtolform_w[PRI][XTOL_AC].fdeList[0].value");
				public final By SpecialitySystems = By.id("xtolform_w[PRI][XTOL_HS].fdeList[0].id");
				public final By SpecialitySystemsNUmber = By.id("xtolform_w[PRI][XTOL_HS].fdeList[0].value");
				public final By Fireplaces = By.id("xtolform_w[PRI][XTOL_FP].fdeList[0].id");
				public final By FireplacesNumber = By.id("xtolform_w[PRI][XTOL_FP].fdeList[0].value");
				public final By InteriorDoors = By.id("xtolform_w[PRI][XTOL_IF].fdeList[0].id");
				public final By InteriorDoorsNumber = By.id("xtolform_w[PRI][XTOL_IF].fdeList[0].value");
				public final By AlternativeEnergy = By.id("xtolform_w[PRI][XTOL_AE].fdeList[0].id");
				public final By AlternativeEnergyNumber = By.id("xtolform_w[PRI][XTOL_AE].fdeList[0].value");
				public final By UserDefinedFeature = By.id("xtolform_w[USER_DEFINED_SRC_0][XT_USR_DEF_FEATURE].value");
				public final By Porches = By.id("xtolform_w[PRI][XT_PORCH_STYLE0].value");
				public final By Decks = By.id("xtolform_w[PRI][XT_DECK_STYLE0].value");
				public final By Patios = By.id("xtolform_w[PRI][XT_PATIO_STYLE0].value");
				
				
				public final By Back = By.id("backbutton");
				public final By Calculate = By.id("calculatebutton");
			}
			
			public final Results R = new Results();
			public final class Results
			{
				public By EstimatedReconstructionCost = By.id("resultsReportEstimatedReplacementCost");
				
				public By Edit = By.id("RESULTS_BOTTOM_BUTTON_EDIT");
				public By Finish = By.id("RESULTS_BOTTOM_BUTTON_FINISH");
			}
		
			public final Quote Q = new Quote();
			public final class Quote
			{
				public final By FullQuote = By.id("fullQuote360");//should return both full quote with and without 360
			}
		}
		
		
		public final ProtectionClass ProtectionClass = new ProtectionClass();
		
		public final class ProtectionClass
		{
			public final By HydrantWithin1000Feet = By.id("hydrant_within_1000feet");//should return both Y and N radio button
			public final By UnclearedBrushWithin100Feet = By.id("uncleared_brush_within_100feet"); //should return both Y and N radio button
			public final By DesignatedBrushWithin150Feet = By.id("designated_Brush_within_150feet"); //should return both Y and N radio button
			
			public final By TerritoryText = By.id("_lblterritory");
			public final By TerritoryDropdown = By.id("_imgterritory");
			public final By TerritoryValues = By.id("_lblterritory1_div_inner_id"); //should return all the territory elements
			
			public final By FireDistrictName = By.id("fire_district_name");
			
		}
		
		public final Coverage Coverage = new Coverage();
		
		public final class Coverage
		{
			
		}
		
		public final AdditionalHouseholdInformation AdditionalHouseholdInformation = new AdditionalHouseholdInformation();
		
		public final class AdditionalHouseholdInformation
		{
			
		}
		
		public final AdditionalPolicyInformation AdditionalPolicyInformation = new AdditionalPolicyInformation();
		public final class AdditionalPolicyInformation
		{
			 
		}
		
		public final CoverageAdditionalInformation CoverageAdditionalInformation = new CoverageAdditionalInformation();
		public final class CoverageAdditionalInformation{
			
		}
		
		public final CoverageBuy CoverageBuy = new CoverageBuy();
		public final class CoverageBuy
		{
			 
		}
		
		public final GoPaperless GoPaperless = new GoPaperless();
		public final class GoPaperless
		{
			public final By eSign = By.id("esignoption");
			public final By PaperlessBilling = By.id("paperlessbillingind");
			public final By PaperlessPolicy = By.id("epolicyoption");
			
			public final By SignMeUp = By.id("signMeUp");
			
			public final By eMail = By.id("eMail");
			
		}
	}
}
