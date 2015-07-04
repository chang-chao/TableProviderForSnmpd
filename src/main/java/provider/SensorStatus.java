package provider;

public class SensorStatus {
	private int port;
	private String value;
	private String status;
	
	
	public SensorStatus() {
		super();
	}
	public SensorStatus(int port, String value, String status) {
		super();
		this.port = port;
		this.value = value;
		this.status = status;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
