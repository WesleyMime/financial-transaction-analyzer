package br.com.fta.transaction.domain;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class BankAccount {

	@NotBlank
	private final String bank;
	@NotBlank
	private final String agency;
	@NotBlank
	private final String account;

	public BankAccount(String bank, String agency, String account) {
		this.bank = bank;
		this.agency = agency;
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public String getAgency() {
		return agency;
	}

	public String getAccount() {
		return account;
	}

	@Override
	public String toString() {
		return "BankAccount [bank=" + bank + ", agency=" + agency + ", account=" + account + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, agency, bank);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(account, other.account) && Objects.equals(agency, other.agency)
				&& Objects.equals(bank, other.bank);
	}
}
