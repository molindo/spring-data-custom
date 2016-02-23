package org.springframework.data.custom.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class JpaEntity {

	@Id
	@GeneratedValue
	private Integer id;

	private String message;
}
