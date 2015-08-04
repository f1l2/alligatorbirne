package configuration.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EPROCE_DATA_SOURCE", uniqueConstraints=@UniqueConstraint(columnNames={"eprocId", "domain", "deviceInformation"}))
public class EProcDataSourceJPA {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Long eprocId;
	
	@Column
	private String domain;
	
	@Column
	private String deviceInformation;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEprocId() {
		return eprocId;
	}

	public void setEprocId(Long eprocId) {
		this.eprocId = eprocId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDeviceInformation() {
		return deviceInformation;
	}

	public void setDeviceInformation(String deviceInformation) {
		this.deviceInformation = deviceInformation;
	}
}
