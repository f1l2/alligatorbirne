package common.data;

import java.util.ArrayList;
import java.util.List;

public class MeasurementData {

	private List<MeasurementPoint> measurementPoints = new ArrayList<MeasurementPoint>();
	
	public void add(MeasurementPoint point) {
		this.measurementPoints.add(point);
	}
	
	public List<MeasurementPoint> getMeasurementPoints() {
		return this.measurementPoints;
	}
	
}
