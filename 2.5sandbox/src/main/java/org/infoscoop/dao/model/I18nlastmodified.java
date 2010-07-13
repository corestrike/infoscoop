package org.infoscoop.dao.model;

// Generated 2010/03/29 16:21:53 by Hibernate Tools 3.3.0.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * IsI18nlastmodified generated by hbm2java
 */
@Entity
@Table(name = "is_i18nlastmodified", uniqueConstraints = @UniqueConstraint(columnNames = "type"))
public class I18nlastmodified implements java.io.Serializable {

	private Integer id;
	private String type;
	private Date lastmodified;

	public I18nlastmodified() {
	}

	public I18nlastmodified(String type, Date lastmodified) {
		this.type = type;
		this.lastmodified = lastmodified;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "type", unique = true, nullable = false, length = 32)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastmodified", nullable = false, length = 19)
	public Date getLastmodified() {
		return this.lastmodified;
	}

	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

}