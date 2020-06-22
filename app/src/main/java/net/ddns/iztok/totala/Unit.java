package net.ddns.iztok.totala;

import android.util.Log;

public class Unit {
	private String unitName;
	private int version;
	private String side;
	private String objectName;
	private String designation;
	private String name;
	private String description;
	private int footprintX;
	private int footprintZ;
	private float buildCostEnergy;
	private float buildCostMetal;
	private int maxDamage;
	private int maxWaterDepth;
	private int maxSlope;
	private float energyUse;
	private int buildTime;
	private int workerTime;
	private boolean BMCode;
	private boolean builder;
	private boolean threeD = true;
	private boolean ZBuffer;
	private boolean noAutoFire;
	private int sightDistance;
	private int radarDistance;
	private String soundCategory;
	private int energyStorage;
	private int metalStorage;
	private String explodeAs;
	private String selfDestructAs;
	private String category;
	private String TEDClass;
	private String copyright;
	private String yardMap;
	private String corpse;
	private String germanName;
	private String germanDescription;
	private int unitNumber;
	private String frenchName;
	private String frenchDescription;
	private byte fireStandOrders;
	private byte standingFireOrder;
	private byte mobileStandOrders;
	private byte standingMoveOrder;
	private boolean canMove;
	private boolean canPatrol;
	private boolean canStop;
	private boolean canGuard;
	private float maxVelocity;
	private float brakeRate;
	private float acceleration;
	private int turnRate;
	private byte steeringMode;
	private boolean shootMe;
	private boolean canFly;
	private byte cruiseAlt;
	private float scale;
	private float bankScale;
	private int buildDistance;
	private boolean canReclamate;
	private float energyMake;
	private float metalMake;
	private String defaultMissionType;
	private int maneuverLeashLength;
	private String movementClass;
	private boolean upright;
	//public byte standingMoveOrder; - se ponovi???
	private int buildAngle;
	private String weapon1;
	private String wpriBadTargetCategory;
	private String badTargetCategory;
	private boolean antiweapons;
	private float damageModifier;
	private boolean canAttack;
	private boolean activateWhenBuilt;
	// ...
	private String weapon2;
	// ...
	private String weapon3;

	public Unit (String data) {
		String[] s = data.split("\t");
		unitName = s[0];
		Log.w("TotalAnnihilation", unitName);
		version = Integer.parseInt(s[1]);
		side = s[2];
		objectName = s[3];
		designation = s[4];
		name = s[5];
		description = s[6];
		footprintX = Integer.parseInt(s[7]);
		footprintZ = Integer.parseInt(s[8]);
		buildCostEnergy = Integer.parseInt(s[9]);
		buildCostMetal = Float.parseFloat(s[10]);
		maxDamage = Integer.parseInt(s[11]);
		try {maxWaterDepth = Integer.parseInt(s[12]);} catch (Exception e) {}
		try {maxSlope = Integer.parseInt(s[13]);} catch (Exception e) {}
		energyUse = Float.parseFloat(s[14]);
		buildTime = Integer.parseInt(s[15]);
		try {workerTime = Integer.parseInt(s[16]);} catch (Exception e) {}
		BMCode = (s[17].equals("1")) ? true : false;
		builder = (s[18].equals("1")) ? true : false;
		threeD = (s[19].equals("1")) ? true : false;
		//zbuffer
		noAutoFire = (s[21].equals("1")) ? true : false;
		sightDistance = Integer.parseInt(s[22]);
		try {radarDistance = Integer.parseInt(s[23]);} catch (Exception e) {}
		soundCategory = s[24];
		try {energyStorage = Integer.parseInt(s[25]);} catch (Exception e) {}
		try {metalStorage = Integer.parseInt(s[26]);} catch (Exception e) {}
		soundCategory = s[24];
		explodeAs = s[27];
		category = s[29];
		//...
		canMove = (s[43].equals("1")) ? true : false;
		try {maxVelocity = Float.parseFloat(s[47]);} catch (Exception e) {}
		try {brakeRate = Float.parseFloat(s[48]);} catch (Exception e) {}
		try {acceleration = Float.parseFloat(s[49]);} catch (Exception e) {}
		try {turnRate = Integer.parseInt(s[50]);} catch (Exception e) {}
		try {weapon1 = s[67];} catch (Exception e) {}
		try {weapon2 = s[84];} catch (Exception e) {}
		try {weapon3 = s[89];} catch (Exception e) {}
	}

	public String getUnitName() {return unitName;}
	public int getVersion() {return version;}
	public String getSide() {return side;}
	public String getObjectName() {return objectName;}
	public String getDesignation() {return designation;}
	public String getName() {return name;}
	public String getDescription() {return description;}
	public int getFootprintX() {return footprintX;}
	public int getFootprintZ() {return footprintZ;}
	public float getBuildCostEnergy() {return buildCostEnergy;}
	public float getBuildCostMetal() {return buildCostMetal;}
	public int getMaxDamage() {return maxDamage;}
	public int getMaxWaterDepth() {return maxWaterDepth;}
	public int getMaxSlope() {return maxSlope;}
	public float getEnergyUse() {return energyUse;}
	public int getBuildTime() {return buildTime;}
	public int getWorkerTime() {return workerTime;}
	public String getSoundCategory() {return soundCategory;}
	public String getExplodeAs() {return explodeAs;}
	public String getCategory() {return category;}
	// ...
	public boolean getCanMove() {return canMove;}
	public float getMaxVelocity() {return maxVelocity;}
	public float getBrakeRate() {return brakeRate;}
	public float getAcceleration() {return acceleration;}
	public int getTurnRate() {return turnRate;}
	// ...
	public String getWeapon1() {return weapon1;}
	// ...
	public String getWeapon2() {return weapon2;}
	// ...
	public String getWeapon3() {return weapon3;}
}
