package commands;




public class CmdMCDSCommand extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	
	public CmdMCDSHunger CmdMCDSHunger = new CmdMCDSHunger();
	public CmdMCDSCrappify CmdMCDSCrappify = new CmdMCDSCrappify();
	public CmdMCDSDebug CmdMCDSDebug = new CmdMCDSDebug();
	public CmdMCDSMelt CmdMCDSMelt = new CmdMCDSMelt();
	public CmdMCDSSeason CmdMCDSSeason = new CmdMCDSSeason();
	public CmdMCDSCheck CmdMCDSCheck = new CmdMCDSCheck();
	public CmdMCDSWriteBiomeData CmdMCDSWriteBiomeData = new CmdMCDSWriteBiomeData();

	public CmdMCDSCommand()
	{
		// Aliases
		this.addAliases("m", "mcds");

		this.setDesc("MCDS commands");
		this.setHelp("This command is used to do other mcds commands");

		this.addChild(this.CmdMCDSHunger);
		this.addChild(this.CmdMCDSCrappify);
		this.addChild(this.CmdMCDSDebug);
		this.addChild(this.CmdMCDSMelt);
		this.addChild(this.CmdMCDSSeason);
		this.addChild(this.CmdMCDSCheck);
		this.addChild(this.CmdMCDSWriteBiomeData);
		// Requirements
		//		this.addRequirements(ReqFactionsEnabled.get());
		//		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}

}
