import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import home.Express1;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import utilities.Utilities;

public class Test
{

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException
	{
		// TODO Auto-generated method stub
		HashMap<String, String> Init = new HashMap<String, String>();
		HashMap<String, String> ECMS = new HashMap<String, String>();
		HashMap<String, String> HI = new HashMap<String, String>();
		HashMap<String, String> PC = new HashMap<String, String>();
		HashMap<String, String> DI = new HashMap<String, String>();
		HashMap<String, String> TV = new HashMap<String, String>();

		 
		Init.put("User", "ball126");
		Init.put("Password", "test11");
		Init.put("Env", "MA11");
		ECMS.put("ZIP", "90802");
		ECMS.put("MarritalStatus", "Single");
		HI.put("PolicyType", "SPTH");
		HI.put("NonSmoker", "jhol");
		
		Express1 u = new Express1();
		boolean flag = u.launchBrowser(Init);
		if (flag)
			flag = u.login(Init);
		if (flag)
			flag = u.goToECMS();
		if (flag)
			flag = u.fillECMS(ECMS);
		if (flag)
			flag = u.fillHouseholdInformation(HI);
		if (flag)
			flag = u.fillProtectionClass(PC);
		if (flag)
			flag = u.fill360Value(TV);
		if (flag)
			flag = u.fillDwellingInformation(DI);
		if (flag)
			flag = u.rate();
		if (flag)
			flag = u.proceedToBuy();
		if (flag)
			flag = u.saveNExit();
//		if (flag)
//			flag = u.closure();


		System.out.println(u.Global.get("ReconstructionCost"));
		System.out.println(u.Global.get("HH"));
		System.out.println("end");

	}

}
