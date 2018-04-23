package no.ntnu.item.trainvisualsystem.jsonparser;

public class TrainInformation {
	
	public double xCoordinate;
	public double yCoordinate;
	public int TrainNo;
	public double Speed;
	public String Station; 
	public long Delay;
	
	
		
	
	public double getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public double getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	public int getTrainNo() {
		return TrainNo;
	}
	public void setTrainNo(int trainNo) {
		TrainNo = trainNo;
	}
	public double getSpeed() {
		return Speed;
	}
	public void setSpeed(double speed) {
		Speed = speed;
	}
	public String getStation() {
		return Station;
	}
	public void setStation(String station) {
		Station = station;
	}
	
	public void setDelay(long delay) {
		Delay = delay;
	}
	public long getDelay() {
		return Delay;
	}
	
	

	
	
	
	
}
