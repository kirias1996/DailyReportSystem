package com.Sutaruhin.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
	public static enum Role{
		ADMIN("管理者"),GENERAL("一般");
		private String name;
		private Role(String name){
			this.name =name;
		}

		public String getValue(String name) {
			return this.name();
		}
	}


	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	String code;

	@Column(length = 20,nullable = false)
	private String name;

	@Column(length = 10,nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(length = 255,nullable = false)
	private String password;

	@Column(columnDefinition = "TINYINT",nullable= false)
	private boolean deleteFlag;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

}
