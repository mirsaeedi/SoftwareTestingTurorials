package tutorial.core.banking.models;

public enum TransferStatus{
	
	Valid,
	Fraud,
	Error,
	NotEnoughMoneyError,
	AccountIsBlockedError,
	MaximumAllowedBalanceExceededError,
	TransferAmountIsNotValid
	
}