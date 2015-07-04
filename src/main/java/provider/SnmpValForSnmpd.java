package provider;

public class SnmpValForSnmpd {
	private String oid;
	private String valType;
	private Object val;

	public SnmpValForSnmpd() {
		super();
	}

	public SnmpValForSnmpd(String oid, String valType, Object val) {
		super();
		this.oid = oid;
		this.valType = valType;
		this.val = val;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getValType() {
		return valType;
	}

	public void setValType(String oidType) {
		this.valType = oidType;
	}

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

}
