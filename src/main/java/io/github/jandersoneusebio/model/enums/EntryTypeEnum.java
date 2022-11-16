package io.github.jandersoneusebio.model.enums;

public enum EntryTypeEnum {

	CPF_CNPJ,
	PHONE,
	EMAIL,
	EVP;
	
	public static boolean verifyIfExists(String entryType) {
		for(EntryTypeEnum value : EntryTypeEnum.values()) {
			if(value.name().equals(entryType)) {
				return true;
			}
		}
		return false;
	}
	
}
