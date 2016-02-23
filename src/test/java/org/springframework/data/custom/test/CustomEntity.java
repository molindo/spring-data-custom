package org.springframework.data.custom.test;

import org.springframework.data.annotation.Id;
import org.springframework.data.custom.annotation.Custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Custom
@AllArgsConstructor
@NoArgsConstructor
public class CustomEntity {

	@Id
	private Integer id;

	private String message;
}
