package com.dscfgos.api.model.dtos.v3.match;

/**
 * @author dscfgos
 * @version 2.0
 * @category MATCH-V3
 */
public class ParticipantStats
{
    private long    physicalDamageDealt;			// - Physical damage dealt
    private long    neutralMinionsKilledTeamJungle;	// - Neutral jungle minions killed in your team's jungle
    private long    magicDamageDealt;				// - Magical damage dealt
    private long    totalPlayerScore;				// - If game was a dominion game, player's total score, otherwise 0
    private int    deaths;							// - Number of deaths
    private boolean win;							// - Flag indicating whether or not the participant won
    private long    neutralMinionsKilledEnemyJungle;// - Neutral jungle minions killed in the enemy team's jungle
    private int 	altarsCaptured ; 
    private long    largestCriticalStrike;			// - Largest critical strike
    private long    totalDamageDealt;				// - Total damage dealt
    private long    magicDamageDealtToChampions;	// - Magical damage dealt to champions
    private long    visionWardsBoughtInGame;		// - Vision wards purchased
    private long 	damageDealtToObjectives ;
    private long    largestKillingSpree;			// - Largest killing spree
    private int     item1;							// - Second item ID
    private long    quadraKills;					// - Number of quadra kills
    private long    teamObjective;					// - If game was a dominion game, number of completed team objectives (i.e., quests)
    private long    totalTimeCrowdControlDealt;		// - Total dealt crowd control time
    private int 	longestTimeSpentLiving ;
    private long    wardsKilled;					// - Number of wards killed

    
    private boolean firstTowerAssist ;	
    private boolean firstTowerKill ;	
    private int item2 ;	
    private int item3 ;	
    private int item0 ;	
    private boolean firstBloodAssist ;	
    private long visionScore ;	
    private int wardsPlaced ;	
    private int item4 ;	
    private int item5 ;	
    private int item6 ;	
    private int turretKills ;	
    private int tripleKills ;	
    private long damageSelfMitigated ;	
    private int champLevel ;	
    private int nodeNeutralizeAssist ;	
    private boolean firstInhibitorKill ;	
    private int goldEarned ;	
    private long magicalDamageTaken ;	
    private int kills ;	
    private int doubleKills ;	
    private int nodeCaptureAssist ;	
    private long trueDamageTaken ;	
    private int nodeNeutralize ;	
    private boolean firstInhibitorAssist ;	
    private int assists ;	
    private int unrealKills ;	
    private int neutralMinionsKilled ;	
    private int objectivePlayerScore ;	
    private int combatPlayerScore ;	
    private long damageDealtToTurrets ;	
    private int altarsNeutralized ;	
    private long physicalDamageDealtToChampions ;	
    private int goldSpent ;	
    private long trueDamageDealt ;	
    private long trueDamageDealtToChampions ;	
    private int participantId ;	
    private int pentaKills ;	
    private long totalHeal ;	
    private int totalMinionsKilled ;	
    private boolean firstBloodKill ;	
    private int nodeCapture ;	
    private int largestMultiKill ;	
    private int sightWardsBoughtInGame ;	
    private long totalDamageDealtToChampions ;	
    private int totalUnitsHealed ;	
    private int inhibitorKills ;	
    private int totalScoreRank ;	
    private long totalDamageTaken ;	
    private int killingSprees ;	
    private long timeCCingOthers ;	
    private long physicalDamageTaken ;
	public long getPhysicalDamageDealt() {
		return physicalDamageDealt;
	}
	public void setPhysicalDamageDealt(long physicalDamageDealt) {
		this.physicalDamageDealt = physicalDamageDealt;
	}
	public long getNeutralMinionsKilledTeamJungle() {
		return neutralMinionsKilledTeamJungle;
	}
	public void setNeutralMinionsKilledTeamJungle(long neutralMinionsKilledTeamJungle) {
		this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
	}
	public long getMagicDamageDealt() {
		return magicDamageDealt;
	}
	public void setMagicDamageDealt(long magicDamageDealt) {
		this.magicDamageDealt = magicDamageDealt;
	}
	public long getTotalPlayerScore() {
		return totalPlayerScore;
	}
	public void setTotalPlayerScore(long totalPlayerScore) {
		this.totalPlayerScore = totalPlayerScore;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	public long getNeutralMinionsKilledEnemyJungle() {
		return neutralMinionsKilledEnemyJungle;
	}
	public void setNeutralMinionsKilledEnemyJungle(long neutralMinionsKilledEnemyJungle) {
		this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
	}
	public int getAltarsCaptured() {
		return altarsCaptured;
	}
	public void setAltarsCaptured(int altarsCaptured) {
		this.altarsCaptured = altarsCaptured;
	}
	public long getLargestCriticalStrike() {
		return largestCriticalStrike;
	}
	public void setLargestCriticalStrike(long largestCriticalStrike) {
		this.largestCriticalStrike = largestCriticalStrike;
	}
	public long getTotalDamageDealt() {
		return totalDamageDealt;
	}
	public void setTotalDamageDealt(long totalDamageDealt) {
		this.totalDamageDealt = totalDamageDealt;
	}
	public long getMagicDamageDealtToChampions() {
		return magicDamageDealtToChampions;
	}
	public void setMagicDamageDealtToChampions(long magicDamageDealtToChampions) {
		this.magicDamageDealtToChampions = magicDamageDealtToChampions;
	}
	public long getVisionWardsBoughtInGame() {
		return visionWardsBoughtInGame;
	}
	public void setVisionWardsBoughtInGame(long visionWardsBoughtInGame) {
		this.visionWardsBoughtInGame = visionWardsBoughtInGame;
	}
	public long getDamageDealtToObjectives() {
		return damageDealtToObjectives;
	}
	public void setDamageDealtToObjectives(long damageDealtToObjectives) {
		this.damageDealtToObjectives = damageDealtToObjectives;
	}
	public long getLargestKillingSpree() {
		return largestKillingSpree;
	}
	public void setLargestKillingSpree(long largestKillingSpree) {
		this.largestKillingSpree = largestKillingSpree;
	}
	public int getItem1() {
		return item1;
	}
	public void setItem1(int item1) {
		this.item1 = item1;
	}
	public long getQuadraKills() {
		return quadraKills;
	}
	public void setQuadraKills(long quadraKills) {
		this.quadraKills = quadraKills;
	}
	public long getTeamObjective() {
		return teamObjective;
	}
	public void setTeamObjective(long teamObjective) {
		this.teamObjective = teamObjective;
	}
	public long getTotalTimeCrowdControlDealt() {
		return totalTimeCrowdControlDealt;
	}
	public void setTotalTimeCrowdControlDealt(long totalTimeCrowdControlDealt) {
		this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
	}
	public int getLongestTimeSpentLiving() {
		return longestTimeSpentLiving;
	}
	public void setLongestTimeSpentLiving(int longestTimeSpentLiving) {
		this.longestTimeSpentLiving = longestTimeSpentLiving;
	}
	public long getWardsKilled() {
		return wardsKilled;
	}
	public void setWardsKilled(long wardsKilled) {
		this.wardsKilled = wardsKilled;
	}
	public boolean isFirstTowerAssist() {
		return firstTowerAssist;
	}
	public void setFirstTowerAssist(boolean firstTowerAssist) {
		this.firstTowerAssist = firstTowerAssist;
	}
	public boolean isFirstTowerKill() {
		return firstTowerKill;
	}
	public void setFirstTowerKill(boolean firstTowerKill) {
		this.firstTowerKill = firstTowerKill;
	}
	public int getItem2() {
		return item2;
	}
	public void setItem2(int item2) {
		this.item2 = item2;
	}
	public int getItem3() {
		return item3;
	}
	public void setItem3(int item3) {
		this.item3 = item3;
	}
	public int getItem0() {
		return item0;
	}
	public void setItem0(int item0) {
		this.item0 = item0;
	}
	public boolean isFirstBloodAssist() {
		return firstBloodAssist;
	}
	public void setFirstBloodAssist(boolean firstBloodAssist) {
		this.firstBloodAssist = firstBloodAssist;
	}
	public long getVisionScore() {
		return visionScore;
	}
	public void setVisionScore(long visionScore) {
		this.visionScore = visionScore;
	}
	public int getWardsPlaced() {
		return wardsPlaced;
	}
	public void setWardsPlaced(int wardsPlaced) {
		this.wardsPlaced = wardsPlaced;
	}
	public int getItem4() {
		return item4;
	}
	public void setItem4(int item4) {
		this.item4 = item4;
	}
	public int getItem5() {
		return item5;
	}
	public void setItem5(int item5) {
		this.item5 = item5;
	}
	public int getItem6() {
		return item6;
	}
	public void setItem6(int item6) {
		this.item6 = item6;
	}
	public int getTurretKills() {
		return turretKills;
	}
	public void setTurretKills(int turretKills) {
		this.turretKills = turretKills;
	}
	public int getTripleKills() {
		return tripleKills;
	}
	public void setTripleKills(int tripleKills) {
		this.tripleKills = tripleKills;
	}
	public long getDamageSelfMitigated() {
		return damageSelfMitigated;
	}
	public void setDamageSelfMitigated(long damageSelfMitigated) {
		this.damageSelfMitigated = damageSelfMitigated;
	}
	public int getChampLevel() {
		return champLevel;
	}
	public void setChampLevel(int champLevel) {
		this.champLevel = champLevel;
	}
	public int getNodeNeutralizeAssist() {
		return nodeNeutralizeAssist;
	}
	public void setNodeNeutralizeAssist(int nodeNeutralizeAssist) {
		this.nodeNeutralizeAssist = nodeNeutralizeAssist;
	}
	public boolean isFirstInhibitorKill() {
		return firstInhibitorKill;
	}
	public void setFirstInhibitorKill(boolean firstInhibitorKill) {
		this.firstInhibitorKill = firstInhibitorKill;
	}
	public int getGoldEarned() {
		return goldEarned;
	}
	public void setGoldEarned(int goldEarned) {
		this.goldEarned = goldEarned;
	}
	public long getMagicalDamageTaken() {
		return magicalDamageTaken;
	}
	public void setMagicalDamageTaken(long magicalDamageTaken) {
		this.magicalDamageTaken = magicalDamageTaken;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getDoubleKills() {
		return doubleKills;
	}
	public void setDoubleKills(int doubleKills) {
		this.doubleKills = doubleKills;
	}
	public int getNodeCaptureAssist() {
		return nodeCaptureAssist;
	}
	public void setNodeCaptureAssist(int nodeCaptureAssist) {
		this.nodeCaptureAssist = nodeCaptureAssist;
	}
	public long getTrueDamageTaken() {
		return trueDamageTaken;
	}
	public void setTrueDamageTaken(long trueDamageTaken) {
		this.trueDamageTaken = trueDamageTaken;
	}
	public int getNodeNeutralize() {
		return nodeNeutralize;
	}
	public void setNodeNeutralize(int nodeNeutralize) {
		this.nodeNeutralize = nodeNeutralize;
	}
	public boolean isFirstInhibitorAssist() {
		return firstInhibitorAssist;
	}
	public void setFirstInhibitorAssist(boolean firstInhibitorAssist) {
		this.firstInhibitorAssist = firstInhibitorAssist;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public int getUnrealKills() {
		return unrealKills;
	}
	public void setUnrealKills(int unrealKills) {
		this.unrealKills = unrealKills;
	}
	public int getNeutralMinionsKilled() {
		return neutralMinionsKilled;
	}
	public void setNeutralMinionsKilled(int neutralMinionsKilled) {
		this.neutralMinionsKilled = neutralMinionsKilled;
	}
	public int getObjectivePlayerScore() {
		return objectivePlayerScore;
	}
	public void setObjectivePlayerScore(int objectivePlayerScore) {
		this.objectivePlayerScore = objectivePlayerScore;
	}
	public int getCombatPlayerScore() {
		return combatPlayerScore;
	}
	public void setCombatPlayerScore(int combatPlayerScore) {
		this.combatPlayerScore = combatPlayerScore;
	}
	public long getDamageDealtToTurrets() {
		return damageDealtToTurrets;
	}
	public void setDamageDealtToTurrets(long damageDealtToTurrets) {
		this.damageDealtToTurrets = damageDealtToTurrets;
	}
	public int getAltarsNeutralized() {
		return altarsNeutralized;
	}
	public void setAltarsNeutralized(int altarsNeutralized) {
		this.altarsNeutralized = altarsNeutralized;
	}
	public long getPhysicalDamageDealtToChampions() {
		return physicalDamageDealtToChampions;
	}
	public void setPhysicalDamageDealtToChampions(long physicalDamageDealtToChampions) {
		this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
	}
	public int getGoldSpent() {
		return goldSpent;
	}
	public void setGoldSpent(int goldSpent) {
		this.goldSpent = goldSpent;
	}
	public long getTrueDamageDealt() {
		return trueDamageDealt;
	}
	public void setTrueDamageDealt(long trueDamageDealt) {
		this.trueDamageDealt = trueDamageDealt;
	}
	public long getTrueDamageDealtToChampions() {
		return trueDamageDealtToChampions;
	}
	public void setTrueDamageDealtToChampions(long trueDamageDealtToChampions) {
		this.trueDamageDealtToChampions = trueDamageDealtToChampions;
	}
	public int getParticipantId() {
		return participantId;
	}
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	public int getPentaKills() {
		return pentaKills;
	}
	public void setPentaKills(int pentaKills) {
		this.pentaKills = pentaKills;
	}
	public long getTotalHeal() {
		return totalHeal;
	}
	public void setTotalHeal(long totalHeal) {
		this.totalHeal = totalHeal;
	}
	public int getTotalMinionsKilled() {
		return totalMinionsKilled;
	}
	public void setTotalMinionsKilled(int totalMinionsKilled) {
		this.totalMinionsKilled = totalMinionsKilled;
	}
	public boolean isFirstBloodKill() {
		return firstBloodKill;
	}
	public void setFirstBloodKill(boolean firstBloodKill) {
		this.firstBloodKill = firstBloodKill;
	}
	public int getNodeCapture() {
		return nodeCapture;
	}
	public void setNodeCapture(int nodeCapture) {
		this.nodeCapture = nodeCapture;
	}
	public int getLargestMultiKill() {
		return largestMultiKill;
	}
	public void setLargestMultiKill(int largestMultiKill) {
		this.largestMultiKill = largestMultiKill;
	}
	public int getSightWardsBoughtInGame() {
		return sightWardsBoughtInGame;
	}
	public void setSightWardsBoughtInGame(int sightWardsBoughtInGame) {
		this.sightWardsBoughtInGame = sightWardsBoughtInGame;
	}
	public long getTotalDamageDealtToChampions() {
		return totalDamageDealtToChampions;
	}
	public void setTotalDamageDealtToChampions(long totalDamageDealtToChampions) {
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
	}
	public int getTotalUnitsHealed() {
		return totalUnitsHealed;
	}
	public void setTotalUnitsHealed(int totalUnitsHealed) {
		this.totalUnitsHealed = totalUnitsHealed;
	}
	public int getInhibitorKills() {
		return inhibitorKills;
	}
	public void setInhibitorKills(int inhibitorKills) {
		this.inhibitorKills = inhibitorKills;
	}
	public int getTotalScoreRank() {
		return totalScoreRank;
	}
	public void setTotalScoreRank(int totalScoreRank) {
		this.totalScoreRank = totalScoreRank;
	}
	public long getTotalDamageTaken() {
		return totalDamageTaken;
	}
	public void setTotalDamageTaken(long totalDamageTaken) {
		this.totalDamageTaken = totalDamageTaken;
	}
	public int getKillingSprees() {
		return killingSprees;
	}
	public void setKillingSprees(int killingSprees) {
		this.killingSprees = killingSprees;
	}
	public long getTimeCCingOthers() {
		return timeCCingOthers;
	}
	public void setTimeCCingOthers(long timeCCingOthers) {
		this.timeCCingOthers = timeCCingOthers;
	}
	public long getPhysicalDamageTaken() {
		return physicalDamageTaken;
	}
	public void setPhysicalDamageTaken(long physicalDamageTaken) {
		this.physicalDamageTaken = physicalDamageTaken;
	}
    
    
}
