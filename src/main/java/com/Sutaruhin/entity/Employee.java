package com.Sutaruhin.entity;
import java.time.LocalDateTime;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@SuppressWarnings("deprecation")
@Data
@Entity
@Table(name = "employee")
//@Where(clause = "delete_flag=false")
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
	@jakarta.validation.constraints.NotBlank
	@Column(length = 10)
	String code;

	@Column(length = 20,nullable = false)
	@jakarta.validation.constraints.NotBlank
	@Length(max=20)
	private String name;

	@Column(length = 10,nullable = false)
	@NotNull(message="権限をリストから選択してください。")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(length = 255,nullable = false)
//	更新時にパスワードを変更しない場合、空欄を許可しているためバリデーションチェックは設定しない
//	@jakarta.validation.constraints.NotBlank
	@Length(max = 255)
	private String password;

	@Column(columnDefinition = "TINYINT",nullable= false)
	private boolean deleteFlag;

	@Column(nullable = false)
	private LocalDateTime createdAt;


	@Column(nullable = false)
	private LocalDateTime updatedAt;

}
