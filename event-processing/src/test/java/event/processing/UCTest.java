package event.processing;

public interface UCTest {

    final String UC1_QUERY1 = "CONDITION doorClosed=1 AND NOT isMovement=1 FROM OfficeRoom WIN:TIME(600)";
    final String UC1_QUERY2 = "CONDITION doorCLosed=1 AND isMovement=1 FROM OfficeRoom WIN:TIME(600)";
    final String UC2_QUERY1 = "CONDITION AVG(value) > 90 AND name='utilizationInPercent' FROM TechnicalFloor WIN:TIME(600)";
    final String UC3_QUERY1 = "CONDITION doorClosed = 1 AND deskA = 1 AND deskB = 0 FROM OfficeRoom WIN:TIME(10)";
    final String UC3_QUERY2 = "CONDITION doorClosed = 1 AND deskA = 1 AND deskB = 1 FROM OfficeRoom WIN:TIME(10)";
    final String UC4_QUERY1 = "CONDITION buildingEntered = 1 AND specialTreatment = 1 AND deskB = 1 FROM EntranceFloor";
    final String UC5_QUERY1 = "CONDITION AVG(waterUsageInLitre) > 10 FROM OfficeFloor WIN:TIME(3600)";
    final String UC6_QUERY1 = "CONDITION temperature > 32 AND smokeDetector = 1 FROM OfficeFloor WIN:TIME(120)";

    final String UC1_NAME_QUERY1 = "UC1_QUERY1";
    final String UC1_NAME_QUERY2 = "UC1_QUERY2";
    final String UC2_NAME_QUERY1 = "UC2_QUERY1";
    final String UC3_NAME_QUERY1 = "UC3_QUERY1";
    final String UC3_NAME_QUERY2 = "UC3_QUERY2";
    final String UC4_NAME_QUERY1 = "UC4_QUERY1";
    final String UC5_NAME_QUERY1 = "UC5_QUERY1";
    final String UC6_NAME_QUERY1 = "UC6_QUERY1";

    final String UC1_RULE1 = "CONDITION doorClosed=1 AND NOT isMovement=1 FROM OfficeRoom WIN:TIME(600)";
    final String UC1_RULE2 = "CONDITION doorCLosed=1 AND isMovement=1 FROM OfficeRoom WIN:TIME(600)";
    final String UC2_RULE1 = UC2_NAME_QUERY1 + " TRIGGERS air_condition, technical_floor, REPORT_WARNING = 1";
    final String UC3_RULE1 = "CONDITION doorClosed = 1 AND deskA = 1 AND deskB = 0 FROM OfficeRoom WIN:TIME(10)";
    final String UC3_RULE2 = "CONDITION doorClosed = 1 AND deskA = 1 AND deskB = 1 FROM OfficeRoom WIN:TIME(10)";
    final String UC4_RULE1 = "CONDITION buildingEntered = 1 AND specialTreatment = 1 AND deskB = 1 FROM EntranceFloor";
    final String UC5_RULE1 = "CONDITION AVG(waterUsageInLitre) > 10 FROM OfficeFloor WIN:TIME(3600)";
    final String UC6_RULE1 = "CONDITION temperature > 32 AND smokeDetector = 1 FROM OfficeFloor WIN:TIME(120)";

    final String UC1_NAME_RULE1 = "UC1_RULE1";
    final String UC1_NAME_RULE2 = "UC1_RULE2";
    final String UC2_NAME_RULE1 = "UC2_RULE1";
    final String UC3_NAME_RULE1 = "UC3_RULE1";
    final String UC3_NAME_RULE2 = "UC3_RULE2";
    final String UC4_NAME_RULE1 = "UC4_RULE1";
    final String UC5_NAME_RULE1 = "UC5_RULE1";
    final String UC6_NAME_RULE1 = "UC6_RULE1";

}
