package me.dio.web.personapi.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.web.personapi.model.PhoneType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
	
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PhoneType type;
	
	@NotEmpty
	@Size(min = 13, max = 14)
	private String numero;
}