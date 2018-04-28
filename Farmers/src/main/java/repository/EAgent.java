package repository;

import org.openqa.selenium.By;

public class EAgent
{
	public final LogIn LogIn = new LogIn();
	public final class LogIn
	{
		public final By User = By.name("USER");
		public final By Password = By.name("PASSWORD");
//		public final By IAgree;
		
	}
	
	public final ENV ENV = new ENV();
	public final class ENV
	{
		public final By MA11 = By.partialLinkText("MA11");
		public final By RA11 = By.partialLinkText("RA11");
		public final By RS11 = By.partialLinkText("RS11");
		public final By TSV1 = By.partialLinkText("TSV1");
	}
	
	
	public final ADE ADE = new ADE();

	public final class ADE
	{
		public final By Personal = By
				.linkText("Personal");

		public final By LogOut = By.linkText("Log out");

		public final By StartQuoting = By
				.className("button_text_area");
		public final By Auto = By.className("auto");
		public final By Home = By.className("home");
		public final By Umbrella = By
				.className("umbrella");

	}
}
